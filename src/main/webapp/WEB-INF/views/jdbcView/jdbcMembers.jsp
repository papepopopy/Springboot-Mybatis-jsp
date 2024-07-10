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
		${jdbcMembers}
		<hr><br>
		
		<table class="table  table-bordered">
		<c:forEach var="member"  items="${jdbcMembers}">
			<tr><td>${member.id}</td><td>${member}</td></tr>
		</c:forEach>
		</table>
		
		 
	</div>
</body>
</html>