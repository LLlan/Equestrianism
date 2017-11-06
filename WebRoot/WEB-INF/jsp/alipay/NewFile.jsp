<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="alipaysubmit" name="alipaysubmit" action="https://mapi.alipay.com/gateway.do?_input_charset=utf-8" method="post">
		<input type="hidden" name="subject" value="测试" />
		<input type="hidden" name="sign_type" value="RSA" />
		<input type="hidden" name="notify_url" value="http://商户网址/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/notify_url.jsp" />
		<input type="hidden" name="out_trade_no" value="2017612928127" />
		<input type="hidden" name="return_url" value="http://商户网址/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/return_url.jsp" />
		<input type="hidden" name="sign" value="wxBdJWMBIOtD3nzMVaVHx/sTbsW93tNNCIv66Zz4ZI7rqhUctofaJglf4oDJjUTHarfCSkgz9sMwOzlBx6rqztXwnTFj2QFuLeZG4c5XP6TXGVUYvxh0UQnBIjZOvvUVeRpu7JinqDmfMvTfpBrFJTabSiw+4eENyk5rEh2B4WY=" />
		<input type="hidden" name="_input_charset" value="utf-8" />
		<input type="hidden" name="app_pay" value="Y" />
		<input type="hidden" name="total_fee" value="0.01" />
		<input type="hidden" name="service" value="alipay.wap.create.direct.pay.by.user" />
		<input type="hidden" name="partner" value="2088022714785785" />
		<input type="hidden" name="seller_id" value="2088022714785785" />
		<input type="hidden" name="payment_type" value="1" />
		<input type="hidden" name="show_url" value="https://item.taobao.com/item.htm?spm=a217m.8316598.701916.4.DJlATQ&id=552203794511" />
		<input type="submit" value="确认" style="display:none;">
	</form>
	<script>document.forms['alipaysubmit'].submit();</script>
</body>
</html>