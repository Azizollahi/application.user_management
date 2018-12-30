var modalEditIns;
var tooltipEditIns;
document.addEventListener('DOMContentLoaded', function() {
	var modalElements = document.querySelectorAll('.modal');
	modalEditIns = M.Modal.init(modalElements, [{
		'dismissible': false
	}]);
	var toolTipElements = document.querySelectorAll('.tableToolTip');
	tooltipEditIns = M.Modal.init(toolTipElements, [{ }]);
});
function openEditModal(thisElement, Id, callBackUrl){
	modalEditIns.open();
	$.post(window.location.origin+callBackUrl,{"ID": Id}, function (data, status) {
		if(status){
			for(var i = 0; i < data.length; i++) {
				$("#editorModal").add("<tr>" +
					"<th><span>"+data[i].headName+"</span></th>"+
					"<td><input name=\'"+data[i].headName+"\' type='text' /></td>" +
					"</tr>");
			}
		}
	});
}

function deleteItem(thisElement, Id, callBackUrl){
	$.ajax({
		url: window.location.origin+callBackUrl+'/'+Id,
		method: "DELETE"
	});
}
