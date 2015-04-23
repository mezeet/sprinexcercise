package com.webjjang.board.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.dao.BoardDaoProvider;
import com.webjjang.board.model.Board;
import com.webjjang.board.model.BoardListView;
import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;

public class BoardViewFileDeleteService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("BoardListService.process()");
		
		//전달되는 데이터를 받자
		String searchKey
		= request.getParameter("searchKey");
		String searchWord
		= request.getParameter("searchWord");
		if(searchWord != null) searchWord = searchWord.trim();
		String searchKeyArr[] = null;
		if(searchWord!=null){
			searchWord = searchWord.trim();
			searchKeyArr = searchKey.split("/");
		}
//		전달받은 데이터 확인
		System.out.println("검색키:"+ searchKey);
		System.out.println("검색단어:"+ searchWord);

		// DB 처리
		BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
		// 필요한 변수들을 선언
		// 현재 페이지
		// 전달 받는 현재페이지
		String currPageStr = request.getParameter("page");
		// 처리할 현재 페이지
		int page = 1;
		if(currPageStr!=null && currPageStr!=""){
			page = Integer.parseInt(currPageStr);
			if(page<1) page=1;
//			System.out.println("현재 페이지:"+page);
		}
		// 전체 페이지 : (전체 글수-1)*페이당글수 +1
		int totalPage = 0;
		int totalRow = 0; // 전체 글수 : DB
		int rowPerPage = 10;
		// 현재페이지 시작글번호, 끝번호
		int startRow = 0, endRow = 0;
		//화면에 페이지 그룹
		int pagePerGroup = 4;
		// 현재페이지가 속해 있는 그룹의 시작페이지,끝페이지
		int startPage=0, endPage = 0;
		try {
			con=JdbcUtil.getConnection();
			//전체 글의 갯수 구하기
			totalRow=dao.selectCount(con, searchKeyArr, searchWord);
			//전체 페이지 구하기
			if(totalRow>0)
				totalPage = (totalRow-1)/rowPerPage +1;
			// 현재 페이지의 시작과 끝 줄수 구하기
			startRow=(page-1)*rowPerPage +1;
			endRow = startRow+rowPerPage -1;
//			System.out.println("startRow:"+startRow);
//			System.out.println("endRow:"+endRow);
			// 페이지 그룹에 시작페이지,끝페이지
			startPage 
			= (page-1)/pagePerGroup*pagePerGroup+1;
			endPage
			= startPage+pagePerGroup -1;
			// 만약에 endPage가 totalPage보다 큰경우
			// endPage를 totalPage로 해준다.
			if(endPage > totalPage) endPage=totalPage;
//			System.out.println("BoardListService.process():startPage-"
//			+startPage+", endPage-"+endPage+", totalPage-"+totalPage);
//			System.out.println
//			("BoardListService.process().totalRow:"+totalRow);
			List<Board> list
			= dao.list(con, startRow, endRow, searchKeyArr, searchWord);
			
			BoardListView data = new BoardListView(
					totalRow, totalPage,
					startRow, endRow, startPage,
					endPage, page, rowPerPage,
					pagePerGroup, list, searchKey, searchWord);
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
		forwardInfo.setForwardStr("/board/list.jsp");
		return forwardInfo;
	}

}
