<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css">
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
	</div>
</body>
</html>