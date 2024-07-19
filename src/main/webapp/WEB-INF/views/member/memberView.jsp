<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 연동 JSP Page</title>
<%@ include file="/include/bs_header.jsp" %>

</head>
<body>
	<div class="container">
		<h1>회원 정보</h1>
		<hr>
		<div>
			<form  id="viewForm" >
			
			  <!-- 추가: 수정 작업후 현재 페이지 정보 유지: POST방식 pageRequestDTO.page값을 적용 -->
			  <input type="hidden" name="page" value="${pageRequestDTO.page}">
			  
			  <div class="row mb-3">
			    <label for="id" class="col-1 form-label">ID</label>
			    <input type="text" class="form-control" id="id" name="searchid" 	
			    		readonly
			    		value="${member.id}">
			  </div>			

			  <div class="mb-3">
			    <label for="pwd" class="form-label">Password</label>
			    <input type="password" class="form-control" id="pwd" name="pwd" 	
			    		value="${member.pwd}">
			    <div id="pwdHelp" class="form-text"></div>
			  </div>
		  
  			  <div class="mb-3">
			    <label for="name" class="form-label">Name</label>
			    <input type="text" class="form-control" id="name" name="searchname" 	
			    		value="${member.name}">
			    <div id="nameHelp" class="form-text"></div>
			  </div>
			  <div class="mb-3">
			    <label for="email" class="form-label">Email address</label>
			    <input type="email" class="form-control" id="email" name="email" 
			    		value="${member.email}" >
			    <div id="emailHelp" class="form-text"></div>
			  </div>

			  <div>

		  		<button type="submit" class="btn btn-primary btn-sm"  	
		  				 id="modify">수정</button>
			  	<button type="button" class="btn btn-danger btn-sm"  
			  			onClick="del();"	>삭제</button>
			  	<button type="button" class="btn btn-success btn-sm" 
			  			 onclick="list();" >목록</button>

			  </div>
			</form>					
		</div>
	</div>
	
<jsp:include page="/include/bs_footer.jsp" />
<script type="text/javascript">
	var viewForm = document.querySelector('#viewForm');
	
	// javascript Event처리
	// 회원 정보 수정
	viewForm.addEventListener("submit", function(e){
		e.preventDefault();// 기본 이벤트 제거(전송기능)
		e.stopPropagation();

		console.log("submit...")
		// 유효성 검사
		
		
		viewForm.action = "/member/modify";
		viewForm.method = "post";
		viewForm.submit();
	});
	// 회원 삭제 기능 처리
	function del(){
		console.log("del...");
		
		let isOK = confirm("${member.id}님 정보 삭제하시겠습니까?");
		console.log("isOkL",isOK);
		
		if (isOK)
			//location.href="/member/remove?id=${member.id}";
			//현재 페이정보 유지: GET방식 pageRequestDTO.link값을 적용 
			location.href="/member/remove?id=${member.id}&${pageRequestDTO.link}";
		else 
			return;
		
	}
	// 회원목록 페이지 이동
	function list(){
		console.log("list...");
		//location.href="/member/list";
		
		
		//location.href="/member/list";
		//현재 페이정보 유지: GET방식 pageRequestDTO.link값을 적용 
		location.href="/member/list?${pageRequestDTO.link}";
	}
	
</script>

</body>
</html>