package com.weixin.dao;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.util.SysConfig;
import com.weixin.method.HttpClientConnectionManager;
import com.weixin.model.OAuthAccessToken;
import com.weixin.model.UserEntity;

public class WeiXinDaoImpl implements WeiXinDao{
	
	public static DefaultHttpClient httpclient;
	private static Logger log=Logger.getLogger(WeiXinDaoImpl.class.getName());
	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
	}
	
	/**
	 * 微信OAuth2.0授权（目前微信只支持在微信客户端发送连接，实现授权）
	 * */
	@Override
	public String getCodeUrl(String appid, String redirect_uri,String scope,String state) throws Exception {
		redirect_uri = URLEncoder.encode(redirect_uri, "utf-8");
		String getCodeUrl=SysConfig.getInstance().getProperty("wx_getCodeUrl").replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("SCOPE", scope).replace("STATE", state);
		log.info("getCodeUrl Value:"+getCodeUrl);
		return getCodeUrl;
	}
	/**
	 * 微信OAuth2.0授权-获取accessToken(这里通过code换取的网页授权access_token,与基础支持中的access_token不同）
	 */
	@Override
	public OAuthAccessToken getOAuthAccessToken(String appid, String secret,String code) throws Exception {
		String getOAuthAccessToken=SysConfig.getInstance().getProperty("wx_getOAuthAccessToken").replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
		log.info("getOAuthAccessToken Value:"+getOAuthAccessToken);
		HttpGet get = HttpClientConnectionManager.getGetMethod(getOAuthAccessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		log.info("jsonObject Value:"+jsonObject);
		OAuthAccessToken accessToken=new OAuthAccessToken();
		accessToken.setAccessToken(jsonObject.getString("access_token"));
		accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
		accessToken.setRefreshToken(jsonObject.getString("refresh_token"));
		accessToken.setOpenid(jsonObject.getString("openid"));
		accessToken.setScope(jsonObject.getString("scope"));
		return accessToken;
	}
	/**
	 * 微信OAuth2.0授权-刷新access_token（如果需要）
	 */
	@Override
	public OAuthAccessToken refershOAuthAccessToken(String appid, String refresh_token) throws Exception {
		String getreferAccessUrl=SysConfig.getInstance().getProperty("wx_getreferAccessUrl").replace("APPID", appid).replace("REFRESH_TOKEN", refresh_token);
		log.info("getreferAccessUrl Value:"+getreferAccessUrl);
		HttpGet get = HttpClientConnectionManager.getGetMethod(getreferAccessUrl);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		OAuthAccessToken accessToken=new OAuthAccessToken();
		accessToken.setAccessToken(jsonObject.getString("access_token"));
		accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
		accessToken.setRefreshToken(jsonObject.getString("refresh_token"));
		accessToken.setOpenid(jsonObject.getString("openid"));
		accessToken.setScope(jsonObject.getString("scope"));
		return accessToken;
	}
	/**
	 * 微信OAuth2.0授权-检验授权凭证（access_token）是否有效
	 */
	@Override
	public boolean isAccessTokenValid(String accessToken, String openId) throws Exception {
		String isOAuthAccessToken=SysConfig.getInstance().getProperty("wx_isOAuthAccessToken").replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		log.info("isOAuthAccessToken Value:"+isOAuthAccessToken);
		HttpGet get = HttpClientConnectionManager.getGetMethod(isOAuthAccessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		int returnNum=jsonObject.getIntValue("errcode");
		if(returnNum==0){
			return true;
		}
		return false;
	}
	/**
	 * 微信OAuth2.0授权-获取用户信息（网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息）
	 */
	@Override
	public UserEntity acceptOAuthUserNews(String accessToken, String openId) throws Exception {
		String getOAuthUserNews=SysConfig.getInstance().getProperty("wx_getOAuthUserNews").replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		log.info("getOAuthUserNews Value:"+getOAuthUserNews);
		HttpGet get = HttpClientConnectionManager.getGetMethod(getOAuthUserNews);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		UserEntity entity=new UserEntity();
		entity.setOpenid(jsonObject.getString("openid"));
		entity.setNickname(jsonObject.getString("nickname"));
		entity.setSex(jsonObject.getIntValue("sex"));
		entity.setProvince(jsonObject.getString("province"));
		entity.setCity(jsonObject.getString("city"));
		entity.setCountry(jsonObject.getString("country"));
		entity.setHeadimgurl(jsonObject.getString("headimgurl"));
		return entity;
	}
	
}
