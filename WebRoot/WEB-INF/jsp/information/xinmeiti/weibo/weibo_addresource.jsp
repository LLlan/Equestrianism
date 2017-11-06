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
    <link rel="stylesheet" href="static/css/xinmeiti/bootstrap.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/weibo_addresource.css"/>
    <script src="static/js/xinmeiti/jquery-2.1.1.min.js"></script>
    <script src="static/js/xinmeiti/bootstrap.js"></script>
    <script src="static/js/xinmeiti/city.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/weibo_addresource.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>微博添加资源</title>
</head>
<body>
<!--顶部-->
<div class="main-nav">
    <div class="container top-header"  style="border: none;">
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
                <a href="javascript:void(0)" class="f1" onclick="personZDjump(1)"><img src="static/images/xinmeiti/gerenzhongxin.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class="onx admin" onclick="personZDjump(2)"><img src="static/images/xinmeiti/resource.png" alt=""/></a>
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
<div class="Resources">
    <ul>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(1)">微信公众号</a></li>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(2)">微信朋友圈</a></li>
        <li><a href="javascript:void(0)" style="color: #ffcc28;" onclick="liuAddSourcejump(3)">微博</a></li>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(4)">网红视频直播</a></li>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(5)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(6)">黄金广告商</a></li>
    </ul>
</div>
<script type="text/javascript">
//各大模块添加页面的跳转
function liuAddSourcejump(data){
	var url='api/xmtv1/liuAddSourcejump.do?num='+data;
	window.location.href=url;
}
</script>
<!--主体-->
<div class="container clearfix">
    <form class="container_middle" action="api/xmtv1/${msg }.do" enctype="multipart/form-data" method="post" id="wbform">
    	<input type="hidden" name="caozuoType" value="${caozuoType }" id="caozuoType">
    	<input type="hidden" name="microBlog_id" value="${pd.microBlog_id }" id="microBlog_id">
        <div class="resource_m">
            <span  class="resource_img"><img style="vertical-align: middle;margin-right: 5px;" src="static/images/xinmeiti/adv1.png" alt=""/></span>
            <span>微博资源</span>
            <button class="btn  btn-warning" onclick="bankAction()" type="button">返回</button>
        </div>
        <script type="text/javascript">
        	//返回操作
        	function bankAction(){
        		var url='api/xmtv1/liuSourcejump.do?num=3';
        		window.location.href=url;
        	} 
        </script>
        <div class="about_word">
            <!--微博地址-->
            <div class="found-list">
                <div class="found-title">微博地址<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input type="text" class="form-con1" name="microBlogHttp" value="${pd.microBlogHttp }" id="microBlogHttp"/>
                </div>
            </div>
            <!--微博昵称-->
            <div class="found-list">
                <div class="found-title">微博昵称<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input type="text" class="form-con1" name="name" value="${pd.name }" id="name"/>
                </div>
            </div>
            <!--微博头像-->
            <div class="found-list upload_file" style="display: block;">
                <div class="found-title">
                    微博头像
                     <span><c:if test="${caozuoType == 'insert' }"><img src="static/images/xinmeiti/star.png" alt=""/></c:if></span>
                </div>
				<input type="file" name="headImgURLfile" id="headImgURL"/>

                <%--<div class="form-time">
                    <div class="file">
                        选择头像
                        <input type="file"/>
                    </div>
                </div>--%>
            </div>
            <!--粉丝人数-->
            <div class="found-list">
                <div class="found-title">粉丝人数<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="form-time">
                    <div class="found-file">
                        <input type="text" class="form-con4" name="fansNum" id="fansNum" value="${pd.fansNum }"/>
                        <div class="great">人</div>
                    </div>
                </div>
            </div>
            <!--地区-->
            <div class="found-list">
                <div class="found-title">
                    地区
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
                 <div class="form-time">
                    <select name="province" id="province">
                    <c:if test="${caozuoType=='insert' }">
                    	<option value="" selected="selected">不限</option>
                    </c:if>
                     <c:if test="${caozuoType=='update' }">
                    	<option value="${pd.province }" selected="selected">${pd.province }</option>
                    </c:if>  
                    </select>
                    <select name="city" id="city">
                       <c:if test="${caozuoType=='insert' }">
                    	<option value="" selected="selected">不限</option>
                    </c:if>
                     <c:if test="${caozuoType=='update' }">
                    	<option value="${pd.city }" selected="selected">${pd.city }</option>
                    </c:if> 
                    </select>
                </div>
            </div>
            <!--硬广价格-->
            <div class="found-list" >
                <div class="found-title" style="padding-right: 18px;">硬广价格</div>
                <div class="form-time">
                    <div class="found-file">
                        <div class="less">直发</div>
                        <input type="hidden" value="硬广直发" name="name1" id="name1">
                        <input type="text" class="form-con3" placeholder="请输入价格" name="price1" id="price1" <c:if test="${pd.price1!='-1' }">value="${pd.price1 }"</c:if>/>
                        <div class="great">元</div>
                    </div>
                    <div class="found-file">
                        <div class="less">转发</div>
                        <input type="hidden" value="硬广转发" name="name2" id="name2">
                         <input type="text" class="form-con3" placeholder="请输入价格" name="price2" id="price2" <c:if test="${pd.price1!='-1' }">value="${pd.price2 }"</c:if>/>
                        <div class="great">元</div>
                    </div>
                    <div class="refer">参考价格 <span class="yuan">0.00</span>元-<span class="yuan">0.00</span>元</div>
                    <p class="form-p1">硬广价格为选填项，如果您不接硬广，请将硬广价格留空</p>
                </div>
            </div>
            <!--软广价格-->
            <div class="found-list" >
                <div class="found-title">软广价格 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="form-time">
                    <div class="found-file">
                        <div class="less">直发</div>
                        <input type="hidden" value="软广直发" name="name3" id="name3">
                        <input type="text" class="form-con3" placeholder="请输入价格" name="price3" id="price3" value="${pd.price3 }"/>
                        <div class="great">元</div>
                    </div>
                    <div class="found-file">
                        <div class="less">转发</div>
                        <input type="hidden" value="软广转发" name="name4" id="name4">
                        <input type="text" class="form-con3" placeholder="请输入价格" name="price4" id="price4" value="${pd.price4 }"/>
                        <div class="great">元</div>
                    </div>
                    <div class="refer">参考价格 <span class="yuan">0.00</span>元-<span class="yuan">0.00</span>元</div>
                    <p class="form-p1">温馨提示：合理的报价有利于您接到更多订单，获取更多收益</p>
                    <p class="form-p1">建议您将价格定在参考区间范围内，也可以自行定价。</p>
                    <p class="form-p1" style="color: red;">新媒体将向广告主收取25%的技术服务费，故前台显示价格将显示为：您填写的价格*1.25。</p>

                </div>
            </div>
            <!--资源介绍-->
            <div class="found-list cont">
                <div class="found-title">资源介绍<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <div class="form-time">
                     <textarea style="padding:3px;" cols="54" rows="10" name="introduce" id="introduce">${pd.introduce }</textarea>
                </div>
            </div>
            <!--资源介绍-->
            <div class="found-list upload_file">
                <div class="found-title">
                   <%-- 资源介绍
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>--%>
                </div>
                <div class="form-time">
                    <div class="button">
                        <a id="buttona"><button class="btn btn-warning" type="button">保存</button></a>
                        <button class="btn btn-warning" type="reset">清空</button>
                    </div>
                    <div class="star">
                        带<i style="color: #ff9c00">*</i> 号必须填
                    </div>
                </div>
            </div>
        </div>
    </form>
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
<script>
    var GG = {
        "kk":function(mm){
            // alert(mm);
        }
    };
    $("#page").initPage(71,1,GG.kk);
    $("#page1").initPage(71,1,GG.kk);
</script>
</body>
</html>