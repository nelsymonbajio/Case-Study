<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
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
		<div>
			<h3>User ID</h3>
			<pre>${userInfo[0].userid}</pre>
			
			<h3>Username</h3>
			<pre>${userInfo[0].username}</pre>
			
			<h3>Name</h3>
			<pre>${userInfo[0].firstname} ${userInfo[0].middlename} ${userInfo[0].lastname}</pre>
			
			<h3>Role</h3>
			<pre>${userInfo[0].role}</pre>
			
			<h3>Privileges</h3>
			<c:if test="${userInfo[0].canCreate==true}">
				<pre>Create</pre>
			</c:if>
			<c:if test="${userInfo[0].canUpdate==true}">
				<pre>Update</pre>
			</c:if>
			<c:if test="${userInfo[0].canDelete==true}">
				<pre>Delete</pre>
			</c:if>
		</div>
		
	</div>
</body>
</html>