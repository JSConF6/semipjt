<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop - 공지사항</title>
		<link href="css/main/mainNoticeDetailView.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<input type="hidden" value="-1" class="search_field">
		<jsp:include page="headernav.jsp" />
		<div class="container">
			<h2 id="DetailTitle">공지사항(${n.notice_title})</h2>
			<div style="width : 60%; margin : 0 auto;">
				<table class="table table-striped table-bordered">
					<tr>
						<th>제목</th>
						<td colspan="3">${n.notice_title }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${n.notice_date}</td>
						<th>조회수</th>
						<td>${n.notice_readcount }</td>
					</tr>
					<tr style="height : 300px;">
						<th>내용</th>
						<td colspan="3" style="font-size : 20px;"><textarea readOnly>${n.notice_content }</textarea></td>
					</tr>
				</table>
				
				<!-- 버튼 -->
				<div class="text-right">
					<a class="btn btn-info" href="MainNoticeList.ma">목록</a>
				</div>
			</div>
		</div> <!-- .container end -->
		<hr>
		<jsp:include page="footer.jsp" />
	</body>
</html>