<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<title>RocketLaptop - ${p.product_name}</title>
		<link href="css/main/mainProductDetailView.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="../main/headernav.jsp" />
		<h1 class="text-center mt-3">상품 상세보기</h1>
		<div id="showImage"><img src="${'LaptopImgUpload/'}${p.product_image}" width="100%" height="100%"></div>
		<div class="container" id="productDetail">
			<hr>
			<p><span>상품 이름</span> &nbsp;${p.product_name }</p>
			<hr>
			<p><span>상품 코드</span> &nbsp;${p.product_code }</p>
			<hr>
			<p><span>제조사</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${p.category_name }</p>
			<hr>
			<p><span>가격</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber pattern="###,###,###" value="${p.product_price}" /> 원</p>
			<hr>
			<p><span>재고 수</span> &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber pattern="###,###,###" value="${p.product_stock}" /> EA</p>
			<hr>
			<p class="text-center"><span>상품 설명</span><br>${p.product_details}</p>
			<hr>
			<div class="container text-right">
				<button class="btn btn-dark ">바로 구매</button>
				<button class="btn btn-secondary">장바구니 담기</button>
			</div>
		</div>
		<hr>
		<jsp:include page="../main/footer.jsp" />
	</body>
</html>