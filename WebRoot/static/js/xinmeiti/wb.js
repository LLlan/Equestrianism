$(function(){
    $(".sidbar .all").mouseover(function(){
        $(".index_sidbar").show();
    }).mouseout(function(){
        $(".index_sidbar").hide()
    });
    $(".index_sidbar").mouseover(function(){
        $(".index_sidbar").show();
    }).mouseout(function(){
        $(".index_sidbar").hide()
    });
    $("#select1 dd").live("click",function () {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectA").remove();
        } else {
            var copyThisA = $(this).clone();
            if ($("#selectA").length > 0) {
                $("#selectA a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisA.attr("id", "selectA"));
            }
        }
        if($(this).hasClass("selected")){
			shaixuan();
		}
    });

    $("#select2 dd").live("click",function () {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectB").remove();
        } else {
            var copyThisB = $(this).clone();
            if ($("#selectB").length > 0) {
                $("#selectB a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisB.attr("id", "selectB"));
            }
        }
        if($(this).hasClass("selected")){
			shaixuan();
		}
    });

    $("#select3 dd").live("click",function () {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectC").remove();
        } else {
            var copyThisC = $(this).clone();
            if ($("#selectC").length > 0) {
                $("#selectC a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisC.attr("id", "selectC"));
            }
        }
        if($(this).hasClass("selected")){
			shaixuan();
		}
    });
    $("#select4 dd").live("click",function () {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectD").remove();
        } else {
            var copyThisD = $(this).clone();
            if ($("#selectD").length > 0) {
                $("#selectD a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisD.attr("id", "selectD"));
            }
        }
    });
    $("#select5 dd").live("click",function () {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectE").remove();
        } else {
            var copyThisE = $(this).clone();
            if ($("#selectE").length > 0) {
                $("#selectE a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisE.attr("id", "selectE"));
            }
        }
    });
    $("#selectA").live("click", function () {
        $(this).remove();
        $("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
        shaixuan();
    });

    $("#selectB").live("click", function () {
        $(this).remove();
        $("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
        shaixuan();
    });

    $("#selectC").live("click", function () {
        $(this).remove();
        $("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
        shaixuan();
    });
    $("#selectD").live("click", function () {
        $(this).remove();
        $("#select4 .select-all").addClass("selected").siblings().removeClass("selected");
    });
    $(".select dd").live("click", function () {
        if ($(".select-result dd").length > 1) {
            $(".select-no").hide();
        } else {
            $(".select-no").show();
        }
    });
  //省市选择事件
    $(".pcbutton").live("click",function(){
    	var province=$("#province option:selected").val();
    	var city=$("#city option:selected").val();
    	if(city!=''){
    		 if ($("#selectC").length > 0) {
                 $("#selectC a").html(city);
             } else {
                 $(".select-result dl").append('<dd class="selected" id="selectC"><a href="javascript:void(0)">'+city+'</a></dd>');
             }
    		 
    		 if ($(".select-result dd").length > 1) {
                 $(".select-no").hide();
             } else {
                 $(".select-no").show();
             }
    		 shaixuan();
    	}else{
    		if(province!=''){
    			
    			if ($("#selectC").length > 0) {
                    $("#selectC a").html(province);
                } else {
                    $(".select-result dl").append('<dd class="selected" id="selectC"><a href="javascript:void(0)">'+province+'</a></dd>');
                }

    			if ($(".select-result dd").length > 1) {
    	            $(".select-no").hide();
    	        } else {
    	            $(".select-no").show();
    	        }
    			shaixuan();
    		}
    	}
    })
});
//筛选执行的方法
function shaixuan() {
	var selectA='';
	var selectB='';
	var selectC='';
	if($("#selectA a").html()!='undefined'){
		selectA=$("#selectA a").html();
	}/*else{
		selectA="0";
	}*/
	if($("#selectB a").html()!='undefined'){
		selectB=$("#selectB a").html();
	}/*else{
		selectB="0";
	}*/
	if($("#selectC a").html()!='undefined'){
		selectC=$("#selectC a").html();
	}/*else{
		selectC="0";
	}*/
	$.ajax({
		type:"post",
		url:"api/xmtv1/wbShaiXuan.do",
		data:{
			selectA:selectA,
			selectB:selectB,
			selectC:selectC
		},
		dataType:"json",
		//cache: false,
		success:function(data){
			//alert(data.list);
			//获取项目名
			var pathName=window.document.location.pathname;
			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

			var str='';
			$.each(data.list,function(i,j){
				if(i<=9){
					str+="<li class='reso-li' style='margin-top: 0;margin-right: 5px;' onclick='jumpTodetail(\""+j.microBlog_id+"\");'>";
					str+='<a href="javascript:void(0)">';
					str+='<div class="resources-t">';
					str+='<img src="'+projectName+'/'+j.headImgURL+'" alt=""/>';
					str+='</div>';
					str+='<div class="newPersonBox">';
					str+='<a href="javascript:void(0)">';
					str+='<div style="font-size: 14px;color: #000;line-height: 30px;text-indent: 8px;">'+j.name+'</div>';
					str+='</a>';
					str+='<a href="javascript:void(0)">';
					str+='<div class="info">'+j.introduce+'</div>';
					str+='</a>';
					str+='<div class="fFans">';
					str+='<i>粉丝：'+j.fansNum+'</i></div>';
					str+='</div>';
					str+='<div class="price">';
					str+='<a href="javascript:void(0)" target="_blank">查看价格</a>';
					str+='</div>';
					str+='</a>';
					str+='</li>';
				};
			});
			$("#sourceUl").html(str);
		}
	});
};