<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/profile.css" type="text/css" rel="stylesheet">
	<title>Update Profile</title>
</head>
<body>
	<div class="wrap">
		<div class="register left">
			<div>
				<h1 align="center">Update Information</h1>
				<br>
			</div>
			<form action="Updated" method="POST">
				<input type="hidden" name="id" value="${userInfo[0].id}"/>
				<div class="form">
					<label for="userid">User ID</label>
					<input type="number" class="align-form" name="userid" required value="${userInfo[0].userid}" maxlength="11"/>
				</div>
				<div class="form">
					<label for="username">Username</label>
					<input type="text" class="align-form" name="username" required value="${userInfo[0].username}" pattern="^[a-zA-Z0-9]*$" maxlength="20"/>
				</div>
				<div class="form">
					<label>First name</label>
					<input type="text" class="align-form" name="firstname" required value="${userInfo[0].firstname}" pattern="^[a-zA-Z ]+$" maxlength="30"/>
				</div>
				<div class="form">
					<label>Middle name</label>
					<input type="text" class="align-form" name="middlename" required value="${userInfo[0].middlename}" pattern="^[a-zA-Z ]+$" maxlength="30"/>
				</div>
				<div class="form">
					<label>Last name</label>
					<input type="text" class="align-form" name="lastname" required value="${userInfo[0].lastname}" pattern="^[a-zA-Z ]+$" maxlength="30"/>
				</div>
				
				<div class="form">
					<label>Account Role</label>
						<c:choose>
							<c:when test="${userInfo[0].role=='Admin'}">
								<select name="role" id="selectRole" onchange="priv()">
									<option value="Admin" selected>Admin</option>
									<option value="User">User</option>
								</select>
						 	</c:when>
						  	<c:otherwise>
							  	<select name="role" id="selectRole" disabled>
									<option value="Admin">Admin</option>
									<option value="User" selected>User</option>
								</select>
						 	</c:otherwise>
						</c:choose>
				</div>
				
				<div class="form">
					<c:choose>
						<c:when test="${userInfo[0].role=='Admin'}">
							<div id="privDiv">
								<label id="lblCreate" style="display:none;">Create</label>
								<input type="checkbox" name="Create" value="Create" checked="checked" id="createPriv" style="display:none;"/>
								<label id="lblUpdate" style="display:none;">Update</label>
								<input type="checkbox" name="Update" value="Update" checked="checked" id="updatePriv" style="display:none;"/>
								<label id="lblDelete" style="display:none;">Delete</label>
								<input type="checkbox" name="Delete" value="Delete" checked="checked" id="deletePriv" style="display:none;"/>
							</div>
						</c:when>
						<c:otherwise>
							<div id="privDiv">
								<c:choose>
									<c:when test="${userInfo[0].canCreate==true}">
										<label id="lblCreate">Create</label>
										<input type="checkbox" name="Create" value="Create" checked="checked" id="createPriv" disabled/>
									</c:when>
									<c:otherwise>
										<label id="lblCreate">Create</label>
										<input type="checkbox" name="Create" value="Create" id="createPriv" disabled/>
									</c:otherwise>
								</c:choose> 
								<c:choose>
									<c:when test="${userInfo[0].canUpdate==true}">
										<label id="lblUpdate">Update</label>
										<input type="checkbox" name="Update" value="Update" checked="checked" id="updatePriv" disabled/>
									</c:when>
									<c:otherwise>
										<label id="lblUpdate">Update</label>
										<input type="checkbox" name="Update" value="Create" id="updatePriv" disabled/>
									</c:otherwise>
								</c:choose> 
								<c:choose>
									<c:when test="${userInfo[0].canDelete==true}">
										<label id="lblDelete">Delete</label>
										<input type="checkbox" name="Delete" value="Delete" checked="checked" id="deletePriv" disabled/>
									</c:when>
									<c:otherwise>
										<label id="lblDelete">Delete</label>
										<input type="checkbox" name="Delete" value="Delete" id="deletePriv" disabled/>
									</c:otherwise>
								</c:choose> 
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-50">
					<input class="btn-update" type="submit" name="UpdateProfile" value="UPDATE" style="width:170px !important;"/>
				</div>
				<div class="col-50">
					<a href="${pageContext.request.contextPath}/Profile/" class="btn-delete" style="width:90px !important;">CANCEL</a>
				</div>
				
			</form>
		</div>
		<div class="register right">
			<div>
				<h1 align="center">Change Password</h1>
				<br>
			</div>
			<form action="ChangePassword" method="POST" name="changePassForm" onsubmit="return validateChangepass()">
				<div class="form">
					<label for="oldpass">Old Password</label>
					<input type="password" class="align-form" name="oldpass" required maxlength="16"/>
				</div>
				<div class="form">
					<label for="newpass">New Password</label>
					<input type="password" id="password" class="align-form" name="newpass" required maxlength="16" pattern="^[a-zA-Z0-9]*$"/>
				</div>
				<div class="form">
					<label for="newpass">Confirm Password</label>
					<input type="password" id="confirm_password"class="align-form" name="confirmpass" required onkeyup='check();' maxlength="16" pattern="^[a-zA-Z0-9]*$"/>
					<span id='message'></span>
				</div>
				<div class="col-50">
					<input type="hidden" name="userID" value="${userInfo[0].userid}">
					<input class="btn-update" type="submit" name="ChangePassUser" value="CHANGE PASSWORD" required onkeyup='check();'/>
				</div>
				<div class="col-50">
					<a href="${pageContext.request.contextPath}/Profile/" class="btn-delete" style="width:90px !important;">CANCEL</a>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/UpdateUser.js"></script>
</body>
</html>