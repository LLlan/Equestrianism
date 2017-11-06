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
	<link rel="stylesheet" href="<%=basePath%>static/css/mashu/style1.css"/>
	<script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
	<title>我对马术还是很了解的嘛，成绩不错呀</title>

</head>
<body>
<div class="container">
	<div class="myCanvas">
		<div class="title col-flex-1">
			<p>2017年中国运动知识竞赛(马术篇)</p>
			<ul>
				<li class="col-flex-2"><img src="<%=basePath%>static/images/mashu/1.png"/></li>
				<li class="col-flex-3"><h3>绝密试卷</h3></li>
				<li class="col-flex-2"><img src="<%=basePath%>static/images/mashu/2.png"/></li>
			</ul>
		</div>
		<div class="middle">
			<p>恭喜你！在第一届中国</p>
			<p>运动知识竞赛</p>
		</div>
		<div class="footer col-flex-1">
			<div class="f1"><a class="grade" href="#">您考了<span>${fenshu}</span>分</a></div>
			<div class="f2"><a class="prefer"  id="chongkao" href="#">我不甘心，我要重考</a></div>
			<div class="f3"><a class="prefer"  id="shaishai" href="#">点击右上角分享，晒晒我的成绩</a></div>
			<c:if test="${fenshu>=60}">
				<div class="f4"><a id="tiyan" class="prefer">太棒了！<br/>我要去体验真正的马术</a></div>
			</c:if>
		</div>
	</div>
</div>
	<script type="text/javascript">
			$(function(){
				$("#tiyan").click(function(){
					var url = '<%=basePath%>group/trunsafeter.do?type=tiaozhan';
					window.location.href = url;
				})
				
				$("#chongkao").click(function(){
					var url = '<%=basePath%>group/chongkao.do';
						$.ajax({
								type: "post",
								url: url,
								data: {},
								dataType:"json",
								success:function(data){
									if(data.result=="1"){
										var url = '<%=basePath%>group/getMashubyRandomID.do?tihao=1';
										window.location.href = url;
									}else{
									}
								}
						});
					
				})
				
				<%-- $("#shaishai").click(function(){
					var url = '<%=basePath%>group/trunsafeter.do?type=shaishai';
					window.location.href = url;
				}) --%>
			});
	
	</script>
</body>
</html>