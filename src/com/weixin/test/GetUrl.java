package com.weixin.test;

import com.fh.util.SysConfig;
import com.weixin.dao.WeiXinDaoImpl;

public class GetUrl {

	public static void main(String[] args){
		String url="http://parking.yunwalker.cn/parking/parker/user.do";
		WeiXinDaoImpl dao=new WeiXinDaoImpl();
		String pathUrl=null;
		try {
			pathUrl=dao.getCodeUrl(SysConfig.getInstance().getProperty("wx_appid"),url,"snsapi_userinfo", "state");
		} catch (Exception e) {
			
		}
		System.out.println(pathUrl);
	}
}
