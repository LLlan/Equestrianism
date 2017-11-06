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
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/wb.css"/>
    <script src="static/js/xinmeiti/jquery-1.7.2.min.js"></script>
    <script src="static/js/xinmeiti/city.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/wb.js"></script>
    <title>微博</title>
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
            <li><a href="#">微博</a></li>
        </ul>
    </div>
    <div class="typeMode clearfix">
        <h2>
            <span>筛选项</span>
        </h2>
        <ul class="select">
			<li class="select-list">
				<dl id="select1">
					<dt>粉丝量:</dt>
					<dd class="select-all selected"><a href="javascript:void(0)">不限</a></dd>
					<dd><a href="javascript:void(0)">5万以下</a></dd>
					<dd><a href="javascript:void(0)">5万-10万</a></dd>
					<dd><a href="javascript:void(0)">10万-20万</a></dd>
					<dd><a href="javascript:void(0)">20万-40万</a></dd>
					<dd><a href="javascript:void(0)">40万-80万</a></dd>
					<dd><a href="javascript:void(0)">80万-120万</a></dd>
					<dd><a href="javascript:void(0)">120万-200万</a></dd>
				</dl>
			</li>
			<li class="select-list">
				<dl id="select2">
					<dt>价格:</dt>
					<dd class="select-all selected"><a href="javascript:void(0)">不限</a></dd>
					<dd><a href="javascript:void(0)">20元以下</a></dd>
					<dd><a href="javascript:void(0)">20-50元</a></dd>
					<dd><a href="javascript:void(0)">50-100元</a></dd>
					<dd><a href="javascript:void(0)">100-300元</a></dd>
					<dd><a href="javascript:void(0)">300-1000元</a></dd>
					<dd><a href="javascript:void(0)">1000-3000元</a></dd>
				</dl>
			</li>
			<li class="select-list">
				<dl id="select3">
					<dt>地区:</dt>
					<dd class="select-all selected"><a href="javascript:void(0)">不限</a></dd>
					<dd><a href="javascript:void(0)">北京</a></dd>
					<dd><a href="javascript:void(0)">上海</a></dd>
					<dd><a href="javascript:void(0)">广州</a></dd>
					<dd><a href="javascript:void(0)">深圳</a></dd>
					<dd><a href="javascript:void(0)">杭州</a></dd>
					<dd><a href="javascript:void(0)">武汉</a></dd>
					<dd><a href="javascript:void(0)">海口</a></dd>
					<dd><a href="javascript:void(0)">三亚</a></dd>
					<dd><a href="javascript:void(0)">天津</a></dd>
					<dd><a href="javascript:void(0)">安徽</a></dd>
					<div class="pcselect">
						<select name="province" id="province">
							<option value="" selected="selected">省份</option>
						</select>
						<select name="city" id="city">
							<option value="" selected="selected">城市</option>
						</select>
						<button class="btn pcbutton">确定</button>
					</div>
					<%--<form action="" method="post">
						<select name="province" id="province">
							<option value="省份" selected="selected">省份</option>
						</select>
						<select name="city" id="city">
							<option value="城市" selected="selected">城市</option>
						</select>
						<button class="btn ">确定</button>
					</form>
				--%></dl>
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
        <ul class="resources-li clearfix" id="sourceUl">
        <c:forEach items="${list }" var="list" begin="0" end="9">
			<li class="reso-li" style="margin-top: 0;margin-right: 5px;" onclick="jumpTodetail('${list.microBlog_id}');">
                <a href="javascript:void(0)">
                    <div class="resources-t">
                        <img src="<%=basePath%>${list.headImgURL}" alt=""/>
                    </div>
                    <div class="newPersonBox">
                        <a href="javascript:void(0)">
                            <div style="font-size: 14px;color: #000;line-height: 30px;text-indent: 8px;">${list.name}</div>
                        </a>
                        <a href="javascript:void(0)">
                            <div class="info">${list.introduce}</div>
                        </a>
                        <div class="fFans">
                            <i>粉丝：${list.fansNum}</i></div>
                    </div>
                    <div class="price">
                        <a href="javascript:void(0)" target="_blank">查看价格</a>
                    </div>
                </a>
            </li>
		</c:forEach>
            <%--<li class="reso-li" style="margin-top: 0;margin-right: 5px;">
                <a href="#">
                    <div class="resources-t">
                        <img src="static/images/xinmeiti/mn.jpg" alt=""/>
                    </div>
                    <div class="newPersonBox">
                        <a href="#">
                            <div style="font-size: 14px;color: #000;line-height: 30px;text-indent: 8px;">刘萌萌</div>
                        </a>
                        <a href="#">
                            <div class="info">演员，代表作《最美的时光》、《偏偏喜欢你》、《煮妇神探》</div>
                        </a>
                        <div class="fFans">
                            <i>粉丝：613900</i></div>
                    </div>
                    <div class="price">
                        <a href="#" target="_blank">查看价格</a>
                    </div>
                </a>
            </li>--%>
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
    };
    //跳转到详情页
	function jumpTodetail(tag_id) {
		var url="api/xmtv1/weiboDetail?tag_id="+tag_id;
		window.open(url);
	};
    $("#page").initPage(71,1,GG.kk);
</script>
</body>
</html>