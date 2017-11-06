package com.weixin.test;

import java.util.Date;
import net.sf.json.JSONObject;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.weixin.model.NoticeTemplate;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PageData productType = new PageData();
		productType.put("value", "商品名称");
	    productType.put("color", "#173177");
	    
	    PageData name = new PageData();
	    name.put("value", "肯德基A套餐");
	    name.put("color", "#173177");
	    
	    PageData time = new PageData();
	    time.put("value", Tools.date2Str(new Date()));
	    time.put("color", "#173177");
	    
	    PageData remark = new PageData();
	    remark.put("value", "备注：如有问题请直接联系我们！");
	    remark.put("color", "#173177");
	    
	    PageData data = new PageData();
	    data.put("productType", productType);
	    data.put("name", name);
	    data.put("time", time);
	    data.put("remark", remark);
	    
	    NoticeTemplate nTemplate = new NoticeTemplate();
	    nTemplate.setData(data);
	    nTemplate.setTemplate_id("357U2xzSl_ytJJyQabloInNVrJQrvYFHU4CAtfhRCaY");
	    nTemplate.setTopcolor("#FF0000");
	    nTemplate.setTouser("openid");
	    nTemplate.setUrl("http://www.baidu.com");
		
		String jsonString = JSONObject.fromObject(nTemplate).toString();
		System.out.println(jsonString);
	}
}
