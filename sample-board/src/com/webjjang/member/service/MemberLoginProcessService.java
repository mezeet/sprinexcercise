package com.webjjang.member.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.dao.BoardDaoProvider;
import com.webjjang.board.model.AttachedFile;
import com.webjjang.board.model.Board;
import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;

public class MemberLoginProcessService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardWriteProcessService.process()");
		String uploadPath
		=request.getServletContext().getRealPath("board/upload");
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
//		System.out.println("제목:"+title);
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		// 첨부파일 처리 : 다중 파일 처리
		@SuppressWarnings("unchecked")
		Enumeration<String> fileNames= multi.getFileNames();

		List<AttachedFile> fileList = null;
		boolean attachFile = false;
		// input file 입력란이 없는 경우
		while(fileNames.hasMoreElements()){
			String fileName = fileNames.nextElement();
//			System.out.println("BoardWriteProcessService.service().fileName:"+fileName);
			String originalFile
			= multi.getOriginalFileName(fileName);
//			System.out.println("내컴파일:"+originalFile);
			if(originalFile!=null){
				if(!attachFile){
					fileList = new ArrayList<AttachedFile>();
					attachFile = true;
				}
				String serverFile 
				= multi.getFilesystemName(fileName);
//				System.out.println("서버파일:"+serverFile);
				AttachedFile attachedFile = new AttachedFile();
				attachedFile.setOriginalFile(originalFile);
				attachedFile.setServerFile(serverFile);
				fileList.add(attachedFile);
			}
		}
		// 처리 후 입력된 파일의 내용 확인용 프로그램
		if(fileList !=null && fileList.size()>0){
			for(AttachedFile af:fileList){
				System.out.println("원본파일:"+af.getOriginalFile());
				System.out.println("서버파일:"+af.getServerFile());
			}
		}
		// 파일을 제외한 데이터 셋팅
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		// 첨부된 파일 데이터 셋팅
		board.setFileList(fileList);
		
		
		// DB 처리
		BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 게시판에 글을 쓴다.
			dao.write(con, board);
			// 파일이 있는 경우 처리
			if(fileList!=null && fileList.size()>0){
				// 방금 작성된 글번호를 가져온다.
				int no = dao.currentNo(con);
				// 작성된 글번호로 파일 정보를 입력한다.
				dao.writeFile(con, no, board.getFileList());
			}
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
