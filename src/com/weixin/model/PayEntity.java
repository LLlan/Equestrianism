package com.weixin.model;
import com.weixin.method.MD5;

public class PayEntity {
	private String appid;//【必填】微信分配的公众账号ID 
	private String attach;//【非必填】附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 
	private String body;//【必填】商品或支付单简要描述
	private String detail;//【非必填】商品名称明细列表
	private String device_info;//【非必填】//终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String fee_type;//【非必填】符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类
	private String goods_tag;//【非必填】商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	private String mch_id;//【必填】微信支付分配的商户号 
	private String nonce_str;//【必填】随机字符串，不长于32位
	private String notify_url;//【必填】接收微信支付异步通知回调地址
	private String openid ;//【非必填】trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。 
	private String out_trade_no;//【必填】商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	private String product_id;//【非必填】trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。 
	private String sign;//【必填】签名
	private String spbill_create_ip;//【必填】APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 
	private String time_expire;//【非必填】订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则   注意：最短失效时间间隔必须大于5分钟
	private String time_start;//【非必填】订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	private int total_fee;//【必填】订单总金额，只能为整数
	private String trade_type;//【必填】取值如下：JSAPI，NATIVE，APP，WAP,详细说明见参数规定
	
	/**
	 * 获取sign
	 * */
	public String getResultSign(String key){
		StringBuffer sb=new StringBuffer();
		if(appid!=null&&!"".equals(appid)){
			sb=sb.append("appid="+appid);
		}
		if(attach!=null&&!"".equals(attach)){
			sb=sb.append("&attach="+attach);
		}
		if(body!=null&&!"".equals(body)){
			sb=sb.append("&body="+body);
		}
		if(detail!=null&&!"".equals(detail)){
			sb=sb.append("&detail="+detail);
		}
		if(device_info!=null&&!"".equals(device_info)){
			sb=sb.append("&device_info="+device_info);
		}
		if(fee_type!=null&&!"".equals(fee_type)){
			sb=sb.append("&fee_type="+fee_type);
		}
		if(goods_tag!=null&&!"".equals(goods_tag)){
			sb=sb.append("&goods_tag="+goods_tag);
		}
		if(mch_id!=null&&!"".equals(mch_id)){
			sb=sb.append("&mch_id="+mch_id);
		}
		if(nonce_str!=null&&!"".equals(nonce_str)){
			sb=sb.append("&nonce_str="+nonce_str);
		}
		if(notify_url!=null&&!"".equals(notify_url)){
			sb=sb.append("&notify_url="+notify_url);
		}
		if(openid!=null&&!"".equals(openid)){
			sb=sb.append("&openid="+openid);
		}
		if(out_trade_no!=null&&!"".equals(out_trade_no)){
			sb=sb.append("&out_trade_no="+out_trade_no);
		}
		if(product_id!=null&&!"".equals(product_id)){
			sb=sb.append("&product_id="+product_id);
		}
		if(spbill_create_ip!=null&&!"".equals(spbill_create_ip)){
			sb=sb.append("&spbill_create_ip="+spbill_create_ip);
		}
		if(time_expire!=null&&!"".equals(time_expire)){
			sb=sb.append("&time_expire="+time_expire);
		}
		if(time_start!=null&&!"".equals(time_start)){
			sb=sb.append("&time_start="+time_start);
		}
		if(total_fee>0){
			sb=sb.append("&total_fee="+total_fee);
		}
		if(trade_type!=null&&!"".equals(trade_type)){
			sb=sb.append("&trade_type="+trade_type);
		}
		System.out.println(sb.toString());
		sb.append("&key="+key);
		System.out.println(sb.toString());
		return MD5.MD5Encode(sb.toString()).toUpperCase();
	}
	
	/**
	 * 获取xml
	 * */
	public String getXML(){
		StringBuffer xml=new StringBuffer("<xml>");
		if(appid!=null&&!"".equals(appid)){
			xml=xml.append("<appid>"+appid+"</appid>");
		}
		if(mch_id!=null&&!"".equals(mch_id)){
			xml=xml.append("<mch_id>"+mch_id+"</mch_id>");
		}
		if(device_info!=null&&!"".equals(device_info)){
			xml=xml.append("<device_info>"+device_info+"</device_info>");
		}
		if(nonce_str!=null&&!"".equals(nonce_str)){
			xml=xml.append("<nonce_str>"+nonce_str+"</nonce_str>");
		}
		if(sign!=null&&!"".equals(sign)){
			xml=xml.append("<sign>"+sign+"</sign>");
		}
		if(body!=null&&!"".equals(body)){
			xml=xml.append("<body><![CDATA["+body+"]]></body>");
		}
		if(detail!=null&&!"".equals(detail)){
			xml=xml.append("<detail>"+detail+"</detail>");
		}
		if(attach!=null&&!"".equals(attach)){
			xml=xml.append("<attach>"+attach+"</attach>");
		}
		if(out_trade_no!=null&&!"".equals(out_trade_no)){
			xml=xml.append("<out_trade_no>"+out_trade_no+"</out_trade_no>");
		}
		if(fee_type!=null&&!"".equals(fee_type)){
			xml=xml.append("<fee_type>"+fee_type+"</fee_type>");
		}
		if(total_fee>0){
			xml=xml.append("<total_fee>"+total_fee+"</total_fee>");
		}
		if(spbill_create_ip!=null&&!"".equals(spbill_create_ip)){
			xml=xml.append("<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>");
		}
		if(time_start!=null&&!"".equals(time_start)){
			xml=xml.append("<time_start>"+time_start+"</time_start>");
		}
		
		if(time_expire!=null&&!"".equals(time_expire)){
			xml=xml.append("<time_expire>"+time_expire+"</time_expire>");
		}
		if(goods_tag!=null&&!"".equals(goods_tag)){
			xml=xml.append("<goods_tag>"+goods_tag+"</goods_tag>");
		}
		
		if(notify_url!=null&&!"".equals(notify_url)){
			xml=xml.append("<notify_url>"+notify_url+"</notify_url>");
		}
		if(trade_type!=null&&!"".equals(trade_type)){
			xml=xml.append("<trade_type>"+trade_type+"</trade_type>");
		}
		
		if(product_id!=null&&!"".equals(product_id)){
			xml=xml.append("<product_id>"+product_id+"</product_id>");
		}
		if(openid!=null&&!"".equals(openid)){
			xml=xml.append("<openid>"+openid+"</openid>");
		}
		xml=xml.append("</xml>");
		return xml.toString();
	}

	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
