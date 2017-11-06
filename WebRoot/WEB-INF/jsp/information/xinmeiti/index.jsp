<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <meta charset="UTF-8">
    <meta name="keywords" content="新媒体广告网、自媒体、影视广告、广告投放、广告推广、娱乐营销、广告平台">
    <link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/index.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/jquery.SuperSlide.2.1.1.js"></script>
    <script src="static/js/xinmeiti/index.js"></script>
    <script src="static/js/xinmeiti/yanZM.js"></script>
    <script src="static/js/xinmeiti/userLogin.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>新媒体广告网-娱乐营销交易平台</title>
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
<div class="classify" style="position: relative;z-index: 2;width:100%;height:600px;">
    <div class=" seem">
        <div class="sidbar fl">
            <div class="all clearfix">
                    <b>全部服务分类</b>
            </div>
            <div class="sidbar_main index_sidbar">
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
                                <span><a href="<%=basePath%>api/xmtv2/videopaltlistPage.do" target="_blank">网红直播</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>新闻发布</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="<%=basePath%>api/xmtv2/medialistPage.do" target="_blank">新闻发布</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>广告招商</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="<%=basePath%>api/xmtv2/adverlistPage.do" target="_blank">广告招商</a></span>
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
                <li class="fl"><a href="api/xmtv2/goDati.do">有奖答题</a></li>
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
    <div id="slideBox" class="slideBox">
        <div class="hd">
            <ul><li class="">1</li><li class="">2</li><li class="on">3</li></ul>
        </div>
        <div class="bd">
            <ul>
                <li style="display: none;"><a href="http://www.SuperSlide2.com" target="_blank"><img src="static/images/xinmeiti/adv1.jpg"></a></li>
                <li style="display: none;"><a href="http://www.SuperSlide2.com" target="_blank"><img src="static/images/xinmeiti/adv2.png"></a></li>
                <li style="display: list-item;"><a href="http://www.SuperSlide2.com" target="_blank"><img src="static/images/xinmeiti/adv3.jpg"></a></li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="m_login" id="IndexLogin">
            <div class="h_login_two">
                <span class="lot1">广告主登录</span>
                <span class=" h_login_s lot2" data-type="2">媒介主登录</span>
                <div style="clear: both" class="login_ptn h_login_s"></div>
            </div>
            <input id="userType" name="userType" type="hidden" value="1">
            <div class="login_pro" style="display: block;">您可以投放广告，提升产品销量</div>
            <div class="login_pto" style="display: none;">您可以接单赚钱</div>
            <div class="login_iphone">
                <input type="text" class="login_m" name="loginName" id="loginName" placeholder="手机号码" style="background: none" value="">
            </div>
            <div class="login_pass">
                <input type="password" name="password" id="password" class="login_m" placeholder="密码" style="background: none">
            </div>
            <div class="login_cll ">
                <div class="login_check">
                    <input type="text" class="login_n" name="validate" id="validate" placeholder="验证码" style="background: none">
                </div>
                <div class="login_code">
                	<img style="position: absolute;" id="codeImg" width="80" height="35">
                </div>
                <div style="clear: both"></div>
            </div>
            <div class="login_free ">
                <div class="lo_i1">
                    <input type="checkbox" id="saveid" onclick="savePaw();">
                </div>
                <div class="lo_i2" style="line-height: 18px;">7天内免登录</div>
                <div style="clear: both"></div>
            </div>
            <div class="login_btn" id="denglu">立即登录</div>
            <div class="login_w">
                <a id="zhuce" target="_blank" href="javascript:void(0);" style="padding-left: 86px; color: #f36200">注册账号</a> |
                <a href="<%=basePath%>api/xmtv1/toWjmm.do" target="_blank">忘记密码？</a>
            </div>
        </div>
        <!--广告主登录-->
        <c:if test="${advertiser!=null && advertiser!='' }">
        <div class="enter_login" >
            <div class="adv">
            <c:if test="${advertiser!=null and advertiser!=''}">
            	<c:if test="${advertiser.rolMark=='1' }">广告主</c:if>
            	<c:if test="${advertiser.rolMark=='2' }">媒介主</c:if>
            </c:if>
            </div>
            <div class="adv_main">
                <div class="adv_img">
                    <img src="static/images/xinmeiti/lo_9.png" alt=""/>
                </div>
                <p>您好！</p>
            </div>
            <div class="adv_balance">
                <ul>
                    <li>
                        <p class="balance">账户余额</p>
                        <p class="price">￥${advertiser.zhangHYE }</p>
                    </li>
                    <li>
                        <p class="balance">冻结金额</p>
                        <p class="price">￥${advertiser.dongJJE }</p>
                    </li>
                    <li>
                        <p class="balance">可用金额</p>
                        <p class="price">￥${advertiser.zhangHYE-advertiser.dongJJE }</p>
                    </li>
                </ul>
            </div>
            
           	<c:if test="${advertiser.rolMark=='1' }">
           	<div class="login_btn" onclick="intoPersonD()">
           		<a href="javascript:void(0)">进入我的星推</a>
           	</div>
           	</c:if>
           	<c:if test="${advertiser.rolMark=='2' }">
           	<div class="login_btn" onclick="intoPersonM()">
           		<a href="javascript:void(0)">进入我的星推</a>
           	</div>
           	</c:if>
            
            <div class="help">
                <a href="javascript:void(0)" onclick="tuichudl()">退出登录</a>
            </div>
        </div>
        </c:if>
   </div>
</div>
<script>
    $(function(){
        jQuery(".slideBox").slide({mainCell:".bd ul",autoPlay:true});
    });
</script>
<!--动态公告-->
<div class="notice clearfix" style="clear: both">
    <div class="gg">
        <h2>动态公告</h2>
    </div>
    <div class="bd">
        <div class="tempWrap">
            <div class="tempWrap" >
                <ul class="picList" >
                <c:forEach items="${listgzh }" var="list" end="12">
                	<li class="clone" style="float: left; width: 190px;cursor: pointer" onclick="jumpTodetailgzh('${list.WCpublic_id}');">
                        <div class="pic">
                                <div class="fl picture1">
                                    <img src="<%=basePath%>${list.headImgURL }">
                                </div>
                                <div class="picture2 fl" style="width: 120px">
                                    <h2>${list.name }</h2>
                                    <p>${list.introduce }</p> <%-- 简介控制为20个字 --%>
                                </div>
                        </div>
                    </li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
//跳转到详情页
function jumpTodetailgzh(tag_id) {
	var url="api/xmtv1/gzhDetail?tag_id="+tag_id;
	window.open(url);
}
</script>
<!--微博、朋友圈-->
<div class="column middle " style="clear: both">
    <div class="column-top-left">
        <ul>
            <li class="column-00 onx"></li>
            <li class="column-11"></li>
            <li class="column-22"></li>
            <li class="column-33"></li>
            <li class="column-44"></li>
            <li class="column-55"></li>
        </ul>
    </div>
    <!--排行榜-->
    <div class="column_middle_right">
        <div class="middle_right_top">
            <img src="static/images/xinmeiti/wbh.png" alt=""/>
            <!--<img src="static/images/xinmeiti/wph.png" alt=""/>-->
            <!-- <img src="static/images/xinmeiti/wxh.png" alt=""/>-->
        </div>
        <div class="middle_right_middle">
            <div class="report"><span>排行榜</span></div>
            <div class="matiyun">
                <table width="100%" border="0" cellspacing="0" cellpadding="1">
                    <thead>
                    
                    <c:forEach items="${listwb }" var="list" end="0">
                    	<tr>
                        <td style="text-align: center">
                            <img src="static/images/xinmeiti/jin1.jpg" >
                        </td>
                        <td>
                        	<img src="<%=basePath%>${list.headImgURL }" style="width: 25px;height: 25px;">
                        </td>
                        <td style="text-align: center">
                        	<a href="javascript:void(0)" target="_blank" style="color: #666" onclick="jumpTodetailwb('${list.microBlog_id}');">${list.name }</a>
                        </td>
                        <td>
                            <div class="two" >
                                ${list.fansNum }
                            </div>
                        </td>
                        <td>
                            <div class="end"><a href="javascript:void(0)" target="_blank" onclick="jumpTodetailwb('${list.microBlog_id}');">下单</a></div>
                        </td>
                    </tr>
                    </c:forEach>
                    
					<c:forEach items="${listwb }" var="list" begin="1" end="1">
					<tr>
                        <td style="text-align: center">
                            <img src="static/images/xinmeiti/jin2.png" >
                        </td>
                        <td>
                        	<img src="<%=basePath%>${list.headImgURL }" style="width: 25px;height: 25px;">
                        	</td>
                        <td style="text-align: center">
                        	<a href="javascript:void(0)" target="_blank" style="color: #666" onclick="jumpTodetailwb('${list.microBlog_id}');">${list.name }</a>
                        </td>
                        <td>
                            <div class="two">
                                 ${list.fansNum }
                            </div>
                        </td>
                        <td>
                            <div class="end"><a href="javascript:void(0)" target="_blank" onclick="jumpTodetailwb('${list.microBlog_id}');">下单</a></div>
                        </td>
                    </tr>
					</c:forEach>
                   
					<c:forEach items="${listwb }" var="list" begin="2" end="2">
					<tr>
                        <td style="text-align: center">
                            <img src="static/images/xinmeiti/jin3.png">
                        </td>
                        <td>
                        	<img src="<%=basePath%>${list.headImgURL }" style="width: 25px;height: 25px;">
                       </td>
                        <td style="text-align: center">
                        	<a href="javascript:void(0)" target="_blank" style="color: #666" onclick="jumpTodetailwb('${list.microBlog_id}');">${list.name }</a>
                        </td>
                        <td>
                            <div class="two" >
                                ${list.fansNum }
                            </div>
                        </td>
                        <td>
                            <div class="end"><a href="javascript:void(0)" target="_blank" onclick="jumpTodetailwb('${list.microBlog_id}');">下单</a></div>
                        </td>
                    </tr>
					</c:forEach>
                   <c:forEach items="${listwb }" var="list" begin="3" end="8" varStatus="vs">
                   <tr>
                        <td style="text-align: center">
                            ${vs.index+1 }
                        </td>
                        <td>
                        	<img src="<%=basePath%>${list.headImgURL }" style="width: 25px;height: 25px;">
                        </td>
                        <td style="text-align: center">
                        	<a href="javascript:void(0)" target="_blank" style="color: #666" onclick="jumpTodetailwb('${list.microBlog_id}');">${list.name }</a>
                        </td>
                        <td>
                            <div class="two">
                               ${list.fansNum }
                            </div>
                        </td>
                        <td>
                            <div class="end"><a href="javascript:void(0)" target="_blank" onclick="jumpTodetailwb('${list.microBlog_id}');">下单</a></div>
                        </td>
                    </tr>
                   </c:forEach>
                    </thead>
                </table>
            </div>
        </div>
    </div>
    <!--微博内容-->
    <div class="column-a1">
        <div class="wx-columnt">
            <div class="column-bottomp" style="display: block;">
                <div class="column-bottom-left">
                    <div class="column-bottom-left-t">
                        <ul>
                        <c:forEach items="${listwb1 }" var="list" end="5">
                        	<li style="margin-left: 0" onclick="jumpTodetailwb('${list.microBlog_id}');">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="javascript:void(0)" target="_blank">
                                            <img src="<%=basePath%>${list.headImgURL } " style="width: 94px;">
                                        </a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="javascript:void(0)" target="_blank">${list.name }</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="javascript:void(0)" target="_blank">${list.introduce }</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：${list.fansNum }</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    //跳转到详情页
	function jumpTodetailwb(tag_id) {
		var url="api/xmtv1/weiboDetail?tag_id="+tag_id;
		window.open(url);
	};
    </script>
    <!--朋友圈内容-->
    <div class="column-a2">
        <div class="wx-columnt">
            <div class="column-bottom" style="display: block;">
                <div class="column-bottom-left">
                    <div class="column-bottom-left-t">
                        <ul>
                        <c:forEach items="${listpyq }" var="list" end="5">
                        	<li style="margin-left: 0" onclick="jumpTodetailpyq('${list.WCfriends_id}');">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="javascript:void(0)" target="_blank">
                                            <img src="<%=basePath%>${list.headImgURL }" style="width: 94px;"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="javascript:void(0)" target="_blank">${list.name }</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="javascript:void(0)" target="_blank">${list.introduce }</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：${list.fansNum }</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
  //跳转到详情页
	function jumpTodetailpyq(tag_id) {
		var url="api/xmtv1/pengyouquanDetail?tag_id="+tag_id;
		window.open(url);
	}
    </script>
    <!--微信公众号内容-->
    <div class="column-a3">
        <div class="wx-columnt">
            <div class="column-bottomx" style="display: block;">
                <div class="column-bottom-left">
                    <div class="column-bottom-left-t">
                        <ul>
                        <c:forEach items="${listgzh }" var="list" end="5">
                        	<li style="margin-left: 0" onclick="jumpTodetailgzh('${list.WCpublic_id}');">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="javascript:void(0)" target="_blank">
                                            <img src="<%=basePath%>${list.headImgURL }" style="width: 94px;"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="javascript:void(0)" target="_blank">${list.name }</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="javascript:void(0)" target="_blank">${list.introduce }</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：${list.fansNum }</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--网红直播-->
    <div class="column-a4">
        <div class="wx-columnt">
            <div class="column-bottoma" style="display: block;">
                <div class="column-bottom-left">
                    <div class="column-bottom-left-t">
                        <ul>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小横</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;">
                                            <a href="#" target="_blank">简单介绍</a>
                                        </div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;">
                                            <a href="#" target="_blank">简单介绍</a>
                                        </div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆 </a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--新闻发布-->
    <div class="column-a5">
        <div class="wx-columnt">
            <div class="column-bottomb" style="display: block;">
                <div class="column-bottom-left">
                    <div class="column-bottom-left-t">
                        <ul>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小谢</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;">
                                            <a href="#" target="_blank">简单介绍</a>
                                        </div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;">
                                            <a href="#" target="_blank">简单介绍</a>
                                        </div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆 </a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--广告商-->
    <div class="column-a6">
        <div class="wx-columnt">
            <div class="column-bottomc" style="display: block;">
                <div class="column-bottom-left">
                    <div class="column-bottom-left-t">
                        <ul>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小筽</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;">
                                            <a href="#" target="_blank">简单介绍</a>
                                        </div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;">
                                            <a href="#" target="_blank">简单介绍</a>
                                        </div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li style="margin-left: 0">
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆</a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="colu-star">
                                    <div class="stp">
                                        <a href="#" target="_blank">
                                            <img src="static/images/xinmeiti/dog.png"></a>
                                    </div>
                                    <div class="starproduct">
                                        <div class="s-xm"><a href="#" target="_blank">王小呆 </a></div>
                                        <div class="s-product" style="height: 44px;"><a href="#" target="_blank">简单介绍</a></div>
                                        <div class="s-fence">
                                            <span>粉丝数：12447</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--网络红人-->
<div class="column-hr column_bottom" style="clear: both">
    <div class="column-top">
        <div class="column-hr-tl"  id="divhr">
            <span>网络红人/</span><span>Network Reds</span>
        </div>
        <div class="column-ter" style="display: block;">
            <ul>
                <div class="column-top-tt"><a target="_blank" id="hrMore" href="#">更多&gt;</a></div>
            </ul>
        </div>
    </div>
    <div class="column-middle">
        <ul>
        <c:forEach items="${listpyq }" var="list" end="7">
        	<li onclick="jumpTodetailpyq('${list.WCfriends_id}');">
                <div class="hr">
                    <div class="hr-top">
                        <img src="<%=basePath %>${list.headImgURL }">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="javascript:void(0)" target="_blank" style="color: #666">${list.name }</a></div>
                        <div class="hr-project">
                            <div class="intro">${list.introduce }</div>
                            <div class="xiaoqing clearfix"><a href="javascript:void(0)" target="_blank">查看详情</a></div>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
        </ul>
    </div>
</div>
<!--有奖答题-->
<div class="column-hr" style="clear: both">
    <div class="column-top">
        <div class="column-hr-tl"  >
            <span>有奖答题/</span><span>Prize answer</span>
        </div>
    </div>
    <div class="column-middle">
        <ul>
            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing clearfix"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>
            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>
            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>
            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>

            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>

            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>

            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>
            <li >
                <div class="hr">
                    <div class="hr-top">
                        <img src="static/images/xinmeiti/timg.jpg">
                    </div>
                    <div class="hr-bottom">
                        <div class="hr-name"><a href="#" target="_blank" style="color: #666">记忆力大挑战&nbsp;&nbsp;答题赢大奖</a></div>
                        <div class="hr-project">
                            <div class="intro">简单的介绍</div>
                            <div class="xiaoqing"><a href="#" target="_blank">开始答题</a></div>
                        </div>
                    </div>
                </div>
            </li>

        </ul>
    </div>
</div>
<!--友情链接-->
<div style="background: #fff; width: 1160px; margin: 20px auto; padding-bottom: 10px;">
    <div class="friend1" style="padding: 10px 0 10px 0;">
        <div class="lianjie">
            <span>友情链接/</span><span>Link</span>
        </div>
    </div>
    <div class="friend-x">
        <ul>
            <a href="http://www.xingtui520.com" target="_blank" title="推特文化"><strong>推特文化</strong></a>
            <a href="http://www.dahe.cn" target="_blank">河南新闻 </a><a href="http://hzzzahy1452451348.st5.com" target="_blank">爱尚笑话网 </a><a href="http://tyzb.com/" target="_blank">体育直播吧 </a><a href="http://www.dy100.me/" target="_blank">经典电影 </a><a href="http://www.baihui.com" target="_blank">crm </a><a href="http://www.ruan-wen.cn" target="_blank">微信营销网</a><a href="http://www.qdnzy.cn/" target="_blank">广东网 </a><a href="http://mp.qq.com/" target="_blank">QQ公众号</a><a href="http://www.allcan.com.cn/" target="_blank">网络广告策划 </a><a href="http://www.17milesktv.com/" target="_blank">幻城娱乐</a><a href="http://www.haixiutv.com/" target="_blank">美女主播 </a><a href="http://www.mw12.com/" target="_blank">明星代言 </a><a href="http://www.sdguanggao.com/" target="_blank">山东广告公司</a><a href="http://www.gaoxiaomedia.com" target="_blank">校园推广</a><a href="http://www.xinglihailan.net/" target="_blank">星力海蓝</a><a href="http://www.jjywlsc.com/" target="_blank">形象代言 </a><a href="http://www.mw12.com/" target="_blank">明星代言</a><a href="http://www.lutoo.org/" target="_blank">娱乐新闻 </a><a href="http://www.89002888.com/" target="_blank">明星经济网</a><a href="http://www.yokao.net/" target="_blank">千贝网</a><a href="http://www.weight-union.com/" target="_blank">专卖店设计</a><a href="http://www.richinfo.cn/" target="_blank">深圳彩讯科技 </a><a href="http://www.8mak.com/" target="_blank">芭玛网 </a><a href="http://www.ywt158.net/" target="_blank">营销型网站</a><a href="http://www.hxad.com/" target="_blank">广告联盟 </a><a href="http://www.cxsj178.com/" target="_blank">液晶广告机 </a><a href="http://www.23sc.com/" target="_blank">微信公众号之家 </a><a href="http://www.youdoucm.com/" target="_blank">优豆传媒 </a><a href="http://www.shejigongsi.net/" target="_blank">深圳广告公司 </a><a href="http://www.mvip2001.org/" target="_blank">南昌广告公司 </a>
        </ul>
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
</body>
<!--轮播图-->
<script type="text/javascript">
    jQuery(".notice").slide({ mainCell: ".bd ul", autoPlay: true, effect: "leftMarquee", vis: 5, interTime: 50 });

</script>
<script type="text/javascript" src="static/js/xinmeiti/jquery.liMarquee.js"></script>
<!--滚动文字-->
<script>
    $(function () {
        $('.top_middle').liMarquee({
            direction: 'up'
        });   
      //点击注册
        $("#zhuce").click(function(){
        	var url = 'api/xmtv1/toRegister.do';
        	//window.location.href=url;
        	window.open(url);
        });
    });
  //页面跳转(跳转到微信公众号列表页、跳转到微信朋友圈列表页、跳转到微博列表页)
    function jumpTopage(data) {
    	window.open('api/xmtv1/'+data+'.do');
    }
  //进入我的星推（媒介主个人中心）
  function intoPersonM(){
	  var url='api/xmtv1/personZDjump.do?num=1';
	  window.open(url);
  }
  //进入我的星推（广告主个人中心）
  function intoPersonD(id){
	  var url='api/xmtv1/personGgzJump.do?num=1';
	  window.open(url);
  }
  //退出登录 
  function tuichudl(){
	  var url='api/xmtv1/tuichudl.do';
	  window.location.href=url;
  }
  //清空cookie
  <%--
  function savePaw() {//loginName--password
	  
		if (!$("#saveid").is(":checked")) {
			$.cookie('loginname1', '', {
				expires : -1
			});
			$.cookie('password1', '', {
				expires : -1
			});
			$("#loginName").val('');
			$("#password").val('');
		}
	}
 
  //读取cookie
  jQuery(function() {
		var loginname = $.cookie('loginname1');
		var password = $.cookie('password1');
		if (typeof(loginname) != "undefined"
				&& typeof(password) != "undefined") {
			$("#loginName").val(loginname);
			$("#password").val(password);
			$("#saveid").attr("checked", true);
			$("#validate").focus();
		}
	});
	--%>
</script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
</html>