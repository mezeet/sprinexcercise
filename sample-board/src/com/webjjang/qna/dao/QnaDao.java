package com.webjjang.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.webjjang.board.model.AttachedFile;
import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.qna.model.Qna;

public abstract class QnaDao {
	// 공통이 아닌 메소드를 추상 메소드로 선언
	public abstract int write
	(Connection con, Qna qna) throws SQLException;
	public abstract int replyWrite
	(Connection con, Qna qna) throws SQLException;
	public abstract int writeFile
	(Connection con, int no, List<AttachedFile> fileList)
			throws SQLException;
	public abstract List<Qna> list
	(Connection con, int startRow, int endRow,
	  String searchKeyArr[], String searchWord)
			throws SQLException;
	public abstract List<Qna> mainList
	(Connection con, int startRow, int endRow)
		throws SQLException;
	// ******* 공통으로 사용되는(동일한 코드) 메소드를 구현한다.
	// 글보기 - 선택한 번호의 하나의 글 보기
	public Qna view(Connection con, int no)
	throws SQLException{
		System.out.println("QnaDao.view()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try{
			//sql 작성
			String sql="select no,title,content,writer, "
					+ " to_char(wdate, 'yyyy-mm-dd') wdate,"
					+ " target "
					+ " from qna "
					+ " where no=?";
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null)
				if(rs.next())
					return makeFromViewResultSet(rs);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return null;
	}

	// Message(bean)에 rs에 있는 데이터를 담는다.List용 메소드
	protected Object makeFromResultSet(ResultSet rs)
			throws SQLException {
		Qna qna = new Qna();
		qna.setNo(rs.getInt("no"));
		qna.setTitle(rs.getString("title"));
		qna.setWriter(rs.getString("writer"));
		qna.setWdate(rs.getString("wdate"));
		qna.setTarget(rs.getInt("target"));
		return qna;
	}
	
	// Message(bean)에 rs에 있는 데이터를 담는다.List용 메소드
	protected Object makeFromListResultSet(ResultSet rs)
			throws SQLException {
		Qna qna = (Qna)makeFromResultSet(rs);
		qna.setRnum(rs.getInt("rnum"));
		qna.setLevNo(rs.getInt("levNo"));
		return qna;
	}
	
	// Message(bean)에 rs에 있는 데이터를 담는다.view용 메소드
	protected Qna makeFromViewResultSet(ResultSet rs)
			throws SQLException {
		Qna qna = (Qna)makeFromResultSet(rs);
		// 추가로 content만 담는다.
		qna.setContent(rs.getString("content"));
		return qna;
	}

	// 데이터의 갯수(전체 데이터)를 세는 메소드
	public int selectCount
	(Connection con,String searchKeyArr[], String searchWord)
		throws SQLException{
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//		전달받은 데이터 확인
		System.out.println("dao 검색키:"+ searchKeyArr);
		System.out.println("dao 검색단어:"+ searchWord);

		// 처리
		try{
			//sql 작성
			String sql="select count(*) from qna ";
			String searchStr = "";
			// 검색에 대한 처리 만들기
			if(searchWord!=null && !searchWord.equals("")){
				sql += " where 1=2 ";
				for(String searchKey: searchKeyArr){
					searchStr += " or " + searchKey + " like ? ";
				}
			}
			sql += searchStr;
			System.out.println(sql);
			//상태 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			//실행 select: executeQuery()
			// ?에 대한 데이터 셋팅
			int idx=1;
			//검색을 위한 데이터 셋팅
			if(searchWord!=null && !searchWord.equals(""))
				for(int i=0;i<searchKeyArr.length;i++){
					pstmt.setString(idx++, "%"+searchWord+"%");
				}
			rs=pstmt.executeQuery();
			if(rs!=null)
				if(rs.next())
					return rs.getInt(1);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return 0;
	}

//	입력된 글번호를 가져오는 메소드
	public int currentNo(Connection con)
			throws SQLException{
			// 사용할 객체 선언
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			// 처리
			try{
				//sql 작성
				String sql="select max(no) from board";
				//상태
				pstmt = con.prepareStatement(sql);
				//실행 select: executeQuery()
				rs=pstmt.executeQuery();
				if(rs!=null)
					if(rs.next())
						return rs.getInt(1);
			} finally {
				// 처리가 된 후 객체 닫기
				JdbcUtil.close(pstmt);JdbcUtil.close(rs);
			}
			return 0;
	}

	// 수정 처리 메소드
	public int update(Connection con, Qna qna)
			throws SQLException {
		System.out.println("QnaDao.update()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			String sql=" update qna set title = ?, content=?, writer=? "
					+ " where no = ? ";
			
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getTitle());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, qna.getWriter());
			pstmt.setInt(4,qna.getNo());
			//실행 select: executeQuery()
			return pstmt.executeUpdate();
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}
	
//	삭제 처리 메소드
	public int delete(Connection con, String table, int no)
			throws SQLException {
		System.out.println("QnaDao.delete()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			String sql=" delete from "+table
					+ " where no = ? ";
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			//실행 select: executeQuery()
			return(pstmt.executeUpdate());
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}
	
//	전달 받은 테이블에 컬럼의 숫자를 전달 받은 숫자 만큼 증가 처리 메소드
	public int increaseTarget(Connection con, String table, String colum, int no, int num)
			throws SQLException {
		System.out.println("QnaDao.increaseTarget()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			String sql=" update "+table+" set "+colum+"="+colum+"+"+num
					+ " where no = ? ";
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			//실행 select: executeQuery()
			return(pstmt.executeUpdate());
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}
	
//	전달 받은 테이블에 컬럼의 숫자를 전달 받은 숫자 만큼 증가 처리 메소드
	public void setNo(Connection con, String table, Qna qna)
			throws SQLException {
		System.out.println("QnaDao.setNo()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try{
			//sql 작성
			String sql=" select refNo, ordNo, levNo from "+table
					+ " where no = ? ";
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna.getNo());
			
			//실행 select: executeQuery()
			rs = pstmt.executeQuery();
			if(rs.next()){
				qna.setRefNo(rs.getInt("refNo"));
				qna.setOrdNo(rs.getInt("ordNo"));
				qna.setLevNo(rs.getInt("levNo"));
			}
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}
	public int increaseGrateThenOrdNo(Connection con, String table, 
		int refNo, int ordNo)	throws SQLException {
		System.out.println("QnaDao.increaseGrateThenOrdNo()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			String sql=" update "+table+" set ordNo = ordNo+1 "
					+ " where refNo = ? and ordNo >= ? ";
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, refNo);
			pstmt.setInt(2, ordNo);

			//실행 select: executeQuery()
			return(pstmt.executeUpdate());
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
			}
	}
	

}
