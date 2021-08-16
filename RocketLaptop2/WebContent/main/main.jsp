<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop</title>
		<script type="text/javascript" src="js/main/main.js"></script>
		<style>
			.carousel-inner img {
		   		width: 100%;
		   		height: 100%;
		  	}
		</style>
	</head>
	<body>
		<jsp:include page="headernav.jsp" />

		<!--banner-->
		<div class="container" style="height:400px">
			<div id="demo" class="carousel slide" data-ride="carousel">
				<ul class="carousel-indicators">
					<li data-target="#demo" data-slide-to="0" class="active"></li>
					<li data-target="#demo" data-slide-to="1"></li>
					<li data-target="#demo" data-slide-to="2"></li>
				</ul>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="image/banner.png" alt="Los Angeles" width="1100" height="500">
						<div class="carousel-caption">
							<h3>Los Angeles</h3>
							<p>We had such a great time in LA!</p>
						</div>   
					</div>
					<div class="carousel-item">
						<img src="image/banner.png" alt="Chicago" width="1100" height="500">
						<div class="carousel-caption">
							<h3>Chicago</h3>
							<p>Thank you, Chicago!</p>
						</div>   
					</div>
					<div class="carousel-item">
						<img src="image/banner.png" alt="New York" width="1100" height="500">
						<div class="carousel-caption">
							<h3>New York</h3>
							<p>We love the Big Apple!</p>
						</div>   
					</div>
				</div>
				<a class="carousel-control-prev" href="#demo" data-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</a>
				<a class="carousel-control-next" href="#demo" data-slide="next">
					<span class="carousel-control-next-icon"></span>
				</a>
			</div>
		</div>

		<!--bestproduct-->
		<div class="container mt-5 mb-5" id="bestProductMain">
			<h3>베스트 상품</h3>
			<div class="row row-cols-1 row-cols-md-3 g-4" id="bestProduct">
				<div class="col">
					<div class="card h-100">
						<img src="image/account_user.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>	
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/among us.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/android_robot.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--newproduct-->
		<div class="container mb-5" id="newProductMain">
			<h3>새로운 상품</h3>
			<div class="row row-cols-1 row-cols-md-3 g-4" id="newProduct">
				<div class="col">
					<div class="card h-100">
						<img src="image/heart.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/social.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/user.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
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