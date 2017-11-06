$(function(){
    /*��������*/
    $(".nav_menu ul li a").click(function(){
        $(this).addClass("onx");
        $(this).parent().siblings().find("a").removeClass("onx");
        if($(this).attr("class")=="admin onx"){
            $(".Resources").show();
        }else{
            $(".Resources").hide();
        }
    });
    /*ѡ����վ*/
    $(".website .col-xs-3 input").click(function(){
        $(".first input:first-child").click(function(){
            $(".modal").hide();
            $(".modal-backdrop").remove();
            $("#online").val($(this).parent().prev().find(".website .col-xs-3 input:checked").next("label").text())
            $("body").css("overflow","scroll");
        })
    });
    /*ѡ��Ƶ��*/
    $(".channel .col-xs-3 input").click(function(){
        $(".two input:first-child").click(function(){
            $(".modal").hide();
            $(".modal-backdrop").remove();
            $("#sel_channel").val($(this).parent().prev().find(".channel .col-xs-3 input:checked").next("label").text())
            $("body").css("overflow","scroll");
        })
    });

  //失焦的一些提示
    //选择网站
    $("#online").blur(function(){
    	if($("#online").val()==''){
    		$("#online").tips({
				side : 2,
				msg : '请选择网站信息',
				bg : '#FF0080',
				time : 1
			});
			$("#online").focus();
    	}
    });
    //选择频道
    $("#sel_channel").blur(function(){
    	if($("#sel_channel").val()==''){
    		$("#sel_channel").tips({
				side : 2,
				msg : '请选择网站频道',
				bg : '#FF0080',
				time : 1
			});
			$("#sel_channel").focus();
    	}
    });
    //资源名称
    $("#resourceName").blur(function(){
    	if($("#resourceName").val()==''){
    		$("#resourceName").tips({
				side : 2,
				msg : '请输入资源名称',
				bg : '#FF0080',
				time : 1
			});
			$("#resourceName").focus();
    	}
    });
    //上传头像
   /* $("#file").blur(function() {
		if($("#file").val() == ''){
			$("#file").tips({
				side : 2,
				msg : '请输选择图片文件',
				bg : '#FF0080',
				time : 1
			});
			$("#price1").focus();
		}
	});*/
    //入口级别
    $("#media_level").blur(function(){
    	if($("#media_level").val()==''){
    		$("#media_level").tips({
				side : 2,
				msg : '请选择入口级别',
				bg : '#FF0080',
				time : 1
			});
			$("#media_level").focus();
    	}
    });
  //入口链接
    	$("#linkeHttp").blur(function(){
        	if($("#linkeHttp").val()==''){
        		$("#linkeHttp").tips({
    				side : 2,
    				msg : '请选择入口级别',
    				bg : '#FF0080',
    				time : 1
    			});
    			$("#linkeHttp").focus();
        	}
    	});
	//入口形式
    	$("#rukouType").blur(function(){
        	if($("#rukouType").val()==''){
        		$("#rukouType").tips({
    				side : 2,
    				msg : '请选择入口形式',
    				bg : '#FF0080',
    				time : 1
    			});
    			$("#rukouType").focus();
        	}
    	});
  //正文带链接
    	$("#textLink_type").blur(function(){
        	if($("#textLink_type").val()==''){
        		$("#textLink_type").tips({
    				side : 2,
    				msg : '请正文带链接形式',
    				bg : '#FF0080',
    				time : 1
    			});
    			$("#textLink_type").focus();
        	}
    	});
		
	//百度新闻源
    $("#baidu_resource").blur(function() {
    	if($("#baidu_resource").val() == ''){
    		$("#baidu_resource").tips({
				side : 2,
				msg : '请选择是否为百度资源',
				bg : '#FF0080',
				time : 1
			});
			$("#baidu_resource").focus();
    	}
	});
  //报价
    $("#price").blur(function() {
    	if($("#price").val() == ''){
    		$("#price").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#price").focus();
    	}
		if($("#price").val() != '' && !numReg.test($("#price").val())){
			$("#price").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price").focus();
		}
	});
	//资源介绍
    $("#media_intro").blur(function() {
    	if($("#media_intro").val() == ''){
    		$("#media_intro").tips({
				side : 2,
				msg : '请输入介绍内容',
				bg : '#FF0080',
				time : 1
			});
			$("#media_intro").focus();
    	}
	});
    //资源截图
    $("#linkeHttpFile").blur(function(){
    	if($("#linkeHttpFile").val() == ''){
    		$("#linkeHttpFile").tips({
				side : 2,
				msg : '文件不可为空',
				bg : '#FF0080',
				time : 1
			});
			$("#linkeHttpFile").focus();
    	}
    });
    
    //保存设置
    $("#buttona").click(function(){

    	//选择网站
    	if($("#online").val()==''){
    		$("#online").tips({
				side : 2,
				msg : '请选择网站',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	//选择频道
    	if($("#sel_channel").val()==''){
    		$("#sel_channel").tips({
				side : 2,
				msg : '请选择频道',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	//资源名称
    	if($("#resourceName").val()==''){
    		$("#resourceName").tips({
				side : 2,
				msg : '资源名称不可为空',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	
    	//入口级别
    	if($("#resourceName").val()==''){
    		$("#resourceName").tips({
				side : 2,
				msg : '请选择入口级别',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	//入口链接
    	if($("#linkeHttp").val() == ''){
    		$("#linkeHttp").tips({
				side : 2,
				msg : '请输入入口链接',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	
    	//入口形式
    	if($("#rukouType option:selected").val() == ''){
    		$("#rukouType").tips({
				side : 2,
				msg : '请选择入口形式',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	//正文带链接情况
    	if($("#textLink_type").val() == ''){
    		$("#textLink_type").tips({
				side : 2,
				msg : '请选择正文带链接类型',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		
		//是否百度新闻源
		if($("#baidu_resource").val() == ''){
    		$("#baidu_resource").tips({
				side : 2,
				msg : '请选择是否为百度因为源',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		//价格
		if($("#price").val() == ''){
    		$("#price").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		if($("#price").val() != '' && !numReg.test($("#price").val())){
			$("#price").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
		}
		//资源介绍
		if($("#media_intro").val() == ''){
    		$("#media_intro").tips({
				side : 2,
				msg : '请输介绍内容',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		//资源截图
		if($("#caozuoType").val()=='insert'){
    		if($("#linkeHttpFile").val()=='' || !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#linkeHttpFile").val()))){
        		$("#linkeHttpFile").tips({
    				side : 2,
    				msg : '不可为空,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1
    			});
        		jumpTop();
    			return;
    		}
    	}else if($("#caozuoType").val()=='update'){
    		if($("#linkeHttpFile").val()!='' && !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#linkeHttpFile").val()))){
        		$("#linkeHttpFile").tips({
    				side : 2,
    				msg : '不可为空,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1
    			});
        		jumpTop();
    			return;
    		}
    	}
		
		var names='';
		var prices='';
		
		//把所有的类型和价格拼接为字符串
		if($("#price1").val()!=''){
			names+=$("#name1").val()+",";
			prices+=$("#price1").val()+",";
		}else{
			names+=$("#name1").val()+",";
			prices+="-1,";
		}
		if($("#price2").val()!=''){
			names+=$("#name2").val()+",";
			prices+=$("#price2").val()+",";
		}else {
			names+=$("#name2").val()+",";
			prices+="-1,";
		}
		if($("#price3").val()!=''){
			names+=$("#name3").val()+",";
			prices+=$("#price3").val()+",";
		}else{
			names+=$("#name3").val()+",";
			prices+="-1,";
		}
		if($("#price4").val()!=''){
			names+=$("#name4").val()+",";
			prices+=$("#price4").val()+",";
		}else{
			names+=$("#name4").val()+",";
			prices+="-1,";
		}
		names+=$("#name5").val()+",";
		prices+=$("#price5").val()+",";
		
		names+=$("#name6").val()+",";
		prices+=$("#price6").val()+",";
		
		names+=$("#name7").val()+",";
		prices+=$("#price7").val()+",";
		
		names+=$("#name8").val();
		prices+=$("#price8").val();
		
		$("#xinwenForm").prepend('<input type="hidden" name="names" value="'+names+'"><input type="hidden" name="prices" value="'+prices+'">');
		//所有条件满足后，提交form表单
		$("#xinwenForm").submit();
	
    })
});
