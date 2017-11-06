package com.fh.controller.pc_alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2017062107537882";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    //public static String merchant_private_key = "";
    //public static String merchant_private_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjiCgcu4J6/m7WN334dgOO40FVtG98xrN2hijICpln3YTJnOQuAu1uwgNSdPA1RmyZ82OmK2vWtYxsqaLZ4+BTVFU7EAWYqom/xSFfCMbaPB8eyoA42m8YPlExyv335ZSTauTW4EUqJF6mXIWMRqnasbAFFawGsGJ3MU4pGr0KBJTpt7daEYohLft7V4YsKl1Wnut8Yry5X+DvVUuUicsiiM9aGlIyjHrVocYmoBzNAuoU6O+lgiU0ITM6jt5nf6YKQQJe3YC8pojwwPemGiSSY/yRJ18x6tWASoL5OdpQyCQIF9t0Faz8fxEjbSCH3Exddc7epotegcXOyAAtZaTQwIDAQAB";
    public static String merchant_private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIWCKzI2yStrIJh6dLWfPY6Ib7PdMOz2wP4QOZsPdpKwxonwZ2qfnDvjuV7h4E+fjdJsLGaBYBT3IbIKoIh58bbinlacLY9ogfiOxoDEefiGgQ7RQMHHNAGogp/S+1LEL/+fGmXFuELD3QE3Q6dwf3lwk3cn1f0ogc7e1RK6fZLzAgMBAAECgYA5hd0wtHQ2teCEcfaJLoQqPlTKjVoAL0z16m23hpETIQFZ+2/De+On1jVPUUkBZYCIkj0LKWEJh3VILdX/F3NyNhfCHcuMpDp9PQuapLm+r8qvq/wJTGDsmoShHtnn9W+Yu8r3boL/0Y9emvikL3ZAcOtfabYSmcIfD/CQhpPHwQJBAMinGnGOqKzHiFsrfOTqL4HocNXgPeeqy2GXnbZT3Fgr5Wvf+BME9mVQrrEUcJomn/0HFtYkEcpZi0t2nMLRzGECQQCqVb0EqfVADA5rLFa5vky8F02IPW07ODl7f9dHe3UWC8nzzexUSn2xaSdEQN1RR5j0FJUlieFu0HN/pP2x7n/TAkEAqAvDSs2mmKW3nltqizFSvmLUdVsLnYVeNgrLzBKp3LJOvyzhT9R9I1cSJz4uG/wzS69vQcMNn0GlNntNSKB6oQJAeL6oa/rywEELo4tduvOcP6UhL9eja9xSWypnd+ObP1KCCVLLGYFokm+hBzAX8vsXAMbqZx7TcBNSHITf1dbkQwJBAK1ielGwAh2yPhhmC9EPfpqfuuOcSqS6oT6R2G9OUuCI6guJJytGqUoMWwWnA7jFovSX4okxG5LqQg4Y2ktmRik=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmip4EnNvSyW9JFT1mWrtegnTL0RCY1z0o5ScITLBkZK/niK2UxG5RWKEFJsVmb3fxdb1IL5pPkNrNl6dk7JwSgu98QPSV/h1b+My1iVpEc6ccw5zDQ6cUbjJvk38gcDjeV5CZe93usTkeEGo++5l+f3jof9PWOb7kS5VrtrhGzrQb/vyeG9pDNEIB58yqYJEIG2eRGAc5gS6k1jqJ3bUayZwpdWab3q6WdPrRDFbSpNM+0et4NHPbevtuqpR1uBmabQoj0ox8oI9NWnk6WN0IFN8kLq1Q88FQys0Ga++0Ln/hQ/U7cX2AK8vOBRzE+9IzMuyPFU7YjXVSq9WumbS6wIDAQAB";
	
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://192.168.1.88:8080/Equestrianism/api/pc_alipay/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://192.168.1.88:8080/Equestrianism/api/pc_alipay/return_url";

	// 签名方式
	//public static String sign_type = "RSA2";
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

