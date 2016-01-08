$(function() {
	var topHeight = $(".top").height();
	var screenHeight = window.innerHeight;
	var columnHeight = screenHeight - topHeight;
	$(".column").css("height",columnHeight);
})