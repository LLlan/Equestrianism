<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/edit_data.css"/>
    <script src="static/js/xinmeiti/jquery-2.1.1.min.js"></script>
    <script src="static/js/xinmeiti/edit_data.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>用户中心</title>
</head>
<body>
<!--顶部-->
<div class="main-nav">
    <div class="container top-header"  style="border: none;">
        <div class="company-name">
            <a href="#">
                <img src="static/images/xinmeiti/logonews.png" alt="link"/>
            </a>
        </div>
        <div class="h-right">
            <div class="user-quit ">
                账户名：<span>${advertiser.phone }</span>
                <a href="#">
                    <span class="e_meal">(0)</span>
                </a>
                        <span>
                            <a href="javascript:void(0)" onclick="tuichudl()">【退出】</a>
                        </span>
            </div>
            <script type="text/javascript">
				//退出登录 
				function tuichudl(){
					  var url='api/xmtv1/tuichudl.do';
					  window.location.href=url;
				}
			</script>
            <div class="help-conter ">
                <a href="#">账单查询</a>
            </div>
            <div class="personal-center ">
                <a href="#">订单列表</a>
            </div>
            <div class="sevice-m" style="text-align: center">客服电话：0571-86882710</div>
        </div>
    </div>
</div>
<!--导航栏-->
<div class="nav">
    <div class="nav_menu">
       <ul>
            <li>
                <a href="javascript:void(0)" class="f1" onclick="personZDjump(1)"><img src="static/images/xinmeiti/gerenzhongxin.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class="admin" onclick="personZDjump(2)"><img src="static/images/xinmeiti/resource.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class="f2" onclick="personZDjump(3)"><img src="static/images/xinmeiti/send.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class=" f3" onclick="personZDjump(4)"><img src="static/images/xinmeiti/query.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class="onx f4" onclick="personZDjump(5)"><img src="static/images/xinmeiti/user.png" alt=""/></a>
            </li>
        </ul>
    </div>
    <script type="text/javascript">
    	//跳转到资源管理首页
    	function personZDjump(data){
    		var url='api/xmtv1/personZDjump.do?num='+data;
    		window.location.href=url;
    	}
    </script>
    </div>
</div>
<!--资源管理-->
<!--<div class="Resources" style="display: none;">
    <ul>
        <li><a href="#">微博</a></li>
        <li><a href="#">微信朋友圈</a></li>
        <li><a href="#">微信公众号</a></li>
        <li><a href="#">网红视频直播</a></li>
        <li><a href="#">新闻媒体发布</a></li>
        <li><a href="">黄金广告商</a></li>
    </ul>
</div>-->
<!--充值-->
<div class="container maintop">
    <div class="line-wrap margin-lr">
        <div class="header-tab">
            <a href="javascript:void(0)" class="active">资料编辑</a>
            <a href="javascript:void(0)">修改密码</a>
        </div>
        <!--资料编辑-->
        <div class="form-wrap wrap1" style="display:block;">
            <div class="margin-lr">
                <form action="api/xmtv1/editInformation.do" method="post" id="informationForm">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>昵称<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入昵称" class="form-control" value="${advertiser.nikeName }" name="nikeName" id="nikeName"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>联系人<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入联系人名称" class="form-control" value="${advertiser.linkman }" name="linkman" id="linkman"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>微信账号<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入微信账号" class="form-control" value="${advertiser.weixinAccount }" name="weixinAccount" id="weixinAccount"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>常用邮箱<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入常用邮箱" class="form-control" value="${advertiser.email }" name="email" id="email"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <%-- 
                        <tr style="display: none;">
                            <td>持卡人姓名<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入持卡人姓名" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        --%>
                        <tr>
                            <td></td>
                            <td>
                                <div class="col-sm-3">
                                    <button class="btn btn-inye" type="button" onclick="saveinformation()">保存</button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!--修改密码-->
        <div class="form-wrap wrap2" style="display:none;">
            <div class="margin-lr">
                <%--<form action="" method="post" id="changePswForm">--%>
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>旧密码<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="password" placeholder="请输入旧密码" class="form-control" name="oldpassword" id="oldpassword"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>新密码<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="password" placeholder="6位以上字母和数字组合" class="form-control" name="newpassword" id="newpassword"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>确认密码<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="password" placeholder="请再次输入新密码" class="form-control" name="twopassword" id="twopassword"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="col-sm-3">
                                    <button class="btn btn-inye" type="button" onclick="savePassword()">保存</button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                <%--</form>--%>
            </div>
        </div>
    </div>
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
<script type="text/javascript">
	//修改密码-保存
	function savePassword(){
		//强：字母+数字+特殊字符 
		var pawReg1=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;     
		//中：字母+数字，字母+特殊字符，数字+特殊字符
		var pawReg2=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
		if($("#oldpassword").val()=='' || !pawReg1.test($("#oldpassword").val()) && !pawReg2.test($("#oldpassword").val())){
			$("#oldpassword").tips({
				side:2,
	            msg:'请正确输入原始密码',
	            bg:'#AE81FF',
	            time:1
	        });
			return;
		}
		if($("#newpassword").val()=='' || !pawReg1.test($("#newpassword").val()) && !pawReg2.test($("#newpassword").val())){
			$("#newpassword").tips({
				side:2,
	            msg:'请正确输入新密码',
	            bg:'#AE81FF',
	            time:1
	        });
			return;
		}
		if($("#newpassword").val()!=$("#twopassword").val()){
			$("#twopassword").tips({
				side:2,
	            msg:'两次密码输入不一致',
	            bg:'#AE81FF',
	            time:1
	        });
			return;
		}
		//$("#changePswForm").submit();
		$.ajax({
			type:"post",
			url:"api/xmtv1/changePassword.do",
			data:{
				oldpassword:$("#oldpassword").val(),
				newpassword:$("#newpassword").val()
			},
			dataType:"json",
			success:function(data){
				if(data.result=='success'){
					window.location.href='api/xmtv1/personZDjump.do?num=1';
				}else if(data.result=='error'){
					window.location.href='api/xmtv1/toLogin.do';
				}else{
					$("#oldpassword").tips({
						side:2,
			            msg:'原始密码输入错误',
			            bg:'#AE81FF',
			            time:1
			        });
				}
			},
		});
	}
	//编辑信息-保存
	function saveinformation(){
		if($("#nikeName").val()==''){
			$("#nikeName").tips({
				side:2,
	            msg:'昵称不能为空',
	            bg:'#AE81FF',
	            time:1
	        });
			return;
		}
		if($("#linkman").val()==''){
			$("#linkman").tips({
				side:2,
	            msg:'请输入联系人姓名',
	            bg:'#AE81FF',
	            time:1
	        });
			return;
		}
		if($("#weixinAccount").val()==''){
			$("#weixinAccount").tips({
				side:2,
	            msg:'请输入微信账号',
	            bg:'#AE81FF',
	            time:1
	        });
			return;
		}
		if($("#email").val()==''){
			$("#email").tips({
				side:2,
	            msg:'请输入常用邮箱',
	            bg:'#AE81FF',
	            time:1
	        });
			return;
		}
		$("#informationForm").submit();
	}
</script>
</body>
</html>