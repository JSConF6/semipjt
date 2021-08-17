<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - ${category_name}</title>
		<link href="css/main/mainNewProductView.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="headernav.jsp" />
		<div class="container"><h2 class="text-left mt-3">${category_name}</h2></div>
		<div class="container">
			<c:if test="${listcount > 0 }">
				<c:set var="index" value="1" />
				<c:forEach var="c" items="${categoryproductlist}">
					<fmt:formatNumber var="product_price" pattern="###,###,###" value="${c.product_price}" />
					<div class="productItems">
						<span style="font-size:20px;"><c:out value="${index}"/></span>
						<a href="MainProductDetail.ma?product_code=${c.product_code}"><img width="100%" height="100%" src="${'LaptopImgUpload/'}${c.product_image}"></a>
						<div class="text-center" style="font-size : 20px">
							<a href="MainProductDetail.ma?product_code=${c.product_code}"><span>${c.product_name}</span></a><br>
							<span>브랜드 : ${c.category_name}</span><br>
							<span>${product_price } 원</span>
						</div>
					</div>
					<c:set var="index" value="${index + 1}" />
				</c:forEach>
			</c:if>
		</div>
		<c:if test="${listcount == 0}">
			<h1 class="text-center">${category_name} 브랜드 상품이 없습니다.</h1>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>