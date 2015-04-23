package com.webjjang.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.model.BoardBean;
import com.webjjang.board.view.BoardDisplay;
import com.webjjang.menu.MenuList;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class BoardSearch implements BoardInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 게시판 리스트 제목 출력
		String str="자유 게시판 검색";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 데이터베이스에서 데이터를 가져온다.
		BoardDao dao = new BoardDao();
		Connection con = null;
		try{
			// 컨넥션 가져오기
			con = DBUtil.getConnection();
			// autocommit이 실행이 안되게 해 놓는다.
			con.setAutoCommit(false);
			// 항목에 대한 메뉴 출력
			Util.printTitleAndMenu
			(MenuList.getMenu("boardSearchMenu"));
			// 검색할 항목을 선택
			String item = getItem(); 
			// 검색할 문자열 입력
			String SearchStr 
			= Util.inputMenuAndData("검색할 문자열:");
			// DB에서 검색할 문자열로 Data를 가져온다.
			List<BoardBean> list
			= dao.search(con,item,SearchStr);
//			// 가져온 데이터를 출력한다.
			BoardDisplay display = new BoardDisplay();
			display.list(list);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			// con이 null 아니고 autocommit이 false이면 true로
				try {
					if(con!=null && !con.getAutoCommit())
						con.setAutoCommit(true);
					// con.close()실행을 하면 정상적인 처리가
					// 된 경우 commit;이 비정상적인 처리가 된
					// 경우 rollback;이 자동 처리된다.
					if(con!=null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

	private String getItem() {
		// TODO Auto-generated method stub
		// 제대로 된 항목이 입력될 때까지 반복
		while(true){
			String item
			= Util.inputMenuAndData("검색할 항목:");
			switch (item) {
			case "1":
				return "title";
			case "2":
				return "content";
			case "3":
				return "writer";
			default:
				System.out.println
				("검색할 항목은 [1-3]입니다.");
			}
		}
	}

}
