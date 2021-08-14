<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop</title>
		<style>
			#title{
				font-size: 30px;
				margin-top: 0px;
			}
		
			#search_input{
				width: 700px;
				border-radius: .25rem;
			}
		
			.fonticon{
				margin-left: 20px;
			}
			
			select{
				color : #495057;
				background-color : #fff;
				background-clip : padding-box;
				border : 1px solid #ced4da;
				border-radius : .25rem;
				transition : border-color .15s ease-in-out, box-shadow .15s ease-in-out;
				outline : none;
				margin-right: 6px;
			}
		
			#category{
				font-size: 25px;
			}
		
			#category > li{
				margin-right: 50px;
			}
			
			.carousel-inner img {
		   		width: 100%;
		   		height: 100%;
		  	}
		
			footer{
				height: 100px;
				background-color: gray;
			}

			.fa-user{
				padding-left: 20px;
			}

			#nav-search{
				height:  70px;
			}
		</style>
	</head>
	<body>
		<%
		// 메인 페이지로 이동했을 때 세션에 값이 담겨있는지 체크
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		int pageNumber = 1; //기본은 1 페이지를 할당
		// 만약 파라미터로 넘어온 오브젝트 타입 'pageNumber'가 존재한다면
		// 'int'타입으로 캐스팅을 해주고 그 값을 'pageNumber'변수에 저장한다
		if(request.getParameter("pageNumber") != null){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
	%>
		<nav class="navbar navbar-expand-sm bg-secondary navbar-dark pt-3 justify-content-center" id="nav-search">
			<a class="navbar-brand" id="title" href="#">RocketLaptop</a>
			<form class="form-inline" action="#">
				<div class="input-group">
					<select id="viewcount" name="search_field">
						<option value="0" selected>상품명</option>
						<option value="1">브랜드명</option>
					</select>
					<input class="form-control mr-sm-2" id="search_input" type="text" placeholder="상품명을 입력하세요">
					<button class="btn btn-success" type="submit">검색</button>
				</div>
			</form>
			<ul class="navbar-nav">
				<c:if test="${id == 'admin'}">
					<li class="nav-item">
						<a class="nav-link fonticon" href="#"><i class="fas fa-user fa-2x"></i><br>관리자 페이지</a>
					</li>
				</c:if>
				<c:if test="${id != 'admin' || empty id}">
					<li class="nav-item">
						<a class="nav-link fonticon" href="#"><i class="fas fa-user fa-2x"></i><br>마이 페이지</a>
					</li>
					<li class="nav-item">
						<a class="nav-link fontiocn" href="#"><i class="fas fa-shopping-cart fa-2x"></i><br>장바구니</a>
					</li>
				</c:if>
			</ul>
		</nav>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-center">
			<ul class="navbar-nav" id="category">
				<li class="nav-item dropdown">
				 	<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						<i class="fas fa-align-justify fa-1x"></i> 카테고리
				  	</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">브랜드명</a>
						<a class="dropdown-item" href="#">브랜드명</a>
						<a class="dropdown-item" href="#">브랜드명</a>
				  	</div>
				</li>
				
			  	<li class="nav-item">
					<a class="nav-link" href="#">새로운 상품</a>
			  	</li>
			  	<li class="nav-item">
					<a class="nav-link" href="#">베스트 상품</a>
			 	 </li>
			  	<li class="nav-item">
					<a class="nav-link" href="#">공지사항</a>
			 	 </li>
			  	<li class="nav-item">
					<a class="nav-link" href="bbs.jsp">문의사항</a>
			 	</li>
			</ul>
		</nav>
			<!-- 게시판 글쓰기 양식 영역 시작 -->
	<div class="container">
		<div class="row">
			<form method="post" action="writeAction.jsp">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px;"></textarea></td>
						</tr>
					</tbody>
				</table>
				<!-- 글쓰기 버튼 생성 -->
				<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
			</form>
		</div>
	</div>
	<!-- 게시판 글쓰기 양식 영역 끝 -->


		<jsp:include page="footer.jsp" />
	</body>
</html>