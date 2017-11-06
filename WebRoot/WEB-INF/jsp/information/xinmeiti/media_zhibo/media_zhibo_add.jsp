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
    <link rel="stylesheet" href="static/css/xinmeiti/media_zhibo_add.css"/>
    <script src="static/js/xinmeiti/jquery-2.1.1.min.js"></script>
    <script src="static/js/xinmeiti/bootstrap.js"></script>
    <script src="static/js/xinmeiti/city.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/media_zhibo_add.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>网红视频直播${res }资源</title>
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
<!--资源管理-->
<div class="Resources">
    <ul>
     	<li><a href="javascript:void(0)" onclick="liuAddSourcejump(1)">微信公众号</a></li>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(2)">微信朋友圈</a></li>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(3)">微博</a></li>
        <li><a href="javascript:void(0)" style="color: #ffcc28;" onclick="liuAddSourcejump(4)">网红视频直播</a></li>
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
    <form class="container_middle" action="api/xmtv2/${msg}.do" enctype="multipart/form-data" method="post" id="zhiboForm">
    <input type="hidden" name="videoPlatform_id" value="${pd.videoPlatform_id }">
    <input type="hidden" name="base_information_id" value="${pd.base_information_id }">
        <div class="resource_m">
            <span  class="resource_img"><img style="vertical-align: middle;margin-right: 5px;" src="static/images/xinmeiti/adv1.png" alt=""/></span>
            <span>${res }视频直播资源</span>
            <button class="btn  btn-warning">返回</button>
        </div>
        <div class="about_word">
            <!--入口级别-->
            <div class="found-list">
                <div class="found-title">平台 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <select id="platformName" class="form-control" name="platformName">
                	<option value="" selected="selected">请选择</option>
                	<c:forEach items="${livelist }" var="ilist">
                    	<option value="${ilist.name }" <c:if test="${pd.platformName==ilist.name }"> selected="selected"</c:if>>${ilist.name }</option>
                    </c:forEach>
                </select>
            </div>
            <!--昵称-->
            <div class="found-list">
                <div class="found-title">昵称<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input id="name" type="text" class="form-con1" name="name" placeholder="昵称" value="${pd.name }"/>
                </div>
            </div>
            <!--数字ID-->
            <div class="found-list">
                <div class="found-title">房间ID<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input id="number" type="text" class="form-con1" name="number" placeholder="数字ID" value="${pd.number }"/>
                </div>
            </div>
            <!--头像-->
            <div class="found-list upload_file" style="display: block;">
                <div class="found-title">
                    头像
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
				<input id="file1" type="file" name="headfile" <c:if test="${pd.headImgURL!='' && pd.headImgURL!=null}">value="${pd.headImgURL }"</c:if>/>
                <!-- <div class="form-time">
                    <div class="file">
                        选择图片
                        <input id="file1" type="file" name="file"/>
						</div>
                </div> -->
            </div>
            <!--性别-->
            <div class="found-list">
                <div class="found-title">
                    性别
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>

                <div class="found-text">
                    <input type="radio" class="form-con1" name="sex" value="男" <c:if test="${pd.sex=='男' || pd.sex==''}">checked="checked"</c:if>/>男
                    <input type="radio" class="form-con2" name="sex" value="女" <c:if test="${pd.sex=='女' }">checked="checked"</c:if>/>女
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
                        <option value="省份" selected="selected">不限</option>
                    </select>
                    <select name="city" id="city">
                        <option value="城市" selected="selected">不限</option>
                    </select>
                </div>
            </div>
            <!--粉丝人数-->
            <div class="found-list">
                <div class="found-title">粉丝人数<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="form-time">
                    <div class="found-file">
                        <input id="fansnumber" type="text" class="form-con4" name="fansnumber" value="${pd.fansNum }"/>
                        <div class="great">人</div>
                    </div>
                </div>
            </div>
            <!--粉丝人数截图-->
            <div class="found-list upload_file" style="display: block;">
                <div class="found-title">
                    粉丝人数截图
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
				<input type="file" id="file2" name="filefans" value=""/>
                <%-- <div class="form-time">
                    <div class="file">
                        选择文件
                       <!--  <input type="file" name="filefans"/> -->
					 	<c:if test="${pd == null || pd.fansNumImgURL == '' || pd.fansNumImgURL == null }">
							<input type="file" id="file2" name="filefans" onchange="fileType(this)" />
						</c:if>
						<c:if test="${pd != null && pd.fansNumImgURL != '' && pd.fansNumImgURL != null }">
							<a href="<%=basePath%>${pd.fansNumImgURL}" target="_blank">
								<img src="<%=basePath%>${pd.fansNumImgURL}" width="210" /> 
							</a>
							<input type="button" class="btn btn-mini btn-danger" value="删除"
								onclick="delP('${pd.fansNumImgURL}','${pd.merchant_id }');" />
							<input type="hidden" name="headImgURL" id="tpz" value="${pd.fansNumImgURL }" />
						</c:if>
						</div>
                </div> --%>
            </div>
            <!--价格-->
            <div class="found-list" >
                <div class="found-title" style="padding-right: 18px;">价格 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="form-time">
                	<%-- <c:if test="${vplist!='' }">
		                <c:forEach items="${vplist }" var="list">
		                    <div class="found-file">
		                        <div class="less">${list.name }</div>
		                        <input id="price1" type="text" class="form-con3" name="price" placeholder="请输入价格" value="${list.price }"/>
		                        <div class="great">元</div>
		                    </div>
		                    <br/>
		                    </c:forEach>
		             </c:if> --%>
		            <%--  <c:if test="${vplist==''&& vplist ==null }"> --%>
						<div class="found-file">
							<div class="less">专场直播</div>
							<input id="price1" type="text" class="form-con3" name="price" placeholder="请输入价格" value="" />
							<div class="great">元</div>
						</div>
						<br />
						<div class="found-file">
	                        <div class="less">直播植入</div>
	                        <input id="price2" type="text" class="form-con3" name="price" placeholder="请输入价格" value=""/>
	                        <div class="great">元</div>
	                    </div>
		            <%-- </c:if> --%>
                    <p class="form-p1" style="color:red;">两项至少选填一项；星推网将向广告主收取一定的技术服务费，故前台显示价格将略高于您填写的价格，具体计算公式请参考帮助中心</p>
                </div>
            </div>
            <!--视频案例-->
            <div class="found-list">
                <div class="found-title">视频案例<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="form-time">
                    <div class="found-content">
                        <input id="videoURL" type="text" class="form-con1" name="videoURL" placeholder="http://" value="${pd.videoURL }"/>
                    </div>
                    <p class="form-p1">请填写往期视频的播放地址</p>
                </div>

            </div>

            <!--资源介绍-->
            <div class="found-list cont">
                <div class="found-title">资源介绍<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <div class="form-time">
                    <textarea id="introduce" style="padding:3px;"  cols="54" rows="10" name="introduce">${pd.introduce }</textarea>
                </div>
            </div>
            <!--资源介绍-->
            <div class="found-list upload_file">
                <div class="found-title">
                  <!--   资源介绍 -->
                    <!-- <span><img src="static/images/xinmeiti/star.png" alt=""/></span> -->
                </div>
                <div class="form-time">
                    <div class="button">
                        <button id="buttona" class="btn btn-warning" type="button">保存</button>
                        <button class="btn btn-warning">清空</button>
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