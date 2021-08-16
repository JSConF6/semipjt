$(function(){
	$("#login").click(function(){
	    $("#loginModal").modal();
	  });
	
	  $("#signup").click(function(){
	  	$("#signup_modal").modal();
	  });
	  
	  var checkid=false;
	  var checkemail=false;
	  
	  $('#joinform').submit(function(){
			if(!checkid){
				$('#ErrorModal').modal('show');
				$('#ErrorModal-Title').text("회원가입 유효성 검사");
				$('#ErrorModal-body').html("<h4>사용가능한 ID로 입력하세요</h4>");
				$("#user_id").val('').focus();
				return false;
			}
			
			if($("#user_password").val() == ''){
				$('#ErrorModal').modal('show');
				$('#ErrorModal-Title').text("회원가입 유효성 검사");
				$('#ErrorModal-body').html("<h3>비밀번호를 입력해주세요</h3>");
				$('#user_password1').val('');
				return false;
			}
			
			
			if($('#user_password1').val() != $("#user_password").val()){
				$('#ErrorModal').modal('show');
				$('#ErrorModal-Title').text("회원가입 유효성 검사");
				$('#ErrorModal-body').html("<h3>비밀번호가 다릅니다.</h3>");
				$('#user_password1').val('');
				return false;
			}
			
			if($('#user_name').val() == ""){
				$('#ErrorModal').modal('show');
				$('#ErrorModal-Title').text("회원가입 유효성 검사");
				$('#ErrorModal-body').html("<h3>이름을 입력해주세요</h3>");
				return false;
			}
		  
		  	if(!$.isNumeric($("#user_birthdate").val())){
				$('#ErrorModal').modal('show');
				$('#ErrorModal-Title').text("회원가입 유효성 검사");
				$('#ErrorModal-body').html("<h6>생년월일은 예시와 같이 숫자를 입력하세요</h6>");
				$("#user_birthdate").val('').focus();
				return false;
			}
			
			if(!checkemail){
				$('#ErrorModal').modal('show');
				$('#ErrorModal-Title').text("회원가입 유효성 검사");
				$('#ErrorModal-body').html("<h4>EMAIL 형식을 확인 하세요</h4>");
				$("#user_email").focus();
				return false;
			}
			
			if($('#user_phone').val() == ''){
				$('#ErrorModal').modal('show');
				$('#ErrorModal-Title').text("회원가입 유효성 검사");
				$('#ErrorModal-body').html("<h4>전화번호를 확인해주세요</h4>");
				$("#user_email").focus();
				return false;
			}
	  });
	  
	$("#user_email").on('keyup',
			function() {
				$("#email_message").empty(); 
				//[A-Za-z0-9_]와 동일한 것이 \w
				//+는 1회이상 반복을 의미합니다. {1,}와 동일합니다.
				//\w+ 는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
				var pattern = /^\w+@\w+[.]\w{3}$/;
				var user_email = $("#user_email").val();
				if (!pattern.test(user_email)) {
					$("#email_message").css('color','red')
									   .html("이메일형식이 맞지않습니다.");
					checkemail=false;
				}else{
					$("#email_message").css('color','green')
					   .html("이메일형식에 맞습니다.");
					checkemail=true;
				}
	});	//email keyup 이벤트 처리 끝
	
	$("#user_id").keyup(function(){
		checkid=true;
		$("#message").empty();//처음에 패턴에 적합하지 않은 경우 메시지 출력후 적합한 데이터를
		//[A-Za-z0-9_]와 동일한 것이 \w
		//+는 1회이상 반복을 의미합니다. {1,}와 동일합니다.
		//\w+ 는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
		var pattern = /^\w{5,12}$/;
		var user_id = $("#user_id").val();
		console.log(user_id);
		if (!pattern.test(user_id)) {
			$("#message").css('color','red')
			             .html("영문자 숫자_로 5~12자 가능합니다.");
			checkid=false;
			return;
		}
		
		$.ajax({
			url :"idcheck.ma",
			data :{"user_id": user_id},
			success : function(resp){
				if (resp == -1){//db에 해당 id가 없는 경우
					$("#message").css('color','green').html(
							"사용 가능한 아이디 입니다.");
					checkid=true;
				} else{//db에 해당 id가 있는 경우(0)
					$("#message").css('color','blue').html(
					"이미 사용중인 아이디 입니다.");
					checkid=false;
				}
			}
		});//ajax end
	})//id keyup en
});