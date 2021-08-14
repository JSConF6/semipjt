<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>회원관리 시스템 관리자모드(회원 정보 보기)</title>
<jsp:include page="/main/header.jsp"/>
<style>
	tr>td:nth-child(odd){font-weight:bold}
	td{text-align:center}
	.container{width:40%}
</style>
</head>
<body>
 <div class="container">
	<table class="table table-bordered">
	    <tr>
			<td>아이디</td>
			<td>${memberinfo.user_id}</td>
		</tr>
	    <tr>
			<td>비밀번호</td>
			<td>${memberinfo.user_password}</td>
		</tr>
	    <tr>
			<td>이름</td>
			<td>${memberinfo.user_name}</td>
		</tr>
	 <!--  주민번호 일단제외 8월13일  
	    <tr>
			<td>주민번호</td>
			<td>${memberinfo.user_jumin}</td> 					
		</tr>
	 -->   <tr>
			<td>성별</td>
			<td>${memberinfo.user_gender}</td>
		</tr>
	    <tr>
			<td>이메일</td>
			<td>${memberinfo.user_email}</td>
		</tr>
	    <tr>
			<td>전화번호</td>
			<td>${memberinfo.user_phone}</td>
		</tr>
	    <tr>
			<td>우편번호</td>
			<td>${memberinfo.user_address1}</td>
		</tr>
	    <tr>
			<td>주소(신주소)</td>
			<td>${memberinfo.user_address2}</td>
		</tr>
	    <tr>
		 <td colspan="2">
		 <a href="/RocketLaptop/main.ma">메인페이지로 돌아가기</a></td>
		</tr>
	</table>	
 </div>
</body>
</html>