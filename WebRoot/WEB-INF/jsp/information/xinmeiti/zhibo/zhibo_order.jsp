	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	%>
	<!DOCTYPE html>
	<html>
	<head lang="en">
	<base href="<%=basePath%>">
	
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="static/css/xinmeiti/page.css" />
	<link rel="stylesheet"
		href="static/css/xinmeiti/foundation-datepicker.css" />
	<link rel="stylesheet" href="static/css/xinmeiti/bootstrap.css" />
	<!--<link rel="stylesheet" href="../css/xmt/foundation.min.css"/>-->
	<link rel="stylesheet" href="static/css/xinmeiti/common.css" />
	<link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css" />
	<link rel="stylesheet" href="static/css/xinmeiti/zhibo_create.css" />
	<!--[if lt IE 9]>
	    <script src="../js/xmt/html5shiv.min.js"></script>
	    <script src="../js/xmt/respond.min.js"></script>
	    <![endif]-->
	<!--  <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script> -->
	<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
	<script src="static/js/xinmeiti/foundation-datepicker.js"></script>
	<script src="static/js/xinmeiti/foundation-datepicker.zh-CN.js"></script>
	<script src="static/js/xinmeiti/bootstrap.js"></script>
	<script src="static/js/xinmeiti/page.js"></script>
	<script src="static/js/xinmeiti/zhibo_create.js"></script>
	<script src="static/js/xinmeiti/gzh_addresource.js"></script>
	<title>新闻创建单</title>
	</head>
	<body>
		<!--顶部-->
		<div class="top">
			<div class="middle">
				<div class="top_left">
					<span>您好！欢迎来到新媒体广告网</span>
				</div>
				<div class="top_middle ">
					<span>滚动文字滚动文字滚动文字滚动文字滚动文字滚动文字</span>
				</div>
				<div class="top_right">
					<span>客服电话：0898-14253868</span>
				</div>
			</div>
		</div>
		<!--logo-->
		<div class="log">
			<div class="middle">
				<div class="l_logo">
					<a href="#"> <img src="static/images/xinmeiti/logonews.png"
						alt="" /> </a>
				</div>
				<div class="l_adv">轮播广告位</div>
				<div class="l_list">
					<span class="l_list1"> <a href="#">我要发广告</a> </span> <span
						class="l_list2"> <a href="#">我要接广告</a> </span>
				</div>
			</div>
		</div>
		<!--分类-->
		<div class="classify">
			<div class=" seem">
				<div class="sidbar fl">
					<div class="all clearfix">
						<b>全部服务分类</b>
					</div>
					<div class="sidbar_main index_sidbar" style="display: none;">
						<div id="changing_over">
							<ul class="clearfix" id="show_table">
								<li class="">
									<div class="title">
										<span>微信公众号</span>
									</div>
									<div class="bar_pad clearfix">
										<span><a href="#" target="_blank">微信公众号</a> </span>
										<div class="clear"></div>
									</div>
								</li>
								<li class="">
									<div class="title">
										<span>微信朋友圈</span>
									</div>
									<div class="bar_pad clearfix">
										<span><a href="#" target="_blank">微信朋友圈</a> </span>
										<div class="clear"></div>
									</div>
								</li>
								<li class="">
									<div class="title">
										<span>微博</span>
									</div>
									<div class="bar_pad clearfix">
										<span><a href="#" target="_blank">微博</a> </span>
										<div class="clear"></div>
									</div>
								</li>
								<li class="">
									<div class="title">
										<span>网红直播</span>
									</div>
									<div class="bar_pad clearfix">
										<span><a href="#" target="_blank">网红直播</a> </span>
										<div class="clear"></div>
									</div>
								</li>
								<li class="">
									<div class="title">
										<span>新闻发布</span>
									</div>
									<div class="bar_pad clearfix">
										<span><a href="#" target="_blank">新闻发布</a> </span>
										<div class="clear"></div>
									</div>
								</li>
								<li class="">
									<div class="title">
										<span>广告招商</span>
									</div>
									<div class="bar_pad clearfix">
										<span><a href="#" target="_blank">广告招商</a> </span>
										<div class="clear"></div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="navmain fl">
					<ul>
						<li class="fl"><a href="#">首页</a></li>
						<li class="fl"><a href="#">有奖答题</a></li>
						<li class="fl"><a href="#">积分兑换</a></li>
						<li class="fl"><a href="#">七嘴八舌</a></li>
						<li class="fl"><a href="#">加入我们</a></li>
						<li class="fl"><a href="#">关于我们</a></li>
					</ul>
				</div>
				<div class="search fr">
					<div class="input">
						<input type="text" id="textsearch" placeholder="您想要找什么，请输入关键字"
							class="s_ser" />
						<div class="sear"></div>
					</div>
				</div>
			</div>
		</div>
		 <form action="<%=basePath%>api/zhiBoOrder/save.do" class="about1 clearfix" id="zhiboFrom" method="post">
		<!--创建订单-->
		<input type="hidden" value="${type_name }" id="type_name" name="type_name"> 
		<div class="about">
			<form class="about1 clearfix">
				<div class="about_top">
					<span>创建直播订单</span>
				</div>
				<div class="about_word">
					<!--活动名称-->
					<div class="found-list">
						<div class="found-title">
							直播标题<span><img src="static/images/xinmeiti/star.png" alt="" />
							</span>
						</div>
	
						<div class="found-content">
							<input type="text" name="anchorOrder_title" class="form-con1" placeholder="请输入直播标题" />
						</div>
					</div>
					<%-- <!--合作形式-->
					<div class="found-list">
						<div class="found-title">
							合作形式 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
					
						<select class="form-control">
						<c:forEach items="${list }" var="zb">
							<option value="${zb.id }">${zb.name }</option>
							</c:forEach>	
						</select>
					
					</div>
					<!--专场直播具体形式-->
					<div class="found-list special" style="display: block;">
						<div class="found-title">
							具体形式 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
	
						<div class="found-text">
						
						</div>
	
					</div>
					<!--直播广告植入具体形式-->
					<div class="found-list telecast" style="display: none;">
						<div class="found-title">
							具体形式 <span><img src="static/images/xinmeiti/star.png"
								alt=""/> </span>
						</div>
	
						<div class="found-text">
						
						</div> --%>
					</div>
					<!--直播内容-->
					<div class="found-list">
						<div class="found-title">
							直播内容 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
	
						<div class="form-time">
							<textarea style="padding:3px;" name="anchorOrder_content" cols="54" rows="10"></textarea>
							<p class="form-p1">请描述直播内容，让预约对象大致了解此次活动，请勿超过1000字</p>
						</div>
					</div>
					<!--直播地点-->
					<div class="found-list">
						<div class="found-title">
							直播地点<span><img src="static/images/xinmeiti/star.png" alt="" />
							</span>
						</div>
	
						<div class="found-content">
							<input type="text" class="form-con1" name="anchorOrder_location" placeholder="请输入直播地点" />
						</div>
					</div>
					<!--开始时间-->
					<div class="found-list">
						<div class="found-title">
							开始时间 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
						<div class="form-time" style="width: 550px;">
							<input style="display: inline-block" name="newsOrder_optime" type="text"
								class="form-control" id="demo-1"> <span
								style="display: inline-block;margin: 0 10px;">至</span> <input
								style="display: inline-block" name="newsOrder_edtime" type="text" class="form-control"
								id="demo-2">
						</div>
					</div>
					<!--预约结果反馈时间-->
					<!-- <div class="found-list">
						<div class="found-title" style="height:60px;width: 120px;">
							预约结果 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span> <br /> <span
								style=" display:inline-block;width: 120px;text-align: center;">反馈时间</span>
						</div>
						<div class="form-time">
							<input type="text" class="form-control" id="demo-3">
							<p class="form-p1">须晚于当前时间至少24小时，预约对象将在该时间之前给您回复</p>
						</div>
					</div> -->
					<!--上传视频-->
					<div class="found-list upload_video" style="display: none;">
						<div class="found-title">
							上传视频 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
						<div class="form-time video">
							<div class="file">
								选择视频 <input type="file" />
							</div>
							<p class="form-p1">提醒:请确认您的稿件链接或文档无误，如因稿件问题导致发布异常，新闻媒体不承担责任。</p>
						</div>
					</div>
					<!--选择资源-->
					<div class="found-list">
						<div class="found-title">
							选择资源 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
						<div class="form-time">
							<div class="form-btn">
								<!--<button data-toggle="modal" data-target="#modal-login" class="btn_pray">添加资源</button>-->
								<button data-toggle="modal" data-target="#modal-login"
									class="btn btn-warning" type="button">添加资源</button>
	
								<!--资源列表-->
								<div class="modal fade" id="modal-login">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<button class="btn btn-warning">添加资源</button>
												<span data-dismiss="modal" class="close">&times;</span>
											</div>
											<div class="modal-body">
												<div class="add_all">
													<div class="search">
														<div class="input">
															<input type="text" placeholder="请输入关键字" class="s_ser" />
															<div class="sear"></div>
														</div>
													</div>
												</div>
												<div class="add_res">
													<table class="table">
														<tbody>
											<tr>
											<th></th>
												<th>直播昵称</th>
												<th>平台</th>
												<th>粉丝量</th>
												<th>推广形式</th>
												<th>性别</th>
												<th>价格</th>
											</tr>
										</tbody>
										<tbody id="Temp">
												<c:forEach items="${list }" var="p">
											<tr>
											<th>
                                                    <input type="checkbox"  class="ck" value="${p.videoPlatform_id }"/>
                                            </th>
												<th>${p.name }</th>
												<th>${p.platformName }</th>
												<th>${p.fansNum}</th>
												<th class="sname">${p.sname}</th>
												<th>${p.sex}</th>
												<th>${p.price }</th>
												<th>
                                        </th>
											</tr>
											</c:forEach>
														</tbody>
													</table>
												</div>
												<div class="add_tip">
													已选择<span id="ckeck_span">0</span>项资源
												</div>
												<div class="txt-center">
													<ul style="padding-bottom:20px;" class="page"
														maxshowpageitem="5" pagelistcount="10" id="page"></ul>
												</div>
											</div>
											<div class="modal-footer">
												 <input class="btn btn-success" id="save" type="button" value="确认"  onclick="queren()"/>
												 <input data-dismiss="modal" class="btn btn-warning" type="button" value="取消" />
													
													
											</div>
										</div>
									</div>
								</div>
								<!--添加资源-->
								<div class="form-tab">
									<table class="table">
										<tbody>
											<tr>
											<td></td>
											<td></td>
												<th>直播昵称</th>
												<th>平台</th>
												<th>粉丝量</th>
												<th>推广形式</th>
												<th>性别</th>
												<th>价格</th>
												<th>操作</th>
											</tr>
										</tbody>
										 <tbody id="d">
										<c:forEach items="${list1 }" var="q">
											<tr>
											<td></td>
											<th><input type="hidden" name="videoPlatform_id" value="${q.videoPlatform_id }"/>
											</th>
												<th>${q.name }</th>
												<th>${q.platformName }</th>
												<th>${q.fansNum}</th>
												<th>${q.sname}</th>
												<th>${q.sex}</th>
												<th class="t">${q.price }</th>
												<th>
                                            <input type="button" value="删除" id="delY"/>
                                        </th>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="form-chioce">
								您共选择了 <span class="option">1</span>项资源，合计价格￥ <span class="price">2000</span>
							</div>
						</div>
					</div>
					<!--上传附件-->
					<div class="found-list upload_file" style="display: block;">
						<div class="found-title">
							上传附件 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
	
						<div class="form-time">
							<div class="file">
								选择附件 <input type="file" />
							</div>
							<p class="form-p1">您可以上传活动文档(txt、doc或docx)或活动图片(gif、jpg、png或jpeg格式的图片)，文件大小请控制在2M以下</p>
						</div>
					</div>
					<div class="found-list">
						<div class="found-title">
							短信通知 <span><img src="static/images/xinmeiti/star.png"
								alt="" /> </span>
						</div>
						<div class="form-time">
							<div class="form-p2">
								<input type="checkbox" checked /> 手机短信通知
							</div>
							<div class="form-p2" style="margin: 5px 0 10px 0;">
								<input type="checkbox" checked /> 同意相关<span class="agree">服务协议</span>
							</div>
							<button class="btn btn-warning">发布</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!--页脚-->
		<div id="footer" style="margin-top: 9px;">
			<div class="foot">
				<div class="foot_lf">
					<span style="margin-right: 50px">版权所有©新媒体广告</span> <span>备案GUIOSJKDH-15GDFG
						545544</span>
				</div>
				<div class="foot_m">
					<div class="box">
						<h3>官方微信公众号</h3>
						<div>
							<img src="static/images/xinmeiti/xingtui.png"
								style="width: 100px;height: 100px;">
						</div>
					</div>
					<div class="box">
						<h3>官方微博</h3>
						<div>
							<img src="static/images/xinmeiti/xinlang.png"
								style="width: 100px;height: 100px;">
						</div>
					</div>
				</div>
	
			</div>
		</div>
		<script type="text/javascript"
			src="static/js/xinmeiti/jquery.liMarquee.js"></script>
		<!--滚动文字-->
<script>
	
	$(function() {
	
	
		 var tdnum=$("#d tr").length;
	    var price=$("th.t").text();
	    $(".price").html(price);
	    $("#Temp input").click(function(){
        	var num=$("#Temp input:checked").length;
        	$("#ckeck_span").html(num);
        	
        });
			 $("#save").click(function(){
        	//拼接id
        	var type_name=$("#type_name").val();
        	var ids="";
        	$(".ck:checked").each(function(){
        		ids+=","+$(this).val();
        	});
        	id=ids.substring(1);
        	//ajax通过ID查询显示
        	var url ='<%=basePath%>api/zhiBoOrder/selectById.do';
        	$.ajax({
					type:"POST",
					data:{id:id,"type_name":type_name},
					dataType:"json",
					url:url,
					success:function(data){ 
					var darray = data.resultList;
						for(var i=0;i<darray.length;i++){
						var list=darray[i];
						for(var j=0;j<list.length;j++){
				 			$("#d").append('<tr><td><input type="hidden" name="videoPlatform_id" value='+list[j].videoPlatform_id+'> </td> <th></th><th>'+list[j].name+'</th><th>'+list[j].platformName+'</th><th>'+list[j].fansNum+'</th><th>'+list[j].sname+'</th><th>'+list[j].sex+'</th><th><span id="price"> '+list[j].price+' </span></th><th><input type="button" onClick="delTr(this)"  value="删除"/></th></tr>');
						}
						}
						//refresh();
						getTotol();
						del();
					}
			});
        	
        });		 //删除原始的Tr
    $("#delY").click(function(){
    	$(this).parent().parent().remove();
    	getTotol();
    	del();	
		
    });
			
				$('.top_middle').liMarquee({
					direction : 'up'
				});
				$('#demo-1').fdatepicker({
					format : 'yyyy-mm-dd hh:ii',
					pickTime : true
				});
				$('#demo-2').fdatepicker({
					format : 'yyyy-mm-dd hh:ii',
					pickTime : true
				});
				$('#demo-3').fdatepicker({
					format : 'yyyy-mm-dd hh:ii',
					pickTime : true
				});
	
				function tt(dd) {
					//alert(dd);
				}
				var GG = {
					"kk" : function(mm) {
						// alert(mm);
					}
				};
	
				$("#page").initPage(71, 1, GG.kk);
				
				 $("#fabu").click(function(){
        			$("#zhiboFrom").submit();
        });
			});
			function delTr(k){
		 	$(k).parent().parent().remove(); 
		 	getTotol();
	    	del();
    } 
    function getTotol(){
        var priceTotol=0;
        priceTotol = parseFloat(priceTotol);
	        $("span[id='price']").each(function(i){
	        	priceTotol+=parseFloat($(this).text());
	        });
	        //原始价格
	        var ysPrice = parseFloat($("th.t").text());
	        if(isNaN(ysPrice)){
	      	  ysPrice = 0;
	        }else{
	        	ysPrice =ysPrice;
	        }
	        
        	 $(".price").html(priceTotol+ysPrice);
        }
     function del(){
        	var count=$("#d tr").length;
        	$(".option").html(count);
        	
        }
		</script>
		
		<script type="text/javascript">
		//点击确认
		function queren() {
			$(".modal").hide();
			$(".modal-backdrop").remove();
			$("body").css("overflow", "scroll");
		}
	</script>
	
	</body>
	</html>