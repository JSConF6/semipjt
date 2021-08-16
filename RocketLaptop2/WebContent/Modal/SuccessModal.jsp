<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<title>${maintitle} 확인 페이지</title>
		<script>
			$(function(){
				$("#SuccessModal").modal('show');
				$('#SuccessModal-Title').text('${title}');
				$('#SuccessModal-body').html('<h4>${body}</h4>');
			});
		</script>
	</head>
	<body>
		<div class="modal hide fade" id="SuccessModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="SuccessModal-Title"></h4>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body" id="SuccessModal-body">
		          
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<a type="button" class="btn btn-primary" href="${path}">확인</a>
					</div>
		        
				</div>
			</div>
		</div>
	</body>
</html>