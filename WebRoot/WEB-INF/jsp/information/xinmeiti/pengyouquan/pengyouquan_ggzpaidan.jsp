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
        <li><a href="javascript:void(0)" onclick="liuSourcejump(2)" style="color: #ffcc28;">微信朋友圈</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(3)">微博</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(4)">网红视频直播</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(5)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(6)">黄金广告商</a></li>
    </ul>
</div>
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
			
		}if(k==5){
			var url='api/guangGaoOrderList/xinWenselectlistpage.do';
			window.location.href=url;
		}if(k==6){
			var url='api/guangGaoOrderList/guangGaoselectlistpage.do';
			window.location.href=url;
			
		}
	
	}
</script>
<!--主体-->
<!--派单列表-->
<div class="body">
    <div class="container">
        <div class="user-center">
            <!--<div class="header-tab" style="margin: 0 0 10px 0; border-bottom: none;">
                <a href="#" class="active">派单列表</a>
            </div>-->
            <div class="header_tab_rt">
                <span class="active" title="">全部订单</span>
                <span title="4">已完成</span>
                <span title="3">待确认</span>
                <span title="2">进行中</span>
                <span title="1">未开始</span>
                <span title="0">未通过</span>
            </div>
        </div>
        <script type="text/javascript">
        	$(function() {
        		/*筛选状态*/
        	    $(".header_tab_rt span").each(function(){
        	    	if($(this).attr("title")==$("#order_state").val()){
        	    		$(this).addClass("active").siblings().removeClass("active");
        	    	}
        	    });
        	    $(".header_tab_rt span").click(function(){
        	        $(this).addClass("active").siblings().removeClass("active");
        	        $("#order_state").val($(this).attr("title"));
        	        $("#order_name").val("");
        	        $("#order_number").val("");
        	        $("#shaixuanForm").submit();
        	    });
			});
        </script>
        <form action="<%=basePath %>api/xmtv1/toGgzorderPyqlist.do" id="shaixuanForm" method="post"> 
        	<input type="hidden" name="order_state" id="order_state" value="${pd.order_state }">
        	<div class="my_Timer clear" style="padding: 20px 0 40px 0   ">
	            <div class="w_water">
	                订单名称
	                <input type="text" class="run_name run_water " name="order_name" id="order_name" value="${pd.order_name }">
	            </div>
	            <div class="w_water" style="margin-left: 40px;">
	                订单编号
	                <input type="text" class="run_name  run_water" name="order_number" id="order_number" value="${pd.order_number }">
	            </div>
	            <div>
	                <button class="btn_more btn btn-warning btn_alter" style="float:left" type="submit">搜索</button>
	            </div>
        	</div>
        </form>
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
                --%>
                <th>价格</th>
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
                <th>${list.name }</th>
                <th>${list.type_name }</th>
                <th>${list.resource_name }</th>
                <th>${list.account }</th>
                <%--
                <th>资源头像</th>
                --%>
                <th>${list.price }</th>
                <th>${list.beginTime }</th>
                <th>
                	<c:if test="${list.order_state=='0' }">未通过</c:if>
                	<c:if test="${list.order_state=='1' }">未开始</c:if>
                	<c:if test="${list.order_state=='2' }">进行中</c:if>
                	<c:if test="${list.order_state=='3' }">待确认</c:if>
                	<c:if test="${list.order_state=='4' }">已完成</c:if>
                </th>
                <th>
                	<a href="javascript:void(0)">查看</a>
                </th>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--<div class="btn4">
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