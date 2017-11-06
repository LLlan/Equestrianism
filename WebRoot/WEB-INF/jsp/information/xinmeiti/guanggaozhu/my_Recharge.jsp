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
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/my_Recharge.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/my_Recharge.js"></script>
    <title>我的充值</title>
    
    
    
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
                <a href="#" class="onx"><img src="../images/xmt/gerenzhongxin.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="../images/xmt/wbh.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="../images/xmt/wph.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="../images/xmt/wxh.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="../images/xmt/wh.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="../images/xmt/xw.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="../images/xmt/ggs.png" alt=""/></a>
            </li>
        </ul>
    </div>
</div>
<!--充值-->
<div class="container maintop">
    <div class="line-wrap margin-lr">
        <div class="header-tab">
            <a href="#" class="active">支付宝充值</a>
            <a href="#">微信充值</a>
            <a href="#">银行卡支付</a>
        </div>
        <!--支付宝充值-->
        <div class="form-wrap wrap1" style="display:block">
            <div class="margin-lr">
                <form action="" method="post" target="_blank">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>支付宝充值</td>
                            <td>
                                <div class="col-sm-3">
                                    <select class="form-control" >
                                        <option value="1">支付宝</option>
                                        <option value="2">网银转账(支付宝)</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>转账金额</td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入金额" class="form-control"/>
                                        <span class="input-group-addon">元</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="col-sm-3">
                                    <button class="btn btn-inye">立即支付</button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!--微信充值-->
        <div class="form-wrap wrap2" style="display: none">
            <div class="margin-lr">
                <form action="" method="post" target="_blank">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>转账金额</td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入金额" class="form-control"/>
                                        <span class="input-group-addon">元</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="col-sm-3">
                                    <button class="btn btn-inye">立即支付</button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!--银行转账-->
        <div class="form-wrap wrap3" style="display: none">
            <div class="margin-lr">
                <table class="table  bank-info">
                    <tbody>
                    <tr>
                        <td class="center" rowspan="3">账号</td>
                        <td>收款人</td>
                        <td>海南有限公司</td>
                    </tr>
                    <tr>
                        <td style="text-align: center">账号</td>
                        <td><b>76373753237563</b></td>
                    </tr>
                    <tr>
                        <td style="text-align: center">开户行</td>
                        <td>中国银行</td>
                    </tr>
                    </tbody>
                </table>
                <table class="table  bank-money">
                    <tbody>
                    <tr>
                        <td class="center">转账金额</td>
                        <td>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" class="form-control "  placeholder="请输入金额" >
                                    <span class="input-group-addon">元</span>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="center">备注</td>
                        <td>
                            <div class="col-sm-5">
                                <textarea class="form-control " placeholder="请输入备注"cols="30" rows="5"></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <div class="col-sm-3">
                                <button class="btn btn-inye">确认转账</button>
                            </div>
                        </td>
                    </tr>
                    </tbody></table>
            </div>
        </div>
    </div>
</div>
<!--页脚-->
<div id="footer">
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
</body>
</html>