package com.webjjang.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.board.dao.BoardDao;
import com.webjjang.board.dao.BoardDaoProvider;
import com.webjjang.board.model.Board;
import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;
import com.webjjang.jdbc.JdbcUtil;
/*
 * 한개의 파일을 서버에서 삭제하는 프로그램
 * 서버에서 지우고 board_file에서도 정보를 삭제한다.
 * board table의 fileCount 컬럼도 1 감소시킨다.
 */
public class FileDeleteService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("FileDeleteService.process()");
		
		 //넘어온 파일명을 받는다.-서버파일명,글번호,첨부위치(board,qna,...)
		 // 서버에 올라가 저장되어 있는 파일명
		 String sFileName = request.getParameter("sfile");
		 System.out.println("/서버저장파일:"+sFileName);
		 // 게시판의 글번호
		 String noStr=request.getParameter("no");
		 int no = Integer.parseInt(noStr);
		 // 첨부파일 번호
		 String fnoStr=request.getParameter("fno");
		 int fno = Integer.parseInt(fnoStr);
		 // 게시판의 리스트 페이지
		 String pageStr=request.getParameter("page");
		 int page = Integer.parseInt(pageStr);
		 // 파일첨부되는 위치
		 String path = request.getParameter("path");
		 
		 // 파일첨부위치 밑에 upload폴더에 위치하게 되며로 파일첨부를 하는 게시판은
		 // 받으시 그 밑에 upload 폴더를 만들어 주어야 합니다.
		 String savePath=path+"\\upload";
		 
		 // 서버 컴퓨터의 실제적인 폴더를 잡아 주기 위해서 실제적인 폴더의 위치를
		 // 알아내는 처리문
		 ServletContext context = request.getServletContext();
		 String uploadPath = context.getRealPath(savePath);
		// String uploadPath = request.getRealPath(savePath);
		 System.out.println(uploadPath);
		 
		 
		// ## DB(board,board_File)에서 파일카운트를 1 감소시키고 파일정보 삭제 처리 시작 ==============
		// DB 처리
		BoardDao dao 
		= BoardDaoProvider.getInstance().getBoardDao();
		Connection con = null;
//			화면에 보여질 파일명
		try {
			con=JdbcUtil.getConnection();
			con.setAutoCommit(false);
			// 주어진 파일 정보를 board_file 테이블에서 삭제하기
			dao.deleteFileTable(con, "board_file", fno);
			
			// 게시판(board)에서 fileCount에서 1 감소 시키기
			dao.decreaseTarget(con, "board", "fileCount", no, 1);
			con.commit();
			con.setAutoCommit(true);
			 // ## 서버의 실제 파일 삭제 처리 시작 ===================================================
			 // 구해진 경로와 서버의 파일명을 연결한다.
			 String pathFile = uploadPath+"\\"+sFileName;
			 System.out.println(pathFile);
			 
			 // 구해진 경로와 서버의 파일명을 File 객체로 연결합니다.
			 File deleteFile = new File(pathFile);
			 // 파일이 존재하면 연결된 삭제 파일을 삭제합니다.
			 if(deleteFile.exists()){
				 System.out.println("파일이 존재합니다. 삭제를 진행합니다.");
				 deleteFile.delete();
				 System.out.println("파일"+sFileName+"을(를) 삭제했습니다.");
			 }
			 else System.out.println("파일이 존재하지 않습니다.");
			 // ## 서버의 실제 파일 삭제 처리 끝 ===================================================
			 
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
			JdbcUtil.close(con);
		}
	// ## DB(board,board_File)에서 파일카운트를 1 감소시키고 파일정보 삭제 처리 끝 ==============
	 
		
	// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
	// fileDelete인 경우 처리 후 board/view.do로 리턴해 준다.
		ForwardInfo forwardInfo = new ForwardInfo();
		// 진행방식 : forward : true
		forwardInfo.setForward(false);
		// 진행하는 곳 : /board/list.jsp
		forwardInfo.setForwardStr("../"+path+"/update.do?no="+no+"&page="+page);
		return forwardInfo;
	}

}
