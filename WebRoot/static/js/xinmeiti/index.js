/*΢����΢������Ȧ������*/
$(function () {
    $(".column-top-left ul li:eq(0)").hover(function () {
        $(".column-a1").show();
        $(".column-a2").hide();
        $(".column-a3").hide();
        $(".column-a4").hide();
        $(".column-a5").hide();
        $(".column-a6").hide();
        $(this).addClass("column-00").addClass("onx");
        $(".column-top-left ul li:eq(1)").removeClass("onx");
        $(".column-top-left ul li:eq(2)").removeClass("onx");
        $(".column-top-left ul li:eq(3)").removeClass("onx");
        $(".column-top-left ul li:eq(4)").removeClass("onx");
        $(".column-top-left ul li:eq(5)").removeClass("onx");
    });
    $(".column-top-left ul li:eq(1)").hover(function () {
        $(".column-a1").hide();
        $(".column-a2").show();
        $(".column-a3").hide();
        $(".column-a4").hide();
        $(".column-a5").hide();
        $(".column-a6").hide();
        $(this).addClass("column-11").addClass("onx");
        $(".column-top-left ul li:eq(0)").removeClass("onx");
        $(".column-top-left ul li:eq(2)").removeClass("onx");
        $(".column-top-left ul li:eq(3)").removeClass("onx");
        $(".column-top-left ul li:eq(4)").removeClass("onx");
        $(".column-top-left ul li:eq(5)").removeClass("onx");
    })
    $(".column-top-left ul li:eq(2)").hover(function () {
        $(".column-a1").hide();
        $(".column-a2").hide();
        $(".column-a3").show();
        $(".column-a4").hide();
        $(".column-a5").hide();
        $(".column-a6").hide();
        $(this).addClass("column-22").addClass("onx");
        $(".column-top-left ul li:eq(0)").removeClass("onx");
        $(".column-top-left ul li:eq(1)").removeClass("onx");
        $(".column-top-left ul li:eq(3)").removeClass("onx");
        $(".column-top-left ul li:eq(4)").removeClass("onx");
        $(".column-top-left ul li:eq(5)").removeClass("onx");
    });
    $(".column-top-left ul li:eq(3)").hover(function () {
        $(".column-a1").hide();
        $(".column-a2").hide();
        $(".column-a3").hide();
        $(".column-a4").show();
        $(".column-a5").hide();
        $(".column-a6").hide();
        $(this).addClass("column-33").addClass("onx");
        $(".column-top-left ul li:eq(0)").removeClass("onx");
        $(".column-top-left ul li:eq(1)").removeClass("onx");
        $(".column-top-left ul li:eq(2)").removeClass("onx");
        $(".column-top-left ul li:eq(4)").removeClass("onx");
        $(".column-top-left ul li:eq(5)").removeClass("onx");
    });
    $(".column-top-left ul li:eq(4)").hover(function () {
        $(".column-a1").hide();
        $(".column-a2").hide();
        $(".column-a3").hide();
        $(".column-a4").hide();
        $(".column-a5").show();
        $(".column-a6").hide();
        $(this).addClass("column-44").addClass("onx");
        $(".column-top-left ul li:eq(0)").removeClass("onx");
        $(".column-top-left ul li:eq(1)").removeClass("onx");
        $(".column-top-left ul li:eq(2)").removeClass("onx");
        $(".column-top-left ul li:eq(3)").removeClass("onx");
        $(".column-top-left ul li:eq(5)").removeClass("onx");
    });
    $(".column-top-left ul li:eq(5)").hover(function () {
        $(".column-a1").hide();
        $(".column-a2").hide();
        $(".column-a3").hide();
        $(".column-a4").hide();
        $(".column-a5").hide();
        $(".column-a6").show();
        $(this).addClass("column-55").addClass("onx");
        $(".column-top-left ul li:eq(0)").removeClass("onx");
        $(".column-top-left ul li:eq(1)").removeClass("onx");
        $(".column-top-left ul li:eq(2)").removeClass("onx");
        $(".column-top-left ul li:eq(3)").removeClass("onx");
        $(".column-top-left ul li:eq(4)").removeClass("onx");
    });

});
 $(function(){
     /*点击改变登录人的身份（广告主还是媒介主）*/
     $(".lot1").click(function(){
         $(".login_pro").show();
         $(".lot1").removeClass("h_login_s");
         $(".lot2").addClass("h_login_s");
         $(".login_pto").hide();
         $("#userType").val("1");
     });
     $(".lot2").click(function(){
         $(".login_pro").hide();
         $(".lot2").removeClass("h_login_s");
         $(".lot1").addClass("h_login_s");
         $(".login_pto").show();
         $("#userType").val("2");
     })
 })