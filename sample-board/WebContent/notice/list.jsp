<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link rel="stylesheet" href="../css/board/board.css" type="text/css">
	<link rel="stylesheet" href="../css/board/boardList.css" type="text/css">
	<script type="text/javascript" src="../js/board/list.js" ></script>
	<h2>공지사항 리스트</h2>
	<table id="listTable">
	<tr>
		<td colspan="3">
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
				<option value="title/content"
				<c:if test="${data.searchKey =='writer' }">
					selected ="selected"
				</c:if>
				>제목+내용</option>
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
		<th class="wdate">작성일</th>
	</tr>
	<c:forEach var="bean" items="${data.list}">
	<tr class="dataTr">
		<td class="no">${ bean.no }</td>
		<td class="title"><div class="ellip">
			${ bean.title }
			</div>
		</td>
		<td class="wdate">${ bean.wdate }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="3"  class="pageTd" >&nbsp;
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
		<td colspan="3" class="buttonTd">
		<input type="button" value="새로고침" id="reflash"
		 alt="${data.page }&searchKey=${data.searchKey}&searchWord=${data.searchWord}" />
<%-- 		<c:if test="${grade >=9 }"> --%>
			<input type="button" value="공지올리기" id="write"/>
<%-- 		</c:if> --%>
		</td>
	</tr>
	</table>
</body>
</html>