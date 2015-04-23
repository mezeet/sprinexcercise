/*
*  utiel.js
* 자주 사용하는 javascript(jQuery) 함수 선언
*/

// 값이 비어 있는지 검사하는 함수 : 비어 있으면 true
// 
function isEmptyData(objId,msg){
//	alert("isEmptyData.objId:"+objId);
//	항목을 검사
//	공백제거
	var data = $(objId).val().trim();
	$(objId).val(data);
//	alert("isEmptyData().objId:"+objId+"/data:"+"data");
//	항목이 비어 있는 경우
	if(data == null || data == ""){
//		경고창을 띄운다.
		alert(msg);
//		커서를 위치시킨다.
		$(objId).focus();
//		비어 있다를 리턴시킨다.
		return true;
	}
//	검사 종료 정상 (데이터가 있다.) false를 returns
	return false;
}


//data의 길이가 정해진 숫자(len) 이상인지 확인. 이상 : true
function moreThanLength(objId,len,msg){
	var obj = document.getElementById(objId);
	var objLen = obj.value.trim().length;
//	alert(len);
// 	정해진 길이(len)보다 작다.
	if(objLen < len){
//		경고창을 띄운다.
		alert(msg+"은(는) "+len+"자 이상이여야 합니다.");
//		커서를 위치시킨다.
		obj.focus();
		return false;
	}
	// 정해진 길이 이상인 경우
	return true;

}


$(document).ready(function(){
	// 뒤로 가기 처리
	$(".back").bind("click",function(){
		history.back();
	});
});
