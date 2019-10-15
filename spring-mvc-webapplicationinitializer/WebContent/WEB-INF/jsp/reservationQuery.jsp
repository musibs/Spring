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
	<form method="post">
		Court Name: <input type="text" name="courtName" value=${courtName} ></input>
		<input type="submit" value="Query" />
	</form>

	<table border="1">
		<tr>
			<th>Court Name</th>
			<th>Date</th>
			<th>Hour</th>
			<th>Player</th>
		</tr>
		<c:forEach items="${reservations}" var="reservation">
			<tr>
				<td>${reservation.courtName}</td>
				<td><fmt:formatDate value="${reservation.date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${reservation.hour}</td>
				<td>${reservation.player.name}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>