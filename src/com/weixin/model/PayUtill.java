package com.weixin.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fh.util.SysConfig;
import com.weixin.model.PayEntity;
import com.weixin.model.Return_pay;
public class PayUtill {
	protected Logger log=Logger.getLogger(PayUtill.class.getName());
	/**
	 * XML传递
	 * */
	public String payPost(String urlStr,String xmlInfo) {
		 String result=null;
	        try {
	            URL url = new URL(urlStr);
	            URLConnection con = url.openConnection();
	            con.setDoOutput(true);
	            con.setRequestProperty("Pragma:", "no-cache");
	            con.setRequestProperty("Cache-Control", "no-cache");
	            con.setRequestProperty("Content-Type", "text/xml");
	            OutputStreamWriter out = new OutputStreamWriter(con
	                    .getOutputStream());    
	            out.write(new String(xmlInfo.getBytes("UTF-8")));
	            out.flush();
	            out.close();
	            BufferedReader br = new BufferedReader(new InputStreamReader(con
	                    .getInputStream()));
	            String line = "";
	            StringBuffer sb=new StringBuffer();
	            for (line = br.readLine(); line != null; line = br.readLine()) {
	                sb=sb.append(line);
	            }
	            result=sb.toString();
	            log.info("XML:"+result);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	 
	 /**
	  * 统一订单
	  * */
	 public Return_pay pay_unifiedorder(PayEntity payEntity,String key){
		 log.info("==================统一标签============================");
		 //获取签名
		 String signString=payEntity.getResultSign(key);
		 payEntity.setSign(signString);
         log.info("sign:"+signString);
		 //获取XML
		 String params=payEntity.getXML();
		 log.info("params XML:"+params);
		
		 //获取微信返回值XML
		 String xml=payPost(SysConfig.getInstance().getProperty("wx_payNoticeUrl"), params);
		 log.info("XML:"+xml);
		 
		 Return_pay pay=new Return_pay();
		 Document document=null;
			try{
				document = DocumentHelper.parseText(xml);
			}catch(Exception e){
				e.toString();
			}
			
		 Element root=document.getRootElement();
         String return_code = root.elementText("return_code");
         log.info("return_code:"+return_code);
         pay.setReturn_code(return_code);
         if("SUCCESS".equalsIgnoreCase(return_code)){
         	String appid = root.elementText("appid");
	            pay.setAppid(appid);
         	    log.info("appid:"+appid);
	            
	            String mch_id = root.elementText("mch_id");
	            pay.setMch_id(mch_id);
	            log.info("mch_id:"+mch_id);
	            
	            String nonce_str = root.elementText("nonce_str");
	            pay.setNonce_str(nonce_str);
	            log.info("nonce_str:"+nonce_str);
	            
	            String device_info = root.elementText("device_info");
	            pay.setDevice_info(device_info);
	            log.info("device_info:"+device_info);
	            
	            String sign = root.elementText("sign");
	            pay.setSign(sign);
	            log.info("sign:"+sign);
	            
	            String result_code = root.elementText("result_code");
	            pay.setResult_code(result_code);
	            log.info("result_code:"+result_code);
	            
	            if("SUCCESS".equalsIgnoreCase(result_code)){
	            	String trade_type = root.elementText("trade_type");
		            pay.setTrade_type(trade_type);
		            log.info("trade_type:"+trade_type);
		            
		            String prepay_id = root.elementText("prepay_id");
		            pay.setPrepay_id(prepay_id);
		            log.info("prepay_id:"+prepay_id);
		            
		            if("NATIVE".equalsIgnoreCase(trade_type)){
		            	String code_url  = root.elementText("code_url");
			            pay.setCode_url(code_url);
			            log.info("code_url:"+code_url);
		            } 
	            }else{
	            	String err_code = root.elementText("err_code");
		            pay.setErr_code(err_code);
		            log.info("err_code:"+err_code);
		            
		            String err_code_des = root.elementText("err_code_des");
		            pay.setErr_code_des(err_code_des);
		            log.info("err_code_des:"+err_code_des);
	            } 
         }else{
         	    String return_msg = root.elementText("return_msg");
	            pay.setReturn_msg(return_msg);
	            log.info("return_msg:"+return_msg);
         }
         log.info("pay.toString():"+pay.toString());
		 
		 return pay;
	 }
	 
	 public static void main(String[] arg){
		PayEntity payEntity=new PayEntity();
	    //payEntity.setAppid(WeiXinStaticFinalValue.AppId);
		payEntity.setAppid(SysConfig.getInstance().getProperty("wx_appid"));
		payEntity.setBody("微信测试");
		//payEntity.setMch_id(WeiXinStaticFinalValue.mchId);
		payEntity.setMch_id(SysConfig.getInstance().getProperty("wx_mchId"));
		payEntity.setNonce_str("ohzshbzjkccxfjam0u80bggfzg6xp6ed");

		//payEntity.setNonce_str(WeiXinStaticMethod.getErWeiMa().toLowerCase());
		payEntity.setNotify_url("http://food.xzdkiosk.com/food/foodwx/SendNoticeServlet.do");
		payEntity.setOpenid("oW29Ovy_b94FmOcq72xaSIAgJFnk");
		payEntity.setOut_trade_no("E2015707123456000");
		payEntity.setSpbill_create_ip("60.168.250.26");
		payEntity.setTotal_fee(1);
		payEntity.setTrade_type("JSAPI");
		
		PayUtill payUtill=new PayUtill();
		Return_pay pay=payUtill.pay_unifiedorder(payEntity,SysConfig.getInstance().getProperty("wx_key"));
		System.out.println("pay.toString():"+pay.toString());
	 }
}
