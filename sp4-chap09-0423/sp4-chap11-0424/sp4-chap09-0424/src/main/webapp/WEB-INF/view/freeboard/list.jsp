<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
리스트
<table>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>내용</td>
		<td>작성자</td>
	</tr>
	<tr>
	<td>${FBList.fb1.no}</td>
		<td>${FBList.fb1.title}</td>
		<td>${FBList.fb1.content}</td>
		<td>${FBList.fb1.writer}</td>
	</tr>
	<tr>
		<td>${FBList.fb2.no }</td>
		<td>${FBList.fb2.title }</td>
		<td>${FBList.fb2.content }</td>
		<td>${FBList.fb2.writer }</td>
	</tr>
		<tr>
		<td>${FBList.fb3.no }</td>
		<td>${FBList.fb3.title }</td>
		<td>${FBList.fb3.content }</td>
		<td>${FBList.fb3.writer }</td>
	</tr>
		<tr>
		<td>${FBList.fb4.no }</td>
		<td>${FBList.fb4.title }</td>
		<td>${FBList.fb4.content }</td>
		<td>${FBList.fb4.writer }</td>
	</tr>
	
</table>

</body>
</html>