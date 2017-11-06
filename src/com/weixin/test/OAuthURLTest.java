package com.weixin.test;

import org.junit.Test;

import com.weixin.dao.WeiXinDao;
import com.weixin.dao.WeiXinDaoImpl;
import com.weixin.staticOrFinal.WeiXinStaticFinalValue;
public class OAuthURLTest {
	WeiXinDao dao=new WeiXinDaoImpl();
	String ip="http://parking.yunwalker.cn/parking/";
	/**
	 * 个人中心
	 * */
	@Test
	public void GetGRZX(){
		String servlet="OAuthUserNewServlet.do";
		String url=ip+servlet;
		String pathUrl=null;
		System.out.println(url);
		try {
			pathUrl=dao.getCodeUrl(WeiXinStaticFinalValue.AppId,url,"snsapi_userinfo", "state");
		} catch (Exception e) {
			
		}
		System.out.println("url:"+pathUrl);
	}
	
}
