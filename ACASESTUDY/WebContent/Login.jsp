<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<input type="text" name="username" placeholder="Type Username" required/> 
			<label for="password"><b>Password</b></label> 
			<input type="password" name="password" placeholder="Type Password" required/>
			<input type="submit" value="SUBMIT" class="btn-login"/>
		</div>
	</form>
</body>
</html>