<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
	<title>Users</title>
</head>

<body>
	<jsp:include page="mainHeader.jsp"/>
	<div class="mainDiv">
		<div>
			<div><h2>USERS</h2></div>
			<div><a href="AddUserForm" class="btn-add" style="float:right;">ADD USER</a></div>
		</div>
		<br><br>
		<table>
			<tr>
				<th>ID</th>
				<th>UserID</th>
				<th>Username</th>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Role</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach var="u" items="${usersList}">
			<tr>
				<td>${fn:escapeXml(u.id)}</td>
				<td>${fn:escapeXml(u.userid)}</td>
				<td>${fn:escapeXml(u.username)}</td>
				<td>${fn:escapeXml(u.firstname)}</td>
				<td>${fn:escapeXml(u.middlename)}</td>
				<td>${fn:escapeXml(u.lastname)}</td>
				<td>${fn:escapeXml(u.role)}</td>
				<td><a href="UpdateUserForm?u=${fn:escapeXml(u.username)}" class="btn-update">UPDATE</a></td>
				<td><a href="DeleteUser?u=${fn:escapeXml(u.username)}" class="btn-delete">DELETE</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>