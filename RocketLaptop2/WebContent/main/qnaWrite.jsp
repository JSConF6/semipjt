<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
 <jsp:include page="header.jsp" /> 
 <script src="js/main/qnaWriteform.js"></script> 
 <style>
 	h1{font-size:1.5rem; text-align:center; color:#1a92b9}
 	.container{width:60%}
 	label{font-weight:bold}
 	#qna_upfile{display:none}
 	img{width:20px;}
 </style>
</head>
<body>
<jsp:include page="headernav.jsp" />
<div class="container">
  <form action="QnaAddAction.ma" method="post" enctype="multipart/form-data"
  		name="qnaform">
  	<h1>MVC 게시판-write 페이지</h1>		
  	<div class="form-group">
  	<label for="qna_name">글쓴이</label>
  	<input name="qna_name" id="qna_name" value="${user_id}" readOnly
  		   type="text" 	class="form-control" placeholder="Enter qna_name">
  	</div>	
  	<div class="form-group">
  	<label for="qna_pass">비밀번호</label>
  	<input name="qna_pass" id="qna_pass" type="password" maxlength="30"
  		   class="form-control" placeholder="Enter qna_pass">
  	</div>	
  	<div class="form-group">
  	<label for="qna_subject">제목</label>
  	<input name="qna_subject" id="qna_subject" type="text" maxlength="100"
  		   class="form-control" placeholder="Enter qna_subject">
  	</div>	
  	<div class="form-group">
  	<label for="qna_content">내용</label>
  	<textarea name="qna_content" id="qna_content" 
  			  rows="10"  class="form-control"></textarea>
  	</div>	
  	<div class="form-group">
  	<label for="qna_file">파일 첨부</label>
  	<label for="upfile">
  		<img src="image/attach.png" alt="파일첨부">
  	</label>
  	<input type="file" id="qna_upfile"  name="qna_file">
  	<span id="qna_filevalue"></span>
  	</div>	
	<div class="form-group">
	<button type=submit class="btn btn-primary">등록</button>
	<button type=reset class="btn btn-danger">취소</button>
	</div>
  </form>
</div>  
</body>
</html>