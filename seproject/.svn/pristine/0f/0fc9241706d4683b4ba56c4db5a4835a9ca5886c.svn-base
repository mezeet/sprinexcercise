package com.webjjang.qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.qna.dao.QnaDao;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class QnaDelete implements QnaInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 제목출력
		String str="Q & A 글삭제";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 게시판의 삭제할 글번호 입력
		int no = Util.inputInt("삭제할 글번호:");
		// 입력한 글을 DB에 저장
		QnaDao dao = new QnaDao();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			dao.delete(con, no);
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
