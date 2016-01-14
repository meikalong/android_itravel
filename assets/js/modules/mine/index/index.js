$(function() {
	$(".loginout").on("touchstart", function() {
		var url = lt.getBasePath() + "loginout";
		$.post(url, null, function(data) {
			location.reload();
		})
	})
})