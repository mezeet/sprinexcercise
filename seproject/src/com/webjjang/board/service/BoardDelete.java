package com.webjjang.board.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class BoardDelete implements BoardInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 제목출력
		String str="자유 게시판 글삭제";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 게시판의 삭제할 글번호 입력
		int no = Util.inputInt("삭제할 글번호:");
		// 입력한 글을 DB에 저장
		BoardDao dao = new BoardDao();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			dao.delete(con, no);
			// 데이터 확인용 리스트 출력
//			BoardInterface obj = new BoardList();
//			obj.service();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
				try {
					if(con!=null && !con.getAutoCommit())
						con.setAutoCommit(true);
					if(con!=null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		// 결과 출력
	}

}
