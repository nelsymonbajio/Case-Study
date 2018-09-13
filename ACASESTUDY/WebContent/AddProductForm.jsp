<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>New Product</title>
	<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="mainDiv">
		<h1 align="center">New Product</h1>
		<br>
		<form action="Products/AddProduct" method="POST">
				<div class="col-25">
					<label for=prodcode>Product Code</label>
				</div>
				<div class="col-75">
					<input type="text" class="align-form" name="prodcode" required placeholder="Type Product Code" pattern="^[a-zA-Z0-9]*$" maxlength="20"/>
				</div>
				<div class="col-25">
					<label for="prodname">Product Name</label>
				</div>
				<div class="col-75">
					<input type="text" class="align-form" name="prodname" required placeholder="Type Product Name" pattern="^[a-zA-Z 0-9]*$" maxlength="30"/>
				</div>
				<div class="col-25">
					<label for="quantity">Quantity</label>
				</div>
				<div class="col-75">
					<input type="number" class="align-form" name="quantity" required placeholder="Type Quantity" maxlength="11"/>
				</div>
				<div class="col-25">
					<label for="price">Price</label>
				</div>
				<div class="col-75">
					<input type="number" class="align-form" name="price" required placeholder="Type Price" step=".01"/>
				</div>
				<div class="col-25">
					<label>Product Type</label>
				</div>
				<div class="col-75">
					<select name="productType" id="selectType" onchange="callType()">
					<option value="Perishable">Perishable</option>
					<option value="Non Perishable">Non Perishable</option>
				</select>
				</div>
				<div class="form1" id="expiryDiv">
					<div class="col-25">
						<label>Expiration Date</label>
					</div>
					<div class="col-75">
						<input type="date" name="expiryDate" id="dateId" required/>
					</div>
				</div>
				<div class="col-80">
				</div>
				<div class="col-10">
					<input class="btn-update" type="submit" name="AddProduct" value="SUBMIT"/>
				</div>
				<div class="col-10">
					<a href="${pageContext.request.contextPath}/Products/" class="btn-delete">CANCEL</a>
				</div>
			
		</form>
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/AddProduct.js"></script>
</body>
</html>