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
	<script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
	<title>中国运动知识竞赛，会难倒你吗？</title>
	<style>
		html,body {
			margin:0;
			padding:0;
			height: 100%;
			width: 100%;

		}
		#yaoqing{
			position: relative;
			height: 100%;
			width: 100%;margin:0;
			padding:0;
			display: block;
			background-image: url(<%=basePath%>static/images/mashu/yaoqing.jpg);
			background-size: 100% 100%;
		}
		#yao{
			position: absolute;
			left: 45px;
			bottom: 40%;
			width: 75%;
			line-height: 100px;
			overflow: hidden;
			text-align: center;
		}
		img{
			display: block;
			width: 60px;
			height: 60px;
			margin: 0 auto;
		}
		h1{
			color: #DBB788;
		}
		.friend{
			display: block;
			width: 100%;
			height: 50px;
			line-height: 50px;
			background-color: #DBB788;
			color: #721822;
		/*	padding:8% 10%;*/
			text-align: center;
			font-size: 2.0em;
			font-weight: 700;

		}
		a{
			text-decoration: none;
			color: #721822;
		}
		a:hover{
			text-decoration: none;
			color: #721822;
		}
	</style>
</head>
<body>
<div id="yaoqing">
	<div id="yao">
		<p><img src="<%=basePath%>static/images/mashu/ok.png" alt=""/></p>
		<h1>提交成功</h1>
		<a class="friend" href="#" id="yq">邀请好友一起参加</a>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#yq").click(function(){
			var url = '<%=basePath%>group/trunsafeter.do?type=yaoqing';
			window.location.href = url;
		})
	})

</script>
</body>
</html>