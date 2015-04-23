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
import com.webjjang.qna.dao.QnaDao;
import com.webjjang.qna.dao.QnaDaoProvider;

public class NoticeDeleteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("NoticeDeleteProcessService.process()");
		
		// 삭제할 글번호 받기
		int no = Integer.parseInt(request.getParameter("no"));

		// DB 처리
		NoticeDao dao 
		= NoticeDaoProvider.getInstance().getDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			dao.delete(con,"notice", no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
