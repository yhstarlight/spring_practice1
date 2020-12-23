<%@ page contentType="text/html; charset=UTF-8"%>

<jsp:include page="../include/admin_header.jsp"/>
<%-- 관리자 상단 공통 페이지 외부 포함 파일 불러오기->jsp:include액션 태그--%>

<%-- 관리자 메인화면 --%>
<div id="aMain_cont">
	<div id="aBw_wrap">
		<h2 class="aBw_title">관리자 공지 작성</h2>
		<form method="post" action="admin_gongji_write_ok"
		onsubmit="return gw_check();">
		<table id="aBw_t">
		<tr>
			<th>이름</th>
			<td>
			<input name="gongji_name" id="gongji_name" size="14"/>
			</td>
		</tr>
		<tr>
			<th>공지 제목</th>
			<td>
			<input name="gongji_title" id="gongji_title" size="36">
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
			<input type="password" name="gongji_pwd" id="gongji_pwd" size="14">
			</td>
		</tr>
		<tr>
			<th>공지 내용</th>
			<td>
			<textarea name="gongji_cont" id="gongji_cont" rows="9" cols="36"></textarea>
			</td>
		</tr>
		</table>
		<div id="aBw_menu">
			<input type="submit" value="공지 작성">
			<input type="reset" value="작성 취소" onClick="$('#gongji_name').focus();">
			<%-- 작성자 입력박스로 포커스가 이동함 --%>
		</div>
		</form>
	</div>
</div>

<%-- 관리자 하단 공통페이지 외부 포함파일 처리--%>
<%@ include file="../include/admin_footer.jsp"%>
