$(function() {
	var num = 0;
	var topHeight = 13/60*window.innerWidth;
	var screenHeight = window.innerHeight;
	var columnHeight = screenHeight - topHeight;
	$(".column").css("height", screenHeight - topHeight);
	$("#a").attr("src", lt.getBasePath() + "sort/iframepage/left");
	$("#b").attr("src", lt.getBasePath() + "sort/iframepage/right");
	document.getElementById("a").onload = function() {
		num++;
		showData();
	}
	document.getElementById("b").onload = function() {
		num++;
		showData();
	}

	function showData() {
		var url = lt.getBasePath() + "sort/findAllSortList";
		if (num == 2) {
			$.post(url, null, function(data) {
				showInView(data);
			});
		}
	}

	function showInView(data) {
		var content = "";

		// 显示左边的内容
		var a$ = document.getElementById("a").contentWindow.$;
		var leftList = data.leftList;
		for (var i = 0, l = leftList.length; i < l; i++) {
			if (i == 0) {
				content += '<li my-data-selected="right-column' + (i + 1)
						+ '" 	class="active">';
			} else {
				content += '<li my-data-selected="right-column' + (i + 1)
						+ '">';
			}
			content += '<a href="javascript:void(0)" class="a-bk">'
					+ leftList[i].name + '</a></li>';
		}
		a$("#left-menu").empty();
		a$("#left-menu").append(content);

		// 显示右边的内容
		var b$ = document.getElementById("b").contentWindow.$;
		var rightList = data.rightList;
		var sorts = {};
		content = "";
		for (var i = 0, l = leftList.length; i < l; i++) {
			var array = [];
			var id = leftList[i].id;
			for (var j = 0, m = rightList.length; j < m; j++) {
				if (rightList[j].parent.id == id) {
					array[array.length] = rightList[j]
				}
			}
			sorts["right-column" + (i + 1)] = array;
		}
		for ( var p in sorts) {
			var sortList = sorts[p];
			content += '<div id="' + p + '" class="column-right width">';
			for (var i = 0, l = sortList.length; i < l; i += 2) {
				content += '<div class="width right-img">';
				var temp = sortList[i];
				if (temp != null) {
					content += '<img src="'
							+ temp.imageUrl
							+ '"><a href="'+lt.getBasePath()+'sort/search?content='+temp.name+'" target="_top"><div class="msg1"><p class="text"><span class="strong">'
							+ temp.name + '</span> <br>' + temp.nameEn
							+ '</p></div></a>';
				}
				temp = sortList[i + 1];
				if (temp != null) {
					content += '<img src="'
							+ temp.imageUrl
							+ '"><a href="'+lt.getBasePath()+'sort/search?content='+temp.name+'" target="_top"><div class="msg2"><p class="text"><span class="strong">'
							+ temp.name + '</span> <br>' + temp.nameEn
							+ '</p></div></a>';
				}
				content += '</div>';
			}
			content += '</div>';
		}
		b$("body").empty();
		b$("body").append(content);
		b$("#right-column1").show();
	}
})