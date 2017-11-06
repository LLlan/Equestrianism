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
	<link href="<%=basePath%>static/css/mashu/mobiscroll.scroller.css" rel="stylesheet" />
	<link href="<%=basePath%>static/css/mashu/mobiscroll.scroller.android-ics.css" rel="stylesheet" />
	<script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
	<script src="<%=basePath%>static/js/mashu/mobiscroll.zepto.js" type="text/javascript"></script>
	<script src="<%=basePath%>static/js/mashu/mobiscroll.core.js" type="text/javascript"></script>
	<script src="<%=basePath%>static/js/mashu/mobiscroll.scroller.js" type="text/javascript"></script>
	<script src="<%=basePath%>static/js/mashu/mobiscroll.area.js"></script>
	<script src="<%=basePath%>static/js/mashu/mobiscroll.scroller.android-ics.js"></script>
	<script src="<%=basePath%>static/js/mashu/mobiscroll.i18n.zh.js"></script>
	<title>中国运动知识竞赛，会难倒你吗？</title>
	<style>
		.col-flex-0{
			-webkit-box-flex: 0;
			-webkit-flex: 0;
			flex: 0;
		}
		.col-flex-1{
			-webkit-box-flex: 1;
			-webkit-flex: 1;
			flex: 1;
		}
		.col-flex-2{
			-webkit-box-flex: 2;
			-webkit-flex: 2;
			flex: 2;
		}
		.col-flex-3{
			-webkit-box-flex: 3;
			-webkit-flex: 3;
			flex: 3;
		}
		.col-flex-4{
			-webkit-box-flex: 4;
			-webkit-flex: 4;
			flex: 4;
		}
		.col-flex-5{
			-webkit-box-flex: 5;
			-webkit-flex: 5;
			flex: 5;
		}
		img{
			max-width:100%;
		}
		html,body{
			margin: 0;
			padding: 0;
			height: 100%;
			width: 100%;
		}
		.container{
			background:url(<%=basePath%>static/images/mashu/yaoqing.jpg) no-repeat;
			background-size:100% 100%;
			display: block;
			width:100%;
			height:100%;
		}
		.myCanvas{
			width:80%;
			height:87%;
			margin:auto;
			position: relative;
			top: 6%;
			display: -webkit-flex;
			display: flex;
			-webkit-flex-direction: column;
			flex-direction: column;
		}
		.myCanvas .d1{
			width: 100%;
			text-align: center;
			color: #D2A975;
			font-size: 1.5em;
			font-weight: bold;
			margin-bottom: 1em;
		}
		.item {
			height: 35px;
			line-height: 35px;
			margin-bottom: 5px;
			/*position: relative;
			z-index: 1;*/
			/*margin-bottom: 20px;*/
		}

		.label {
			float: left;
			width: 26%;
			height: 35px;
			line-height: 35px;
			text-align: center;
			font-size: 15px;
			color: #721822;
			margin-right: 4%;
			font-weight: bold;
			background-color: #D2A975;

		}
		.text {
			float: right;
			width: 67%;
			height: 33px;
			line-height: 33px;
			border: none;
			/* background-color: #D2A975; */
		}
		.item select{
			width: 32%;
			height: 35px;
			line-height: 35px;
			background-color: #D2A975;
			font-size: 15px;
			font-weight: bold;
			color: #721822;
			text-align: center;
		}

		.btn-img {
			width: 50%;
			height: 30px;
			line-height: 30px;
			background-color: #D2A975;
			-moz-border-radius: 3px;
			-webkit-border-radius: 3px;
			border-radius: 3px;
			display: inline-block;
			border: 0 none;
			font-size: 15px;
			font-weight: bold;
			color: #721822;
			text-align: center;
			margin: 10px 25%;
		}
		.d3{
			width: 90%;
			padding:0 5%;
			border: 1px solid #914841;
			margin-top: 15px;
			text-align: left;
		}
		.d3 .F1{
			color: #DBB788;
			font-size: 1.2em;
			font-weight: bold;
			margin: 1px 0;
			text-align: center;
			letter-spacing: 2px;
		}
		.d3 .F2,.F3{
			color: #CC9E76;
			margin: 3px 0;
		}
		.d4{
			text-align: center;
			color: #DBB788;
			font-weight: bold;
		}
	</style>
</head>
<body>
<div class="container">
	<div class="myCanvas">
		<div class="d1">敢不敢来向驯服小马，发起挑战？</div>
		<!--form表单-->
		<div id="tioazhan ">
			<form action="<%=basePath%>group/saveJoin.do" method="post">
				<div class="items ">
					<div class="item ">
						<span class="label">姓名</span>
						<div class="input">
							<input type="text" id="join_name" name="join_name" class="text">
						</div>
					</div>
					<div class="item">
						<span class="label">电话号码</span>
						<div class="input">
							<input type="text" id="join_phone" name="join_phone" class="text">
						</div>
					</div>
					<div class="item">
						<span class="label">所在地区</span>
						<!--<select  name="province" id="province">
							<option value="省份" selected="selected">省份</option>
						</select>
						<select  name="city" id="city">
							<option value="城市" selected="selected">城市</option>
						</select>-->
						<input style="color: red;" class="text" id="area" name="join_address" placeholder="请选择所在地区" areaid="10064 10043 10375">
					</div>
					<div class="item">
						<input type="submit" class="btn-img" id="registsubmit" value="立即提交" />
					</div>
				</div>
			</form>
		</div>
		<!--活动说明-->
		<div class="d3 ">
			<p class="F1">活动说明</p>
			<p class="F2">1.线下活动时间：2017年7月(具体时间在通知)活动地点：东莞市长安镇金伯乐马术学府</p>
			<p class="F3">2.将从报名成功的粉丝中抽选20名，参与以纯暑期马术体验日活动；还有机会获赠以纯金伯乐马术大赛赛事限量公仔哦！机会均等，邀约体验吧</p>
		</div>
		<div class="d4">了解以纯金伯乐国际马术大奖赛</div>
	</div>
</div>
<script>
	$(function(){
		var valo = $("#area").attr("areaid");
		$('#area').scroller('destroy').scroller({ preset: 'area', theme: 'android-ics light', display: 'bottom',valueo:valo });

	});
</script>
</body>
</html>