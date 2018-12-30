var addIns;
document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.modal');
	addIns = M.Modal.init(elems, [{
		'dismissible': false
	}]);
});

function openAddModal(thisElement, callBackUrl) {
	addIns.open();
	$.post(callBackUrl,{"ID": Id}, function (data, status) {
		if(status){
			for(var i = 0; i < data.length; i++) {
				$("#addModalTable").add("<tr>" +
					"<th><span>"+data[i].headName+"</span></th>"+
					"<td><input name=\'"+data[i].headName+"\' type='text' /></td>" +
					"</tr>");
			}
		}
	});
}

function exportByFindingName(thisElement, callBackUrl){
	var searchName = $("#searchName").val();
	$('a#searchExportId').attr({target: '_blank',
		href  : callBackUrl + '?searchName=' + searchName });
}
