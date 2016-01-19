$(function() {
	$("#form1").submit(function() {
		var $submit = $("#submit");
		$submit.attr("disabled", "disabled").css("background-color", "#ccc");
		$(this).ajaxSubmit(function(data) {
			if (data.code == "200") {
				jsInterface.setMineRefresh();
				window.opener = null;
				window.open('', '_self');
				window.close();
			} else{
				$submit.removeAttr("disabled").removeAttr("style");
				new jBox("Notice", {
					content: data.message,
					position: {
						x: "center",
						y: "center"
					},
					autoClose: 2000
				});
			}
		});
		return false;
	});

	$("#add").click(function() {
		$("#form1").hide();
		$("#top").hide();
		$("#form2").show();
	});

	$("#form2").submit(function() {
		var $register = $("#form2 #register");
		var name=$("#form2 #name").val();
		var phone=$("#form2 #username").val();
		var password=$("#form2 #password").val();
		var confirmpassword=$("#confirmpassword").val();
		if(name==null||name==""){
			$("#form2 #name").addClass("error");
			new jBox("Notice", {
				content: "用户名不能为空",
				position: {
					x: "center",
					y: "center"
				},
				autoClose: 2000
			});
			return false;
		}else{
			$("#form2 #name").removeClass("error");
		}
		if (!(/^1[3|4|5|7|8]\d{9}$/.test(phone))) {
			$("#form2 #username").addClass("error");
			new jBox("Notice", {
				content: "请输入正确的手机号码",
				position: {
					x: "center",
					y: "center"
				},
				autoClose: 2000
			});
			return false;
		}else{
			$("#form2 #username").removeClass("error");
		}
		if(password==""){
			$("#form2 #password").addClass("error");
			new jBox("Notice", {
				content: "输入密码",
				position: {
					x: "center",
					y: "center"
				},
				autoClose: 2000
			});
			return false;
		}else{
			$("#form2 #password").removeClass("error");
		}
		if(password!=confirmpassword){
			$("#confirmpassword").addClass("error");
			new jBox("Notice", {
				content: "两次输入的密码不一样",
				position: {
					x: "center",
					y: "center"
				},
				autoClose: 2000
			});
			return false;
		}else{
			$("#confirmpassword").removeClass("error");
		}
		$register.attr("disabled", "disabled").css("background-color", "#ccc");
		$(this).ajaxSubmit(function(data) {
			if (data.code == "200") {
				jsInterface.setMineRefresh();
				window.opener = null;
				window.open('', '_self');
				window.close();
			} else {
				new jBox("Notice", {
					content: data.message,
					position: {
						x: "center",
						y: "center"
					},
					autoClose: 2000
				});
				$register.removeAttr("disabled").removeAttr("style");
			}
		});
		return false;
	});
})