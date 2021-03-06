<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - 베스트 상품</title>
		<link href="css/main/mainBestProductView.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="headernav.jsp" />
		<div class="container"><h2 class="text-left mt-3">RocketLaptop 베스트 상품</h2></div>
		<div class="container">
			<c:if test="${listcount > 0 }">
				<c:forEach var="b" items="${bestlist}">
					<fmt:formatNumber var="product_price" pattern="###,###,###" value="${b.product_price}" />
					<div class="productItems">
						<span style="font-size:20px;"><c:out value="${index}"/></span>
						<a href="MainProductDetail.ma?product_code=${b.product_code}"><img width="100%" height="100%" src="${'LaptopImgUpload/'}${b.product_image}"></a>
						<div style="font-size : 20px">
							<p class="text-center"><a href="MainProductDetail.ma?product_code=${b.product_code}"><span>${b.product_name}</span></a></p>
							<p><span>상품 브랜드&nbsp;&nbsp;:&nbsp;&nbsp;${b.category_name }</span></p>
							<p><span>노트북 가격&nbsp;&nbsp;:&nbsp;&nbsp;${product_price } 원</span></p>
							<p><span>노트북 상태&nbsp;&nbsp;:&nbsp;&nbsp;${b.product_status}</span></p>
							<p><span>상품 판매량&nbsp;&nbsp;:&nbsp;&nbsp;${b.product_sales}개</span></p>
							<p><span>상품 등록일&nbsp;&nbsp;:&nbsp;&nbsp;${b.product_date }</span></p>
						</div>
					</div>
				</c:forEach>
				
				<div class="container justify-content-center">
					<ul class="pagination">
						<c:if test="${page <= 1 }">
							<li class="page-item">
								<a class="page-link gray">이전&nbsp;</a>
							</li>
						</c:if>
						<c:if test="${page > 1 }">
							<li class="page-item">
								<a href="MainBestProductList.ma?page=${page-1}&search_field=${search_field}" class="page-link">이전&nbsp;</a>
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
						   			<a href="MainBestProductList.ma?page=${a}&search_field=${search_field}" 
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
								<a href="MainBestProductList.ma?page=${page+1}&search_field=${search_field}" 
					   				class="page-link">&nbsp;다음</a>
								</li>	
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
		<c:if test="${listcount == 0}">
			<h1 class="text-center">베스트 상품이 없습니다.</h1>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>