package com.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fh.util.SysConfig;
import com.weixin.model.AccessToken;
import com.weixin.model.MyX509TrustManager;
import com.weixin.model.Ticket;
/**
 * 通用工具类
 * 
 */
public class CommonUtil {
    
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
    
    // 凭证获取（GET）
    //public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
    /**
     * 发送 https 请求
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject（通过 JSONObject.get(key) 的方式获取 JSON 对象的属性值）
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        
        JSONObject jsonObject = null;
        
        try {
            // 创建 SSLContext 对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
        	//TrustManager[] tm = { new TrustAnyTrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述 SSLContext 对象中得到 SSLSocketFactory 对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            
            // 当 outputStr 不为 null 时，向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error(" 连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https 请求异常：{}", e);
        }
        
        return jsonObject;
    }

    /**
     * 获取接口访问凭证
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getToken(String appid, String appsecret) {
    	AccessToken token = null;
        String requestUrl = SysConfig.getInstance().getProperty("wx_getTokenUrl").replace("APPID", appid).replace("APPSECRET", appsecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                token = new AccessToken();
                token.setToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                token = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return token;
    }
    
    /** 
	 * 获取access_token 
	 *  
	 * @param CorpID 企业Id 
	 * @param SECRET 管理组的凭证密钥，每个secret代表了对应用、通讯录、接口的不同权限；不同的管理组拥有不同的secret 
	 * @return 
	 */  
	public static Ticket getTicket(String accessToken) {  
		Ticket ticket = null;  
	  
	    String requestUrl = SysConfig.getInstance().getProperty("wx_getTicket").replace("ACCESS_TOKE", accessToken).replace("JSAPI", "jsapi");  
	    JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);  
	    // 如果请求成功  
	    if (null != jsonObject) {  
	        try {  
	        	ticket = new Ticket();  
	        	ticket.setTicket(jsonObject.getString("ticket"));
	        	ticket.setExpiresIn(jsonObject.getInt("expires_in"));
	            System.out.println("获取ticket成功:"+jsonObject.getString("ticket")+"————"+jsonObject.getInt("expires_in"));
	        } catch (Exception e) {  
	            accessToken = null;  
	            // 获取token失败  
	            String error = String.format("获取ticket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	            System.out.println(error);
	        }  
	    }  
	    return ticket;  
	}
    
	// 获得js signature
    public static String getSignature(String jsapi_ticket, String timestamp,
            String nonce, String jsurl) throws IOException {
        /****
         * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
         * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
         * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
         * URL 转义。即 signature=sha1(string1)。
         * **如果没有按照生成的key1=value&key2=value拼接的话会报错
         */
        String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket,
                "timestamp=" + timestamp, "noncestr=" + nonce, "url=" + jsurl };
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2])
                .concat("&"+paramArr[3]);
        System.out.println("拼接之后的content为:"+content);
        String gensignature = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行 sha1 加密
            byte[] digest = md.digest(content.toString().getBytes());
            gensignature = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将 sha1 加密后的字符串与 signature 进行对比
        if (gensignature != null) {
            return gensignature;// 返回signature
        } else {
            return "false";
        }
        // return (String) (ciphertext != null ? ciphertext: false);
    }
    
    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
    
    /**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 根据内容类型判断文件扩展名
     * 
     * @param contentType 内容类型
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }
}
