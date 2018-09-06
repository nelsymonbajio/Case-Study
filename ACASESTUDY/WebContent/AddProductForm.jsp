<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>New Product</title>
	<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="register" style="margin: 0 auto;">
		<div class="container">
			<h1 align="center">New Product</h1>
			<br>
		</div>
		<form action="Products/AddProduct" method="POST">
			<div class="form1">
				<label for=prodcode>Product Code</label>
				<input type="text" class="align-form" name="prodcode" required placeholder="Type Product Code">
			</div>
			<div class="form1">
				<label for="prodname">Product Name</label>
				<input type="text" class="align-form" name="prodname" required placeholder="Type Product Name">
			</div>
			<div class="form1">
				<label for="quantity">Quantity</label>
				<input type="number" class="align-form" name="quantity" required placeholder="Type Quantity">
			</div>
			<div class="form1">
				<label for="price">Price</label>
				<input type="number" class="align-form" name="price" required placeholder="Type Price">
			</div>
			<div class="form1">
				<label>Product Type</label>
				<select name="productType" id="selectType" onclick="callType()">
					<option value="Perishable">Perishable</option>
					<option value="Non Perishable">Non Perishable</option>
				</select>
			</div>
			<div class="form1" id="expiryDiv">
				<label>Expiration Date</label>
				<input type="date" name="expiryDate" id="dateId" required>
			</div>
			<input class="btn-add" type="submit" name="AddProduct" value="SUBMIT">
			<a href="${pageContext.request.contextPath}/Products/" class="btn-delete" style="width:70px !important;">CANCEL</a>
		</form>
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/AddProduct.js"></script>
</body>
</html>