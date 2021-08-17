<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp"/>
		<title>RocketLaptop - 장바구니</title>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="headernav.jsp"/>
		<div class="container mt-4"><h1>장바구니</h1></div>
		<div class="container" style="margin-bottom : 50px;">
			<c:if test="${listcount > 0}">
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th class="align-middle">모두 선택<br><input type="checkbox"></th>
							<th class="align-middle">상품 이미지</th>
							<th class="align-middle">상품명</th>
							<th class="align-middle">가격</th>
							<th class="align-middle">구입 수량</th>
							<th class="align-middle">최종 가격</th>
							<th class="align-middle">삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${cartlist}">
							<c:set var="src" value="${'LaptopImgUpload/'}${c.product_image}"/>
							<tr>
								<td><input type="checkbox"></td>
								<td><img src="${src}" width="80px" height="80px"></td>
								<td>${c.product_name}</td>
								<td><fmt:formatNumber pattern="###,###,###" value="${c.product_price}"/></td>
								<td>
									<input class="text-center" type="number" value="${c.cart_stock}" readOnly style="width:40px;">
								</td>
								<td><fmt:formatNumber pattern="###,###,###" value="${c.product_price * c.cart_stock}"/></td>
								<td><button class="btn btn-danger">삭제</button></td>
							</tr>
							<c:set var="pricesum" value="${pricesum + (c.product_price * c.cart_stock)}"/>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-right"><button class="btn btn-danger float-left">선택 삭제</button>
				<span style="font-size : 30px;">주문 가격 : <fmt:formatNumber pattern="###,###,###" value="${pricesum}"/></span></div>
				<div class="text-right"><button class="btn btn-primary" style="font-size: 20px; width: 100px;">주문</button></div>
			</c:if>
		</div>
		<c:if test="${listcount == 0 }">
			<div class="container text-center"><h1>장바구니에 담긴 상품이 없습니다.</h1></div>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp"/>
	</body>
</html>