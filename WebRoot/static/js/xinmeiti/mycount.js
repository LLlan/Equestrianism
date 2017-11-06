$(function(){
	/*个人中心*/
    $(".nav_menu ul li a").click(function(){
        $(this).addClass("onx");
        $(this).parent().siblings().find("a").removeClass("onx");
    });
    /*派单列表*/
    $(".user-center .header-tab a:first-child").click(function(){
        $(this).addClass("active");
        $(".tablex").show();
        $(".user-center .header-tab a:last-child").removeClass("active");
        $(".tabley").hide();
    });
    $(".user-center .header-tab a:last-child").click(function(){
        $(this).addClass("active");
        $(".tablex").hide();
        $(".user-center .header-tab a:first-child").removeClass("active");
        $(".tabley").show();
    })
});
