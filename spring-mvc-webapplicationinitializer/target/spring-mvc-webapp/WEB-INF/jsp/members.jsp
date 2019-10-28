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
	<h1>Add a New Member</h1>	
	<form method="post">
		Member Id: <input type="text" name="memberId" value=${memberId} ></input>
		First Name: <input type="text" name="firstName" value=${firstName} ></input>
		Last Name: <input type="text" name="lastName" value=${lastName} ></input>
		<input type="submit" value="Add" />
	</form>

	<table border="1">
		<tr>
			<th>Name</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<c:forEach items="${members}" var="member">
			<tr>
				<td>${member.memberId}</td>
				<td>${member.firstName}</td>
				<td>${member.lastName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>