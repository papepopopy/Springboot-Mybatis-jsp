<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 연동 JSP Page</title>
<!-- 인크루드 -->
<%-- <%@ include file="/include/bs_header.jsp" %> --%>
<jsp:include page="/include/bs_header.jsp"/>
</head>
<body>
	<div class="container">
 	<div>현재 페이지에서 표시할 회원 정보 수 : ${pageResponseDTO.memberList.size() }</div>
	<div>${pageResponseDTO.total }</div>
	<div>${pageResponseDTO.memberList }</div>
		<h1>회원 목록 조회 </h1>
		<!-- 검색 기능 -->
		<form action="/member/list" method="get" od ="searchForm">
			<div><input type="hidden" name="page" vlaue="${num }"/></div>
			<div>
				
			</div>
			<div>
				<button type="button" name="submit" class="btn"></button>
				<button type="button" name="rest" class="btn">clear</button>
			</div>
		</form>
		
		<hr>
		<div>
			<%-- ${members } --%>
			<table class="table table-bolder mt-4">
				<thead>
					<tr>
						<th scope="col">NO</th>
						<th scope="col">ID</th>
						<th scope="col">이름</th>
						<th scope="col">이메일</th>
						<th scope="col">등록일자</th>
					</tr>
				</thead>
				<tbody>
					<%-- start 반복구간 --%>
					<%-- <c:forEach var="member" items="${ members}"> --%>
					<c:forEach var="member" items="${ pageResponseDTO.memberList}">
					
					<tr>
						<td scope="row">${member.recnum }</td>
						<td scope="row">
							<a href="/member/view?id=${member.id }"  
								class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
								${member.id }
							</a>
						</td>
						<td scope="row">${member.name }</td>
						<td scope="row">${member.email }</td>
						<td scope="row">
							<fmt:formatDate value= "${member.joinDate }" pattern="yyyy.MM.dd HH:mm:ss"/>
						</td>
					</tr>
					<%-- end 반복구간 --%>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="d-flex justify-content-between mt-5">
			<div>
				<button type="button" class="btn btn-success" onclick="register()">회원가입</button>
			</div>
			<br>
			<div>
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  	<!-- 첫번째 블럭(1이상 => 비활성화) -->
				  	<c:if test="${pageResponseDTO.prev }">
					    <li class="page-item">
					      <a class="page-link" data-num="${pageResponseDTO.start-1 }" 
					      	aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:if>
				    
			    	<!-- 해당 블럭의 시작과 마지막 번호 -->
			    	<%-- <c:forEach begin="1" end="10" var="i"> --%>
			    	<c:forEach begin="${pageResponseDTO.start}" 
			    	end="${pageResponseDTO.end" var = "i"}>
			    		<!-- 현재 데이터 -->
			    		<li class="page-item ${pageResponseDTO.page == i ? 'active' : ''}">
			    			<a class="page-link" href="#" data-num=${i }>${i }</a>
			    		</li>		
			    	</c:forEach>
			    	
			    	<!-- 마지막번째 블럭(1이상 => 비활성화) -->
			    	<c:if test="${pageResponcseDTO.next }">
					    <li class="page-item">
					      <a class="page-link" href="#" data-num="${pageResponseDTO.next+1 }">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:if>
				    
				  </ul>
				</nav>
			</div>
		</div>
		
	</div>
	<jsp:include page="/include/bs_footer.jsp">
	<script type="text/javascript">
		$(".pagination a").click(function(){
			e.preventDefault();
			e.stopProgation();
			
			let page_num = $(this).attr("data-num");
			console.log("요청한 페이지  : ", data-num)
			
			if(${pageResponseDTO.page} != page_num){
				//location.href="/member/list?page="+page_num;
				//const formObj = document.querySelector("form")
				
				//JQery
				const formObj = ${"#seachForm"};
				formObj.innerHTML += '<input type="hidden" name="page" value="'+page_num+'"/>';
				formObj.submit();
				
			} else{return;}
		});
		function register() {
			console.log("click")
			location.href="/member/registerMember";
		}
		//javascript
		document.querySelector(".pagination").addEventListener("click",, (e) => {
			e.prevateDefault();
			e.stopPropagetion();
			
			const target = e.target;
			if(target.tagName !== "A")return;

			const num = target.getAttribute("date-num");
			const formObj = document.querySelector("#searchForm");
			formObj.innerHTML += '<input type="hidden" name="page" value="'+page_num+'"/>';
			/* formObj.innerHTML += `<input type="hidden" name="page" value="${page_num}"/>`; */
			
			formObj.submit();
		})
	</script>
</body>
</html>