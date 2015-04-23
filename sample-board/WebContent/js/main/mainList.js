/**
 * 
 */

$(document).ready(function(){
	// qna 목록 중에 클릭하면 qna 보기로 이동
	$("#qna .dataTr").bind("click",function(){
//		alert("test");
		location="../qna/view.do?"+$(this).attr("title");
	});
	
//	공지사항 목록 중에 클릭하면 공지 보기로 이동
	$("#notice .dataTr").bind("click",function(){
		location="../notice/view.do?"+$(this).attr("title");
	});

//	상품 목록 중에 클릭하면 상품 보기로 이동
	$("#goods .goodsItem").bind("click",function(){
//		location="../goods/view.do?"+$(this).attr("title");
		alert("쇼핑몰은 개발 중 입니다.");
	});

	
});