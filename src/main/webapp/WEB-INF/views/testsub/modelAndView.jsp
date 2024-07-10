<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/include/bs_header.jsp" %>
</head>
<body>
	<div class="container">
		<div class="alert alert-info">
		거주지 : ${address }
		</div>
		<hr>
		<table class="table  table-bordered">
		<c:forEach var="name"  items="${names }">
			<tr><td>${name }</td></tr>
		</c:forEach>
		</table>
		
	</div>
</body>
</html>