<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<meta content="width=device-width, height=device-height,initial-scale=1.0,
minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="<%=basePath%>static/css/mashu/common.css"/>
	<script src="<%=basePath%>static/js/mashu/uaredirect.js"></script>
	<script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
	<title>中国运动知识竞赛，会难倒你吗？</title>
	<style>
		html,body {
			margin:0;
			padding:0;
			height: 100%;
			width: 100%;
			overflow: hidden;
			text-align: center;
			background-image: url(<%=basePath%>/static/images/mashu/xialingyun.jpg);
			background-size: 100% 100%;
		}
		#up{
			width: 100%;
			height: 85%;
		}
		img{
			width: 55%;
			height:10%;
		}
		a{
			text-decoration: none;
		}
		a:hover{
			text-decoration: none;
		}
	</style>
	
</head>
<body>
<div id="up"></div>
<a href="#">
	<img id="baoming" src="<%=basePath%>static/images/mashu/baoming.png" alt=""/>
</a>

<script>
	$(function(){
		$("#baoming").click(function(){
			var url = '<%=basePath%>group/trunsafeter.do?type=jietiban';
			window.location.href = url;
		})
	})
</script>
</body>
</html>