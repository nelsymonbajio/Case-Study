<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
		<title>Update Information</title>
	</head>
<body>
	<div class="wrap">
		<div class="register left">
			<div>
				<h1 align="center">Update Information</h1>
				<br>
			</div>
			<form action="UpdateUser" method="POST">
				<input type="hidden" name="id" value="${userInfo[0].id}">
				<div class="form1">
					<label for="userid">User ID</label>
					<input type="number" class="align-form" name="userid" required value="${userInfo[0].userid}">
				</div>
				<div class="form1">
					<label for="username">Username</label>
					<input type="text" class="align-form" name="username" required value="${userInfo[0].username}">
				</div>
				<div class="form1">
					<label>First name</label>
					<input type="text" class="align-form" name="firstname" required value="${userInfo[0].firstname}">
				</div>
				<div class="form1">
					<label>Middle name</label>
					<input type="text" class="align-form" name="middlename" required value="${userInfo[0].middlename}">
				</div>
				<div class="form1">
					<label>Last name</label>
					<input type="text" class="align-form" name="lastname" required value="${userInfo[0].lastname}">
				</div>
				<div class="form1">
					<c:choose>
					  <c:when test="${userInfo[0].role=='Admin'}">
					    <label>Account Role</label>
						<select name="role">
							<option value="Admin" selected>Admin</option>
							<option value="User">User</option>
						</select>
					  </c:when>
					  <c:otherwise>
						<label>Account Role</label>
						<select name="role">
							<option value="Admin">Admin</option>
							<option value="User" selected>User</option>
						</select>
					  </c:otherwise>
					</c:choose>
				</div>
				<div class="form1">
					<c:choose>
						<c:when test="${userInfo[0].role=='Admin'}">
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${userInfo[0].canCreate==true}">
									<input type="checkbox" name="Create" value="Create" checked="checked">Create
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="Create" value="Create">Create
								</c:otherwise>
							</c:choose> 
							<c:choose>
								<c:when test="${userInfo[0].canUpdate==true}">
									<input type="checkbox" name="Update" value="Update" checked="checked">Update
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="Update" value="Create">Update
								</c:otherwise>
							</c:choose> 
							<c:choose>
								<c:when test="${userInfo[0].canDelete==true}">
									<input type="checkbox" name="Delete" value="Delete" checked="checked">Delete
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="Delete" value="Delete">Delete
								</c:otherwise>
							</c:choose> 
						</c:otherwise>
					</c:choose>
				</div>
				<div class="form1">
					<input class="btn-add" type="submit" name="UpdateUser" value="UPDATE">
					<a href="/ACASESTUDY/Users/" class="btn-delete" style="width:70px !important;">CANCEL</a>
				</div>
			</form>
		</div>
		<div class="register right">
			<div>
				<h1 align="center">Change Password</h1>
				<br>
			</div>
			<form action="UserChangePass" method="POST">
				<div class="form1">
					<label for="oldpass">Old Password</label>
					<input type="password" class="align-form" name="oldpass" required>
				</div>
				<div class="form1">
					<label for="newpass">New Password</label>
					<input type="password" id="password" class="align-form" name="newpass" required>
				</div>
				<div class="form1">
					<label for="newpass">Confirm Password</label>
					<input type="password" id="confirm_password"class="align-form" name="confirmpass" required onkeyup='check();'>
					<span id='message'></span>
				</div>
				<div class="form1">
					<input type="hidden" name="userID" value="${userInfo[0].userid}">
					<input class="btn-add" type="submit" name="ChangePassUser" value="Change Password" required onkeyup='check();'>
					<a href="/ACASESTUDY/Users/" class="btn-delete" style="width:70px !important;">CANCEL</a>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var check = function() {
			if (document.getElementById('password').value == document
					.getElementById('confirm_password').value) {
				document.getElementById('message').style.color = 'green';
				document.getElementById('message').innerHTML = 'matching';
			} else {
				document.getElementById('message').style.color = 'red';
				document.getElementById('message').innerHTML = 'not matching';
			}
		}
	</script>
</body>
</html>