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

    <link rel="stylesheet" href="static/css/xinmeiti/media_adv_add.css"/>
    <script src="static/js/xinmeiti/jquery-2.1.1.min.js"></script>
    <script src="static/js/xinmeiti/bootstrap.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/media_adv_add.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script>
    <title>广告招商添加资源</title>
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
                <a href="javascript:void(0)" class=" f2"><img src="static/images/xinmeiti/send.png" alt=""/></a>
            </li>
            <li>
                <a href="javascript:void(0)" class=" f3"><img src="static/images/xinmeiti/query.png" alt=""/></a>
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
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(4)">网红视频直播</a></li>
        <li><a href="javascript:void(0)" onclick="liuAddSourcejump(5)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)" style="color: #ffcc28;" onclick="liuAddSourcejump(6)">黄金广告商</a></li>
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
    <form id="advForm" class="container_middle" action="api/xmtv2/${msg}.do" enctype="multipart/form-data" method="post">
    	<input type="hidden" name="media_id" value="${pd.media_id }">
    	<input type="hidden" name="id" value="${pd.id }">
        <div class="resource_m">
            <span  class="resource_img"><img style="vertical-align: middle;margin-right: 5px;" src="static/images/xinmeiti/adv1.png" alt=""/></span>
            <span>${res}广告资源</span>
            <button class="btn  btn-warning">返回</button>
        </div>
        <div class="about_word">
            <!--选择网站-->
            <div class="found-list">
                <div class="found-title">选择网站 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input type="text" id="media" class="form-con1" placeholder="请选择" name="media_name" value="${pd.media_name }" readonly="readonly"/>
                </div>
                <button type="button" data-toggle="modal" data-target="#modal-web" class="btn btn-warning">选择网站</button>
                <!--选择网站详细内容-->
                <div class="modal" id="modal-web" style="display: none;">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button  class="btn btn-warning">选择网站</button>
                                <span data-dismiss="modal" class="close">&times;</span>
                            </div>
                            <div class="modal-body">
                                <div class="add_all">
                                    <div class="search">
                                        <div class="input">
                                            <input type="text"  placeholder="请输入关键字" name="" class="s_ser"/>
                                            <div class="sear"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="add_res available">
                                <!-- 此处迭代 -->
                                	<c:forEach items="${weblist }" var="wlist">
	                                    <div class="col-xs-3">
	                                        <input type="radio" name="website" /><label for="poster">${wlist.name }</label>
	                                    </div>
                                    </c:forEach>
                                <div class="txt-center">
                                    <ul style="padding-bottom:20px;" class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
                                </div>
                            </div>
                           
                        </div>
                         	<div class="modal-footer water">
                                <input class="btn btn-success" type="button" value="确认"/>
                                <input data-dismiss="modal" class="btn btn-warning" type="button" value="取消"/>
                            </div>
                    </div>
                </div>
            </div>
            </div>
             <!--选择频道-->
            <div class="found-list">
                <div class="found-title">选择频道<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input type="text" id="channelName" class="form-con1" name="channelName"  placeholder="请选择" value="${pd.channelName }" readonly="readonly"/>
                </div>
                <button type="button" data-toggle="modal" data-target="#modal-channel" class="btn btn-warning">选择频道</button>
                <!--选择频道详细内容-->
                <div class="modal" id="modal-channel" style="display: none;">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="btn btn-warning">选择频道</button>
                                <span data-dismiss="modal" class="close">&times;</span>
                            </div>
                            <div class="modal-body">
                                <div class="add_all">
                                    <div class="search">
                                        <div class="input">
                                            <input type="text"  placeholder="请输入关键字" name="" class="s_ser"/>
                                            <div class="sear"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="add_res bottled">
                                	<c:forEach items="${channellist }" var="clist">
	                                    <div class="col-xs-3">
	                                        <input type="radio" id="brand" name="channel" /><label for="brand">${clist.name }</label>
	                                    </div>
                                    </c:forEach>
                                </div>
                                <div class="txt-center">
                                    <ul style="padding-bottom:20px;" class="page" maxshowpageitem="5" pagelistcount="10"  id="page1"></ul>
                                </div>
                            </div>
                            <div class="modal-footer collection">
                                <input class="btn btn-success" type="button" value="确认"/>
                                <input data-dismiss="modal" class="btn btn-warning" type="button" value="取消"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--资源名称-->
            <div class="found-list">
                <div class="found-title">资源名称<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input id="resourceName" type="text" class="form-con1" name="resourceName" placeholder="请选择" value="${pd.resourceName }"/>
                </div>
            </div>
            <!--上传头像-->
            <div class="found-list upload_file" style="display: block;">
                <div class="found-title">
                    上传头像
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
				<input id="file" type="file" name="file" value="${pd.media_logo }"/>
                <!-- <div class="form-time">
                    <div class="file">
                        上传头像
                        <input id="file" type="file" name="file"/>
                        
                    </div>
                </div> -->
            </div>
            <!--入口级别-->
            <div class="found-list">
                <div class="found-title">入口级别 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <select id="media_level" class="form-control" name="media_level">
                	<option value="">请选择</option>
                	<c:forEach items="${levellist }" var="llist">
                    	<option value="${llist.level_name }" <c:if test="${pd.media_level==llist.level_name }">selected="selected"</c:if>>${llist.level_name }</option>
                    </c:forEach>
                </select>
            </div>
            <!--入口连接-->
            <div class="found-list">
                <div class="found-title">入口连接<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input type="text" id="linkeHttp" class="form-con1" name="linkeHttp" placeholder="http://" value="${pd.linkeHttp}"/>
                </div>
            </div>
            <!--资源类型-->
            <div class="found-list">
                <div class="found-title" style="padding-right: 18px;">资源类型</div>
                <div class="form-time">
                    <div class="found_type"><input type="checkbox" name="source_type" value="jpg"/>&nbsp;jpg</div>
                    <div class="found_type"><input type="checkbox" name="source_type" value="png"/>&nbsp;png</div>
                    <div class="found_type"><input type="checkbox" name="source_type" value="swf"/>&nbsp;swf</div>
                    <div class="found_type"><input type="checkbox" name="source_type" value="gif"/>&nbsp;gif</div>
                    <div class="found_type"><input type="checkbox" name="source_type" value="flv"/>&nbsp;flv</div>
                    <div class="found_type"><input type="checkbox" name="source_type" value="文字"/>&nbsp;文字</div>
                </div>
            </div>
            <!--图片尺寸-->
            <div class="found-list" >
                <div class="found-title" style="padding-right: 18px;">图片尺寸</div>
                <div class="found-content">
                    <input type="text" id="source_size" class="form-con1" name="source_size" value="${pd.source_size }"/>
                </div>
            </div>
            <!--文件大小-->
            <div class="found-list" >
                <div class="found-title" style="padding-right: 18px;">文件大小</div>
                <div class="found-file">
                    <div class="less">小于</div>
                    <input type="text" id="file_size" class="form-con3" name="file_size" value="${pd.file_size }"/>
                    <div class="great">KB</div>
                </div>
            </div>
            <!--文字大小-->
            <div class="found-list" >
                <div class="found-title" style="padding-right: 18px;">文字字数</div>
                <div class="found-file">
                    <div class="less">小于</div>
                    <input type="text" id="word_size" class="form-con3" name="word_size" value="${pd.word_size }"/>
                    <div class="great">字</div>
                </div>
            </div>
            <!--入口形式-->
            <div class="found-list">
                <div class="found-title">入口形式 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <select id="rukouType" class="form-control" name="rukouType">
                	<option value="" selected="selected">请选择</option>
                	<c:forEach items="${rukoulist }" var="rlist">
                    	<option value="${rlist.rukouType }" <c:if test="${pd.rukouType==rlist.rukouType }">selected="selected"</c:if>>${rlist.rukouType }</option>
                    </c:forEach>
                </select>
            </div>
            <!--正文带链接情况-->
            <div class="found-list">
                <div class="found-title">正文带链接<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <select id="textLink_type" class="form-control" name="textLink_type">
                	<option value="" selected="selected">请选择</option>
                	<c:forEach items="${textlist }" var="tlist">
                    	<option value="${tlist.type }" <c:if test="${pd.textLink_type==tlist.type }">selected="selected"</c:if>>${tlist.type }</option>
                    </c:forEach>
                </select>
            </div>
            <!--百度新闻源-->
            <div class="found-list">
                <div class="found-title">百度新闻源<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <select id="baidu_resource" class="form-control" name="baidu_resource" >
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${pd.baidu_resource =='1' }"> selected="selected"</c:if>>是</option>
                    <option value="2" <c:if test="${pd.baidu_resource =='2' }"> selected="selected"</c:if>>否</option>
                    <option value="3" <c:if test="${pd.baidu_resource =='3' }"> selected="selected"</c:if>>不确定</option>
                </select>
            </div>
            <!--报价-->
            <div class="found-list">
                <div class="found-title">报价<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="form-time">
                    <div class="found-file">
                        <input type="text" id="price" class="form-con4" name="price" placeholder="请输入价格" value="${pd.price }"/>
                        <div class="great">元</div>
                    </div>
                    <p class="form-p1" style="color:red;">媒体网将向广告主收取25%的技术服务费，故前台价格将显示为：您填写的价格*1.25。</p>
                </div>
            </div>
            <!--资源介绍-->
            <div class="found-list cont">
                <div class="found-title">资源介绍<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <div class="form-time">
                    <textarea id="media_intro" style="padding:3px;"  cols="54" rows="10" name="media_intro">${pd.media_intro }</textarea>
                </div>
            </div>
            <!--资源截图-->
            <div class="found-list upload_file">
                <div class="found-title">
                   资源截图
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>

                <div class="form-time">
                    <div class="file">
                        选择文件
                        <input id="fileadv" type="file" name="fileadv" value="${pd.linkeHttp_img }"/>
                    </div>
                    <div class="button">
                        <button id="buttona" class="btn btn-warning">保存</button>
                        
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