<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="viewport" content="width=device-width, maximum-scale=2.0">
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/register.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/register.js"></script>
    <script src="static/js/xinmeiti/yanZM.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>注册</title>
</head>
<body>
   <input type="hidden" id="yzm" >
   <input type="hidden" id="finaYz">
    <div class="header-bg">
        <div class="header">
            <div class="header-lf clearfix">
                <div class="name">
                    <a href="#">欢迎来到新媒体广告网</a>
                </div>
                <div class="platform">娱乐营销推广资源交易平台</div>
            </div>
            <div class="header-menu">
                <ul>
                    <li><a href="#">首页</a></li>
                    <li><a href="#">关于我们</a></li>
                    <li><a href="#">成功案例</a></li>
                    <li><a href="#">帮助中心</a></li>
                    <li><a href="#">联系我们</a></li>
                </ul>
            </div>
        </div>
    </div>
<!--login-->
    <div class="header-ov">
        <div class="Overall-width">
            <div class="n-logo-search">
                <div class="n-logo">
                    <a href="#"><img src="static/images/xinmeiti/logonews.png" alt=""/></a>
                </div>
                <div class="n-search-all">

                </div>
                <div class="t-j-advice">
                    <ul class="l_list">
                        <li class="l_list1">
                            <a href="#">我要投广告</a>
                        </li>
                        <li class="l_list2">
                            <a href="#">我要接广告</a>
                        </li>
                    </ul>
                </div>

            </div>
            <div class="n-coulum">
                <div class="n-coulum-d">
                    <ul>
                        <li><a href="#">微博</a></li>
                        <li><a href="#">微信朋友圈</a></li>
                        <li><a href="#">微信公众号</a></li>
                        <li><a href="#">网红直播</a></li>
                        <li><a href="#">新闻发布</a></li>
                        <li><a href="#">广告招商</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
<!--主要内容-->
<div class="clearfix regbg">
    <div class="content">
        <div class="reg_user">
            <ul class="reg_fo">
                <li class="on">设置用户名</li>
                <li><span>注册成功</span></li>
            </ul>
        </div>
        <div id="reg-info" class="left">
            <table style="margin-left:30px; " class="reg-table">
                <tbody><tr>
                    <td class="td-login" width="91"><strong>*</strong>选择身份:</td>
                    <td colspan="2" style="font-size: 14px;color: #666666;">
                        <div style="float:left;">
                            <div class="checkboxgood">
                                <input class="userType" type="radio" value="1" id="checkboxgoodInput" name="role">
                            </div>
                        </div>
                        <div style="float:left; margin-top:3px; margin-left:0px;">广告主（我是企业/微商，我要询价）</div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td width="281" style="font-size: 14px;color: #666666;">
                        <div style="float:left;">
                            <div class="checkboxgood">
                                <input class="userType" type="radio" value="2" checked="checked" id="checkboxgoodInput1" name="role">
                            </div>

                        </div>
                        <div style="float:left; margin-top:3px; margin-left:0px;">媒介主（我有媒体资源，我要接单）</div>

                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="td-login part"><strong>*</strong>登录名:</td>
                    <td class="">
                        <span class="login_user"></span>
                        <input type="text" id="loginName" name="loginName" placeholder="请输入手机号" class="login_Class" style="border-left: none;width : 220px">
                    </td>
                    <td width="140">&nbsp;</td>
                </tr>
                <tr>
                    <td class="td-login part"><strong>*</strong>登录密码:</td>
                    <td><span class="login_pwd"></span><input type="password" id="pwd" name="pwd"  class="login_Class" placeholder="6-20个英文,数字,符号2种以上" style="border-left: none;width : 220px"></td>
                    <td width="140">&nbsp;</td>
                </tr>
                <tr>
                    <td class="td-login"></td>
                    <td valign="top" class="part">
                        <table width="210" cellspacing="0" id="strongtb" style="display:inline">
                            <tbody><tr align="center" bgcolor="#f5f5f5">
                                <td width="70" style="line-height:30px;" id="strength_L">弱</td>
                                <td width="70" style="line-height:30px;" id="strength_M">中</td>
                                <td width="70" style="line-height:30px;" id="strength_H">强</td>
                            </tr>
                            </tbody></table>
                    </td>
                    <td width="140"></td>
                </tr>
                <tr>
                    <td class="td-login"><strong>*</strong>确认密码:</td>
                    <td class="part"><span class="login_pwd"></span><input type="password" id="pwd2" class="login_Class" name="pwd2" placeholder="确认您的登录密码" style="border-left:none;width : 220px" ></td>
                    <td width="140" class="part">&nbsp;</td>
                </tr>
                <tr>
                    <td class="td-login"><strong>*</strong>验证码:</td>
                    <td class="part">
                        <input name="validate" id="validate" type="text" maxlength="4" size="36" class="login_Class" value="" placeholder="验证码" style="width: 120px;margin-right:15px;"><img style="position: absolute;" id="codeImg" width="75" height="35">
                    </td>
                    <td width="140" class="part">&nbsp;</td>
                </tr>
                <%--<tr id="ph-code-tr" class="dis">
                    <td></td>
                    <td class="part">
                        <input type="text" id="mobileCode" name="mobileCode" maxlength="6" size="36" class="login_Class" placeholder="手机验证码" style="width: 120px;margin-right:15px;">
                        <a href="javascript:void(0);" class="phone_code" id="phoneCode">获取手机验证码</a>
                        <span class="phone_code" style="color:grey;display: none" id="phoneCode2"><lable id="mes">90</lable>秒后重新获取</span>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                --%><tr>
                    <td class="td-login part"><strong>*</strong>联系人QQ:</td>
                    <td class="">
                        <span class=""></span>
                        <input type="text" id="QQ" name="QQ" placeholder="请输入联系人QQ" class="login_Class" style="width : 220px">

                    </td>
                    <td width="140">&nbsp;</td>
                </tr>
                <tr>
                    <td></td>
                    <td class="part" style="font-size: 12px">
                        <div style=" height:30px;float:left;">
                            <label>
                                <input type="checkbox" id="agree" name="agree" checked="" style="height: 29px;float: left;margin-right: 5px;">
                                <span class="grent">我已阅读并同意</span>
                                    <span class="grent" style="color: blue;">
                                        <a id="btnShowServiceInfo" href="javascript:;" class="blue-link" onclick="ServiceClick()">《星推服务条款》</a>
                                    </span>
                            </label>
                        </div>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="button" id="btn" name="btn" class="reg_btn submit-btn" style="cursor: pointer;" value="注册" onclick="register()"></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td></td>
                    <td style="padding-top:10px;">
                        <span class="color_999"> <a  href="<%=basePath%>api/xmtv1/toLogin.do">已有帐号，直接登录</a></span>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                </tbody></table>
            <div style="height: 60px"></div>
        </div>
        <div id="reg-success" class="left" style="display: none;">
            <table style="margin-left:30px; width:543px;">
                <tbody>
                <tr>
                    <td colspan="2">
                        <div class="success">
                            <div class="succtext"><em><img src="#"></em><span>恭喜你，<br>成功注册为星推<span style="float:initial;margin-left: 0;" id="reg-text-name">广告主</span></span></div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td  colspan="2">
                        <div class="enternext">
                            <ul>
                                <li><em class="enter"></em><a href="#">进入星推我的管理台</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="height: 60px"></div>
        </div>
        <div class="info_rh right">
            <h3 style="margin-left: 50px">星推三大优势让你轻松解决广告投放问题</h3>
            <ul>
                <li>
                    <div class="img1"></div>
                    <p style="margin-bottom: 10px"><strong>星推三大优势让你轻松解决广告投放问题</strong></p>
                    <p>星推三大优势让你轻松解决广告投放问题</p>
                </li>
                <li>
                    <div class="img2"></div>
                    <p><strong>星推三大优势让你轻松解决广告投放问题</strong></p>
                    <p>星推三大优势让你轻松解决广告投放问题</p>
                </li>
                <li>
                    <div class="img3"></div>
                    <p><strong>星推三大优势让你轻松解决广告投放问题</strong></p>
                    <p>星推三大优势让你轻松解决广告投放问题</p>
                </li>
            </ul>
        </div>
    </div>
    <div class="clear"></div>
</div>
    <!--页脚-->
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
</body>
</html>