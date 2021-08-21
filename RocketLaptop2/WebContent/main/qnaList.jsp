<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<style>
   select.form-control{
         width:auto;margin-bottom:2em;display:inline-block}
   .rows{text-align:right;}
   .gray{color:gray}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(1){width:8%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(2){width:50%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(3){width:14%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(4){width:17%}
   body > div > table > thead > tr:nth-child(2) > th:nth-child(5){width:11%}
 </style>
 <script src="js/qna/qnaList.js"></script>
<title>RocketLaptop - 문의사항</title>
</head>
<body>

	<input type="hidden" value="-1" class="search_field">
	<jsp:include page="headernav.jsp" />
	<div class="container" style="margin-bottom : 130px;">
		<div class='intro_message'>
       	      <p>&nbsp;<img src="image/qna.jpg" class="txc-image" width="1110" height="200" style="clear: none; float: none;" /></p></div>
		<%-- 게시글이 있는 경우--%>
		<c:if test="${listcount > 0 }">
			<div class="rows">
				<span>줄보기</span>
				<select class="fomr-control" id="viewcount">
					<option value="1">1</option>
					<option value="3">3</option>
					<option value="5">5</option>
					<option value="7">7</option>
					<option value="10" selected>10</option>
				</select>
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th colspan="3">문의사항 게시판 </th>
						<th colspan="2"><font size=3>글 개수 : ${listcount}</font></th>
					</tr>
					<tr>
						<th><div>번호</div></th>
						<th><div>제목</div></th>
						<th><div>작성자</div></th>
						<th><div>날짜</div></th>
						<th><div>조회수</div></th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${listcount-(page-1)*limit}" />
					<c:forEach var="b" items="${qnalist}">
						<tr>
							<td>
								<%--번호 --%> <c:out value="${num}" />
								<%-- num 출력 --%> <c:set var="num" value="${num-1}" /> <%-- num=num-1; 의미--%>
							</td>
							<td>
								<%--제목 --%>
								<div>
									<%-- 답변글 제목앞에 여백 처리 부분 
			      board_re_lev,  board_num, 
			      board_subject, board_name, board_date, 
			      board_readcount : property 이름 --%>
									<c:if test="${b.qna_re_lev != 0}">
										<!--  답글인 경우 -->
										<c:forEach var="a" begin="0" end="${b.qna_re_lev*2}" step="1">
				&nbsp; 	
				</c:forEach>
										<img src='image/line.gif'>
									</c:if>

									<c:if test="${b.qna_re_lev == 0}">
										<%-- 원문인 경우 --%>
				&nbsp;  
			</c:if>

									<a href="QnaDetailAction.ma?num=${b.qna_num}"> <c:out
											value="${b.qna_subject}" /> <%-- ${b.board_subject} --%> <%-- escapeXml="true" : HTML 태그를 화면에 그대로 보여줍니다. --%>
										<%-- <fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="today"></fmt:parseNumber>
										<fmt:parseDate value="${b.qna_date}" var="chg_dttm" pattern="yyyy-MM-dd"/>
										<fmt:parseNumber value="${b.qna_date / (1000*60*60*24)}" integerOnly="true" var="chgDttm"></fmt:parseNumber>
											<c:if test="${today - chgDttm le 1}">
												<i><img src="image/icon_new.gif" alt="" /></i>	
										    </c:if>     진섭 수정 해준 부분!!!--%>
									</a>
								</div>
							</td>
							<td><div>${b.qna_name}</div></td>
							<td><div>${b.qna_date}</div></td>
							<td><div>${b.qna_readcount}</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="center-block">
				<ul class="pagination justify-content-center">
					<c:if test="${page <= 1 }">
						<li class="page-item"><a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page > 1 }">
						<li class="page-item"><a href="QnaList.ma?page=${page-1}"
							class="page-link">이전&nbsp;</a></li>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<li class="page-item "><a class="page-link gray">${a}</a></li>
						</c:if>
						<c:if test="${a != page }">
							<li class="page-item"><a href="QnaList.ma?page=${a}"
								class="page-link">${a}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
						<li class="page-item"><a class="page-link gray">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage }">
						<li class="page-item"><a href="QnaList.ma?page=${page+1}"
							class="page-link">&nbsp;다음</a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
		<%-- <c:if test="${listcount > 0 }"> end --%>

		<%-- 게시글이 없는 경우--%>
		<c:if test="${listcount == 0 }">
			<font size=5>등록된 글이 없습니다.</font>
		</c:if>

		<button type="button" class="btn btn-primary float-right writebtn">글 쓰 기</button>
	</div>
		<hr>
		<jsp:include page="footer.jsp" />
</body>
</html>