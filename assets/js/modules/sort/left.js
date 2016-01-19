$(function() {
	$("#left-menu").on("click", "li", function() {
		var $this = $(this);
		var selectId = $this.attr("my-data-selected");
		$("#left-menu li").removeClass("active");
		$this.addClass("active");
		
		$(".column-right").hide();
		$("#" + selectId).show();
		var b$ = window.parent.document.getElementById("b").contentWindow.$;
		b$(".column-right").hide();
		b$("#"+selectId).show();
		b$("img.imglazy").lazyload();
	})
})