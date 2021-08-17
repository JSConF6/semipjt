$(function(){
	$('#buybtn').click(function(){
		var checkid = $('.checkid').val();
		if(checkid == ''){
			$('#productOrderModal').modal('show');
			return false;
		}
	});
	
	$('#cartbtn').click(function(){
		var checkid = $('.checkid').val();
		if(checkid == ''){
			$('#productCartModal').modal('show');
			return false;
		}
	});
});