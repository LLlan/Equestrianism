package com.weixin.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.fh.util.SysConfig;

public class Wxutils {

	public static String url = SysConfig.getInstance().getProperty("system_url")+ "/wx.action";

	public static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ SysConfig.getInstance().getProperty("wx_appid") + "&secret=" + SysConfig.getInstance().getProperty("wx_appsecret");// 获取token
	public static String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
	public static String guanzhu_url = "https://api.weixin.qq.com/sns/oauth2/access_token?";// 获取用户是否关注
	
	
	
	/*** 自定义菜单 **/
	public static String menu_create = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";// 创建自定义菜单
	public static String menu_get = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";// 查询自定义菜单
	public static String menu_del = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";// 删除自定义菜单
	/*** end自定义菜单 **/

	/**** 用户管理 */
	public static String user_url = "https://api.weixin.qq.com/cgi-bin/user/info?";// 用户基本信息
	public static String user_list = "https://api.weixin.qq.com/cgi-bin/user/get?";
	/**** end用户管理 */
	
	/** 二维码管理 */
	// 生成二维码
	private static String yongjiu_ewm = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	
	private static String upload_img = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";//临时素材
	
	
	private static String kefu = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	
	
	
	

	
	public static void main(String[] args) {
//		String token = "wkv-aWSLbFe90mSxTAK_Gqv3ooxYw_GJLMx10GBqLQ-MOlHaf-7SKEsxn4HNsvui7Ppe7mUfZiQSCctX9iS3sY09fnq25bZNwmPqeym2A8QSS-Bc2zx3NKzbph3fth0uJVNiAEAZLD";
//		String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type=image";
//		
//		JSONObject jsonObj = JSONObject.fromObject("{\"type\":\"image\",\"media_id\":\"6OxVtQx6qfMEnJnx0YUJQ2YrXhsAj0sGKjqYHvrcvlCH976SpHe-FlrMK7ma0F0y\",\"created_at\":1461686387}");
//		String mediaId = jsonObj.getString("media_id");
//		System.out.println(mediaId);
////		upload_img(null,"F:\\1\\20160426234804.jpg");
	 
//		String ticket = "gQGI8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1EwVEJiY0RtbHZEdmwxYTRxMnlWAAIEOYkfVwMEAAAAAA==";
//		String img = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
//		String filepath = Wxutils.downloadMedia(img, "F:\\img\\");
		
		String json = "{\"touser\":\"ohj20szXzXwDMLg1hjoUC0PU0gYk\",\"msgtype\":\"text\", \"text\":  {\"content\":\"欢迎关注公众号\" }}";
		kefu_msg(null,json);
	}
	
	
	public static JSONObject kefu_msg(HttpServletRequest request,String json){
		boolean debug = true;
		String wx_util_debug = SysConfig.getInstance().getProperty("wx_util_debug");
		if (StringUtils.isNotBlank(wx_util_debug) && wx_util_debug.equals("1")) {
			debug = false;
		}
		String token = Wxutils.getToken(request);
		String url = kefu + token;
		HttpPost httppost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			StringEntity s = new StringEntity(json.toString(), HTTP.UTF_8);
			s.setContentEncoding("utf-8");
			s.setContentType("application/json");
			httppost.setHeader("Content-Type",
					"application/json; charset=utf-8");
			httppost.setEntity(s);
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			String context = EntityUtils.toString(entity, HTTP.UTF_8);
			JSONObject result = JSONObject.fromObject(context);
			if (debug) {
				System.out.println("客服接口：" + result.toString());
			}
			Object errcode = result.get("errcode");
			if (null == errcode) {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	} 
	
	
	/**
	 * 获取用户信息 判断是否关注，获取关注信息
	 * 
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static JSONObject userinfo(String access_token, String openid) {
		String url = user_url + "access_token=" + access_token + "&openid="
				+ openid + "&lang=zh_CN";
		try {
			StringBuffer info = getJsonStringByConnection(url);
			JSONObject json = JSONObject.fromObject(info.toString());
			// System.out.println("userinfo = "+json.toString());
			return json;
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 上传
	 * @param request
	 * @param imgpath
	 * @return
	 */
	public static  String upload_img(HttpServletRequest request,Map<String, String> textMap ,Map<String, String> fileMap){
		JSONObject jsonObj = new JSONObject();
		String token = Wxutils.getToken(request);
		String url = upload_img+token+"&type=image";
		System.out.println("url");
		String result = formUpload(url,textMap,fileMap);
		System.out.println(result);
		return result;
		
	}
	
	
	
	/**
	 * 下载图片
	 * 
	 * @param mediaId
	 * @param savePath
	 * @return
	 */
	public static String downloadMedia(String mediaId, String savePath,String openid) {
		String filePath = null;
		// 拼接请求地址
		System.out.println(mediaId);
		try {
			URL url = new URL(mediaId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = getFileEndWitsh(conn
					.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			SimpleDateFormat formattxt = new SimpleDateFormat("yyyyMMddHHmmss");
			String todaystring = formattxt.format(new Date());
			if(StringUtils.isNotBlank(openid)){
				todaystring = openid;
			}
			filePath = savePath + todaystring + fileExt;

			BufferedInputStream bis = new BufferedInputStream(conn
					.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + filePath);
			System.out.println(info);

			filePath = todaystring + fileExt;
		} catch (Exception e) {
			filePath = null;
			e.printStackTrace();
		}
		return filePath;
	}
	
	
	
	/**
	 * 生成永久二维码
	 */
	public static JSONObject create_yongjiu_code(HttpServletRequest request, String json) {
		boolean debug = true;
		String wx_util_debug = SysConfig.getInstance().getProperty("wx_util_debug");
		if (StringUtils.isNotBlank(wx_util_debug) && wx_util_debug.equals("1")) {
			debug = false;
		}
		String token = Wxutils.getToken(request);
		String url = yongjiu_ewm + token;
		HttpPost httppost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			StringEntity s = new StringEntity(json.toString(), HTTP.UTF_8);
			s.setContentEncoding("utf-8");
			s.setContentType("application/json");
			httppost.setHeader("Content-Type",
					"application/json; charset=utf-8");
			httppost.setEntity(s);
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			String context = EntityUtils.toString(entity, HTTP.UTF_8);
			JSONObject result = JSONObject.fromObject(context);
			if (debug) {
				System.out.println("生成永久二维码返回的结果：" + result.toString());
			}
			Object errcode = result.get("errcode");
			if (null == errcode) {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String postFile(String url, String filePath, String title,
			String introduction) {
		File file = new File(filePath);
		if (!file.exists())
			return null;
		String result = null;
		try {
			URL url1 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");
			String boundary = "-----------------------------"+ System.currentTimeMillis();
			conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + boundary);
			OutputStream output = conn.getOutputStream();
			output.write(("--" + boundary + "\r\n").getBytes());
			output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n",file.getName()).getBytes());
			output.write("Content-Type: image/jpg \r\n\r\n".getBytes());
			byte[] data = new byte[1024];
			int len = 0;
			FileInputStream input = new FileInputStream(file);
			while ((len = input.read(data)) > -1) {
				output.write(data, 0, len);
			}
			output.write(("--" + boundary + "\r\n").getBytes());
			output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
			output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title,introduction).getBytes());
			output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
			output.flush();
			output.close();
			input.close();
			InputStream resp = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			while ((len = resp.read(data)) > -1)
				sb.append(new String(data, 0, len, "utf-8"));
			resp.close();
			result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	 

	public static String formUpload(String urlStr, Map<String, String> textMap,
			Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------"+ System.currentTimeMillis(); // boundary就是request头和上传文件内容的分隔符
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
			conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes("utf-8"));
			}

			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap().getContentType(file);
					if (filename.endsWith(".png")) {
						contentType = "image/png";
					} else if (filename.endsWith(".jpg")) {
						contentType = "image/jpeg";
					} else if (filename.endsWith(".mp4")) {
						contentType = "video/mpeg4";
					}
					if (contentType == null || contentType.equals("")) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append(
							"\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes("utf-8"));

					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		System.out.println(res);

		JSONObject jsonObj = JSONObject.fromObject(res);
		String mediaId = jsonObj.getString("media_id");
		return mediaId;
	}

	/**
	 * 创建菜单
	 * 
	 * @param request
	 * @param json
	 * @return
	 */
	public static boolean menuCreate(HttpServletRequest request, String json) {
		boolean flag = false;
		// String token =
		// "4lUEnttAXNVZFVF8_O5TmJUNBN_WVPjsm7_zQmKcSwctzWdNqMdGemEo8At7F5gpAtG1PDKnV53JLlz44xLVgPUJlHpeRXxiRHNzr3kRL48";
		String token = Wxutils.getToken(request);
		String url = menu_create + token;
		HttpPost httppost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			StringEntity s = new StringEntity(json.toString(), HTTP.UTF_8);
			s.setContentEncoding("utf-8");
			s.setContentType("application/json");
			httppost.setHeader("Content-Type",
					"application/json; charset=utf-8");
			httppost.setEntity(s);
			HttpResponse response = httpClient.execute(httppost);
			String content1 = EntityUtils.toString(response.getEntity());
			JSONObject result = JSONObject.fromObject(content1);
			Object errcode = result.get("errcode");
			System.out.println(result);
			if (null != errcode && Integer.parseInt(errcode.toString()) == 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("创建菜单失败");
		}
		return flag;
	}
	
	/**
	 * 删除菜单
	 * 
	 * @param request
	 * @return
	 */
	public static boolean memuDel(HttpServletRequest request) {
		boolean flag = false;
		// String token =
		// "4lUEnttAXNVZFVF8_O5TmJUNBN_WVPjsm7_zQmKcSwctzWdNqMdGemEo8At7F5gpAtG1PDKnV53JLlz44xLVgPUJlHpeRXxiRHNzr3kRL48";
		String token = Wxutils.getToken(request);
		String url = menu_del + token;
		try {
			StringBuffer info = getJsonStringByConnection(url);
			JSONObject json = JSONObject.fromObject(info.toString());
			Integer errcode = (Integer) json.get("errcode");
			if (null != errcode && errcode.intValue() == 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("删除菜单失败");
		}
		return flag;
	}
	
	/**
	 * 获取菜单
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject menuGet(HttpServletRequest request) {
		String token = Wxutils.getToken(request);
		String url = menu_get + token;
		try {
			StringBuffer info = getJsonStringByConnection(url);
			JSONObject json = JSONObject.fromObject(info.toString());
			Integer errcode = (Integer) json.get("errcode");
			// System.out.println(json.toString());
			if (null == errcode) {
				return json;
			}

		} catch (Exception e) {
			System.out.println("获取菜单失败");
		}
		return null;
	}
	 

	/**
	 * 用户列表
	 * 
	 * @param request
	 * @param next_openid
	 * @return
	 */
	public static JSONObject user_list(HttpServletRequest request,
			String next_openid) {
		String token = Wxutils.getToken(request);
		String url = user_list + "access_token=" + token;
		if (StringUtils.isNotBlank(next_openid)) {
			url = url + "&next_openid=" + next_openid;
		}
		try {
			StringBuffer info = getJsonStringByConnection(url);
			JSONObject json = JSONObject.fromObject(info.toString());
			Object errcode = json.get("errcode");
			// System.out.println(json.toString());
			if (null == errcode) {
				return json;
			}
		} catch (Exception e) {
		}
		return null;
	}
 

       
	public static int calLastedTime(Date startDate) {
		long a = new Date().getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}

	/**
	 * 写入文件
	 * 
	 * @param rootPath
	 * @param jsfile
	 * @param info
	 */
	public static void write_token(String rootPath, String jsfile, String info) {
		String filepath = rootPath + jsfile;
		File jsf = new File(filepath);
		if (jsf.exists()) {
			// System.out.println("写入的数据 ===" + info);
			write(filepath, info);
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param path
	 * @param line
	 */
	public static void write(String path, String line) {
		try {
			FileWriter writer = new FileWriter(path);
			PrintWriter pw = new PrintWriter(writer);
			pw.print(line);
			pw.flush();
			writer.close();
		} catch (IOException iox) {
			System.err.println(iox);
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> read(String path) {
		List<String> lines = new ArrayList<String>();
		File f = new File(path);
		if (f.exists()) {
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines;
	}

	/**
	 * 将token缓存到文件里面
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		System.out.print("进入获取token");
		boolean flag = false;
		String token = null;
		String rootPath = request.getSession().getServletContext().getRealPath(
				"/");
		String jsfile = "js/token.js";
		long now_time = new Date().getTime();
		List<String> lines = Wxutils.read(rootPath + jsfile);
		System.out.println(rootPath + jsfile);
		if (null != lines && lines.size() > 0) {
			String token_time = lines.get(0);
			System.out.println(token_time);
			token = token_time.split(",")[0];
			long time = Long.parseLong(token_time.split(",")[1]);
			System.out.println("获取token==" + token + "  获取time==" + time);
			System.out.println("(now_time - time) / 1000 =="
					+ (now_time - time) / 1000);
			System.out.println("(now_time - time) / 1000 > 110 * 60=="
					+ ((now_time - time) / 1000 > 110 * 60));
			if ((now_time - time) / 1000 > 110 * 60) {
				flag = true;
			}
		} else {
			flag = true;
		}
		if (flag) {
			token = Wxutils.getToken();
			if (null != token) {
				String info = token + "," + now_time;
				System.out.println("info == " + info);
				Wxutils.write_token(rootPath, jsfile, info);
			}
		}
		return token;
	}

	/**
	 * 将token缓存到文件里面
	 * 
	 * @param request
	 * @return
	 */
	 

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public static String getToken() {
		String debug = SysConfig.getInstance().getProperty("wx_util_get_token");
		if (debug.equals("1")) {
			try {
				StringBuffer info = getJsonStringByConnection(token_url);
				JSONObject json = JSONObject.fromObject(info.toString());
				String access_token = (String) json.get("access_token");
				if (null != access_token) {
					return access_token;
				}
			} catch (Exception e) {
			}
		} else {
			return "123456789";
		}

		return null;
	}
	

	/*********************** 用户管理 **************************************/

 


	/**
	 * @param urlString
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	protected static StringBuffer getJsonStringByConnection(String urlString)
			throws MalformedURLException, IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream urlStream = connection.getInputStream();
		connection.setConnectTimeout(2000);
		connection.setReadTimeout(5000);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(urlStream, Charset.forName("UTF-8")));
		StringBuffer sTotalString = new StringBuffer();
		String sCurrentLine = "";
		while ((sCurrentLine = bufferedReader.readLine()) != null) {
			sTotalString.append(sCurrentLine);
		}
		bufferedReader.close();
		connection.disconnect();
		return sTotalString;
	}

	/**
	 * 下载图片
	 * 
	 * @param mediaId
	 * @param savePath
	 * @return
	 */
	public static String downloadMedia(String mediaId, String savePath) {
		String filePath = null;
		// 拼接请求地址
		try {
			URL url = new URL(mediaId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = getFileEndWitsh(conn
					.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			SimpleDateFormat formattxt = new SimpleDateFormat("yyyyMMddHHmmss");
			String todaystring = formattxt.format(new Date());
			filePath = savePath + todaystring + fileExt;

			BufferedInputStream bis = new BufferedInputStream(conn
					.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + filePath);
			System.out.println(info);

			filePath = todaystring + fileExt;
		} catch (Exception e) {
			filePath = null;
			e.printStackTrace();
		}
		return filePath;
	}
	
	

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileEndWitsh(String contentType) {
		String fileEndWitsh = ".jpg";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param token
	 * @return
	 */
	public static String getTicket(String token) {
		String ticket = null;
		String url;
		url = ticket_url + "access_token=" + token + "&type=jsapi";
		try {
			StringBuffer info = getJsonStringByConnection(url);
			JSONObject json = JSONObject.fromObject(info.toString());
			Integer code = (Integer) json.get("errcode");
			if (code == 0) {
				ticket = (String) json.get("ticket");
			}
			// System.out.println("ticket==" + ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getTicket(HttpServletRequest request, String token) {
		// System.out.print("进入获取ticket");
		boolean flag = false;
		String ticket = null;
		String rootPath = request.getSession().getServletContext().getRealPath(
				"/");
		String jsfile = "js/ticket.js";
		long now_time = new Date().getTime();
		List<String> lines = Wxutils.read(rootPath + jsfile);
		// System.out.println(rootPath + jsfile);
		if (null != lines && lines.size() > 0) {
			String token_time = lines.get(0);
			// System.out.println(token_time);
			ticket = token_time.split(",")[0];
			long time = Long.parseLong(token_time.split(",")[1]);
			// System.out.println("获取ticket==" + ticket + "  获取time==" + time);
			if ((now_time - time) / 1000 > 110 * 60) {
				flag = true;
			}
		} else {
			flag = true;
		}
		if (flag) {
			ticket = Wxutils.getTicket(token);
			if (null != ticket) {
				String info = ticket + "," + now_time;
				Wxutils.write_token(rootPath, jsfile, info);
			}
		}
		return ticket;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		// System.out.println(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		// System.out.println("nonceStr=" + nonce_str);
		// System.out.println("timestamp=" + timestamp);
		// System.out.println("\n signature =" + signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
