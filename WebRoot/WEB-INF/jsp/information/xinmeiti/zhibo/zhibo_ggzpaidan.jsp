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
    <link rel="stylesheet" href="static/css/xinmeiti/gg_paidan.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/gg_paidan.js"></script>
    <title>派单列表</title>
</head>
<body>

<script type="text/javascript">
	function liuSourcejump(k){
		 if(k==1){
			 var url='api/xmtv1/toGgzorderGzhlist.do';
				window.location.href=url;
		}if(k==2){
			var url='api/xmtv1/toGgzorderPyqlist.do';
			window.location.href=url;
		}if(k==3){
			var url='api/xmtv1/toGgzorderWblist.do';
			window.location.href=url;
		}if(k==4){
			var url='api/guangGaoOrderList/zhiboselectlistpage.do';
			window.location.href=url;
		}if(k==5){
			var url='api/guangGaoOrderList/xinWenselectlistpage.do';
			window.location.href=url;
		}if(k==6){
			var url='api/guangGaoOrderList/guangGaoselectlistpage.do';
			window.location.href=url;
			
		}
	
	}
</script>
<!--顶部-->
<form action="api/guangGaoOrderList/zhiboselectlistpage.do" method="post" id="x">
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
                <a href="#" class="onx f1"><img src="static/images/xinmeiti/dingdanliebiao.png" alt=""/></a>
            </li>
        </ul>
    </div>
</div>
<div class="Resources">
    <ul>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(1)">微信公众号</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(2)">微信朋友圈</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(3)">微博</a></li>
        <li><a href="javascript:void(0)" style="color: #ffcc28;" onclick="liuSourcejump(4)">网红视频直播</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(5)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(6)">黄金广告商</a></li>
    </ul>
</div>
<!--主体-->
<!--派单列表-->
<div class="body">
    <div class="container">
        <div class="user-center">
            <!--<div class="header-tab" style="margin: 0 0 10px 0; border-bottom: none;">
                <a href="#" class="active">派单列表</a>
            </div>-->
            <div class="header_tab_rt">
                <span id="state0">全部订单</span>
                <span id="state1">已完成</span>
                <span id="state2">进行中</span>
                <span id="state3">拒单</span>
            </div>
        </div>
        <div class="my_Timer clear" style="padding: 20px 0 40px 0   ">
            <div class="w_water">
                订单名称
                <input type="text" class="run_name run_water " name="anchorOrder_title" value="">
            </div>
            <div class="w_water" style="margin-left: 40px;">
                订单编号
                <input type="text" class="run_name  run_water" name="anchorOrder_id" value="">
            </div>
            <div>
                <button class="btn_more btn btn-warning btn_alter " style="float: left">搜索</button>
            </div>

        </div>
        <table style="display: block"  class="table">
            <tbody>
            <tr>
                <th>订单编号</th>
                <th>订单名称</th>
                <th>平台</th>
                <th>价格</th>
                <th>执行时间</th>
                <th>订单状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list }" var="p">
            	<tr>
            		<th>${p.anchorOrder_id }</th>
            		<th>${p.anchorOrder_title }</th>
            		<th>${p.platformName }</th>
            		<th>${p.price }</th>
            		<th>${p.newsOrder_optime }</th>
            	<c:if test="${p.order_state =='0' }">
            	<th>未通过</th>
            	</c:if>
            	<c:if test="${p.order_state =='1' }">
            	<th>未开始</th>
            	</c:if>
            	<c:if test="${p.order_state =='2' }">
            	<th>进行中</th>
            	</c:if>
            	<c:if test="${p.order_state =='3' }">
            	<th>待确认</th>
            	</c:if>
            	<c:if test="${p.order_state =='4' }">
            	<th>已完成</th>
            	</c:if>
            	</tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="btn4">
            <button  class="btn" type="button">查看更多</button>
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
</form>
<script type="text/javascript">
	$(function(){
	var orderState="${order_state}";
	if(orderState==""){
		$("#state0").css("background-color","#ffcc28");
	}else if(orderState==4){
		$("#state1").css("background-color","#ffcc28");
	}else if(orderState==2){
		$("#state2").css("background-color","#ffcc28");
	}else{
		$("#state3").css("background-color","#ffcc28");
	}
	
	
		$("#sosuo").click(function(){
			$("#x").submit();
		});
		$("#state0").click(function(){
			$(this).append('<input type="hidden" name="order_state" id="st" value='+ +'>');
			$("#x").submit();
		});
		$("#state1").click(function(){
			$(this).append('<input type="hidden" name="order_state" id="st" value='+4+'>');
			$("#x").submit();
		});
		$("#state2").click(function(){
			$(this).append('<input type="hidden" name="order_state" id="st" value='+2+'>');
			$("#x").submit();
		});
		$("#state3").click(function(){
			$(this).append('<input type="hidden" name="order_state" id="st" value='+0+'>');
			$("#x").submit();
		});
	
	});
</script>
</body>
</html>