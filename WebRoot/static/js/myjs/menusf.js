$(function() {
	menusf();
	if(typeof($.cookie('menusf'))=="undefined"){
		//展开状态
		$("#sidebar").attr("class","");
	}else{
		$("#sidebar").attr("class","menu-min");
	}
});

//保存缩放菜单状态
function menusf(){
	if(document.getElementsByName('menusf')[0].checked){
		$.cookie('menusf', '', { expires: -1 });
		//这是缩放状态
		$("#sidebar").attr("class","menu-min");
	}else{
		//展开
		$.cookie('menusf', 'ok');
		$("#sidebar").attr("class","");
	}
}
