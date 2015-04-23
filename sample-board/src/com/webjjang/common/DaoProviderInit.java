package com.webjjang.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.webjjang.board.dao.BoardDaoProvider;
import com.webjjang.notice.dao.NoticeDaoProvider;
import com.webjjang.qna.dao.QnaDaoProvider;

/**
 * Servlet implementation class DaoProviderInit1
 */
//@WebServlet("/DaoProviderInit1")
public class DaoProviderInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 * 서버가 실행되면서 자동으로 실행시켜주는 메소드 : init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println
		("com.webjjang.common.DaoProviderInit.init()");
		String dbms=getInitParameter("dbms");
		System.out.println
		("DBMS의 종류:"+dbms);
		// 값이 셋팅되어 있지 않으면 oracle을 기본 값으로
		if(dbms==null) dbms = "oracle";
		// 각각의 개발단위의 DaoProvider에 값을 넣어 준다.
		BoardDaoProvider.getInstance().setDbms(dbms);
		QnaDaoProvider.getInstance().setDbms(dbms);
		NoticeDaoProvider.getInstance().setDbms(dbms);
	}

}
