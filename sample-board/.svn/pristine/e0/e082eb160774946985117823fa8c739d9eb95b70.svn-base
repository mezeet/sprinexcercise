<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%
 //넘어온 파일명을 받는다.(서버에 있는 파일)
 String fileName = request.getParameter("fileName");
 String oFileName = request.getParameter("oFileName");
 System.out.println(oFileName);

 String savePath="upload";
 ServletContext context = getServletContext();
 String uploadPath = context.getRealPath(savePath);
// String uploadPath = request.getRealPath(savePath);
 out.println(uploadPath);
 
 String pathFile = uploadPath+"\\"+fileName;
 out.println(pathFile);
 
 //한번에 보낼 데이터의 크기를 정한다.
 byte b[] = new byte[4096];
 File oFile = new File(pathFile);
 
 FileInputStream in = new FileInputStream(pathFile);
 
 String mimeType = getServletContext().getMimeType(pathFile);
 out.println(mimeType);
 
 //원본 파일명이 넘어온 경우 원본 파일명으로 다운로드 파일
 //명을 사용할 수 있도록 설정
 String sEncoding=null;
 System.out.println("--->"+oFileName);
 if(oFileName == null) System.out.println("ofilename is null");
 if(oFileName.equals("")) System.out.println("ofilename is blank");
 if(oFileName != null && !oFileName.equals("")){
  System.out.println("oFileName");
  sEncoding = new String(oFileName.getBytes("utf-8"),"8859_1");
 }else{
  System.out.println("fileName");
  sEncoding = new String(fileName.getBytes("utf-8"),"8859_1");
 }
 response.setHeader
 ("Content-Disposition","attachment;filename="+sEncoding);
 
 ServletOutputStream out2 = response.getOutputStream();
 int numRead;
 
 while((numRead = in.read(b,0,b.length))!=-1){
  out2.write(b,0,numRead);
 }
 out2.flush();
 out2.close();
 in.close();
%>
