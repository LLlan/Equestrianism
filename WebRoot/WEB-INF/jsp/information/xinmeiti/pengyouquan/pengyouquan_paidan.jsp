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
    <link rel="stylesheet" href="static/css/xinmeiti/paidan.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/paidan.js"></script>
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>
    <script src="static/js/xinmeiti/page.js"></script>
    <title>派单列表</title>
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
                <a href="javascript:void(0)" class="onx f2" onclick="personZDjump(3)"><img src="static/images/xinmeiti/send.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class=" f3" onclick="personZDjump(4)"><img src="static/images/xinmeiti/query.png" alt=""/></a>
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
<div class="Resources">
    <ul>
        <li><a href="<%=basePath %>api/xmtv1/toMjzorderGzhlist.do">微信公众号</a></li>
        <li><a href="<%=basePath %>api/xmtv1/toMjzorderPyqlist.do" style="color: #ffcc28;">微信朋友圈</a></li>
        <li><a href="<%=basePath %>api/xmtv1/toMjzorderWblist.do">微博</a></li>
        <li><a href="javascript:void(0)">网红视频直播</a></li>
        <li><a href="javascript:void(0)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)">黄金广告商</a></li>
    </ul>
</div>
<!--主体-->
<!--派单列表-->
<div class="body">
    <div class="container">
        <div class="user-center">
            <div class="header-tab" style="margin: 0 0 10px 0; border-bottom: none;">
                <a href="#" class="active">派单列表</a>
            </div>
            <div class="header_tab_rt">
                <span class=" active btn1">可执行</span>
                <span class="btn2">已完成</span>
                <span class="btn3">全部</span>
            </div>
        </div>
        <table style="display: block"  class="table">
            <tbody>
            <tr>
                <th>订单编号</th>
                <th>创建时间</th>
                <th>订单名称</th>
                <th>推广形式</th>
                <th>资源名称</th>
                <th>资源账号</th>
                <%--
                <th>资源头像</th>
                
                <th>价格</th>
                --%>
                <th>执行时间</th>
                <th>订单状态</th>
                <th>操作</th>
            </tr>
            </tbody>
            <tbody>
            <c:forEach items="${list }" var="list" end="9">
            <tr>
            	<th>${list.order_number }</th>
            	<th>${list.order_time }</th>
                <th>${list.order_name }</th>
                <th>${list.type_name }</th>
                <th>${list.source_name }</th>
                <th>${list.account }</th>
                <%--
                <th>资源头像</th>
                
                <th>${list.price }</th>
                --%>
                <th>${list.beginTime }</th>
                <th>
                	<c:if test="${list.mjzorder_state=='0' }">拒单</c:if>
                	<c:if test="${list.mjzorder_state=='1' }">未开始</c:if>
                	<c:if test="${list.mjzorder_state=='2' }">已接单</c:if>
                	<c:if test="${list.mjzorder_state=='3' }">已宣传</c:if>
                	<c:if test="${list.mjzorder_state=='4' }">已完成</c:if>
                </th>
                <th>
                	<a href="javascript:void(0)">查看</a>
                </th>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <%-- 
        <div class="btn4">
            <button  class="btn" type="button">查看更多</button>
        </div>
        --%>
        <div class="txt-center">
            <ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
        </div>
        <script type="text/javascript">
        	 var GG = {
        		        "kk":function(mm){
        		            // alert(mm);
        		        }
        		   };
        	$("#page").initPage(71,1,GG.kk);
        
        </script>
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
</body>
</html>