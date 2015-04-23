<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문구의 모든 것 웹짱샵~~~</title>
<!-- css 포함 선언 -->
<link href="../css/site.css" rel="stylesheet" type="text/css"/>
<!-- jquery 포함 선언 -->
<script type="text/javascript"
 src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
 src="../js/jquery-migrate-1.2.1.min.js"></script>
<link href="../css/themes/sunny/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript"
 src="../js/jquery-ui.js"></script>
<!--  사용자 jQuery 프로그램 포함 -->
</head>
<body>
<div id="wrapper">
<header>
	<jsp:include page="${head }"></jsp:include>
</header>
<section>
	<jsp:include page="${main }"></jsp:include>
</section>
<footer>
	<jsp:include page="./footer.jsp"></jsp:include>
</footer>
</div>
</body>
</html>