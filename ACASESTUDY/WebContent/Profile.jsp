<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet" type="text/css">
	<title>Profile</title>
</head>	
<body>
	<c:choose>
		<c:when test="${sessionScope.accessLevel=='admin'}">
			<jsp:include page="mainHeader.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="userHeader.jsp" />
		</c:otherwise>
	</c:choose>
	<div class="mainDiv">
		<h2>Profile Information</h2>
		<br>
			<div class="col-25">
				<label for="userid"><b>User ID</b></label>
				<br>
			</div>
			<div class="col-75">
				<input type="text" name="userid" value="${userInfo[0].userid}" disabled>
			</div>
			<div class="col-25">
				<label for="username"><b>Username</b></label>
			</div>
			<div class="col-75">
				<input type="text" name="username" value="${userInfo[0].username}" disabled>
			</div>
			<div class="col-25">
				<label><b>Name</b></label>
			</div>
			<div class="col-75">
				<input type="text" value="${userInfo[0].firstname} ${userInfo[0].middlename} ${userInfo[0].lastname}" disabled>
			</div>
			<div class="col-25">
				<label><b>Role</b></label>
			</div>
			<div class="col-75">
				<input type="text" value="${userInfo[0].role}" disabled>
			</div>
			<div class="col-25">
				<label><b>Privileges</b></label>
			</div>
			<div class="col-75">
				<c:if test="${userInfo[0].canCreate==true}">
					<input type="text" value="Create" disabled>
				</c:if>
				<c:if test="${userInfo[0].canUpdate==true}">
					<input type="text" value="Update" disabled>
				</c:if>
				<c:if test="${userInfo[0].canDelete==true}">
					<input type="text" value="Delete" disabled>
				</c:if>
				<c:if test="${userInfo[0].canCreate==false && userInfo[0].canUpdate==false && userInfo[0].canDelete==false}">
					<input type="text" value="No Privilege" disabled>
				</c:if>
			</div>
			<div class="col-90">
			</div>
			<div class="col-10">
				<a href="UpdateProfile" class="btn-update" style="width:200px !important;">UPDATE INFORMATION</a>
			</div>
		
	</div>
</body>
</html>