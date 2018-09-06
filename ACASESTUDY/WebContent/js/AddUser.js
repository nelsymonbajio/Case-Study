function priv()
{
	var select = document.getElementById("selectRole");
	var role = select.options[select.selectedIndex].value;
	var privDiv = document.getElementById("privDiv");
	if(role == "Admin")
	{
		privDiv.style.display = "none";
	}
	else{
		privDiv.style.display = "block";
	}
}