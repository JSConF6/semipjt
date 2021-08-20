$(function(){
	function SetProduct(Img, Name, Category, Price, info, href){
		var output = "<div class='col'>" +
					 	"<div class='card h-100'>" +
					 		"<img src='LaptopImgUpload/" + Img + "' class='card-img-top' alt='...' height='60%'>" +
					 		"<div class='card-body'>" +
					 			"<p class='card-title'><span>노트북 이름&nbsp;&nbsp;:&nbsp;&nbsp;" + Name + "</span></p>" +
					 			"<p class='card-text'><span>상품 브랜드&nbsp;&nbsp;:&nbsp;&nbsp;" + Category + "</span></p>" +
					 			"<p class='card-text'><span>노트북 가격&nbsp;&nbsp;:&nbsp;&nbsp;" + Price + "원</span></p>" +
					 			"<p class='card-text'><span>" + info + "</span></p>" +
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
				console.log(data)
				$('#bestProductMain > #bestProduct').remove();
				$('#newProductMain > #newProduct').remove();
				var bestoutput = "<div class='row row-cols-1 row-cols-md-3 g-4' id='bestProduct'>";
				if(data.bestlist.length != 0){
					$(data.bestlist).each(function(index, item){
						var price = Number(this.product_price).toLocaleString('en');
						bestoutput += SetProduct(this.product_image, this.product_name,
											 this.category_name, price,
											 "상품 판매량&nbsp;&nbsp;:&nbsp;&nbsp;" + this.product_sales + "개", 
											 'MainProductDetail.ma?product_code=' + this.product_code);
					});
					bestoutput += "</div>";
				}else{					
					bestoutput += "<div class='container text-center'><h1>베스트 상품이 없습니다.</h1></div>";
				}
				
				
				var newoutput = "<div class='row row-cols-1 row-cols-md-3 g-4' id='newProduct'>";
				if(data.newlist.length != 0){
					$(data.newlist).each(function(index, item){
						var price = Number(this.product_price).toLocaleString('en');
						newoutput += SetProduct(this.product_image, this.product_name,
											 this.category_name, price,
											 "상품 등록일&nbsp;&nbsp;:&nbsp;&nbsp;" + this.product_date, 
											 'MainProductDetail.ma?product_code=' + this.product_code);
					});
					newoutput += "</div>";
				}else{					
					newoutput += "<div class='container text-center'><span style='font-size:33px;'>새로 올라온 상품이 없습니다.</span></div>";
				}
				
				$('#bestProductMain').append(bestoutput);
				$('#newProductMain').append(newoutput);
			}
		});
	}
	
	MainProduct();
	
});
