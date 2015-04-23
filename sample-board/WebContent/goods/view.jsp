<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/board/board.css" type="text/css">
<link rel="stylesheet" href="../css/board/boardView.css" type="text/css">
<script type="text/javascript" src="../js/board/view.js"></script>
<h2>공지사항 글보기</h2>
<table id="listTable">
<tbody>
<tr>
	<th>글번호</th>
	<td class="data1">${board.no }</td>
	<th>작성일</th>
	<td class="data1">${board.wdate}</td>
</tr>
<tr>
	<th>제목</th>
	<td colspan="3" class="data">${board.title }</td>
</tr>
<tr>
	<th>내용</th>
	<td colspan="3" class="data">
		<c:if test="${board.attachFile != null }">
			<div class="attachFile" style="text-align:center"><img src="./upload/${board.attachFile }" alt="공지 이미지" /></div>
		</c:if>
		<div class="content"><pre>${board.content }</pre></div>
	</td>
</tr>
</tbody>
<tfoot>
<tr>
<td class="buttonTd"  colspan="4">
	<input type="button" value="수정" id="updateBtn" alt="no=${board.no }&page=${page }" />
	<input type="button" value="삭제" id="deleteBtn" alt="no=${board.no }" />
	<input type="button" value="리스트로 가기" id="listBtn" alt="page=${page }" />
</td>
</tr>
</tfoot>
</table>