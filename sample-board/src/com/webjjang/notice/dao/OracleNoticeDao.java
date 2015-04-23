package com.webjjang.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.notice.model.Notice;

public class OracleNoticeDao extends NoticeDao{

	// 게시판 쓰기 메소드
	@Override
	public int write(Connection con, Notice notice)
		throws SQLException {
		// TODO Auto-generated method stub
		//필요한 객체 선언
		System.out.println("OracleNoticeDao.write()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		// 처리
		try{
			//sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql=" insert into notice(no,title, "
				+ " content,startDate,endDate,attachFile) "
				+ " values(qna_seq.nextval,?,?,";
			if(notice.getStartDate().equals("sysdate"))
				sql+=" sysdate";
			else
				sql+=" ? ";
			sql	+= ", ?,?) ";
			System.out.println("OracleNoticeDao.write().sql:"+sql);
			System.out.println("startDate:"+notice.getStartDate()+"/endDate:"+notice.getEndDate());
			//상태
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			// ?에 대한 데이터 셋팅
			pstmt.setString(idx++, notice.getTitle());
			pstmt.setString(idx++, notice.getContent());
			if(!notice.getStartDate().equals("sysdate"))
				pstmt.setString(idx++, notice.getStartDate());
			pstmt.setString(idx++, notice.getEndDate());
			pstmt.setString(idx++, notice.getAttachFile());
			// 실행
			return pstmt.executeUpdate();
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);
		}
	}


	@Override
	// 상속받은 MessageDao에서 추상메소드로 선언한 메소드를
	// 구현(재정의)해 준다. 
	public List<Notice> list
	(Connection con, int startRow, int endRow,
			  String searchKeyArr[], String searchWord, String period)
	throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("OracleNoticeDao.list()");
		// 사용할 객체 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = null;
		String searchStr = "";
		searchStr += " where 1=1 ";
		// 전체 기간 공지는 아무것도 붙이지 않는다.
		if(period.equals("all")) ;
		// 현재 진행중인 공지
		if(period.equals("cur")){
			searchStr += " and startDate <= sysdate ";
			searchStr += " and to_char(endDate,'yyyy-mm-dd') >= to_char(sysdate,'yyyy-mm-dd') ";
		}
		
		// 기간이 지난 공지
		if(period.equals("gon")){
			searchStr += " and to_char(endDate,'yyyy-mm-dd') <  to_char(sysdate,'yyyy-mm-dd') ";
		}
		
		// 예약되어 있는 공지
		if(period.equals("res")){
			searchStr += " and startDate > sysdate ";
		}
		

		// 검색에 대한 처리 만들기
		if(searchWord != null && !searchWord.equals("")){
			searchStr = " and (1=2 ";
			for(String searchKey: searchKeyArr){
				searchStr += " or " + searchKey + " like ? ) ";
			}
		}
		// 처리
		try{
			//sql 작성
			// board의 내용의 리스트해당항목을 글번호 desc
			String sql=" select no,title, "
			+" to_char(startDate,'yyyy-mm-dd') startDate, "
			+" to_char(endDate,'yyyy-mm-dd') endDate, "
			+" to_char(wdate,'yyyy-mm-dd') wdate "
			+"  from notice "
			+ searchStr
//			최신글 역정렬 : 순서
			+ " order by no desc ";
			// rownum을 붙인다.
			sql=" select rownum rnum,no,title, "
			+ " startDate, endDate, wdate  "
			+ " from ("+sql+") ";
			// rnum중에서 startRow,endRow를 적용시킨다.:where
			sql=" select * from ("+sql+") "
			+ " where rnum between ? and ? ";
			
			System.out.println("OracleNoticeDao.list().sql:"+sql);
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
				list=new ArrayList<Notice>();
				while(rs.next()){
					list.add((Notice)makeFromListResultSet(rs));
				}
			}
			return list;
		} finally {
			// 처리가 된 후 객체 닫기
			JdbcUtil.close(pstmt);JdbcUtil.close(rs);
		}
	}

}
