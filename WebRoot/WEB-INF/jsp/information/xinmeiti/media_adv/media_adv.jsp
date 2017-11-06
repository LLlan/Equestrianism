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
    <link rel="stylesheet" href="static/css/xinmeiti/media_adv.css"/>
    <script src="static/js/xinmeiti/jquery-2.1.1.min.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/media_adv.js"></script>
    <title>广告招商资源管理</title>
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
       <li><a href="javascript:void(0)" onclick="liuSourcejump(1)">微信公众号</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(2)">微信朋友圈</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(3)">微博</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(4)">网红视频直播</a></li>
        <li><a href="javascript:void(0)" onclick="liuSourcejump(5)">新闻媒体发布</a></li>
        <li><a href="javascript:void(0)" style="color: #ffcc28;" onclick="liuSourcejump(6)">黄金广告商</a></li>
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
            <span  class="resource_img"><img style="vertical-align: middle;margin-right: 5px;" src="static/images/xinmeiti/adv1.png" alt=""/></span>
            <span>广告招商资源管理</span>
        </div>
        <div class="resource_t">
            <button class="btn" id="add">添加</button>
            <button class="btn">启用</button>
            <button class="btn">下架</button>
        </div>
        <table class="table">
            <tbody>
            <tr>
            	<th class="center" onclick="selectAll()">
					<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
				</th>
                <th>头像</th>
                <th>媒体名称</th>
                <th>入口等级</th>	
                <th>入口形式</th>
                <th>价格</th>
                <th>资源状态</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
           	<c:forEach items="${aolist }" var="list">
	            <tr>
	            	<td class='center' style="width: 30px;">
						<label><input type='checkbox' name='ids' value="${list.media_id}" /><span class="lbl"></span></label>
					</td>
	                <td class="pic"><img src="${list.media_logo }" alt=""/></td>
	                <td>${list.media_name }</td>
	                <td>${list.media_level }</td>
	                <td>${list.rukouType}</td>
	                <td>${list.price }</td>
	                <td id="${list.WCpublic_id }">
                	<c:if test="${list.sourceState=='0' }">已停用</c:if>
                	<c:if test="${list.sourceState=='1' }">已启用</c:if>
                </td>
                <td>
                	<c:if test="${list.checkedState=='0' }">已拒绝</c:if>
                	<c:if test="${list.checkedState=='1' }">已通过</c:if>
                	<c:if test="${list.checkedState=='2' }">待审核</c:if>
                </td>
	                <td>
	                    <a href="javascript:void(0)" onclick="getupdata('${list.media_id}')">
	                        <span class="btn_b">编辑</span>
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

        $("#page").initPage(71,1,GG.kk);
    })
    
    $(function(){
    	$("#add").click(function(){
    		var url = "api/xmtv2/goAddAdver.do";
    		window.location.href = url;
    	})
    })
    
    /* 点击修改按钮能出现的操作 */
    function getupdata(tid){
    	var url = "api/xmtv2/toEditAdver.do?tid="+tid;
    	window.location.href = url;
    	//window.location.href = url+'tid='+tid;
    }
    
    //全选（是/否）
		function selectAll(){
			 var checklist = document.getElementsByName ("ids");
			   if(document.getElementById("zcheckbox").checked){
			   for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = 1;
			   } 
			 }else{
			  for(var j=0;j<checklist.length;j++){
			     checklist[j].checked = 0;
			  }
			}
		}
</script>
</body>
</html>