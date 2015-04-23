<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>공지사항</h3>
<article>
<table>
	<c:forEach var="bean" items="${data.noticeList}">
	<tr class="dataTr" title="no=${bean.no }&page=1">
		<td class="title">${bean.title }</td>
		<td class="wdate">${bean.wdate}</td>
	</tr>
	</c:forEach>
</table>
</article> 