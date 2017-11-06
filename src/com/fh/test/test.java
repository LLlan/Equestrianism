package com.fh.test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.text.DefaultEditorKit.CutAction;

import com.fh.util.MD5;

public class test {

	/**
	 * 功能：
	 * 作者：lj
	 * 日期：2017-4-5
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*// TODO Auto-generated method stub
		int x=(int)(Math.random()*20);
		System.out.println("-------x是------"+x);
		String string ="6";
		while (string.equals("6")) {
			if(true){
				System.out.println("===============sfsf=================");
				x = new Random().nextInt(20)+1;
				System.out.println("===============sfsf================="+x);
				break;
			}
		
		}*/
		//System.out.println(MD5.md5("asd123"));
		/*String ss="5万以下";
		System.out.println(ss.indexOf("万以下dd"));
		System.out.println(Integer.parseInt(ss.replaceAll("万以下", ""))*10000);
		String str = "123abc你好efc";
*/
    	//String reg = "[\u4e00-\u9fa5]";
    	
    	//Pattern pat = Pattern.compile(reg);  

    	//Matcher mat=pat.matcher(str); 
    	/*String repickStr = Pattern.compile("[\u4e00-\u9fa5]").matcher(str).replaceAll("");
    	String aa[]=repickStr.split(",");
    	System.out.println(aa[0]);
    	System.out.println("去中文后:"+repickStr);*/
		
		/*String name="sdfsdf"+(int)((Math.random()*9+1)*100000);
		int mobile_code = (int)((Math.random()*9+1)*100000);
		System.out.println(name);*/
		
		//实现图片裁剪
		String srcpath = "d:\\1.jpg";  //原图路径
		String subpath = "d:\\11.jpg";  //新图路径
		cut(srcpath, subpath, 456, 558, 257, 257);
	}
	/** 
     *  
     * 对图片裁剪，并把裁剪完蛋新图片保存 。 
     */  
  
    public static void cut(String srcpath,String subpath,int x, int y, int width,int height ) throws IOException {  
        FileInputStream is = null;  
        ImageInputStream iis = null;  
        try {  
            // 读取图片文件  
            is = new FileInputStream(srcpath);  
  
            /** 
             *  
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 
             *  
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 . 
             *  
             * (例如 "jpeg" 或 "tiff")等 。 
             */  
            Iterator<ImageReader> it = ImageIO  
                    .getImageReadersByFormatName("jpg");  
  
            ImageReader reader = it.next();  
  
            // 获取图片流  
            iis = ImageIO.createImageInputStream(is);  
  
            /** 
             *  
             * <p> 
             * iis:读取源。true:只向前搜索 
             * </p> 
             * .将它标记为 ‘只向前搜索’。 
             *  
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 
             *  
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。 
             */  
            reader.setInput(iis, true);  
  
            /** 
             *  
             * <p> 
             * 描述如何对流进行解码的类 
             * <p> 
             * .用于指定如何在输入时从 Java Image I/O 
             *  
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 
             *  
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回 
             *  
             * ImageReadParam 的实例。 
             */  
            ImageReadParam param = reader.getDefaultReadParam();  
  
            /** 
             *  
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象 
             *  
             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。 
             */  
            Rectangle rect = new Rectangle(x, y, width, height);  
  
            // 提供一个 BufferedImage，将其用作解码像素数据的目标。  
            param.setSourceRegion(rect);  
  
            /** 
             *  
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 
             *  
             * 它作为一个完整的 BufferedImage 返回。 
             */  
            BufferedImage bi = reader.read(0, param);  
  
            // 保存新图片  
            ImageIO.write(bi, "jpg", new File(subpath));  
        } finally {  
            if (is != null)  
                is.close();  
            if (iis != null)  
                iis.close();  
        }  
  
    }  
}
