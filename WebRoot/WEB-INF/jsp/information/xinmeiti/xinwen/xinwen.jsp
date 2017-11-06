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
    <link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>

    <link rel="stylesheet" href="static/css/xinmeiti/xinwen.css"/>

    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/xinwen.js"></script>
    <title>新闻发布</title>
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
		    	window.location.href='api/xmtv1/'+data+'.do';
		    }
		    
		    //页面跳转（跳到新闻发布资源、网红直播资源、广告招商资源的广告主方向列表）
		    function jumpTopage2(data){
		    	window.location.href='api/xmtv2/'+data+'.do';
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
<div class="listFrm clearfix">
    <div class="postion_c clearfix">
        <ul>
            <li>当前位置:</li>
            <li><a href="#">新闻发布</a></li>
        </ul>
    </div>
    <div class="typeMode clearfix">
        <h2>
            <span>筛选项</span>
        </h2>
        <ul class="select">
            <li class="select-list">
                <dl id="select1">
                    <dt>价格:</dt>
                    <dd class="select-all selected"><a href="javascript:void(0)">不限</a></dd>
                    <dd><a href="javascript:void(0)">100元以下</a></dd>
                    <dd><a href="javascript:void(0)">100-500元</a></dd>
                    <dd><a href="javascript:void(0)">500-1000元</a></dd>
                    <dd><a href="javascript:void(0)">1000-3000元</a></dd>
                    <dd><a href="javascript:void(0)">3000-6000元</a></dd>
                    <dd><a href="javascript:void(0)">6000-10000元</a></dd>
                </dl>
            </li>
            <li class="select-list">
                <dl id="select2">
                    <dt>入口级别:</dt>
                    <dd class="select-all selected"><a href="javascript:void(0)">不限</a></dd>
                    <c:forEach items="${levellist }" var="llist">
                    	<dd><a  href="javascript:void(0)">${llist.level_name }</a></dd>
                    </c:forEach>	
                </dl>
            </li>
            <li class="select-list">
                <dl id="select3">
                    <dt>入口形式:</dt>
                    <dd class="select-all selected"><a href="javascript:void(0)">不限</a></dd>
                    <c:forEach items="${rukoulist }" var="rlist">
                    	<dd><a  href="javascript:void(0)">${rlist.rukouType }</a></dd>
                    </c:forEach>	
                </dl>
            </li>
            <li class="select-result">
                <dl>
                    <dt>已选条件：</dt>
                    <dd class="select-no">暂时没有选择过滤条件</dd>
                </dl>
            </li>
        </ul>
    </div>
    <!--资源-->
    <div class="resources">
        <ul class="resources-li clearfix">
        <!-- 由此开始迭代 -->
				<c:forEach items="${medialist}" var="melist">
					<li class="reso-li" style="margin-top: 0;margin-right: 5px;" onclick="getXinwenDetailById('${melist.media_id}')">
						<a href="javascript:vodi(0)">
							<div class="resources-t">
								<img src="${melist.media_logo}" alt="" />
							</div>
							<div class="newPersonBox">
									<div style="font-size: 14px;color: #000;line-height: 30px;text-indent: 8px;">${melist.media_name}</div>
									<div class="info">${melist.media_intro}</div> 
							</div>
							<div class="price">
								<a id="aprice" href="javascript:void(0)" target="_blank">查看价格</a>
							</div> 
						</a>
					</li>
				</c:forEach>
				<c:if test="${medialist==''||medialist==null}">
					<p>没有数据。</p>
				</c:if>
			</ul>
        <div class="txt-center">
            <ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
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
    function tt(dd){
        //alert(dd);
    }
    var GG = {
        "kk":function(mm){
            // alert(mm);
        }
    }

    $("#page").initPage(71,1,GG.kk);
    
    
    //-----------------------------------------------//
    function getXinwenDetailById(tid){
    	var url = "api/xmtv2/detailsMedia.do";
    	window.location.href=url+'?tid='+tid;
    }
    
    
</script>
</body>
</html>