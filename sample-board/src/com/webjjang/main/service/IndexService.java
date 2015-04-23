package com.webjjang.main.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.dao.BoardDaoProvider;
import com.webjjang.board.model.Board;
import com.webjjang.board.model.BoardListView;
import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;

public class IndexService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IndexService.process()");
		
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("main/index.do");
		return forwardInfo;
	}

}
