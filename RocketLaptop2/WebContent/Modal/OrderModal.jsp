<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<title>${maintitle}</title>
		<script>
			$(function(){
				$("#OrderModal").modal('show');
			});
		</script>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="../main/headernav.jsp" />
		<div class="modal hide fade" id="OrderModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">${title}</h4>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body ">
		          		<h3>${body}</h3>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<a type="button" class="btn btn-primary" onClick="javascript:location.replace('${path}');">확인</a>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../main/footer.jsp" />
	</body>
</html>