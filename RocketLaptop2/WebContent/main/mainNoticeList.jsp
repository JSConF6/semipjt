<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - 공지사항</title>
		<script type="text/javascript" src="js/main/mainNoticeList.js"></script>
		<link href="css/main/mainNoticeList.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="headernav.jsp" />
		<div class="container"><h1 id="MainNoticeTitle"><a href="MainNoticeList.ma">공지사항</a></h1></div>
		<div class="container">
			<h2>글 개수 : ${listcount }</h2>
			<c:if test="${listcount > 0}">
				<table class="table table-hover table-striped table-bordered text-center">
				    <thead>
				        <tr>
				            <th class="align-middle">번호</th>
				            <th class="align-middle">제목</th>
				            <th class="align-middle">작성일</th>
				            <th class="align-middle">조회수</th>
				        </tr>
				    </thead>
				    <tbody>
				    	<c:set var="num" value="${listcount-(page-1)*limit }" />
				    	<c:forEach var="n" items="${noticelist }">
				    		<tr>
				    			<td>
				    				<c:out value="${num }" />
				    				<c:set var="num" value="${num-1 }"/>
				    			</td>
				    			<td>
				    				<a href="MainNoticeDetail.ma?num=${n.notice_num}">
				 						<c:out value="${n.notice_title}" />  
									</a>
								</td>
				    			<td>${n.notice_date}</td>
				    			<td>${n.notice_readcount}</td>
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
							<a href="MainNoticeList.ma?page=${page-1}&search_field=${search_field}&search_word=${search_word}" class="page-link">이전&nbsp;</a>
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
					   			<a href="MainNoticeList.ma?page=${a}&search_field=${search_field}&search_word=${search_word}" 
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
							<a href="MainNoticeList.ma?page=${page+1}&search_field=${search_field}&search_word=${search_word}" 
				   				class="page-link">&nbsp;다음</a>
			  			</li>	
					</c:if>
				</ul>
			</c:if>
		</div>
		<c:if test="${listcount == 0}">
			<h1 class="MainNoticeBody">공지사항이 없습니다.</h1>
		</c:if>
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>