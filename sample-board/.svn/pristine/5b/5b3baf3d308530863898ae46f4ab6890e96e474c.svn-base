package com.webjjang.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.notice.model.Notice;
import com.webjjang.qna.model.Qna;

public abstract class NoticeDao {
	// 공통이 아닌 메소드를 추상 메소드로 선언
	public abstract int write
	(Connection con, Notice notice) throws SQLException;
	public abstract List<Notice> list
	(Connection con, int startRow, int endRow,
	  String searchKeyArr[], String searchWord, String period)
			throws SQLException;
	
	// ******* 공통으로 사용되는(동일한 코드) 메소드를 구현한다.
	// 글보기 - 선택한 번호의 하나의 글 보기
	public Notice view(Connection con, int no)
	throws SQLException{
		System.out.println("NoticeDao.view()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try{
			//sql 작성
			String sql="select no,title,content, attachFile, "
					+" to_char(startDate,'yyyy-mm-dd') startDate, "
					+" to_char(endDate,'yyyy-mm-dd') endDate, "
					+" to_char(wdate,'yyyy-mm-dd') wdate "
					+ " from notice "
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
		Notice notice = new Notice();
		notice.setNo(rs.getInt("no"));
		notice.setTitle(rs.getString("title"));
		notice.setStartDate(rs.getString("startDate"));
		notice.setEndDate(rs.getString("endDate"));
		notice.setWdate(rs.getString("wdate"));
		return notice;
	}
	
	// Message(bean)에 rs에 있는 데이터를 담는다.List용 메소드
	protected Object makeFromListResultSet(ResultSet rs)
			throws SQLException {
		Notice notice = (Notice)makeFromResultSet(rs);
		notice.setRnum(rs.getInt("rnum"));
		return notice;
	}
	
	// Message(bean)에 rs에 있는 데이터를 담는다.view용 메소드
	protected Notice makeFromViewResultSet(ResultSet rs)
			throws SQLException {
		Notice notice = (Notice)makeFromResultSet(rs);
		// 추가로 content만 담는다.
		notice.setContent(rs.getString("content"));
		notice.setAttachFile(rs.getString("attachFile"));
		return notice;
	}

	// 메인용 리스트 메소드
	public List<Qna> mainList
	(Connection con, int startRow, int endRow)
		throws SQLException{
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Qna> list=null;

		// 처리
		try{
			//sql 작성
			String sql=" select no,title,writer from qna "
					+ " where no=refNo "
					+ " order by no desc ";
			sql = " select rownum rnum,  no,title,writer from ( "+sql
					+" ) ";
			sql = "select * from ("+sql+ ") where rnum between ? and ? ";
			System.out.println(sql);
			//상태 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null){
				boolean isData = false;
				while(rs.next()){
					if(!isData){
						list=new ArrayList<Qna>();
						isData = true;
					}
					Qna qna = new Qna();
					qna.setNo(rs.getInt("no"));
					qna.setTitle(rs.getString("title"));
					qna.setWriter(rs.getString("writer"));
					list.add(qna);
				}
			}
			return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
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
			String sql="select count(*) from notice ";
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
	public int update(Connection con, Notice notice)
			throws SQLException {
		System.out.println("NoticeDao.update()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			String sql=" update notice set title = ?, content=? "
					+ " where no = ? ";
			
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3,notice.getNo());
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
