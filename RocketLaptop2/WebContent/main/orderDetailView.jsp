<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - 주문 상세</title>
		<style>
			hr, footer{
				clear : both;
			}
			
			.product{
				width : 48%;
				height:200px;
				border : 1px dotted gray;
			}
			
			.product > p{
				font-size : 30px;
				text-align : left;
				margin : 0;
			}
			
			.product > p > span{
				font-size : 25px;
			}
		</style>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="headernav.jsp" />
		<fmt:formatNumber var="order_totalprice" pattern="###,###,###" value="${o.order_totalprice}"/>
		<div class="container text-center">
			<h1 class="mt-3 mb-3">주문 상세</h1>
			<table class="table table-striped table-bordered text-center mb-5">
				<thead>
					<tr>
						<th>주문 번호</th>
						<th>수령인</th>
						<th>수령인 연락처</th>
						<th>주소</th>
						<th>주문 가격</th>
						<th>걸제방식</th>
						<th>배송상태</th>
						<th>결제일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${o.order_num}</td>
						<td>${o.order_name}</td>
						<td>${o.order_phone}</td>
						<td>(${o.user_address1}) ${o.user_address2} ${o.user_address3}</td>
						<td>${order_totalprice}원</td>
						<td>${o.order_payment}</td>
						<td>${o.order_delivery}</td>
						<td>${o.order_date}</td>
					</tr>
				</tbody>
			</table>
			<c:set var="index" value="1" />
			<c:forEach var="ol" items="${orderlist}">
				<fmt:formatNumber var="product_price" pattern="###,###,###" value="${ol.product_price}"/>
				<fmt:formatNumber var="product_totalprice" pattern="###,###,###" value="${ol.product_price * ol.cart_stock}"/>
				<c:if test="${index % 2 != 0}">
					<div class="float-left mb-5 product">
						<img class="float-left" src="${'LaptopImgUpload/'}${ol.product_image}" width="200px" height="197px">
						<p>상품명&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a href="MainProductDetail.ma?product_code=${ol.product_code}">${ol.product_name}</a></span></p>
						<p>가격&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${product_price}원</span></p>
						<p>구입 수량&nbsp;&nbsp;<span>${ol.cart_stock}개</span></p>
						<p>최종 가격&nbsp;&nbsp;<span>${product_totalprice}원</span></p>
					</div>
				</c:if>
				<c:if test="${index % 2 == 0}">
					<div class="float-right mb-5 product">
						<img class="float-left" src="${'LaptopImgUpload/'}${ol.product_image}" width="200px" height="197px">
						<p>상품명&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a href="MainProductDetail.ma?product_code=${ol.product_code}">${ol.product_name}</a></span></p>
						<p>가격&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${product_price}원</span></p>
						<p>구입 수량&nbsp;&nbsp;<span>${ol.cart_stock}개</span></p>
						<p>최종 가격&nbsp;&nbsp;<span>${product_totalprice}원</span></p>
					</div>
				</c:if>
				<c:set var="index" value="${index + 1}"/>
			</c:forEach>
		</div>
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>