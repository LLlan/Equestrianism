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
minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
          name="viewport">
    <title>中国运动知识竞赛，会难倒你吗？</title>
    <style>
        html,body {
            margin:0;
            padding:0;
            height: 100%;
            width: 100%;
            overflow: hidden;
            text-align: center;
            background-image: url(<%=basePath%>static/images/mashu/fenxiang.jpg);
            background-size: 100% 100%;
        }
    </style>
</head>
<body>
<div style='margin:0 auto;display:none;'>
<img src="<%=basePath%>static/images/mashu/fengxiang300.jpg"/>
</div>
</body>
</html>