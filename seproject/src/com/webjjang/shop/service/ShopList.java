package com.webjjang.shop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.webjjang.board.model.BoardBean;
import com.webjjang.board.view.BoardDisplay;
import com.webjjang.shop.dao.ShopDao;
import com.webjjang.shop.model.ShopBean;
import com.webjjang.shop.view.ShopDisplay;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class ShopList implements ShopInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 게시판 리스트 제목 출력
		String str="상품 리스트";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 데이터베이스에서 데이터를 가져온다.
		ShopDao dao = new ShopDao();
		Connection con = null;
		try{
			// 컨넥션 가져오기
			con = DBUtil.getConnection();
			// autocommit이 실행이 안되게 해 놓는다.
			con.setAutoCommit(false);
			List<ShopBean> list = dao.list(con);
			// 가져온 데이터를 출력한다.
			ShopDisplay display = new ShopDisplay();
			display.list(list);
//			con.commit();
		}catch(SQLException e){
			e.printStackTrace();
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}finally{
			// con이 null 아니고 autocommit이 false이면 true로
				try {
					if(con!=null && !con.getAutoCommit())
						con.setAutoCommit(true);
					// con.close()실행을 하면 정상적인 처리가
					// 된 경우 commit;이 비정상적인 처리가 된
					// 경우 rollback;이 자동 처리된다.
					if(con!=null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

}
