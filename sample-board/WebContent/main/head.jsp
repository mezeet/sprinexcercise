<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<nav id="mainHotMenu">
		<ul>
			<c:if test="${sessionScope.grade >= 9 }">
				<li title="관리자"><a href="#">Admin</a></li>
			</c:if>
			<c:if test="${sessionScope.id != null }">
				<li title="마이페이지"><a href="#">My Page</a></li>
				<li title="로그아웃"><a href="#">Logout</a></li>
			</c:if>
			<li title="로그인"><a href="#">Login</a></li>
			<li title="회원가입"><a href="#">Join</a></li>
		</ul>
	</nav>
	<nav id="mainMenu">
		<ul>
			<li title="home"><a href="../main/index.do">Home</a></li>
			<li title="회사소개"><a href="#">Company</a></li>
			<li title="공지사항"><a href="../notice/list.do">Notice</a></li>
			<li title="쇼핑몰"><a href="#">Shop</a></li>
			<li title="일반게시판"><a href="../board/list.do">Board</a></li>
			<li title="이미지게시판"><a href="#">Image</a></li>
			<li title="고객센터"><a href="../qna/list.do">Q&amp;A</a></li>
			<li title="자료실"><a href="#">Data</a></li>
		</ul>
	</nav>
	