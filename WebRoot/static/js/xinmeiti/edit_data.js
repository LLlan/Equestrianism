$(function(){
	/*个人中心*/
    /*$(".nav_menu ul li a").click(function(){
        $(this).addClass("onx");
        $(this).parent().siblings().find("a").removeClass("onx");
        if($(this).attr("class")=="admin onx"){
            $(".Resources").show();
        }else{
            $(".Resources").hide();
        }
        if($(this).attr("class")=="f4 onx"){
            $(".maintop").show();
        }else{
            $(".maintop").hide();
        }
    });*/
    /*资料编辑*/
    $(".line-wrap  .header-tab a:nth-child(1)").click(function(){
        $(this).addClass("active");
        $(".wrap1").show();
        $(".wrap2").hide();
        $(".line-wrap .header-tab a:nth-child(2)").removeClass("active");
    });
    $(".line-wrap  .header-tab a:nth-child(2)").click(function(){
        $(this).addClass("active");
        $(".wrap1").hide();
        $(".wrap2").show();
        $(".line-wrap .header-tab a:nth-child(1)").removeClass("active");
    });
});
