<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<link rel="stylesheet" href="../css/board/board.css" type="text/css">
<link rel="stylesheet" href="../css/board/boardUpdate.css" type="text/css">
<script type="text/javascript" src="../js/util.js" ></script>
<script type="text/javascript" src="../js/board/write.js" ></script>
<h2>Q &amp; A 글쓰기</h2>
<form action="writeProcess.do" method="post" id="boardWriteForm">
<table id="listTable">
<tbody id="boardWriteBody">
<tr>
	<th>제목</th>
	<td class="data"><input type="text" name="title" id="inputTitle" maxlength="100"/></td>
</tr>
<tr>
	<th>내용</th>
	<td class="data">
		<textarea name="content" id="inputContent"></textarea>
	</td>
</tr>
<tr>
	<th>작성자</th>
	<td class="data"><input type="text" name="writer" id="inputWriter"/></td>
</tr>
</tbody>
<tfoot>
<tr>
	<td colspan="2" class="buttonTd">
		<input type="submit" value="글올리기" />
		<input type="reset" value="재입력" />
		<input type="button" value="리스트로 가기" class="back" />
	</td>
</tr>
</tfoot>
</table>
</form>
