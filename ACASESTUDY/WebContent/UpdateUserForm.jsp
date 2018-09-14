<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/profile.css" type="text/css" rel="stylesheet">
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
				<input type="hidden" name="id" value="${userInfo[0].id}"/>
				<div class="form">
					<label for="userid">User ID</label>
					<input type="number" class="align-form" name="userid" required value="${userInfo[0].userid}" maxlength="11"/>
				</div>
				<div class="form">
					<label for="username">Username</label>
					<input type="text" class="align-form" name="username" required value="${userInfo[0].username}" pattern="^[a-zA-Z0-9]*$" maxlength="20"
					oninvalid="this.setCustomValidity('Numbers and Letters Only')"
					oninput="setCustomValidity('')"/>
				</div>
				<div class="form">
					<label>First name</label>
					<input type="text" class="align-form" name="firstname" required value="${userInfo[0].firstname}" pattern="^[a-zA-Z ]+$" maxlength="30"
					oninvalid="this.setCustomValidity('Letters Only')"
					oninput="setCustomValidity('')"/>
				</div>
				<div class="form">
					<label>Middle name</label>
					<input type="text" class="align-form" name="middlename" required value="${userInfo[0].middlename}" pattern="^[a-zA-Z ]+$" maxlength="30"
					oninvalid="this.setCustomValidity('Letters Only')"
					oninput="setCustomValidity('')"/>
				</div>
				<div class="form">
					<label>Last name</label>
					<input type="text" class="align-form" name="lastname" required value="${userInfo[0].lastname}" pattern="^[a-zA-Z ]+$" maxlength="30"
					oninvalid="this.setCustomValidity('Letters Only')"
					oninput="setCustomValidity('')"/>
				</div>
				
				<div class="form">
					<label>Account Role</label>
					<select name="role" id="selectRole" onchange="priv()">
						<c:choose>
						  <c:when test="${userInfo[0].role=='Admin'}">
								<option value="Admin" selected>Admin</option>
								<option value="User">User</option>
						  </c:when>
						  <c:otherwise>
								<option value="Admin">Admin</option>
								<option value="User" selected>User</option>
						  </c:otherwise>
						</c:choose>
					</select>
				</div>
				<div class="form">
					<c:choose>
						<c:when test="${userInfo[0].role=='Admin'}">
							<div id="privDiv">
								<label id="lblCreate" style="display:none;">CREATE</label>
								<input type="checkbox" name="Create" value="Create" checked="checked" id="createPriv" style="display:none;"/>
								<label id="lblUpdate" style="display:none;">UPDATE</label>
								<input type="checkbox" name="Update" value="Update" checked="checked" id="updatePriv" style="display:none;"/>
								<label id="lblDelete" style="display:none;">DELETE</label>
								<input type="checkbox" name="Delete" value="Delete" checked="checked" id="deletePriv" style="display:none;"/>
							</div>
						</c:when>
						<c:otherwise>
							<div id="privDiv">
								<c:choose>
									<c:when test="${userInfo[0].canCreate==true}">
										<label id="lblCreate">CREATE</label>
										<input type="checkbox" name="Create" value="Create" checked="checked" id="createPriv"/>
									</c:when>
									<c:otherwise>
										<label id="lblCreate">CREATE</label>
										<input type="checkbox" name="Create" value="Create" id="createPriv"/>
									</c:otherwise>
								</c:choose> 
								<c:choose>
									<c:when test="${userInfo[0].canUpdate==true}">
										<label id="lblUpdate">UPDATE</label>
										<input type="checkbox" name="Update" value="Update" checked="checked" id="updatePriv"/>
									</c:when>
									<c:otherwise>
										<label id="lblUpdate">UPDATE</label>
										<input type="checkbox" name="Update" value="Create" id="updatePriv"/>
									</c:otherwise>
								</c:choose> 
								<c:choose>
									<c:when test="${userInfo[0].canDelete==true}">
										<label id="lblDelete">DELETE</label>
										<input type="checkbox" name="Delete" value="Delete" checked="checked" id="deletePriv"/>
									</c:when>
									<c:otherwise>
										<label id="lblDelete">DELETE</label>
										<input type="checkbox" name="Delete" value="Delete" id="deletePriv"/>
									</c:otherwise>
								</c:choose> 
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-50">
					<input type="hidden" name="u" value="${userInfo[0].username}">
					<input class="btn-update" type="submit" name="UpdateUser" value="UPDATE" onclick="return confirm('Are you sure?')" style="width:170px !important;"/>
				</div>		
				<div class="col-50">
					<a href="${pageContext.request.contextPath}/Users/" class="btn-delete" style="width:90px !important;">CANCEL</a>
				</div>		
			</form>
		</div>
		<div class="register right">
		
			<h1 align="center">Change Password</h1>
			<br>
			<form action="UserChangePass" method="POST" name="changePassForm" onsubmit="return validateChangepass()">
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
					<input class="btn-update" type="submit" name="ChangePassUser" value="Change Password" required onkeyup='check();' style="width:170px !important;"/>
				</div>	
				<div class="col-50">
					<a href="${pageContext.request.contextPath}/Users/" class="btn-delete" style="width:90px !important;">CANCEL</a>
				</div>
			</form>
			<br>
			<br>
			<br>
			<form action="ResetUser" method="POST" onsubmit="return confirm('Are you sure you want to reset this account?')">
				<h1 align="center">Reset Account Password</h1>
				<input type="hidden" name="userID" value="${userInfo[0].userid}"/>
				<input type="submit" value="RESET ACCOUNT" class="btn-delete" style="width:170px !important;"/>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/UpdateUser.js"></script>
</body>
</html>