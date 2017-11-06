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
		
		<meta charset="utf-8" />
		<%@ include file="../admin/top.jsp"%> 
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
		
		<!--查看图片插件 -->
		<link rel="stylesheet" media="screen" type="text/css" href="plugins/zoomimage/css/zoomimage.css" />
	    <link rel="stylesheet" media="screen" type="text/css" href="plugins/zoomimage/css/custom.css" />
	    <script type="text/javascript" src="plugins/zoomimage/js/jquery.js"></script>
	    <script type="text/javascript" src="plugins/zoomimage/js/eye.js"></script>
	    <script type="text/javascript" src="plugins/zoomimage/js/utils.js"></script>
	    <script type="text/javascript" src="plugins/zoomimage/js/zoomimage.js"></script>
	    <script type="text/javascript" src="plugins/zoomimage/js/layout.js"></script>
		<!--查看图片插件 -->
<script type="text/javascript">
	
	$(top.hangge());
	
	//全选 （是/否）
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
	
	//审核不通过
	function checkedNo(obj,tagID){
		 var url='api/xmtv1/sysPyqChecked?tagID='+tagID+'&num=1';
		 $.get(url,function(data){
			 $("#"+tagID).html("已拒绝");
		 });
	}
	
	//审核通过
	function checkedYes(obj,tagID){
		 var url='api/xmtv1/sysPyqChecked?tagID='+tagID+'&num=2';
		 $.get(url,function(data){
			 $("#"+tagID).html("已通过");
		 });
	}
	/*$(function() {
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});	
			});
		});*/
		//批量通过和拒绝
		function checkedAll(msg) {
			bootbox.confirm(msg,function(result){
				if(result){
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
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						var num="";
						var text="";
						if(msg=="确定批量拒绝吗?"){
							num="3";
							text="已拒绝";
						}else if(msg=="确定批量通过吗?"){
							num="4";
							text="已通过";
						}
						$.ajax({
							type:'post',
							url:'<%=basePath%>api/xmtv1/sysPyqChecked.do',
							dataTyoe:'json',
							cache: false,
							data:{
								"ids":str,
								"num":num
							},
							success:function(data){
								var arrayid=str.split(",");
								for(var i = 0;i < arrayid.length;i++){
									$("#"+arrayid[i]).html(text);
								}
								$.each(data.list, function(i, list){
									nextPage('${page.currentPage}');
							 });
							},
						});
					}
				}
			});	
		}
</script>
</head>

<body>
<div id="page-content" class="clearfix">
<div class="row-fluid">
	
	<!-- 检索  -->
	<form action="<%=basePath%>/api/xmtv1/sysPyqlistPage.do" method="post" name="userForm" id="userForm">
	<table>
		<tr>
			<td>
				<span class="input-icon">
					<input autocomplete="off" id="nav-search-input" type="text" name="searchName" value="${pd.searchName }" placeholder="这里输入关键词" />
					<i id="nav-search-icon" class="icon-search"></i>
				</span>
			</td>
			<td><c:if test=""></c:if>
				<select name="selectName" style="border-radius: 4px!important;border-color: #6fb3e0;height: 28px!important;font-size: 12px;width: 120px;">
					<option value="">请选择审核状态</option>
					<option value="0" <c:if test="${pd.selectName!='' && pd.selectName=='0' }">selected="selected"</c:if>>已拒绝</option>
					<option value="1" <c:if test="${pd.selectName!='' && pd.selectName=='1' }">selected="selected"</c:if>>已通过</option>
					<option value="2" <c:if test="${pd.selectName!='' && pd.selectName=='2' }">selected="selected"</c:if>>待审核</option>
				</select>
			</td>
			<td style="vertical-align:top;">
				<button class="btn btn-mini btn-light" onclick="search();" title="检索">
					<i id="nav-search-icon" class="icon-search"></i>
				</button>
			</td>
		</tr>
	</table>
	<!-- 检索  -->
	<table id="table_report" class="table table-striped table-bordered table-hover">
		<thead>
		<tr>
			<th class="center">
				<label><input type="checkbox" id="zcheckbox" onclick="selectAll()"/><span class="lbl"></span></label>
			</th>
			<th class="center"  style="width: 50px;">序号</th>
			<th class='center'>账号</th>
			<th class='center'>昵称</th>
			<th class='center'>性别</th>
			<th class='center'>粉丝数</th>
			<th class='center'>头像</th>
			<th class='center'>粉丝截图</th>
			<th class='center'>个人信息截图</th>
			<th class='center'>审核状态</th>
			<th class='center'>时间</th>
			<th class='center'>操作</th>
		</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="var" varStatus="vs">
				<tr>
					<td class='center' style="width: 30px;">
				 		<label><input type='checkbox' name='ids' value="${var.WCfriends_id }"/><span class="lbl"></span></label>
					</td>
					<td class='center' style="width: 30px;">${vs.index+1}</td>
					<td class='center'>${var.account}</td>
					<td class='center'>${var.name}</td>
					<td class='center'>${var.sex}</td>
					<td class='center'>${var.fansNum}</td>
					<td class='center'>
						<a href="<%=basePath%>${var.headImgURL}" class="bwGal"><img alt="" src="<%=basePath%>${var.headImgURL}" style="width: 30px;height: 30px;"></a>
					</td>
					<td class='center'>
						<a href="<%=basePath%>${var.fansNumImgURL}" class="bwGal"><img alt="" src="<%=basePath%>${var.fansNumImgURL}" style="width: 30px;height: 30px;"></a>
					</td>
					<td class='center'>
						<a href="<%=basePath%>${var.informationImgURL}" class="bwGal"><img alt="" src="<%=basePath%>${var.informationImgURL}" style="width: 30px;height: 30px;"></a>
					</td>
					<td class='center' id="${var.WCfriends_id }">
						<c:if test="${var.checkedState=='0' }">已拒绝</c:if>
						<c:if test="${var.checkedState=='1' }">已通过</c:if>
						<c:if test="${var.checkedState=='2' }">待审核</c:if>
					</td>
					<td class='center'>${var.time}</td>
					<td style="width: 80px;">
						<a class='btn btn-mini btn-info' title="拒绝" onclick="checkedNo(this,'${var.WCfriends_id }')" >拒绝</a>
						<a class='btn btn-mini btn-danger' title="通过"  onclick="checkedYes(this,'${var.WCfriends_id }')">通过</a>
					</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
				<td colspan="100" class="center">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;width:50px;">
					<a class="btn btn-small btn-success" onclick="checkedAll('确定批量通过吗?');">批量通过</a>
					<a class="btn btn-small btn-danger" onclick="checkedAll('确定批量拒绝吗?');" style="margin: -55px 0 0 88px;width: 52px;text-align: center;">批量拒绝</a>
				</td>
				
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
	</form>
</div>
</div>
		<style type="text/css">
		li {list-style-type:none;}
		</style>
		<ul class="navigationTabs">
            <li><a></a></li>
            <li></li>
        </ul>
</body>
</html>