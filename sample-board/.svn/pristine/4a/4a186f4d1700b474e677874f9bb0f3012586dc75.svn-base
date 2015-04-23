/**
 * 
 */

$(document).ready(function(){
//	alert("view test");
	$("#boardWriteForm").bind("submit",function(){
//		alert("checkData");
		// 데이터가 비어 있는 검사
		if(isEmptyData("#inputTitle","제목을 입력하셔야 합니다."))
			return false;
		if(isEmptyData("#inputContent","내용을 입력하셔야 합니다."))
			return false;
		if(isEmptyData("#inputWriter","작성자를 입력하셔야 합니다."))
			return false;
	// 처리된 결과물을 가지고 return true이면 submit()을 실행하고
	// 처리된 결과물이 return false이면 submit()을 하지 않도록 하기 위해
	// 메소드를 달아 둔다.
		$("#boardWriteForm").submit();
	});
	var fileIdx = 1; var lastFileIdx = 5;
	var addFileInput="<input type=\"button\" value=\"+\" class=\"addFile\" />";
	var subFileInput="<input type=\"button\" value=\"-\" class=\"subFile\" />";
	$(".addFile").live("click",function(){
		if(fileIdx < lastFileIdx){
			var addFileTag = "<tr><th>첨부파일</th>	<td class=\"data\"><input type=\"file\" name=\"file"+(++fileIdx)+"\" /> ";
			if(fileIdx < lastFileIdx) addFileTag += addFileInput;
			if(fileIdx > 1) addFileTag += subFileInput;
			addFileTag += "</td></tr>";
			$("#boardWriteBody").append(addFileTag);
		}
		if(fileIdx > 1) $(this).next().remove();
		$(this).remove();
	});
	$(".subFile").live("click",function(){
		fileIdx--;
		var $thisFileTr = $(this).parent().parent();
		var $prevFileTd = $("input[name='file"+fileIdx+"']").parent();
		$prevFileTd.append(addFileInput);
		if(fileIdx > 1)$prevFileTd.append(subFileInput);
		$thisFileTr.remove();
	});
	
	$("#useQuestionTitle").bind("click", function(){
		if($("#useQuestionTitle").val() == "질문제목사용하기"){
			$("#inputTitle").val("[Re] "+$("#qustionTitle").text());
			$("#useQuestionTitle").val("질문제목사용하지 않기");
		}
		else {
			$("#inputTitle").val("");
			$("#useQuestionTitle").val("질문제목사용하기");
		}
	});
	
	

	$("#startDate").datepicker({
	       changeMonth: true,
	       changeYear: true,
	       dateFormat: "yy-mm-dd",
	       minDate: 0,
	       onClose: function( selectedDate ) {
	           $( "#endDate" ).datepicker( "option", "minDate", selectedDate );
	       }
	 });
	 
	 $("#endDate").datepicker({
	  changeMonth: true,
	  changeYear: true,
	  dateFormat: "yy-mm-dd",
	     minDate: 0,
	     onClose: function( selectedDate ) {
	         $( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
	       }
	 });

	 $("#clearStartDate").bind("click", function(){
//		 alert("clearStartDate click");
		$("#startDate").val("");
	 });

	 $("#clearEndDate").bind("click", function(){
			$("#endDate").val(""); 
		 });

	  

	
});