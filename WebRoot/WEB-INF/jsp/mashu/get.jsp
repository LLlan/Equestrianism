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
    <script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
    <title>准备答题</title>
</head>
<style>
    html,body {
        margin:0;
        padding:0;
        height: 100%;
        width: 100%;
        text-align: center;
        background-image: url(<%=basePath%>static/images/mashu/enter.jpg);
        background-size: 100% 100%;
    }
    #up{
        width: 100%;
        height: 80%;

    }
   img{
       width: 60%;
       height: 10%;
   }
    a{
        text-decoration: none;
    }
    a:hover{
        text-decoration: none;
    }
</style>
<body>
<div id="up"></div>
<a href="#">
    <img id="get" src="<%=basePath%>static/images/mashu/get.png" alt=""/>
</a>
<script>
   $(function(){
		$("#get").click(function(){
			 	var url = '<%=basePath%>group/getMashubyRandomID.do?tihao=1';
				window.location.href = url;
		})
	})
</script>
</body>
</html>