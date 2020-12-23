/**
 * admin_gongji.js
 */

function gw_check(){
	if($.trim($("#gongji_name").val()) == ""){
		alert("공지 작성자를 입력하세요!");
		$('$gongji_name').val("").focus(); //입력박스 값을 초기화하고 포커스
		return false;
	}
	
	if($.trim($("#gongji_title").val())==""){
		alert("공지 제목을 입력하세요!");
		$("#gongji_title").val("").focus();
		return false;
	}
	
	if($.trim($("#gongji_pwd").val())==""){
		alert("비밀번호를 입력하세요!");
		$("#gongji_pwd").val("").focus();
		return false;
	}
	
	if($.trim($('#gongji_cont').val())==''){
		alert("공지 내용을 입력하세요!");
		$("#gongji_cont").val('').focus();
		return false;
	}
} //유효성 검증=>validate