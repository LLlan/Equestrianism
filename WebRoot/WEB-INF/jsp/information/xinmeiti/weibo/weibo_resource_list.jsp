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
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/weibo_resource_list.css"/>
    <script src="static/js/xinmeiti/jquery-2.1.1.min.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/weibo_resource_list.js"></script>
    <script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
    <title>微博资源管理</title>
</head>
<body>
<!--顶部-->
<div class="main-nav">
    <div class="container top-header" style="border: none;">
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
                            <a href="#" onclick="tuichudl()">【退出】</a>
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
       <li><a href="javascript:void(0)" onclick="liuSourcejump(1)">微信公众号</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(2)">微信朋友圈</a></li>
        <li><a href="javascript:void(0)" style="color: #ffcc28;" onclick="liuSourcejump(3)">微博</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(4)">网红视频直播</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(5)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(6)">黄金广告商</a></li>
    </ul>
</div>
<script type="text/javascript">
//各大模块资源管理页面的跳转
function liuSourcejump(data){
	var url='api/xmtv1/liuSourcejump.do?num='+data;
	window.location.href=url;
}
</script>
<!--主体-->
<div class="container clearfix">
    <div class="container_middle">
        <div class="resource_m">
            <span  class="resource_img"><img style="vertical-align: middle;margin-right: 5px;" src="static/images/xinmeiti/web1.png" alt=""/></span>
            <span>微博资源管理</span>
        </div>
        <div class="resource_t">
            <button class="btn" onclick="addgzhsource()">添加</button>
            <button class="btn" onclick="changeStateAll('确定批量启用吗?')">启用</button>
            <button class="btn" onclick="changeStateAll('确定批量下架吗?')">下架</button>
        </div>
        <script type="text/javascript">
        	//点击添加
        	function addgzhsource(){
        		var url='api/xmtv1/liuAddSourcejump.do?num=3';
        		window.location.href=url;
        	}
        </script>
        <table class="table">
            <tbody>
            <tr>
            	<th class="center" style="width: 0">
					<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
				</th>
                <th>头像</th>
                <%--<th>账号</th>--%>
                <th>名称</th>
                <th>粉丝量</th>
                <th>地区</th>
                <th>硬广直发</th>
                <th>硬广转发</th>
                <th>软广直发</th>
                <th>软广转发</th>
                <th>资源状态</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list1w }" var="list" varStatus="vs" end="9">
            <tr>
            	<td class='center' style="width: 30px;">
            		<c:if test="${list.checkedState=='1' }">
            			<label><input type='checkbox' name='ids' value="${list.microBlog_id }"/><span class="lbl"></span></label>
            		</c:if>
				</td>
            	<td><img src="<%=basePath%>${list.headImgURL}" style="width: 25px;height: 25px"></td>
            	<td>${list.name}</td>
                <td>${list.fansNum}</td>
                <td><span>${list.province }</span>/<span>${list.city }</span></td>
                <td>￥${list.price1 }</td>
                <td>￥${list.price2 }</td>
                <td>￥${list.price3 }</td>
                <td>￥${list.price4 }</td>
                <td id="${list.microBlog_id }">
                	<c:if test="${list.sourceState=='0' }">已停用</c:if>
                	<c:if test="${list.sourceState=='1' }">已启用</c:if>
                </td>
                <td>
                	<c:if test="${list.checkedState=='0' }">已拒绝</c:if>
                	<c:if test="${list.checkedState=='1' }">已通过</c:if>
                	<c:if test="${list.checkedState=='2' }">待审核</c:if>
                </td>
                <td>
                    <a href="javascript:void(0)">
                        <span class="btn_b" onclick="wbtoUpdate('${list.microBlog_id }')">编辑</span>
                    </a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="txt-center">
            <ul style="padding-bottom:20px;" class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
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
<script>
    $(function(){
        function tt(dd){
            //alert(dd);
        }
        var GG = {
            "kk":function(mm){
                // alert(mm);
            }
        };
      //复选框
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				//$(this).closest('tr').toggleClass('selected');
			});	
		});
        $("#page").initPage(71,1,GG.kk);
    });
  //点击编辑
    function wbtoUpdate(tag_id) {
		var url='api/xmtv1/toWbEdite.do?tag_id='+tag_id;
		window.location.href=url;
	}
  //批量启用和下架
	function changeStateAll(msg) {
		var str='';
		for ( var i = 0; i < document.getElementsByName('ids').length; i++) {
			if(document.getElementsByName('ids')[i].checked){
				if(str==''){
					str+=document.getElementsByName('ids')[i].value;
				}else{
					str+=','+document.getElementsByName('ids')[i].value;
				}
			}
		}
		if(str==''){
			$("#zcheckbox").tips({
				side:3,
	            msg:'您还没有选择任何内容,点这里全选',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		//bootbox.confirm(msg,function(result){
			//if(result){
				var num="";
				var text="";
				if(msg=="确定批量下架吗?"){
					num="0";
					text="已停用";
				}else if(msg=="确定批量启用吗?"){
					num="1";
					text="已启用";
				}
				$.ajax({
					type:'post',
					url:'<%=basePath%>api/xmtv1/changeWbsourceState.do',
					dataTyoe:'json',
					cache: false,
					data:{
						"ids":str,
						"num":num
					},
					success:function(data){
						if(data.result=="error"){
							window.location.href="api/xmtv1/toLogin.do";
						}else{
							var arrayid=str.split(",");
							for(var i = 0;i < arrayid.length;i++){
								$("#"+arrayid[i]).html(text);
							}
						}
					},
				});
			//}
		//});	
	}
</script>
</body>
</html>