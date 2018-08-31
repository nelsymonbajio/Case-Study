<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>New Product</title>
	<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="register" style="margin: 0 auto;">
		<div class="container">
			<h1 align="center">New Product</h1>
			<br>
		</div>
		<form action="Products/AddProduct" method="POST">
			<div class="form1">
				<label for="userid">User ID</label>
				<input type="number" class="align-form" name="userid" required placeholder="Type Userid">
			</div>
			<div class="form1">
				<label for="username">Username</label>
				<input type="text" class="align-form" name="username" required placeholder="Type Username">
			</div>
			<div class="form1">
				<label>First name</label>
				<input type="text" class="align-form" name="firstname" required placeholder="Type Firstname">
			</div>
			<div class="form1">
				<label>Middle name</label>
				<input type="text" class="align-form" name="middlename" required placeholder="Type Middlename">
			</div>
			<div class="form1">
				<label>Last name</label>
				<input type="text" class="align-form" name="lastname" required placeholder="Type Lastname">
			</div>
			<div class="form1">
				<input type="checkbox" name="Create" value="Create">Create
				<input type="checkbox" name="Update" value="Update">Update 
				<input type="checkbox" name="Delete" value="Delete">Delete<br>
			</div>
			<div class="form1">
			<label>Account Role</label>
				<select name="role">
					<option value="Admin">Admin</option>
					<option value="User">User</option>
				</select>
			</div>
			<input class="btn-add" type="submit" name="AddUser" value="REGISTER">
			<a href="/ACASESTUDY/Users/" class="btn-delete">CANCEL</a>
		</form>
	</div>
</body>
</html>