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
    $(".list-module ul li").click(function(){
        $(this).addClass("select");
        $(this).siblings().removeClass("select");
    })
});