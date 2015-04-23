<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q &amp; A 글 리스트</title>
	<link rel="stylesheet" href="../css/board/board.css" type="text/css">
	<link rel="stylesheet" href="../css/board/boardList.css" type="text/css">
	<script type="text/javascript" src="../js/board/list.js" ></script>

</head>
<body>
	<h2>Q &amp; A 리스트</h2>
	<table id="listTable">
	<tr>
		<td colspan="5">
		<form action="list.do" method="post">
			<select name="searchKey">
				<option value="title" 
				<c:if test="${data.searchKey =='title' }">
					selected ="selected"
				</c:if>
				>제목</option>
				<option value="content"
				<c:if test="${data.searchKey =='content' }">
					selected ="selected"
				</c:if>
				 >내용</option>
				<option value="writer"
				<c:if test="${data.searchKey =='writer' }">
					selected ="selected"
				</c:if>
				>작성자</option>
				<option value="title/content"
				<c:if test="${data.searchKey =='title/content' }">
					selected ="selected"
				</c:if>
				>제목+내용</option>
				<option value="title/writer"
				<c:if test="${data.searchKey =='title/writer' }">
					selected ="selected"
				</c:if>
				>제목+작성자</option>
				<option value="content/writer"
				<c:if test="${data.searchKey =='content/writer' }">
					selected ="selected"
				</c:if>
				>내용+작성자</option>
				<option value="title/content/writer"
				<c:if test="${data.searchKey =='title/content/writer' }">
					selected ="selected"
				</c:if>
				>모든항목</option>
			</select>
			<input type="text" name="searchWord"
			  value="${data.searchWord }"/>
			<button type="submit">검색</button>
			<button type="button" id="allView">모두보기</button>
		</form>
		</td>
	</tr>
	<tr>
		<th class="no">글번호</th>
		<th class="title">제목</th>
		<th class="writer">작성자</th>
		<th class="wdate">작성일</th>
		<th class="target">조회수</th>
	</tr>
	<c:forEach var="bean" items="${data.list}">
	<tr class="dataTr">
		<td class="no">${ bean.no }</td>
		<td class="title"><div class="ellip">
<!-- 			들여쓰기 -->
			<c:forEach var="lev" begin="1"
			 end="${bean.levNo * 3 }"> &nbsp;
			</c:forEach>
			${ bean.title }
			</div>
		</td>
		<td class="writer">${ bean.writer }</td>
		<td class="wdate">${ bean.wdate }</td>
		<td class="target">${ bean.target }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5"  class="pageTd">&nbsp;
			<a href="list.do?page=1&searchKey=${data.searchKey}&searchWord=${data.searchWord}">처음</a>&nbsp;
			<c:if test="${data.startPage > 1 }">
				<a href="list.do?page=${data.page-data.pagePerGroup }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&lt;&lt;</a>&nbsp;
			</c:if>
<!-- 			이전페이지 이동 -->
			<c:if test="${data.page != 1 }">
				<a href="list.do?page=${data.page-1 }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&lt;</a>&nbsp;
			</c:if>
<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 -->
			<c:forEach var="page" begin="${data.startPage}"
			 end="${data.endPage }">
			 <c:choose>
			 	<c:when test="${data.page == page }">
			 		<b>${page }</b>
			 	</c:when>
			 	<c:otherwise>
					<a href="list.do?page=${page }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">${page }</a>&nbsp;
				</c:otherwise>
			 </c:choose>
			</c:forEach>
<!-- 			페이지 그룹의 시작 페이지부터 끝 페이지까지 반복 끝-->

<!-- 			다음 페이지 (현재 페이지+1,마지막페이지인 경우 표시안함) -->
			<c:if test="${data.page < data.totalPage }">
				<a href="list.do?page=${data.page + 1 }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&gt;</a>&nbsp;
			</c:if>
			<c:if test="${data.endPage<data.totalPage}">
				<a href="list.do?page=${data.page+data.pagePerGroup }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">&gt;&gt;</a>&nbsp;
			</c:if>
			<a href="list.do?page=${data.totalPage }&searchKey=${data.searchKey}&searchWord=${data.searchWord}">끝</a>&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="5" class="buttonTd">
		<input type="button" value="새로고침" id="reflash"
		 alt="${data.page }&searchKey=${data.searchKey}&searchWord=${data.searchWord}" />
		<input type="button" value="글쓰기" id="write"/>
		</td>
	</tr>
	</table>
</body>
</html>