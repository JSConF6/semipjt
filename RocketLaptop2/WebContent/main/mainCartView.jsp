<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp"/>
		<title>RocketLaptop - 장바구니</title>
		<script src="js/cart/mainCartView.js" type="text/javascript"></script>
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<input type="hidden" value="${user_id}" class="checkUser_id">
		<jsp:include page="headernav.jsp"/>
		<div class="container mt-4 text-center"><h1>장바구니</h1></div>
		<div class="container" style="margin-bottom : 50px;">
			<c:if test="${listcount > 0}">
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th class="align-middle">모두 선택<br><input name="allCheck" type="checkbox"></th>
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
								<td class="align-middle"><input name="RowCheck" type="checkbox" value="${c.product_price * c.order_de_count}" data-cartNum="${c.cart_num}"></td>
								<td class="align-middle"><img src="${src}" width="80px" height="80px"></td>
								<td class="align-middle"><a href="MainProductDetail.ma?product_code=${c.product_code}">${c.product_name}</a></td>
								<td class="align-middle"><fmt:formatNumber pattern="###,###,###" value="${c.product_price}"/>원</td>
								<td class="align-middle">
									<input class="text-center" type="number" value="${c.order_de_count}" readOnly style="width:40px;">
								</td>
								<td class="align-middle"><fmt:formatNumber pattern="###,###,###" value="${c.product_price * c.order_de_count}"/>원</td>
								<td class="align-middle"><button class="btn btn-danger cartdelbtn" value="${c.cart_num}">삭제</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-right"><button class="btn btn-danger float-left" id="selectionDelete">선택 삭제</button>
				<span style="font-size : 30px;" class="orderPrice">주문 가격 : 0원</span></div>
				<div class="text-right"><button class="btn btn-primary orderbtn" style="font-size: 20px; width: 100px;">주문</button></div>
			</c:if>
		</div>
		
		<!-- 장바구니 삭제 모달창 -->
		<div class="modal hide fade" id="CartDeleteModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">장바구니</h4>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body">
		          		<h5>선택하신 상품을 삭제하시겠습니까?</h5>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" id="cartmodaldelbtn">삭제</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
					</div>
		        
				</div>
			</div>
		</div>
		
		<!-- 주문 정보 입력 모달창 -->
		<div class="modal fade" id="OrderInfoModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
		      
			        <!-- Modal Header -->
					<div class="modal-header text-center">
						<h2 class="modal-title">주문 정보 입력</h2>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
			        <!-- Modal body -->
			        <div class="modal-body">
		          		<form action="OrderAction.ma" id="OrderForm" method="post" name="OrderFrom">
		          			<input type="hidden" value="${user_id}" name="user_id">
		          			<input type="hidden" value="" name="pricesum">
		          			<input type="hidden" value="" name="cartNumArr">
		          			
							<!-- 수령인 -->
							<div class="form-group text-left">
				      			<label for="order_name">수령인</label>
				      			<input type="text" class="form-control" id="order_name" name="order_name" placeholder="수령인 입력">
				    		</div>
				    			
				    		<!-- 수령인 연락처 -->
				    		<div class="form-group text-left">
				      			<label for="order_phone">수령인 연락처</label>
				      			<input type="text" class="form-control" id="order_phone" name="order_phone" placeholder="ex) 010-XXXX-XXXX">
				    		</div>
				    		
				    		<!-- 우편번호 -->
				    		<div class="form-group text-left">
				      			<label for="order_address1">우편번호</label>
				      			<div class="input-group">
				      				<input type="text" class="form-control" id="order_address1" name="order_address1" readOnly>
				      				<input type="button" class="btn btn-info" id="postsearchbtn" value="주소 검색">
				      			</div>
				    		</div>
				    		
				    		<!-- 주소 -->
				    		<div class="form-group text-left">
				      			<label for="order_address2">주소</label>
				      			<input type="text" class="form-control" id="order_address2" name="order_address2" readOnly>
				    		</div>
				    		
				    		<!-- 상세 주소 -->
				    		<div class="form-group text-left">
				      			<label for="order_address3">상세 주소</label>
				      			<input type="text" class="form-control" id="order_address3" name="order_address3" placeholder="상세주소 입력">
				    		</div>
				    		
				    		<!-- 결제 방식 -->
				    		<div class="form-group text-center">
				      			<div><h4>결제 방식</h4></div>
								<div class="form-check-inline">
									<label class="form-check-label" for="creditcardPayment">
										<input type="radio" class="form-check-input" id="creditcardPayment" name="order_payment" value="카드결제">카드결제
									</label>
								</div>
							    <div class="form-check-inline">
									<label class="form-check-label" for="phonePayment">
										<input type="radio" class="form-check-input" id="phonePayment" name="order_payment" value="휴대전화">휴대전화
									</label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label" for="accountPayment">
										<input type="radio" class="form-check-input" id="accountPayment" name="order_payment" value="계좌이체">계좌이체
									</label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label" for="samsungpayPayment">
										<input type="radio" class="form-check-input" id="samsungpayPayment" name="order_payment" value="삼성페이">삼성페이
									</label>
								</div>
				    		</div>
				    		
				    		<!-- 총 결제 금액 -->
				    		<div class="form-group text-left">
				      			<p style="font-size : 30px;" class="text-center">총 결제 금액 : <span style="font-size : 30px;" class="total_price"></span></p>
				    		</div>
						   
							<!-- 버튼 -->
							<div class="text-right">
								<button type="button" class="btn btn-primary" id="orderFrombtn">주문</button>
								<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 장바구니 선택삭제 에러 모달창 -->
		<div class="modal hide fade" id="CartSelectionDeleteFailModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">장바구니</h4>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body">
		          		<h3>장바구니 삭제 실패</h3>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
					</div>
		        
				</div>
			</div>
		</div>
		
		<!-- 주문 정보 입력 오류 모달창 -->
		<div class="modal hide fade" id="OrderInfoErrorModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title" id="OrderInfoErrorModal-Title"></h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body" id="OrderInfoErrorModal-body">
					  
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
		        
				</div>
			</div>
		</div>
		<c:if test="${listcount == 0 }">
			<div class="container text-center"><h1>장바구니에 담긴 상품이 없습니다.</h1></div>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp"/>
	</body>
</html>