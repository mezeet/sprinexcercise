package com.webjjang.qna.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.qna.dao.QnaDao;
import com.webjjang.qna.dao.QnaDaoProvider;
import com.webjjang.qna.model.Qna;

public class QnaReplyWriteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("QnaWriteProcessService.process()");

		// 입력된 데이터를 꺼내오기
		// 원본 글번호와 페이지를 받는다.
		int no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		String title = request.getParameter("title");
//		System.out.println("제목:"+title);
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		// 파일을 제외한 데이터 셋팅
		Qna qna = new Qna();
		qna.setNo(no);
		qna.setTitle(title);
		qna.setContent(content);
		qna.setWriter(writer);
		
		// DB 처리
		QnaDao dao 
		= QnaDaoProvider.getInstance().getDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 원본글의 관련글, 순서, 레벨의 정보를 가져 옵니다.
			dao.setNo(con, "qna", qna);
			// 관련글
			qna.setRefNo(qna.getRefNo());
			// 글순서
			qna.setOrdNo(qna.getOrdNo()+1);
			// 구해진 글 순서 보다 같거나 큰 글들의 1증가 시킨다.
			dao.increaseGrateThenOrdNo(con,"qna", qna.getRefNo(), qna.getOrdNo());
			// 글레벨
			qna.setLevNo(qna.getLevNo()+1);
			// 부모글
			qna.setParentNo(qna.getNo());
			
			System.out.println("원본글:"+no+"/관련글:"+qna.getRefNo()
					+"/글순서:"+qna.getOrdNo()+"/글레벨:"+qna.getLevNo()
					+"/부모글:"+qna.getParentNo());
			
			// 게시판에 글을 쓴다.
			dao.replyWrite(con, qna);
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JdbcUtil.close(con);
		}
			
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : redirect : false
		forwardInfo.setForward(false);
		// 진행하는 곳 : list.do
		forwardInfo.setForwardStr("list.do?page="+page);
		return forwardInfo;
	}

}
