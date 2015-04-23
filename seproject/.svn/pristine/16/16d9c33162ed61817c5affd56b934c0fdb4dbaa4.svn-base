package com.webjjang.shop.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.model.BoardBean;
import com.webjjang.board.view.BoardDisplay;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class ShopUpdate implements ShopInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 제목출력
		String str="자유 게시판 글수정";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 수정할 글 번호 입력
		int no = Util.inputInt("수정할 글번호:");
		// 입력한 글을 DB에 저장
		BoardDao dao = new BoardDao();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			// 입력한 글번호 해당 데이터를 가져온다.
			BoardBean bean = dao.view(con, no);
			// 화면에 출력
			BoardDisplay display = new BoardDisplay();
			display.updateView(bean);
			// 수정할 항목 번호 입력 및 데이터 수정
			updateData(bean);
//			display.updateView(bean); // 수정한 데이터 확인
			// 실제적인 수정을 한다. JDBC프로그램 작성
			dao.update(con,bean);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
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

	private void updateData(BoardBean bean) {
		// TODO Auto-generated method stub
		// 반복문
		while(true){
			// 수정할 항목 번호를 입력
			String item = Util.inputMenuAndData
				("수정할 항목을 선택[0:수정입력완료]:");
			// 입력된 항목 번호에 맞는 데이터 입력
			switch (item) {
			case "1":
				bean.setTitle
				(Util.inputMenuAndData("글제목:"));
				break;
			case "2":
				bean.setContent
				(Util.inputMenuAndData("글내용:"));
				break;
			case "3":
				bean.setWriter
				(Util.inputMenuAndData("작성자:"));
				break;
			case "0":
				return;

			default:
				System.out.println
				("수정할 항목 번호가 아닙니다.");
				System.out.println
				("항목번호는 [1부터 3까지]입니다.");
			}
		} // while
	} // end of updateData()

}
