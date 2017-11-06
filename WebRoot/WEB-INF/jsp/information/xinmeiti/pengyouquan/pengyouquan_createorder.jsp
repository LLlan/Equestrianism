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
    <!--<link rel="stylesheet" href="static/css/xinmeiti/foundation.min.css"/>-->
    <link rel="stylesheet" href="static/css/xinmeiti/common.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/liMarquee.css"/>
    <link rel="stylesheet" href="static/css/xinmeiti/pengyouquan_create.css"/>
    <!--[if lt IE 9]>
    <script src="static/js/xinmeiti/html5shiv.min.js"></script>
    <script src="static/js/xinmeiti/respond.min.js"></script>
    <![endif]-->
    <script src="static/js/xinmeiti/jquery-1.11.1.min.js"></script>
    <script src="static/js/xinmeiti/foundation-datepicker.js"></script>
    <script src="static/js/xinmeiti/foundation-datepicker.zh-CN.js"></script>
    <script src="static/js/xinmeiti/bootstrap.js"></script>
    <script src="static/js/xinmeiti/page.js"></script>
    <script src="static/js/xinmeiti/pengyouquan_create.js"></script>
    <title>朋友圈创建单</title>
<style type="text/css">
	.zjh{
		border: 1px solid #e4d7d7;
		line-height: 28px;
		background-color: #e4d7d7;
		text-align: center;
		border-radius: 5px;
	}
</style>
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
    <form class="about1 clearfix" action="api/xmtv1/createPyqorder.do" enctype="multipart/form-data" method="post" id="pyqorderform">
    	<input type="hidden" value="${pd.WCfriends_id }" id="ids" name="ids">
        <div class="about_top">
            <span>创建微信朋友圈订单</span>
        </div>
        <div class="about_word">
            <!--合作形式-->
            <div class="found-list">
                <div class="found-title">投放形式 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="form-time">
                <input type="text" class="form-con1 zjh" value="${pd.name1 }" readonly="readonly" name="type_name" id="typeName"/>
                    <%--<select class="form-control">
                        <option  value="1">直发</option>
                        <option  value="2">分享</option>

                    </select>
                    <div class="image">
                        <img src="static/images/xinmeiti/about.png" alt=""/>
                        <div class="info" style="display: none">
                            <h1>硬广/分享</h1>
                            <p>直发：指账号在自己的朋友圈直接发送推广信息</p>
                            <p>分享：指账号从别的地方将推广信息分享到自己的朋友圈.</p>
                        </div>
                    </div>
                    --%>
                </div>
                
            </div>
            <!--订单名称-->
            <div class="found-list">
                <div class="found-title">订单名称<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input type="text" class="form-con1" placeholder="请输入订单名称，请勿超过32个汉字" maxlength="32" name="name" id="name"/>
                </div>
                <div class="number"><span>0</span>/32</div>
            </div>
            <!--开始时间-->
            <div class="found-list">
                <div class="found-title">
                    开始时间
                    <span><img src="static/images/xinmeiti/star.png" alt=""/></span>
                </div>
                <div class="form-time">
                    <input  style="display: inline-block" type="text" class="form-control" id="demo-1" name="beginTime">
                    <p class="form-p1">须晚于当前时间至少30分钟</p>
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
                        <button data-toggle="modal" data-target="#modal-login" class="btn btn-warning" type="button" onclick="tianjiaziyuan()">添加资源</button>
                        <!--资源列表-->
                        <div class="modal fade" id="modal-login">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button class="btn btn-warning">添加资源</button>
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
                                                    <td>账号</td>
                                                    <td>昵称</td>
                                                    <td>粉丝量</td>
                                                    <td>地区</td>
                                                    <td>价格</td>
                                                </tr>
                                                </tbody>
                                                <tbody id="tianjiazy">
                                                <%-- 
                                                <tr>
                                                    <td>
                                                        <input type="checkbox"/>

                                                    </td>
                                                    <td>
                                                        <span>
                                                            <img src="static/images/xinmeiti/zq.png">
                                                        </span>
                                                        <span>星空物语</span>
                                                    </td>
                                                    <td class="introduce">微信好友大多数都是关于是否撒的发生的冯绍峰股票以及个人多朋友等</td>
                                                    <td>江苏</td>
                                                    <td>男</td>
                                                    <td>1234</td>
                                                    <td>￥23753</td>
                                                </tr>
                                                --%>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="add_tip">已选择<span id="ckeck_span">0</span>项资源</div>
                                        <div class="txt-center">
                                            <ul style="padding-bottom:20px;" class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input class="btn btn-success" type="button" value="确认" onclick="queren()"/>
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
                                <tr>
                                    <th>头像</th>
                                    <th>昵称</th>
                                    <th>账号</th>
                                    <th>粉丝量</th>
                                    <th>价格</th>
                                    <th>操作</th>
                                </tr>
                                </tr>
                                </tbody>
                                <tbody id="orderNum">
                                <tr>
                                	<th>
                                		<img src="${pd.headImgURL }" style="width: 50px;height: 50px;">
                                	</th>
                                    <th>${pd.name }</th>
                                    <th>${pd.account }</th>
                                    <th>${pd.fansNum }</th>
                                    <th>${pd.price }</th>
                                    <th>
                                        <a href="javascript:void(0)" onclick="deltr(this)" title="${pd.WCfriends_id }">x</a>
                                    </th>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-chioce">
                        您共选择了
                        <span class="option">1</span>项资源
                        ,形式为:&nbsp;<span class="format">${pd.name1 }</span>，合计价格￥
                        <span class="price" id="countPrice">${pd.price }</span>
                    </div>
                </div>
            </div>
            <!--转发链接-->
            <div class="found-list link" style="display: block;">
                <div class="found-title">转发链接<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>
                <div class="found-content">
                    <input type="text" class="form-con1" placeholder="http://" name="zhuanfa_link" id="zhuanfa_link"/>
                </div>
            </div>
            <!--分享步骤-->
            <div class="found-list trans" style="display:block;">
                <div class="found-title">分享步骤<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <div class="form-time">
                    <textarea style="padding:3px;" cols="70" rows="10" name="steps" id="steps"></textarea>
                    <div class="number"><span>0</span>/1000</div>
                    <p class="form-p1">在微信公众号：账号昵称（微信号：weixinhao）的历史图文消息中找到文章</p>
                    <p class="form-p1">（标题：文章标题；链接：http:// ）并将该文章分享到朋友圈</p>
                </div>

            </div>
            <!--分享语-->
            <div class="found-list trans" style="display:block;">
                <div class="found-title">分享语<span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <div class="form-time">
                    <textarea style="padding:3px;" cols="70" rows="10" name="fenxiangyu" id="fenxiangyu"></textarea>
                    <div class="number"><span>0</span>/1000</div>
                    <p class="form-p1">此内容会直接发出，请勿填写无关信息</p>
                </div>
            </div>
            <!--直发内容-->
            <!--<div class="found-list cont" style="display: none;">
                <div class="found-title">直发内容 <span><img src="static/images/xinmeiti/star.png" alt=""/></span></div>

                <div class="form-time">
                    <textarea style="padding:3px;"  cols="70" rows="10"></textarea>
                    <div class="number"><span>0</span>/1000</div>
                    <p class="form-p1">请描述直发内容，让预约对象大致了解此次活动</p>
                </div>
            </div>-->
            <!--上传-->
            <!--<div class="found-list upload_file" style="display: none;">
                <div class="found-title">
                    直发配图
                </div>

                <div class="form-time">
                    <div class="file">
                        上传
                        <input type="file"/>
                    </div>
                    <p class="form-p1">您可以上传活动文档(txt、doc或docx)或活动图片(gif、jpg、png或jpeg格式的图片)，文件大小请控制在2M以下</p>
                </div>
            </div>-->
            
            <!--订单备注-->
            <div class="found-list">
                <div class="found-title">订单备注</div>
                <div class="form-time">
                    <textarea style="padding:3px;"  cols="70" rows="10" name="remarks" id="remarks"></textarea>
                    <p class="form-p1">选填，您可以进一步明确发布要求</p>
                </div>
            </div>
            <!--上传附件-->
            <div class="found-list upload">
                <div class="found-title">
                    正品证明
                </div>

                <div class="form-time">
                    <div class="file">
                        上传
                        <input type="file" name="proveURLfile" id="proveURL"/>
                    </div>
                    <p class="form-p1">您可以上传活动文档(txt、doc或docx)或活动图片(gif、jpg、png或jpeg格式的图片)，文件大小请控制在2M以下</p>
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
                    <button class="btn btn-warning" type="button" onclick="fabu()">发布</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
	function fabu() {
		$("#pyqorderform").submit();
	}
</script>
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
        $('#demo-3').fdatepicker({
            format: 'yyyy-mm-dd hh:ii',
            pickTime: true
        });

        function tt(dd){
            //alert(dd);
        }
        var GG = {
            "kk":function(mm){
                // alert(mm);
            }
        };

        $("#page").initPage(71,1,GG.kk);
    });
  //点击添加资源事件
    function tianjiaziyuan(){
    	var ids=$("#ids").val();//获取所有已选资源的ID字符串
    	$.ajax({
			type:'post',
			url:'api/xmtv1/getPyqOrderAddResourceList.do',
			dataTyoe:'json',
			cache: false,
			async:false,
			data:{
				"ids":ids
			},
			success:function(data){
				var str='';
				$.each(data.list,function(i,j){
					str+='<tr>';
					str+='<td>';
					str+='<input type="checkbox" value="'+j.WCfriends_id+'" name="checkboxs"/>';
					str+='<span>';
					str+=' <img src="'+j.headImgURL+'">';
					str+='</span>';
					str+='</td>';
					str+='<td>'+j.account+'</td>';
					str+='<td>'+j.name+'</td>';
					str+='<td>'+j.fansNum+'</td>';
					str+='<td>'+j.province+'/'+j.city+'</td>';
					str+='<td>'+j.price+'</td>';
					str+='</tr>';
				});
				$("#tianjiazy").html(str);
			}
		})
    }
  //点击X事件
  	function deltr(obj){
	   $(obj).parent().parent().remove();//移除tr标签
	   var ids='';
	   var arr=$("#ids").val().split(",");
	   for ( var i = 0; i < arr.length; i++) {
		   if(arr[i]!=$(obj).attr("title")){
			   if(ids==''){
				   ids+=arr[i];
			   }else{
				   ids+=","+arr[i];
			   }
		   }
	   }
	   $("#ids").val(ids);
  	}
  //点击确认
    function queren() {
		$(".modal").hide();
        $(".modal-backdrop").remove();
        $("body").css("overflow","scroll");
        
        var ids='';
        var inputs=$("#tianjiazy input[type=checkbox]:checked");
        $.each(inputs,function(){
        	if(ids==''){
				ids+=$(this).val();
			}else{
				ids+=','+$(this).val();
			}
        });
		if(ids!=''){
			$.ajax({
				type:'post',
				url:'api/xmtv1/getPyqresourcelist.do',
				dataTyoe:'json',
				cache: false,
				async:false,
				data:{
					"ids":ids
				},
				success:function(data){
					var str="";
					$.each(data.list,function(i,j){
						str+='<tr>';
						str+='<th>';
						str+='<img src="'+j.headImgURL+'" style="width: 40px;height: 40px;">';
						str+='</th>';
						str+='<th>'+j.name+' </th>';
						str+='<th>'+j.account+' </th>';
						str+='<th>'+j.fansNum+'</th>';
						str+='<th>'+j.price+'</th>';
						str+='<th>';
						str+='<a href="javascript:void(0)" onclick="deltr(this)" title="'+j.WCfriends_id+'">x</a>';
						str+='</th>';
						str+='</tr>';
					});
					$("#orderNum").append(str);
					//去除刚刚资源的选中状态
					$.each(inputs,function(){
                    	$(this).attr("checked", false);
                    });
					//把已选资源的id写会页面
					$("#ids").val($("#ids").val()+","+ids);
					//$("#countPrice").html(count.toFixed(2));
				},
			});
		}
	}
</script>

</body>
</html>
