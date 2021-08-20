function go(page){
	var order_delivery = $('#order_delivery').val();
	var data = "order_delivery=" + order_delivery + "&state=ajax&page=" + page;
	ajax(data);
}

function setPaging(href, digit){
	var output = "<li class=page-item>";
	gray = "";
	if(href == ""){
		gray = " gray";
	}
	anchor = "<a class='page-link" + gray + "'" + href + ">" + digit + "</a></li>";
	output += anchor;
	return output;
}

function ajax(sdata){
	console.log(sdata);
	output = "";
	$.ajax({
		url : "OrderList.ad",
		type : 'POST',
		data : sdata,
		dataType : 'json',
		cache : false,
		success : function(data){
			console.log(data);
			$('#order_delivery').val(data.delivery);
			$('.OrderBodyDiv').find('h2').text("주문건수  : " + data.listcount);
			
			if(data.listcount > 0){
				$(".OrderBody").remove();
				$('.OrderBodyTable').remove();
				
				var output = "<table class='table table-striped table-bordered text-center OrderBodyTable'><thead><tr><th>주문 번호</th>" +
							 "<th>수령인</th>" +
							 "<th>수령인 연락처</th>" +
							 "<th>주소</th>" +
							 "<th>주문 가격</th>" +
							 "<th>결제 방식</th>" +
							 "<th>배송 상태</th></tr></thead><tbody>";
				$(data.orderlist).each(function(index, item){
					output += "<tr><td><a href='OrderDetail.ad?order_num=" 
						   + this.order_num + "&user_id=" + this.user_id + "'>" + this.order_num + "</a></td>";
					output += "<td>" + this.order_name + "</td>";
					output += "<td>" + this.order_phone + "</td>";
					output += "<td>(" + this.user_address1 + ") " + this.user_address2 + " " + this.user_address3 + "</td>";
					output += "<td>" + Number(this.order_totalprice).toLocaleString('en') + "원</td>";
					output += "<td>" + this.order_payment + "</td>";
					output += "<td>" + this.order_delivery + "</td>";
					output += "</tr>";
				})
				output += "</tbody></table>";
				$('.OrderBodyDiv').append(output);
				
				$(".pagination").remove();
				output = '<ul class="pagination justify-content-center">';
				
				digit = '이전&nbsp;';
				href = "";
				if(data.page > 1){
					href = "href=javascript:go(" + (data.page - 1) + ")";
				}
				output += setPaging(href, digit);
				
				for(var i = data.startpage; i <= data.endpage; i++){
					digit = i;
					href = "";
					if(i != data.page){
						href = "href=javascript:go(" + i + ")";
					}
					output += setPaging(href, digit);
				}
				
				digit = "&nbsp;다음&nbsp;";
				href = "";
				if(data.page < data.maxpage){
					href = "href=javascript:go(" + (data.page + 1) + ")";
				}
				output += setPaging(href, digit);
				output += '</ul>';
				
				$('.OrderBodyDiv').append(output);
			}else if(data.listcount == 0){
				output = '';
				$(".OrderBodyTable").remove();
				$(".pagination").remove();
				$(".OrderBody").remove();
				if(data.listcount == 0){
					var order_delivery_num = $('#order_delivery').val();
					var message = ['배송 준비중인 상품이 없습니다.', '배송 중인 상품이 없습니다.', '배송 완료된 상품이 없습니다.'];
					output += '<h1 class="OrderBody">' + message[order_delivery_num] + '</h1>';
					$('.OrderBodyDiv').append(output);
				}
			}
		} // $.ajax end
	}); // ajax() end
}

$(function(){
	var selectedValue=$('.search_field').val();
	console.log(selectedValue)
	
	if(selectedValue!='-1'){
		$("#orderinfo").val(selectedValue);
	}
	
	// 검색 버튼 클릭한 경우
	$('#ordersearch').click(function(){
		// 검색어 유효성 검사를 한다.
		if($("#search_word").val()==''){
			$('#OrderErrorModal').modal('show');
			$('#OrderErrorModal-Title'). text("검색어 입력");
			$('#OrderErrorModal-body').html("<h3>검색어를 입력해주세요</h3>");
			return false;
		}
	});
	
	// 검색창 select가 바뀌면 placholder 바뀐다
	$('#orderinfo').change(function(){
		selectedValue = $(this).val();
		$("#search_word").val('');
		message = ["주문번호", "수령인", "결제방식"];
		$("#search_word").attr("placeholder", message[selectedValue] + " 입력하세요");
	});
	
	$('#order_delivery').change(function(){
		go(1);
	});
});