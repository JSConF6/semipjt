<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/main/headernav.js" type="text/javascript"></script>
<style>
	#title{
		font-size: 30px;
		margin-top: 4px;
		margin-bottom: 8px;
	}
		
	#search_word{
		width: 410px;
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
	
	#categorynav{
		height : 50px;
	}

	#headersearch{
		height:  70px;
		margin-left: 165px;
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
	<input type="hidden" value="${user_id}" class="headernavid">
	<input type="hidden" value="${search_field}" class="search_field">
	<ul class="navbar-nav">
		<c:if test="${user_id == 'admin'}">
			<li class="nav-item">
				<a class="nav-link fonticon text-dark" href="NoticeList.ad"><i class="fas fa-user fa-2x" style="padding-left : 24px;"></i><br>관리자페이지</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fontiocn text-dark" href="OrderListView.ma?user_id=${user_id}"><i class="fas fa-laptop-code fa-2x" style="padding-left : 4px;"></i><br>주문목록</a>
			</li>
			<li>
				<a class="nav-link fontiocn text-dark" href="MainCartView.ma?user_id=${user_id}"><i class="fas fa-shopping-cart fa-2x"></i><br>장바구니</a>
			</li>
		</c:if>
		<c:if test="${user_id != 'admin' || empty user_id}">
			<li class="nav-item">
				<a class="nav-link fonticon text-dark" id="updateMember" style="cursor : pointer;"><i class="fas fa-user fa-2x" style="padding-left : 18px;"></i><br>마이페이지</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fontiocn text-dark" href="OrderListView.ma?user_id=${user_id}"><i class="fas fa-laptop-code fa-2x" style="padding-left : 4px;"></i><br>주문목록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fontiocn text-dark" href="MainCartView.ma?user_id=${user_id}"><i class="fas fa-shopping-cart fa-2x" style="padding-left : 2px;"></i><br>장바구니</a>
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
			<a class="nav-link" href="MainNewProductList.ma">새로운 상품</a>
	  	</li>
	  	<li class="nav-item">
			<a class="nav-link" href="MainBestProductList.ma">베스트 상품</a>
	 	 </li>
	  	<li class="nav-item">
			<a class="nav-link" href="MainNoticeList.ma">공지사항</a>
	 	 </li>
	  	<li class="nav-item">
			<a class="nav-link" href="QnaList.ma">문의사항</a>
	 	</li>
	</ul>
</nav>

<!-- 오류 모달창 -->
<div class="modal hide fade" id="headerNavErrorModal">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content">
      
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title" id="headerNavErrorModal-Title"></h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
        
			<!-- Modal body -->
			<div class="modal-body" id="headerNavErrorModal-body">
			  
			</div>
        
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
			</div>
        
		</div>
	</div>
</div>