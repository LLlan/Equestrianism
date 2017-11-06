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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/xinmeiti/page.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/foundation-datepicker.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/bootstrap.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/adv_create.css"/>
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/foundation-datepicker.js"></script>
    <script src="static/js/xinmeiti/foundation-datepicker.zh-CN.js"></script>
    <script src="static/js/xinmeiti/bootstrap.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/adv_create.js"></script>
    <title>广告创建单</title>
</head>
<body>
<!--顶部-->
<div class="top">
    <div class="middle">
        <div class="top_left"><span>您好！欢迎来到新媒体广告网</span></div>
        <div class="top_middle "><span>滚动文字滚动文字滚动文字滚动文字滚动文字滚动文字</span></div>
        <div class="top_right"><span>客服电话：0898-14253868</span></div>
    </div>
</div>
<!--logo-->
<div class="log">
    <div class="middle">
        <div class="l_logo">
            <a href="#">
                <img src="static/images/xinmeiti/logonews.png" alt=""/>
            </a>
        </div>
        <div class="l_adv">轮播广告位</div>
        <div class="l_list">
            <span class="l_list1">
                <a href="#">我要发广告</a>
            </span>
           <span class="l_list2">
               <a href="#">我要接广告</a>
           </span>
        </div>
    </div>
</div>
<!--分类-->
<div class="classify">
    <div class=" seem">
        <div class="sidbar fl">
            <div class="all clearfix">
                <b>全部服务分类</b>
            </div>
            <div class="sidbar_main index_sidbar" style="display: none;">
                <div id="changing_over">
                    <ul class="clearfix" id="show_table">
                        <li class="">
                            <div class="title"><span>微信公众号</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">微信公众号</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>微信朋友圈</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">微信朋友圈</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>微博</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">微博</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>网红直播</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">网红直播</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>新闻发布</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">新闻发布</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li class="">
                            <div class="title"><span>广告招商</span></div>
                            <div class="bar_pad clearfix">
                                <span><a href="#" target="_blank">广告招商</a></span>
                                <div class="clear"></div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="navmain fl">
            <ul>
                <li class="fl"><a href="#">首页</a></li>
                <li class="fl"><a  href="#">有奖答题</a></li>
                <li class="fl"><a href="#">积分兑换</a></li>
                <li class="fl"><a href="#">七嘴八舌</a></li>
                <li class="fl"><a href="#">加入我们</a></li>
                <li class="fl"><a href="#">关于我们</a></li>
            </ul>
        </div>
        <div class="search fr">
            <div class="input">
                <input type="text" id="textsearch" placeholder="您想要找什么，请输入关键字" class="s_ser"/>
                <div class="sear"></div>
            </div>
        </div>
    </div>
</div>
<!--创建订单-->
<div class="about">
    <form action="<%=basePath%>api/guangGao/order/save.do" class="about1 clearfix" id="guangGaoFrom" method="post">
        <div class="about_top">
            <span>创建广告招商订单</span>
        </div>
            <!--活动名称-->
            <div class="found-list">
                <div class="found-title">活动名称 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <div class="found-content">
                    <input type="text" name="arimOrder_title" class="form-con1" placeholder="请输入活动名称，请勿超过40个字"/>
                </div>
            </div>
            <!--稿件内容-->
            <div class="found-list">
                <div class="found-title">
                    稿件内容
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>

                <div class="found-text">
                    <input type="radio" class="form-con2" checked/>上传文件
                    <input type="radio" class="form-con2" />上传视频
                </div>
            </div>
            <!--上传文件-->
            <div class="found-list upload_file" style="display: block;">
                <div class="found-title">
                    上传文件
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>

                <div class="form-time">
                    <div class="file">
                        选择文件
                        <input type="file"/>
                    </div>
                    <p class="form-p1">提醒:请确认您的稿件链接或文档无误，如因稿件问题导致发布异常，新闻媒体不承担责任。</p>
                </div>
            </div>
            <!--上传视频-->
            <div class="found-list upload_video" style="display: none;">
                <div class="found-title">
                    上传视频
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>

                <div class="form-time video">
                    <div class="file">
                        选择视频
                        <input type="file"/>
                    </div>
                    <p class="form-p1">提醒:请确认您的稿件链接或文档无误，如因稿件问题导致发布异常，新闻媒体不承担责任。</p>
                </div>
            </div>
            <!--开始时间-->
            <div class="found-list">
                <div class="found-title">
                    开始时间
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
                <div class="form-time">
                    <div class="input-group ">
                        <input type="text" name="newsOrder_optime" class="form-control"  placeholder="开始时间" id="demo-1">
                    </div>
                    <p  class="form-p1">请选择当前时间24小时后</p>
                </div>
            </div>
            <!--截止时间-->
            <div class="found-list">
                <div class="found-title">
                    截止时间
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
                <div class="form-time">
                    <div class="input-group  ">
                        <input type="text" name="newsOrder_edtime" class="form-control " placeholder="截止时间" id="demo-2">
                    </div>
                    <p  class="form-p1">请选择当前时间48小时后</p>
                </div>
            </div>
            <!--选择资源-->
            <div class="found-list">
                <div class="found-title">
                    选择资源
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
                <div class="form-time">
                    <div class="form-btn">
                        <!--<button data-toggle="modal" data-target="#modal-login" class="btn_pray">添加资源</button>-->
                        <button data-toggle="modal" data-target="#modal-login" class="btn btn-warning" type="button">添加资源</button>

                        <!--资源列表-->
                        <div class="modal fade" id="modal-login">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                       <button class="btn btn-warning">添加资源1</button>
                                        <span data-dismiss="modal" class="close">&times;</span>
                                    </div>
                                    <div class="modal-body">
                                        <div class="add_all">
                                            <div class="search">
                                                <div class="input">
                                                    <input type="text"  placeholder="请输入关键字" class="s_ser"/>
                                                    <div class="sear"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="add_res">
                                            <table class="table">
                                                <tbody>
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td>名称</td>
                                                    <td>入口形式</td>
                                                    <td>入口级别</td>
                                                    <td>价格</td>
                                                </tr>
                                                </tbody>
                                                <tbody id="Temp">
                                                    <c:choose>
                                                    <c:when test="${not empty plist}">
                                                    	<c:forEach items="${plist }" var="pl">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" class="ck" value="${pl.media_id }"/>
                                                    </td>
                                                    <td class='center' style="width: 30px;">
                                                    	<%--  <td>${pl.media_logo }</td> --%>
	                                                   	 <td>${pl.media_name }</td>
	                                                   	 <td>${pl.rukouType }</td>
	                                                     <td>${pl.media_level }</td>
	                                                     <td>${pl.price}</td>
                                                </tr>
                                                	</c:forEach>
                                                    </c:when>
                                                    </c:choose>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="add_tip">已选择<span id="ckeck_span">0</span>项资源</div>
                                        <div class="txt-center">
                                            <ul style="padding-bottom:20px;" class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                          <input class="btn btn-success" id="save" type="button" value="确认"  onclick="queren()"/>
                                        <input data-dismiss="modal" class="btn btn-warning" type="button" value="取消"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--添加资源-->
                        <div class="form-tab">
                            <table class="table">
                                <tbody>
                                    <tr>
                                    	<th></th>
                                        <th>图片</th>
                                        <th>资源名称</th>
                                        <th>入口形式</th>
                                        <th>入口级别</th>
                                        <th>价格</th>
                                        <th>操作</th>
                                    </tr>
                                </tbody>
                                <tbody id="d">
                                    <c:forEach items="${list }" var="pd">
                                    <tr>
                                    <th><input type="hidden" name="media_id" value="${pd.media_id }"/></th>
                                        <%-- <th>${pd.media_logo }</th> --%>
                                        <th></th>
                                        <th>${pd.media_name }</th>
                                       	<th>${pd.rukouType }</th>
                                        <th>${pd.media_level }</th>
                                        <th class="t">${pd.price }</th>
                                        <th>
                                            <input type="button" value="删除"id="delY"/>
                                        </th>
                                    </tr> 
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-chioce">
                        您共选择了
                        <span class="option">1</span>项资源，合计价格￥
                        <span class="price">2000</span>
                    </div>
                </div>
            </div>
            <div class="found-list">
                <div class="found-title">
                    短信通知
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
                <div class="form-time">
                    <div class="form-p2">
                        <input type="checkbox" checked/>
                        手机短信通知
                    </div>
                    <div class="form-p2" style="margin: 5px 0 10px 0;">
                        <input type="checkbox" checked/>
                        同意相关<span class="agree">服务协议</span>
                    </div>
                    <button id="fabu" class="btn btn-warning">发布</button>
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
<script type="text/javascript" src="static/js/xinmeiti/jquery.liMarquee.js"></script>
<!--滚动文字-->
<script>
    $(function () {
     
   var tdnum=$("#d tr").length;
    var price=$("th.t").text();
    $(".price").html(price);
   
    $("#save").click(function(){
        	//拼接id
        	var ids="";
        	$(".ck:checked").each(function(){
        		ids+=","+$(this).val();
        	});
        	id=ids.substring(1);
        	//ajax通过ID查询显示
        	var url ='<%=basePath%>api/guangGao/order/getById.do';
        	$.ajax({
					type:"POST",
					data:{id:id},
					dataType:"json",
					url:url,
					success:function(data){ 
					var darray = data.resultList;
						for(var i=0;i<darray.length;i++){
						var list=darray[i];
							for(var j=0;j<list.length;j++){
					 			$("#d").append('<tr><td><input type="hidden" name="media_id" value='+list[j].media_id+'> </td> <th></th><th>'+list[j].media_name+'</th><th>'+list[j].rukouType+'</th><th>'+list[j].media_level+'</th><th><span id="price">'+list[j].price+'</span></th><th><input type="button" onClick="delTr(this)"  value="删除"/></th></tr>');
							}
						}
						//refresh();
						getTotol();
						del();
					}
			});
			
        	
        });
        
       
        
    $("#Temp input").click(function(){
        	var num=$("#Temp input:checked").length;
        	$("#ckeck_span").html(num);
        	
        });
        
        $("#fabu").click(function(){
        	$("#guangGaoFrom").submit();
        });
   	 //删除原始的Tr
     $("#delY").click(function(){
    	$(this).parent().parent().remove();
    	getTotol();
    	del();
    });
        $('.top_middle').liMarquee({
            direction: 'up'
        });
        $('#demo-1').fdatepicker({
            format: 'yyyy-mm-dd hh:ii',
            pickTime: true
        });
        $('#demo-2').fdatepicker({
            format: 'yyyy-mm-dd hh:ii',
            pickTime: true
        });

        function tt(dd){
        }
        var GG = {
            "kk":function(mm){
            }
        };
        $("#page").initPage(71,1,GG.kk);
    });
    function delTr(k){
	 	$(k).parent().parent().remove(); 
	 	getTotol();
	 	del();
    } 
    
     function getTotol(){
        var priceTotol=0;
        priceTotol = parseFloat(priceTotol);
	        $("span[id='price']").each(function(i){
	        	priceTotol+=parseFloat($(this).text());
	        });
	        //原始价格
	        var ysPrice = parseFloat($("th.t").text());
	        if(isNaN(ysPrice)){
	      	  ysPrice = 0;
	        }else{
	        	ysPrice =ysPrice;
	        }
        	 $(".price").html(priceTotol+ysPrice);
        	 
        }
        function del(){
        	var count=$("#d tr").length;
        	$(".option").html(count);
        	
        }
        
</script>

</script>
	<script type="text/javascript">
                              
		//点击确认
		function queren(){
			$(".modal").hide();
			$(".modal-backdrop").remove();
			$("body").css("overflow", "scroll");
			
		}
	</script>

</body>
</html>
