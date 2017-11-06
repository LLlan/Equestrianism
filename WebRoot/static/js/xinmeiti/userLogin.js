//验证手机格式的正则表达是
var phoneReg=/^1[3-9]\d{9}$/;
$(function(){
	//点击登录
	$("#denglu").click(function(){
		var phone=$("#loginName").val();
		var password=$("#password").val();
		var validate=$("#validate").val();
		var rolMark=$("#userType").val();
		//判断手机号格式
		if(!phoneReg.test(phone)){
			$("#loginName").tips({
				side : 2,
				msg : '手机号格式不正确',
				bg : '#FF5080',
				time : 3
			});
			$("#loginName").focus();
			return false;
		}
		//密码不能为空
		if(password==''){
			$("#password").tips({
				side : 2,
				msg : '密码不能为空',
				bg : '#FF5080',
				time : 3
			});
			$("#password").focus();
			return false;
		}
		//判断验证码格式
		if(validate.length!=4){
			$("#validate").tips({
				side : 3,
				msg : '验证码长度不正确',
				bg : '#FF5080',
				time : 3
			});
			$("#validate").focus();
			return false;
		}
		$.ajax({
			type:"post",
			url:"api/xmtv1/login.do",
			data:{
				"phone":phone,
				"password":password,
				"validate":validate,
				"rolMark":rolMark
			},
			dataType:"json",
			cache: false,
			success:function(data){
				if("success" == data.result){
					saveCookie();
					window.location.href="api/xmtv1/index.do";
				}else if("验证码错误" == data.result){
					$("#validate").tips({
						side : 3,
						msg : '验证码错误',
						bg : '#FF5080',
						time : 3
					});
					$("#validate").focus();
					changeCode();
				}else{
					$("#loginName").tips({
						side : 1,
						msg : '用户名或密码错误',
						bg : '#FF5080',
						time : 3
					});
				}
			}
		})
	})
})
 //保存cookie
 function saveCookie() {
	if ($("#saveid").is(":checked")) {
		$.cookie('loginname1', $("#loginName").val(), {
			expires : 7
		});
		$.cookie('password1', $("#password").val(), {
			expires : 7
		});
	}
}

function savePaw() {//loginName--password
	  
	if (!$("#saveid").is(":checked")) {
		$.cookie('loginname1', '', {
			expires : -1
		});
		$.cookie('password1', '', {
			expires : -1
		});
		$("#loginName").val('');
		$("#password").val('');
	}
}

//读取cookie
jQuery(function() {
	var loginname = $.cookie('loginname1');
	var password = $.cookie('password1');
	if (typeof(loginname) != "undefined"
			&& typeof(password) != "undefined") {
		$("#loginName").val(loginname);
		$("#password").val(password);
		$("#saveid").attr("checked", true);
		$("#validate").focus();
	}
});