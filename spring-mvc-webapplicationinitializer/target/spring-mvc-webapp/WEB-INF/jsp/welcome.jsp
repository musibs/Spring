<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<h3>Welcome to Court Reservation Application</h3>
	<p>Today is :  <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/> </p>
	<hr />
	<p>Time took for processing:"${handlingTime}"</p>
</body>
</html>