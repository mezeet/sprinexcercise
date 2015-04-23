package com.webjjang.member.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;

public class MemberClauseAcceptService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("BoardWriteService.process()");
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("/board/write.jsp");
		return forwardInfo;
	}

}
