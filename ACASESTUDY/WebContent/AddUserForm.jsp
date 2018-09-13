<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Create User</title>
		<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/profile.css" type="text/css" rel="stylesheet">
	</head>
<body>
	<div class="register" style="margin: 0 auto;">
	
		<h1 align="center">REGISTRATION</h1>
		
		<form action="Users/RegisterUser" method="POST">
			<div class="form">
				<label for="userid">User ID</label>
				<input type="number" class="align-form" name="userid" required placeholder="Type Userid" maxlength="11"/>
			</div>
			<div class="form">
				<label for="username">Username</label>
				<input type="text" class="align-form" name="username" required placeholder="Type Username" pattern="^[a-zA-Z0-9]*$" maxlength="20"
				oninvalid="this.setCustomValidity('Numbers and Letters Only')"
				oninput="setCustomValidity('')"/>
			</div>
			<div class="form">
				<label>First name</label>
				<input type="text" class="align-form" name="firstname" required placeholder="Type Firstname" pattern="^[a-zA-Z ]+$" maxlength="30"
				oninvalid="this.setCustomValidity('Letters Only')"
				oninput="setCustomValidity('')"/>
			</div>
			<div class="form">
				<label>Middle name</label>
				<input type="text" class="align-form" name="middlename" required placeholder="Type Middlename" pattern="^[a-zA-Z ]+$" maxlength="30"
				oninvalid="this.setCustomValidity('Letters Only')"
				oninput="setCustomValidity('')"/>
			</div>
			<div class="form">
				<label>Last name</label>
				<input type="text" class="align-form" name="lastname" required placeholder="Type Lastname" pattern="^[a-zA-Z ]+$" maxlength="30"
				oninvalid="this.setCustomValidity('Letters Only')"
				oninput="setCustomValidity('')"/>
			</div>
			<div class="form">
				<label>Account Role</label>
				<select name="role" id="selectRole" onchange="priv()">
					<option value="User">User</option>
					<option value="Admin">Admin</option>
				</select>
			</div>
			<div class="form" id="privDiv">
				<label for="Create">CREATE</label>
				<input type="checkbox" name="Create" value="Create" id="createPriv"/>
				<label for="Create">UPDATE</label>
				<input type="checkbox" name="Update" value="Update" id="updatePriv"/>
				<label for="Create">DELETE</label> 
				<input type="checkbox" name="Delete" value="Delete" id="deletePriv"/><br>
			</div>
			<div class="col-50">
				<input class="btn-update" type="submit" name="AddUser" value="REGISTER" style="width:170px !important;"/>
			</div>
			<div class="col-50">
				<a href="${pageContext.request.contextPath}/Users/" class="btn-delete" style="width:90px !important;">CANCEL</a>
			</div>
		</form>
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/UpdateUser.js"></script>
</body>
</html>