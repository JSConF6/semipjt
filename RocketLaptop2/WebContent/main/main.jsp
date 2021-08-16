<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop</title>
		<script type="text/javascript" src="js/main/main.js"></script>
		<style>
			.carousel-inner img {
		   		width: 100%;
		   		height: 100%;
		  	}
		</style>
	</head>
	<body>
		<jsp:include page="headernav.jsp" />

		<!--banner-->
		<div class="container" style="height:400px">
			<div id="demo" class="carousel slide" data-ride="carousel">
				<ul class="carousel-indicators">
					<li data-target="#demo" data-slide-to="0" class="active"></li>
					<li data-target="#demo" data-slide-to="1"></li>
					<li data-target="#demo" data-slide-to="2"></li>
				</ul>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="image/banner.png" alt="Los Angeles" width="1100" height="500">
						<div class="carousel-caption">
							<h3>Los Angeles</h3>
							<p>We had such a great time in LA!</p>
						</div>   
					</div>
					<div class="carousel-item">
						<img src="image/banner.png" alt="Chicago" width="1100" height="500">
						<div class="carousel-caption">
							<h3>Chicago</h3>
							<p>Thank you, Chicago!</p>
						</div>   
					</div>
					<div class="carousel-item">
						<img src="image/banner.png" alt="New York" width="1100" height="500">
						<div class="carousel-caption">
							<h3>New York</h3>
							<p>We love the Big Apple!</p>
						</div>   
					</div>
				</div>
				<a class="carousel-control-prev" href="#demo" data-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</a>
				<a class="carousel-control-next" href="#demo" data-slide="next">
					<span class="carousel-control-next-icon"></span>
				</a>
			</div>
		</div>

		<!--bestproduct-->
		<div class="container mt-5 mb-5" id="bestProductMain">
			<h3>베스트 상품</h3>
			<div class="row row-cols-1 row-cols-md-3 g-4" id="bestProduct">
				<div class="col">
					<div class="card h-100">
						<img src="image/account_user.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>	
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/among us.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/android_robot.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--newproduct-->
		<div class="container mb-5" id="newProductMain">
			<h3>새로운 상품</h3>
			<div class="row row-cols-1 row-cols-md-3 g-4" id="newProduct">
				<div class="col">
					<div class="card h-100">
						<img src="image/heart.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/social.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/user.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
      
      <!-- https://getbootstrap.com/docs/3.4/css/  참고해서 적용할 것 -->
      <!-- The Modal (회원가입 화면) -->
      <!-- Modal -->
  <div class="modal fade" id="signup_modal" role="dialog">
     <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:15px 50px;">
          <h4><span class="glyphicon glyphicon-user"></span> 회원가입</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body" style="padding:40px 40px;">
          <form name="joinform" id="joinform" action="joinProcess.ma" method="post">
	            <div class="form-group">
	              <label for="user_id"><span class="glyphicon glyphicon-info-sign"></span> 아이디</label>
	              <input type="text" class="form-control" name="user_id" id="user_id" placeholder="아이디" required maxLength="12">
	              <span id="message"></span>
	            </div>
	            <div class="form-group">
	              <label for="user_password"><span class="glyphicon glyphicon-info-sign"></span> 비밀번호</label>
	              <input type="password" class="form-control" name="user_password" id="user_password" placeholder="비밀번호" required>
	            </div>
	            <div class="form-group">
	              <label for="psw_check"><span class="glyphicon glyphicon-info-sign"></span> 비밀번호 확인</label>
	              <input type="password" class="form-control" name="user_password1" id="user_password1" placeholder="비밀번호 확인" required>
	            </div>
	            <div class="form-group">
	              <label for="user_name"><span class="glyphicon glyphicon-info-sign"></span> 이름</label>
	              <input type="text" class="form-control" name="user_name" id="user_name" placeholder="이름" maxLength="5" required>
	            </div>
	            <div class="form-group">
	              <label for="user_birthdate"><span class="glyphicon glyphicon-info-sign"></span> 생년월일</label>
	              <input type="text" class="form-control" name="user_birthdate" id="user_birthdate" maxLength="8" placeholder="생년월일 (ex)19980101" required>
	            </div>
	            <div class="form-group">
	              <label for="user_gender"><span class="glyphicon glyphicon-info-sign"></span> 성별</label>
	              <br>
	              <input type="radio" name="user_gender" value="남" checked><span>남자</span>
				  <input type="radio" name="user_gender" value="여"><span>여자</span>
	            </div>
			    <div class="form-group">
                <label for="user_email"><span class="glyphicon glyphicon-info-sign"></span> 이메일주소</label>
                <input type="text" class="form-control" name="user_email" id="user_email" maxLength="30" placeholder="이메일" required>
                <span id="email_message"></span>
	            </div>
			    <div class="form-group">
                <label for="user_phone"><span class="glyphicon glyphicon-info-sign"></span> 전화번호</label>
                <input type="text" class="form-control" name="user_phone" id="user_phone" maxLength="20" placeholder="전화번호" required>
	            </div>
			    <div class="form-group">
                <label for="user_address1"><span class="glyphicon glyphicon-info-sign"></span> 우편번호</label>
                <input type="text" class="form-control" name="user_address1" id="user_address1" maxLength="5" placeholder="우편번호" required>
	            </div>
				<div class="form-group">
                <label for="user_address2"><span class="glyphicon glyphicon-info-sign"></span> 주소</label>
                <input type="text" class="form-control" name="user_address2" id="user_address2" maxLength="40" placeholder="주소" required>
	            </div>				
				<div class="form-group">
                <label for="user_memberfile"><span class="glyphicon glyphicon-paperclip"></span> 프로필사진</label>
                <input type="file"  name="user_memberfile" id="user_memberfile" maxLength="20" accept="image/*">
	            </div>				
		  </form>
        </div>
        <div class="modal-footer">
		    <button type="submit" class="btn btn-primary btn-lg">회원가입</button>
		    <button type="button" class="btn btn-default btn-lg" data-dismiss="modal">가입취소</button>
      		</div>
        </div>
      </div>
     </div>
  <script>

	$(function(){
		$("#login").click(function(){
		    $("#myModal").modal();
		  });
		
		  $("#signup").click(function(){
		  	$("#signup_modal").modal();
		  });
		  
		  $('#joinform').submit(function(){
			  var checked=false;
			  var checkemail=false;
				$("#joinform").submit(function(){
					if(!$.isNumeric($("#user_birthdate").val())){
						alert("생년월일은 예시와 같이 숫자를 입력하세요");
						$("#user_birthdate").val('').focus();
						return false;
					}
					
					if(!checked){
						alert("사용가능한 id로 입력하세요");
						$("#user_id").val('').focus();
						return false;
					}
					
					if(!checkemail){
						alert("email 형식을 확인 하세요");
						$("#user_email").focus();
						return false;
					}
					
				})
		  });
				
		$("#user_email").on('keyup',
				function() {
					$("#email_message").empty(); 
					//[A-Za-z0-9_]와 동일한 것이 \w
					//+는 1회이상 반복을 의미합니다. {1,}와 동일합니다.
					//\w+ 는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
					var pattern = /^\w+@\w+[.]\w{3}$/;
					var user_email = $("#user_email").val();
					if (!pattern.test(user_email)) {
						$("#email_message").css('color','red')
										   .html("이메일형식이 맞지않습니다.");
						checkemail=false;
					}else{
						$("#email_message").css('color','green')
						   .html("이메일형식에 맞습니다.");
						checkemail=true;
					}
		});	//email keyup 이벤트 처리 끝
		
		$("#user_id").keyup(function(){
			checkid=true;
			$("#message").empty();//처음에 패턴에 적합하지 않은 경우 메시지 출력후 적합한 데이터를
			//[A-Za-z0-9_]와 동일한 것이 \w
			//+는 1회이상 반복을 의미합니다. {1,}와 동일합니다.
			//\w+ 는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
			var pattern = /^\w{5,12}$/;
			var user_id = $("#user_id").val();
			console.log(user_id);
			if (!pattern.test(user_id)) {
				$("#message").css('color','red')
				             .html("영문자 숫자_로 5~12자 가능합니다.");
				checkid=false;
				return;
			}
			
			$.ajax({
				url :"idcheck.ma",
				data :{"user_id": user_id},
				success : function(resp){
					if (resp == -1){//db에 해당 id가 없는 경우
						$("#message").css('color','green').html(
								"사용 가능한 아이디 입니다.");
						checkid=true;
					} else{//db에 해당 id가 있는 경우(0)
						$("#message").css('color','blue').html(
						"이미 사용중인 아이디 입니다.");
						checkid=false;
					}
				}
			});//ajax end
		})//id keyup en
	});

</script>
		<!-- 오류 모달창 -->
		<div class="modal hide fade" id="ErrorModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="ErrorModal-Title"></h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body" id="ErrorModal-body">
					  
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
		        
				</div>
			</div>
		</div>
	</body>
</html>