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
 /*//失焦的一些提示
    //平台
    $("#platformName").blur(function(){
    	if($("#platformName").val()==''){
    		$("#platformName").tips({
				side : 2,
				msg : '请输入微信账号',
				bg : '#FF0080',
				time : 1
			});
			$("#platformName").focus();
    	}
    });
    //昵称
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
    //房间ID
    $("#number").blur(function(){
    	if($("#number").val()==''){
    		$("#number").tips({
				side : 2,
				msg : '请输入粉丝人数',
				bg : '#FF0080',
				time : 1
			});
			$("#number").focus();
    	}
    	if($("#fansNum").val()!='' && !znumReg.test($("#fansNum").val())){
    		$("#fansNum").tips({
				side : 2,
				msg : '请输入正确的数字',
				bg : '#FF0080',
				time :1
			});
    		//jumpTop();
			return;
    	}
    });
    //头像
    $("#file1").blur(function() {
		if($("#file1").val() == ''){
			$("#file1").tips({
				side : 2,
				msg : '请上传头像',
				bg : '#FF0080',
				time : 1
			});
			$("#file1").focus();
		}
	});
  //地区
    if($("#province option:selected").val() == ''){
		$("#province").tips({
			side : 2,
			msg : '请选择省份',
			bg : '#FF0080',
			time : 1
		});
		//jumpTop();
		return;
	}
	if($("#city option:selected").val() == ''){
		$("#city").tips({
			side : 2,
			msg : '请选择城市',
			bg : '#FF0080',
			time : 1
		});
		//jumpTop();
		return;
	}
  //粉丝人数
    $("#fansnumber").blur(function() {
    	if($("#fansnumber").val() == ''){
    		$("#fansnumber").tips({
				side : 2,
				msg : '请填写你的粉丝数',
				bg : '#FF0080',
				time : 1
			});
			$("#fansnumber").focus();
			return false;
    	}
		if($("#fansnumber").val() != '' && !numReg.test($("#fansnumber").val())){
			$("#fansnumber").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
			$("#fansnumber").focus();
			return false;
		}
	});
	//粉丝人数截图
    $("#file2").blur(function() {
    	if($("#file2").val() == ''){
    		$("#file2").tips({
				side : 2,
				msg : '文件不能为空',
				bg : '#FF0080',
				time : 1
			});
			$("#file2").focus();
			return false;
    	}
	});
	//过往案例
    $("#videoURL").blur(function() {
    	if($("#videoURL").val() == ''){
    		$("#videoURL").tips({
				side : 2,
				msg : '请填写你的案例',
				bg : '#FF0080',
				time : 1
			});
			$("#videoURL").focus();
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
    });*/
    
    
  //保存操作
    $("#buttona").click(function(){
    	/*//平台
    	if($("#platformName").val()==''){
    		$("#platformName").tips({
				side : 2,
				msg : '请选择平台',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
    	}
    	//昵称
    	if($("#name").val()==''){
    		$("#name").tips({
				side : 2,
				msg : '请输入主播名称',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
    	}
    	//房间ID
    	$("#number").blur(function() {
        	if($("#number").val() == ''){
        		$("#number").tips({
    				side : 2,
    				msg : '请填写你的直播间ID',
    				bg : '#FF0080',
    				time : 1
    			});
    			$("#number").focus();
    			return false;
        	}
    		if($("#number").val() != '' && !numReg.test($("#number").val())){
    			$("#number").tips({
    				side : 2,
    				msg : '请输数字',
    				bg : '#FF0080',
    				time : 1
    			});
    			$("#number").focus();
    			return false;
    		}
    	});
    	
    	//头像
    	if($("#caozuoType").val()=='insert'){
    		if($("#file1").val()=='' || !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#file1").val()))){
        		$("#file1").tips({
    				side : 2,
    				msg : '必填,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1
    			});
        		//jumpTop();
    			return;
    		}
    	}else if($("#caozuoType").val()=='update'){
    		if($("#file1").val()!='' && !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#file1").val()))){
        		$("#file1").tips({
    				side : 2,
    				msg : '请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1
    			});
        		//jumpTop();
    			return;
    		}
    	}
    	
    	//地区
    	if($("#province option:selected").val() == ''){
    		$("#province").tips({
				side : 2,
				msg : '请选择省份',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
    	}
    	if($("#city option:selected").val() == ''){
    		$("#city").tips({
				side : 2,
				msg : '请选择城市',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
    	}
    	
    	//粉丝人数
    	if($("#fansnumber").val()==''){
    		$("#fansnumber").tips({
				side : 2,
				msg : '请输入粉丝人数',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
    	}
    	if($("#fansnumber").val()!='' && !znumReg.test($("#fansnumber").val())){
    		$("#fansnumber").tips({
				side : 2,
				msg : '请输入正确的数字',
				bg : '#FF0080',
				time :1
			});
    		//jumpTop();
			return;
    	}
    	//粉丝人数截图
    	if($("#caozuoType").val()=='insert'){
    		if($("#file2").val()=='' || !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#file2").val()))){
        		$("#file2").tips({
    				side : 2,
    				msg : '不可为空,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1
    			});
        		//jumpTop();
    			return;
    		}
    	}else if($("#caozuoType").val()=='update'){
    		if($("#file2").val()!='' && !((/\.(gif|jpg|jpeg|png|bmp)$/i).test($("#file2").val()))){
        		$("#file2").tips({
    				side : 2,
    				msg : '不可为空,请按要求选择文件(格式只能为gif、jpg、jpeg、png、bmp)',
    				bg : '#FF0080',
    				time : 1
    			});
        		//jumpTop();
    			return;
    		}
    	}
    	
    	//价格
    	if($("#price5").val() == ''){
    		$("#price5").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
    	}
		if($("#price5").val() != '' && !numReg.test($("#price5").val())){
			$("#price5").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
		}
		if($("#price6").val() == ''){
    		$("#price6").tips({
				side : 2,
				msg : '价格不能为空',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
    	}
		if($("#price6").val() != '' && !numReg.test($("#price6").val())){
			$("#price6").tips({
				side : 2,
				msg : '请输数字',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
			return;
		}
		
		//过往案例
		if($("#videoURL").val() == ''){
    		$("#videoURL").tips({
				side : 2,
				msg : '请输入你曾经做过的案例',
				bg : '#FF0080',
				time : 1
			});
    		//jumpTop();
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
    		//jumpTop();
			return;
    	}*/
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
		
		$("#zhiboForm").prepend('<input type="hidden" name="names" value="'+names+'"><input type="hidden" name="prices" value="'+prices+'">');
		//所有条件满足后，提交form表单
		$("#zhiboForm").submit();
	});
    
});
