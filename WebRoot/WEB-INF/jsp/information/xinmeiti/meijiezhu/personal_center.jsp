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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/personal_center.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/personal_center.js"></script>
    <title>媒体主账户</title>
</head>
<body>

<!--顶部-->
<div class="main-nav">
    <div class="container top-header">
        <div class="company-name">
            <a href="#">
                <img src="static/images/xinmeiti/logonews.png" alt="link"/>
            </a>
        </div>
        <div class="h-right">
            <div class="user-quit ">
                账户名：<span>${advertiser.phone }</span>
                <a href="#">
                    <span class="e_meal">(0)</span>
                </a>
                        <span>
                            <a href="javascript:void(0)" onclick="tuichudl()">【退出】</a>
                        </span>
            </div>
            <script type="text/javascript">
				//退出登录 
				function tuichudl(){
					  var url='api/xmtv1/tuichudl.do';
					  window.location.href=url;
				}
			</script>
            <div class="help-conter ">
                <a href="#">账单查询</a>
            </div>
            <div class="personal-center ">
                <a href="#">订单列表</a>
            </div>
            <div class="sevice-m" style="text-align: center">客服电话：0571-86882710</div>
        </div>
    </div>
</div>
<!--导航栏-->
<div class="nav">
    <div class="nav_menu">
        <ul>
             <li>
                <a href="javascript:void(0)" class="onx f1" onclick="personZDjump(1)"><img src="static/images/xinmeiti/gerenzhongxin.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class="admin" onclick="personZDjump(2)"><img src="static/images/xinmeiti/resource.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class="f2" onclick="personZDjump(3)"><img src="static/images/xinmeiti/send.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class=" f3" onclick="personZDjump(4)"><img src="static/images/xinmeiti/query.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class=" f4" onclick="personZDjump(5)"><img src="static/images/xinmeiti/user.png" alt=""/></a>
            </li>
        </ul>
    </div>
    <script type="text/javascript">
    	//跳转到资源管理首页
    	function personZDjump(data){
    		var url='api/xmtv1/personZDjump?num='+data;
    		window.location.href=url;
    	}
    </script>
</div>
<!--资源管理-->
<%--<div class="Resources" style="display: none;">
    <ul>
        <li><a href="javascript:void(0)">微博</a></li>
        <li><a href="javascript:void(0)">微信朋友圈</a></li>
        <li><a href="javascript:void(0)">微信公众号</a></li>
        <li><a href="javascript:void(0)">网红视频直播</a></li>
        <li><a href="javascript:void(0)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)">黄金广告商</a></li>
    </ul>
</div>
--%><!--主体-->
<div class="statistics-money">
    <div class="container">
        <div class="money-inter">
            <div class="block user-info">
                <div class="F1">
                    <span class="a1"></span>
                    ${advertiser.nikeName }
                </div>
                <div  class="F2">
                    <span class="a2"></span>
                    ${advertiser.phone }
                </div>
                <div class="F3">
                    <div class="bank">
                        <a  href="#" class="btn btn-primary center">账单查询</a>
                    </div>
                    <div class="query">
                        <a  href="#" class="btn btn-primary center">银行绑定</a>
                    </div>
                </div>

            </div>
            <div class="block total">
                <div class="img">
                    <img src="static/images/xinmeiti/zongjine.png" alt=""/>
                </div>
                <div class="tip">账户余额</div>
                <div class="number">${advertiser.zhangHYE }元</div>
            </div>
            <div class="block card">
                <div class="img">
                    <img src="static/images/xinmeiti/keyongjine.png" alt=""/>
                </div>
                <div class="tip">已提现</div>
                <div class="number">${advertiser.yiTiXian }元</div>
            </div>
            <div class="block forzen">
                <div class="img">
                    <img src="static/images/xinmeiti/dongjiejine.png" alt=""/>
                </div>
                <div class="tip">总收益</div>
                <div class="number">${advertiser.zongSY }元</div>
            </div>
        </div>
    </div>
</div>
<!--派单列表-->
<div class="body">
    <div class="container">
        <div class="user-center">
            <div class="header-tab" style="margin: 0 0 10px 0; border-bottom: none;">
                <a href="#" class="active">派单列表</a>
            </div>
            <div class="header_tab_rt">
                <span class=" active btn1">可执行</span>
                <span class="btn2">已完成</span>
                <span class="btn3">全部</span>
            </div>
        </div>
        <table style="display: block"  class="table">
            <tbody>
            <tr>
                <th>订单编号</th>
                <th>订单名称</th>
                <th>资源名称</th>
                <th>价格</th>
                <th>平台</th>
                <th>执行时间</th>
                <th>订单状态</th>
                <th>操作</th>
            </tr>
            </tbody>
        </table>
        <div class="btn4">
            <button  class="btn" type="button">查看更多</button>
        </div>
        <!--订单统计-->
        <div class="m_bottom ">
            <div class="order_star ">
                <div class="my_star"><i></i>订单统计</div>

                <div class="m_count m_count_table">
                    <table class="table ">
                        <tbody>
                        <tr>
                            <td>类型</td>
                            <td>数量</td>
                            <td>金额</td>
                        </tr>
                        <tr>
                            <th>订单数</th>
                            <th><span>0</span>单</th>
                            <th><span>￥</span>0</th>
                        </tr>
                        <tr>
                            <th>已完成</th>
                            <th><span>0</span>单</th>
                            <th><span>￥</span>0</th>
                        </tr>
                        <tr>
                            <th>拒单数</th>
                            <th><span>0</span>单</th>
                            <th><span>￥</span>0</th>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="m_rce">
                <div class="my_rce"><i></i>资源统计</div>
                <div class="turn_over clear">
                    <div class="turn_1">
                        <span>${pd.numz }单</span>
                        <span>资源总数</span>
                    </div>
                    <div class="turn_2">
                        <ul>
                            <li>待审核</li>
                            <li class="list">${pd.numzd }单</li>
                            <li>不通过</li>
                            <li class="list">${pd.numzb }单</li>
                            <li>已下架</li>
                            <li class="list">${pd.numzx }单</li>
                        </ul>
                    </div>
                    <div class="turn_3">
                        <ul>
                            <li>
                                <img src="static/images/xinmeiti/weifrind.png" alt=""/>
                            </li>
                            <li>
                                <a href="javascript:void(0)" target="_blank" onclick="liuSourcejump(2)">
                                    <span>${pd.nump }单</span>
                                    <span>朋友圈</span>
                                </a>
                            </li>
                            <li>
                                <img src="static/images/xinmeiti/web1.png" alt=""/>
                            </li>
                            <li>
                                <a href="javascript:void(0)" target="_blank" onclick="liuSourcejump(3)">
                                    <span>${pd.numm }单</span>
                                    <span>微博转发</span>
                                </a>
                            </li>
                            <li>
                                <img src="static/images/xinmeiti/news.png" alt=""/>
                            </li>
                            <li>
                                <a href="javascript:void(0)" target="_blank" onclick="liuSourcejump(5)">
                                    <span>${pd.numx }单</span>
                                    <span>新闻媒体</span>
                                </a>
                            </li>
                            <li>
                                <img src="static/images/xinmeiti/play.png" alt=""/>
                            </li>
                            <li>
                                <a href="javascript:void(0)" target="_blank" onclick="liuSourcejump(1)">
                                    <span>${pd.nump }单</span>
                                    <span>公众号</span>
                                </a>
                            </li>
                            <li>
                                <img src="static/images/xinmeiti/adv1.png" alt=""/>
                            </li>
                            <li>
                                <a href="javascript:void(0)" target="_blank" onclick="liuSourcejump(4)">
                                    <span>${pd.numv }单</span>
                                    <span>网红直播</span>
                                </a>
                            </li>
                            <li>
                                <img src="static/images/xinmeiti/weifrind.png" alt=""/>
                            </li>
                            <li>
                                <a href="javascript:void(0)" target="_blank" onclick="liuSourcejump(6)">
                                    <span>${pd.numg }单</span>
                                    <span>广告招商</span>
                                </a>
                            </li>
                        </ul>
                </div>
                <script type="text/javascript">
					//各大模块资源管理页面的跳转
					function liuSourcejump(data){
						var url='api/xmtv1/liuSourcejump.do?num='+data;
						window.open(url);
					}
				</script>
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

</body>
</html>