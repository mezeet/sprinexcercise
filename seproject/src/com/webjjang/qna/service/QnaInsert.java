package com.webjjang.qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.webjjang.qna.dao.QnaDao;
import com.webjjang.qna.model.QnaBean;
import com.webjjang.qna.view.QnaDisplay;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class QnaInsert implements QnaInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 제목출력
		String str="Q & A 글쓰기";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// Q&A 의 글 입력 - 제목,내용,작성자
		QnaDisplay display = new QnaDisplay();
		QnaBean bean = display.inputQnaData();
		// 입력한 글을 DB에 저장
		QnaDao dao = new QnaDao();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			dao.insert(con, bean);
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
