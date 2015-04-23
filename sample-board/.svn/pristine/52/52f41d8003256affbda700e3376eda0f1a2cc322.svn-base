package com.webjjang.common;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.controller.ForwardInfo;
import com.webjjang.controller.Service;

public class FileDownloadService implements Service {

	@Override
	public ForwardInfo process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("FileDownload.process()");
		
		 //넘어온 파일명을 받는다.
		// 파일 올리기 때 내 컴퓨터에 있었던 원본 파일명
		 String oFileName = request.getParameter("ofile");
		 // 서버에 올라가 저장되어 있는 파일명
		 String sFileName = request.getParameter("sfile");
		 System.out.println("원본파일:"+oFileName+"/서버저장파일:"+sFileName);

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
		 
		 // 구해진 경로와 서버의 파일명을 연결한다.
		 String pathFile = uploadPath+"\\"+sFileName;
		 System.out.println(pathFile);
		 
		 //한번에 보낼 데이터의 크기를 정한다.
		 // 보통 4096을 설정합니다. 네트워크 장비에서 한번에 보내는 패킷이라고 보면 됩니다.
		 byte b[] = new byte[4096];
		 
		 // 서버에 있는 파일은 읽기 위한 파일로 연결합니다.
		 FileInputStream in = new FileInputStream(pathFile);
		 
		 // 파일의 종류를 알아보기 위한 테스트
		 String mimeType = context.getMimeType(pathFile);
		 System.out.println(mimeType);
		 
		 //원본 파일명이 넘어온 경우 원본 파일명으로 다운로드 파일
		 //명을 사용할 수 있도록 설정
		 String sEncoding=null;
		 System.out.println("--->"+oFileName);
		 if(oFileName == null) System.out.println("ofilename is null");
		 if(oFileName.equals("")) System.out.println("ofilename is blank");
		 // 서버에서 운영되는 한글이 8859_1 코드이며 getBytes() 파일의 한글이 euc-kr
		 // 이며로 한글을 제대로 연결해서 다운로드 해주기 위한 한글 변환 처리를 해줍니다.
		 if(oFileName != null && !oFileName.equals("")){
		  System.out.println("oFileName");
		  sEncoding = new String(oFileName.getBytes("euc-kr"),"8859_1");
//		  sEncoding = oFileName;
		 // 만약에 클라이언트의 파일명으로 운영을 하지 않고 서버 파일명으로만 운영하는 경우
		 }else{
		  System.out.println("sFileName");
		  sEncoding = new String(sFileName.getBytes("euc-kr"),"8859_1");
		 }
		 
		 // 서버에서 클라이언트로 보낼때 파일의 정보를 셋팅하는데 파일명을 filename 속성을
		 // 이용해서 변경해 줍니다.
		 response.setHeader
		 ("Content-Disposition","attachment;filename="+sEncoding);
		 
		 //다운로드(서버에서 클라이언트로 보내는 로 데이터를 넘기기
		 ServletOutputStream out2 = response.getOutputStream();

		 int numRead;
		 
		 // server 파일에서 버퍼 만큼 읽어오고 더 out2로 쓰기를
		 // 모든 데이터를 다 할 때까지 반복처리한다.
		 while((numRead = in.read(b,0,b.length))!=-1){
		  out2.write(b,0,numRead);
		 }
		 out2.flush();
		 out2.close();
		 in.close();

			
		// 진행방식과 진행되는 곳의 정보를 ForwardInfo 저장하는 처리
		// download인 경우 처리 후 표시하는 곳으로 이동하지 않아도 되므로 null을 리턴해 준다.
		ForwardInfo forwardInfo = null;
		return forwardInfo;
	}

}
