<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
	<title>Users</title>
</head>
<body>
	<nav>
	<ul>
		<li><a href="/ACASESTUDY/AdminMain.jsp">HOME</a></li>
		<li><a href="/ACASESTUDY/Products/">PRODUCTS</a></li>
		<li><a href="/ACASESTUDY/Users/">USERS</a></li>
		<li><a href="Profile.jsp">PROFILE</a></li>
		<li><a href="/ACASESTUDY/Login/Logout">LOGOUT</a></li>
	</ul>
	</nav>
	<div style="width:800px; margin:0 auto;">
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
				<td>${u.id}</td>
				<td>${u.userid}</td>
				<td>${u.username}</td>
				<td>${u.firstname}</td>
				<td>${u.middlename}</td>
				<td>${u.lastname}</td>
				<td>${u.role}</td>
				<td><a href="UpdateUserForm?u=${u.username}" class="btn-update">Update</a></td>
				<td><a href="DeleteUser?u=${u.username}" class="btn-delete">Delete</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>