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
		var pricesum = price * order_de_count;
		$('#MainOrderInfoModal').modal('show');
		$('input[name="order_de_count"]').remove();
		$('input[name="pricesum"]').remove();
		$('#OrderForm').append('<input type="hidden" value="' + order_de_count + '"name="order_de_count">');
		$('#OrderForm').append('<input type="hidden" value="' + pricesum + '"name="pricesum">');
	});
	
	// 장바구니 담기 버튼 클릭시
	$('#cartbtn').click(function(){
		var user_id = $('.checkid').val();
		var product_code = $('.product_code').val();
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
					}
					
					if(data == -1){
						$('#CartAddConfirmModal').modal('show');
						$('.CartAddConfirmModal_body').html("<h4>장바구니 담기에 실패했습니다.</h4>");
					}
					
					if(data == 0){
						$('#CartErrorModal').modal('show');
						$(".order_de_count").val('1');
					}
				}
			});
		});
	});
});