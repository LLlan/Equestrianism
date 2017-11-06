package com.fh.controller.information.comimages;

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
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.service.information.comimages.ComimagesService;
import com.fh.service.information.company.CompanyService;

/** 
 * 类名称：ComimagesController
 * 创建人：FH 
 * 创建时间：2016-08-11
 */
@Controller
@RequestMapping(value="/comimages")
public class ComimagesController extends BaseController {
	public static final String COMIMGPATH = "company/";	//图片上传路径
	@Resource(name="comimagesService")
	private ComimagesService comimagesService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Comimages");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("COMIMAGES_ID", this.get32UUID());	//主键
		pd.put("COMID", "");	//商家ID
		pd.put("IMGURL", "");	//图片
		comimagesService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 图片上传
	 */
	@RequestMapping(value="/upload")
	@ResponseBody
	public Object upload(
			@RequestParam(required=false) MultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		logBefore(logger, "新增upload");
		String uid=request.getParameter("uid");//获取uid
		String pid=request.getParameter("pid");//获取jsp id参数
		System.out.println("sdfsdfsdf"+pid);
		logBefore(logger, "uid"+uid);
		logBefore(logger, "pid"+pid);
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + COMIMGPATH + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
		}else{
			System.out.println("上传失败");
		}
		
		PageData pd = new PageData();
		
		//pd.put("COMIMAGES_ID", this.get32UUID());			//主键
		pd.put("COMID", pid);								//商家ID	
		pd.put("IMGURL", Const.FILEPATHIMG + COMIMGPATH + ffile + "/" + fileName);				//路径
		//加水印
		//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
		comimagesService.save(pd);
		
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Comimages");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			comimagesService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Comimages");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		comimagesService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Comimages");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = comimagesService.list(page);	//列出Comimages列表
			mv.setViewName("information/comimages/comimages_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/imagesList")
	public ModelAndView imagesList(){
		logBefore(logger, "列表imagesList");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData>	varList = comimagesService.findByComId(pd);	//列出Images列表
			List<PageData>	varListn = new ArrayList<PageData>();
			if(varList.size()>0){
				for(int i=0;i<varList.size();i++){
					PageData images = new PageData();
					images.put("id", varList.get(i).get("COMIMAGES_ID").toString());
					images.put("name", this.getName(varList.get(i).get("COMID").toString()));
					images.put("image", varList.get(i).get("IMGURL").toString());
					
					varListn.add(images);
				}
			}
			mv.setViewName("information/comimages/comimages_list");
			mv.addObject("varList", varListn);
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
		logBefore(logger, "去新增Comimages页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("information/comimages/comimages_add");
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
		logBefore(logger, "去修改Comimages页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = comimagesService.findById(pd);	//根据ID读取
			mv.setViewName("information/comimages/comimages_edit");
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
		logBefore(logger, "批量删除Comimages");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				comimagesService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Comimages到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("商家ID");	//1
			titles.add("图片");	//2
			dataMap.put("titles", titles);
			List<PageData> varOList = comimagesService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("COMID"));	//1
				vpd.put("var2", varOList.get(i).getString("IMGURL"));	//2
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
	 * 修改
	 */
	public String getName(String comId) throws Exception{
		logBefore(logger, "getName");
		PageData pd = new PageData();
		pd = this.getPageData();

		pd.put("COMPANY_ID", comId);
		PageData company = this.companyService.findById(pd);
		
		if(company!=null){
		    return company.getString("NAME");
		}else{
			return "";
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
