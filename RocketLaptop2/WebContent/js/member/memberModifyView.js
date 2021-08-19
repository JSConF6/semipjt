$(function(){
	$("input[value="+$(".user_gender").val()+"]").prop('checked', true);
	
	// 다음 우편 검색 API
	$('#postsearchbtn').click(function(){
		Postcode();
	});
	
	function Postcode(){
		new daum.Postcode({
			oncomplete: function(data) {
				
				var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; // 참고 항목 변수
				
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				
				if(fullRoadAddr !== ''){
					fullRoadAddr += extraRoadAddr;
				}
				
				
				$('#adminpage_user_address1').val(data.zonecode);
				$('#adminpage_user_address2').val(fullRoadAddr);
				
			}
		}).open();
	}
	
	// userModifyFrom을 submit했을떄 유효성 검사
	$('#memberModifyFrom').submit(function(){
		if($.trim($('#adminpage_user_password').val()) == ''){
			$('#MemberModifyErrorModal').modal('show');
			$('#MemberModifyErrorModal-Title').text("회원 정보");
			$('#MemberModifyErrorModal-body').html("<h4>비밀번호를 입력해주세요</h4>");
			return false;
		}

		if($.trim($('#adminpage_user_email').val()) == ''){
			$('#MemberModifyErrorModal').modal('show');
			$('#MemberModifyErrorModal-Title').text("회원 정보");
			$('#MemberModifyErrorModal-body').html("<h4>이메일을 입력해주세요</h4>");
			return false;
		}
		
		var emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if(!emailReg.test($.trim($('#adminpage_user_email').val()))){
			$('#MemberModifyErrorModal').modal('show');
			$('#MemberModifyErrorModal-Title').text("회원 정보");
			$('#MemberModifyErrorModal-body').html("<h5>이메일 형식에 맞게 입력해주세요</h5>");
			$('#adminpage_user_email').val('');
			return false;
		}
		
		if($.trim($('#adminpage_user_phone').val()) == ''){
			$('#MemberModifyErrorModal').modal('show');
			$('#MemberModifyErrorModal-Title').text("회원 정보");
			$('#MemberModifyErrorModal-body').html("<h4>전화번호를 입력해주세요</h4>");
			return false;
		}
		
		var phoneReg = /^\d{3}-\d{3,4}-\d{4}$/;
		if(!phoneReg.test($.trim($('#adminpage_user_phone').val()))){
			$('#MemberModifyErrorModal').modal('show');
			$('#MemberModifyErrorModal-Title').text("회원 정보");
			$('#MemberModifyErrorModal-body').html("<h5>전화번호 형식에 맞게 입력해주세요</h5>");
			$('#adminpage_user_phone').val('');
			return false;
		}
	});

});