<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 내용보기</title>
<script src="../resources/js/jquery.js"></script>

<style type="text/css">
 #modDiv{
  width:300px; height:100px;
  background-color:gray;/*배경색*/
  position: absolute; top:35%; left:30%;
  padding:10px; /*상하좌우 안여백*/
  z-index:1000; /*  position 속성이 absolute나 fixed으로 설정된 곳에서 사용함.
  이 속성은 요소가 겹쳐지는 순서를 제어할 수 있다. 값이 큰 것이 앞에 나온다. */
 }
</style>

</head>
<body>
	<table border="1">
		<tr>
			<th colspan="2">스프링 게시판 내용보기</th>
		</tr>
		<tr>
			<th>제목</th> <td>${b.title }</td>
			<%--el 표현 언어는 b.getTitle()메서드를 호출한 효과와 같다. --%>
		</tr>
		<tr>
			<th>글 내용</th> <td>${b.content }</td>
		</tr>
		<tr>
			<th>조회수</th> <td>${b.viewcnt }</td>
		</tr>
		<tr>
			<th colspan="2">
			<input type="button" value="수정" onclick="location='/controller/board/board_edit?bno=${b.bno }&page=${page}';"/>
			<%--bno값이 get방식으로 전달 --%>
			<input type="button" value="삭제" onclick="location='/controller/board/board_del?bno=${b.bno }';"/>
			<input type="button" value="목록" onclick="location='/controller/board/board_list?page=${page}';"/>
			</th>
		</tr>
	</table>
	<br>
	<hr>
	<br>
	<%--댓글 수정 화면 --%>
	<div id="modDiv" style="display:none;">
	<%--display:none; 은 css속성값은 해당 화면을 안보이게 함 --%>
		<div class="modal-title">
		</div>
		<div>
		<textarea rows="3" cols="30" id="replytext"></textarea>
		</div>
		<div>
		<button type="button" id="replyModBtn">댓글 수정</button>
		<button type="button" id="replyDelBtn">댓글 삭제</button>
		<button type="button" id="closeBtn" onClick="modDivClose()">닫기</button>
		</div>
	</div>
	
	<h2>아작스 댓글 페이지</h2> <%-- H2태그는 본문제목을 지정할 때 사용함. --%>
	<%-- H1태그가 가장 크고, H6글자 크기가 가장 작다. --%>
	<%-- 댓글 목록 --%>
	<div> <%-- 레이아웃을 그릴 때 사용 --%>
		<div>
		댓글 작성자 : <input type="text" name="replyer" id="newReplyWriter"/>
		</div>
		<br>
		<div>
		댓글 내용:<textarea rows="5" cols="30" name="replytext"
		id="newReplyText"></textarea>
		</div>
		<br>
		<button id="replyAddBtn">댓글 등록</button>
	</div>
	
	<br>
	<hr>
	[댓글 개수 : <b>${b.replycnt}</b>개]
	<br>
	
	<ul id="replies"></ul><%-- ul li 태그는 순서 없는 목록 태그 --%>

<%--jQuery라이브러리 읽어오기 --%>
<script>
//type속성을 생략하면 기본값이 javascript
//var bno=17; 게시판 번호. var이 변수정의 예약어
var bno=${b.bno}
//자바스크립트에서 el 즉 표현언어를 사용할 수 있다. 게시판 번호값을 저장
//bno는 자바스크립트 변수명

getAllList();//댓글 목록 함수 호출
function getAllList(){ //function키워드로 getAllList() 함수정의
	$.getJSON("/controller/replies/all/" + bno, function(data){
		//get방식으로 JSON데이터를 가져오기 위한 jQuery 비동기식 아작스 함수
		//=>가져오는 것이 성공시 data매개변수에 값이 저장됨
		var str="";
		$(data).each(function(){ //$는 jQuery란 뜻이고 each() jQuery함수로서 반복
			str += "<li data-rno='"+this.rno+"' class='replyLi'>" 
			+ this.rno+ " : <span class='com' style='color:blue;font-weight:bold;'>"+ this.replytext + "</span>"
			+" <button>댓글수정</button></li><br/>";
		});
		$("#replies").html(str);//jQuery html()함수로 str변수에 저장된 문자와
		//html태그를 replies 아이디 영역에 변경 적용.
	});
} //댓글 목록

//댓글 추가
	$('#replyAddBtn').on("click",function(){ //해당버튼 클릭이벤틀가 발생시 실행
		var replyer=$('#newReplyWriter').val();//val() jQuery 함수는 입력박스 값을 가져온다.
		var replytext=$('#newReplyText').val();
		
		$.ajax({ //get or post 방식을 처리하는 jQuery 비동기식 아작스 함수 => 가장 사용빈도가 높다.
			//$대신 jQuery를 사용해도 됨
			type : 'post', //비동기식 아작스로 서버로 자료를 보내는 법
			url : '/controller/replies',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno, //json 키 이름 : 값 쌍으로 전달
				replyer : replyer, //키이름은 ReplyVO.java의 변수명과 일치
				replytext : replytext
			}),
			success : function(result) {
				//비 동기식으로 가져온 것이 성공시 호출되는 출력함수 => 정상적으로 가져온 데이터는 result매개변수에 저장됨
				//반환 문자열을 result매개변수에 저장됨
				if(result == 'success') {
					alert('댓글이 등록되었습니다!');
					//자바스크립트에서는 한문장 끝을 뜻하는 세미콜론은 해도되고 안해도 됨
					location.reload(); //자바스크립트 location객체의 reload()
					//메서드로 새로고침(단축키F5)
					getAllList(); //댓글등록함수 호출
				}
			}
		});
	});

	//댓글 수정 화면
	$('#replies').on('click',".replyLi button", function(){
		//.replyLi는 클래스명 선택자
		var reply=$(this).parent();
		var rno=reply.attr("data-rno");//data-rno속성 값인 댓글번호
		var replytext=reply.text();//댓글 내용
		
		$('.modal-title').html(rno);//댓글번호를 .modal-title 클래스 선택자 영역
		//문자와 태그를 변경 적용
		$('#replytext').val(replytext);
		$('#modDiv').show('slow');//수정화면을 show()함수로 천천히 보이게함
	});
	
	//댓글 수정 화면 닫기
	function modDivClose(){
		$('#modDiv').hide('slow');//hide()함수로 천천히 숨김
	}//modDivClose()함수 정의
	
	//댓글 수정 완료
	$('#replyModBtn').on("click",function(){
		var rno=$('.modal-title').html();//댓글 번호
		var replytext=$('#replytext').val();//댓글 내용
		
		$.ajax({
			type:'put',
			url:'/controller/replies/'+rno,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"PUT"
			},
			data:JSON.stringify({
				replytext:replytext//replytext키이름의 수정댓글 내용이 담겨져서 전달됨
			}),
			dataType:'text',
			success:function(result){
				if(result=='SUCCESS'){
					alert('댓글이 수정되었습니다!');
					$("#modDiv").hide('slow'); //수정화면을 숨김
					getAllList();//댓글 목록 함수를 호출
				}
			}
		});
	});
	
	//댓글 삭제
	$('#replyDelBtn').on('click', function(){
		var rno=$(".modal-title").html();//댓글 번호
		
		$.ajax({
			type:'delete',
			url:'/controller/replies/'+rno,
			header:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"DELETE"
			},
			dataType:'text',
			success:function(result){
				if(result=='success'){
					alert('댓글이 삭제되었습니다!');
					$("#modDiv").hide('slow');
					location.reload();
					getAllList();
				}
			}
		});
	});
</script>
</body>
</html>