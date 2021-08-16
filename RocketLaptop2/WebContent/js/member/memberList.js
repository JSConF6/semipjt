$(function(){
	var selectedValue=$('.search_field').val();
	
	if(selectedValue!='-1'){
		$("#userinfo").val(selectedValue);
	}
	
	// 검색 버튼 클릭한 경우
	$('#search').click(function(){
		// 검색어 유효성 검사를 한다.
		if($("input").val()==''){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title'). text("검색어 입력");
			$('#ErrorModal-body').html("<h4>검색어를 입력해주세요</h4>");
			return false;
		}
	});
	
	// 검색창 select가 바뀌면 placholder 바뀐다
	$('#userinfo').change(function(){
		selectedValue = $(this).val();
		$("input").val('');
		message = ["아이디", "이름", "성별", "이메일", "전화번호"];
		$("input").attr("placeholder", message[selectedValue] + " 입력하세요");
	});
	
});