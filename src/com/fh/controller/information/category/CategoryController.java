package com.fh.controller.information.category;

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
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.service.information.category.CategoryService;

/** 
 * 类名称：CategoryController
 * 创建人：FH 
 * 创建时间：2016-07-08
 */
@Controller
@RequestMapping(value="/category")
public class CategoryController extends BaseController {
	public static final String IMGPATH = "category/";	//图片上传路径
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(
			HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile tp,
			@RequestParam(value="NAME",required=false) String NAME,
			@RequestParam(value="DETAIL",required=false) String DETAIL,
			@RequestParam(value="STATUS",required=false) String STATUS
			) throws Exception{
		logBefore(logger, "新增Category");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("NAME", NAME);
		pd.put("DETAIL", DETAIL);
		pd.put("STATUS", STATUS);
		
		//pd.put("CATEGORY_ID", this.get32UUID());	//主键
		pd.put("INSTIME", Tools.date2Str(new Date()));	//插入时间
		
		try{
			//图片上传
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != tp && !tp.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(tp, filePath, this.get32UUID());	
				pd.put("IMGURL", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);//执行上传
			}else{
				logBefore(logger, "上传失败");
				pd.put("IMGURL", "");
			}
			//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			categoryService.save(pd);
		}catch(Exception e){
			logBefore(logger, "添加失败:"+e.getMessage());
		}

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Category");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			categoryService.delete(pd);
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
				
				String tpurl = pd.getString("tpurl");													//图片路径
				if(tpurl != null){
					//删除硬盘上的文件 start
					String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
					tpurl = xmpath.trim() + "TP/" + tpurl.trim();
					tpurl = tpurl.substring(6);															//去掉 'file:/'
					File f = new File(tpurl.trim()); 
					if(f.exists()){
						f.delete();
					}else{
						System.out.println("===="+tpurl+"不存在");
					}
					//删除硬盘上的文件 end
					categoryService.delTp(pd);														//删除数据中图片数据
				}	
					
					out.write("success");
					out.close();
			}catch(Exception e){
				logger.error(e.toString(), e);
			}
		}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(
			HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile tp,
			@RequestParam(value="tpz",required=false) String tpz,
			@RequestParam(value="CATEGORY_ID",required=false) String CATEGORY_ID,
			@RequestParam(value="NAME",required=false) String NAME,
			@RequestParam(value="DETAIL",required=false) String DETAIL,
			@RequestParam(value="STATUS",required=false) String STATUS
			) throws Exception{
		logBefore(logger, "修改Category");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("NAME", NAME);
		pd.put("DETAIL", DETAIL);
		pd.put("STATUS", STATUS);
		
		pd.put("CATEGORY_ID", CATEGORY_ID);	//主键
		
		if(null == tpz){
			tpz = "";
		}
		try{
			//图片上传
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != tp && !tp.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(tp, filePath, this.get32UUID());	
				pd.put("IMGURL", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);//执行上传
			}else{
				logBefore(logger, "上传失败");
				pd.put("IMGURL", tpz);
			}		
			//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			categoryService.edit(pd);
		}catch(Exception e){
			logBefore(logger, "修改失败："+e.getMessage());
		}

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Category");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			//检索条件================================
			String KEYW = pd.getString("keyword");
			String STATUS = pd.getString("STATUS");
			logBefore(logger, "STATUS"+STATUS);
			logBefore(logger, "KEYW"+KEYW);
			if(null != KEYW && !"".equals(KEYW)){
				KEYW = KEYW.trim();
				pd.put("KEYW", KEYW);
			}
			if(null != STATUS && !"".equals(STATUS)){
				STATUS = STATUS.trim();
				pd.put("CATEGORY", STATUS);
			}
			
			//检索条件================================
			
			page.setPd(pd);
			List<PageData>	varList = categoryService.list(page);	//列出Category列表
			mv.setViewName("information/category/category_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Category页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("information/category/category_edit");
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
		logBefore(logger, "去修改Category页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = categoryService.findById(pd);	//根据ID读取
			mv.setViewName("information/category/category_edit");
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
		logBefore(logger, "批量删除Category");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				categoryService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Category到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("类别名称");	//1
			titles.add("图片");	//2
			titles.add("描述");	//3
			titles.add("状态");	//4
			titles.add("插入时间");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = categoryService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NAME"));	//1
				vpd.put("var2", varOList.get(i).getString("IMGURL"));	//2
				vpd.put("var3", varOList.get(i).getString("DETAIL"));	//3
				vpd.put("var4", varOList.get(i).get("STATUS").toString());	//4
				vpd.put("var5", varOList.get(i).getString("INSTIME"));	//5
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
