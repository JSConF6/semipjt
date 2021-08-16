<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop</title>
		<script>
			$(function() {
				var checkuser_id=false;
				var checkuser_email=false;
				//주민번호 앞자리 6자리인 경우
				if (jumin1.value.length == 6) {
					pattern=/^[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|[3][01])$/;
					if(pattern.test(jumin1.value)){
						jumin2.focus();
					} else { 
						alert("숫자또는 형식에 맞게 입력하세요");
						jumin1.value = '';
						jumin1.focus();
					}
				}
				
				//주민번호 앞자리 7자리인 경우		
				if (jumin2.value.length == 7) {
					pattern = /^[1234][0-9]{6}$/;
					if (pattern.test(jumin2.value)){
					
					//주민 번호 뒷자리에 따라 남자 여자 성별 라디오 버튼 자동선택
					var c = Number(jumin2.value.substring(0,1));
					var index=(c-1)%2;
					var user_gender = document.getElementById("user_gender"+(index+1));
					gender.checked=true;
					} else {
						alert("형식에 맞게 입력하세요");
						jumin2.value = '';
						jumin2.focus();
					} //if
				} //if
					
					if(!checkuser_id){
						alert("사용가능한 id로 입력하세요.");
						$("input:eq(0)").val('').focus();
						return false;
					}
					
					if(!checkuser_email){
						alert("email 형식을 확인하세요");
						$("input:eq(6)").focus();
						return false;
					}
				}
			
				$("input:eq(6)").on('keyup', function() {
						$("#email_message").empty();
						var pattern = /^\w+@\w+[.]\w{3}$/;
						var user_email = $("input:eq(6)").val();
						if (!pattern.test(email)){
							$("#email_message").css('color', 'red')
												.html("이메일형식이 맞지 않습니다.");
							checkuser_email=false;
						}else{
							$("#email_message").css('color', 'green')
												.html("이메일형식에 맞습니다.");
							checkemail=true;
						}
				}); //email keyup 이벤트 처리 끝
				
				$("input:eq(0)").on('keyup', function() {
					checkid=true;
					$("#message").empty();
					
					var pattern = /^\w{5,12 $/};
					var user_id = $("input:eq(0)").val();
					if (!pattern.test(id)){
						$('#message').css('color', 'red').html("영문자 숫자_로 5~12자 가능합니다.");
							checkid=false;
						}
					})
			})
		</script>
		<style>
			#title{
				font-size: 30px;
				margin-top: 0px;
			}
		
			#search_input{
				width: 700px;
				border-radius: .25rem;
			}
		
			.fonticon{
				margin-left: 20px;
			}
			
			select{
				color : #495057;
				background-color : #fff;
				background-clip : padding-box;
				border : 1px solid #ced4da;
				border-radius : .25rem;
				transition : border-color .15s ease-in-out, box-shadow .15s ease-in-out;
				outline : none;
				margin-right: 6px;
			}
		
			#category{
				font-size: 25px;
			}
		
			#category > li{
				margin-right: 50px;
			}
			
			.carousel-inner img {
		   		width: 100%;
		   		height: 100%;
		  	}
		
			footer{
				height: 100px;
				background-color: gray;
			}

			.fa-user{
				padding-left: 20px;
			}

			#nav-search{
				height:  70px;
			}
		</style>
	</head>
	<body>
		<nav class="navbar navbar-expand-sm bg-secondary navbar-dark pt-3 justify-content-center" id="nav-search">
			<a class="navbar-brand" id="title" href="#">RocketLaptop</a>
			<form class="form-inline" action="#">
				<div class="input-group">
					<select id="viewcount" name="search_field">
						<option value="0" selected>상품명</option>
						<option value="1">브랜드명</option>
					</select>
					<input class="form-control mr-sm-2" id="search_input" type="text" placeholder="상품명을 입력하세요">
					<button class="btn btn-success" type="submit">검색</button>
				</div>
			</form>
			<ul class="navbar-nav">
				<c:if test="${id == 'admin'}">
					<li class="nav-item">
						<a class="nav-link fonticon" href="#"><i class="fas fa-user fa-2x"></i><br>관리자 페이지</a>
					</li>
				</c:if>
				<c:if test="${id != 'admin' || empty id}">
					<li class="nav-item">
						<a class="nav-link fonticon" href="#"><i class="fas fa-user fa-2x"></i><br>마이 페이지</a>
					</li>
					<li class="nav-item">
						<a class="nav-link fontiocn" href="#"><i class="fas fa-shopping-cart fa-2x"></i><br>장바구니</a>
					</li>
				</c:if>
			</ul>
		</nav>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-center">
			<ul class="navbar-nav" id="category">
				<li class="nav-item dropdown">
				 	<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						<i class="fas fa-align-justify fa-1x"></i> 카테고리
				  	</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">브랜드명</a>
						<a class="dropdown-item" href="#">브랜드명</a>
						<a class="dropdown-item" href="#">브랜드명</a>
				  	</div>
				</li>
				
			  	<li class="nav-item">
					<a class="nav-link" href="#">새로운 상품</a>
			  	</li>
			  	<li class="nav-item">
					<a class="nav-link" href="#">베스트 상품</a>
			 	 </li>
			  	<li class="nav-item">
					<a class="nav-link" href="#">공지사항</a>
			 	 </li>
			  	<li class="nav-item">
					<a class="nav-link" href="bbs.jsp">문의사항</a>
			 	</li>
			</ul>
		</nav>
		
	<!-- 회원가입 양식 -->
	<form name="joinform" action="joinProcess.net" method="post">
		<h1>회원가입</h1>
		<hr>
		<b>아이디</b>
		<input type="text" name="user_id" placeholder="아이디" required maxLength="12">
		<span id="message"></span>
		
		<b>비밀번호</b><input
			type="password" name="user_password" placeholder="비밀번호" required>
			
		<b>비밀번호 확인</b><input
			type="password" name="user_password1" placeholder="비밀번호 확인" required>
		
		<b>이름</b><input type="text" name="name" placeholder="이름"
			maxLength="5" required>
			
		<b>주민등록번호</b><input type="text" name="user_jumin" maxLength="14" placeholder="주민등록번호" required>
		
		<b>성별</b>
		<div>
			<input type="radio" name="user_gender" value="남" checked><span>남자</span>
			<input type="radio" name="user_gender" value="여"><span>여자</span>
		</div>
		
		<b>이메일주소 </b>
		<input type="text" name="user_email" placeholder="이메일" maxLength="30" required>
		<span id="email_message"></span>
		
		<b>전화번호 </b>
		<input type="text" name="user_phone" placeholder="전화번호" maxLength="20" required>
		
		<b>우편번호 </b>
		<input type="text" name="user_address1" placeholder="우편번호" maxLength="10" required>
		
		<b>주소 </b>
		<input type="text" name="user_address2" placeholder="주소" maxLength="100" required>
		
	
		<div class="celafix">
			<button type="submit" class="submitbtn">회원가입</button>
			<button type="reset" class="cancelbtn">다시작성</button> 
		</div>
		
	</form>

		<jsp:include page="footer.jsp" />
	</body>
</html>