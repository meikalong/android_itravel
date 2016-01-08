$(function() {
	$("#left-menu").on("click", "li", function() {
		var $this = $(this);
		var selectId = $this.attr("my-data-selected");
		$(".column-right").hide();
		$("#" + selectId).show();
		console.log("console");
		alert(121212);
		var _$ = window.parent.document.getElementById("b").contentWindow.$;
		console.log(_$);
		alert(_$);
		_$(".column-right").hide();
		_$("#"+selectId).show();
	})
})
