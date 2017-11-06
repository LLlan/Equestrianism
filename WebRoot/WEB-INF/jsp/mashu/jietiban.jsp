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
    <link rel="stylesheet" href="<%=basePath%>static/css/mashu/common.css"/>
    <script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
    <title>中国运动知识竞赛，会难倒你吗？</title>
    <style>
        html,body {
            margin:0;
            padding:0;
            height: 100%;
            width: 100%;
            text-align: center;
            background-image: url(<%=basePath%>static/images/mashu/jietiban.jpg);
            background-size: 100% 100%;
        }
        #ji{
            width: 100%;
            height: 72%;
        }
        #jie{
            width: 80%;
            height: auto;
            margin: 0 auto;
            text-align: center;
            border: 1px solid #000;
        }
        img{
            width: 43%;
            height: 10%;
        }
        #jie .R1{
            color: #C52027;
            font-size: 2em;
            font-weight: bold;
            margin-bottom: 8px;
        }
        #jie .R2,.R3,.R4{
            width: 100%;
            height: 2.5em;
            line-height: 2.5em;
            font-size: 1.5em;
            font-weight: bold;
            color: #C52027;
            border-bottom: 1px solid #C52027;
        }
       /* #bj{
            width: 100%;
            height: 20%;

        }*/
    </style>
</head>
<body>
<img src="<%=basePath%>static/images/mashu/fengxiang300.jpg" style="height:0;width:0;"/>
<div id="ji"></div>
<a href="#" onclick="get22();"><img id="jiefeng"  src="<%=basePath%>static/images/mashu/jiefeng.jpg" alt=""/></a>
<div id="bj"></div>
<script>
    $(function(){
        
    })
    
    function get22(){
    		var url = '<%=basePath%>group/trunsafeter.do?type=get';
			window.location.href = url;
   			
    }
</script>
</body>
</html>