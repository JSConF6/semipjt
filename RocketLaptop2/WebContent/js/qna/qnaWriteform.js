$(document).ready(function(){
	//submit 버튼 클릭할 때 이벤트 부분
	$("form").submit(function() {
		
		if ($.trim($("input").eq(1).val()) == "") {
			alert("비밀번호를 입력하세요");
			$("input:eq(1)").focus();
			return false;
		}
	
		if ($.trim($("input").eq(2).val()) == "") {
			alert("제목을 입력하세요");
			$("input:eq(2)").focus();
			return false;
		}

		if ($.trim($("textarea").val()) == "") {
			alert("내용을 입력하세요");
			$("textarea").focus();
			return false;
		}
		/*
		$.ajax({
			url: "BoardAddAction.bo",
			type: "post",
			cache: ,
			dataType: "html",
			data: "",
			success: function(data){
				console.log('aaaaa');
			},
			error: function (request, status, error){
				console.log('error');
			}
			});*/
	});//submit end
	
	$("#qna_upfile").change(function(){
		console.log($(this).val())	//c:\fakepath\upload.png
		var inputfile = $(this).val().split('\\');
		$('#qna_filevalue').text(inputfile[inputfile.length - 1]);
	});
	
});//ready() end