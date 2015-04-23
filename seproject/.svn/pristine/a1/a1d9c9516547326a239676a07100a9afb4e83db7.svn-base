package com.webjjang.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.model.BoardBean;
import com.webjjang.board.model.BoardCommentBean;
import com.webjjang.shop.model.PriceBean;
import com.webjjang.shop.model.ShopBean;

public class ShopDao {
	public void chageEndDate(Connection con, PriceBean bean)
	throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" update price "
		+" set endDate = to_date(?) "
		+" where code=? "
		+" and to_date(?) between startDate and endDate ";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bean.getStartDate());
		pstmt.setInt(2, bean.getCode());
		pstmt.setString(3, bean.getStartDate());
		// 5. 실행
		pstmt.executeUpdate();
		// 6. 결과표시
		System.out.println("이전 가격 종료일 변경");
		pstmt.close();
	}

	public void changeInsertPrice(Connection con,
			PriceBean bean)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" insert into price(no,code,price, "
		+" startDate,endDate) "
		+" values(price_seq.nextval, "
		+" ?,?,?,'9999-12-31') ";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bean.getCode());
		pstmt.setInt(2, bean.getPrice());
		pstmt.setString(3, bean.getStartDate());
		// 5. 실행
		pstmt.executeUpdate();
		// 6. 결과표시
		System.out.println("변경할 가격이 등록되었습니다.");
		System.out.println("가격변경이 완료되었습니다.");
		pstmt.close();
	}

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
		= " delete from board "
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
		= " update board set target = target+1 "
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
	
	public void insert(Connection con, ShopBean bean)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" insert into goods(code,cateNo,name, "
		+" content,company,productDate) "
		+" values(goods_seq.nextval,?,?,?,?,null) ";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bean.getCateNo());
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getContent());
		pstmt.setString(4, bean.getCompany());
		// 5. 실행
		pstmt.executeUpdate();
		// 6. 결과표시
		System.out.println("상품이 등록되었습니다.");
		pstmt.close();
	}

	public void insertPrice(Connection con, ShopBean bean)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" insert into price(no,code,price, "
		+" startDate,endDate) "
		+" values(price_seq.nextval, "
		+" (select max(code) from goods), "
		+" ?,sysdate,'9999-12-31') ";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bean.getPrice());
		// 5. 실행
		pstmt.executeUpdate();
		// 6. 결과표시
		System.out.println("가격이 등록되었습니다.");
		System.out.println("상품등록이 완료되었습니다.");
		pstmt.close();
	}

	public List<ShopBean> list(Connection con)
	throws SQLException{
		// TODO Auto-generated method stub
		List<ShopBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " select g.code, c.name category, g.name, "
		+ " g.company, p.price "
		+ " from goods g, category c, price p " 
		+ " where c.no=g.cateno " 
		+ " and g.code=p.code "
		+ " and sysdate between startDate and endDate "
		+ " order by g.code ";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		// 5. 실행
		rs = pstmt.executeQuery();
		if(rs!=null) // 데이터가 있으면 list를 생성
			list = new ArrayList<>();
		// 6. 결과표시
		while(rs.next()){
			// List에 BoardBean 데이터 채우기
			ShopBean bean = new ShopBean();
			bean.setCode(rs.getInt("code"));
			bean.setCategory(rs.getString("category"));
			bean.setName(rs.getString("name"));
			bean.setCompany(rs.getString("company"));
			bean.setPrice(rs.getInt("price"));
			list.add(bean);
		}
		pstmt.close();rs.close();
		return list;
	} // list()

	public BoardBean view(Connection con, int no)
	throws SQLException{
		// TODO Auto-generated method stub
		BoardBean bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		= " select no,title,content,writer, "
		+ " to_char(wdate,'yyyy-mm-dd') wdate, "
		+ " target from board "
		+ " where no = ?";
		// 4. 실행상태 & 데이터 셋팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 결과표시
		if(rs.next()){
			// BoardBean 데이터 채우기
			bean = new BoardBean();
			bean.setNo(rs.getInt("no"));
			bean.setTitle(rs.getString("title"));
			bean.setWriter(rs.getString("writer"));
			bean.setContent(rs.getString("content"));
			bean.setWdate(rs.getString("wdate"));
			bean.setTarget(rs.getInt("target"));
		}
		pstmt.close();rs.close();
		if(bean==null)
		throw new SQLException("수정할 데이터가 없습니다.");
		return bean;
	} //end of view()

	public void update(Connection con, BoardBean bean)
	throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		// 1. 드라이버확인 & 2. 연결 -> service
		// 3. sql문 작성
		String sql 
		=" update board "
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

}
