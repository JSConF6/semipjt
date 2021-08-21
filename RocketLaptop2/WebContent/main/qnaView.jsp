<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
 <title>RocketLaptop - 문의사항</title>
 <jsp:include page="header.jsp"/>
<script src="js/qna/qnaView.js"></script>
<link rel="stylesheet" href="css/qna/view.css">
<style>
	tr:nth-child(1) {text-align:center}
	tr:nth-child(1) {width: 20%}
	a {color:white}
	tr:nth-child(5)>td:nth-child(2)>a {color:black}
	tbody tr:last-child {text-align: center;}
	.btn-primary{background-color: #4f97e5}
	#myModal {display: none}
	tr>td:nth-child(1){font-weight:bold; font-size:1em}
</style>
</head>
<body>
<input type="hidden" value="-1" class='search_field'>
 <input type="hidden" id="loginid" value="${user_id}" name="loginid">
<jsp:include page="headernav.jsp" />
 <div class="container">
  <table class="table">
  	<tr><th colspan="2"><h1>RocketLaptop - 문의사항 상세</h1></th></tr>
  	<tr>
  		<td><div>글쓴이</div></td>
  		<td><div>${qnadata.qna_name}</div></td>
  	</tr>	
  	<tr>
  		<td><div>제목</div></td>
  		<td><c:out value="${qnadata.qna_subject}" /></td>
  	</tr>	
  	<tr>
  		<td><div>내용</div></td>
  		<td style="padding-right:0px"><textarea class="form-control" rows="5"
  			readOnly >${qnadata.qna_content}</textarea></td>
  	</tr>
  	
  	<c:if test="${qnadata.qna_re_lev==0}"><%--원문글인 경우에만  첨부파일을 추가할 수 있습니다. --%>
  		<tr>
  		<td><div>첨부파일</div></td>
  		<c:if test="${!empty qnadata.qna_file}"><%--파일 첨부한 경우 --%>
  		<td><img src="image/down.png" width="10px">
  			<a href="QnaFileDown.ma?qna_filename=${qnadata.qna_file}">
  			${qnadata.qna_file}</a></td>	
  		</c:if>
  		<c:if test="${empty qnadata.qna_file}"><%-- 파일 첨부하지 않은 경우 --%>
  			<td></td>
  		</c:if>
  		</tr>
  	</c:if>		

	<tr>
		<td colspan="2" class="center">
			<c:if test="${!empty user_id}">
				<a href="QnaReplyView.ma?num=${qnadata.qna_num}">
					<button class="btn btn-primary">답변</button>
				</a>
			</c:if>

		<c:if test="${qnadata.qna_name == user_id || user_id == 'admin'}">
			<a href="QnaModifyView.ma?num=${qnadata.qna_num}">
				<button class="btn btn-info">수정</button>
			</a>
			<%-- href의 주소를 #으로 설정합니다. --%>
			<a href="#">
				<button class="btn btn-danger" data-toggle="modal"
						data-target="#myModal">삭제</button>
			</a>
		</c:if>
		
		<a href="QnaList.ma">
			<button class="btn btn-secondary">목록</button>
		</a>
		</td>
  </table>
 <%-- 게시판 view end --%>
 
 	<%-- modal 시작 --%>
 	<div class="modal" id="myModal">
  	 <div class="modal-dialog modal-sm modal-dialog-centered">
 	  <div class="modal-content">
 	  	<div class="modal-header" style="padding-bottom : 0px;">
			<h4 class="modal-title">비밀번호 입력</h4>
			<button type="button" class="close" data-dismiss="modal">&times;</button>
		</div>
 	   <%-- Modal body --%>
 	   <div class="modal-body" style="padding-bottom : 0px; ">
 	   	<form name="deleteForm" class="deleteForm" action="QnaDeleteAction.ma" method="post">
 	   		<%--http://localhost:8088/Board/BoardDetailAction.bo?num=22
 	   		    주소를 보면 num을 파라미터로 넘기고 있습니다.
 	   		    이 값을 가져와서 ${param.num}를 사용
 	   		    또는 ${boarddata.board_num}
 	   		--%>
 	   		<input type="hidden" name="num" value="${param.num}"
 	   			id="comment_qna_num">
 	   		<div class="form-group">
 	   			<input  type="password"
 	   					class="form-control" placeholder="Enter password"
 	   					name="qna_pass" id="qna_pass">
 	   			</div>
 	   			<div class="text-right">
 	   				<button type="submit" class="btn btn-primary">전송</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
				</div> 	   	
 	   	</form>  
 	    </div><!-- class="modal-body" -->
 	   </div><!-- class="modal-content" -->
 	  </div><!-- class="modal-dialog" -->
 	 </div><!-- class="modal" -->
 	 
 	 <div class="CommentBox">
 	 	<div class="comment_option">
 	 		<h3 class="comment_title">
 	 			댓글 <sup id="count"></sup>	
 	 		</h3>
 	 		<div class="comment_tab">
 	 			<ul class="comment_tab_list">
 	 			</ul>
 	 		</div>
	 	 </div><!-- comment option end -->
	 	 <ul class="comment_list">
	 	 </ul>
	 	 <div class="CommentWriter">
	 	 	<div class="comment_inbox">
	 	 		<b class="comment_inbox_name">${id}</b> <span
	 	 			class="comment_inbox_count">0/200</span>
	 	 		<textarea placeholder="댓글을 남겨보세요" rows="1"
	 	 			class="comment_inbox_text" maxlength="200"></textarea>	
	 	 	</div>
	 	 	<div class="register_box">
	 	 		<div class="button btn_cancel">취소</div>
	 	 		<div class="button btn_register">등록</div>
	 	 	</div>
	 	 </div> <!-- CommentWriter end -->
 	 </div><!-- CommentBox end -->
 	 
 	 <!-- 댓글 삭제 확인 모달창 -->
	<div class="modal hide fade" id="ReplyDelModal">
		<div class="modal-dialog modal-sm modal-dialog-centered">
			<div class="modal-content">
	      
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">문의사항 상세</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	        
				<!-- Modal body -->
				<div class="modal-body">
				  <h3>정말 삭제하시겠습니까??</h3>
				</div>
	        
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
					<button type="button" class="btn btn-info" data-dismiss="modal">취소</button>
				</div>
	        
			</div>
		</div>
	</div>
 	 
 	 <!-- 오류 모달창 -->
	<div class="modal hide fade" id="qnaViewErrorModal">
		<div class="modal-dialog modal-sm modal-dialog-centered">
			<div class="modal-content">
	      
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="qnaViewErrorModal-Title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	        
				<!-- Modal body -->
				<div class="modal-body" id="qnaViewErrorModal-body">
				  
				</div>
	        
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
				</div>
	        
			</div>
		</div>
	</div>
  </div><!-- class="container" -->
  
  <hr>
  <jsp:include page="footer.jsp" />
</body>
</html>