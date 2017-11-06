package com.weixin.staticOrFinal;

/**
 * 使用常量
 * */
public class WeiXinStaticFinalValue {
	public final static String AppId ="wx18fe61aa92948fdc";
	public final static String AppSecret ="826918cb06845586fadc6a16c24b1719";
	public final static String mchId ="1339976701";
	public final static String key ="obNx6IPifVelUhz5ZYpdFd7CKtVe2ni8";
	
	public final static String getTokenUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String getCodeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public final static String getOAuthAccessToken="https://101.226.90.58/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public final static String getreferAccessUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	public final static String getOAuthUserNews="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public final static String isOAuthAccessToken="https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	
	/**
	 * 统一下单
	 * */
	public final static String payNoticeUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	
}
