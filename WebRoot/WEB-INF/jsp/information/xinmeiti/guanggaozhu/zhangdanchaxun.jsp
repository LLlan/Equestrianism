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
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/foundation-datepicker.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/foundation.min.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/zhangdanchaxun.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/foundation-datepicker.js"></script>
    <script src="static/js/xinmeiti/foundation-datepicker.zh-CN.js"></script>
    <script src="static/js/xinmeiti/my_Recharge.js"></script>
    <title>账单查询</title>
</head>
<body>
<!--顶部-->
<div class="main-nav">
    <div class="container top-header">
        <div class="company-name">
            <a href="#">
                <img src="../images/xmt/logonews.png" alt="link"/>
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
                <a style="display:block;margin-top: 4px" href="#" class="onx"><img src="../images/xmt/gerenzhongxin.png" alt=""/></a>
            </li>
            <li>
                <a style="display:block;margin-top: 4px" href="#"><img src="../images/xmt/wbh.png" alt=""/></a>
            </li>
            <li>
                <a style="display:block;margin-top: 4px" href="#"><img src="../images/xmt/wph.png" alt=""/></a>
            </li>
            <li>
                <a style="display:block;margin-top: 4px" href="#"><img src="../images/xmt/wxh.png" alt=""/></a>
            </li>
            <li>
                <a style="display:block;margin-top: 4px" href="#"><img src="../images/xmt/wh.png" alt=""/></a>
            </li>
            <li>
                <a style="display:block;margin-top: 4px" href="#"><img src="../images/xmt/xw.png" alt=""/></a>
            </li>
            <li>
                <a style="display:block;margin-top: 4px" href="#"><img src="../images/xmt/ggs.png" alt=""/></a>
            </li>
        </ul>
    </div>
</div>
<!--主体-->
<div class="container top user-center">
    <div class="line-wrap margin-left">
        <div class="header-top marginLeft">
            <h4 class="page-inner-title">当前帐户信息</h4>&nbsp; <button  class="btn btn-inye btn-sm">立即充值</button>
        </div>
        <div class="bill-wrap padding">
            <table class="table table-hover">
                <tbody><tr>
                    <th>账户名称</th>

                    <th>余额</th>
                    <th>可用资金</th>
                    <th>冻结资金</th>
                </tr>
                <tr>
                    <td>sldfgsg</td>
                    <td>￥0.00</td>
                    <td>￥0.00</td>
                    <td>￥0.00</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="header-top marginLeft">
            <h4>充值明细</h4>
        </div>
        <div class="search-wrap form-inline padding ">
            <div class="input-group input-append input-prepend">
                <input type="text" class="form-control"  placeholder="开始时间" id="demo-1">
            </div>
            -
            <div class="input-group  ">
                <input type="text" class="form-control " placeholder="结束时间" id="demo-2">
            </div>
            <button  class="btn btn-inye" style="margin-top: -15px;">查询</button>
        </div>
        <div class="padding">
            <table class="table table-hover">
                <tbody><tr>
                    <th>充值流水号</th>
                    <th>充值时间</th>
                    <th>充值金额</th>
                    <th>充值类型</th>
                    <th>状态</th>
                </tr>
                <tr>
                    <td>123456789</td>
                    <td>2017-03-56 14:02:30</td>
                    <td>￥300.00</td>
                    <td>支付宝</td>
                    <td>成功</td>
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
                    <img src="../images/xmt/xingtui.png" style="width: 100px;height: 100px;">
                </div>
            </div>
            <div class="box">
                <h3>官方微博</h3>
                <div>
                    <img src="../images/xmt/xinlang.png" style="width: 100px;height: 100px;">
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function tt(dd){
        //alert(dd);
    }
    var GG = {
        "kk":function(mm){
            // alert(mm);
        }
    }

    $("#page").initPage(71,1,GG.kk);
    $('#demo-1').fdatepicker({
        format: 'yyyy-mm-dd hh:ii',
        pickTime: true
    });
    $('#demo-2').fdatepicker({
        format: 'yyyy-mm-dd hh:ii',
        pickTime: true
    });
</script>
</body>
</html>