<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - ${search_word} 검색결과</title>
		<link href="css/main/mainSearchView.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="headernav.jsp" />
		<h2 class="text-center mt-3">${search_word} 검색결과</h2>
		<div class="container">
			<c:if test="${listcount > 0 }">
				<c:set var="index" value="1" />
				<c:forEach var="s" items="${searchlist}">
					<fmt:formatNumber var="product_price" pattern="###,###,###" value="${s.product_price}" />
					<div class="productItems">
						<span style="font-size:20px;"><c:out value="${index}"/></span>
						<a href="MainProductDetail.ma?product_code=${s.product_code}"><img width="100%" height="100%" src="${'LaptopImgUpload/'}${s.product_image}"></a>
						<div class="text-center" style="font-size : 20px">
							<a href="MainProductDetail.ma?product_code=${s.product_code}"><span>${s.product_name}</span></a><br>
							<span>상품 상태  : ${s.product_status}</span><br>
							<span>${product_price } 원</span>
						</div>
					</div>
					<c:set var="index" value="${index + 1}" />
				</c:forEach>
			</c:if>
		</div>
		<c:if test="${listcount == 0 && !empty search_word}">
			<h1 class="text-center">${search_word} 검색결과가 없습니다.</h1>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>