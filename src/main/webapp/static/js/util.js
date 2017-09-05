function checkMinMaxInput(componentId, min, max ) {
	
	$('#' + componentId).on('input',function(e){
		
		 if( $("#componentId").val() < min){
			 alert("Number quantity min > 0 ");
		 }
		 else if( $("#componentId").val() > max)
		 {
			alert("Number quantity <= Max ");
		 }
		console.log('Component: ' + componentId + " min: " + min + " max: " + max);
    });
}

function loadingSpinner(isLoading) {
	if (isLoading) {
		var div = $(document.createElement('div'));
		div.addClass("loading-spinner");
		div.attr("id", "loading-spinner");
		document.getElementsByTagName('body')[0].appendChild(div.get(0));
	} else {
		$("#loading-spinner").remove();
	}
}