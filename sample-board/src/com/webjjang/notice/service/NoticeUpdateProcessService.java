package com.webjjang.notice.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.notice.dao.NoticeDao;
import com.webjjang.notice.dao.NoticeDaoProvider;
import com.webjjang.notice.model.Notice;
import com.webjjang.qna.dao.QnaDao;
import com.webjjang.qna.dao.QnaDaoProvider;
import com.webjjang.qna.model.Qna;

public class NoticeUpdateProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("QnaUpdateProcessService.process()");
		
		// 입력된 데이터를 꺼내오기
		String pageStr = request.getParameter("page");
		int page = Integer.parseInt(pageStr);
		String noStr = request.getParameter("no");
		int no = Integer.parseInt(noStr);
		String title = request.getParameter("title");
//		System.out.println("제목:"+title);
		String content = request.getParameter("content");

		// 데이터 셋팅
		Notice notice = new Notice();
		notice.setNo(no);
		notice.setTitle(title);
		notice.setContent(content);

		// DB 처리
		NoticeDao dao 
		= NoticeDaoProvider.getInstance().getDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			// 게시판에 글을 수정한다.
			dao.update(con, notice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : redirect : false
		forwardInfo.setForward(false);
		// 진행하는 곳 : list.do
		forwardInfo.setForwardStr("view.do?no="+no+"&page="+page);
		return forwardInfo;
	}

}
