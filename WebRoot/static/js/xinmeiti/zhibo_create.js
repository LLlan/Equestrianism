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
    $(".form-con1").click(function(){
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
    });
    /*$("#option1").click(function(){
        $(".special").show();
        $(".telecast").hide();
    });
    $("#option2").click(function(){
        console.log("1111")
        $(".special").hide();
        $(".telecast").show();
    });*/
    $(".form-control").change(function(){
        console.log($(this).val())
        if($(this).val()==1){
            $(".special").show();
            $(".telecast").hide();
        }else{
            $(".special").hide();
            $(".telecast").show();
        }
    })
});