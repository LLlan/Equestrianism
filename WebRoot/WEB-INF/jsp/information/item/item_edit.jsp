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
		
		
		<link rel="stylesheet" type="text/css" href="plugins/webuploader/webuploader.css" />
		<link rel="stylesheet" type="text/css" href="plugins/webuploader/style.css" />
		
<script type="text/javascript">
	
	//保存
	function save(){
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
	//删除图片
	function delP(tpurl,id,num){
		 if(confirm("确定要删除图片？")){
		 if(num==1){
			var url = "<%=basePath%>item/deltp.do?image1="+tpurl+"&item_id="+id+"&num="+num;
		 }else{
		 	var url = "<%=basePath%>item/deltp.do?image2="+tpurl+"&item_id="+id+"&num="+num;
		 }
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
	<form action="item/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="item_id" id="item_id" value="${pd.item_id}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width: 178px;">题号:</td>
				<td><input type="text" style="width:95%;" name="serial" id="serial" value="${pd.serial}" placeholder="这里输入题号" title="商品名称"/></td>
			</tr>
			
			<tr>
				<td>答案拼接:</td>
				<td><input type="text" style="width:95%;" name="item_name" id="item_name" value="${pd.item_name}" placeholder="这里输入答案拼接（逗号拼接）" title="商品原价"/></td>
			</tr>
			
			<tr>
				<td>正确答案:</td>
				<td><input type="text" style="width:95%;" name="right_answer" id="right_answer" value="${pd.right_answer}" placeholder="这里输入正确答案" title="商品折扣价"/></td>
			</tr>
			
			<tr>
				<td>非阴影图片（题目）:</td>
				<td>
					<c:if test="${pd == null || pd.image1 == '' || pd.image1 == null }">
					<input type="file" id="imageFile1" name="imageFile1"/>
					</c:if>
					<c:if test="${pd != null && pd.image1 != '' && pd.image1 != null }">
						<a href="${pd.image1}" target="_blank"><img src="${pd.image1}" width="150"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.image1}','${pd.item_id }',1);" />
						<input type="hidden" name="image1" id="tpz" value="${pd.image1 }"/>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td>阴影图片（答案背景）:</td>
				<td>
					<c:if test="${pd == null || pd.image2 == '' || pd.image2 == null }">
					<input type="file" id="imageFile2" name="imageFile2"/>
					</c:if>
					<c:if test="${pd != null && pd.image2 != '' && pd.image2 != null }">
						<a href="${pd.image2}" target="_blank"><img src="${pd.image2}" width="150"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.image2}','${pd.item_id }',2);" />
						<input type="hidden" name="image2" id="tpz" value="${pd.image2}"/>
					</c:if>
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
			if($("#item_id").val()==""){
			}else{
				$("#serial").attr("readonly","readonly");
			}
			
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//上传
			$('#imageFile1').ace_file_input({
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
			
			//上传
			$('#imageFile2').ace_file_input({
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
			UE.getEditor('PACKAGE');
			} 
		setTimeout('reurl()',500);
		function reurl2(){ 
			UE.getEditor('BUYNOTICE');
			} 
		setTimeout('reurl2()',500);
		function reurl3(){ 
			UE.getEditor('DESCRIBES');
			} 
		setTimeout('reurl3()',500);
		</script>
	
</body>
</html>