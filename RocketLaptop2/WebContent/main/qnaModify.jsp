<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
 <title>MVC 게시판 </title>
 <jsp:include page="header.jsp" /> 
 <script src="js/qna/qnaModifyform.js"></script>	<!-- <======================= 경로 확인하기  --> 
 <style>
 	.container{width:60%}
 	h1{font-size:1.5rem; text-align:center; color:#1a92b9}
 	#qna_upfile{display:none}
 </style>
</head>
<body>
<%--게시판 수정 --%>
<jsp:include page="headernav.jsp" />
<div class="container">
  <form action="QnaModifyAction.ma" method="post" name="qna_modifyform"
  		enctype="multipart/form-data"> <%-- <================= reply와는 다르게 파일첨부 있으므로 --%>
  	<input type="hidden" name="qna_num" value="${qnadata.qna_num}">
  	<h1>MVC 게시판 - 수정</h1>		
  	<div class="form-group">
  	<label for="qna_name">글쓴이</label> <input type="text"  
  		   class="form-control" value="${qnadata.qna_name}" readOnly >
  	</div>	
  	
  	<div class="form-group">
  	<label for="qna_subject">제목</label>
  	<textarea name="qna_subject" id="qna_subject"   rows="1"
  		      class="form-control" maxlength="100" >${qnadata.qna_subject}</textarea>
  	</div>	
  	
  	<div class="form-group">
  	<label for="qna_content">내용</label>
  	<textarea name="qna_content"  id="qna_content"    
  			  class="form-control"  rows="15" >${qnadata.qna_content}</textarea>
  	</div>	
<%--원문글인 경우에만 파일첨부 수정 가능합니다 --%>
	<c:if test="${qnadata.qna_re_lev==0}">
		<div class="form-group read">
			<label for="qna_file">파일첨부</label>
				<label for="qna_upfile">
					<img src="image/attach.png" alt="파일첨부" width="20px">
				</label>
				<input type="file" id="qna_upfile" name="qna_file">
				<span id="qna_filevalue">${qnadata.qna_file}</span>
				<img src="image/remove.png" alt="파일삭제" width="10px" class="remove">
		</div>
	</c:if>
	
  	<div class="form-group">
  	<label for="qna_pass">비밀번호</label>
  	<input name="qna_pass" 
  		   id="qna_pass"       type="password"   size="10"    maxlength="30"
  		   class="form-control"  placeholder="Enter qna_pass" value="">
  	</div>	
	
	<div class="form-group">
	<button type=submit class="btn btn-primary">수정</button>
	<button type=reset  class="btn btn-danger" 
		   onClick="history.go(-1)">취소</button>
	</div>
  </form>
</div>  <%--class="container" end --%>
</body>
</html>