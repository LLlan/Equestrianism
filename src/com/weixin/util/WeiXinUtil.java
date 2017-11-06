package com.weixin.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fh.util.SysConfig;
import com.weixin.dao.WeiXinDaoImpl;
import com.weixin.model.AccessToken;
import com.weixin.model.NoticeTemplate;
import com.weixin.model.OAuthAccessToken;
import com.weixin.model.UserEntity;
/**
 * 公众平台通用接口工具类
 * 
 * @author 梁森
 * @date 2016-09-26
 */
public class WeiXinUtil {
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);

	// 菜单创建（POST） 限100（次/天）   
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	
	// 获取access_token的接口地址（GET） 限200（次/天）   
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	  
	
	
    public static String getUrl(String url){
		
		WeiXinDaoImpl dao=new WeiXinDaoImpl();
		String pathUrl=null;
		try {
			pathUrl=dao.getCodeUrl(SysConfig.getInstance().getProperty("wx_appid"),url,"snsapi_userinfo", "state");
		} catch (Exception e) {
			
		}
		return pathUrl;
	}
	
	public static  UserEntity getUserInfo(String code) throws Exception{
		log.info("code:"+code);
	    WeiXinDaoImpl dao=new WeiXinDaoImpl();
	    UserEntity entity=null;
		try {
			OAuthAccessToken oac = dao.getOAuthAccessToken(SysConfig.getInstance().getProperty("wx_appid"), SysConfig.getInstance().getProperty("wx_appsecret"),code);//通过code换取网页授权access_token
			log.info("--------------------"+oac.getAccessToken()+";"+oac.getRefreshToken()+";"+oac.getScope()+";"+oac.getOpenid());
			OAuthAccessToken oacd=dao.refershOAuthAccessToken(SysConfig.getInstance().getProperty("wx_appid"), oac.getRefreshToken());//刷新access_token
			log.info("--------------------"+oacd.getAccessToken()+";"+oacd.getRefreshToken()+";"+oacd.getScope()+";Openid:"+oacd.getOpenid());
			entity=dao.acceptOAuthUserNews(oacd.getAccessToken(),oacd.getOpenid());//获取用户信息
			log.info("--------------------"+entity.getNickname()+";"+entity.getCountry());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("-------------------"+entity.getNickname()+";"+entity.getCountry());
		return entity;
	}
	
	/**
	  * 方法名：getWxConfig</br>
	  * 详述：获取微信的配置信息 </br>
	  * 开发人员：souvc  </br>
	  * 创建时间：2016-1-5  </br>
	  * @param request
	  * @return 说明返回值含义
	  * @throws 说明发生此异常的条件
	  */
	public static Map<String, Object> getWxConfig(HttpServletRequest request,String requestUrl) {
	     Map<String, Object> ret = new HashMap<String, Object>();

	     String access_token = CommonUtil.getToken(SysConfig.getInstance().getProperty("wx_appid"), SysConfig.getInstance().getProperty("wx_appsecret")).getToken();
	     
	     String jsapi_ticket = CommonUtil.getTicket(access_token).getTicket();
	     String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
	     String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
	       
	     String signature = "";
	     try {
			  signature = CommonUtil.getSignature(jsapi_ticket, timestamp, nonceStr, requestUrl);
		 } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
	     }
	     System.out.println("appId"+SysConfig.getInstance().getProperty("wx_appid"));
	     System.out.println("timestamp"+timestamp);
	     System.out.println("nonceStr"+nonceStr);
	     System.out.println("signature"+signature);
	     ret.put("appId", SysConfig.getInstance().getProperty("wx_appid"));
	     ret.put("timestamp", timestamp);
	     ret.put("nonceStr", nonceStr);
	     ret.put("signature", signature);
	     return ret;
    }
	
	/**
     * 发送模板消息
     * appId 公众账号的唯一标识
     * appSecret 公众账号的密钥
     * openId 用户标识
     */
    public static String send_template_message(String appId, String appSecret, NoticeTemplate nt) {

    	AccessToken token = CommonUtil.getToken(appId, appSecret);
        String access_token = token.getToken();
        String url = SysConfig.getInstance().getProperty("wx_sendModelNotice").replace("ACCESS_TOKEN", access_token);
        
        String jsonString = JSONObject.fromObject(nt).toString();
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonString);
        log.info(jsonObject.toString());
        String result = "0";
        if (null != jsonObject) {  
             if ("0" != jsonObject.getString("errcode")) {  
                 result = jsonObject.getString("errcode");  
                  log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
             }  
        }
        log.info("模板消息发送结果："+result);
        return result;
    }
}