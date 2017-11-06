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
    $(".form-control").change(function(){
        if($(this).val()==1){
            $(".format").html("单图文硬广");
        }else if($(this).val()==2){
            $(".format").html("单图文软广");
        }else if($(this).val()==3){
            $(".format").html("多图文第一条硬广");
        }else if($(this).val()==4){
            $(".format").html("多图文第一条软广");
        }else if($(this).val()==5){
            $(".format").html("多图文第二条硬广");
        }else if($(this).val()==6){
            $(".format").html("多图文第二条软广");
        }else if($(this).val()==7){
            $(".format").html("多图文第3~N条硬广");
        }else{
            $(".format").html("多图文第3~N条软广");
        }
    });
    /*说明书*/
    /*$(".found-list .image").mouseover(function(){
     $(".found-list .image .info").fadeIn()
     }).mouseout(function(){
     $(".found-list .image .info").fadeOut()
     });*/
    $(".found-list .image").hover(function(){
        $(".found-list .image .info").fadeIn();
    },function(){
        $(".found-list .image .info").fadeOut();
    });
    $(".form-p2 .form-img").hover(function(){
        $(".form-p2  .info1").fadeIn();
    },function(){
        $(".form-p2  .info1").fadeOut();
    });
    //订单名称
    $("#name").blur(function() {
		if($("#name").val()==''){
			$("#name").tips({
				side : 2,
				msg : '订单名称不能为空',
				bg : '#FF0080',
				time : 1
			});
			return;
		}
	});
    //开始时间
   
});