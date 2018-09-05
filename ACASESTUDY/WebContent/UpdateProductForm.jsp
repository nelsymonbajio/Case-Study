<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Update Product</title>
		<link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
	</head>
<body>
	<div class="register" style="margin: 0 auto;">
		<div class="container">
			<h1 align="center">Update Product</h1>
			<br>
		</div>
		<form action="UpdateProduct" method="POST">
			<input type="hidden" name="prodid" value="${prodInfo[0].prodid}">
			<div class="form1">
				<label for=prodcode>Product Code</label>
				<input type="text" class="align-form" name="prodcode" required placeholder="Type Product Code" value="${prodInfo[0].prodCode}">
			</div>
			<div class="form1">
				<label for="prodname">Product Name</label>
				<input type="text" class="align-form" name="prodname" required placeholder="Type Product Name" value="${prodInfo[0].name}">
			</div>
			<div class="form1">
				<label for="quantity">Quantity</label>
				<input type="number" class="align-form" name="quantity" required placeholder="Type Quantity" value="${prodInfo[0].qty}">
			</div>
			<div class="form1">
				<label for="price">Price</label>
				<input type="number" class="align-form" name="price" required placeholder="Type Price" value="${prodInfo[0].price}">
			</div>
			<div class="form1">
				<label>Product Type</label>
				<select name="productType" id="selectType" onclick="callType()">
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
						<label id="label" style="display:none;">Expiration Date</label>
						<input type="date" name="expiryDate" id="dateId" required style="display:none;">
					</c:when>
					<c:otherwise>
						<label id="label">Expiration Date</label>
						<input type="date" name="expiryDate" id="dateId" required value="${prodInfo[0].expiryDate}">
					</c:otherwise>
				</c:choose>
			</div>
			
			<input class="btn-add" type="submit" name="AddProduct" value="UPDATE">
			<a href="/ACASESTUDY/Products/" class="btn-delete">CANCEL</a>
		</form>
	</div>
	
<script type="text/javascript">
	function callType()
	{
		var select = document.getElementById("selectType");
		var prodType = select.options[select.selectedIndex].value;
		var expiryDiv = document.getElementById("expiryDiv");
		var date = document.getElementById("dateId");
		var label = document.getElementById("label");
		
		if(prodType=='Non Perishable')
		{
			expiryDiv.style.display="none";
			date.value="";
			date.required=false;
		}
		else{
			expiryDiv.style.display="block";
			date.style.display="inline";
			label.style.display="inline";
			date.required=true;
		}
	}
</script>
</body>
</html>