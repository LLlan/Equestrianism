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
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
</head>

<script type="text/javascript">
	$(top.hangge());
	//保存
	function save(){
		if($("#NAME").val()==""){
			
			$("#NAME").tips({
				side:3,
	            msg:'请输入网站名称',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#NAME").focus();
			return false;
		}
		//判断是否已经存在
		var url='webRes/selectByName.do';
		$.post(url,{"name":$("#NAME").val()},function(data){
			if("已存在" == data.result){
				$("#NAME").tips({
					side:3,
		            msg:'该名称已存在',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			}else{
				$("#Form").submit();
			}
		});
	}
</script>


<body>
	<form action="webRes/${msg }.do" name="Form" id="Form" method="post" >
	<input type="hidden" name="website_id" id="website_id" value="${pd.website_id}"/>
		<div id="zhongxin">
		<table>
			<tr class="info">
				<td><input type="text" name="name" id="NAME" placeholder="这里输入网站名称" value="${pd.name }" title="名称"/></td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
	</form>
</body>

</html>