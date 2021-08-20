<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<jsp:include page="AdminNav.jsp" />
		<title>관리자 페이지 - 회원 목록</title>
		<link href="css/admin/adminNav.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="js/member/memberList.js"></script>
		<link href="css/member/memberList.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<c:if test="${user_id == 'admin'}">
				<h1 class="MemberListTitle"><a href="MemberList.ad">회원 목록</a></h1>
				<form action="MemberList.ad" method="post">
				    <div class="input-group">
				        <select id="userinfo" name="search_field">
				            <option value="0" selected>아이디</option>
				            <option value="1">이름</option>
				            <option value="2">성별</option>
				            <option value="3">이메일</option>
				            <option value="4">전화번호</option>
				        </select>
				        <input name="search_word" type="text" class="form-control"
				               placeholder="아이디 입력하세요" value="${search_word}" id="search_word">
				        <button id="search" class="btn btn-primary" type="submit">검색</button>
				    </div>
				</form>
				<input type="hidden" value="${search_field}" class="search_field">
				<h2>회원 수 : ${listcount}</h2>
				
				<c:if test="${listcount > 0 }">
					<table class="table table-striped table-bordered text-center">
						<thead>
							<tr>
								<th>프로필사진</th>
								<th>아이디</th>
								<th style="width : 92px;">이름</th>
								<th style="width : 105px;">생년월일</th>
								<th style="width : 50px;">성별</th>
								<th>이메일</th>
								<th style="width : 132px;">전화번호</th>
								<th>주소</th>
								<th style="width : 107px;">가입일</th>
								<th style="width : 76px;">수정</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="m" items="${memberlist}">
							<c:set var="birthdate" value="${m.user_birthdate}"/>
							<c:set var="birthdate" value="${fn:substring(birthdate, 0, 4)}-${fn:substring(birthdate, 4, 6)}-${fn:substring(birthdate, 6, 8)}" /> 
								<tr>
									<td><img src="image/among us.png" style="width : 70px;"></td>
									<td class="align-middle">${m.user_id}</td>
									<td class="align-middle"><a href="MemberDetail.ad?user_id=${m.user_id}">${m.user_name}</a></td>
									<td class="align-middle">${birthdate}</td>
									<td class="align-middle">${m.user_gender }</td>
									<td class="align-middle">${m.user_email }</td>
									<td class="align-middle">${m.user_phone }</td>
									<td class="align-middle">${m.user_address1}<br> ${m.user_address2}</td>
									<td class="align-middle">${m.user_joindate}</td>
									<td class="align-middle">
									<a class="btn btn-primary" id="MemberModifybtn" href="MemberModifyView.ad?user_id=${m.user_id}">수정</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<ul class="pagination justify-content-center">
						<c:if test="${page <= 1 }">
							<li class="page-item">
								<a class="page-link gray">이전&nbsp;</a>
							</li>
						</c:if>
						<c:if test="${page > 1 }">
							<li class="page-item">
								<a href="MemberList.ad?page=${page-1}&search_field=${search_field}&search_word=${search_word}" class="page-link">이전&nbsp;</a>
							</li>
						</c:if>
						
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page }">
								<li class="page-item " >
						   			<a class="page-link gray">${a}</a>
								</li>
							</c:if>
							<c:if test="${a != page }">
					    		<li class="page-item">
						   			<a href="MemberList.ad?page=${a}&search_field=${search_field}&search_word=${search_word}" 
						      		class="page-link">${a}</a>
					    		</li>	
							</c:if>
						</c:forEach>
						
					    <c:if test="${page >= maxpage }">
							<li class="page-item">
					   			<a class="page-link gray">&nbsp;다음</a> 
							</li>
						</c:if>
						<c:if test="${page < maxpage }">
				  			<li class="page-item">
								<a href="MemberList.ad?page=${page+1}&search_field=${search_field}&search_word=${search_word}"
					   				class="page-link">&nbsp;다음</a>
				  			</li>	
						</c:if>
					</ul>
				</c:if>
				
				<c:if test="${listcount == 0 && empty search_word}">
					<h1 class="MemberListBody">회원이 없습니다.</h1>
				</c:if>
				
				<c:if test="${listcount == 0 && !empty search_word}">
					<h1 class="MemberListBody">검색결과가 없습니다.</h1>
				</c:if>
				  
				<!-- 오류 모달창 -->
				<div class="modal hide fade" id="MemberErrorModal">
					<div class="modal-dialog modal-sm modal-dialog-centered">
						<div class="modal-content">
				      
							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title" id="MemberErrorModal-Title"></h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
				        
							<!-- Modal body -->
							<div class="modal-body" id="MemberErrorModal-body">
				          
							</div>
				        
							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
							</div>
				        
						</div>
					</div>
				</div>
			</c:if>
		</div>
		<hr>
		<jsp:include page="../main/footer.jsp" />
	</body>
</html>