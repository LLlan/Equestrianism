<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html" />
    <title></title>
    <script src="<%=basePath%>static/js/mashu/jquery-1.11.3.js"></script>
</head>
<frameset rows="10%">
     <frame  src="<%=basePath%>group/index.do"  name="mainFrame" id="mainFrame"/>
     <frame  src="<%=basePath%>group/mp3.do" name="topFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" >
</frameset>



<body>
	<%-- <iframe src="<%=basePath%>group/index.do" frameborder="0" name="22" style="width: 100%;height: 100%"></iframe> --%>
<%-- <iframe src="<%=basePath%>group/mp3.do" marginwidth=0 marginheight=0 width=100% height=30 frameborder=0></iframe> --%>



</body>
<script type="text/javascript">
</script>
</html>