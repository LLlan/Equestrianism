$(function(){
    /*个人中心*/
    $(".nav_menu ul li a").click(function(){
        $(this).addClass("onx");
        $(this).parent().siblings().find("a").removeClass("onx");
        if($(this).attr("class")=="admin onx"){
            $(".Resources").show();
        }else{
            $(".Resources").hide();
        }
    });
    /*充值*/
    $(".form-control").change(function(){
        if($(this).val()==1){
            $(".table tr:eq(1)").show();
            $(".table tr:eq(2)").show();

            $(".table tr:eq(3)").hide();
            $(".table tr:eq(4)").hide();
            $(".table tr:eq(5)").hide();
            $(".table tr:eq(6)").hide();
        }else{
            $(".table tr:eq(1)").hide();
            $(".table tr:eq(2)").hide();
            $(".table tr:eq(3)").show();
            $(".table tr:eq(4)").show();
            $(".table tr:eq(5)").show();
            $(".table tr:eq(6)").show();
        }
    })
});
