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
   /* $(".form-con1").click(function(){
        $(".form-con1").attr("checked","checked");
        $(".upload_file").show();
        $(".form-con2").attr("checked",false);
        $(".upload_video").hide();
    });
    $(".form-con2").click(function(){
        $(".form-con2").attr("checked","checked");
        $(".form-con1").attr("checked",false);
        $(".upload_file").hide();
        $(".upload_video").show();
    });*/
    /*
    $(".form-control").change(function(){
        if($(this).val()==1||$(this).val()==2){
            $(".cont").show();
            $(".upload_file").show();
            $(".link").hide();
            $(".special").hide();
            $(".trans").hide();
        }else{
            $(".cont").hide();
            $(".link").show();
            $(".upload_file").hide();
            $(".special").show();
            $(".trans").show();

        }
    });
    */
    /*˵����*/
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
});