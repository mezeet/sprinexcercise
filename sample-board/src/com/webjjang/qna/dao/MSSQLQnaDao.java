package com.webjjang.qna.dao;

import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.model.AttachedFile;
import com.webjjang.qna.model.Qna;

public class MSSQLQnaDao extends QnaDao{

	// 방명록 쓰기 메소드
	@Override
	public int write(Connection con, Qna qna)
		throws SQLException {
		// TODO Auto-generated method stub
		//필요한 객체 선언
		return 0;
	}

	@Override
	// 상속받은 MessageDao에서 추상메소드로 선언한 메소드를
	// 구현(재정의)해 준다. 
	public List<Qna> list
	(Connection con, int startRow, int endRow,
			  String searchKeyArr[], String searchWord)
	throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("OracleLMessageDao.selectList()");
		return null;
	}

	@Override
	public int writeFile(Connection con, int no, List<AttachedFile> fileList)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyWrite(Connection con, Qna qna) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Qna> mainList(Connection con, int startRow, int endRow)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
