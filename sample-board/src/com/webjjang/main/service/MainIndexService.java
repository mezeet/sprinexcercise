package com.webjjang.main.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.goods.model.Goods;
import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.main.model.MainListView;
import com.webjjang.notice.dao.NoticeDao;
import com.webjjang.notice.dao.NoticeDaoProvider;
import com.webjjang.notice.model.Notice;
import com.webjjang.qna.dao.QnaDao;
import com.webjjang.qna.dao.QnaDaoProvider;
import com.webjjang.qna.model.Qna;

public class MainIndexService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MainIndexService.process()");
		
		// DB 처리
		QnaDao qnaDao 
		= QnaDaoProvider.getInstance().getDao();
		NoticeDao noticeDao
		= NoticeDaoProvider.getInstance().getDao();
//		GoodsDao goodsDao
//		= GoodsDaoProvider.getInstance().getDao();
		Connection con = null;
		// 필요한 변수들을 선언
		// main에 표시할 시작글번호, 끝번호
		int startRow = 1, endRow = 5;
		try {
			con=JdbcUtil.getConnection();
			List<Notice> noticeList
			= noticeDao.list(con, startRow, endRow, null, null, "cur");
			List<Qna> qnaList
			= qnaDao.mainList(con, startRow, endRow);
			List<Goods> goodsList = null;
			
			MainListView data = new MainListView(noticeList, qnaList, goodsList);
//			System.out.println("data.getStartPage():"+data.getStartPage());
//			System.out.println("data.getEndPage():"+data.getEndPage());
			request.setAttribute("data", data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
			
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(true);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("/main/main.jsp");
		return forwardInfo;
	}

}
