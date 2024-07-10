<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

   
<% String name = "홍길동"; %>
<c:set var="name" value="<%=name %>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP View Page 인식하기 테스트</title>
</head>
<body>
	<div>
	<% out.println("Hello SpringBoot JSP View !!!!");%>
	</div>
	<hr>
	<div><%= name %></div>
	<hr>
	<div> ${name}님 안녕하세요!!</div>

</body>
</html>