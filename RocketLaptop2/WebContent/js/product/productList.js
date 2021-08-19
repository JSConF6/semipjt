$(function(){
	var selectedValue=$('.search_field').val();
	
	if(selectedValue!='-1'){
		$("#productinfo").val(selectedValue);
	}
	
	// 검색 버튼 클릭한 경우
	$('#productsearch').click(function(){
		// 검색어 유효성 검사를 한다.
		if($("#search_word").val()==''){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title'). text("검색어 입력");
			$('#ProductErrorModal-body').html("<h3>검색어를 입력해주세요</h3>");
			return false;
		}
	});
	
	// 검색창 select가 바뀌면 placholder 바뀐다
	$('#productinfo').change(function(){
		selectedValue = $(this).val();
		$("#search_word").val('');
		message = ["상품코드", "상품명", "카테고리", "상품상태"];
		$("#search_word").attr("placeholder", message[selectedValue] + " 입력하세요");
	});
	
	// 선택삭제 부분
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
		 var list = $("input[name='RowCheck']");
		 for(var i = 0; i < list.length; i++){
			 if(list[i].checked){
				 valueArr.push(list[i].value);
			 }
		 }
		 if(valueArr.length == 0){
			$("#ProductErrorModal").modal('show');
			$('#ProductErrorModal-Title').text('선택 삭제');
			$("#ProductErrorModal-body").html("<h3>선택한 상품이 없습니다.</h3>");
		 }else{
			 $("#SelectionDeleteModal").modal('show');
			 $("#SelectionDeletebtn").click(function(){
				 $("#SelectionDeleteModal").modal('hide');
				 $.ajax({
					url : 'ProductSelectionDelete.ad',
					type : 'post',
					traditional : true,
					data : {valueArr : valueArr},
					success : function(data){
						if(data == valueArr.length){
							$("#SelectionDeleteSuccessModal").modal('show');
						}else{
							$("#SelectionDeleteFailModal").modal('show');
						}
					}
				 });
			 });
		 }
	}
	
	$('#selectionDelete').click(function(){
		deleteValue();
	});
	
	// 상품등록 버튼 클릭하면 모달창이 뜨고 ajax를 이용해 카테고리를 드롭다운 방식으로 추가해준다.
	$("#productAddbtn").click(function(){
		$.ajax({
			url : "CategoryList.ad",
			type : 'post',
			dataType : 'json',
			cache : false,
			success : function(data){
				$(".category > option").remove();
				$(data.categorylist).each(function(index, item){
					$(".category").append("<option value='" + this.category_code
							+ "'>" + this.category_name + "</option>");
				});
			}
		});
		
		$('#ProductAddModal').modal('show');
	});
	
	// 카테고리 삭제를 클릭하면 모달창이 뜨고 ajax를 이용해 카테고리를 드롭다운 방식으로 추가해준다.
	$('#categoryDelbtn').click(function(){
		$.ajax({
			url : "CategoryList.ad",
			type : 'post',
			dataType : 'json',
			cache : false,
			success : function(data){
				$(".deletecategory > option").remove();
				$(data.categorylist).each(function(index, item){
					$(".deletecategory").append("<option value='" + this.category_code
							+ "'>" + this.category_name + "</option>");
				});
			}
		});
		
		$('#CategoryDelModal').modal('show');
	});
	
	// 카테고리 추가 input 유효성 검사
	$("#category_AddName").keyup(function(){
		$(this).val($(this).val().replace(/[^A-Z]/g, ""));
	});
	
	
	// Modal창 안에 상품등록 폼이 submit할때 유효성 검사
	$('#productAddFrom').submit(function(){
		if($.trim($('#product_code').val()) == ''){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("상품 등록");
			$('#ProductErrorModal-body').html("<h4>상품 코드를 입력해주세요</h4>");
			return false;
		}
		
		if($.trim($('.category').val()) == ''){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("상품 등록");
			$('#ProductErrorModal-body').html("<h4>카테고리를 선택해주세요</h4>");
			return false;
		}
		
		if($.trim($('#product_name').val()) == ''){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("상품 등록");
			$('#ProductErrorModal-body').html("<h4>상품명을 입력해주세요</h4>");
			return false;
		}
		
		if($.trim($('#product_price').val()) == ''){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("상품 등록");
			$('#ProductErrorModal-body').html("<h4>상품 가격을 입력해주세요</h4>");
			return false;
		}
		
		if($.trim($('#product_details').val()) == ""){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("상품 등록");
			$('#ProductErrorModal-body').html("<h4>상품 상세정보를 입력해주세요</h4>");
			return false;
		}
		
		if($.trim($('input[name="productStatus"]:checked').val()) == ''){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("상품 등록");
			$('#ProductErrorModal-body').html("<h4>상품 상태를 체크해주세요</h4>");
			return false;
		}
		
		if($.trim($('#imgUpload').val()) == ''){
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("상품 등록");
			$('#ProductErrorModal-body').html("<h4>이미지를 첨부해주세요</h4>");
			return false;
		}
	});
	
	// 가격, 재고 수는 숫자만 입력 숫자 입력 안하면 value초기화
	$("#product_price").keyup(function(){
		if(!($.isNumeric($('#product_price').val()))){
			$("#product_price").val('');
		}
	});
	
	// 이미지 첨부 부분
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
	
	$('#imgUpload').change(function(){
		var inputimg = $(this).val().split("\\");
		var filename = inputimg[inputimg.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;
		if(pattern.test(filename)){
			$('#imgvalue').text(filename);
			show();
		}else{
			$('#ProductErrorModal').modal('show');
			$('#ProductErrorModal-Title').text("이미지 첨부");
			$('#ProductErrorModal-body').html("<h6>확장자는 gif, jpg, jpeg, png가 가능합니다.</h6>");
			return false;
		}
	});
	
	$('.remove').click(function(){
		$("#imgvalue").text('');
		show();
	});
});