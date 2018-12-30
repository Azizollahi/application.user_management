document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.dropdown-trigger');
	var instance = M.Dropdown.init(elems, [{
		'alignment': 'left'
	}]);
	instance.isScrollable = false;
});
