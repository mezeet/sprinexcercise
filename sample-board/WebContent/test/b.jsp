<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="No-Cache" />
<meta http-equiv="Pragma" content="No-Cache" />

<script type="text/javascript"
 src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
 src="../js/jquery-migrate-1.2.1.min.js"></script>
 <script type="text/javascript">
 // history의 길이를 구해서 길이 만큼 앞으로 보내고 그 값을 변경함으로 처리 하려고 했으나 실패
//  var backlen = history.length;
//  history.go(-backlen);
//  window.location.replace("aa.jsp");
 
 
 // 뒤로 가기 버튼은 막을수 있으나 창의 뒤로 가기는 막을 수 없음
//  $(document).keydown(function(e){
// 	if(e.keyCode == 8){
// 		alert("이전페이지로 갈수 없습니다.");
// 		return false;
// 	}
//  });
 
// 페이지를 나갈때 이벤트 안먹음 
//  $(window).unload(function() {
//   alert('Handler for .unload() called.');
// });

//  $(document).unload(function() {
//   alert('Handler for .unload() called.');
// });
 
 $(window).unload(function() {
	 return "test";
	});
 </script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>