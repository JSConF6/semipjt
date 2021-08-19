$(function(){
	$("#noticeModifyFrom").submit(function(){
		if($.trim($("#modifyNotice_title").val()) == ""){
			$("#NoticeDetailErrorModal").modal('show');
			$('#NoticeDetailErrorModal-Title').text('공지사항 등록');
			$("#NoticeDetailErrorModal-body").html("<h3>제목을 입력해주세요.</h3>");
			return false;
		}
					
		if($.trim($("#modifyNotice_content").val()) == ""){
			$("#NoticeDetailErrorModal").modal('show');
			$('#NoticeDetailErrorModal-Title').text('공지사항 등록');
			$("#NoticeDetailErrorModal-body").html("<h3>내용을 입력해주세요.</h3>");
			return false;
		}
	});
});