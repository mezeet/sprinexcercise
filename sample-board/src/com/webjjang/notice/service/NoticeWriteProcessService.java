package com.webjjang.notice.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;
import com.webjjang.notice.dao.NoticeDao;
import com.webjjang.notice.dao.NoticeDaoProvider;
import com.webjjang.notice.model.Notice;

public class NoticeWriteProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("NoticeWriteProcessService.process()");

		String uploadPath
		=request.getServletContext().getRealPath("notice/upload");
		System.out.println(uploadPath);
		//첨부파일 용량 제한 : 100M
		int size = 100*1024*1024;
		// 파일 업로드와 함께하는 데이터 받기
		// ** Multipart 사용
		MultipartRequest multi
	      = new MultipartRequest(request, uploadPath,
	      size, "utf-8" ,new DefaultFileRenamePolicy());
		
		// 입력된 데이터를 꺼내오기
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String startDate = multi.getParameter("startDate");
		String endDate = multi.getParameter("endDate");
		if(startDate == null || startDate.trim().equals(""))
			startDate = "sysdate";
		if(endDate == null || endDate.trim().equals(""))
			endDate = "9999-12-31";
		// 서버에 올라간 파일면 가져오기
		String attachFile 
		= multi.getFilesystemName("file1");
		System.out.println(multi.getContentType("file1"));

		
		// 데이터 셋팅
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setAttachFile(attachFile);
		notice.setStartDate(startDate);
		notice.setEndDate(endDate);
		
		
		// DB 처리
		NoticeDao dao 
		= NoticeDaoProvider.getInstance().getDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
			dao.write(con, notice);
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JdbcUtil.close(con);
		}
			
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : redirect : false
		forwardInfo.setForward(false);
		// 진행하는 곳 : list.do
		forwardInfo.setForwardStr("list.do");
		return forwardInfo;
	}

}
