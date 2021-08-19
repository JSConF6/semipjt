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