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

public class QnaWriteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("QnaWriteProcessService.process()");

		// 입력된 데이터를 꺼내오기
		String title = request.getParameter("title");
//		System.out.println("제목:"+title);
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		// 파일을 제외한 데이터 셋팅
		Qna qna = new Qna();
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
			// 게시판에 글을 쓴다.
			dao.write(con, qna);
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
		forwardInfo.setForwardStr("list.do");
		return forwardInfo;
	}

}
