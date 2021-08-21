<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
 <jsp:include page="header.jsp" /> 
 <title>RocketLaptop - 문의사항</title>
 <script src="js/qna/qnaWriteform.js"></script> 
 <style>
 	h1{font-size:1.5rem; text-align:center; margin-top : 20px;}
 	.container{width:45%; margin-bottom : 50px;}
 	label{font-weight:bold}
 	#qna_upfile{display:none}
 	.fileimg{width:20px;}
 	textarea{resize : none;}
 </style>
</head>
<body>
<input type="hidden" value="-1" class="search_field">
<jsp:include page="headernav.jsp" />
<div class="container">
  <form action="QnaAddAction.ma" method="post" enctype="multipart/form-data"
  		name="qnaform" id="writeForm">
  	<h1 style="font-size : 40px;">RocketLaptop - 문의사항 글쓰기</h1>		
  	<div class="form-group">
  	<label for="qna_name">글쓴이</label>
  	<input name="qna_name" id="qna_name" value="${user_id}" readOnly
  		   type="text" 	class="form-control" placeholder="작성자명을 입력하세요">
  	</div>	
  	<div class="form-group">
  	<label for="qna_pass">비밀번호</label>
  	<input name="qna_pass" id="qna_pass" type="password" maxlength="30"
  		   class="form-control" placeholder="비밀번호를 입력하세요">
  	</div>	
  	<div class="form-group">
  	<label for="qna_subject">제목</label>
  	<input name="qna_subject" id="qna_subject" type="text" maxlength="100"
  		   class="form-control" placeholder="제목을 작성하세요">
  	</div>	
  	<div class="form-group">
  	<label for="qna_content">내용</label>
  	<textarea name="qna_content" id="qna_content" 
  			  rows="10"  class="form-control"></textarea>
  	</div>	
  	<div class="form-group">
  	<label for="qna_file">파일 첨부</label>
  	<label for="qna_upfile">
  		<img src="image/attach.png" alt="파일첨부" class="fileimg">
  	</label>
  	<input type="file" id="qna_upfile"  name="qna_file">
  	<span id="qna_filevalue"></span>
  	</div>	
	<div class="form-group text-right">
	<button type=submit class="btn btn-primary">등록</button>
	<a class="btn btn-danger" href="javascript:history.back();">취소</a>
	</div>
  </form>
  
  <!-- 오류 모달창 -->
	<div class="modal hide fade" id="qnaWriteErrorModal">
		<div class="modal-dialog modal-sm modal-dialog-centered">
			<div class="modal-content">
	      
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="qnaWriteErrorModal-Title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	        
				<!-- Modal body -->
				<div class="modal-body" id="qnaWriteErrorModal-body">
				  
				</div>
	        
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
				</div>
	        
			</div>
		</div>
	</div>
</div>
<hr>
<jsp:include page="footer.jsp" />
</body>
</html>