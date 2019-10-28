<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ReservationQuery</title>
</head>
<body>
	<h1>Member</h1>


	<table border="1">
		<tr>
			<th>Name</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<tr>
			<td>${member.memberId}</td>
			<td>${member.firstName}</td>
			<td>${member.lastName}</td>
		</tr>
	</table>
	<h1>${token}</h1>
</body>
</html>