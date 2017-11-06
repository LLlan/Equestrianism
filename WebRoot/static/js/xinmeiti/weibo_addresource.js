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
    	$("#buttona").attr("href",ur+"#wbform")
    };
//保存操作
    $("#buttona").click(function(){
    	//微博地址
    	if($("#microBlogHttp").val()==''){
    		$("#microBlogHttp").tips({
				side : 2,
				msg : '请输入微博地址',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
    	//微博昵称
    	if($("#name").val()==''){
    		$("#name").tips({
				side : 2,
				msg : '请输入微博昵称',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
    	//微博头像
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
    				time : 1.5
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
				time : 1.5
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
    
    	//地区$("#status option:selected").val()
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
    	//软广直发
    	if($("#price3").val() == ''){
    		$("#price3").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
		if($("#price3").val() != '' && !numReg.test($("#price3").val())){
			$("#price3").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1.5
			});
			jumpTop();
			return;
		}
		//软广转发
		if($("#price4").val() == ''){
    		$("#price4").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1.5
			});
    		jumpTop();
			return;
    	}
		if($("#price4").val() != '' && !numReg.test($("#price4").val())){
			$("#price4").tips({
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
		
		names+=$("#name3").val()+",";
		prices+=$("#price3").val()+",";
		
		names+=$("#name4").val();
		prices+=$("#price4").val();

		$("#wbform").prepend('<input type="hidden" name="names" value="'+names+'"><input type="hidden" name="prices" value="'+prices+'">');
		//所有条件满足后，提交form表单
		$("#wbform").submit();
	});
//失焦的一些提示
    //微博地址
    $("#microBlogHttp").blur(function(){
    	if($("#microBlogHttp").val()==''){
    		$("#microBlogHttp").tips({
				side : 2,
				msg : '请输入微信地址',
				bg : '#FF0080',
				time : 1
			});
			$("#microBlogHttp").focus();
    	}
    });
    //微博昵称
    $("#name").blur(function(){
    	if($("#name").val()==''){
    		$("#name").tips({
				side : 2,
				msg : '请输入微博昵称',
				bg : '#FF0080',
				time : 1
			});
			$("#name").focus();
    	}
    });
    //粉丝人数
    $("#fansNum").blur(function(){
    	if($("#fansNum").val()==''){
    		$("#fansNum").tips({
				side : 2,
				msg : '请输入粉丝人数',
				bg : '#FF0080',
				time : 1
			});
			$("#fansNum").focus();
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
    });
    //硬广直发价格
    $("#price1").blur(function() {
		if($("#price1").val() != '' && !numReg.test($("#price1").val())){
			$("#price1").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price1").focus();
		}
	});
    //硬广转发价格
    $("#price2").blur(function() {
		if($("#price2").val() != '' && !numReg.test($("#price2").val())){
			$("#price2").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price2").focus();
		}
	});
  //软广直发
    $("#price3").blur(function() {
    	if($("#price3").val() == ''){
    		$("#price3").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#price3").focus();
			return false;
    	}
		if($("#price3").val() != '' && !numReg.test($("#price3").val())){
			$("#price3").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price3").focus();
			return false;
		}
	});
	//软广转发
    $("#price4").blur(function() {
    	if($("#price4").val() == ''){
    		$("#price4").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#price4").focus();
			return false;
    	}
		if($("#price4").val() != '' && !numReg.test($("#price4").val())){
			$("#price4").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price4").focus();
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
				time : 1
			});
			$("#introduce").focus();
			return false;
    	}
    });
});
