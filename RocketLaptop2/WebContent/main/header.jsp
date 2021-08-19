<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
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
	
	.modal{
		overflow-y:auto;
	}
	
	#user_memberfile{
		display : none;
	}
	
	#update_user_memberfile{
		display : none;
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
				<a class="nav-link text-dark" id="signup" style="cursor : pointer;">회원가입</a>
			</li>
		</c:if>
	</ul>
</nav>

<!--  로그인  모달 기능 구현 소스 _ 향후 배경 색상 검토 할 것!! -->
<!-- LoginModal -->
<div class="modal hide fade" id="loginModal" role="dialog">
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
						<label for="login_id"><span class="glyphicon glyphicon-user"></span> Username</label>
						<input type="text" class="form-control" id="login_id" name="login_id" placeholder="아이디를 입력하세요"><!-- 교훈: 모달 id 중복 시 유효성검사 적용 안될 가능성 -->
					</div>
					<div class="form-group">
						<label for="login_password"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
						<input type="password" class="form-control" id="login_password" name="login_password" autocomplete="off" placeholder="비밀번호를 입력하세요">
					</div>
					<div class="checkbox">
						<label><input type="checkbox" value="" checked>ID저장</label>
					</div>
						<button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> 로그인</button>
				</form>
			</div>
			<div class="modal-footer loginmodal-footer">
				<p>Not a member? <a href="main.ma"> 회원가입  &nbsp; </a></p>
				<p>Forgot <a href="main.ma"> 비밀번호찾기 &nbsp; </a></p>
				<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> 취소
          		</button>
		  		<strong>본 사이트는 Chrome 브라우저에 최적화되어 있습니다.</strong> 
			</div>
		</div> 
	</div>
</div>

<!-- https://getbootstrap.com/docs/3.4/css/  참고해서 적용할 것 -->
<!-- The Modal (회원가입 화면) -->
<!-- Modal -->
<div class="modal hide fade" id="signup_modal" role="dialog">
	<div class="modal-dialog">
    
	    <!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="padding:15px 50px;">
				<h4><span class="glyphicon glyphicon-user"></span> 회원가입</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" style="padding:40px 40px;">
				<form name="joinform" id="joinform" action="joinProcess.ma" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="user_id"><span class="glyphicon glyphicon-info-sign"></span> 아이디</label>
						<input type="text" class="form-control" name="user_id" id="user_id" placeholder="아이디" maxLength="12">
						<span id="message"></span>
					</div>
					<div class="form-group">
						<label for="user_password"><span class="glyphicon glyphicon-info-sign"></span> 비밀번호</label>
						<input type="password" class="form-control" name="user_password" id="user_password" autocomplete="off" placeholder="비밀번호">
					</div>
					<div class="form-group">
						<label for="psw_check"><span class="glyphicon glyphicon-info-sign"></span> 비밀번호 확인</label>
						<input type="password" class="form-control" name="user_password1" id="user_password1" autocomplete="off" placeholder="비밀번호 확인">
					</div>
					<div class="form-group">
						<label for="user_name"><span class="glyphicon glyphicon-info-sign"></span> 이름</label>
						<input type="text" class="form-control" name="user_name" id="user_name" placeholder="이름" maxLength="5">
					</div>
					<div class="form-group">
						<label for="user_birthdate"><span class="glyphicon glyphicon-info-sign"></span> 생년월일</label>
						<input type="text" class="form-control" name="user_birthdate" id="user_birthdate" maxLength="8" placeholder="(예) 19980101">
					</div>
					<div class="form-group">
						<label for="user_gender"><span class="glyphicon glyphicon-info-sign"></span> 성별</label>
						<br>
						<input type="radio" name="user_gender" value="남자" checked><span>남자</span>
						<input type="radio" name="user_gender" value="여자"><span>여자</span>
					</div>
					<div class="form-group">
						<label for="user_email"><span class="glyphicon glyphicon-info-sign"></span> 이메일주소</label>
						<input type="text" class="form-control" name="user_email" id="user_email" maxLength="30" placeholder="(예) abc@abc.net">
						<span id="email_message"></span>
					</div>
					<div class="form-group">
						<label for="user_phone"><span class="glyphicon glyphicon-info-sign"></span> 전화번호</label>
						<input type="text" class="form-control" name="user_phone" id="user_phone" maxLength="20" placeholder="(예) 010-XXXX-XXXX">
					</div>
					<div class="form-group">
						<label for="user_address1"><span class="glyphicon glyphicon-info-sign"></span></label>
						<div class="input-group">
							<input type="text" class="form-control" name="user_address1" id="user_address1" size="5" maxLength="5" placeholder="우편번호 5자리">
							<input type="button" class="btn btn-warning" name="postcode" id="postcode" value="주소검색  by_kakao">
						</div>
						<input type="text" class="form-control" name="user_address2" id="user_address2" maxLength="40" placeholder="상세주소">
					</div>
					<div class="form-group">
						<span class="glyphicon glyphicon-paperclip"></span> 프로필사진<br>
						<label for="user_memberfile"><img src="image/attach.png" width="20px"></label> 
						<span id="filename">${memberinfo.user_memberfile}</span>

						<%-- memberinfo.memberfile의 값이 없으면 기본 사진을 보여줍니다.
							값이 존재하면 memberupload 폴더에 존재하는 파일명으로 경로를 설정합니다. --%>
						<span id="showImage">
							<c:if test='${empty memberinfo.user_memberfile}'>
								<c:set var='src' value='image/profile.png'/>
							</c:if>
							<c:if test='${!empty memberinfo.user_memberfile}'>
								<c:set var='src' value='${"memberupload/"}${memberinfo.user_memberfile}'/>
							</c:if>
							<%-- 위에서 memberinfo.memberfile의 값이 있는 경우와 없는 경우에 따라 src 속성값이 달라집니다. --%>	
							<img src="${src}" id="filenameSrc" width="20px" alt="profile">
						</span>
						<input type="file"  name="user_memberfile" id="user_memberfile" maxLength="20" accept="image/*">
					</div>
					<div> 
						<ul class="pagination justify-content-center ">
						<li><button type="submit" class="btn btn-primary btn-lg">가입완료</button></li>
			    		<li><button type="button" class="btn btn-secondary btn-lg" data-dismiss="modal">가입취소</button></li>
			    		</ul>
					</div> 
		  		</form>
			</div>
		</div>
	</div>
</div>



<!-- The Modal (마이페이지 회원정보 수정화면) -->
<!-- Modal -->
<div class="modal hide fade" id="updateMember_modal" role="dialog">
	<div class="modal-dialog">
    
	    <!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="padding:15px 50px;">
				<h4><span class="glyphicon glyphicon-user"></span> 회원 정보 수정</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" style="padding:40px 40px;">
				<form name="updateform" id="updateform" action="updateProcess.ma" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="user_id"><span class="glyphicon glyphicon-info-sign"></span> 아이디</label>
						<input type="text" class="form-control" name="update_user_id" id="update_user_id" value="" maxLength="12" readonly>
						<span id="update_message"></span>
					</div>
					<div class="form-group">
						<label for="user_password"><span class="glyphicon glyphicon-info-sign"></span> 비밀번호</label>
						<input type="password" class="form-control" name="update_user_password" id="update_user_password"  autocomplete="off" placeholder="비밀번호">
					</div>
					<div class="form-group">
						<label for="psw_check"><span class="glyphicon glyphicon-info-sign"></span> 비밀번호 확인</label>
						<input type="password" class="form-control" name="update_user_password1" id="update_user_password1"  autocomplete="off" placeholder="비밀번호 확인">
					</div>
					<div class="form-group">
						<label for="user_name"><span class="glyphicon glyphicon-info-sign"></span> 이름</label>
						<input type="text" class="form-control" name="update_user_name" id="update_user_name"  placeholder="이름" maxLength="5">
					</div>
					<div class="form-group">
						<label for="user_birthdate"><span class="glyphicon glyphicon-info-sign"></span> 생년월일</label>
						<input type="text" class="form-control" name="update_user_birthdate" id="update_user_birthdate"  maxLength="8" placeholder="(예) 19980101">
					</div>
					<div class="form-group">
						<label for="user_gender"><span class="glyphicon glyphicon-info-sign"></span> 성별</label>
						<br>
						<input type="radio" name="update_user_gender" value="남자"><span>남자</span>
						<input type="radio" name="update_user_gender" value="여자"><span>여자</span>
					</div>
					<div class="form-group">
						<label for="user_email"><span class="glyphicon glyphicon-info-sign"></span> 이메일주소</label>
						<input type="text" class="form-control" name="update_user_email" id="update_user_email"  maxLength="30" placeholder="(예) abc@abc.net">
						<span id="update_email_message"></span>
					</div>
					<div class="form-group">
						<label for="user_phone"><span class="glyphicon glyphicon-info-sign"></span> 전화번호</label>
						<input type="text" class="form-control" name="update_user_phone" id="update_user_phone"  maxLength="20" placeholder="(예) 010-XXXX-XXXX">
					</div>
					<div class="form-group">
						<label for="user_address1"><span class="glyphicon glyphicon-info-sign"></span></label>
						<div class="input-group">
							<input type="text" class="form-control" name="update_user_address1" id="update_user_address1"  size="5" maxLength="5" placeholder="우편번호 5자리">
							<input type="button" class="btn btn-warning" name="update_postcode" id="update_postcode" value="주소검색  by_kakao">
						</div>
						<input type="text" class="form-control" name="update_user_address2" id="update_user_address2"  maxLength="40" placeholder="상세주소">
					</div>
					<div class="form-group">
						<span class="glyphicon glyphicon-paperclip"></span> 프로필사진<br>
						<label for="update_user_memberfile"><img src="image/attach.png" width="20px"></label> 
						<span id="update_filename"></span>

						<%-- memberinfo.memberfile의 값이 없으면 기본 사진을 보여줍니다.
							값이 존재하면 memberupload 폴더에 존재하는 파일명으로 경로를 설정합니다. --%>
						<span id="update_showImage">
							<%-- 	<c:if test='${empty memberinfo.user_memberfile}'>
									<c:set var='src' value='image/profile.png'/>
								</c:if>
								<c:if test='${!empty memberinfo.user_memberfile}'>
									<c:set var='src' value='${"memberupload/"}${memberinfo.user_memberfile}'/>
								</c:if> --%>
							<img src="" id="update_filenameSrc" width="20px" alt="profile">
						</span>
						<input type="file"  name="update_user_memberfile" id="update_user_memberfile" maxLength="20" accept="image/*">
					</div>
					<!-- justify-content-center : 가운데 정렬 -->		
						<div> 
							<ul class="pagination justify-content-center ">
								<li><button type="submit" class="btn btn-primary btn-lg">수정완료</button></li>
					    		<li><button type="button" class="btn btn-secondary btn-lg" data-dismiss="modal">수정취소</button></li>
					    		<li><a data-toggle="modal" href="#myModal2" class="btn btn-danger btn-lg">회원탈퇴</a></li>				
				    		</ul>
						</div> 
		  		</form>
			</div>
		</div>
	</div>
</div>


<!-- 회원탈퇴  modal -->
    <div class="modal" id="myModal2" aria-hidden="true" style="display: none; z-index: 1180;">
    	<div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">회원탈퇴 최종확인</h4>
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div><div class="container"></div>
            <div class="modal-body">
            	<form name="memberDeleteform" id="memberDeleteform"  action="memberDelete.ma" method="post">
					<p><strong>비밀번호를 입력하세요.</strong></p>
					<input type="password" id="checkPassword" name="checkPassword">
              		<a href="#" type="submit" class="btn btn-danger" id="secession">탈퇴하기</a>
 	                <button type="button" class="btn btn-secondary"  data-dismiss="modal">취소</button>
				</form>
            </div>
            <div class="modal-footer">
            </div>
          </div>
        </div>
    </div>



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

