<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
 <title>MVC 게시판 </title>
 <jsp:include page="header.jsp" /> 
 <script src="js/qna/qnaReply.js"></script> <!-- <==========================================================경로 확인하기  -->
 <style>
 	h1{font-size:1.5rem; text-align:center; color:#1a92b9}
 	label{font-weight:bold}
 	.container{width:60%}
 </style>
</head>
<body>
<jsp:include page="headernav.jsp" />
<div class="container">
  <form action="QnaReplyAction.ma" method="post" name="qnaform">
  	<input type="hidden" name="qna_re_ref" value="${qnadata.qna_re_ref}">
  	<input type="hidden" name="qna_re_lev" value="${qnadata.qna_re_lev}">
  	<input type="hidden" name="qna_re_seq" value="${qnadata.qna_re_seq}">
  	<h1>MVC 게시판-Reply</h1>		
  	<div class="form-group">
  	<label for="qna_name">글쓴이</label>
  	<input name="qna_name" id="qna_name" 
  		   type="text"  	 value="${user_id}"		class="form-control" readOnly >
  	</div>	
  	<div class="form-group">
  	<label for="qna_subject">제목</label>
  	<textarea name="qna_subject" id="qna_subject"   rows="1"
  		      class="form-control" maxlength="100" >RE:${qnadata.qna_subject}</textarea>
  	</div>	
  	<div class="form-group">
  	<label for="qna_content">내용</label>
  	<textarea name="qna_content" id="qna_content"   rows="10"  
  			  class="form-control"></textarea>
  	</div>	

  	<div class="form-group">
  	<label for="qna_pass">비밀번호</label>
  	<input name="qna_pass" id="qna_pass" 
  		   type="password"   class="form-control" >
  	</div>	
	
	<div class="form-group">
	<input type=submit class="btn btn-primary" value="등록">
	<input type=button class="btn btn-danger" value="취소"
		   onClick="history.go(-1)">
	</div>
  </form>
</div>  
</body>
</html>