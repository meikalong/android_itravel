$(function() {
	$("form").submit(function() {
		$("#submit").attr("disabled","disabled").css("background-color","#ccc");
		$(this).ajaxSubmit(function(data) {
			if(data.code=="200"){
				jsInterface.setMineRefresh();
				window.opener=null;
				window.open('','_self');
				window.close();
			}else{
				$("#submit").removeAttr("disabled").removeAttr("style");
			}
		});
		return false;
	});
	
})