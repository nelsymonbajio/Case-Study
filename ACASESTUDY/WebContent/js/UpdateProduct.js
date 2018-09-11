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
		label.style.display="inline-block";
		date.required=true;
	}
}