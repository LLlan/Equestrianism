<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="viewport" content="width=device-width, maximum-scale=2.0">
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/wjmm.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/yanZM.js"></script>
    <script src="static/js/xinmeiti/userLogin.js"></script>
    <script type="text/javascript" src="static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>登录</title>
</head>
<body>
<!--顶部-->
<div class="top">
    <div class="middle">
        <div class="top_left"><span>您好！欢迎来到<a href="#">新媒体广告网</a></span></div>
        <div class="top_right"><span>客服电话：0898-14253868</span></div>
    </div>
</div>
    <div class="login-middle" style=" height: 601px;border: 1px solid #ddd;">
        <div class="login-im">
            <img src="static/images/xinmeiti/login_f.png" style="margin:0">
        </div>
        <div class="login-mian login-m" >

            <ul class="login-t clearfix">
                <li class="login-bor-l login-active" id="guanggaozhu">广告主</li>
                <li class="login-bor-r f-right login-normal" id="meijiezhu">媒介主</li>
            </ul>
            <input id="userType" name="userType" type="hidden" value="1">
            <div class="login-star clearfix">
                <div class="log-step">
                    <span class="log-st-i i-user"></span>
                    <input type="text" placeholder="手机号" class="log-input" name="loginName" id="loginName">
                </div>
            </div>
            <div class="login-star clearfix" style="margin-top: 15px;">
                <div class="log-step">
                    <span class="log-st-i  i-pwd"></span>
                    <input type="password" placeholder="登录密码" class="log-input" name="password" id="password">
                </div>
            </div>
            <div class="log-step in-ver" style="margin-top: 15px;">
                <input type="text" placeholder="请输入验证码" class="log-input" name="validate" id="validate">
            </div>
            <span class="">
                        <%--<img id="checkCode" width="75" height="35" src="static/images/xinmeiti/verifyimage.jpg">--%>
            	<img style="position: absolute;margin-top: 15px;height: 44px;margin-left: 10px;width: 100px;" id="codeImg">
            </span>
            <div class="login-five clearfix">
                <span class="log-fon1">
                    <input type="checkbox" id="saveid" onclick="savePaw();">
                </span>
                <span>七天内免登录</span>
                <span class="log-fon2"><a href="<%=basePath%>api/xmtv1/toWjmm.do">忘记密码？</a></span>
            </div>
            <div class="login-star clearfix">
                <div class="login-bottom-btn" style="margin-top:15px;">
                    <input type="button" class="login-Btn" value="立即登录" id="denglu" style="cursor: pointer;">
                </div>
            </div>
            <div class="login-five clearfix"><a style="color: #ff7700;" href="javascript:void(0)" id="zhuce">免费注册</a></div>
            <script type="text/javascript">
            	$(function(){
            		//点击注册
                    $("#zhuce").click(function(){
                    	var url = 'api/xmtv1/toRegister.do';
                    	//window.location.href=url;
                    	window.location.href=url;
                    });
            	})
            </script>
        </div>
    </div>
<div id="footer" style="margin-top: 9px;">
    <div class="foot">
        <div class="foot_lf">
            <span style="margin-right: 50px">版权所有©新媒体广告</span>
            <span>备案GUIOSJKDH-15GDFG 545544</span>
        </div>
        <div class="foot_m">
            <div class="box">
                <h3>官方微信公众号</h3>
                <div>
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
<script>
    $(function(){
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
        <%--
        $(".login-t li").click(function(){
            $(this).removeClass('login-normal').addClass('login-active').siblings().removeClass('login-active').addClass('login-normal');
        });
        --%>
    });
</script>
</body>
</html>