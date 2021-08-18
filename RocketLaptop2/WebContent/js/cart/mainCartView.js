$(function(){
	// OrderForm submit 유효성 검사
	$('#OrderForm').submit(function(){
		if($.trim($('#order_name').val()) == ''){
			$('#OrderInfoErrorModal').modal('show');
			$('#OrderInfoErrorModal-Title').text("주문 정보");
			$('#OrderInfoErrorModal-body').html("<h3>수령인을 입력해주세요.</h3>");
			return false;
		}
		
		if($.trim($('#order_phone').val()) == ''){
			$('#OrderInfoErrorModal').modal('show');
			$('#OrderInfoErrorModal-Title').text("주문 정보");
			$('#OrderInfoErrorModal-body').html("<h4>수령인 연락처를 입력해주세요.</h4>");
			return false;
		}
		
		var phoneReg = /^\d{3}-\d{3,4}-\d{4}$/;
		if(!phoneReg.test($.trim($('#order_phone').val()))){
			$('#OrderInfoErrorModal').modal('show');
			$('#OrderInfoErrorModal-Title').text("주문 정보");
			$('#OrderInfoErrorModal-body').html("<h5>전화번호 형식에 맞게 입력해주세요</h5>");
			$('#order_phone').val('');
			return false;
		}
		
		if($.trim($('#order_address1').val()) == ''){
			$('#OrderInfoErrorModal').modal('show');
			$('#OrderInfoErrorModal-Title').text("주문 정보");
			$('#OrderInfoErrorModal-body').html("<h3>주소검색을 해주세요.</h3>");
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
	
	// 주문 부분
	$('.orderbtn').click(function(){
		$('#OrderInfoModal').modal('show');
		
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
	
	// 장바구니 선택 삭제 부분
	var chkObj = $("input[name='RowCheck']");
	var rowCnt = chkObj.length;
	
	$('input[name="allCheck"]').click(function(){
		var chk_listArr = $('input[name="RowCheck"]');
		for(var i = 0; i < chk_listArr.length; i++){
			chk_listArr[i].checked = this.checked;
		}
	});
	
	$('input[name="RowCheck"]').click(function(){
		if($('input[name="RowCheck"]:checked').length == rowCnt){
			$("input[name='allCheck']")[0].checked = true;
		}else{
			$("input[name='allCheck']")[0].checked = false;
		}
	});
	
	function deleteValue(){
		 var valueArr = new Array();
		 var user_id = $('.checkUser_id').val();
		 var list = $("input[name='RowCheck']");
		 for(var i = 0; i < list.length; i++){
			 if(list[i].checked){
				 valueArr.push(list[i].value);
			 }
		 }
		 if(valueArr.length == 0){
			$("#ErrorModal").modal('show');
			$('#ErrorModal-Title').text('장바구니 선택 삭제');
			$("#ErrorModal-body").html("<h3>선택한 상품이 없습니다.</h3>");
		 }else{
			 $("#CartDeleteModal").modal('show');
			 $("#cartmodaldelbtn").click(function(){
				 $("#CartDeleteModal").modal('hide');
				 $.ajax({
					url : 'MainCartSelectionDelete.ma',
					type : 'post',
					traditional : true,
					data : {valueArr : valueArr, user_id : user_id},
					success : function(data){
						if(data == valueArr.length){
							location.href="MainCartView.ma?user_id=" + user_id;
						}else{
							$("#CartSelectionDeleteFailModal").modal('show');
						}
					}
				 });
			 });
		 }
	}
	
	$('#selectionDelete').click(function(){
		deleteValue();
	});
	
	// 장바구니 삭제 부분
	function cartDelete(cart_num){
		var user_id = $(".checkUser_id").val();
		$('#CartDeleteModal').modal('show');
		$('#cartmodaldelbtn').click(function(){
			$('#CartDeleteModal').modal('hide');
			$.ajax({
				url : "MainCartDeleteAction.ma",
				type : 'post',
				data : {cart_num : cart_num, user_id : user_id},
				success : function(data){
					if(data == 1){
						location.href="MainCartView.ma?user_id=" + user_id;
					}else{
						$('#ErrorModal').modal('show');
						$('#ErrorModal-Title').text('장바구니');
						$('#ErrorModal-body').html("<span style='font-size : 21px;'>장바구니 상품 삭제 실패 했습니다.</span>");
					}
				}
			});
		});
	}
	
	$('.cartdelbtn').click(function(){
		var cart_num = $(this).attr('data-cartNum');
		cartDelete(cart_num);
	});
});