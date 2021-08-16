<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="js/main/header.js" type="text/javascript"></script>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

	*{
		font-family: 'Do Hyeon', sans-serif;
	}

	 #nav-top{
		height: 40px;
		font-size: 25px;
	 }

	.navbar-dark .navbar-nav .nav-link {
		color: rgb(255, 255, 255);
	}
	
	#loginUser{
		color : white;
	}
	
	.logoutbtn{
		color : white;
	}
	
	footer{
		height : 80px;
	}
	
	#logo{
		float : left;
	}
			
	/* 로그인에 적용할 스타일 내역 추가   향후 색상 변경 검토 할 것!!!!!!!!!!!!!!!!!!!!!!!!!!! */
	.loginmodal-header, .loginmodal-header > h4, .loginmodal-header > .close {
	    background-color: #5cb85c;
	    color:white !important;
	    text-align: center;
	    font-size: 30px;
	}
	
	.loginmodal-footer {
	    background-color: #f9f9f9;
	    justify-content: center;
	}
</style>

<nav class="navbar navbar-expand-sm bg-white navbar-dark float-right mt-3" id="nav-top">
	<ul class="navbar-nav">
		<c:if test="${!empty user_id}">
			<c:if test="${user_id == 'admin' }">
				<li class="nav-item">
					<span id="loginUser" class="text-dark">관리자님 </span><a href="logout.ma" class="logoutbtn text-dark">(로그아웃)</a>
				</li>
			</c:if>
			<c:if test="${user_id != 'admin'}">
				<li class="nav-item">
					<span id="loginUser" class="text-dark">${user_id}님 </span><a href="logout.ma" class="logoutbtn text-dark">(로그아웃)</a>
				</li>
			</c:if>
		</c:if>
		<c:if test="${empty user_id}">
			<li class="nav-item">
				<a class="nav-link text-dark" id="login" style="cursor : pointer;">로그인</a>
			</li>
			<li class="nav-item">
				<a class="nav-link text-dark" href="#">회원가입</a>
			</li>
		</c:if>
	</ul>
</nav>

<!--  로그인  모달 기능 구현 소스 _ 향후 배경 색상 검토 할 것!! -->
<!-- LoginModal -->
<div class="modal fade" id="loginModal" role="dialog">
	<div class="modal-dialog">
    
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header loginmodal-header" style="padding:35px 50px;">
				<h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" style="padding:40px 50px;">
				<form role="form" action="loginProcess.ma" method="post">
					<div class="form-group">
						<label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
						<input type="text" class="form-control" id="user_id" name="user_id" placeholder="아이디를 입력하세요">
					</div>
					<div class="form-group">
						<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
						<input type="password" class="form-control" id="user_password" name="user_password" placeholder="비밀번호를 입력하세요">
					</div>
					<div class="checkbox">
						<label><input type="checkbox" value="" checked>ID저장</label>
					</div>
						<button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> 로그인</button>
				</form>
			</div>
			<div class="modal-footer loginmodal-footer">
				<p>Not a member? <a href="#"> 회원가입  &nbsp; </a></p>
				<p>Forgot <a href="#"> 비밀번호찾기 &nbsp; </a></p>
				<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> 취소
          		</button>
		  		<strong>본 사이트는 Chrome 브라우저에 최적화되어 있습니다.</strong> 
			</div>
		</div> 
	</div>
</div>