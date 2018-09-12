<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript" ></script>
	
	<title>Products Page</title>
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
		<div>
			<div>
				<h2>PRODUCTS</h2>
			</div>
			<c:if test="${sessionScope.createPriv==true}">
				<a href="AddProductForm" class="btn-add" style="float: right;">ADD PRODUCT</a>
				<br>
			</c:if>
		</div>
		<br>
		<br>
		<div class="product-tbl">
			<table id="productTable" class="display">
			<thead>
				<tr>
					<th>ID</th>
					<th>ProdCode</th>
					<th>Name</th>
					<th>Type</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Expiration Date</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
		
				<c:forEach var="p" items="${prodList}">
					<tr>
						<td>${p.prodid}</td>
						<td>${p.prodCode}</td>
						<td>${p.name}</td>
						<td>${p.productType}</td>
						<td>${p.qty}</td>
						<td>${p.price}</td>
						<c:choose>
							<c:when test="${empty p.expiryDate}">
								<td align="center">N/A</td>
							</c:when>
							<c:otherwise>
								<td align="center">${p.expiryDate}</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.updatePriv==true}">
								<td><a href="UpdateProductForm?p=${p.prodid}"
									class="btn-update">UPDATE</a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.deletePriv==true}">
								<td><a href="DeleteProduct?p=${p.prodid}"
									class="btn-delete" onclick="return confirm('Are you sure?')">DELETE</a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>

					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</div>
<script src="${pageContext.request.contextPath}/js/tableSearch.js"></script>
</body>
</html>