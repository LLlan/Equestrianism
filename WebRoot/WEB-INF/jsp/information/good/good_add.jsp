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
			if($("#COMID").val()==""){
			$("#COMID").tips({
				side:3,
	            msg:'请输入商家ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COMID").focus();
			return false;
		}
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入商品名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#ORIGINALPRICE").val()==""){
			$("#ORIGINALPRICE").tips({
				side:3,
	            msg:'请输入原价',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ORIGINALPRICE").focus();
			return false;
		}
		if($("#DISCOUNTPRICE").val()==""){
			$("#DISCOUNTPRICE").tips({
				side:3,
	            msg:'请输入折扣价',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DISCOUNTPRICE").focus();
			return false;
		}
		if($("#PACKAGE").val()==""){
			$("#PACKAGE").tips({
				side:3,
	            msg:'请输入商品套餐',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PACKAGE").focus();
			return false;
		}
		if($("#BUYNOTICE").val()==""){
			$("#BUYNOTICE").tips({
				side:3,
	            msg:'请输入购买须知',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BUYNOTICE").focus();
			return false;
		}
		if($("#DESCRIBES").val()==""){
			$("#DESCRIBES").tips({
				side:3,
	            msg:'请输入商品描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DESCRIBES").focus();
			return false;
		}
		if($("#DETAIL").val()==""){
			$("#DETAIL").tips({
				side:3,
	            msg:'请输入简介',
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
			var url = "<%=basePath%>good/deltp.do?tpurl="+tpurl+"&GOOD_ID="+id+"&guid="+new Date().getTime();
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
	<form action="good/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="GOOD_ID" id="GOOD_ID" value="${pd.GOOD_ID}"/>
		<div id="zhongxin">
		<div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->

            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
		<table id="table_report" class="table table-striped table-bordered table-hover">

            <tr>
				<td>选择商家:</td>
				<td>
				     <select class="chzn-select" name="COMID" id="COMID" data-placeholder="请选择商家" style="vertical-align:top;"  title="商家">
				            <option value=""></option>
				            <c:forEach items="${varComs}" var="com">
					          <option value="${com.COMPANY_ID }" <c:if test="${com.COMPANY_ID == pd.COMID }">selected</c:if>>${com.NAME }</option>
				            </c:forEach>
				     </select>
				</td>
			</tr>
			<tr>
				<td>商品名称:</td>
				<td><input type="text" style="width:95%;" name="NAME" id="NAME" value="${pd.NAME}" placeholder="这里输入商品名称" title="商品名称"/></td>
			</tr>
			<tr>
				<td>商品原价:</td>
				<td><input type="text" style="width:40%;" name="ORIGINALPRICE" id="ORIGINALPRICE" value="${pd.ORIGINALPRICE}" placeholder="这里输入商品原价" title="商品原价"/></td>
			</tr>
			<tr>
				<td>商品折扣价:</td>
				<td><input type="text" style="width:95%;" name="DISCOUNTPRICE" id="DISCOUNTPRICE" value="${pd.DISCOUNTPRICE}" placeholder="这里输入商品折扣价" title="商品折扣价"/></td>
			</tr>
			<tr>
				<td>商品首页图片:</td>
				<td>
					<c:if test="${pd == null || pd.IMGURL == '' || pd.IMGURL == null }">
					<input type="file" id="tp" name="tp"/>
					</c:if>
					<c:if test="${pd != null && pd.IMGURL != '' && pd.IMGURL != null }">
						<a href="${pd.IMGURL}" target="_blank"><img src="${pd.IMGURL}" width="210"/></a>
						<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delP('${pd.IMGURL}','${pd.GOOD_ID }');" />
						<input type="hidden" name="tpz" id="tpz" value="${pd.IMGURL }"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>商品套餐:</td>
				<td id="nr">
					<textarea  style="width:93%;height:150px" name="PACKAGE" id="PACKAGE">${pd.PACKAGE }</textarea>
				</td>
			</tr>
			<tr>
				<td>购买须知:</td>
				<td id="nr">
					<textarea  style="width:93%;height:150px" name="BUYNOTICE" id="BUYNOTICE">${pd.BUYNOTICE }</textarea>
				</td>
			</tr>
			<tr>
				<td>商品介绍:</td>
				<td id="nr">
					<textarea  style="width:93%;height:200px" name="DESCRIBES" id="DESCRIBES">${pd.DESCRIBES }</textarea>
				</td>
			</tr>
			<tr>
				<td>一句话描述:</td>
				<td><input type="text" style="width:95%;" name="DETAIL" id="DETAIL" value="${pd.DETAIL}" placeholder="这里输入简单的描述该商品" title="商品描述"/></td>
			</tr>
			<tr>
				<td>选择:</td>
				<td>
					<select name="ISHOME" title="是否首页推广">
					<option value="0" <c:if test="${pd.ISHOME == '0' }">selected</c:if> >首页不推荐</option>
					<option value="1" <c:if test="${pd.ISHOME == '1' }">selected</c:if> >首页推荐</option>
					</select>
					<select name="ISREC" title="是否推荐">
					<option value="0" <c:if test="${pd.ISREC == '0' }">selected</c:if> >不推荐</option>
					<option value="1" <c:if test="${pd.ISREC == '1' }">selected</c:if> >推荐</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>商品状态:</td>
				<td>
					<select name="STATUS" title="商品状态">
					<option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >上架</option>
					<option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >下架</option>
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
		
		<script type="text/javascript" src="plugins/webuploader/webuploader.js"></script>
    	<script type="text/javascript" src="plugins/webuploader/upload_good.js"></script>
    	
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