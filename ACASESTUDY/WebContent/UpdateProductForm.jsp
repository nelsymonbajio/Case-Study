<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Update Product</title>
		<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">
	</head>
<body>
	<div class="mainDiv">
		<div class="container">
			<h1 align="center">Update Product</h1>
			<br>
		</div>
		<form action="UpdateProduct" method="POST">
			<input type="hidden" name="prodid" value="${prodInfo[0].prodid}"/>
			<div class="col-25">
				<label for=prodcode>Product Code</label>
			</div>
			<div class="col-75">
				<input type="text" class="align-form" name="prodcode" required placeholder="Type Product Code" value="${prodInfo[0].prodCode}" pattern="^[a-zA-Z0-9]*$" maxlength="20"/>
			</div>
			<div class="col-25">
				<label for="prodname">Product Name</label>
			</div>
			<div class="col-75">
				<input type="text" class="align-form" name="prodname" required placeholder="Type Product Name" value="${prodInfo[0].name}" pattern="^[a-zA-Z 0-9]*$" maxlength="30"/>
			</div>
			<div class="col-25">
				<label for="quantity">Quantity</label>
			</div>
			<div class="col-75">
				<input type="number" class="align-form" name="quantity" required placeholder="Type Quantity" value="${prodInfo[0].qty}" maxlength="11"/>
			</div>
			<div class="col-25">
				<label for="price">Price</label>
			</div>
			<div class="col-75">
				<input type="number" class="align-form" name="price" required placeholder="Type Price" value="${prodInfo[0].price}" step=".01"/>
			</div>
			<div class="col-25">
					<label>Product Type</label>
			</div>
			<div class="col-75">
				<select name="productType" id="selectType" onchange="callType()">
					<c:choose>
						<c:when test="${prodInfo[0].productType=='Perishable'}">
							<option value="Perishable" selected>Perishable</option>
							<option value="Non Perishable">Non Perishable</option>
						</c:when>
						<c:otherwise>
							<option value="Perishable">Perishable</option>
							<option value="Non Perishable" selected>Non Perishable</option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>
			
			<div class="form1" id="expiryDiv">
				<c:choose>
					<c:when test="${prodInfo[0].productType=='Non Perishable'}">
						<div class="col-25">
							<label id="label" style="display:none;">Expiration Date</label>
						</div>
						<div class="col-75">
							<input type="date" name="expiryDate" id="dateId" style="display:none;" value=""/>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-25">
							<label id="label">Expiration Date</label>
						</div>
						<div class="col-75">
							<input type="date" name="expiryDate" id="dateId" required value="${prodInfo[0].expiryDate}"/>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-80">
			</div>
			<div class="col-10">
				<input class="btn-update" type="submit" name="AddProduct" value="UPDATE"/>
			</div>
			<div class="col-10">
				<a href="${pageContext.request.contextPath}/Products/" class="btn-delete" style="height:14px !important;">CANCEL</a>
			</div>
			
		</form>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/UpdateProduct.js"></script>
</body>
</html>