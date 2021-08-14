<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>RocketLaptop 회원수정 페이지</title>
<link href="css/join.css" type="text/css" rel="stylesheet">
<link rel="shortcut icon" href="image/favicon-16x16.png">
<style>
h3{
	text-align: center; color: #1a92b9;
}
input[type=file]{
	display:none;
}

body{ 
	background-color:ghostwhite !important;
}


</style>
</head>
<body>
<jsp:include page="/main/header.jsp"/>
<form name="joinform" action="updateProcess.ma" method="post"
      enctype="multipart/form-data">
		<h3>내정보 관리</h3>
		<hr>
		<b>아이디</b>
		<input type="text" name="user_id" value="${memberinfo.user_id}" readonly>
		
		<b>비밀번호</b>
		<input type="password" name="user_pass" value="${memberinfo.user_password}" readonly>
		
		<b>이름</b>
		<input type="text" name="user_name" value="${memberinfo.user_name}"  placeholder="Enter name"
		       required>
	
	<!-- 주민번호 일단 제외(8월13일 결정) 
	<b>주민번호</b>
		<input type="text" name="user_jumin"  value="${memberinfo.user_jumin}"    maxLength="2" 
		       placeholder="Enter jumin" required>
	-->	
		
		<b>성별</b>
		<div>
			<input type="radio" name="user_gender" value="남">
			<img src="image/img_avatar3.png" alt="남자" width="35px" title="남자">
			<input type="radio" name="user_gender" value="여">
			<img src="image/img_avatar4.png" alt="여자" width="35px" title="여자">
		</div>

		<b>이메일 주소</b>
		<input type="text" name="user_email" value="${memberinfo.user_email}" placeholder="Enter email"  
		       required><span id="email_message"></span>
		
		<b>전화번호</b>
		<input type="text" name="user_phone" value="${memberinfo.user_phone}" placeholder="Enter phone"  
		       required>
		
		<b>우편번호</b>
		<input type="text" name="user_address1" value="${memberinfo.user_address1}" placeholder="Enter postcode"  
		       required>
		
		<b>주소</b>
		<input type="text" name="user_address2" value="${memberinfo.user_address2}" placeholder="Enter address"  
		       required>
		
		<b>프로필 사진</b>
		<label>
			<img src="image/attach.png" width="10px">
			<span id="filename">${memberinfo.memberfile}</span>
			<%-- memberinfo.memberfile의 값이 없으면 기본 사진을 보여줍니다.
				값이 존재하면 memberupload 폴더에 존재하는 파일명으로 경로를 설정합니다. --%>
			<span id="showImage">
				<c:if test='${empty memberinfo.memberfile}'>
					<c:set var='src' value='image/profile.png'/>
				</c:if>
				<c:if test='${!empty memberinfo.memberfile}'>
					<c:set var='src' value='${"memberupload/"}${memberinfo.memberfile}'/>
				</c:if>
				<%-- 위에서 memberinfo.memberfile의 값이 있는 경우와 없는 경우에 따라 src 속성값이 달라집니다. --%>	
				<img src="${src}" width="20px" alt="profile">
			</span>
			<%--accept: 업로드 할 파일 타입을 설정합니다.
				<input type="file" accept="파일 확장자|audio/*|video/*|image/*|미디어타입">
				파일 확장자는 .png, .jpg, .pdf, .hwp 처럼 (.)으로 시작되는 파일 확장자를 의미합니다.
				audio/* : 모든 타입의 오디오 파일
				image/* : 모든 타입의 이미지 파일
			--%>
			<input type="file" name="memberfile" accept="image/*">
		</label>
		<div class="clearfix">
			<button type="submit" class="submitbtn">수정완료</button>
			<button type="button" class="cancelbtn">취소</button>
		</div>
	</form>
<script>
	//성별 체크해주는 부분
	$("input[value='${memberinfo.user_gender}']").prop('checked',true);
	
	$(".cancelbtn").click(function(){
		history.back();
	});
	
	//처음 화면 로드시 보여줄 이메일은 이미 체크 완료된 것이므로 기본 checkemail=true입니다.
	var checkemail=true;
	$("input:eq(4)").on('keyup',
			function() {
				$("#email_message").empty();
				//[A-Za-z0-9_]와 동일한 것이 \w
				//+는 1회이상 반복을 의미합니다. {1,}와 동일합니다.
				//\w+ 는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
				var pattern = /^\w+@\w+[.]\w{3}$/;
				var email = $("input:eq(6)").val();
				if (!pattern.test(email)) {
					$("#email_message").css('color','red').html("이메일형식이 맞지않습니다.");
					checkemail=false;
				}else{
					$("#email_message").css('color','green').html("이메일형식에 맞습니다.");
					checkemail=true;
				}
			});	//email keyup 이벤트 처리 끝
	
	var check=0;	// <========================================= 의미?
		/*	if(!$.isNumeric($("input[name='user_jumin']").val())){
			alert("주민번호는 숫자를 입력하세요");
			$("input[name='jumin']").val('').focus();
			return false;
		}
		*/ // ================================= 주민번호 일단 제외	
	
	$('form').submit(function(){
		if(!checkemail){
			alert("email 형식을 확인 하세요");
			$("input:eq(6)").focus();
			return false;
		}
		
		if (check ==0) {
			value = $('#filename').text();
			html = "<input type='text' value='" + value + "' name='check'>";
			$(this).append(html);
		}
	})
	
	
	$('input[type=file]').change(function(event){
		check++;
		var inputfile = $(this).val().split('\\');
		var filename=inputfile[inputfile.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;//플래그 i는 대소문자 구분없는 검색
		if (pattern.test(filename)) {
			$('#filename').text(filename);//inputfile.length - 1 =2
			
			var reader = new FileReader();	//파일을 읽기 위한 객체 생성
		//DataURL 형식으로 파일을 읽어옵니다.
		//읽어온 결과는 reader객체의 result 속성에 저장됩니다.
		//event.target.files[0] : 선택한 그림의 파일객체에서 첫번째 객체를 가져옵니다.
			reader.readAsDataURL(event.target.files[0]);
		
			reader.onload = function(event) {//읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#showImage').html('<img width="20px" src="'
									+ event.target.result + '">');
			};
		}else{
			alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
			check=0;
		}
	})//$('input[type=file]').change() end
</script>
</body>
</html>