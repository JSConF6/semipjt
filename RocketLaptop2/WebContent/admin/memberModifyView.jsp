<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<title>관리자 페이지 - 회원 수정</title>
		<link href="css/member/memberModifyView.css" type="text/css" rel="stylesheet">
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script type="text/javascript" src="js/member/memberModifyView.js"></script>
	</head>
	<body>
		<c:if test="${user_id == 'admin' }">
			<input type="hidden" value="${m.user_gender }" class="user_gender">
			<div id="ModifyDiv" class="mt-2">
				<h1>회원 수정 - 관리자</h1>
				<form action="MemberModifyAction.ad" id="memberModifyFrom" method="post" name="memberModifyFrom">				
					
					<!-- 회원 아이디 -->
					<div class="form-group text-left">
						<label for="user_id">아이디</label>
						<input type="text" class="form-control"
							   id="user_id" name="user_id" value="${m.user_id}" readOnly>
					</div>
					
					<!-- 회원 비밀번호 -->
					<div class="form-group text-left">
						<label for="user_password">비밀번호</label>
						<input type="text" class="form-control"
							   id="user_password" name="user_password" value="${m.user_password}">
					</div>
					
					<!-- 회원 이름 -->
					<div class="form-group text-left">
						<label for="user_name">이름</label>
						<input type="text" class="form-control"
							   id="user_name" name="user_name" value="${m.user_name}" readOnly>
					</div>
					
					<!-- 회원 생년월일 -->
					<div class="form-group text-left">
						<c:set var="birthdate" value="${m.user_birthdate}"/>
						<c:set var="birthdate" value="${fn:substring(birthdate, 0, 4)}-${fn:substring(birthdate, 4, 6)}-${fn:substring(birthdate, 6, 8)}" />
						<label for="user_datebirth">생년월일</label>
						<input type="text" class="form-control"
						       id="user_datebirth" name="user_datebirth" value="${birthdate}" readOnly>
					</div>
					
					<!-- 회원 성별 -->
					<div class="form-group text-left" style="font-size : 25px">
						<label for="user_gender">성별 </label>
						<div class="form-check-inline">
							<label class="form-check-label" for="male">
								<input type="radio" class="form-check-input"
								       id="male" name="user_gender" value="남자" onClick="return(false);">남자
							</label>
						</div>
						<div class="form-check-inline">
							<label class="form-check-label" for="female">
								<input type="radio" class="form-check-input"
								 	   id="female" name="user_gender" value="여자" onClick="return(false);">여자
							</label>
						</div>
					</div>
					
					<!-- 회원 이메일 -->
					<div class="form-group text-left">
				  		<label for="user_email">이메일</label>
				  		<input type="text" class="form-control"
						       id="user_email" name="user_email" value="${m.user_email}" >
					</div>
				    
					<!-- 회원 전화번호 -->
					<div class="form-group text-left">
						<label for="user_phone">전화번호</label>
						<input type="text" class="form-control"
						       id="user_phone" name="user_phone" value="${m.user_phone}">
					</div>
					
					<!-- 회원 우편번호 -->
					<div class="form-group text-left">
						<label for="user_address1">우편번호</label>
						<div class="input-group">
							<input type="text" class="form-control"
							       id="user_address1" name="user_address1" value="${m.user_address1}" readOnly>
							<button type="button" class="btn btn-secondary" id="postsearchbtn">우편검색</button>
						</div>
					</div>
					
					<!-- 회원 주소 -->
					<div class="form-group text-left">
						<label for="user_address2">주소</label>
						<input type="text" class="form-control"
						       id="user_address2" name="user_address2" value="${m.user_address2}" readOnly> 
					</div>
					
				   
					<!-- 버튼 -->
					<div class="text-right mb-4">
						<button type="submit" class="btn btn-primary">수정</button>
						<a type="button" class="btn btn-danger" href="javascript:history.back();">취소</a>
					</div>
				</form>
		
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
			</div>
		</c:if>
		<hr>
		<jsp:include page="../main/footer.jsp" />
	</body>
</html>