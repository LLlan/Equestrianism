$(function(){
    /*��������*/
    $(".nav_menu ul li a").click(function(){
        $(this).addClass("onx");
        $(this).parent().siblings().find("a").removeClass("onx");
        /*if($(this).attr("class")=="admin onx"||$(this).attr("class")=="f2 onx"){
            $(".Resources").show();
        }else{
            $(".Resources").hide();
        }*/
    });
    /*�ɵ��б�*/
    $(".header_tab_rt span").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
       /* $(this).css({"background":"#fdb14e","color":"#fff"});
        $(this).siblings("span").css({"background":"#FCFBFA","color":"#666"});*/
    })
});
