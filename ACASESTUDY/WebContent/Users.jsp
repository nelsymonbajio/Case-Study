<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript" ></script>
	<title>Users</title>
</head>

<body>
	<jsp:include page="mainHeader.jsp"/>
	<div class="mainDiv">
		<div>
			<h2>USERS</h2>
			<div>
				<a href="AddUserForm" class="btn-add" style="float:right;">ADD USER</a>
				<br>
			</div>
		</div>
		<br><br>
		<table id="usersTable">
			<thead>
				<tr>
					<th>ID</th>
					<th>UserID</th>
					<th>Username</th>
					<th>First Name</th>
					<th>Middle Name</th>
					<th>Last Name</th>
					<th>Role</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
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
						<td><a href="DeleteUser?u=${fn:escapeXml(u.username)}" class="btn-delete" onclick="return confirm('Are you sure?')">DELETE</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script src="${pageContext.request.contextPath}/js/tableSearch.js"></script>
</body>
</html>