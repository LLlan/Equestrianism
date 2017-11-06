<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/gzh_detail.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/gzh_detail.js"></script>
    <title>公众号详情页</title>
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
        <span><a href="#">公众号</a></span>
        >
        <span>老司机</span>
    </div>
    <div class="col_lf">
        <div class="module_badic_all clearfix">
            <div class="head_portrait ">
                <p>
                    <img src="<%=basePath%>${pd.headImgURL}" alt="">
                </p>
            </div>
            <dl class="basic_info ">
                <dt>${pd.name }</dt>
                <dd class="price_dd">
                    <span>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</span>
                    <div id="PriceBlock"></div>
                </dd>
                <dd class="list-module" id="varli">
                    <span>推广形式：</span>
                    <div>
                        <ul>
                        <c:forEach items="${list }" var="list" begin="0" end="0">
                        	<li  class="select" <c:if test="${list.price=='-1' }">title="不接"</c:if><c:if test="${list.price!='-1' }">title="${list.price }"</c:if>>${list.name }</li>
                        </c:forEach>
                        <c:forEach items="${list }" var="list" begin="1">
                        	<li  class="" <c:if test="${list.price=='-1' }">title="不接"</c:if><c:if test="${list.price!='-1' }">title="${list.price }"</c:if>>${list.name }</li>
                        </c:forEach>
                        </ul>
                        <script type="text/javascript">
                        	$(function() {
                        		var li=document.getElementById("varli").getElementsByTagName("li");
								$.each(li,function(i,j){
									if($(this).attr("class") == "select"){
										if(isNaN($(this).attr("title"))){
											$("#PriceBlock").html("￥不接");
										}else{
											$("#PriceBlock").html("￥"+parseFloat($(this).attr("title")).toFixed(2));
										}
										return false;
									}
								});
							});
                        </script>
                    </div>
                </dd>
            </dl>
            <div class="index_info f_right">
                <button class="btn" onclick="creatOrder('${pd.f_id }');">我要下单</button>
            </div>
            <script type="text/javascript">
            	//跳转到下单页面
            	function creatOrder(tag_id){
            		var li=document.getElementById("varli").getElementsByTagName("li");
            		var mark='';
            		$.each(li,function(i,j){
						if($(this).attr("class") == "select"){
							if(!isNaN($(this).attr("title"))){
								var tempname=$(this).html();
								if(tempname=='单图文硬广'){
									mark='1';
								}else if(tempname=='单图文软广'){
									mark='2';
								}else if(tempname=='多图文第一条硬广'){
									mark='3';
								}else if(tempname=='多图文第一条软广'){
									mark='4';
								}else if(tempname=='多图文第二条硬广'){
									mark='5';
								}else if(tempname=='多图文第二条软广'){
									mark='6';
								}else if(tempname=='多图文第3~N条硬广'){
									mark='7';
								}else if(tempname=='多图文第3~N条软广'){
									mark='8';
								}
							}
						}
					});
            		if(mark!=''){
            			var url="api/xmtv1/toGzhCreateOredr.do?tag_id="+tag_id+"&namendp="+mark;
                		window.open(url);
            		}
            	}
            </script>
        </div>
        <!--基本信息-->
        <div class="m-mode  clearfix">
            <div class="ml-title">
                基本信息
                <%--<span>${pd.name }</span>--%>
            </div>
            <ul class="col_three">
                <li>
                    <span>名称:</span>
                    <p>${pd.name }</p>
                </li>
                <li>
                    <span>粉丝量:</span>
                    <p>${pd.fansNum }</p>
                </li>
                <li>
                    <span>地区:</span>
                    <p>${pd.city }</p>
                </li>
                <li style="width: 100%;">
                    <span>简介：</span>
                    <p>${pd.introduce }</p> 
                </li>
            </ul>
        </div>
        <!--大家都在看-->
        <div class="per_module clearfix">
            <div class="ml-title">
                大家都在看
            </div>
            <ul class="col_two clearfix">
            <c:forEach items="${list1 }" var="list" begin="0" end="3">
            	<li onclick="jumpTodetail('${list.WCpublic_id}');">
                    <button class="btn btn1">我要下单</button>
                    <div class="pic">
                        <img src="<%=basePath%>${list.headImgURL}" alt=""/>
                    </div>
                    <dl class="text">
                        <dd>${list.name}</dd>
                        <dd>粉丝:<span>${list.fansNum}</span></dd>
                        <dd>地区: <span>${list.city}</span></dd>
                        <dd>简介: <span>${list.introduce}</span></dd>
                    </dl>

                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
    <div class="s-mode">
        <div class="more-right">
            <div class="mr-title">人气榜</div>
            <c:forEach items="${list2 }" var="list" begin="0" end="6">
	            <dl class="more-people">
	                <a href="javascript:void(0)" target="_blank">
	                    <dt>
	                        <img src="<%=basePath%>${list.headImgURL }">
	                    </dt>
	                    <dd class="u-name">${list.name}</dd>
	                    <dd class="u-price">${list.introduce}</dd>
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
  //跳转到详情页
	function jumpTodetail(tag_id) {
		var url="api/xmtv1/gzhDetail?tag_id="+tag_id;
		//window.open(url);//在新窗口中打开
		window.location.href=url;//在当前页打开
	}
</script>
</body>
</html>