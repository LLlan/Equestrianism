package com.fh.controller.information.company;

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
import com.fh.entity.information.Cate;
import com.fh.entity.system.User;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.service.information.category.CategoryService;
import com.fh.service.information.comimages.ComimagesService;
import com.fh.service.information.company.CompanyService;

/** 
 * 类名称：CompanyController
 * 创建人：FH 
 * 创建时间：2016-08-10
 */
@Controller
@RequestMapping(value="/company")
public class CompanyController extends BaseController {
	public static final String COMIMGPATH = "company/";	//图片上传路径
	public static final String COMLOGOIMGPATH = "company_logo/";	//图片上传路径
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	@Resource(name="comimagesService")
	private ComimagesService comimagesService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(
			HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile tp,
			@RequestParam(value="COMPANY_ID",required=false) String COMPANY_ID,
			@RequestParam(value="NAME",required=false) String NAME,
			@RequestParam(value="LONGITUDE",required=false) String LONGITUDE,
			@RequestParam(value="LATITUDE",required=false) String LATITUDE,
			@RequestParam(value="TYPE",required=false) String TYPE,
			@RequestParam(value="SALESTIME",required=false) String SALESTIME,
			@RequestParam(value="TELEPHONE",required=false) String TELEPHONE,
			@RequestParam(value="WEBSITE",required=false) String WEBSITE,
			@RequestParam(value="ADDRESS",required=false) String ADDRESS,
			@RequestParam(value="DESCRIBES",required=false) String DESCRIBES,
			@RequestParam(value="INTRODUCE",required=false) String INTRODUCE,
			@RequestParam(value="CONFIGURE",required=false) String CONFIGURE,
			@RequestParam(value="ISHOME",required=false) String ISHOME,
			@RequestParam(value="ISREC",required=false) String ISREC,
			@RequestParam(value="STATUS",required=false) String STATUS
			) throws Exception{
		logBefore(logger, "新增Company");
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);//获取当前登录者
				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("COMPANY_ID", COMPANY_ID);
		pd.put("NAME", NAME);
		pd.put("LONGITUDE", LONGITUDE);
		pd.put("LATITUDE", LATITUDE);
		pd.put("TYPE", TYPE);
		pd.put("SALESTIME", SALESTIME);
		pd.put("TELEPHONE", TELEPHONE);
		pd.put("WEBSITE", WEBSITE);
		pd.put("ADDRESS", ADDRESS);
		pd.put("DESCRIBES", DESCRIBES);
		pd.put("INTRODUCE", INTRODUCE);
		pd.put("CONFIGURE", CONFIGURE);
		pd.put("ISHOME", ISHOME);
		pd.put("ISREC", ISREC);
		pd.put("STATUS", STATUS);
		
		//pd.put("COMPANY_ID", this.get32UUID());	//主键
		pd.put("ADDUSER", user.getNAME());	//添加人
		pd.put("ADDTIME", Tools.date2Str(new Date()));	//添加时间
		
		try{
			//图片上传
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != tp && !tp.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + COMLOGOIMGPATH + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(tp, filePath, this.get32UUID());	
				pd.put("LOGOURL", Const.FILEPATHIMG + COMLOGOIMGPATH + ffile + "/" + fileName);//执行上传
			}else{
				logBefore(logger, "上传失败");
				pd.put("LOGOURL", "");
			}
			//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			companyService.save(pd);
		}catch(Exception e){
			logBefore(logger, "添加失败:"+e.getMessage());
		}

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
		logBefore(logger, "删除Company");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			companyService.delete(pd);
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
				companyService.delTp(pd);														//删除数据中图片数据
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
			@RequestParam(value="COMPANY_ID",required=false) String COMPANY_ID,
			@RequestParam(value="NAME",required=false) String NAME,
			@RequestParam(value="LONGITUDE",required=false) String LONGITUDE,
			@RequestParam(value="LATITUDE",required=false) String LATITUDE,
			@RequestParam(value="TYPE",required=false) String TYPE,
			@RequestParam(value="SALESTIME",required=false) String SALESTIME,
			@RequestParam(value="TELEPHONE",required=false) String TELEPHONE,
			@RequestParam(value="WEBSITE",required=false) String WEBSITE,
			@RequestParam(value="ADDRESS",required=false) String ADDRESS,
			@RequestParam(value="DESCRIBES",required=false) String DESCRIBES,
			@RequestParam(value="INTRODUCE",required=false) String INTRODUCE,
			@RequestParam(value="CONFIGURE",required=false) String CONFIGURE,
			@RequestParam(value="ISHOME",required=false) String ISHOME,
			@RequestParam(value="ISREC",required=false) String ISREC,
			@RequestParam(value="STATUS",required=false) String STATUS
			) throws Exception{
		logBefore(logger, "修改Company");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("NAME", NAME);
		pd.put("LONGITUDE", LONGITUDE);
		pd.put("LATITUDE", LATITUDE);
		pd.put("TYPE", TYPE);
		pd.put("SALESTIME", SALESTIME);
		pd.put("TELEPHONE", TELEPHONE);
		pd.put("WEBSITE", WEBSITE);
		pd.put("ADDRESS", ADDRESS);
		pd.put("DESCRIBES", DESCRIBES);
		pd.put("INTRODUCE", INTRODUCE);
		pd.put("CONFIGURE", CONFIGURE);
		pd.put("ISHOME", ISHOME);
		pd.put("ISREC", ISREC);
		pd.put("STATUS", STATUS);
		
		pd.put("COMPANY_ID", COMPANY_ID);
		
		if(null == tpz){
			tpz = "";
		}
		try{
			//图片上传
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != tp && !tp.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + COMLOGOIMGPATH + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(tp, filePath, this.get32UUID());	
				pd.put("LOGOURL", Const.FILEPATHIMG + COMLOGOIMGPATH + ffile + "/" + fileName);//执行上传
			}else{
				logBefore(logger, "上传失败");
				pd.put("LOGOURL", tpz);
			}		
			//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			companyService.edit(pd);
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
		logBefore(logger, "列表Company");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			//检索条件================================
			String KEYW = pd.getString("keyword");
			String CATEGORY = pd.getString("CATEGORY");
			
			logBefore(logger, "CATEGORY"+CATEGORY);
			if(null != KEYW && !"".equals(KEYW)){
				KEYW = KEYW.trim();
				pd.put("KEYW", KEYW);
			}
			if(null != CATEGORY && !"".equals(CATEGORY)){
				CATEGORY = CATEGORY.trim();
				pd.put("CATEGORY", CATEGORY);
			}
			
			//检索条件================================
			
			page.setPd(pd);
			
			List<Cate> varCates = this.categoryService.findAllCate();
			
			List<PageData>	varListn = companyService.list(page);	//列出Company列表
			List<PageData>	varList = new ArrayList<PageData>();
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData company = new PageData();
					company.put("id", varListn.get(i).get("COMPANY_ID").toString());
					company.put("name", varListn.get(i).get("NAME").toString());
					company.put("cate", this.getCate(varListn.get(i).get("TYPE").toString())==null?"":this.getCate(varListn.get(i).get("TYPE").toString()).get("NAME").toString());
					company.put("salestime", varListn.get(i).get("SALESTIME").toString());
					company.put("telephone", varListn.get(i).get("TELEPHONE").toString());
					company.put("address", varListn.get(i).get("ADDRESS").toString());
					company.put("isHome", varListn.get(i).get("ISHOME").toString());
					company.put("isRec", varListn.get(i).get("ISREC").toString());
					company.put("status", varListn.get(i).get("STATUS").toString());
					company.put("logo", varListn.get(i).get("LOGOURL").toString());
					company.put("user", varListn.get(i).get("ADDUSER").toString());
					company.put("time", varListn.get(i).get("ADDTIME")==null?"":varListn.get(i).get("ADDTIME").toString());
					
					varList.add(company);
				}
			}
			
			mv.setViewName("information/company/company_list");
			mv.addObject("varList", varList);
			mv.addObject("varCates", varCates);
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
		logBefore(logger, "去新增Company页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<Cate> varCates = this.categoryService.findAllCate();
			//生成COMPANY_ID
			pd.put("COMPANY_ID", this.get32UUID());
			
			mv.setViewName("information/company/company_add");
			mv.addObject("varCates", varCates);
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
		logBefore(logger, "去修改Company页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<Cate> varCates = this.categoryService.findAllCate();
			
			pd = companyService.findById(pd);	//根据ID读取
			mv.setViewName("information/company/company_edit");
			mv.addObject("varCates", varCates);
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
		logBefore(logger, "批量删除Company");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				companyService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Company到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("商家名称");	//1
			titles.add("经度");	//2
			titles.add("纬度");	//3
			titles.add("商家类型");	//4
			titles.add("营业时间");	//5
			titles.add("联系电话");	//6
			titles.add("网址");	//7
			titles.add("图片");	//8
			titles.add("商家地址");	//9
			titles.add("商家介绍");	//10
			titles.add("商家配置");	//11
			titles.add("添加人");	//12
			titles.add("添加时间");	//13
			dataMap.put("titles", titles);
			List<PageData> varOList = companyService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NAME"));	//1
				vpd.put("var2", varOList.get(i).getString("LONGITUDE"));	//2
				vpd.put("var3", varOList.get(i).getString("LATITUDE"));	//3
				vpd.put("var4", varOList.get(i).get("TYPE").toString());	//4
				vpd.put("var5", varOList.get(i).getString("SALESTIME"));	//5
				vpd.put("var6", varOList.get(i).getString("TELEPHONE"));	//6
				vpd.put("var7", varOList.get(i).getString("WEBSITE"));	//7
				vpd.put("var8", varOList.get(i).getString("LOGOURL"));	//8
				vpd.put("var9", varOList.get(i).getString("ADDRESS"));	//9
				vpd.put("var10", varOList.get(i).getString("INTRODUCE"));	//10
				vpd.put("var11", varOList.get(i).getString("CONFIGURE"));	//11
				vpd.put("var12", varOList.get(i).getString("ADDUSER"));	//12
				vpd.put("var13", varOList.get(i).getString("ADDTIME"));	//13
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
	 * 获取类别
	 */
	public PageData getCate(String id) throws Exception{
		logBefore(logger, "getCate");
		PageData pd = new PageData();
		pd = this.getPageData();

		if(id==null||id.equals("")){
        	return null;
        }else{
        	pd.put("CATEGORY_ID", id);
    		PageData cate = this.categoryService.findById(pd);
    		
    		return cate;
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
