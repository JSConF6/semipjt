<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
// 로그인 처리 -> 로그인 x (로그인페이지 이동)
String user_id = (String) session.getAttribute("user_id");
if( user_id == null ){
	 response.sendRedirect("./MemberLogin.ma");
}
// 회원 비밀번호만 입력받아서 deletePro.jsp페이지 이동후 삭제
// 비밀번호와 db 비밀번호 일치 -> 삭제하면안됨 비번이 같은 회원이 있을 수 있음
// 따라서 
%> 
<fieldset>
	<legend>회원탈퇴</legend>
	<form action="./MemberDeleteAction.ma" method="post">
	<!-- input타입중 hidden은 화면에 있는 해당 input태그를 숨겨서 정보 전달   -->
		아이디 : <input type="hidden" name="user_id" value="<%=user_id %>" readonly><br>
		비밀번호 : <input type="password" name="user_password"><br>
		<input type="submit" class="btn" value="탈퇴하기">
		<input type="button" class="btn" value="뒤로가기" onclick="location.href='/main.ma'">
	</form>
</fieldset>
</body>
</html>
