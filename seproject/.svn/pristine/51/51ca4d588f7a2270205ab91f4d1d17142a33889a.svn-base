package com.webjjang.shop.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.model.BoardBean;
import com.webjjang.board.view.BoardDisplay;
import com.webjjang.shop.dao.ShopDao;
import com.webjjang.shop.model.ShopBean;
import com.webjjang.shop.view.ShopDisplay;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class ShopInsert implements ShopInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 제목출력
		String str="상품등록";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 게시판의 글 입력 - 제목,내용,작성자
		ShopDisplay display = new ShopDisplay();
		// 분류번호,상품명,설명, 제조사,가격를 받아온다.
		ShopBean bean = display.inputShopData();
		// 입력한 글을 DB에 저장
		ShopDao dao = new ShopDao();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			// 상품 등록
			dao.insert(con, bean);
			// 가격 등록
			dao.insertPrice(con, bean);
			// 데이터 확인용 리스트 출력
//			BoardInterface obj = new BoardList();
//			obj.service();
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
