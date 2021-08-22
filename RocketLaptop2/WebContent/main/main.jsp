<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop</title>
		<link href="css/main/main.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="js/main/main.js"></script>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="headernav.jsp" />
		<!--banner-->
		<div class="container">
			<div class="jumbotron">
			</div>     
		</div>

		<!--bestproduct-->
		<div class="container mt-5 mb-5" id="bestProductMain">
			<h3>베스트 상품</h3>
		</div>

		<!--newproduct-->
		<div class="container mb-5" id="newProductMain">
			<h3>새로운 상품</h3>
		</div>
		
		<hr>
		<jsp:include page="footer.jsp" />
		
		<!-- 오류 모달창 -->
		<div class="modal hide fade" id="ErrorModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="ErrorModal-Title"></h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body" id="ErrorModal-body">
					  
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
		        
				</div>
			</div>
		</div>
  </body>
</html>