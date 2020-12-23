<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 게시판 수정 폼</title>
<script type="text/javascript" src="../resources/js/jquery.js"></script>
<%-- jQuery 외부 라이브러리 읽어오기. type 속성 생략가능 --%>
<script src="../resources/js/board.js"></script>
<%-- board.js 자바스크립트 외부 포함 파일 읽어오기 --%>
</head>
<body>
	<form method="post" action="board_edit_ok" onsubmit="return check();">
	<%--onsubmit은 사건처리기 즉 이벤트 핸들러라 한다.
		데이터가 서버로 전송된 것을 처리함. check()함수를 호출. check()는 자바 스크립트에서 정의한 함수명 --%>
	<input type="hidden" name="bno" value="${eb.bno }"/>
	<%-- hidden은 웹 브라우저 출력창에는 만들어지지않는다. 하지만 수정, 삭제폼에서
	서버로 값을 전달할 때 유용하게 사용한다. 브라우저 소스보기에서 자료노출된다. 그러므로 비번같은 자료는 
	히든을 사용하면 안된다. 보안성은 좋지 않다. bno네임 파라미터 이름에 번호값을 담아서 전달함. --%>
	<input type="hidden" name="page" value="${page}" />
	<table border="1">
	<%--table html태그는 표를 만든다. border 속성을 표테이블 두께.
	숫자가 커질수록 두께는 굵어진다. --%>
	
	<tr> <%--tr태그는 행을 만듬. --%>
		<th> <%--th태그는 열을 만듬. 이 태그의 특징은 글자를 중앙 정렬하고 진하게 출력 --%>
		글쓴이</th>
		<td> <%--td태그도 열을 만듬 --%>
		<input type="text" name="writer" id="writer" size="14"
		value="${eb.writer }"/>
		<%--type="text"는 한줄 입력박스를 만듬. 입력한 값은 name 속성명 writer에 저장되어
		서버로 전달됨. size 속성은 입력 박스 길이--%>
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input name="title" id="title" size="36" value="${eb.title }"/>
		<%--type 속성을 생략하면 기본값이 text이다. --%>
	</tr>
	<tr>
		<th>글 내용</th>
		<td><textarea name="content" id="content" rows="10"
		cols="36">${eb.content}</textarea></td>
		<%--rows 속성은 행을 마들고 col속성은 열은만듬.
		textarea 태그는 한줄 이상 입력할 수 있는 박스를 만든다. --%>
	</tr>
	<tr>
		<th colspan="2"><%-- colspan속성은 열을 합침. 여기서는 2개 열을 합침 --%>
		<input type="submit" value="수정"/>
		<%--submit은 입력한 자료를 서버로 전송한다. --%>
		<input type="reset" value="취소"/>
		<input type="button" value="목록" onclick="location='/controller/board/board_list?page=${page}';"/>
		<%-- onclick은 클릭사건을 처리하는 사건 처리기 즉 이벤트 핸들러이다. --%>
		<%--reset은 입력한 값을 초기화. --%>
		</th>
	</tr>
	</table>
	</form>
</body>
</html>