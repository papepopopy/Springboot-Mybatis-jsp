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
		<h1>회원 목록 조회 </h1>
		<hr>
		<div>
			${members }
		</div>
	</div>
</body>
</html>