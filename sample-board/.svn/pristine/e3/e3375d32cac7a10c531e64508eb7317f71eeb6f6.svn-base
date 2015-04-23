<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/board/board.css" type="text/css">
<link rel="stylesheet" href="../css/board/boardView.css" type="text/css">
<script type="text/javascript" src="../js/board/view.js"></script>
<h2>게시판 글보기</h2>
<table id="listTable">
<tbody>
<tr>
	<th>글번호</th>
	<td class="data1">${board.no }</td>
	<th>조회수</th>
	<td class="data1">${board.target }</td>
</tr>
<tr>
	<th>제목</th>
	<td colspan="3" class="data">${board.title }</td>
</tr>
<tr>
	<th>내용</th>
	<td colspan="3" class="data"><div class="content"><pre>${board.content }</pre></div></td>
</tr>
<tr>
	<th>작성자</th>
	<td colspan="3" class="data">${board.writer }</td>
</tr>
<c:forEach var="file1" items="${board.fileList }">
	<tr>
		<th>첨부파일</th>
		<td colspan="3" class="data">${file1.originalFile }
		 <a href="../common/download.do?ofile=${file1.originalFile }&sfile=${file1.serverFile }&path=board"
		  class="down"><button type="button">다운로드</button></a></td>
	</tr>
</c:forEach>
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