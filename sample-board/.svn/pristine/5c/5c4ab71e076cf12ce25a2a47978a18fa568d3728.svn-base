package com.webjjang.board.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.dao.BoardDaoProvider;
import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;

public class BoardDeleteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardDeleteProcessService.process()");
		
		// 삭제할 글번호 받기
		int no = Integer.parseInt(request.getParameter("no"));
		
		// DB 처리
		BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			// 해당글이 가지고 있는 서버의 파일 리스트를 불러온다.(추후 개발)
			// DB에서 데이터를 삭제한다.
			dao.delete(con, "board", no);
			// 해당글이 가지고 있는 서버 파일 리스트의 파일을 삭제한다.(추후 개발)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 삭제 처리가 다 끝나면 글 목록(list.do)로 이동한다.
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : redirect : false
		forwardInfo.setForward(false);
		// 진행하는 곳 : list.do
		forwardInfo.setForwardStr("list.do");
		return forwardInfo;
	}

}
