$(function() {
	$("form").submit(function() {
		$(this).ajaxSubmit(function(data) {
			if(data.code=="200"){
				jsInterface.setMineRefresh();
				window.opener=null;
				window.open('','_self');
				window.close();
			}
		});
		return false;
	});
	
})