$(document).ready(function()
{
	$('#productTable').DataTable( {
		"columnDefs": [
		    { "orderable": false, "targets": 7 },
		    { "orderable": false, "targets": 8 }
		  ],
		  "order": [[ 0, "desc" ]]
		  });
	
	$('#usersTable').DataTable( {
		"columnDefs": [
		    { "orderable": false, "targets": 7 },
		    { "orderable": false, "targets": 8 }, 
		  ],
		  "order": [[ 0, "desc" ]]
		  });
});
