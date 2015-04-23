package com.webjjang.shop.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.model.BoardBean;
import com.webjjang.board.view.BoardDisplay;
import com.webjjang.shop.dao.ShopDao;
import com.webjjang.shop.model.PriceBean;
import com.webjjang.shop.model.ShopBean;
import com.webjjang.shop.view.ShopDisplay;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class ShopChangePrice implements ShopInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 제목출력
		String str="상품가격변경";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 가격을 변경할 상품코드 입력
		int code = Util.inputInt("가격을 변경할 상품코드:");
		// 게시판의 글 입력 - 제목,내용,작성자
		ShopDisplay display = new ShopDisplay();
		// 가격,판매시작일을 받아온다.
		PriceBean bean = display.inputPriceData();
		bean.setCode(code);
		// 입력한 글을 DB에 저장
		ShopDao dao = new ShopDao();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			// 원래의 상품 가격의 종료일 변경
			dao.chageEndDate(con, bean);
			// 변경할 가격 등록
			dao.changeInsertPrice(con, bean);
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
