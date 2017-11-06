$(function(){
    /*个人中心*/
    $(".nav_menu ul li a").hover(function(){
        $(this).addClass("onx");
        $(this).parent().siblings().find("a").removeClass("onx");
    });
    /*充值*/
    $(".line-wrap  .header-tab a:nth-child(1)").click(function(){
        $(this).addClass("active");
        $(".wrap1").show();
        $(".wrap2").hide();
        $(".wrap3").hide();
        $(".line-wrap .header-tab a:nth-child(2)").removeClass("active");
        $(".line-wrap .header-tab a:nth-child(3)").removeClass("active");
    });
    $(".line-wrap  .header-tab a:nth-child(2)").click(function(){
        $(this).addClass("active");
        $(".wrap1").hide();
        $(".wrap2").show();
        $(".wrap3").hide();
        $(".line-wrap .header-tab a:nth-child(1)").removeClass("active");
        $(".line-wrap .header-tab a:nth-child(3)").removeClass("active");
    });
    $(".line-wrap  .header-tab a:nth-child(3)").click(function(){
        $(this).addClass("active");
        $(".wrap1").hide();
        $(".wrap2").hide();
        $(".wrap3").show();
        $(".line-wrap .header-tab a:nth-child(1)").removeClass("active");
        $(".line-wrap .header-tab a:nth-child(2)").removeClass("active");
    });
});

