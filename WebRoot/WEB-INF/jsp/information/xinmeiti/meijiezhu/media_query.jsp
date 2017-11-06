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
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/media_query.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/media_query.js"></script>
    <title>账单查询</title>
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
                账户名：<span>18864875632</span>
                <a href="#">
                    <span class="e_meal">(0)</span>
                </a>
                        <span>
                            <a href="#">【退出】</a>
                        </span>
            </div>
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
                <a href="javascript:void(0)" class=" f2" onclick="personZDjump(3)"><img src="static/images/xinmeiti/send.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class="onx f3" onclick="personZDjump(4)"><img src="static/images/xinmeiti/query.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class=" f4" onclick="personZDjump(5)"><img src="static/images/xinmeiti/user.png" alt=""/></a>
            </li>
        </ul>
    </div>
    <script type="text/javascript">
    	//跳转到资源管理首页
    	function personZDjump(data){
    		var url='api/xmtv1/personZDjump?num='+data;
    		window.location.href=url;
    	}
    </script>
</div>
<!--资源管理-->
<div class="Resources" style="display: none;">
    <ul>
        <li><a href="javascript:void(0)" style="color: #ffcc28;">微信公众号</a></li>
        <li><a href="javascript:void(0)">微信朋友圈</a></li>
        <li><a href="javascript:void(0)">微博</a></li>
        <li><a href="javascript:void(0)">网红视频直播</a></li>
        <li><a href="javascript:void(0)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)">黄金广告商</a></li>
    </ul>
</div>
<!--主体-->
<div class="container top user-center">
    <div class="line-wrap margin-left">
        <div class="header-top marginLeft">
            <h4 class="page-inner-title">当前帐户信息</h4>
        </div>
        <div class="bill-wrap padding">
            <table class="table table-hover">
                <tbody><tr>
                    <th>账户名</th>
                    <th>账户余额</th>
                    <th>已提现</th>
                </tr>
                <tr>
                    <td>人来人往</td>
                    <td>￥0.00</td>
                    <td>￥0.00</td>
                </tr>
                </tbody>
            </table>
            <button  class="btn btn-sm">提现绑卡</button>
        </div>
        <div class="header-top marginLeft">
            <h4>账单明细</h4>
        </div>
        <div class="padding">
            <table class="table table-hover">
                <tbody>
                <tr>
                    <th>订单号</th>
                    <th>日期</th>
                    <th>活动名称</th>
                    <th>资源名</th>
                    <th>金额</th>
                </tr>
                <tr>
                    <td>123456789</td>
                    <td>2017-03-56 14:02:30</td>
                    <td>夏令营</td>
                    <td>夏令营</td>
                    <td>￥300.00</td>
                </tr>
                <tr>
                    <td colspan="5" style="display: none;">
                        暂无数据
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="txt-center">
                <ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
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
<script>
    $(function(){
        var GG = {
            "kk":function(mm){
                // alert(mm);
            }
        }

        $("#page").initPage(71,1,GG.kk);
    })
</script>
</body>
</html>