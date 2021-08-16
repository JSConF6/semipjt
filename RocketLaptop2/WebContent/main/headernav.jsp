<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	#title{
		font-size: 30px;
		margin-top: 4px;
		margin-bottom: 8px;
	}
		
	#search_word{
		width: 500px;
		border-radius: .25rem;
		border : 1px solid black;
	}
		
	.fonticon{
		margin-left: 10px;
	}
	
	select{
		color : #495057;
		background-color : #fff;
		background-clip : padding-box;
		border : 1px solid #ced4da;
		border-radius : .25rem;
		transition : border-color .15s ease-in-out, box-shadow .15s ease-in-out;
		outline : none;
	}
	
	#productsearch{
		border : 1px solid black;
	}
		
	#category{
		font-size: 25px;
	}
		
	#category > li{
		margin-right: 30px;
	}
	
	.fa-user{
		padding-left: 20px;
	}
	
	#categorynav{
		height : 50px;
	}

	#headersearch{
		height:  70px;
		margin-left: 155px;
	}
</style>
<nav class="navbar navbar-expand-sm bg-white navbar-dark pt-3 justify-content-center" id="headersearch">
	<a class="navbar-brand text-dark" href="main.ma"><img src="image/RocketLaptopLogo.png" width="150px" style="margin-right : -20px"></a>
	<form action="MainSearch.ma" method="post">
		<div class="input-group" style="margin-top : 9px;">
		    <select id="productsearch" name="search_field">
		    	<option value="0" selected>상품명</option>
		    	<option value="1">카테고리명</option>
		    </select>
		    <input name="search_word" type="text" class="form-control"
		           placeholder="상품명 입력하세요" value="${search_word}" id="search_word">
			<button id="search" class="btn btn-success" type="submit">검색</button>
		</div>
	</form>
	<input type="hidden" value="${search_field }" class="search_field">
	<ul class="navbar-nav">
		<c:if test="${user_id == 'admin'}">
			<li class="nav-item">
				<a class="nav-link fonticon text-dark" href="NoticeList.ad" style="margin-left: 40px;"><i class="fas fa-user fa-2x"></i><br>관리자 페이지</a>
			</li>
		</c:if>
		<c:if test="${user_id != 'admin' || empty user_id}">
			<li class="nav-item">
				<a class="nav-link fonticon text-dark" href="#"><i class="fas fa-user fa-2x"></i><br>마이 페이지</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fontiocn text-dark" href="#"><i class="fas fa-shopping-cart fa-2x"></i><br>장바구니</a>
			</li>
		</c:if>
	</ul>
</nav>
<nav class="navbar navbar-expand-sm bg-secondary navbar-dark justify-content-center" id="categorynav">
	<ul class="navbar-nav" id="category">
		<li class="nav-item dropdown">
		 	<a class="nav-link dropdown-toggle category" href="#" id="navbardrop" data-toggle="dropdown">
				<i class="fas fa-align-justify fa-1x"></i> 카테고리
		  	</a>
			<div class="dropdown-menu" id="categorymenu">
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
			<a class="nav-link" href="#">문의사항</a>
	 	</li>
	</ul>
</nav>