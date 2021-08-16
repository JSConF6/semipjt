<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!--    <=============================== 삭제 예정 페이지  
<!doctype html>
<html>
<head>
<title>회원가입 시스템 로그인 페이지</title>
<link href="css/login.css" type="text/css" rel="stylesheet">
<script src ="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		$(function(){
			$(".join").click(function(){
				location.href="join.net"	
			});

			var user_id = '${user_id}';
			if(user_id){
					$("#user_id").val(user_id);
					$("#remember").prop('checked',true);
			}
		})
	</script>
</head>
<body>
<form name="loginform" action="/RocketLaptop/loginProcess.ma" method="post">
  <h1>로그인</h1>
      <hr>
      <b>아이디</b>
        <input type="text" name="user_id" placeholder="Enter id" id="user_id" required>
        <b>Password</b>
        <input type="password" name="user_password" placeholder="Enter password" required>
        <input type="checkbox" id="remember" name="remember" value="store">
        <span>remember</span>
        <div class="clearfix">
          <button type="submit" class="submitbtn">로그인</button>
          <button type="button" class="join">회원가입</button>
        </div>
</form>
</body>
</html>

 -->