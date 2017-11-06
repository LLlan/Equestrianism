package com.weixin.method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class staticMethod {
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
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(staticMethod.getIpAddress());

	}
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	public static String createSignJSAPI(String appId,String timeStamp,String nonceStr,String packages,String key){
		String sp="appId="+appId+"&nonceStr="+nonceStr+"&package="+packages+"&signType=MD5&timeStamp="+timeStamp+"&key="+key;
		System.out.println("md5 sb:"+sp);
		return MD5.MD5Encode(sp).toUpperCase();
	}

}
