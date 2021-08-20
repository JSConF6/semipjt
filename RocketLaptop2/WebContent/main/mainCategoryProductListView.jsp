<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - ${category_name}</title>
		<link href="css/main/mainCategoryProductView.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="headernav.jsp" />
		<div class="container"><h2 class="text-left mt-3">${category_name}</h2></div>
		<div class="container">
			<c:if test="${listcount > 0 }">
				<c:forEach var="c" items="${categoryproductlist}">
					<fmt:formatNumber var="product_price" pattern="###,###,###" value="${c.product_price}" />
					<div class="productItems">
						<span style="font-size:20px;"><c:out value="${index}"/></span>
						<a href="MainProductDetail.ma?product_code=${c.product_code}"><img width="100%" height="100%" src="${'LaptopImgUpload/'}${c.product_image}"></a>
						<div style="font-size : 20px">
							<p class="text-center"><a href="MainProductDetail.ma?product_code=${c.product_code}"><span>${c.product_name}</span></a></p>
							<p><span>상품 브랜드&nbsp;&nbsp;:&nbsp;&nbsp;${c.category_name}</span></p>
							<p><span>노트북 가격&nbsp;&nbsp;:&nbsp;&nbsp;${product_price }원</span></p>
							<p><span>노트북 상태&nbsp;&nbsp;:&nbsp;&nbsp;${c.product_status}</span></p>
							<p><span>상품 판매량&nbsp;&nbsp;:&nbsp;&nbsp;${c.product_sales}개</span></p>
							<p><span>상품 등록일&nbsp;&nbsp;:&nbsp;&nbsp;${c.product_date}</span></p>
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
								<a href="MainCategoryProductList.ma?page=${page-1}&search_field=${search_field}&category_name=${category_name}" class="page-link">이전&nbsp;</a>
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
						   			<a href="MainCategoryProductList.ma?page=${a}&search_field=${search_field}&category_name=${category_name}" 
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
								<a href="MainCategoryProductList.ma?page=${page+1}&search_field=${search_field}&category_name=${category_name}" 
					   				class="page-link">&nbsp;다음</a>
								</li>	
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
		<c:if test="${listcount == 0}">
			<h1 class="text-center">${category_name} 브랜드 상품이 없습니다.</h1>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>