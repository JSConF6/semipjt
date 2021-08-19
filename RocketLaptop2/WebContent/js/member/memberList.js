$(function(){
	var selectedValue=$('.search_field').val();
	
	if(selectedValue!='-1'){
		$("#userinfo").val(selectedValue);
	}
	
	// 검색 버튼 클릭한 경우
	$('#search').click(function(){
		// 검색어 유효성 검사를 한다.
		if($("#search_word").val()==''){
			$('#MemberErrorModal').modal('show');
			$('#MemberErrorModal-Title'). text("검색어 입력");
			$('#MemberErrorModal-body').html("<h3>검색어를 입력해주세요</h3>");
			return false;
		}
	});
	
	// 검색창 select가 바뀌면 placholder 바뀐다
	$('#userinfo').change(function(){
		selectedValue = $(this).val();
		$("#search_word").val('');
		message = ["아이디", "이름", "성별", "이메일", "전화번호"];
		$("#search_word").attr("placeholder", message[selectedValue] + " 입력하세요");
	});
	
});