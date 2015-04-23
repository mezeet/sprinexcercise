/**
 * 
 */

$(document).ready(function(){
//	alert("view test");
	$(".content").parent().height("120px").css("vertical-align","top");
	
	// 글삭제 이동
	$("#deleteBtn").bind("click",function(){
		if(confirm("이글을 삭제하시겠습니까?"))
			location="deleteProcess.do?"+$(this).attr("alt");
	});
	
	// 글수정 이동
	$("#updateBtn").bind("click",function(){
		location="update.do?"+$(this).attr("alt");
	});
	
	// 리스트 이동
	$("#listBtn").bind("click",function(){
		location="list.do?"+$(this).attr("alt");
	});
	// 답변달기 이동
	$("#replyBtn").bind("click",function(){
		location="replyWrite.do?"+$(this).attr("alt");
	});
});