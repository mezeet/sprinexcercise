package com.webjjang.notice.dao;

import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import com.webjjang.notice.model.Notice;

public class MySQLNoticeDao extends NoticeDao{

	// 방명록 쓰기 메소드
	@Override
	public int write(Connection con,  Notice notice)
		throws SQLException {
		// TODO Auto-generated method stub
		//필요한 객체 선언
		return 0;
	}

	@Override
	// 상속받은 MessageDao에서 추상메소드로 선언한 메소드를
	// 구현(재정의)해 준다. 
	public List<Notice> list
	(Connection con, int startRow, int endRow,
			  String searchKeyArr[], String searchWord, String period)
	throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("OracleLMessageDao.selectList()");
		return null;
	}

}
