$(document).ready(function(){
	//submit 버튼 클릭할 때 이벤트 부분
	$("form").submit(function() {
		
		if ($.trim($("#qna_subject").val()) == "") {
			$('#qnaReplyErrorModal').modal('show');
			$('#qnaReplyErrorModal-Title').text('문의사항 답변');
			$('#qnaReplyErrorModal-body').html('<h3>제목을 입력하세요.</h3>');
			return false;
		}
	
		if ($.trim($("#qna_content").val()) == "") {
			$('#qnaReplyErrorModal').modal('show');
			$('#qnaReplyErrorModal-Title').text('문의사항 답변');
			$('#qnaReplyErrorModal-body').html('<h3>내용을 입력하세요.</h3>');
			return false;
		}

		if ($.trim($("#qna_pass").val()) == "") {
			$('#qnaReplyErrorModal').modal('show');
			$('#qnaReplyErrorModal-Title').text('문의사항 답변');
			$('#qnaReplyErrorModal-body').html('<h3>비밀번호를 입력하세요.</h3>');
			return false;
		}
	});//submit end
	
});//ready() end