<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 절대 주소는 /board/wirteProcess.do 인데 현재 이 페이지를 볼 때면 /board 로 들어와 있기에 -->
<!-- 상대 주소로 요청한다. -->
<form action="writeProcess.do">
	 제   목 : <input 		name="title" type ="text" value="원본 제목"/><br />
	 내   용 : <textarea	name="content"rows="3" cols="30">원본 내용</textarea><br />
	 작성자 : <input		name="writer" type ="text" value="원본 작성자"/><br /> 
	<input type="submit" value="전송"/>
</form>
</body>
</html>