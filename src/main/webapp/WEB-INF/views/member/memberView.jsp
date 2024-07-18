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
			<form name="viewForm">
			  <!-- 수정 작업 후 현재 페이지 정보 유지 -->
			  <input type="hidden" name="page" value="${pageRequestDTO.page}">
			  
			  <div class="mb-3">
			    <label for="id" class="col-3 form-label">ID</label>
			    <input class="form-control" type="text" id="id" class="col-9 form-text" name="id"
			    	readonly value ="${member.id }"/>
			  </div>
			  
			  <div class="mb-3">
			    <label for="pwd" class="form-label">Password</label>
			    <input type="password" class="form-control" id="pwd" name="pwd" 
			    	value=${member.pwd}>
			    	<!-- readonly="readonly" -->
			    <div id="pwdHelp" class="form-text"></div>
			  </div>
			  
			   <div class="mb-3">
			    <label for="pwd2" class="form-label">Password Check</label>
			    <input type="password" class="form-control" id="pwd2" name="pwd">
			  </div>
			  <div class="mb-3">
			    <label for="name" class="form-label">Name</label>
			    <input type="text" class="form-control" id="name" name="name" value="${member.name }" >
			    <div id="nameHelp" class="form-text"></div>
			  </div>
			  <div class="mb-3">
			    <label for="email" class="form-label">Email address</label>
			    <input type="email" class="form-control" id="email" name="email" 
			    	value="${member.email }">
			    <div id="emailHelp" class="form-text"></div>
			  </div>
			  
			  
			  <div>
			  	<button type="button" class="btn btn-primary btn-sm" id="modify">수정</button>
			  	<button type="button" onclick="deleteItem();" class="btn btn-danger btn-sm" id="delete">삭제</button>
			  	<button type="button" onclick="listItem();" class="btn btn-success btn-sm" id="list">목록</button>
			  </div>
			</form>
		</div>
	</div>
	<jsp:include page="/include/bs_footer.jsp"/>
	<script type="text/javascript">
		var viewForm = docoment/querySelector('#viewForm');
		
		//회원 정보 수정
		//이벤트 : 서브밋 버튼
		viewForm.addEventListener("submit", function(e){
			//하위 이벤트 전이 막기
			e.preventDefault();//전송기능 제거 
			e.stopPropagation();
			
			//유효성 검사후 
			
			viewForm.action = "/member.modify";
			viewForm.method = "post";
			viewForm.submit();
		})
		
		//회원 삭제 처리 기능		
		function deleteItem(){
			console.log("delete");
			
			let isOK = confirm("삭제");
			consol.log("isOK : ", isOK);
			
			if(isOK)
				location.href="/member/remove?id=${member.id}&${pageRequestDTO.link}";
			else 
				return;
		}
		
		//회원 목록 페이지 이동
		function listItem(){
			console.log("list");
			location.href="/member/list?${pageRequestDTO.link}";
		}
	</script>
</body>
</html>