$(function(){
	// 검색 버튼 클릭한 경우
	$('#search').click(function(){
		// 검색어 유효성 검사를 한다.
		if($("#search_word").val()==''){
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
					$("#categorymenu").append("<a class='dropdown-item' href='#'>" 
							+ this.category_name + "</a>");
				});
			}
		});
	});
	
	function SetProduct(Img, Name, Content, info, href){
		var output = "<div class='col'>" +
					 	"<div class='card h-100'>" +
					 		"<img src='LaptopImgUpload/" + Img + "' class='card-img-top' alt='...' height='60%'>" +
					 		"<div class='card-body'>" +
					 			"<h5 class='card-title'>" + Name + "</h5>" +
					 			"<p class='card-text'>" + Content + "</p>" +
					 			"<p class='card-text'>" + info + "</p>" +
					 		"</div>" +
					 		"<div class='card-footer text-center'>" +
					 			"<a href='" + href + "'>상품 상세보기</a>" +
					 		"</div>" +
					 	"</div>" +
					 "</div>";
		
		return output;
	}
	
	function MainProduct(){
		$.ajax({
			url : "MainProductList.ma",
			type : 'post',
			dataType : 'json',
			cache : false,
			success : function(data){
				console.log()
				$('#bestProductMain > #bestProduct').remove();
				$('#newProductMain > #newProduct').remove();
				var bestoutput = "<div class='row row-cols-1 row-cols-md-3 g-4' id='bestProduct'>";
				$(data.bestlist).each(function(index, item){
					bestoutput += SetProduct(this.product_image, this.product_name,
										 this.product_details, "상품 판매량 : " + this.product_sales, 'MainProductDetail.ma?product_code=' + this.product_code);
				});
				bestoutput += "</div>";
				
				var newoutput = "<div class='row row-cols-1 row-cols-md-3 g-4' id='newProduct'>";
				$(data.newlist).each(function(index, item){
					newoutput += SetProduct(this.product_image, this.product_name,
										 this.product_details, "상품 등록 날짜 : " + this.product_date, 'MainProductDetail.ma?product_code=' + this.product_code);
				});
				newoutput += "</div>";
				
				$('#bestProductMain').append(bestoutput);
				$('#newProductMain').append(newoutput);
			}
		});
	}
	
	MainProduct();
	
});
