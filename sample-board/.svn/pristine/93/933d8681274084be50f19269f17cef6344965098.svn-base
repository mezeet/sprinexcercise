<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<link rel="stylesheet" href="../css/board/board.css" type="text/css">
<link rel="stylesheet" href="../css/board/boardUpdate.css" type="text/css">
<script type="text/javascript" src="../js/util.js" ></script>
<script type="text/javascript" src="../js/board/write.js" ></script>
<h2>공지사항 쓰기</h2>
<form action="writeProcess.do" method="post" id="boardWriteForm"
enctype="multipart/form-data">
<table id="listTable">
<tbody id="boardWriteBody">
<tr>
	<th>제목</th>
	<td class="data"><input type="text" name="title" id="inputTitle" maxlength="100"/></td>
</tr>
<tr>
	<th>공지기간</th>
	<td class="data">
		<input type="text" name="startDate" id="startDate" />
		- <input type="text" name="endDate" id="endDate" />
		<div>
			공지시작일이 비어 있으면 오늘부터 적용이 됩니다.
			<input type="button" value="시작일 비우기" id="clearStartDate" /><br/>
			공지종료일이 비어 있으면 '9999-12-31'까지 적용이 됩니다.
			<input type="button" value="종료일 비우기"  id="clearEndDate" />
		</div>
	</td>
</tr>
<tr>
	<th>내용</th>
	<td class="data">
		<textarea name="content" id="inputContent"></textarea>
	</td>
</tr>
<tr>
	<th>공지이미지파일</th>
	<td class="data">
		<input type="file" name="file1" />
	</td>
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
