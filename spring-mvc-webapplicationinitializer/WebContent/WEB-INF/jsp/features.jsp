<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Features Details</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>Feature Id</th>
			<th>Feature Name</th>
			<th>Feature Description</th>
			<th>Feature Release Version</th>
			<th>Feature Developers</th>
		</tr>
		<c:forEach items="${features.listOfFeatures}" var="feature">
			<tr>
				<td>${feature.featureId}</td>
				<td>${feature.featureName}</td>
				<td>${feature.featureDescription}</td>
				<td>${feature.releaseVersion}</td>
				<td>
					<c:forEach items="${feature.featureDevelopers}" var="developer">
						${developer}
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
	<h1>${token}</h1>
</body>
</html>