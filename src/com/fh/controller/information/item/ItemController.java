package com.fh.controller.information.item;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.information.Com;
import com.fh.entity.system.User;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.service.information.company.CompanyService;
import com.fh.service.information.good.GoodService;
import com.fh.service.information.goodimages.GoodimagesService;
import com.fh.service.information.item.ItemService;
/**
 * 题目设置
 * 功能：
 * 作者： lj
 * date：2017-4-22
 *
 */
@Controller
@RequestMapping(value="/item")
public class ItemController extends BaseController {
	public static final String GOODIMGPATH = "good/";	//图片上传路径
	public static final String itemImages = "item/";	//图片上传路径
	
	@Resource(name="goodService")
	private GoodService goodService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	@Resource(name="goodimagesService")
	private GoodimagesService goodimagesService;
	
	@Resource(name="itemService")
	private ItemService itemService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/itemlist")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Item");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	itemList = itemService.itemlistPage(page);
			mv.setViewName("information/item/item_list");
			mv.addObject("itemList", itemList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(
			) throws Exception{
		logBefore(logger, "修改题目");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			pd=itemService.findById(pd);
		}catch(Exception e){
			logBefore(logger, "修改失败："+e.getMessage());
		}
		mv.addObject("pd", pd);
		mv.addObject("msg", "update");
		mv.setViewName("information/item/item_edit");
		return mv;
	}
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(
			@RequestParam(value="imageFile1",required=false) MultipartFile imageFile1,
			@RequestParam(value="imageFile2",required=false) MultipartFile imageFile2,
			@RequestParam(value="serial",required=false) String serial,
			@RequestParam(value="item_name",required=false) String item_name,
			@RequestParam(value="right_answer",required=false) String right_answer
			) throws Exception{
		logBefore(logger, "新增Item");
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);//获取当前登录者
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("item_id", this.get32UUID());
		pd.put("serial", serial);
		pd.put("item_name", item_name);
		pd.put("right_answer", right_answer);
		pd.put("item_time", Tools.date2Str(new Date()));	//添加时间
		try{
			//图片上传
			String  ffile = DateUtil.getDays(), fileName = "",fileName2="";
			if (null != imageFile1 && !imageFile1.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + itemImages + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(imageFile1, filePath, this.get32UUID());	
				pd.put("image1", Const.FILEPATHIMG + itemImages + ffile + "/" + fileName);//执行上传
			}else{
				logBefore(logger, "--未上传图片--");
			}
			if(null != imageFile2 && !imageFile2.isEmpty()){
				String filePath2 = PathUtil.getClasspath() + Const.FILEPATHIMG + itemImages + ffile;		//文件上传路径
				fileName2 = FileUpload.fileUp(imageFile2, filePath2, this.get32UUID());	
				pd.put("image2", Const.FILEPATHIMG + itemImages + ffile + "/" + fileName2);//执行上传
			}else{
				logBefore(logger, "--未上传图片--");
			}
			//保存并添加图片
			itemService.save(pd);
		}catch(Exception e){
			logBefore(logger, "添加失败:"+e.getMessage());
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/update")
	public ModelAndView upload(
			@RequestParam(required=false) MultipartFile imageFile1,
			@RequestParam(required=false) MultipartFile imageFile2,
			@RequestParam(required=false) String item_name,
			@RequestParam(required=false) String right_answer,
			@RequestParam(required=false) String image1,
			@RequestParam(required=false) String image2,
			@RequestParam(required=false) String item_id,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		logBefore(logger, "执行修改");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		System.out.println("==============="+imageFile1);
		System.out.println("==============="+imageFile2);
		if (null != imageFile1 && !imageFile1.isEmpty()){
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + itemImages + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(imageFile1, filePath, this.get32UUID());				//执行上传
			pd.put("image1", Const.FILEPATHIMG + itemImages + ffile+"/"+fileName);		
		}else{
			pd.put("image1", image1);		
		}
		if (null != imageFile2 && !imageFile2.isEmpty()){
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + itemImages + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(imageFile2, filePath, this.get32UUID());		
			pd.put("image2", Const.FILEPATHIMG + itemImages + ffile+"/"+fileName);	//执行上传
		}else{
			
			pd.put("image2", image2);		
		}
		
		pd.put("item_id", item_id);
		pd.put("item_name", item_name);
		pd.put("right_answer", right_answer);
		itemService.update(pd);
		//加水印
		//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/del")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Item");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			itemService.del(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	//删除图片
	@RequestMapping(value="/deltp")
	public void deltp(PrintWriter out) {
		logBefore(logger, "删除图片");
		try{
			ModelAndView mv = new ModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			String num = pd.getString("num");	
			//图片路径
			String tpurl = pd.getString("image1");	
			String tpurl2 = pd.getString("image2");	
			if(num.equals("1")){
				pd.put("image1", tpurl);
				itemService.delTp(pd);
			}else{
				pd.put("image2", tpurl2);
				itemService.delTp2(pd);
			}
														//图片路径
			if(tpurl != null){
				//删除硬盘上的文件 start
				String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
				tpurl = xmpath.trim() + Const.FILEPATHIMG + itemImages + tpurl.trim();
				System.out.println("------tpurl-------"+tpurl);														//去掉 'file:/'
				File f = new File(tpurl); 
				if(f.exists()){
					f.delete();
				}else{
					System.out.println("===="+tpurl+"不存在");
				}
				//删除硬盘上的文件 end
			}	
			
			if(tpurl2 != null){
				//删除硬盘上的文件 start
				String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
				tpurl = xmpath.trim() + Const.FILEPATHIMG + itemImages + tpurl.trim();
				System.out.println("------tpurl-------"+tpurl2);														//去掉 'file:/'
				File f = new File(tpurl2); 
				if(f.exists()){
					f.delete();
				}else{
					System.out.println("===="+tpurl2+"不存在");
				}
				//删除硬盘上的文件 end
			}	
							
				out.write("success");
				out.close();
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增item页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("information/item/item_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Good页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<Com> varComs = this.companyService.listAllCom(pd);
			pd = goodService.findById(pd);	//根据ID读取
			mv.setViewName("information/good/good_edit");
			mv.addObject("varComs", varComs);
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Good");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				goodService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Good到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("商家ID");	//1
			titles.add("商品名称");	//2
			titles.add("类型");	//3
			titles.add("原价");	//4
			titles.add("折扣价");	//5
			titles.add("商品套餐");	//6
			titles.add("购买须知");	//7
			titles.add("商品描述");	//8
			titles.add("简介");	//9
			titles.add("状态");	//10
			titles.add("添加人");	//11
			titles.add("添加时间");	//12
			dataMap.put("titles", titles);
			List<PageData> varOList = goodService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("COMID"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).get("TYPE").toString());	//3
				vpd.put("var4", varOList.get(i).getString("ORIGINALPRICE"));	//4
				vpd.put("var5", varOList.get(i).getString("DISCOUNTPRICE"));	//5
				vpd.put("var6", varOList.get(i).getString("PACKAGE"));	//6
				vpd.put("var7", varOList.get(i).getString("BUYNOTICE"));	//7
				vpd.put("var8", varOList.get(i).getString("DESCRIBE"));	//8
				vpd.put("var9", varOList.get(i).getString("DETAIL"));	//9
				vpd.put("var10", varOList.get(i).get("STATUS").toString());	//10
				vpd.put("var11", varOList.get(i).getString("ADDUSER"));	//11
				vpd.put("var12", varOList.get(i).getString("ADDTIME"));	//12
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 获取商家
	 */
	public PageData getCom(String id) throws Exception{
		logBefore(logger, "getCom");
		PageData pd = new PageData();
		pd = this.getPageData();

		if(id==null||id.equals("")){
        	return null;
        }else{
        	pd.put("COMPANY_ID", id);
    		PageData com = this.companyService.findById(pd);
    		
    		return com;
        }
	}
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
