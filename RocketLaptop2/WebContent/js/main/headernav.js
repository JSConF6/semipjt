$(function(){
	var selectedValue=$(".search_field").val();
	
	if(selectedValue!='-1'){
		$("#productsearch").val(selectedValue);
	}
	
	// 검색 버튼 클릭한 경우
	$('#search').click(function(){
		// 검색어 유효성 검사를 한다.
		if($.trim($("#search_word").val())==''){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title'). text("검색어 입력");
			$('#ErrorModal-body').html("<h4>검색어를 입력해주세요</h4>");
			return false;
		}
	});
	
	// 검색창 select가 바뀌면 placholder 바뀐다
	$('#productsearch').change(function(){
		selectedValue = $(this).val();
		$("input").val('');
		message = ["상품명", "카테고리명"];
		$("input").attr("placeholder", message[selectedValue] + " 입력하세요");
	});
	
	// 카테고리 ajax로 받아서 카테고리 추가
	$('.category').click(function(){
		$.ajax({
			url : 'CategoryList.ad',
			type : 'post',
			dataType : 'json',
			cache : false,
			success : function(data){
				$('#categorymenu > a').remove();
				$(data.categorylist).each(function(index, item){
					$("#categorymenu").append("<a class='dropdown-item' "
							+ "href='MainCategoryProductList.ma?category_name="+ this.category_name + "'>" 
							+ this.category_name + "</a>");
				});
			}
		});
	});
});