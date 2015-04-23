package com.webjjang.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.model.AttachedFile;
import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.qna.model.Qna;

public class OracleQnaDao extends QnaDao{

	// 게시판 쓰기 메소드
	@Override
	public int write(Connection con, Qna qna)
		throws SQLException {
		// TODO Auto-generated method stub
		//필요한 객체 선언
		System.out.println("OracleQnaDao.write()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql=" insert into qna(no,title, "
				+ " content,writer, "
				+ " refNo, ordNo, levNo, parentNo) "
				+ " values(qna_seq.nextval,?,?,?,"
				+ " qna_seq.nextval,1,0,qna_seq.nextval) ";
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			pstmt.setString(1, qna.getTitle());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, qna.getWriter());
			// 실행
			return pstmt.executeUpdate();
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

	// 게시판 쓰기 메소드
	@Override
	public int replyWrite(Connection con, Qna qna)
		throws SQLException {
		// TODO Auto-generated method stub
		//필요한 객체 선언
		System.out.println("OracleQnaDao.replyWrite()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql=" insert into qna(no,title, "
				+ " content,writer, "
				+ " refNo, ordNo, levNo, parentNo) "
				+ " values(qna_seq.nextval,?,?,?,"
				+ " ?,?,?,?) ";
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			pstmt.setString(1, qna.getTitle());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, qna.getWriter());
			pstmt.setInt(4, qna.getRefNo());
			pstmt.setInt(5, qna.getOrdNo());
			pstmt.setInt(6, qna.getLevNo());
			pstmt.setInt(7, qna.getParentNo());
			
			// 실행
			return pstmt.executeUpdate();
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}


	@Override
//	파일을 첨부시키는 메소드
	public int writeFile(Connection con, 
		int no, List<AttachedFile> fileList)
		throws SQLException
	{
		System.out.println("OracleBoardDao.writeFile()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql=" insert into board_file(no,bno, "
				+" originalFile,serverFile) "
				+" values(board_file_seq.nextval,?,?,?) ";
			//상태
			pstmt = con.prepareStatement(sql);
			for(AttachedFile af : fileList){
				// ?에 대한 데이터 셋팅
				pstmt.setInt(1, no);
				pstmt.setString(2, af.getOriginalFile());
				pstmt.setString(3, af.getServerFile());
				// 실행
				pstmt.executeUpdate();
			}
			return 1;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}

	@Override
	// 상속받은 MessageDao에서 추상메소드로 선언한 메소드를
	// 구현(재정의)해 준다. 
	public List<Qna> list
	(Connection con, int startRow, int endRow,
			  String searchKeyArr[], String searchWord)
	throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("OracleQnaDao.list()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Qna> list = null;
		String searchStr = "";
		// 검색에 대한 처리 만들기
		if(searchWord != null && !searchWord.equals("")){
			searchStr = " where 1=2 ";
			for(String searchKey: searchKeyArr){
				searchStr += " or " + searchKey + " like ? ";
			}
		}
		// 처리
		try{
			//sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql=" select no,title,writer, "
			+" to_char(wdate,'yyyy-mm-dd') wdate, "
			+" target, levNo from qna "
			+ searchStr
//			질문 답변형 정렬 : 관련글,순서
			+ " order by refNo desc, ordNo ";
			// rownum을 붙인다.
			sql=" select rownum rnum,no,title,writer, "
			+ " wdate, target, levNo "
			+ " from ("+sql+") ";
			// rnum중에서 startRow,endRow를 적용시킨다.:where
			sql=" select * from ("+sql+") "
			+ " where rnum between ? and ? ";
			
			System.out.println("OracleQnaDao.list().sql:"+sql);
			//상태
			pstmt = con.prepareStatement(sql);
			// ?에 대한 데이터 셋팅
			int idx=1;
			//검색을 위한 데이터 셋팅
			if(searchWord != null && !searchWord.equals(""))
				for(int i=0;i<searchKeyArr.length;i++){
					pstmt.setString(idx++, "%"+searchWord+"%");
				}
			
			pstmt.setInt(idx++, startRow);
			pstmt.setInt(idx++, endRow);
			//실행 select: executeQuery()
			rs=pstmt.executeQuery();
			if(rs!=null){
				list=new ArrayList<Qna>();
				while(rs.next()){
					list.add((Qna)makeFromListResultSet(rs));
				}
			}
			return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
	}

	@Override
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


}
