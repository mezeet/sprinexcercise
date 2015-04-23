package com.webjjang.board.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.dao.BoardDaoProvider;
import com.webjjang.board.model.Board;
import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;

public class BoardViewService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardViewService.process()");
		
		// DB 처리
		BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
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
			// 조회수 1 증가
			dao.increaseTarget(con, "board", "target", no, 1);
//			increaseTarget(Connection con, String table, String colum, int no, int num)

			Board board = dao.view(con, no);
			if(board != null){
				if(board.getFileCount()>0){
					board.setFileList(dao.fileList(con, no));
//					System.out.println("BoardViewService.process().board.fileList.size:"+board.getFileList().size());
				}
				request.setAttribute("board", board);
				request.setAttribute("page", page);
				viewFile="/board/view.jsp";
			}
//			else viewFile="/error/boardList.jsp";
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
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr(viewFile);
		return forwardInfo;
	}

}
