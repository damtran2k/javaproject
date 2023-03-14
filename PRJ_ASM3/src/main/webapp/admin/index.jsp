<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
	String userID = (String)session.getAttribute("user");
	out.println("Welcome : "+ userID);
	%>
	<br><br>
	<a href="<%=request.getContextPath()%>/LogoutController">Log out</a>
	<br>
	<a href="<%=request.getContextPath()%>/ListController">CONTINUE SHOPPING</a>
</body>
</html>