<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<title>장바구니</title>
		<script>
			$(function(){
				$("#CartModal").modal('show');
			});
		</script>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="../main/headernav.jsp" />
		<div class="modal hide fade" id="CartModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">장바구니</h4>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body">
		          		<h5>로그인 후 장바구니를 이용해주세요.</h5>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<a type="button" class="btn btn-primary" href="main.ma">확인</a>
					</div>
		        
				</div>
			</div>
		</div>
		<jsp:include page="../main/footer.jsp" />
	</body>
</html>