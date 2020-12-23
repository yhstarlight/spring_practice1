<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인화면</title>
<link rel="stylesheet" type="text/css"
href="./resources/css/admin.css"/>
<link rel="stylesheet" type="text/css"
href="./resources/css/admin_gongji.css"/>
<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/admin_gongji.js"></script>
</head>
<body>
<div id="aMain_wrap">
	<%--관리자 메인 상단 --%>
	<div id="aMain_header">
		<%--관리자 로고 --%>
	<div id="aMain_logo">
	<a href="admin_main">
		<img src="./resources/images/admin/admin_logo.png">
	</a>
	</div>
	
	<%--관리자 상단 메뉴 --%>
	<div id="aMain_menu">
	<ul> <%--메뉴 구성은 ul li태그로 한다. --%>
		<li><a href="admin_gongji_write">공지사항</a></li><%-- 하이퍼링크 걸때 href="#"로 주면 이동통로를 막았다는 뜻 --%>
		<li><a href="#">댓글</a></li>
		<li><a href="#">자료실</a></li>
		<li><a href="#">회원관리</a></li>
	</ul>
	</div>
	
	<%-- 관리자 우측메뉴 --%>
	<div id="aMain_right">
	<form method="post" action="admin_logout">
	<h3 class="aRgiht_title"></h3>
	${admin_name}님 로그인을 환영합니다
	<input type="submit" value=로그아웃>
	</form>
	</div>
	</div>
	
	<div class="clear"></div>
	