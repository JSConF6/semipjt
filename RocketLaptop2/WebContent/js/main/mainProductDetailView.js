$(function(){
	var product_stock = $(".product_stock").val();
	
	$('.plus').click(function(){
		var num = $(".stock").val();
		var plusNum = Number(num) + 1;
		   
		if(plusNum >= product_stock) {
			$(".stock").val(num);
		}else{
			$(".stock").val(plusNum);       
		}
	});
		  
	$(".minus").click(function(){
		var num = $(".stock").val();
		var minusNum = Number(num) - 1;
		   
		if(minusNum <= 0) {
			$(".stock").val(num);
		} else {
			$(".stock").val(minusNum);          
		}
	});
	
	$('#buybtn').click(function(){
		var checkid = $.trim($('.checkid').val());
		if(checkid == ''){
			$('#productOrderModal').modal('show');
			return false;
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
						stock : $('.stock').val()
					   },
				cache : false,
				success : function(data){
					if(data == 1){
						$('#CartAddConfirmModal').modal('show');
						$('.CartAddConfirmModal_body').html("<h3>장바구니에 담았습니다.</h3>");
						$(".stock").val('1');
					}else{
						$('#CartErrorModal').modal('show');
						$(".stock").val('1');
					}
				},
				error : function(){
					$('#CartAddConfirmModal').modal('show');
					$('#CartAddConfirmModal_body').html("<h5>장바구니 담기에 실패했습니다.</h5>");
				}
			});
		});
	});
});