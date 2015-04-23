package com.webjjang.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.model.BoardBean;
import com.webjjang.board.model.BoardCommentBean;
import com.webjjang.qna.model.QnaBean;

public class QnaDao {
	public List<BoardCommentBean>
	commentList(Connection con, int no)
	throws SQLException{
		// TODO Auto-generated method stub
		List<BoardCommentBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " select no,content,writer, "
		+ " to_char(wdate,'yyyy-mm-dd') wdate "
		+ " from board_com "
		+ " where bno = ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		if(rs!=null){ // 데이터가 있으면 list를 생성
			list = new ArrayList<>();
		}
		// 6. 결과표시
		while(rs.next()){
			// List에 BoardBean 데이터 채우기
			BoardCommentBean bean
			= new BoardCommentBean();
			bean.setNo(rs.getInt("no"));
			bean.setContent(rs.getString("content"));
			bean.setWriter(rs.getString("writer"));
			bean.setWdate(rs.getString("wdate"));
			list.add(bean);
		}
		pstmt.close();rs.close();
//		System.out.println
//		("BoardDao.commentList():list:"+list);
		return list;
	} // end of commentList()

	public void delete(Connection con, int no)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " delete from qna "
		+ " where no = ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		// 5. 실행
		// - DB이상, 쿼리 이상 : SQLException 생성 throw
		// - 실행 결과가 1 인 경우 : 해당 글이 있어서 delete 성공
		// - 실행 결과가 0 인 경우 : 해당 글이 없다.
		int result = pstmt.executeUpdate();
		// 6. 표시
		if(result == 0){
			throw new SQLException("해당 글이 없습니다.");
		}else{
			System.out.println("해당글이 성공적으로 삭제되었습니다.");
		}
		// 7. 닫기
		pstmt.close();
	}

	public void incerease(Connection con, int no)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " update qna set target = target+1 "
		+ " where no = ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		// 5. 실행
		// - DB이상, 쿼리 이상 : SQLException 생성 throw
		// - 실행 결과가 1 인 경우 : 해당 글이 있어서 update 성공
		// - 실행 결과가 0 인 경우 : 해당 글이 없다.
		int result = pstmt.executeUpdate();
//		System.out.println(result);
		if(result == 0)
			throw new SQLException("해당 글이 없습니다.");
		pstmt.close();
	}// end of increase()
	
	public void incereaseReplyOrdNo
	(Connection con, int refNo, int ordNo)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " update qna set ordNo = ordNo +1 "
		+ " where refNo = ? and ordNo >= ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, refNo);
		pstmt.setInt(2, ordNo);
		// 5. 실행
		// - DB이상, 쿼리 이상 : SQLException 생성 throw
		// - 실행 결과가 1 인 경우 : 해당 글이 있어서 update 성공
		// - 실행 결과가 0 인 경우 : 해당 글이 없다.
		pstmt.executeUpdate();
		pstmt.close();
	}// end of increase()
	
	// 원본글 작성을 위한 글쓰기
	public void insert(Connection con, QnaBean bean)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" insert into qna(no,title,content,writer,"
		+" refNo,ordNo,levNo,parentNo) "
		+" values(qna_seq.nextval, ?,?,?,"
		+" qna_seq.nextval,1,0,qna_seq.nextval)";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		String title = bean.getTitle();
		String content = bean.getContent();
		String writer = bean.getWriter();
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		// 5. 실행
		pstmt.executeUpdate();
		// 6. 결과표시
		System.out.println("성공적으로 글쓰기가 되었습니다.");
		pstmt.close();
	} // end of insert()

	public void insertReply(Connection con, QnaBean bean)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" insert into qna(no,title,content,writer,"
		+" refNo,ordNo,levNo,parentNo) "
		+" values(qna_seq.nextval, ?,?,?,"
		+" ?,?,?,?)";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		String title = bean.getTitle();
		String content = bean.getContent();
		String writer = bean.getWriter();
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		pstmt.setInt(4, bean.getRefNo());
		pstmt.setInt(5, bean.getOrdNo());
		pstmt.setInt(6, bean.getLevNo());
		pstmt.setInt(7, bean.getParentNo());
		// 5. 실행
		pstmt.executeUpdate();
		// 6. 결과표시
		System.out.println("성공적으로 답변쓰기가 되었습니다.");
		pstmt.close();
	} // end of insertReply()

	public List<QnaBean> list(Connection con)
	throws SQLException{
		// TODO Auto-generated method stub
		List<QnaBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= "select no,title,writer, "
		+" to_char(wdate,'yyyy-mm-dd') wdate, target "
		+" , refNo,ordNo,levNo,parentNo "
		+" from qna order by refNo asc ,  ordNo asc";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		// 5. 실행
		rs = pstmt.executeQuery();
		if(rs!=null) // 데이터가 있으면 list를 생성
			list = new ArrayList<>();
		// 6. 결과표시
		while(rs.next()){
			// List에 BoardBean 데이터 채우기
			QnaBean bean = new QnaBean();
			bean.setNo(rs.getInt("no"));
			bean.setTitle(rs.getString("title"));
			bean.setWriter(rs.getString("writer"));
			bean.setWdate(rs.getString("wdate"));
			bean.setTarget(rs.getInt("target"));
			bean.setRefNo(rs.getInt("refNo"));
			bean.setOrdNo(rs.getInt("ordNo"));
			bean.setLevNo(rs.getInt("levNo"));
			bean.setParentNo(rs.getInt("parentNo"));
			list.add(bean);
		}
		pstmt.close();rs.close();
		return list;
	} // list()

	public QnaBean view(Connection con, int no)
	throws SQLException{
		// TODO Auto-generated method stub
		QnaBean bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " select no,title,content,writer, "
		+ " to_char(wdate,'yyyy-mm-dd') wdate, "
		+ " target from qna "
		+ " where no = ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 결과표시
		if(rs.next()){
			// BoardBean 데이터 채우기
			bean = new QnaBean();
			bean.setNo(rs.getInt("no"));
			bean.setTitle(rs.getString("title"));
			bean.setWriter(rs.getString("writer"));
			bean.setContent(rs.getString("content"));
			bean.setWdate(rs.getString("wdate"));
			bean.setTarget(rs.getInt("target"));
		}
		pstmt.close();rs.close();
		if(bean==null)
		throw new SQLException("데이터가 없습니다.");
		return bean;
	} //end of view()

	public void update(Connection con, BoardBean bean)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" update qna "
		+" set title=?, content=?, writer=? "
		+" where no = ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		int no = bean.getNo();
		String title = bean.getTitle();
		String content = bean.getContent();
		String writer = bean.getWriter();
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		pstmt.setInt(4, no);
		// 5. 실행
		pstmt.executeUpdate();
		// 6. 결과표시
		System.out.println("성공적으로 글수정이 되었습니다.");
		pstmt.close();
	}

	public List<BoardBean>
	search(Connection con, String item, String searchStr)
	throws SQLException{
		// TODO Auto-generated method stub
		List<BoardBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" select no,title,writer, "
		+" to_char(wdate,'yyyy-mm-dd') wdate,target "
		+" from board "
		+" where 1=2 "
		+" or "+item+" like ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+searchStr+"%");
		// 5. 실행
		rs = pstmt.executeQuery();
		if(rs!=null) // 데이터가 있으면 list를 생성
			list = new ArrayList<>();
		// 6. 결과표시
		while(rs.next()){
			// List에 BoardBean 데이터 채우기
			BoardBean bean = new BoardBean();
			bean.setNo(rs.getInt("no"));
			bean.setTitle(rs.getString("title"));
			bean.setWriter(rs.getString("writer"));
			bean.setWdate(rs.getString("wdate"));
			bean.setTarget(rs.getInt("target"));
			list.add(bean);
		}
		pstmt.close();rs.close();
		return list;
	}

	public QnaBean selectParent(Connection con, int no)
	throws SQLException{
		// TODO Auto-generated method stub
		QnaBean bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " select no,title, refNo, ordNo,levNo "
		+ " from qna "
		+ " where no = ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 결과표시
		if(rs.next()){
			// BoardBean 데이터 채우기
			bean = new QnaBean();
			bean.setNo(rs.getInt("no"));
			bean.setTitle(rs.getString("title"));
			bean.setRefNo(rs.getInt("refNo"));
			bean.setOrdNo(rs.getInt("ordNo"));
			bean.setLevNo(rs.getInt("levNo"));
		}
		pstmt.close();rs.close();
		if(bean==null)
		throw new SQLException("데이터가 없습니다.");
		return bean;
	}

}
