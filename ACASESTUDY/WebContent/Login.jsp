<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
<title>Login</title>
</head>
<body>
	<form action="Login" method="POST">
		<div class="login-wrap">
			<h2 class="login-header">LOGIN</h2>
			<label for="username"><b>Username</b></label> 
			<input type="text" name="username" placeholder="Type Username" required> 
			<label for="password"><b>Password</b></label> 
			<input type="password" name="password" placeholder="Type Password" required>
			<input type="submit" value="SUBMIT" class="btn-login">
		</div>
	</form>
</body>
</html>