<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="header.jsp" />
		<title>RocketLaptop</title>
		 <!-- link 와 script는 로그인을 위한 용도 -->
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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

			.fa-user{
				padding-left: 20px;
			}

			#nav-search{
				height:  70px;
			}
			
			/* 로그인에 적용할 스타일 내역 추가   향후 색상 변경 검토 할 것!!!!!!!!!!!!!!!!!!!!!!!!!!! */
			.modal-header, h4, .close {
			    background-color: #5cb85c;
			    color:white !important;
			    text-align: center;
			    font-size: 30px;
			}
			.modal-footer {
			    background-color: #f9f9f9;
			    justify-content: center;
			}
			
		</style>
	</head>
	<body>
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
				<c:if test="${user_id == 'admin'}">
					<li class="nav-item">
						<a class="nav-link fonticon" href="NoticeList.ad"><i class="fas fa-user fa-2x"></i><br>관리자 페이지</a>
					</li>
				</c:if>
				<c:if test="${user_id != 'admin' || empty user_id}">
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
					<a class="nav-link" href="#">문의사항</a>
			 	</li>
			</ul>
		</nav>

		  <!--banner-->
		<div class="container">
			<div id="demo" class="carousel slide" data-ride="carousel">
				<ul class="carousel-indicators">
					<li data-target="#demo" data-slide-to="0" class="active"></li>
					<li data-target="#demo" data-slide-to="1"></li>
					<li data-target="#demo" data-slide-to="2"></li>
				</ul>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="image/banner.png" alt="Los Angeles" width="1100" height="500">
						<div class="carousel-caption">
							<h3>Los Angeles</h3>
							<p>We had such a great time in LA!</p>
						</div>   
					</div>
					<div class="carousel-item">
						<img src="image/banner.png" alt="Chicago" width="1100" height="500">
						<div class="carousel-caption">
							<h3>Chicago</h3>
							<p>Thank you, Chicago!</p>
						</div>   
					</div>
					<div class="carousel-item">
						<img src="image/banner.png" alt="New York" width="1100" height="500">
						<div class="carousel-caption">
							<h3>New York</h3>
							<p>We love the Big Apple!</p>
						</div>   
					</div>
				</div>
				<a class="carousel-control-prev" href="#demo" data-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</a>
				<a class="carousel-control-next" href="#demo" data-slide="next">
					<span class="carousel-control-next-icon"></span>
				</a>
			</div>
		</div>

		<!--bestproduct-->
		<div class="container mt-5 mb-5">
			<h3>베스트 상품</h3>
			<div class="row row-cols-1 row-cols-md-3 g-4">
				<div class="col">
					<div class="card h-100">
						<img src="image/account_user.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>	
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/among us.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/android_robot.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--newproduct-->
		<div class="container mb-5">
			<h3>새로운 상품</h3>
			<div class="row row-cols-1 row-cols-md-3 g-4">
				<div class="col">
					<div class="card h-100">
						<img src="image/heart.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/social.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card h-100">
						<img src="image/user.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">상품 이름</h5>
							<p class="card-text">상품 설명</p>
						</div>
						<div class="card-footer">
							<a href="#">자세히 보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
		
		<!--  로그인  모달 기능 구현 소스 _ 향후 배경 색상 검토 할 것!! -->
		<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form" action="loginProcess.ma" method="post">
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
              <input type="text" class="form-control" id="user_id" name="user_id" placeholder="아이디를 입력하세요">
            </div>
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <input type="password" class="form-control" id="user_password" name="user_password" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="checkbox">
              <label><input type="checkbox" value="" checked>ID저장</label>
            </div>
              <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> 로그인</button>
            </form>
        </div>
        <div class="modal-footer">
          <p>Not a member? <a href="#"> 회원가입  &nbsp; </a></p>
          <p>Forgot <a href="#"> 비밀번호찾기 &nbsp; </a></p>
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
          <span class="glyphicon glyphicon-remove"></span> 취소
          </button>
		  <strong>
			  본 사이트는 Chrome 브라우저에 최적화되어 있습니다.
		  </strong> 
        </div>
      </div>
      
    </div>
  </div>
  <script>
	$(document).ready(function(){
	  $("#login").click(function(){
	    $("#myModal").modal();
	  });
	});
</script>
	</body>
</html>