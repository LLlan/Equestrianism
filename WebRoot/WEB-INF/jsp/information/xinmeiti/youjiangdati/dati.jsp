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
    <link rel="stylesheet" href="static/css/xinmeiti/dati.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/jquery.SuperSlide.2.1.1.js"></script>
    <script src="static/js/xinmeiti/jquery.liMarquee.js"></script>
    <script src="static/js/xinmeiti/dati.js"></script>
    <title>有奖答题</title>
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
                                <span><a href="#" target="_blank">微信公众号</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>微信朋友圈</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">微信朋友圈</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>微博</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">微博</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>网红直播</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">网红直播</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>新闻发布</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">新闻发布</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>广告招商</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">广告招商</a></span>
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
                <li class="fl"><a class="onx" href="#">有奖答题</a></li>
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
<!--答题区-->
<div class="middle" style="margin-top: 70px;margin-bottom: 30px;">

    <div class="container">
        <div class="Answer">
            <div class="content">
                <!--开始答题-->
                <div class="answer_top">
                    <div class="answer_top_cont" style="text-align: justify">
                        <b>有奖答题规则:</b>
                        <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem cum eaque fugiat necessitatibus nobis odio omnis quasi sapiente tempore! Animi expedita magnam officiis porro quos sit tempore vitae. Magnam, recusandae!</span>
                    </div>
                </div>
                <div class="answer_bottom"></div>
                <button class="btn">开始看题</button>
                <!--答案页面-->
                <!--<div class="answer_top"></div>
                <div class="text_tip">
                    温柔is价格丝光机是个搜i感觉
                </div>
                <div style="display: block;width: 150px;margin-top: 10px;margin-bottom: 10px;overflow: hidden;">
                    <span class="read">1</span>/<span class="num">10</span>
                    <h3 style="margin: 10px 0;">倒计时</h3>
                    <h4>10<b>秒</b></h4>
                </div>
                <button class="btn">下一题</button>-->
                <!--开始答题-->
               <!-- <div class="answer_top"></div>
                <div style="display: block;width: 150px;margin-top: 10px;margin-bottom: 10px;overflow: hidden;">
                    <span class="read">1</span>/<span class="num">10</span>
                    <h3 style="margin: 10px 0;">倒计时</h3>
                    <h4>10<b>秒</b></h4>
                </div>
                <button class="btn">继续看题</button>
                <button class="btn">看图完毕开始答题</button>-->
                <!--有奖答题-->
                    <!--<div class="answer_top"></div>
                    <div class="text_tip">
                   1.这是什么品种的马？
               </div>
                    <div class="subject">
                   <ul>
                       <li>
                           <span class="f1">A</span>
                           <span class="f2 ok  "></span>
                           <span class="f3">汗血宝马</span>
                       </li>
                       <li>

                           <span class="f1">B</span>
                           <span class="f2 error"></span>
                           <span class="f3">马镫宝马</span>
                       </li>
                       <li>
                           <span class="f1">C</span>
                           <span class="f2 "></span>
                           <span class="f3">赫尔斯因</span>
                       </li>
                       <li>
                           <span class="f1">D</span>
                           <span class="f2"></span>
                           <span class="f3">蒙古马</span>
                       </li>
                   </ul>
               </div>
                    <button  class="btn">下一题</button>-->
                <!--答题完成-->
               <!-- <div class="Success">
                    <ul>
                        <li>
                            <h1 class="color">亲，恭喜您答对<span class="color">5</span>题</h1>
                        </li>
                        <li>
                            <h1>奖励您<span class="color">5</span>个积分</h1>
                        </li>
                        <li>
                            <h1>您的记忆力<span class="color">非常棒哦</span></h1>
                        </li>
                        <li>
                            <h3>答题人数：<span class="color">100</span>人</h3>
                            <h3>您的排名：第 <span class="color">61</span>位</h3>
                            <span style="font-size: 12px;font-weight: 600;" class="color">亲，继续加油哦！</span>
                        </li>
                        <li>
                            <button class="btn">继续答题</button>
                            <button class="btn">领取积分</button>

                        </li>
                    </ul>
                </div>-->
            </div>

        </div>
        <!--排行榜-->
        <div class="column_middle_right">
            <div class="middle_right_top">
                有奖答题排行榜
            </div>
            <div class="middle_right_middle">
                <div class="report">
                    <h3>我的排名: <span style="color: #e4393c;">3</span></h3>
                </div>
                <div class="matiyun">
                    <table width="100%" border="0" cellspacing="0" cellpadding="1">
                        <thead>
                        <tr>
                            <td style="text-align: center">
                                <img src="static/images/xinmeiti/jin1.jpg" >
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    674586
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">
                                <img src="static/images/xinmeiti/jin2.png" >
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">陈乔恩</a></td>
                            <td>
                                <div class="two">
                                    5746387
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">


                                <img src="static/images/xinmeiti/jin3.png">

                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">微博搞笑...</a></td>
                            <td>
                                <div class="two" >
                                    478678

                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">
                                4
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two">
                                    34785
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">
                                5
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two">
                                    256786
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">
                                6
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two">
                                    157862
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">
                                7
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two">
                                    86786
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">
                                8
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#"  target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    74532
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">
                                9
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    10527
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                                10
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    7327
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                                11
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    7222
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                                12
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    6850
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                                13
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    6440
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                                14
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    5630
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                                15
                            </td><td>
                            <img src="static/images/xinmeiti/1.jpg" style="width: 25px;height: 25px;"></td>
                            <td style="text-align: center"><a href="#" target="_blank" style="color: #666">马天宇</a></td>
                            <td>
                                <div class="two" >
                                    5440
                                </div>
                            </td>
                            <td>
                                <div class="end"><a href="#" target="_blank">下单</a></div>
                            </td>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
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
<!--滚动文字-->
<script>
    $(function () {
        $('.top_middle').liMarquee({
            direction: 'up'
        });
    });
</script>
</body>
</html>