<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Create User</title>
		<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
	</head>
<body>
	<div class="container register">
		<div class="container">
			<h1 align="center">REGISTRATION</h1>
			<br>
		</div>
		<form action="Users/RegisterUser" method="POST">
			<div class="form-group">
				<label for="userid">User ID</label>
				<input type="number" class="form-control" name="userid" required placeholder="Type Userid">
			</div>
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" class="form-control" name="username" required placeholder="Type Username">
			</div>
			<div class="form-group">
				<label>First name</label>
				<input type="text" class="form-control" name="firstname" required placeholder="Type Firstname">
			</div>
			<div class="form-group">
				<label>Middle name</label>
				<input type="text" class="form-control" name="middlename" required placeholder="Type Middlename">
			</div>
			<div class="form-group">
				<label>Last name</label>
				<input type="text" class="form-control" name="lastname" required placeholder="Type Lastname">
			</div>
			<div class="form-group">
			<label>Account Role</label>
				<select name="role">
					<option value="Admin">Admin</option>
					<option value="User">User</option>
				</select>
			</div>
			<input class="btn btn-info" type="submit" name="AddUser" value="REGISTER">
			<a href="/ACASESTUDY/Users/" class="btn btn-danger">CANCEL</a>
		</form>
	</div>
</body>
</html>