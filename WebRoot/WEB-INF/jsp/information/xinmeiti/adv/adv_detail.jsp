<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	
	<meta charset="utf-8" />
	<link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
	<link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css"/>
	<link rel="stylesheet" href="static/css/xinmeiti/adv_detail.css"/>
	<script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
	<script src="static/js/xinmeiti/adv_detail.js"></script>
	<title>广告招商详情页</title>
</head>
<body>
<!--顶部-->
<div class="top">
	<div class="middle">
		<div class="top_left"><span>您好！欢迎来到新媒体广告网</span></div>
		<div class="top_middle "><span>滚动文字滚动文字滚动文字滚动文字滚动文字滚动文字</span></div>
		<div class="top_right"><span>客服电话：0898-14253868</span></div>
	</div>
</div>
<!--logo-->
<div class="log">
	<div class="middle">
		<div class="l_logo">
			<a href="#">
				<img src="static/images/xinmeiti/logonews.png" alt=""/>
			</a>
		</div>
		<div class="l_adv">轮播广告位</div>
		<div class="l_list">
            <span class="l_list1">
                <a href="#">我要发广告</a>
            </span>
           <span class="l_list2">
               <a href="#">我要接广告</a>
           </span>
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
			<div class="sidbar_main index_sidbar" style="display: none">
				<div id="changing_over">
					<ul class="clearfix" id="show_table">
						<li class="">
							<div class="title"><span>微信公众号</span></div>
							<div class="bar_pad clearfix">
								<span><a href="javascript:void(0)" target="_blank" onclick="jumpTopage('gongzhonghao')">微信公众号</a></span>
								<div class="clear"></div>
							</div>
						</li>
						<li class="">
							<div class="title"><span>微信朋友圈</span></div>
							<div class="bar_pad clearfix">
								<span><a href="javascript:void(0)" target="_blank" onclick="jumpTopage('pengyouquan');">微信朋友圈</a></span>
								<div class="clear"></div>
							</div>
						</li>
						<li class="">
							<div class="title"><span>微博</span></div>
							<div class="bar_pad clearfix">
								<span><a href="javascript:void(0)" target="_blank" onclick="jumpTopage('weibo');">微博</a></span>
								<div class="clear"></div>
							</div>
						</li>
						<li class="">
							<div class="title"><span>网红直播</span></div>
							<div class="bar_pad clearfix">
								<span><a href="javascript:void(0)" target="_blank" onclick="jumpTopage2('videopaltlistPage')">网红直播</a></span>
								<div class="clear"></div>
							</div>
						</li>
						<li class="">
							<div class="title"><span>新闻发布</span></div>
							<div class="bar_pad clearfix">
								<span><a href="javascript:void(0)" target="_blank" onclick="jumpTopage2('medialistPage')">新闻发布</a></span>
								<div class="clear"></div>
							</div>
						</li>
						<li class="">
							<div class="title"><span>广告招商</span></div>
							<div class="bar_pad clearfix">
								<span><a href="javascript:void(0)" target="_blank" onclick="jumpTopage2('adverlistPage')">广告招商</a></span>
								<div class="clear"></div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			//页面跳转(跳转到微信公众号列表页、跳转到微信朋友圈列表页、跳转到微博列表页)
		    function jumpTopage(data) {
		    	window.open('api/xmtv1/'+data+'.do');
		    }
		    
		    //页面跳转（跳到新闻发布资源、网红直播资源、广告招商资源的广告主方向列表）
		    function jumpTopage2(data){
		    	window.open('api/xmtv2/'+data+'.do');
		    }
			</script>
		<div class="navmain fl">
			<ul>
				<li class="fl"><a href="#">首页</a></li>
				<li class="fl"><a  href="#">有奖答题</a></li>
				<li class="fl"><a href="#">积分兑换</a></li>
				<li class="fl"><a href="#">七嘴八舌</a></li>
				<li class="fl"><a href="#">加入我们</a></li>
				<li class="fl"><a href="#">关于我们</a></li>
			</ul>
		</div>
		<div class="search fr">
			<div class="input">
				<input type="text" id="textsearch" placeholder="您想要找什么，请输入关键字" class="s_ser"/>
				<div class="sear"></div>
			</div>
		</div>
	</div>
</div>
<!--主体-->
<div class="The_stage clearfix">
	<div class="n_position">
		当前位置>
		<span><a href="#">首页</a></span>
		>
		<span><a href="#">广告招商</a></span>
		>
		<span>52路公交</span>
	</div>
	<div class="col_lf">
		<div class="module_badic_all clearfix">
			<div class="head_portrait ">
				<p>
					<img src="${pd.media_logo }" alt="">
				</p>
			</div>
			<dl class="basic_info ">
				<dt>${pd.media_name}</dt>
				<dd class="price_dd">
					<span>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</span>
					<div id="PriceBlock">￥${pd.price }</div>
				</dd>
				<dd class="list-module">
					<span>推广形式：</span>
					<div>
						<ul>
							<li class="select">${pd.name }</li>
						</ul>
					</div>
				</dd>
			</dl>
			<div class="index_info f_right">
			<input type="hidden" value="${pd.media_id}" id="id"/>
				<button id="go_order" class="btn">我要下单</button>
			</div>
		</div>
		<!--基本信息-->
		<div class="m-mode  clearfix">
			<div class="ml-title">
				基本信息
			</div>
			<ul class="col_three">
				<li>
					<span>名称:</span>
					<p>${pd.media_name}</p>
				</li>
				<li>
					<span>位置/线路:</span>
					<p>${pd.linkeHttp}</p>
				</li>
				<li>
					<span>地区:</span>
					<p>${pd.rukouType}</p>
				</li>
				<li style="width: 100%;">
					<span>资源介绍：</span>
					<p>
						${pd.media_intro}
					</p>
				</li>
			</ul>
		</div>
		<!--大家都在看-->
		<div class="per_module clearfix">
			<div class="ml-title">
				大家都在看
			</div>
			<ul class="col_two clearfix">
				<c:forEach items="${mslist}" var="list" begin="0" end="3">
					<li    onclick="getGuanggaoDetailById('${list.media_id}')">
						<button class="btn btn1">我要下单</button>
						<div class="pic">
							<img src="${list.media_logo }" alt=""/>
						</div>
						<dl class="text">
	                        <dd>${list.media_name}</dd>
	                        <dd>简介: <span>${list.media_intro}</span></dd>
	                    </dl>
	
					</li>
				</c:forEach>
				
			</ul>
		</div>
	</div>
	<div class="s-mode">
		<div class="more-right">
			<div class="mr-title">人气榜</div>
			
				<c:forEach items="${mslist}" var="list" end="6">
					<dl class="more-people" onclick="getGuanggaoDetailById('${list.media_id}')">
						<a href="#" target="_blank">
							<dt>
								<img src="${list.media_logo }">
							</dt>
							<dd class="u-name">${list.media_name}</dd>
							<dd class="u-price">¥&nbsp;&nbsp;${list.price }</dd>
						</a>
					</dl>
				</c:forEach>
		</div>
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
<script type="text/javascript" src="static/js/xinmeiti/jquery.liMarquee.js"></script>
<!--滚动文字-->
<script>
	$(function () {
		$('.top_middle').liMarquee({
			direction: 'up'
		});
	});
	 $("#go_order").click(function(){
          var media_id=$("#id").val();
        	var url ="api/guangGao/order/goGuangGao.do";
    		window.location.href=url+'?media_id='+media_id;
        });
	/* 传递ID */
	function getGuanggaoDetailById(tid){
   		var url = "api/xmtv2/detailsAdver.do";
    	window.location.href=url+'?tid='+tid;
   }
</script>
</body>
</html>