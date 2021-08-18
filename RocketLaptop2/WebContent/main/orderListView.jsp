<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - 주문 목록</title>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="headernav.jsp" />
		<div class="container text-center">
			<h1 class="mt-3 mb-3">주문 목록</h1>
			<c:if test="${listcount > 0 }">
				<table class="table table-striped table-bordered text-center">
					<thead>
						<tr>
							<th>주문 번호</th>
							<th>수령인</th>
							<th>수령인 연락처</th>
							<th>주소</th>
							<th>주문 가격</th>
							<th>결제 방식</th>
							<th>배송 상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="o" items="${orderlist}">
							<fmt:formatNumber var="order_totalprice" pattern="###,###,###" value="${o.order_totalprice}"/>
							<tr>
								<td><a href="OrderDetailView.ma?order_num=${o.order_num}&user_id=${param.user_id}">${o.order_num}</a></td>
								<td>${o.order_name}</td>
								<td>${o.order_phone}</td>
								<td>(${o.user_address1}) ${o.user_address2} ${o.user_address3}</td>
								<td>${order_totalprice}원</td>
								<td>${o.order_payment}</td>
								<td>${o.order_delivery}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		<c:if test="${listcount == 0}">
			<div class="container text-center"><h1>주문하신 상품이 없습니다.</h1></div>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>