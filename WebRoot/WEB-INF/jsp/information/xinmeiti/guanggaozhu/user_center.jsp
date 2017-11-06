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
<base href="<%=basePath%>">">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/my_Recharge.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/my_Recharge.js"></script>
    <title>用户中心</title>
</head>
<body>
<!--顶部-->
<div class="main-nav">
    <div class="container top-header">
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
            <div class="user_conter">
                <a href="#">用户中心</a>
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
                <a href="#" class="onx"><img src="static/images/xinmeiti/gerenzhongxin.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="static/images/xinmeiti/wbh.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="static/images/xinmeiti/wph.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="static/images/xinmeiti/wxh.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="static/images/xinmeiti/wh.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="static/images/xinmeiti/xw.png" alt=""/></a>
            </li>
            <li>
                <a href="#"><img src="static/images/xinmeiti/ggs.png" alt=""/></a>
            </li>
        </ul>
    </div>
</div>
<!--充值-->
<div class="container maintop" style="display: block;">
    <div class="line-wrap margin-lr">
        <div class="header-tab">
            <a href="#"  class="active">资料编辑</a>
            <a href="#">修改密码</a>
        </div>
        <!--资料编辑-->
        <div class="form-wrap wrap1" style="display:block;">
            <div class="margin-lr">
                <form action="" method="post" target="_blank">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>昵称<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入昵称" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>联系人<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入联系人" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>公司名称<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入公司名称" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>常用邮箱<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入常用邮箱" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
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
                        <tr>
                            <td></td>
                            <td>
                                <div class="col-sm-3">
                                    <button class="btn btn-inye">保存</button>
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
                <form action="" method="post" target="_blank">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>旧密码<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入旧密码" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>新密码<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请输入新密码" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>确认密码<span><img src="static/images/xinmeiti/star.png" alt=""></span></td>
                            <td>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" placeholder="请再次输入新密码" class="form-control"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="col-sm-3">
                                    <button class="btn btn-inye">保存</button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
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