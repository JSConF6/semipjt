$(function(){
	$('.plus').click(function(){
		var num = $(".order_de_count").val();
		var plusNum = Number(num) + 1;
		   
		if(plusNum >= 1000) {
			$(".order_de_count").val(num);
		}else{
			$(".order_de_count").val(plusNum);       
		}
	});
		  
	$(".minus").click(function(){
		var num = $(".order_de_count").val();
		var minusNum = Number(num) - 1;
		   
		if(minusNum <= 0) {
			$(".order_de_count").val(num);
		} else {
			$(".order_de_count").val(minusNum);          
		}
	});
	
	// 다음 우편 검색 API
	$('#postsearchbtn').click(function(){
		Postcode();
	});
	
	function Postcode(){
		new daum.Postcode({
			oncomplete: function(data) {
				
				var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; // 참고 항목 변수
				
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				
				if(fullRoadAddr !== ''){
					fullRoadAddr += extraRoadAddr;
				}
				
				
				$('#order_address1').val(data.zonecode);
				$('#order_address2').val(fullRoadAddr);
				
			}
		}).open();
	}
	
	// 바로 구매 버튼 클릭시
	$('#buybtn').click(function(){
		var order_de_count = $(".order_de_count").val();
		var price = $(".price").val();
		var pricesum = parseInt(price * order_de_count);
		var user_id = $('.checkid').val();
		if(user_id == ''){
			$('#productOrderModal').modal('show');
			$(".productOrderErrorBtn").click(function(){
				$('#productOrderModal').modal('hide');
				$('#loginModal').modal('show');
			});
			return false;
		}
		$('#MainOrderInfoModal').modal('show');
		$('input[name="order_de_count"]').remove();
		$('input[name="pricesum"]').remove();
		$('#OrderForm').append('<input type="hidden" value="' + order_de_count + '"name="order_de_count">');
		$('#OrderForm').append('<input type="hidden" value="' + pricesum + '"name="pricesum">');
		$('.total_price').text(Number(pricesum).toLocaleString('en') + "원");
	});
	
	// 주문 정보 입력 유효성 검사
	$('#OrderForm').submit(function(){
		if($.trim($('#order_name').val()) == ''){
			$('#DetailOrderInfoErrorModal').modal('show');
			$('#DetailOrderInfoErrorModal-Title').text("주문 정보");
			$('#DetailOrderInfoErrorModal-body').html("<h3>수령인을 입력해주세요.</h3>");
			return false;
		}
		
		if($.trim($('#order_phone').val()) == ''){
			$('#DetailOrderInfoErrorModal').modal('show');
			$('#DetailOrderInfoErrorModal-Title').text("주문 정보");
			$('#DetailOrderInfoErrorModal-body').html("<h4>수령인 연락처를 입력해주세요.</h4>");
			return false;
		}
		
		var phoneReg = /^\d{3}-\d{3,4}-\d{4}$/;
		if(!phoneReg.test($.trim($('#order_phone').val()))){
			$('#DetailOrderInfoErrorModal').modal('show');
			$('#DetailOrderInfoErrorModal-Title').text("주문 정보");
			$('#DetailOrderInfoErrorModal-body').html("<h5>전화번호 형식에 맞게 입력해주세요</h5>");
			$('#order_phone').val('');
			return false;
		}
		
		if($.trim($('#order_address1').val()) == ''){
			$('#DetailOrderInfoErrorModal').modal('show');
			$('#DetailOrderInfoErrorModal-Title').text("주문 정보");
			$('#DetailOrderInfoErrorModal-body').html("<h3>주소검색을 해주세요.</h3>");
			return false;
		}
		
		if($.trim($('#order_address3').val()) == ''){
			$('#OrderInfoErrorModal').modal('show');
			$('#OrderInfoErrorModal-Title').text("주문 정보");
			$('#OrderInfoErrorModal-body').html("<h3>상세주소를 입력해주세요.</h3>");
			return false;
		}
		
		if($.trim($('input[name="order_payment"]:checked').val()) == ''){
			$('#OrderInfoErrorModal').modal('show');
			$('#OrderInfoErrorModal-Title').text("주문 정보");
			$('#OrderInfoErrorModal-body').html("<h4>결제방식을 체크해주세요</h4>");
			return false;
		}
	});
	
	// 장바구니 담기 버튼 클릭시
	$('#cartbtn').click(function(){
		var user_id = $('.checkid').val();
		var product_code = $('.product_code').val();
		if(user_id == ''){
			$('#CartErrorModal').modal('show');
			$("#cartErrorBtn").click(function(){
				$('#CartErrorModal').modal('hide');
				$('#loginModal').modal('show');
			});
			return false;
		}
		$('#CartModal').modal('show');
		$('#addbtn').click(function(){
			$('#CartModal').modal('hide');
			$.ajax({
				url : "MainCartAction.ma",
				type : 'post',
				data : {product_code : product_code, 
						user_id : user_id, 
						order_de_count : $('.order_de_count').val()
					   },
				success : function(data){
					console.log(data);
					if(data == 1){
						$('#CartAddConfirmModal').modal('show');
						$('.CartAddConfirmModal_body').html("<h3>장바구니에 담았습니다.</h3>");
						$(".order_de_count").val('1');
					}else{
						$('#CartAddErrorModal').modal('show');
					}
				}
			});
		});
	});
});