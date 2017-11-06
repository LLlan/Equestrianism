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
		<title></title>
		
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<link rel="stylesheet" href="static/css/chosen.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	//保存
	function save(){
			if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入类别名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		/*if($("#IMGURL").val()==""){
			$("#IMGURL").tips({
				side:3,
	            msg:'请输入图片',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IMGURL").focus();
			return false;
		}*/
		if($("#DETAIL").val()==""){
			$("#DETAIL").tips({
				side:3,
	            msg:'请输入描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DETAIL").focus();
			return false;
		}
		if($("#STATUS").val()==""){
			$("#STATUS").tips({
				side:3,
	            msg:'请输入状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATUS").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
	//删除图片
	function delP(tpurl,id){
		 if(confirm("确定要删除图片？")){
			var url = "<%=basePath%>category/deltp.do?tpurl="+tpurl+"&CATEGORY_ID="+id+"&guid="+new Date().getTime();
			$.get(url,function(data){
				if(data=="success"){
					alert("删除成功!");
					document.location.reload();
				}
			});
		} 
	}
</script>
	</head>
<body>
    <form action="category/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="CATEGORY_ID" id="CATEGORY_ID" value="${pd.CATEGORY_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">

			<tr>
				<td>类别名称:</td>
				<td><input type="text" style="width:87%;" name="NAME" id="NAME" value="${pd.NAME}" placeholder="这里输入类别名称" title="商品名称"/></td>
			</tr>
			<tr>
				<td>类别图片:</td>
				<td>
					<c:if test="${pd == null || pd.IMGURL == '' || pd.IMGURL == null }">
					<input type="file" id="tp" name="tp"/>
					</c:if>
					<c:if test="${pd != null && pd.IMGURL != '' && pd.IMGURL != null }">
						<a href="${pd.IMGURL}" target="_blank"><img src="${pd.IMGURL}" width="80%"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.IMGURL}','${pd.CATEGORY_ID }');" />
						<input type="hidden" name="tpz" id="tpz" value="${pd.IMGURL }"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>类别描述:</td>
				<td id="nr">
					<textarea  style="width:80%;height:190px" name="DETAIL" id="DETAIL">${pd.DETAIL }</textarea>
				</td>
			</tr>
			<tr>
				<td>选择:</td>
				<td>
					<select name="STATUS" title="状态">
					   <option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >启用</option>
					   <option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >停用</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="center" colspan="2">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 单选框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		
		<!-- 编辑框-->
		<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/plugins/ueditor/";</script>
		<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.js"></script>
		<!-- 编辑框-->
		
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//上传
			$('#tp').ace_file_input({
				no_file:'请选择图片 ...',
				btn_choose:'选择',
				btn_change:'更改',
				droppable:false,
				onchange:null,
				thumbnail:false //| true | large
				//whitelist:'gif|png|jpg|jpeg'
				//blacklist:'exe|php'
				//onchange:''
				//
			});
			
		});
		
		function reurl(){ 
			UE.getEditor('DETAIL');
			} 
		setTimeout('reurl()',500);
		</script>
	
</body>
</html>