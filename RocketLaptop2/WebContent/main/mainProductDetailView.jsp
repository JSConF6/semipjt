<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - ${p.product_name}</title>
		<link href="css/main/mainProductDetailView.css" type="text/css" rel="stylesheet">
		<script src="js/main/mainProductDetailView.js" type="text/javascript"></script>
	</head>
	<body>
		<input type="hidden" value="${user_id}" class="checkid">
		<input type="hidden" value="${p.product_code}" class="product_code">
		<input type="hidden" value="${p.product_stock }" class="product_stock">
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="headernav.jsp" />
		<h1 class="text-center mt-3">상품 정보</h1>
		<div id="showImage"><img src="${'LaptopImgUpload/'}${p.product_image}" width="100%" height="100%"></div>
		<div class="container" id="productDetail">
			<hr>
			<p><span>상품 이름</span> &nbsp;&nbsp;${p.product_name }</p>
			<hr>
			<p><span>상품 코드</span> &nbsp;&nbsp;${p.product_code }</p>
			<hr>
			<p><span>제조사</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${p.category_name }</p>
			<hr>
			<p><span>가격</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber pattern="###,###,###" value="${p.product_price}" /> 원</p>
			<hr>
			<p><span>재고 수</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber pattern="###,###,###" value="${p.product_stock}" /> EA</p>
			<hr>
			<p><span>상품 상태</span> &nbsp;&nbsp;${p.product_status}</p>
			<hr>
			<p>
				<span>주문 수량</span>&nbsp;
				<button type="button" class="plus">+</button>
				<input type="number" class="stock" min="1" max="${p.product_stock}" value="1" readOnly>
				<button type="button" class="minus">-</button>
			</p>
			<hr>
			<p class="text-center"><span>상품 설명</span><br>${p.product_details}</p>
			<hr>
			<div class="container text-center">
				<button class="btn btn-info" id="cartbtn">장바구니 담기</button>
			</div>
		</div>
		<hr>
		<jsp:include page="footer.jsp" />
		
		<!-- 주문 오류 모달창 -->
		<div class="modal hide fade" id="productOrderModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">상품 주문</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body">
					  <h4>로그인 후 구매 해주십시오.</h4>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
		        
				</div>
			</div>
		</div>
		
		<!-- 장바구니 담기 오류 모달창 -->
		<div class="modal hide fade" id="CartErrorModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">장바구니 담기</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body">
					  <h4>로그인 후 담아주세요.</h4>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
		        
				</div>
			</div>
		</div>
		
		<!-- 장바구니 담기 확인 모달창 -->
		<div class="modal hide fade" id="CartAddConfirmModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">장바구니 담기</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body CartAddConfirmModal_body">
						
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<a type="button" class="btn btn-primary" href="javascript:location.href='MainCartView.ma?user_id=${user_id}';">장바구니로 </a>
						<button type="button" class="btn btn-info" data-dismiss="modal">계속 쇼핑</button>
					</div>
		        
				</div>
			</div>
		</div>
		
		<!-- 장바구니 모달창 -->
		<div class="modal hide fade" id="CartModal">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
		      
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">장바구니 담기</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
		        
					<!-- Modal body -->
					<div class="modal-body">
					  <span style="font-size : 19px;">상품을 장바구니에 추가하시겠습니까?</span>
					</div>
		        
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="addbtn">추가</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
					</div>
		        
				</div>
			</div>
		</div>
	</body>
</html>