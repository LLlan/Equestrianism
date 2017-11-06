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
    	$("#buttona").attr("href",ur+"#gzhform")
    };
//保存操作
    $("#buttona").click(function(){
    	//微信号
    	if($("#account").val()==''){
    		$("#account").tips({
				side : 2,
				msg : '请输入微信账号',
				bg : '#FF0080',
				time : 1
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
				time : 1
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
    				time : 1
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
    				time : 1
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
				time : 1
			});
    		jumpTop();
			return;
    	}
    	if($("#fansNum").val()!='' && !znumReg.test($("#fansNum").val())){
    		$("#fansNum").tips({
				side : 2,
				msg : '请输入正确的数字',
				bg : '#FF0080',
				time :1
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
    				time : 1
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
    				time : 1
    			});
        		jumpTop();
    			return;
    		}
    	}
    	
    	//地区$("#status option:selected").val()
    	if($("#province option:selected").val() == ''){
    		$("#province").tips({
				side : 2,
				msg : '请选择省份',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	if($("#city option:selected").val() == ''){
    		$("#city").tips({
				side : 2,
				msg : '请选择城市',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
    	//多图文第二条
    	if($("#price5").val() == ''){
    		$("#price5").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		if($("#price5").val() != '' && !numReg.test($("#price5").val())){
			$("#price5").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
		}
		if($("#price6").val() == ''){
    		$("#price6").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		if($("#price6").val() != '' && !numReg.test($("#price6").val())){
			$("#price6").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
		}
		
		//多图文第3~N条
		if($("#price7").val() == ''){
    		$("#price7").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		if($("#price7").val() != '' && !numReg.test($("#price7").val())){
			$("#price7").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
		}
		if($("#price8").val() == ''){
    		$("#price8").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
    		jumpTop();
			return;
    	}
		if($("#price8").val() != '' && !numReg.test($("#price8").val())){
			$("#price8").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
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
				time : 1
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
		
		$("#gzhform").prepend('<input type="hidden" name="names" value="'+names+'"><input type="hidden" name="prices" value="'+prices+'">');
		//所有条件满足后，提交form表单
		$("#gzhform").submit();
	});
//失焦的一些提示
    //微信号
    $("#account").blur(function(){
    	if($("#account").val()==''){
    		$("#account").tips({
				side : 2,
				msg : '请输入微信账号',
				bg : '#FF0080',
				time : 1
			});
			$("#account").focus();
    	}
    });
    //微信名称
    $("#name").blur(function(){
    	if($("#name").val()==''){
    		$("#name").tips({
				side : 2,
				msg : '请输入微信名称',
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
				time :1
			});
    		jumpTop();
			return;
    	}
    });
    //单图文硬广价格
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
    //单图文软广价格
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
  //多图文第一条硬广
    $("#price3").blur(function() {
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
	//多图文第一条软广
    $("#price4").blur(function() {
		if($("#price4").val() != '' && !numReg.test($("#price4").val())){
			$("#price1").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price4").focus();
			return false;
		}
	});
  //多图文第2条硬广
    $("#price5").blur(function() {
    	if($("#price5").val() == ''){
    		$("#price5").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#price5").focus();
			return false;
    	}
		if($("#price5").val() != '' && !numReg.test($("#price5").val())){
			$("#price5").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price5").focus();
			return false;
		}
	});
	//多图文第2条软广
    $("#price6").blur(function() {
    	if($("#price6").val() == ''){
    		$("#price6").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#price6").focus();
			return false;
    	}
		if($("#price6").val() != '' && !numReg.test($("#price6").val())){
			$("#price6").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price6").focus();
			return false;
		}
	});
  //多图文第3~N条硬广
    $("#price7").blur(function() {
    	if($("#price7").val() == ''){
    		$("#price7").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#price7").focus();
			return false;
    	}
		if($("#price7").val() != '' && !numReg.test($("#price7").val())){
			$("#price7").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price7").focus();
			return false;
		}
	});
	//多图文第3~N条软广
    $("#price8").blur(function() {
    	if($("#price8").val() == ''){
    		$("#price8").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#price8").focus();
			return false;
    	}
		if($("#price8").val() != '' && !numReg.test($("#price8").val())){
			$("#price8").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#price8").focus();
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


