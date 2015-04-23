package com.webjjang.member.service;

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

public class MemberDeleteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardDeleteProcessService.process()");
		
		// DB 처리
		BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
		int startRow = 0, endRow = 0;
		try {
			dao.delete(con,"member", 1);
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
