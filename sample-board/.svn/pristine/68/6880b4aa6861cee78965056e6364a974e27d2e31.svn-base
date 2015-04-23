/**
 * 
 */

//전역변수 선언 - 페이지, 검색키, 검색어
var page = 1;
var searchKey = "";
var searchStr = "";

$(document).ready(function(){
	page  = $("#reflash").attr("alt");

	$(".title>.ellip").width("380px");
	// 글 목록 중에 클릭하면 글보기로 이동
	$(".dataTr").bind("click",function(){
//		alert("test");
		var no= $(this).children("td").first().html();
//		alert(no);
		location="./view.do?no="+no+"&page="+page;
	});
	
//	글쓰기 링크
	$("#write").bind("click",function(){
		location="./write.do";
	});
//	목록 새로고침 링크
	$("#reflash").bind("click",function(){
		location="./list.do?page="+page;
	});
	
//  검색없이 모든 리스트 보기	
	$("#allView").bind("click",function(){
		location="./list.do";
	});
});