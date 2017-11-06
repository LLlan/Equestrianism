package com.fh.demo;

public class TestLogo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//生成二维码
		try {
			String text = "https://www.baidu.com";  
			QRCodeUtil.encode(text, "e:/111.jpg", "e:/image/", true);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		//解析二维码
		/*try {
			System.out.println(QRCodeUtil.decode("e:/image/4886888.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
