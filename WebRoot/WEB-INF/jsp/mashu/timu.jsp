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
	<link rel="stylesheet" href="<%=basePath%>static/css/mashu/style.css"/>
	<script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
	<title></title>
	<style type="text/css">
		.ckdn{
			width: 30px;
			display: flex;
			align-items:center;
			justify-content:center;
		}
		.xiayiti{
			width: 30px;
			display: flex;
			align-items:center;
			justify-content:center;
		}
	</style>
</head>
<body>
<div class="container">
	<div class="myCanvas">
		<div class="title">
			<p>2017年中国运动知识竞赛(马术篇)</p>
			<ul>
				<li class="col-flex-2"><img src="<%=basePath%>static/images/mashu/1.png"/></li>
				<li class="col-flex-3"><h3>绝密试卷/第${tihao}题</h3></li>
				<li class="col-flex-2"><img src="<%=basePath%>static/images/mashu/2.png"/></li>
			</ul>
		</div>
		<div class="err">
			<img id="bgimg" src="<%=basePath%>${resultPd.image1}" alt="" />
			<div id="errContent">
				<img src="<%=basePath%>static/images/mashu/error.png"/>
				<h2 class="quest">回答错误</h2>
			</div>
			<div id="okContent">
				<img src="<%=basePath%>static/images/mashu/ok.png"/>
				<h2 class="quest">回答正确</h2>
			</div>
		</div>
		
<%-- 		<div class="ok">
			<img src="<%=basePath%>static/images/mashu/(3).jpg" alt="" />
			<div id="okContent">
				<img src="<%=basePath%>static/images/mashu//ok.png"/>
				<h2 class="quest">回答正确</h2>
			</div>
		</div> --%>

		<div class="question">
			<ul class="col-flex-1">
			<c:forEach items="${varList}" var="list" varStatus="vs">
				<li>
				<c:choose>
				<c:when test="${vs.index+1 eq '1'}">
					<span>A</span>
				</c:when>
				<c:when test="${vs.index+1 eq '2'}">
					<span>B</span>
				</c:when>
				<c:when test="${vs.index+1 eq '3'}">
					<span>C</span>
				</c:when>
				<c:when test="${vs.index+1 eq '4'}">
					<span>D</span>
				</c:when>
				</c:choose>
				<a href="#" class="col-flex-1" tid2="${resultPd.item_id}" tid="${list.item_name}" title=""><b></b>${list.item_name}</a>
				</li>
			</c:forEach>
			</ul>
			<a class="xiayiti" id="xiayiti" href="#">
				<div>
					<h2><br />下<br />一<br />题</h2>
				</div>
			</a>
			
			<a class="ckdn" id="ckdn" href="#">
				<div>
					<h2><br />查<br />看<br />答<br/>案</h2>
					<p></p>
				</div>
			</a>
			<input type="hidden" id="answer">
			<input type="hidden" id="item_id">
			<input type="hidden" id="clientIp" value="${clientIp}">
		</div>
	</div>
</div>
<script>
	$(function(){
		//$(".ok").hide();
		$("#xiayiti").hide();
		$("#errContent").hide();
		$("#okContent").hide();
		$(".myCanvas .question>ul li a").click(function(){
			$(this).find("b").css('display','inline-block');
			$(this).parent().siblings().find('b').css('display','none');
		 	var answer = $(this).attr("tid");
		 	var item_id = $(this).attr("tid2");
			//赋值选择的答案
			$("#answer").val(answer);
			$("#item_id").val(item_id);
		});
		
			//查看答案
			$(".ckdn").click(function(){
				$("#xiayiti").show();
				$("#ckdn").hide();
				var url = '<%=basePath%>group/checkAnswer.do';
				var item_id = $("#item_id").val();
				var answer = $("#answer").val();
				var clientIp = $("#clientIp").val();
						$.ajax({
								type: "post",
								url: url,
								data: {answer:answer,item_id:item_id,clientIp:clientIp},
								dataType:"json",
								success:function(data){
									if(data.result=="1"){
										//console.log(data.image2);
										$("#bgimg").attr("src","<%=basePath%>"+data.image2);
										$("#okContent").show();
									}else{
										$("#bgimg").attr("src","<%=basePath%>"+data.image2);
										//console.log("<%=basePath%>"+data.image2);
										$("#errContent").show();
									}
								}
						});
				
			});
			
			//下一题
			$(".xiayiti").click(function(){
				$("#ckdn").show();
				$("#xiayiti").hide();
				var tt = "${tihao}";
				var randomID = "${randomID}";
				console.log(randomID);
				var tihao =parseInt(tt)+1;
				var clientIp = $("#clientIp").val();
				var url = '<%=basePath%>group/getMashubyRandomID.do?tihao='+tihao+'&clientIp='+clientIp+'&randomID='+randomID;
				window.location.href=url;
			})
	})
</script>
</body>
</html>