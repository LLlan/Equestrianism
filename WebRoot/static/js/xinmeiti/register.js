//验证手机格式的正则表达是
var phoneReg=/^1[3-9]\d{9}$/;
//强：字母+数字+特殊字符 
var pawReg1=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;     
//中：字母+数字，字母+特殊字符，数字+特殊字符
var pawReg2=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
//验证QQ格式
var qqReg=/^[1-9]\d{4,11}$/;
$(function() {
	//输入手机号失去焦点事件
	$("#loginName").blur(function(){
		if(!phoneReg.test($("#loginName").val())){
			$("#loginName").tips({
				side : 2,
				msg : '请正确输入手机号',
				bg : '#68B500',
				time : 3
			});
			$("#loginName").focus();
		}
	})
	//输入密码的keyup事件
	$("#pwd").keyup(function() {
		var password=document.getElementById("pwd").value;//密码
		pawQD(password);
	})
	//输入密码的失去焦点事件
	$("#pwd").blur(function(){
		var password=$("#pwd").val();//密码
		if(password.length>=6 && password.length<=20){//首先判断密码长度是否满足条件
			if(pawReg1.test(password) || pawReg2.test(password)){//密码格式正确
				pawQD(password);
			}else{
				$("#pwd").tips({
					side : 2,
					msg : '密码格式不正确',
					bg : '#68B500',
					time : 3
				});
				$("#pwd").focus();
			}
		}else{
			$("#pwd").tips({
				side : 2,
				msg : '密码长度不正确',
				bg : '#68B500',
				time : 3
			});
			$("#pwd").focus();
		}
	})
	//确认密码的失焦事件
	$("#pwd2").blur(function(){
		if($("#pwd").val() != $("#pwd2").val()){
			$("#pwd2").tips({
				side : 2,
				msg : '确认密码输入不一致',
				bg : '#68B500',
				time : 3
			});
			$("#pwd2").focus();
		}
	})
	//输入验证码的失焦事件
	$("#validate").blur(function(){
		if($("#validate").val().length!=4){
			$("#validate").tips({
				side : 2,
				msg : '验证码长度不正确',
				bg : '#68B500',
				time : 3
			});
			$("#validate").focus();
			return false;
		}
	})
	//输入QQ的失焦事件
	$("#QQ").blur(function(){
		if(!qqReg.test($("#QQ").val())){
			$("#QQ").tips({
				side : 2,
				msg : 'QQ格式不正确',
				bg : '#68B500',
				time : 3
			});
			$("#QQ").focus();
		}
	})
})
//点击注册事件
function register() {
	var phone=$("#loginName").val();//手机号
	var password=$("#pwd").val();//密码
	var pwd2=$("#pwd2").val();//确认密码
	var validate=$("#validate").val();//验证码
	//var mobileCode=document.getElementById("mobileCode").value;//手机短信验证码
	var linkmanQQ=$("#QQ").val();//联系人扣扣
	
	//判断手机号格式是否正确
	if(!phoneReg.test(phone)){
		$("#loginName").tips({
			side : 2,
			msg : '请正确输入手机号',
			bg : '#68B500',
			time : 3
		});
		$("#loginName").focus();
		return false;
	}
	//判断密码
	if(password.length>=6 && password.length<20){//首先判断密码长度是否满足条件
		if(pawReg1.test(password) || pawReg2.test(password)){//密码格式正确
			pawQD(password);
		}else{
			$("#pwd").tips({
				side : 2,
				msg : '密码格式不正确',
				bg : '#68B500',
				time : 3
			});
			$("#pwd").focus();
			return false;
		}
	}else{
		$("#pwd").tips({
			side : 2,
			msg : '密码长度不正确',
			bg : '#68B500',
			time : 3
		});
		$("#pwd").focus();
		return false;
	}
	//两次密码输入不一致
	if(password != pwd2){
		$("#pwd2").tips({
			side : 2,
			msg : '确认密码输入不一致',
			bg : '#68B500',
			time : 3
		});
		$("#pwd2").focus();
		return false;
	}
	//判断验证码格式
	if(validate.length != 4){
		$("#validate").tips({
			side : 2,
			msg : '验证码长度不正确',
			bg : '#68B500',
			time : 3
		});
		$("#validate").focus();
		return false;
	}
	
	
	//QQ格式
	if(isNaN(linkmanQQ)){
		$("#QQ").tips({
			side : 2,
			msg : 'QQ格式不正确',
			bg : '#68B500',
			time : 3
		});
		$("#QQ").focus();
		return false;
	}
	
	
	var radioVal  = $('input[name="role"]:checked').val(); //获取被选中Radio的Value值1-广告主，2-媒介主
	//var canShu=radioVal+","+phone+","+password+","+validate+","+mobileCode+","+linkmanQQ;
	var canShu=radioVal+","+phone+","+password+","+validate+","+linkmanQQ;
	$.ajax({
		type:"post",
		url:"api/xmtv1/register.do",
		data:{
			"canShu":canShu
		},
		dataType:"json",
		cache: false,
		success:function(data){
			console.log(data);
			if("success" == data.result){
				var url="api/xmtv1/login.do?phone="+data.phone+"&password="+data.password+"&rolMark="+data.rolMark+"&validate="+validate;
				$.get(url,function(data){
					window.location.href="api/xmtv1/index.do";
				})
			}else if("手机号已经注册" == data.result){
				$("#loginName").tips({
					side : 2,
					msg : "手机号已经注册,可直接登录",
					bg : '#FF5080',
					time : 3
				});
			}else{
				$("#validate").tips({
					side : 2,
					msg : "验证码不正确",
					bg : '#FF5080',
					time : 3
				});
			}
		}
	})
	
}


//密码强度
function pawQD(password) {
	if(password.length<10){//弱
		document.getElementById("strength_L").style.background="red";
		document.getElementById("strength_M").style.background="";
		document.getElementById("strength_H").style.background="";
	}else if(password.length>=15){//强
		document.getElementById("strength_L").style.background="";
		document.getElementById("strength_M").style.background="";
		document.getElementById("strength_H").style.background="red";
	}else{//中
		document.getElementById("strength_L").style.background="";
		document.getElementById("strength_M").style.background="red";
		document.getElementById("strength_H").style.background="";
	}
}
//获取短信验证码
/*$("#phoneCode").click(function(){
	var phone = $("#loginName").val();
	if(phone==null || phone==""){
		$("#loginName").tips({
			side : 2,
			msg : '请输入手机号',
			bg : '#68B500',
			time : 3
		});
		$("#loginName").focus();
		return false;
	}else{
		var url = 'api/xmtv1/getSms.do?phone='+phone;
		$.get(url,function(data){
			console.log(data)
			if(data.msg=="01"){
				$("#yzm").val(data.yanzhengma);
			}
		})
	}
	
})*/

//验证码输入框onblur事件

/*$("#mobileCode").blur(function(){
	if($(this).val()!=$("#yzm").val()){
			$("#mobileCode").tips({
				side : 3,
				msg : '验证码输入错误',
				bg : '#FF3366',
				time : 3
			});
			$("#mobileCode").focus();
			$("#finaYz").val("1");	
	}
})*/	








