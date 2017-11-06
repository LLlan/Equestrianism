package com.fh.test;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import net.coobird.thumbnailator.Thumbnails;



public class CutImg {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		/**
		 * 如果有旋转的话，先进行旋转，然后在进行裁剪
		 * //1.先旋转
		 * Thumbnails.of("d:\\1.jpg").scale(1).rotate(-45).toFile("d:\\11.jpg");
		 * //2.在裁剪
		 *Thumbnails.of("d:\\11.jpg").sourceRegion(678, 407, 216, 207)
			.size(139, 133).keepAspectRatio(false)
			.toFile("d:\\12.jpg");
		 */
		 //System.out.println(new File("d:\\1.jpg"));
	     BufferedImage sourceImage = ImageIO.read(new File("d:\\1.jpg"));  
         BufferedImage dstImage = null;  
         // AffineTransform transform = new AffineTransform(-1, 0, 0, 1,  
         // sourceImage.getWidth(), 0);// 水平翻转  
         AffineTransform transform = new AffineTransform(1, 0, 0, -1, 0,  
               sourceImage.getHeight());// 垂直翻转  
         // AffineTransform transform = new AffineTransform(-1, 0, 0, -1,  
         // sourceImage.getWidth(), sourceImage.getHeight());// 旋转180度  
         AffineTransformOp op = new AffineTransformOp(transform,  
               AffineTransformOp.TYPE_BILINEAR);  
         dstImage = op.filter(sourceImage, null);  
 
         JTabbedPane tabbedPane = new JTabbedPane();  
         tabbedPane.add("Source Transform", new JLabel(  
               new ImageIcon(sourceImage)));  
         tabbedPane.add("Affine Transform", new JLabel(new ImageIcon(dstImage)));  
 
         JFrame jframe = new JFrame();  
         jframe.setSize(800, 600);  
         jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
         jframe.getContentPane().add(tabbedPane);  
         jframe.setVisible(true);  
		
		
	}

	 /** 获得文件的绝对地址 */  
     public static final URL getURL(String path) {  
         return "".getClass().getResource(path);  
     }  
}
