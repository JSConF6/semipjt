<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<title>문의사항</title>
		<script>
			$(function(){
				$("#QnaDeleteModal").modal('show');
			});
		</script>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="../main/headernav.jsp" />
		<div class="modal hide fade" id="QnaDeleteModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">문의사항</h4>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body">
		          		<h3>삭제 되었습니다.</h3>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<a type="button" class="btn btn-primary" href="QnaList.ma">확인</a>
					</div>
		        
				</div>
			</div>
		</div>
		<jsp:include page="../main/footer.jsp" />
	</body>
</html>