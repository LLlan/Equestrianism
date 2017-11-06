package com.weixin.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.weixin.model.Return_Notice_Pay;

public class SendNoticeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(SendNoticeServlet.class.getName());
	 private final String HEADER = "<?xml version= \"1.0\" encoding=\"utf-8\"?>";
  /** 
   * 发货通知
   */  
  @Override
public void doGet(HttpServletRequest request, HttpServletResponse response)  
          throws ServletException, IOException {  
      doPost(request, response);  
  }  
  @Override
public void doPost(HttpServletRequest request, HttpServletResponse response)  
          throws ServletException, IOException {  
  	    response.setContentType("text/xml;charset=utf-8");
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		StringBuilder buff = new StringBuilder();
	    buff.append(HEADER);
	    
		String postStr=null;
		try{
			postStr=this.readStreamParameter(request.getInputStream());
			//postStr=this.readStreamParameter(request.getInputStream());
		}catch(Exception e){
			log.error("Error SendProductNoticeServiceImpl method get postStr:"+ e);
		}
	   Return_Notice_Pay pay=new Return_Notice_Pay();
	   if (null!=postStr&&!postStr.isEmpty()){
			Document document=null;
			try{
				document = DocumentHelper.parseText(postStr);
			}catch(Exception e){
				log.error("Error SendProductNoticeServiceImpl method get document:"+ e);
			}
			if(null==document){
				buff.append("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[支付失败]]></return_msg></xml>");
				out.print(buff.toString());
				return;
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
	            	String openid = root.elementText("openid");
		            pay.setOpenid(openid);
		            log.info("openid:"+openid);
	            	
		            String is_subscribe = root.elementText("is_subscribe");
		            pay.setIs_subscribe(is_subscribe);
		            log.info("is_subscribe:"+is_subscribe);
		            
		            String trade_type = root.elementText("trade_type");
		            pay.setTrade_type(trade_type);
		            log.info("trade_type:"+trade_type);
		            
		            String bank_type = root.elementText("bank_type");
		            pay.setBank_type(bank_type);
		            log.info("bank_type:"+bank_type);
		            
		            String total_fee = root.elementText("total_fee");
		            pay.setTotal_fee(Integer.parseInt(total_fee));
		            log.info("total_fee:"+total_fee);
		            
		            String coupon_fee = root.elementText("coupon_fee");
		            if(coupon_fee!=null&&!"".equals(coupon_fee)){
		            	pay.setCoupon_fee(Integer.parseInt(coupon_fee));
		            }  
		            log.info("coupon_fee:"+coupon_fee);
		            
		            String fee_type = root.elementText("fee_type");
		            pay.setFee_type(fee_type);
		            log.info("fee_type:"+fee_type);
		           
		            String transaction_id = root.elementText("transaction_id");
		            pay.setTransaction_id(transaction_id);
		            log.info("transaction_id:"+transaction_id);
		            
		            String out_trade_no = root.elementText("out_trade_no");
		            pay.setOut_trade_no(out_trade_no);
		            log.info("out_trade_no:"+out_trade_no);
		            
		            String attach = root.elementText("attach");
		            pay.setAttach(attach);
		            log.info("attach:"+attach);
		            
		            String time_end = root.elementText("time_end");
		            pay.setTime_end(time_end);
		            log.info("attach:"+attach);
		        	
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
	   }
       log.info("pay.toString():"+pay.toString());

		 if(pay.getReturn_code()=="SUCCESS"&&pay.getResult_code()=="SUCCESS"){
				buff.append("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[支付成功]]></return_msg></xml>");
		 }else{
				buff.append("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[支付失败]]></return_msg></xml>");
		 }
		 log.info("out :"+buff.toString());
        out.print(buff.toString());
         

  } 
 
  
//从输入流读取post参数
	protected String readStreamParameter(ServletInputStream in){
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader=null;
		try{
			reader = new BufferedReader(new InputStreamReader(in));
			String line=null;
			while((line = reader.readLine())!=null){
				buffer.append(line);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=reader){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
}
