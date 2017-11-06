$(function(){
	var numReg=/^(?!0(\.0+)?$)([1-9][0-9]*|0)(\.[0-9]+)?$/;//只能为大于0的整数或小数
	var znumReg=/^[0-9]*[1-9][0-9]*$/;
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
    var ur=window.location.href;
    function jumpTop(){
    	$("#buttona").attr("href",ur+"#pyqform")
    };
//保存操作
    $("#buttona").click(function(){
    	//微信号
    	if($("#account").val()==''){
    		$("#account").tips({
				side : 2,
				msg : '请输入微信账号',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
    	//微信名称
    	if($("#name").val()==''){
    		$("#name").tips({
				side : 2,
				msg : '请输入微信名称',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
    	//微信头像
    	if($("#caozuoType").val()=='insert'){
    		if($("#headImgURL").val()=='' || !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#headImgURL").val()))){
        		$("#headImgURL").tips({
    				side : 2,
    				msg : '必填,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1.5
    			});
        		jumpTop();
    			return;
    		}
    	}else if($("#caozuoType").val()=='update'){
    		if($("#headImgURL").val()!='' && !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#headImgURL").val()))){
        		$("#headImgURL").tips({
    				side : 2,
    				msg : '请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time :1.5
    			});
        		jumpTop();
    			return;
    		}
    	}
    	
    	//粉丝人数
    	if($("#fansNum").val()==''){
    		$("#fansNum").tips({
				side : 2,
				msg : '请输入粉丝人数',
				bg : '#FF0080',
				time :1.5
			});
    		jumpTop();
			return;
    	}
    	if($("#fansNum").val()!='' && !znumReg.test($("#fansNum").val())){
    		$("#fansNum").tips({
				side : 2,
				msg : '请输入正确的数字',
				bg : '#FF0080',
				time :1.5
			});
    		jumpTop();
			return;
    	}
    	
    	//粉丝人数截图
    	if($("#caozuoType").val()=='insert'){
    		if($("#fansNumImgURL").val()=='' || !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#fansNumImgURL").val()))){
        		$("#fansNumImgURL").tips({
    				side : 2,
    				msg : '必填,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1.5
    			});
        		jumpTop();
    			return;
    		}
    	}else if($("#caozuoType").val()=='update'){
    		if($("#fansNumImgURL").val()!='' && !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#fansNumImgURL").val()))){
        		$("#fansNumImgURL").tips({
    				side : 2,
    				msg : '必填,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1.5
    			});
        		jumpTop();
    			return;
    		}
    	}
    	//个人信息截图
    	if($("#caozuoType").val()=='insert'){
    		if($("#informationImgURL").val()=='' || !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#informationImgURL").val()))){
        		$("#informationImgURL").tips({
    				side : 2,
    				msg : '必填,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1.5
    			});
        		jumpTop();
    			return;
    		}
    	}else if($("#caozuoType").val()=='update'){
    		if($("#informationImgURL").val()!='' && !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#informationImgURL").val()))){
        		$("#informationImgURL").tips({
    				side : 2,
    				msg : '必填,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1.5
    			});
        		jumpTop();
    			return;
    		}
    	}
    	if($("#province option:selected").val() == ''){
    		$("#province").tips({
				side : 2,
				msg : '请选择省份',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
    	if($("#city option:selected").val() == ''){
    		$("#city").tips({
				side : 2,
				msg : '请选择城市',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
    	//价格
    	if($("#price1").val() == ''){
    		$("#price1").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
		if($("#price1").val() != '' && !numReg.test($("#price1").val())){
			$("#price1").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1.5
			});
			jumpTop();
			return;
		}
    	
		//资源介绍
		if($("#introduce").val() == ''){
    		$("#introduce").tips({
				side : 2,
				msg : '请输介绍内容',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
		//所有条件满足后，提交form表单
		$("#pyqform").submit();
	});
//失焦的一些提示
    //微信号
    $("#account").blur(function(){
    	if($("#account").val()==''){
    		$("#account").tips({
				side : 2,
				msg : '请输入微信账号',
				bg : '#FF0080',
				time : 1.5
			});
    	}
    });
    //微信名称
    $("#name").blur(function(){
    	if($("#name").val()==''){
    		$("#name").tips({
				side : 2,
				msg : '请输入微信名称',
				bg : '#FF0080',
				time : 1.5
			});
    	}
    });
    //粉丝人数
    $("#fansNum").blur(function(){
    	if($("#fansNum").val()==''){
    		$("#fansNum").tips({
				side : 2,
				msg : '请输入粉丝人数',
				bg : '#FF0080',
				time :1.5
			});
    	}
    	if($("#fansNum").val()!='' && !znumReg.test($("#fansNum").val())){
    		$("#fansNum").tips({
				side : 2,
				msg : '请输入正确的数字',
				bg : '#FF0080',
				time :1.5
			});
    	}
   
    });
   
    	
    //价格
    $("#price1").blur(function() {
    	if($("#price1").val() == ''){
    		$("#price1").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1.5
			});
			return false;
    	}
		if($("#price1").val() != '' && !numReg.test($("#price1").val())){
			$("#price1").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1.5
			});
			return false;
		}
	}); 
    //资源介绍
    $("#introduce").blur(function(){
    	if($("#introduce").val() == ''){
    		$("#introduce").tips({
				side : 2,
				msg : '请输介绍内容',
				bg : '#FF0080',
				time : 1.5
			});
			return false;
    	}
    });
});
