/**
 * 
 */

$(document).ready(function(){
//	alert("view test");
	$("#backList").bind("click",function(){
//		alert("backList");
		location="view.do?"+$(this).attr("alt");
	});
	
	// 파일 삭제를 누르면 확인하는 경고를 띄우고 확인을 클릭하면 지우게 처리
	$(".fileDelete").bind("click",function(){
		var msg="파일삭제는 바로 적용됩니다.\n삭제하시겠습니까?";
		if(confirm(msg)){
			location=$(this).attr("href");
		}
		else return false;
	});
});