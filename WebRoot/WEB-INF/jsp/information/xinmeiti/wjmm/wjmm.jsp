<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="viewport" content="width=device-width, maximum-scale=2.0">
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/wjmm.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>忘记密码</title>
</head>
<body>
<!--顶部-->
<div class="top">
    <div class="middle">
        <div class="top_left"><span>您好！欢迎来到<a href="#">新媒体广告网</a></span></div>
        <div class="top_right"><span>客服电话：0898-14253868</span></div>
    </div>
</div>
<%--<form action="" method="" id="" name="">--%>
    <div class="login-middle" style=" height: 601px;border: 1px solid #ddd;">
        <div class="login-im">
            <img src="static/images/xinmeiti/login_f.png" style="margin:0">
        </div>
        <div class="login-mian login-r" >
            <dl class="log-r-box">
                <dd class="log-r-t" style="font-size:22px;color:#656565;">找回密码</dd>
                <dd class="login-ha" id="suc">已有账号？直接<a href="<%=basePath%>api/xmtv1/toLogin.do">登录</a></dd>
            </dl>
            <ul class="login-t clearfix">
                <li class="login-bor-l login-active" id="guanggaozhu">广告主</li>
                <li class="login-bor-r f-right login-normal" id="meijiezhu">媒介主</li>
            </ul>
            <%--  --%>
            <input type="hidden" id="userType" value="1">
            <input type="hidden" id="smsvalue">
            <input type="hidden" id="phone">
            <div class="login-star clearfix">
                <div class="log-step">
                    <span class="log-st-i i-user"></span>
                    <input type="text" placeholder="手机号" class="log-input" name="mobile" id="mobile">
                </div>
            </div>
            <div class="login-star clearfix" style="margin-top: 15px;">
                <div class="log-step in-ver">
                    <input type="text" placeholder="请输入验证码" class="log-input" name="code" id="mobileCode">
                </div>
                <input type="button" class="img-ver1" value="获取验证码" onclick="clickButton(this)" id="getSms">
            </div>
            <div class="login-star clearfix" style="margin-top: 15px;">
                <div class="log-step">
                    <span class="log-st-i i-pwd"></span>
                    <input type="password" placeholder="输入新密码" class="log-input" name="pwd" id="psw">
                </div>
                <div class="log-tip">请输入6位以上的密码（字母+数字组合）</div>
            </div>
            <div class="login-star clearfix">
                <div class="log-step">
                    <span class="log-st-i i-pwd"></span>
                    <input type="password" placeholder="确认密码" class="log-input" name="psw2" id="psw2">
                </div>
            </div>
            <div class="login-star clearfix">
                <div class="login-bottom-btn" style="margin-top:15px;">
                    <input type="button" class="login-Btn" value="重置密码" onclick="resetPsw();">
                </div>
            </div>
        </div>
    </div>
<%--</form>--%>
<div id="footer" style="margin-top: 9px;">
    <div class="foot">
        <div class="foot_lf">
            <span style="margin-right: 50px">版权所有©新媒体广告</span>
            <span>备案GUIOSJKDH-15GDFG 545544</span>
        </div>
        <div class="foot_m">
            <div class="box">
                <h3>官方微信公众号</h3>
                <div >
                    <img src="static/images/xinmeiti/xingtui.png" style="width: 100px;height: 100px;">
                </div>
            </div>
            <div class="box">
                <h3>官方微博</h3>
                <div>
                    <img src="static/images/xinmeiti/xinlang.png" style="width: 100px;height: 100px;">
                </div>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
	//验证手机格式的正则表达是
	var phoneReg=/^1[3-9]\d{9}$/;
	//强：字母+数字+特殊字符 
	var pawReg1=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;     
	//中：字母+数字，字母+特殊字符，数字+特殊字符
	var pawReg2=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
	//点击重置密码
	function resetPsw(){
		//判断是否正确输入手机号
		if($("#mobile").val()==''){
			$("#mobile").tips({
				side : 2,
				msg : '请正确输入手机号',
				bg : '#68B500',
				time : 1
			});
			return;
		}
		if($("#smsvalue").val()==''){
			$("#getSms").tips({
				side : 2,
				msg : '请先获取短信验证码',
				bg : '#68B500',
				time : 1
			});
			return;
		}
		if($("#mobileCode").val().length!=6){
			$("#mobileCode").tips({
				side : 2,
				msg : '请正确输入短信验证码',
				bg : '#68B500',
				time : 1
			});
			return;
		}
		if(!pawReg1.test($("#psw").val()) && !pawReg2.test($("#psw").val()) || !$("#psw").val().length>6){
			$("#psw").tips({
				side : 2,
				msg : '请按要求输入密码',
				bg : '#68B500',
				time : 1
			});
			return;
		}
		if($("#psw2").val()!=$("#psw").val()){
			$("#psw2").tips({
				side : 2,
				msg : '两次密码输入不一致，请重新输入',
				bg : '#68B500',
				time : 1
			});
			return;
		}
		if($("#mobile").val()!=$("#phone").val()){
			$("#mobile").tips({
				side : 2,
				msg : '接受短信验证码的手机号与当前手机号不一致，请注意',
				bg : '#68B500',
				time : 1
			});
			return;
		}
		if($("#mobileCode").val()!=$("#smsvalue").val()){
			$("#mobileCode").tips({
				side : 2,
				msg : '请正确填写验证码',
				bg : '#68B500',
				time : 1
			});
			return;
		}
		$.ajax({
        	type:"post",
        	url:"api/xmtv1/resetPsw.do",
        	dataType:"json",
        	data:{
        		phone:$("#mobile").val(),
        		rolMark:$("#userType").val(),
        		password:$("#psw").val()
        	},
        	success:function(data){
        		if(data.result=="success"){
        			$("#suc").tips({
        				side : 2,
        				msg : '密码重置成功，点击这里进行登录',
        				bg : '#68B500',
        				time : 3
        			});
        		}else{
        			$("#mobile").tips({
        				side : 2,
        				msg : '手机号或身份选择错误，请正确选择',
        				bg : '#68B500',
        				time : 2
        			});
        		}
        	}
        });
	}
    /*获取验证码*/
    function clickButton(obj){
    	if(!phoneReg.test($("#mobile").val())){
    		$("#mobile").tips({
				side : 2,
				msg : '请正确输入手机号',
				bg : '#68B500',
				time : 1
			});
			return;
    	}
    	var mark='';
    	//验证该手机号是否注册
   	 	$.ajax({
        	type:"post",
        	url:"api/xmtv1/zhuceYesOrNo.do",
        	dataType:"json",
        	async: false,
        	data:{
        		phone:$("#mobile").val(),
        		rolMark:$("#userType").val()
        	},
        	success:function(data){
        		if(data.result=="error"){
        			mark='0';
        			$("#mobile").tips({
        				side : 2,
        				msg : '该手机号还未注册，请先注册',
        				bg : '#68B500',
        				time : 1
        			});
        		}
        	}
        });
    	if(mark!=''){
    		return;
    	}
        var obj = $(obj);
        obj.attr("disabled","disabled");/*按钮倒计时*/
        var time = 60;
        var set=setInterval(function(){
            obj.val(--time+"秒");
        }, 1000);/*等待时间*/
        setTimeout(function(){
            obj.attr("disabled",false).val("重新获取验证码");/*倒计时*/
            clearInterval(set);
        }, 60000);
        //获取验证码
       $.ajax({
        	type:"post",
        	url:"api/xmtv1/getSms.do",
        	dataType:"json",
        	data:{
        		phone:$("#mobile").val()
        	},
        	success:function(data){
        		if(data.result=="验证码发送成功"){
        			console.log(data);
        			$("#smsvalue").val(data.yanzhengma);
        			$("#phone").val(data.phone);
        		}
        	}
        });
    }
    //新密码失去焦点事件
    $(function() {
    	$("#psw").blur(function() {
			if(!pawReg1.test($("#psw").val()) && !pawReg2.test($("#psw").val()) || !$("#psw").val().length>6){
				$("#psw").tips({
					side : 2,
					msg : '请按要求输入密码',
					bg : '#68B500',
					time : 1
				});
			}
		});
		$("#psw2").focus(function() {
    		if($("#psw").val()==''){
    			$("#psw").tips({
					side : 2,
					msg : '请按要求输入密码',
					bg : '#68B500',
					time : 1
				});
    		}
    	});
    	$("#psw2").blur(function() {
    		if($("#psw2").val()!=$("#psw").val()){
    			$("#psw2").tips({
					side : 2,
					msg : '两次密码输入不一致，请重新输入',
					bg : '#68B500',
					time : 1
				});
    		}
    	});
	});
    /*广告主、媒体主交替*/
    $("#guanggaozhu").click(function() {
    	$("#guanggaozhu").removeClass('login-normal');
    	$("#guanggaozhu").addClass('login-active');
    	$("#meijiezhu").removeClass('login-active');
    	$("#meijiezhu").addClass('login-normal');
    	$("#userType").val("1");
	});
    $("#meijiezhu").click(function() {
    	$("#meijiezhu").removeClass('login-normal');
    	$("#meijiezhu").addClass('login-active');
    	$("#guanggaozhu").removeClass('login-active');
    	$("#guanggaozhu").addClass('login-normal');
    	$("#userType").val("2");
	});
    /*广告主、媒体主交替*/
    /*$(".login-t li").click(function(){
        $(this).removeClass('login-normal').addClass('login-active').siblings().removeClass('login-active').addClass('login-normal');
    })*/
</script>
</body>
</html>