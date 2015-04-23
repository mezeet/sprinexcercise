<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/board/board.css" type="text/css">
<link rel="stylesheet" href="../css/board/boardUpdate.css" type="text/css">
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/board/write.js"></script>
<script type="text/javascript" src="../js/board/update.js"></script>
<h2>공지사항 글 수정</h2>
<form action="updateProcess.do" method="post" id="boardUpdateForm">
<table id="listTable">
<tbody id="boardWriteBody">
<tr>
	<th>글번호</th>
	<td class="data1">
		${board.no }
		<input type="hidden" name="no" value="${board.no }" />
		<input type="hidden" name="page" value="${page }" />
	</td>
	<th>작성일</th>
	<td class="data1">${board.wdate}</td>
</tr>
<tr>
	<th>제목</th>
	<td colspan="3" class="data"><input type="text" name="title" id="inputTitle" maxlength="100"
	 value="${board.title }" /></td>
</tr>
<tr>
	<th>내용</th>
	<td colspan="3" class="data">
	<textarea rows="4" name="content" id="inputContent">${board.content }</textarea></td>
</tr>
</tbody>
<tfoot>
<tr>
<td class="buttonTd"  colspan="4">
	<input type="submit" value="수정"/>
	<input type="reset" value="원래대로"/>
	<input type="button" value="글보기로 가기"
	id="backList" alt="no=${board.no }&page=${page}" />
</td>
</tr>
</tfoot>
</table>
</form>