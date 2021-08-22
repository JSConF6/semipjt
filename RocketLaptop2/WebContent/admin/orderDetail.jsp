<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<jsp:include page="AdminNav.jsp" />
		<title>RocketLaptop - 주문 상세</title>
		<link href="css/admin/adminNav.css" type="text/css" rel="stylesheet">
		<script src="js/order/orderDetail.js" type="text/javascript"></script>
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
				font-size : 20px;
			}
			
			.table{
				width : 102%;
			}
		</style>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<fmt:formatNumber var="order_totalprice" pattern="###,###,###" value="${o.order_totalprice}"/>
		<div class="container text-center">
			<h1 class="mt-3 mb-3">주문 상세</h1>
			<table class="table table-striped table-bordered text-center mb-3">
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
			<div class="text-right mb-3">
				<input type="hidden" value="${o.order_num}" class="order_num">
				<input type="hidden" value="${o.user_id}" class="order_user_id">
				<button type="button" class="btn btn-danger deliverybtn1">배송중</button>
				<button type="button" class="btn btn-success deliverybtn2">배송완료</button>
				<a type="button" class="btn btn-primary" href="OrderList.ad">목록</a>
			</div>
			<c:set var="index" value="1" />
			<c:forEach var="ol" items="${orderlist}">
				<fmt:formatNumber var="product_price" pattern="###,###,###" value="${ol.product_price}"/>
				<fmt:formatNumber var="product_totalprice" pattern="###,###,###" value="${ol.product_price * ol.order_de_count}"/>
				<c:if test="${index % 2 != 0}">
					<div class="float-left mb-5 product">
						<img class="float-left" src="${'LaptopImgUpload/'}${ol.product_image}" width="200px" height="197px">
						<p>상품명&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${ol.product_name}</span></p>
						<p>가격&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${product_price}원</span></p>
						<p>구입 수량&nbsp;&nbsp;<span>${ol.order_de_count}개</span></p>
						<p>최종 가격&nbsp;&nbsp;<span>${product_totalprice}원</span></p>
					</div>
				</c:if>
				<c:if test="${index % 2 == 0}">
					<div class="float-right mb-5 product">
						<img class="float-left" src="${'LaptopImgUpload/'}${ol.product_image}" width="200px" height="197px">
						<p>상품명&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${ol.product_name}</span></p>
						<p>가격&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${product_price}원</span></p>
						<p>구입 수량&nbsp;&nbsp;<span>${ol.order_de_count}개</span></p>
						<p>최종 가격&nbsp;&nbsp;<span>${product_totalprice}원</span></p>
					</div>
				</c:if>
				<c:set var="index" value="${index + 1}"/>
			</c:forEach>
			
			<!-- 오류 모달창 -->
			<div class="modal hide fade" id="DeliveryStatusErrorModal">
				<div class="modal-dialog modal-sm modal-dialog-centered">
					<div class="modal-content">
			      
						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">배송상태 변경 실패</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
			        
						<!-- Modal body -->
						<div class="modal-body">
						  <h2>배송 상태 변경실패</h2>
						</div>
			        
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
			        
					</div>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="../main/footer.jsp" />
	</body>
</html>