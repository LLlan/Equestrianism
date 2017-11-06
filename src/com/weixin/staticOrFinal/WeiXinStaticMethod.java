package com.weixin.staticOrFinal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;
import com.weixin.method.MD5;
/**
 * 微信中静态方法
 * */
public class WeiXinStaticMethod {
	private static Random random=new Random();
	private static String stringContent="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static Integer num=32;
	
	public static String getErWeiMa(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<num;i++){
			sb.append(stringContent.charAt(random.nextInt((stringContent.length()-1))));
		}
		return sb.toString();
	}
	public static String getIpAddress() throws UnknownHostException {   
        InetAddress address = InetAddress.getLocalHost();   
  
        return address.getHostAddress();   
    }  
	
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	public static String createSignJSAPI(String appId,String timeStamp,String nonceStr,String packages,String key){
		String sp="appId="+appId+"&nonceStr="+nonceStr+"&package="+packages+"&signType=MD5&timeStamp="+timeStamp+"&key="+key;
		System.out.println("md5 sb:"+sp);
		return MD5.MD5Encode(sp).toUpperCase();
	}
	/**
	 * 获取当前时间
	 * */
	public static String getTime(){
		return new Date().getTime()+"";
	}
	
	/**
	 * 数组转字符串
	 * */
	public static String ArrayToString(String [] arr){
		StringBuffer bf = new StringBuffer();
		for(int i = 0; i < arr.length; i++){
		 bf.append(arr[i]);
		}
		return bf.toString();
	}
	/**
	 * SHA1加密
	 * */
	public static String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
		   resultString = new String(sourceString);
		   MessageDigest md = MessageDigest.getInstance("SHA-1");
		   resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}
	protected static String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
		    	buf.append("0");
		   	}
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}
}
