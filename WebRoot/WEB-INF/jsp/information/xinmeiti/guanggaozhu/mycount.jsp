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
    <link rel="stylesheet" href="static/css/xinmeiti/mycount.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/mycount.js"></script>
    <title>我的账户</title>
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
                      <a href="javascript:void(0)" id="go_order_list" onClick="go_order_list()">订单列表</a>
                    </div>
                    <script type="text/javascript">
						function go_order_list(){
							var url='api/xmtv1/toGgzorderGzhlist.do';
							window.location.href=url;
						};
					</script>
                    <div class="sevice-m" style="text-align: center">客服电话：0571-86882710</div>
            </div>
        </div>
    </div>
<!--导航栏-->
<div class="nav">
    <div class="nav_menu">
        <ul>
            <li>
                <a href="<%=basePath%>api/xmtv1/personGgzJump.do?num=1" class="onx"><img src="static/images/xinmeiti/gerenzhongxin.png" alt=""/></a>
            </li>
            <li>
                <a href="<%=basePath%>api/xmtv1/weibo.do"><img src="static/images/xinmeiti/wbh.png" alt=""/></a>
            </li>
            <li>
                <a href="<%=basePath%>api/xmtv1/pengyouquan.do"><img src="static/images/xinmeiti/wph.png" alt=""/></a>
            </li>
            <li>
                <a href="<%=basePath%>api/xmtv1/gongzhonghao.do"><img src="static/images/xinmeiti/wxh.png" alt=""/></a>
            </li>
            <li>
                <a href="<%=basePath%>api/xmtv2/videopaltlistPage.do"><img src="static/images/xinmeiti/wh.png" alt=""/></a>
            </li>
            <li>
                <a href="<%=basePath%>api/xmtv2/medialistPage.do"><img src="static/images/xinmeiti/xw.png" alt=""/></a>
            </li>
            <li>
                <a href="<%=basePath%>api/xmtv2/adverlistPage.do"><img src="static/images/xinmeiti/ggs.png" alt=""/></a>
            </li>
        </ul>
    </div>
</div>
<!--主体-->
<div class="statistics-money">
    <div class="container">
        <div class="money-inter">
            <div class="block user-info">
                <div>
                    <span class="a1"></span>
                    ${advertiser.nikeName }
                </div>
                <div>
                    <span class="a2"></span>
                    ${advertiser.phone }
                </div>
                <div>
                    <span class="a3"></span>
                    ${advertiser.company }
                </div>
                <div>
                    <span class="a4"></span>
                    ${advertiser.linkman }
                </div>
                <div class="pay">
                    <a  href="#" class="btn btn-primary center">充值</a>
                </div>
            </div>
            <div class="block total">
                <div class="img">
                    <img src="static/images/xinmeiti/zongjine.png" alt=""/>
                </div>
                <div class="tip">总金额</div>
                <div class="number">${advertiser.zhangHYE }元</div>
            </div>
            <div class="block card">
                <div class="img">
                    <img src="static/images/xinmeiti/keyongjine.png" alt=""/>
                </div>
                <div class="tip">可用金额</div>
                <div class="number">${advertiser.zhangHYE-advertiser.dongJJE }元</div>
            </div>
            <div class="block forzen">
                <div class="img">
                    <img src="static/images/xinmeiti/dongjiejine.png" alt=""/>
                </div>
                <div class="tip">冻结金额</div>
                <div class="number">${advertiser.dongJJE }元</div>
            </div>
        </div>
        <div class="order-info-wrap">
            <div class="block">
                <img src="static/images/xinmeiti/dingdanzongshu.png" style="vertical-align: middle;" alt="">
                <span class="tip">总订单数</span>
                <span class="info ">0单</span>
            </div>
            <div class="block">
                <img src="static/images/xinmeiti/jinxingzhongdedingdan.png" style="vertical-align: middle;" alt="">
                <span class="tip">进行中的订单数</span>
                <span class="info ">0单</span></div>
        </div>
    </div>
</div>
<!--派单列表-->
<!--<div class="body">
    <div class="container">
        <div class="user-center">
            <div class="header-tab" style="margin: 0 0 10px 0; border-bottom: none;">
                <a href="#" class="active">订单列表</a>
            </div>
        </div>
        <table style="display: block"  class="table tablex">
            <tbody>
            <tr>
                <th>订单编号</th>
                <th>订单名称</th>
                <th>平台</th>
                <th>开始时间</th>

                <th>创建时间</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
           <tr>
                <td colspan="8">暂无数据</td>
            </tr>
            </tbody>
        </table>
        <table style="display: none"  class="table tabley">
            <tbody>
            <tr>
                <th>订单编号</th>
                <th>活动名称</th>
                <th>平台</th>
                <th>预约结果反馈时间</th>
                <th>预计推广时间</th>
                <th>创建时间</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="8">暂无数据</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>-->
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