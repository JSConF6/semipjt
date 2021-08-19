
$(function(){
	// ID저장 기능
	$("#login").click(function(){
		$.ajax({
			url : "login.ma",
			type : "post",
			cache : false,
			success : function(data){
				console.log($.trim(data));
				if($.trim(data) != ''){
					$('#login_id').val(data);
					$('#remember').prop('checked', true);
				}
			}
		})
		$("#loginModal").modal();
	});
	
	// 비밀번호 찾기
	$('#findpassbtn').click(function(){
		$("#loginModal").modal('hide');
		$('#FindPasswordModal').modal('show');
	});
	
	// 로그인 모달에서 회원가입 모달로
	$('#signupbtn').click(function(){
		$("#loginModal").modal('hide');
		$("#signup_modal").modal();
	});
	
	$("#signup").click(function(){
		$("#signup_modal").modal();
	});
	 
	// -----------------------------------  회원탈퇴 유효성 --------------------------------
	  $('#secession').click(function(){
		  $.ajax({
				  type: "post",
				  url:'memberUpdate.ma',
				  dataType: "json",
				  success : function(data){
					  console.log("ajax2"+data.user_password);
					  	  // 패스워드 입력 확인
					  if($('#checkPassword').val() == ""){
						  alert("패스워드를 입력해 주세요.");
						  $('#checkPassword').focus();
						  return;
					  }else if($('#checkPassword').val() !=  data.user_password){
						  alert("비밀번호가 일치하지 않습니다.");
						  return;
					  }else{
						  // 탈퇴
						  var result = confirm("정말 탈퇴 하시겠습니까?");
						  if(result){
							  $('#memberDeleteform').submit();
						  	  }
					  }
				  },
				  error: function(){
								alert("서버 에러");
				  }
		  });// ajax end
	  });// ('#secession').click end

	$("#updateMember").click(function(){
		var user_id = $('.headernavid').val();
		if(user_id == ''){
			$('#headerNavErrorModal').modal('show');
			$('#headerNavErrorModal-Title').text('마이페이지');
			$('#headerNavErrorModal-body').html('<h3>로그인 후 이용해주세요.</h3>');
		}else{
			//정보가져오는 url ajax로 가져오기
			$.ajax({
				type: "post",
				url:'memberUpdate.ma',
				dataType: "json",
				cache : false,
				success : function(data){
					console.log("ajax"+data.user_memberfile);
					// {"user_id":"aaaaa","user_password":"11111","user_name":"admin","user_birthdate":19980101,"user_gender":"남","user_email":"222@hu.com","user_phone":"010-9999-9999","user_address1":"05200","user_address2":"서울 강동구 가래여울길 3","user_memberfile":"logo.gif","user_joindate":"20210817"}
					$('#update_user_id').val(data.user_id);
					$('#update_user_password').val(data.user_password);
					$('#update_user_name').val(data.user_name);
					$('#update_user_birthdate').val(data.user_birthdate);
					 console.log(data.user_gender);
					$("input[name=update_user_gender][value="+data.user_gender+"]").prop("checked",true);
					$('#update_user_email').val(data.user_email);
					$('#update_user_phone').val(data.user_phone);
					$('#update_user_address1').val(data.user_address1);
					$('#update_user_address2').val(data.user_address2);
					if(data.user_memberfile == ''){
						$('#update_filename').text(data.user_memberfile);
						$('#update_filenameSrc').attr('src', 'image/profile.png');
					}else{
						$('#update_filename').text(data.user_memberfile);
						$('#update_filenameSrc').attr('src', '/RocketLaptop/memberupload/'+data.user_memberfile);
					}
				}
			})
			$("#updateMember_modal").modal();  
		}
	});
	  
	var checkid=false;
	var checkemail=false;
	// -----------------------------------  회원가입 유효성 --------------------------------
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
			
		if($.trim($('#user_phone').val()) == ''){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원가입 유효성 검사");
			$('#ErrorModal-body').html("<h4>전화번호를 입력해주세요</h4>");
			return false;
		}
			
		var phoneReg = /^\d{3}-\d{3,4}-\d{4}$/;
		if(!phoneReg.test($.trim($('#user_phone').val()))){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원 정보");
			$('#ErrorModal-body').html("<h5>전화번호 형식에 맞게 입력해주세요</h5>");
			$('#user_phone').val('');
			return false;
		}
			
		if($.trim($('#user_address1').val()) == ''){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원가입 유효성 검사");
			$('#ErrorModal-body').html("<h4>주소검색을 해주세요</h4>");
			return false;
		}
			
	});
	  
	$('#user_memberfile').change(function(event){
		var check = 0;
		var inputfile = $(this).val().split('\\');
		var filename=inputfile[inputfile.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;//플래그 i는 대소문자 구분없는 검색
		
		if(pattern.test(filename)) {
			$('#filename').text(filename);//inputfile.length - 1 =2
				
			var reader = new FileReader();	//파일을 읽기 위한 객체 생성
			//DataURL 형식으로 파일을 읽어옵니다.
			//읽어온 결과는 reader객체의 result 속성에 저장됩니다.
			//event.target.files[0] : 선택한 그림의 파일객체에서 첫번째 객체를 가져옵니다.
			reader.readAsDataURL(event.target.files[0]);
			check++;
			reader.onload = function(event) {//읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#filenameSrc').attr('src',event.target.result);
			};
		}else{
			alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
			check=0;
		}
	})//$('input[type=file]').change() end
	  
	  
	// 우편 검색 버튼 클릭
	$("#postcode").click(function(){
		new daum.Postcode({
			oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 예제를 참고하여 다양한 활용법을 확인해 보세요.
			//console.log(data);
			$("#user_address2").val(data.address);
			$("#user_address1").val(data.zonecode);
					
			}
		}).open();
	});	// $("#postcode").click() end

	  
	$("#user_email").on('keyup',function() {
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
	})//id keyup end
	
	
	// -----------------------------------  회원정보수정 유효성 --------------------------------
	$('#updateform').submit(function(){

		if($("#update_user_password").val() == ''){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원정보수정 유효성 검사");
			$('#ErrorModal-body').html("<h3>비밀번호를 입력해주세요</h3>");
			$('#update_user_password1').val('');
			return false;
		}
			
			
		if($('#update_user_password1').val() != $("#update_user_password").val()){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원정보수정 유효성 검사");
			$('#ErrorModal-body').html("<h3>비밀번호가 다릅니다.</h3>");
			$('#update_user_password1').val('');
			return false;
		}
			
		if($('#update_user_name').val() == ""){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원정보수정 유효성 검사");
			$('#ErrorModal-body').html("<h3>이름을 입력해주세요</h3>");
			return false;
		}
		  
		if(!$.isNumeric($("#update_user_birthdate").val())){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원정보수정 유효성 검사");
			$('#ErrorModal-body').html("<h6>생년월일은 예시와 같이 숫자를 입력하세요</h6>");
			$("#update_user_birthdate").val('').focus();
			return false;
		}
			
		if(!checkemail){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원정보수정 유효성 검사");
			$('#ErrorModal-body').html("<h4>EMAIL 형식을 확인 하세요</h4>");
			$("#update_user_email").focus();
			return false;
		}
			
		if($.trim($('#update_user_phone').val()) == ''){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원정보수정 유효성 검사");
			$('#ErrorModal-body').html("<h4>전화번호를 입력해주세요</h4>");
			return false;
		}
			
		var phoneReg = /^\d{3}-\d{3,4}-\d{4}$/;
		if(!phoneReg.test($.trim($('#update_user_phone').val()))){
			$('#ErrorModal').modal('show');
			$('#ErrorModal-Title').text("회원정보수정 유효성 검사");
			$('#ErrorModal-body').html("<h5>전화번호 형식에 맞게 입력해주세요</h5>");
			$('#update_user_phone').val('');
			return false;
		}
			
	});
	  
	$('#update_user_memberfile').change(function(event){
		var check = 0;
		var inputfile = $(this).val().split('\\');
		var filename=inputfile[inputfile.length - 1];
		var pattern = /(gif|jpg|jpeg|png)$/i;//플래그 i는 대소문자 구분없는 검색
		
		if(pattern.test(filename)) {
			$('#update_filename').text(filename);//inputfile.length - 1 =2
			
			var reader = new FileReader();	//파일을 읽기 위한 객체 생성
			//DataURL 형식으로 파일을 읽어옵니다.
			//읽어온 결과는 reader객체의 result 속성에 저장됩니다.
			//event.target.files[0] : 선택한 그림의 파일객체에서 첫번째 객체를 가져옵니다.
			reader.readAsDataURL(event.target.files[0]);
			check++;
			reader.onload = function(event) {//읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#update_filenameSrc').attr('src',event.target.result);
			};
		}else{
			alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
			check=0;
		}
	})//$('input[type=file]').change() end
	  
	  
	// 우편 검색 버튼 클릭
	$("#update_postcode").click(function(){
		new daum.Postcode({
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
				// 예제를 참고하여 다양한 활용법을 확인해 보세요.
				//console.log(data);
				$("#update_user_address2").val(data.address);
				$("#update_user_address1").val(data.zonecode);
					
			}
		}).open();
	});	// $("#postcode").click() end

	  
	$("#update_user_email").on('keyup', function() {
		$("#update_email_message").empty(); 
		//[A-Za-z0-9_]와 동일한 것이 \w
		//+는 1회이상 반복을 의미합니다. {1,}와 동일합니다.
		//\w+ 는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
		var pattern = /^\w+@\w+[.]\w{3}$/;
		var user_email = $("#update_user_email").val();
		if (!pattern.test(user_email)) {
			$("#update_email_message").css('color','red')
							   .html("이메일형식이 맞지않습니다.");
			checkemail=false;
		}else{
			$("#update_email_message").css('color','green')
			   .html("이메일형식에 맞습니다.");
			checkemail=true;
		}
	});	//email keyup 이벤트 처리 끝
});//document end