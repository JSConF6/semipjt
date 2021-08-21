$(document).ready(function(){
	//submit 버튼 클릭할 때 이벤트 부분
	$("#writeForm").submit(function() {
		
		if ($.trim($("#qna_pass").val()) == "") {
			$('#qnaWriteErrorModal').modal('show');
			$('#qnaWriteErrorModal-Title').text('문의사항 글쓰기');
			$('#qnaWriteErrorModal-body').html('<h3>비밀번호를 입력하세요.</h3>');
			return false;
		}
	
		if ($.trim($("#qna_subject").val()) == "") {
			$('#qnaWriteErrorModal').modal('show');
			$('#qnaWriteErrorModal-Title').text('문의사항 글쓰기');
			$('#qnaWriteErrorModal-body').html('<h2>제목을 입력하세요.</h2>');
			return false;
		}

		if ($.trim($("#qna_content").val()) == "") {
			$('#qnaWriteErrorModal').modal('show');
			$('#qnaWriteErrorModal-Title').text('문의사항 글쓰기');
			$('#qnaWriteErrorModal-body').html('<h2>내용을 입력하세요.</h2>');
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