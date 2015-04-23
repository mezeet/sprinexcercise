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

public class QnaUpdateService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("QnaUpdateService.process()");
		
		// DB 처리
		QnaDao dao 
		= QnaDaoProvider.getInstance().getDao();
		Connection con = null;
		String noStr = request.getParameter("no");
		String pageStr = request.getParameter("page");
//		System.out.println("BoardViewService.process().noStr:"+noStr);
		if(noStr==null || noStr.equals("")) return null;
		if(pageStr==null || pageStr.equals("")) return null;
		int no = Integer.parseInt(noStr);
		int page = Integer.parseInt(pageStr);
//		화면에 보여질 파일명
		String viewFile = null;
		try {
			con=JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			Qna qna= dao.view(con, no);
			if(qna != null){
				request.setAttribute("board", qna);
				request.setAttribute("page", page);
				viewFile="/qna/update.jsp";
			}
			con.commit();
			con.setAutoCommit(true);
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
			JdbcUtil.close(con);
		}
			
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/update.jsp
		forwardInfo.setForwardStr(viewFile);
		return forwardInfo;
	}
}