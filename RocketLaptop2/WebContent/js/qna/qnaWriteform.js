$(document).ready(function(){
	//submit 버튼 클릭할 때 이벤트 부분
	$("form").submit(function() {
		
		if ($.trim($("#qna_pass").val()) == "") {
			alert("비밀번호를 입력하세요");
			$("#qna_pass").focus();
			return false;
		}
	
		if ($.trim($("#qna_subject").val()) == "") {
			alert("제목을 입력하세요");
			$("#qna_subject").focus();
			return false;
		}

		if ($.trim($("#qna_content").val()) == "") {
			alert("내용을 입력하세요");
			$("#qna_content").focus();
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