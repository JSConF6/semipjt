$(function(){
	$("input[value="+$(".product_status").val()+"]").prop('checked', true);
	
	var check = 0;
	// 수정 폼 submit할때 유효성 검사
	$('#productModifyFrom').submit(function(){
		if($.trim($('#product_name').val()) == ''){
			$('#ProductModifyErrorModal').modal('show');
			$('#ProductModifyErrorModal-Title').text("상품 등록");
			$('#ProductModifyErrorModal-body').html("<h4>상품명을 입력해주세요</h4>");
			return false;
		}
		
		if($.trim($('#product_price').val()) == ''){
			$('#ProductModifyErrorModal').modal('show');
			$('#ProductModifyErrorModal-Title').text("상품 등록");
			$('#ProductModifyErrorModal-body').html("<h4>상품 가격을 입력해주세요</h4>");
			return false;
		}
		
		if($.trim($('#product_details').val()) == ""){
			$('#ProductModifyErrorModal').modal('show');
			$('#ProductModifyErrorModal-Title').text("상품 등록");
			$('#ProductModifyErrorModal-body').html("<h4>상품 상세정보를 입력해주세요</h4>");
			return false;
		}
		
		if($.trim($('#imgvalue').text()) == ''){
			$('#ProductModifyErrorModal').modal('show');
			$('#ProductModifyErrorModal-Title').text("상품 등록");
			$('#ProductModifyErrorModal-body').html("<h4>이미지를 첨부해주세요</h4>");
			return false;
		}
		
		// 파일을 변경하지 않으면 적용
		if(check == 0){
			value = $("#imgvalue").text();
			html = "<input type='text' value='" + value + "' name='check'>";
			$(this).append(html);
		}
	});
	
	$("#product_price").keyup(function(){
		if(!($.isNumeric($('#product_price').val()))){
			$("#product_price").val('');
		}
	});
	
	function show(){
		if($('#imgvalue').text() == ''){
			$(".remove").css('display', 'none');
		} else {
			$(".remove").css('display', 'inline-block');
		}
	}
	
	show();
	
	$('#imgUploadbtn').click(function(e){
		e.preventDefault();
		$('#imgUpload').click();
	});
	
	$('#imgUpload').change(function(event){
		check++;
		var inputimg = $(this).val().split("\\");
		var filename = inputimg[inputimg.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;
		if(pattern.test(filename)){
			$('#imgvalue').text(filename);
			
			var reader = new FileReader();
			reader.readAsDataURL(event.target.files[0]);
			reader.onload = function(event){
				$("#ProsuctShowImage").html('<img width="100%" height="100%" src="' + event.target.result + '">');
			}
	
			show();
		}else{
			$('#ProductModifyErrorModal').modal('show');
			$('#ProductModifyErrorModal-Title').text("이미지 첨부");
			$('#ProductModifyErrorModal-body').html("<h6>확장자는 gif, jpg, jpeg, png가 가능합니다.</h6>");
			return false;
		}
	});
	
	$('.remove').click(function(){
		$("#imgvalue").text('');
		$("#ProsuctShowImage > img").remove();
		show();
	});
});