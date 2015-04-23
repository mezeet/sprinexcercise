package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.model.AttachedFile;
import com.webjjang.board.model.Board;
import com.webjjang.jdbc.JdbcUtil;

public abstract class BoardDao {
	// 공통이 아닌 메소드를 추상 메소드로 선언
	public abstract int write
	(Connection con, Board board) throws SQLException;
	public abstract int writeFile
	(Connection con, int no, List<AttachedFile> fileList)
			throws SQLException;
	public abstract List<Board> list
	(Connection con, int startRow, int endRow,
			String searchKeyArr[], String searchWord)
			throws SQLException;
	
	// ******* 공통으로 사용되는(동일한 코드) 메소드를 구현한다.
	// 글보기 - 선택한 번호의 하나의 글 보기
	public Board view(Connection con, int no)
	throws SQLException{
		System.out.println("BoardDao.view()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try{
			//sql 작성
			String sql="select no,title,content,writer, "
					+ " to_char(wdate, 'yyyy-mm-dd') wdate,"
					+ " target, fileCount"
					+ " from board"
					+ " where no=?";
			System.out.println("BoardDao.view().sql:"+sql);
			System.out.println("BoardDao.view().no:"+no);
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null)
				if(rs.next())
					return makeBoardFromViewResultSet(rs);
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		return null;
	}

	// Message(bean)에 rs에 있는 기본 데이터를 담는다.
	protected Board makeBoardFromResultSet(ResultSet rs)
			throws SQLException {
		Board board = new Board();
		board.setNo(rs.getInt("no"));
		board.setTitle(rs.getString("title"));
		board.setWriter(rs.getString("writer"));
		board.setWdate(rs.getString("wdate"));
		board.setTarget(rs.getInt("target"));
		board.setFileCount(rs.getInt("fileCount"));
		return board;
	}
	
	// Message(bean)에 rs에 있는 데이터를 담는다.List용 메소드
	// rnum이니 경우 view에서는 필요없는 항목이므로 list에서만 따로 셋팅
	protected Board makeBoardFromListResultSet(ResultSet rs)
			throws SQLException {
		Board board = makeBoardFromResultSet(rs);
		board.setRnum(rs.getInt("rnum"));
		return board;
	}
	
	// Message(bean)에 rs에 있는 데이터를 담는다.view용 메소드
	// content인 경우 list에는 없고 view나 update에서만 필요하므로 따로 셋팅
	protected Board makeBoardFromViewResultSet(ResultSet rs)
			throws SQLException {
		Board board = makeBoardFromResultSet(rs);
		// 추가로 content만 담는다.
		board.setContent(rs.getString("content"));
		return board;
	}

	// 데이터의 갯수(전체 데이터)를 세는 메소드
	public int selectCount(Connection con,
			  String searchKeyArr[], String searchWord)
		throws SQLException{
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 처리
		try{
			//sql 작성
			String sql="select count(*) from board ";
			String searchStr = "";
			if(searchWord != null && !searchWord.equals("")){
				searchStr = " where 1=2 ";
				for(String searchKey: searchKeyArr){
					searchStr += " or " + searchKey + " like ? ";
				}
			}
			sql += searchStr;
			//상태
			pstmt = con.prepareStatement(sql);
			int idx=1;
			if(searchWord != null && !searchWord.equals(""))
				for(int i=0;i<searchKeyArr.length;i++){
					pstmt.setString(idx++, "%"+searchWord+"%");
				}
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
	public int update(Connection con, Board board)
			throws SQLException {
		System.out.println("BoardDao.update()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			String sql=" update board set title = ?, content=?, writer=? "
					+ " where no = ? ";
			
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			pstmt.setInt(4,board.getNo());
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
		System.out.println("BoardDao.delete()");
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
		System.out.println("BoardDao.increaseTarget()");
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
	
	// 첨부파일 리스트를 가져오는 메소드
	public List<AttachedFile> fileList(Connection con, int no)
			throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("BoardDao.fileList()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttachedFile> list = null;
		boolean createList = false;
		// 처리
		try{
			//sql 작성
			String sql="select no,bno,originalFile,serverFile "
					+ " from board_file "
					+ " where bno=? ";
			System.out.println("BoardDao.fileList().sql:"+sql);
			System.out.println("BoardDao.fileList().no:"+no);
			//상태
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null)
				while(rs.next()){
					if(!createList){
						list = new ArrayList<AttachedFile>();
						createList = true;
					}
					AttachedFile file1 = new AttachedFile();
					file1.setNo(rs.getInt("no"));
					file1.setOriginalFile(rs.getString("originalFile"));
					file1.setServerFile(rs.getString("serverFile"));
					list.add(file1);
				}
//			return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
		System.out.println("BoardDao.fileList().list.size:"+list.size());
		return list;
	}


	
	//	정해진 데이터의 테이블.컬럼을 주어진 수 만큼 감소 처리 메소드
	public int decreaseTarget(Connection con, String table, String colum, int no, int num)
			throws SQLException {
		System.out.println("BoardDao.decreaseTarget()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			String sql=" update "+table+" set "+colum+"="+colum+"-"+num
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

	
	//	정해진 데이터의 테이블에서 삭제 처리 메소드
	public int deleteFileTable(Connection con, String table, int no)
			throws SQLException {
		System.out.println("BoardDao.deleteFileTable()");
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


}


