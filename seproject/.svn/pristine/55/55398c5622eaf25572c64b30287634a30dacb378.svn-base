package com.webjjang.shop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.model.BoardBean;
import com.webjjang.board.model.BoardCommentBean;
import com.webjjang.board.view.BoardDisplay;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class ShopView implements ShopInterface {

	@Override
	public void service() {
		// TODO Auto-generated method stub
		// 게시판 글보기 제목 출력
		String str="자유 게시판 글보기";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// 데이터베이스에서 데이터를 가져온다.
		BoardDao dao = new BoardDao();
		Connection con = null;
		try{
			// 컨넥션 가져오기
			con = DBUtil.getConnection();
			// autocommit이 실행이 안되게 해 놓는다.
			con.setAutoCommit(false);
			// 조회할 글 선택
			int no  = Util.inputInt("보여질 글번호:");
			// 보는 글의 조횟수를 1증시킨다.
			dao.incerease(con, no);
			// 보는 글의 데이터를 가져온다. -->BoardBean
			BoardBean bean = dao.view(con, no);
			// 보는 글의 댓글을 가져온다. --> List<BoardCommentBean>
			List<BoardCommentBean> list
			= dao.commentList(con,no);
//			System.out.println
//			("Board.service.().commentList:"+list);
			// 가져온 데이터를 출력한다.
			BoardDisplay display = new BoardDisplay();
			display.view(bean,list);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
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
