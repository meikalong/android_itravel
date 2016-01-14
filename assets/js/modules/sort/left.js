$(function() {
	$("#left-menu").on("click", "li", function() {
		var $this = $(this);
		var selectId = $this.attr("my-data-selected");
		$("#left-menu li").removeClass("active");
		$this.addClass("active");
		
		$(".column-right").hide();
		$("#" + selectId).show();
		var _$ = window.parent.document.getElementById("b").contentWindow.$;
		_$(".column-right").hide();
		_$("#"+selectId).show();
	})
})