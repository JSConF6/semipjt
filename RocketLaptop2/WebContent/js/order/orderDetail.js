$(function(){
	var order_num = $('.order_num').val();
	var user_id = $('.order_user_id').val();
	$('.deliverybtn1').click(function(){
		var deliveryStatus = "배송중";
		$.ajax({
			url : "OrderDeliveryUpdate.ad",
			type : 'post',
			data : {deliveryStatus : deliveryStatus,
					order_num : order_num,
					user_id : user_id},
			cache : false,
			success : function(data){
				if(data == 1){
					location.replace('OrderDetail.ad?order_num=' + order_num + '&user_id=' + user_id);
				}else{
					$("#DeliveryStatusErrorModal").modal('show');
				}
			}
		});
	});
	
	$('.deliverybtn2').click(function(){
		var deliveryStatus = "배송완료";
		$.ajax({
			url : "OrderDeliveryUpdate.ad",
			type : 'post',
			data : {deliveryStatus : deliveryStatus,
					order_num : order_num,
					user_id : user_id},
			cache : false,
			success : function(data){
				if(data == 1){
					location.replace('OrderDetail.ad?order_num=' + order_num + '&user_id=' + user_id);
				}else{
					$("#DeliveryStatusErrorModal").modal('show');
				}
			}
		});
	});
});