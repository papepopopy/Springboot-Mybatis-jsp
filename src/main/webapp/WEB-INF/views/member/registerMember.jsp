<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<%@ include file="/include/bs_header.jsp" %>

</head>
<body>
	<div class="container">
		<h1>회원 가입</h1>
		<hr>
		<div>
			<form name="memberForm">
			  <div class="row  align-items-center  mb-3">
				  <div class=" col-10">
				    <label for="id" 	class="form-label">ID</label>
				    <input type="text" 	class="form-control" id="id" name="id" >
				    <div id="id_msg" 	class="form-text">4자이상 숫자와 영문자 혼영 첫자는 영문</div>
				  </div>
				  <div class="col-2">
				  	<input type="button" id="idCheck" class="btn btn-info btn-sm"  value="ID Check" />
				  </div>
			  </div>
			  
			  <div class="mb-3">
			    <label for="email" class="form-label">Email address</label>
			    <input type="email" class="form-control" id="email" name="email" >
			    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
			  </div>
			  <div class="mb-3">
			    <label for="pwd" class="form-label">Password</label>
			    <input type="password" class="form-control" id="pwd" name="pwd">
			  </div>
			  <div class="mb-3 form-check">
			    <input type="checkbox" class="form-check-input" id="remember " name="remember">
			    <label class="form-check-label" for="remember">remember me</label>
			  </div>
			  <div>
			  	<button type="submit" class="btn btn-primary" id="send">Submit</button>
			  </div>
			</form>			
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<jsp:include page="/include/bs_footer.jsp" /><!--  jquery 연결 -->
	<script type="text/javascript">
		$('#id').focus(function(){
			$('#message').text("");
		})
	
		//중복 체크 버튼
		$('#idCheck').click(function(){
			
			let id_check = $('#id').val();
			console.log('id: ', id)
			//서버에 전송 중복체크(등록여부확인)
			$.ajax({
				type: 'post',
				url: '/member/idcheck',
				async: false, 
				data: {id : id_check},
				dataType: 'text',
				
				success: function(data, textStatus){
					console.log("success...");
					console.log(data, textStatus);
					if (data==true) {
						$('#message').text("사용중인 아이디입니다.");
					} else {
						$('#message').text("사용 가능한 아이디입니다.");
					}
				},
				error: function(){
					console.log("error...");
				},
				complete: function(){
					console.log("complete...");
				}
			});
		}) //end idcheck()
		
		//전송 버튼 클릭 시
		$('#send').click(function(){
			e.preventDefault();
			
			//유효성 체크
			let id = $('#id').val();
			if(id.length==0 || id == '') {
				alert('4자리 이상 입력하세요');
				$('#id').focus;
				
				return fales;
			}
			
			//제이쿼리
			//action="/member/insert" method="post"
			$('#memberForm').attr('action', '/member/insert');
			$('#memberForm').attr('method', 'post');
			
			$('#memberForm').submit();
			
			
			
		})
		
		
	</script>
</body>
</html>