/**
 * Created by zxm on 2017/3/31.
 */
$.fn.extend({
    "initPage":function(listCount,currentPage,fun){
        var maxshowpageitem = $(this).attr("maxshowpageitem");
        if(maxshowpageitem!=null&&maxshowpageitem>0&&maxshowpageitem!=""){
            page.maxshowpageitem = maxshowpageitem;
        }
        var pagelistcount = $(this).attr("pagelistcount");
        if(pagelistcount!=null&&pagelistcount>0&&pagelistcount!=""){
            page.pagelistcount = pagelistcount;
        }

        var pageId = $(this).attr("id");
        //给pageId赋值
        page.pageId=pageId;
        if(listCount<0){
            listCount = 0;
        }
        if(currentPage<=0){
            currentPage=1;
        }
        page.setPageListCount(listCount,currentPage,fun);

    }
});
var  page = {
    "pageId":"",
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":10,//每一页显示的内容条数
    /**
     * 初始化分页界面
     * @param listCount 列表总量
     * pageCount是分页数
     */
    "initWithUl":function(listCount,currentPage){
        var pageCount = 1;
        if(listCount>=0){
        	//取余数，如果总条数%每页显示的条数>0,则在原来基础上+1，否则取相除的结果
            var pageCount = listCount%page.pagelistcount>0?parseInt(listCount/page.pagelistcount)+1:parseInt(listCount/page.pagelistcount);
        }
        var appendStr = page.getPageListModel(pageCount,currentPage);
        $("#"+page.pageId).html(appendStr);
    },
    /**
     * 设置列表总量和当前页码
     * @param listCount 列表总量
     * @param currentPage 当前页码
     */
    "setPageListCount":function(listCount,currentPage,fun){
        listCount = parseInt(listCount);
        currentPage = parseInt(currentPage);
        page.initWithUl(listCount,currentPage);
        page.initPageEvent(listCount,fun);
        fun(currentPage);
    },
    "initPageEvent":function(listCount,fun){
        $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
            page.setPageListCount(listCount,$(this).attr("page-data"),fun);
        });
    },
    "getPageListModel":function(pageCount,currentPage){
        var prePage = currentPage-1;
        var nextPage = currentPage+1;
        var prePageClass ="pageItem";
        var nextPageClass = "pageItem";
        if(prePage<=0){
            prePageClass="pageItemDisable";
        }
        if(nextPage>pageCount){
            nextPageClass="pageItemDisable";
        }
        if((nextPage-1)>pageCount){
            nextPageClass="pageItemDisable";
        }
      /*  if(currentPage==pageCount){
        	 nextPageClass="pageItemDisable";
        }*/
        var appendStr ="";
        appendStr+="<li class='"+prePageClass+"' page-data='1' page-rel='firstpage' disabled='disabled' onclick='gotoPage("+(pageCount/pageCount)+")'>首页</li>";
        appendStr+="<li class='"+prePageClass+"' page-data='"+prePage+"' page-rel='prepage' onclick='gotoPage("+(currentPage-1)+")'>&lt;上一页</li>";
        var miniPageNumber = 1;
        if(currentPage-parseInt(page.maxshowpageitem/2)>0&&currentPage+parseInt(page.maxshowpageitem/2)<=pageCount){
            miniPageNumber = currentPage-parseInt(page.maxshowpageitem/2);
        }else if(currentPage-parseInt(page.maxshowpageitem/2)>0&&currentPage+parseInt(page.maxshowpageitem/2)>pageCount){
            miniPageNumber = pageCount-page.maxshowpageitem+1;
            if(miniPageNumber<=0){
                miniPageNumber=1;
            }
        }
        var showPageNum = parseInt(page.maxshowpageitem);
        if(pageCount<showPageNum){
            showPageNum = pageCount;
        }
        for(var i=0;i<showPageNum;i++){
        	var pageNumber= miniPageNumber++;
            var itemPageClass = "pageItem";
            if(pageNumber==currentPage){
                itemPageClass = "pageItemActive";
            }

            appendStr+="<li class='"+itemPageClass+"' page-data='"+pageNumber+"' onclick='gotoPage("+pageNumber+")' page-rel='itempage'>"+pageNumber+"</li>";
        }
        appendStr+="<li class='"+nextPageClass+"'  page-data='"+nextPage+"' page-rel='nextpage' onclick='gotoPage("+(currentPage+1)+");'>下一页&gt;</li>";
        appendStr+="<li class='"+nextPageClass+"' page-data='"+pageCount+"' page-rel='lastpage' onclick='gotoPage("+pageCount+")'>尾页</li>";
       return appendStr;

    }
    
}


/**
 * 点击页面跳转事件
 * @param pageNum
 */
	function gotoPage(pageNum){
		setDisabled();
		var url = document.forms[0].getAttribute("action");
		var bool = url.indexOf("?");
		var start = page.pagelistcount * (pageNum-1);
		var end = page.pagelistcount * pageNum;
		if(bool>0){
			window.location.href=url+"&start="+start+"&end="+end;
			alert(start);
			alert(end)  
		}else{
			alert(11)
		}
		
		
		/*var tempStr = "temText" ;
		var bool = tempStr.lastIndexOf("?");
		//返回大于等于0的整数值，若不包含"Text"则返回"-1。
		if(bool>0){
			alert(bool)
		 //document.write("包含字符串");
		}else{
			alert(11)
		 //document.write("不包含字符串");
		}*/
		
		
	}


	/**
	 * 设置按钮不可点击
	 */
	function setDisabled(){
		$.each($(".page li"),function(){
			if($(this).attr("class")=="pageItemDisable"){
				$(this).attr("disabled",true);
				//$(this).css("background-color","red");
				$(this).removeAttr("onclick");
			}
		})
		
	}
	
/**
 * 页面初始化
 */	
	$(function(){
		setDisabled();
		
	})
