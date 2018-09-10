function validateChangepass()
{
	var x = document.forms["changePassForm"]["newpass"].value;
	var y = document.forms["changePassForm"]["confirmpass"].value;
	
	if(x==y)
	{
		alert("sucess");
		return true;
	}
	else
	{
		alert("New password and Confirm password does not match");
		return false;
	}
}
function priv()
{
	var select = document.getElementById("selectRole");
	var role = select.options[select.selectedIndex].value;
	var privDiv = document.getElementById("privDiv");
	var lblCreate = document.getElementById("lblCreate");
	var lblUpdate = document.getElementById("lblUpdate");
	var lblDelete = document.getElementById("lblDelete");
	var chkCreate = document.getElementById("createPriv");
	var chkUpdate = document.getElementById("updatePriv");
	var chkDelete = document.getElementById("deletePriv");

	if(role == "Admin")
	{
		privDiv.style.display = "none";
		lblCreate.style.display ="none";
		chkCreate.style.display ="none";
		lblUpdate.style.display ="none";
		chkUpdate.style.display ="none";
		lblDelete.style.display ="none";
		chkDelete.style.display ="none";
	}
	else
	{
		privDiv.style.display = "block";
		lblCreate.style.display ="inline";
		chkCreate.style.display ="inline";
		lblUpdate.style.display ="inline";
		chkUpdate.style.display ="inline";
		lblDelete.style.display ="inline";
		chkDelete.style.display ="inline";
	}
}
var check = function() {
	if (document.getElementById('password').value == document
			.getElementById('confirm_password').value) {
		document.getElementById('message').style.color = 'green';
		document.getElementById('message').innerHTML = 'matching';
	} else {
		document.getElementById('message').style.color = 'red';
		document.getElementById('message').innerHTML = 'not matching';
	}
}