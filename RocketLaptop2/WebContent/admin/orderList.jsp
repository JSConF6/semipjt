<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="../main/header.jsp" />
		<jsp:include page="AdminNav.jsp" />
		<title>관리자 페이지 - 주문 목록</title>
		<link href="css/admin/adminNav.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="js/order/orderList.js"></script>
		<link href="css/order/orderList.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="container text-center OrderMain">
			<c:if test="${user_id == 'admin' }">
				<h1 class="OrderTitle"><a href="OrderList.ad">주문 목록</a></h1>
				<form action="OrderList.ad" method="post">
				    <div class="input-group">
				        <select id="orderinfo" name="search_field">
				            <option value="0" selected>주문번호</option>
				            <option value="1">수령인</option>
				            <option value="2">걸제방식</option>
				        </select>
				        <input name="search_word" type="text" class="form-control"
				               placeholder="주문번호 입력하세요" value="${search_word}" id="search_word">
				        <button id="ordersearch" class="btn btn-primary" type="submit">검색</button>
				    </div>
				</form>
				<input type="hidden" value="${search_field }" class="search_field">
				<h2>주문건수 : ${listcount}</h2>
				<div class="float-right">
					<select class="form-control" id="order_delivery">
						<option value="0" selected>배송준비</option>
						<option value="1">배송중</option>
						<option value="2">배송완료</option>
					</select>
				</div>
				<c:if test="${listcount > 0 }">
					<table class="table table-striped table-bordered text-center OrderTable">
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
									<td><a href="OrderDetail.ad?order_num=${o.order_num}&user_id=${o.user_id}">${o.order_num}</a></td>
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
					
					<ul class="pagination justify-content-center">
						<c:if test="${page <= 1 }">
							<li class="page-item">
								<a class="page-link gray">이전&nbsp;</a>
							</li>
						</c:if>
						<c:if test="${page > 1 }">
							<li class="page-item">
								<a href="OrderList.ad?page=${page-1}&search_field=${search_field}&search_word=${search_word}" class="page-link">이전&nbsp;</a>
							</li>
						</c:if>
						
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page }">
								<li class="page-item " >
						   			<a class="page-link gray">${a}</a>
								</li>
							</c:if>
							<c:if test="${a != page }">
					    		<li class="page-item">
						   			<a href="OrderList.ad?page=${a}&search_field=${search_field}&search_word=${search_word}" 
						      		class="page-link">${a}</a>
					    		</li>	
							</c:if>
						</c:forEach>
						
					    <c:if test="${page >= maxpage }">
							<li class="page-item">
					   			<a class="page-link gray">&nbsp;다음</a> 
							</li>
						</c:if>
						<c:if test="${page < maxpage }">
								<li class="page-item">
								<a href="OrderList.ad?page=${page+1}&search_field=${search_field}&search_word=${search_word}"
					   				class="page-link">&nbsp;다음</a>
								</li>	
						</c:if>
					</ul>
				</c:if>
			</c:if>
			
			<c:if test="${listcount == 0 && empty search_word}">
				<h1 class="OrderBody">주문목록이 없습니다.</h1>
			</c:if>
					
			<c:if test="${listcount == 0 && !empty search_word}">
				<h1 class="OrderBody">검색결과가 없습니다.</h1>
			</c:if>
			
			<!-- 오류 모달창 -->
			<div class="modal hide fade" id="OrderErrorModal">
				<div class="modal-dialog modal-sm modal-dialog-centered">
					<div class="modal-content">
			      
						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title" id="OrderErrorModal-Title"></h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
			        
						<!-- Modal body -->
						<div class="modal-body" id="OrderErrorModal-body">
			          
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