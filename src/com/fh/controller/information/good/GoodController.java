package com.fh.controller.information.good;

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

/** 
 * 类名称：GoodController
 * 创建人：FH 
 * 创建时间：2016-08-17
 */
@Controller
@RequestMapping(value="/good")
public class GoodController extends BaseController {
	public static final String GOODIMGPATH = "good/";	//图片上传路径
	public static final String GOODLOGOIMGPATH = "good_logo/";	//图片上传路径
	
	@Resource(name="goodService")
	private GoodService goodService;
	
	@Resource(name="companyService")
	private CompanyService companyService;
	
	@Resource(name="goodimagesService")
	private GoodimagesService goodimagesService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(
			@RequestParam(value="tp",required=false) MultipartFile tp,
			@RequestParam(value="GOOD_ID",required=false) String GOOD_ID,
			@RequestParam(value="COMID",required=false) String COMID,
			@RequestParam(value="NAME",required=false) String NAME,
			@RequestParam(value="TYPE",required=false) String TYPE,
			@RequestParam(value="ORIGINALPRICE",required=false) String ORIGINALPRICE,
			@RequestParam(value="DISCOUNTPRICE",required=false) String DISCOUNTPRICE,
			@RequestParam(value="PACKAGE",required=false) String PACKAGE,
			@RequestParam(value="BUYNOTICE",required=false) String BUYNOTICE,
			@RequestParam(value="DESCRIBES",required=false) String DESCRIBES,
			@RequestParam(value="DETAIL",required=false) String DETAIL,
			@RequestParam(value="ISHOME",required=false) String ISHOME,
			@RequestParam(value="ISREC",required=false) String ISREC,
			@RequestParam(value="STATUS",required=false) String STATUS
			) throws Exception{
		logBefore(logger, "新增Good");
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);//获取当前登录者
				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData comData = this.getCom(COMID);
		pd.put("GOOD_ID", GOOD_ID);
		pd.put("COMID", COMID);
		pd.put("NAME", NAME);
		pd.put("TYPE", comData.get("TYPE").toString());
		pd.put("ORIGINALPRICE", ORIGINALPRICE);
		pd.put("DISCOUNTPRICE", DISCOUNTPRICE);
		pd.put("PACKAGE", PACKAGE);
		pd.put("BUYNOTICE", BUYNOTICE);
		pd.put("DESCRIBES", DESCRIBES);
		pd.put("DETAIL", DETAIL);
		pd.put("ISHOME", ISHOME);
		pd.put("ISREC", ISREC);
		pd.put("STATUS", STATUS);
		
		//pd.put("GOOD_ID", this.get32UUID());	//主键
		pd.put("ADDUSER", user.getNAME());	//添加人
		pd.put("ADDTIME", Tools.date2Str(new Date()));	//添加时间
		
		try{
			//图片上传
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != tp && !tp.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + GOODLOGOIMGPATH + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(tp, filePath, this.get32UUID());	
				pd.put("IMGURL", Const.FILEPATHIMG + GOODLOGOIMGPATH + ffile + "/" + fileName);//执行上传
			}else{
				logBefore(logger, "上传失败");
				pd.put("IMGURL", "");
			}
			//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			goodService.save(pd);
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
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + GOODIMGPATH + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
		}else{
			System.out.println("上传失败");
		}
		
		PageData pd = new PageData();
		
		//pd.put("COMIMAGES_ID", this.get32UUID());			//主键
		pd.put("GOODID", pid);								//商家ID	
		pd.put("IMGURL", Const.FILEPATHIMG + GOODIMGPATH + ffile + "/" + fileName);				//路径
		//加水印
		//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
		goodimagesService.save(pd);
		
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Good");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			goodService.delete(pd);
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
				goodService.delTp(pd);														//删除数据中图片数据
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
			@RequestParam(value="tp",required=false) MultipartFile tp,
			@RequestParam(value="tpz",required=false) String tpz,
			@RequestParam(value="GOOD_ID",required=false) String GOOD_ID,
			@RequestParam(value="COMID",required=false) String COMID,
			@RequestParam(value="NAME",required=false) String NAME,
			@RequestParam(value="TYPE",required=false) String TYPE,
			@RequestParam(value="ORIGINALPRICE",required=false) String ORIGINALPRICE,
			@RequestParam(value="DISCOUNTPRICE",required=false) String DISCOUNTPRICE,
			@RequestParam(value="PACKAGE",required=false) String PACKAGE,
			@RequestParam(value="BUYNOTICE",required=false) String BUYNOTICE,
			@RequestParam(value="DESCRIBES",required=false) String DESCRIBES,
			@RequestParam(value="DETAIL",required=false) String DETAIL,
			@RequestParam(value="ISHOME",required=false) String ISHOME,
			@RequestParam(value="ISREC",required=false) String ISREC,
			@RequestParam(value="STATUS",required=false) String STATUS
			) throws Exception{
		logBefore(logger, "修改Good");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData comData = this.getCom(COMID);
		pd.put("COMID", COMID);
		pd.put("NAME", NAME);
		pd.put("TYPE", comData.get("TYPE").toString());
		pd.put("ORIGINALPRICE", ORIGINALPRICE);
		pd.put("DISCOUNTPRICE", DISCOUNTPRICE);
		pd.put("PACKAGE", PACKAGE);
		pd.put("BUYNOTICE", BUYNOTICE);
		pd.put("DESCRIBES", DESCRIBES);
		pd.put("DETAIL", DETAIL);
		pd.put("ISHOME", ISHOME);
		pd.put("ISREC", ISREC);
		pd.put("STATUS", STATUS);
		
		pd.put("GOOD_ID", GOOD_ID);
		
		if(null == tpz){
			tpz = "";
		}
		try{
			//图片上传
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != tp && !tp.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + GOODLOGOIMGPATH + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(tp, filePath, this.get32UUID());	
				pd.put("IMGURL", Const.FILEPATHIMG + GOODLOGOIMGPATH + ffile + "/" + fileName);//执行上传
			}else{
				logBefore(logger, "上传失败");
				pd.put("IMGURL", tpz);
			}		
			//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			goodService.edit(pd);
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
		logBefore(logger, "列表Good");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			//检索条件================================
			String KEYW = pd.getString("keyword");
			String COMID = pd.getString("COMID");
			
			logBefore(logger, "COMID"+COMID);
			if(null != KEYW && !"".equals(KEYW)){
				KEYW = KEYW.trim();
				pd.put("KEYW", KEYW);
			}
			if(null != COMID && !"".equals(COMID)){
				COMID = COMID.trim();
				pd.put("COMID", COMID);
			}
			
			//检索条件================================
			
			page.setPd(pd);
			
			List<Com> varComs = this.companyService.listAllCom(pd);
			
			List<PageData>	varListn = goodService.list(page);	//列出Good列表
			List<PageData>	varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData good = new PageData();
					good.put("id", varListn.get(i).get("GOOD_ID").toString());
					good.put("name", varListn.get(i).get("NAME").toString());
					good.put("imgurl", varListn.get(i).get("IMGURL").toString());
					good.put("com", this.getCom(varListn.get(i).get("COMID").toString())==null?"":this.getCom(varListn.get(i).get("COMID").toString()).get("NAME").toString());
					good.put("price", varListn.get(i).get("ORIGINALPRICE").toString());
					good.put("newprice", varListn.get(i).get("DISCOUNTPRICE").toString());
					good.put("package", varListn.get(i).get("PACKAGE").toString());
					good.put("buynotice", varListn.get(i).get("BUYNOTICE").toString());
					good.put("describes", varListn.get(i).get("DESCRIBES").toString());
					good.put("detail", varListn.get(i).get("DETAIL").toString());
					good.put("isHome", varListn.get(i).get("ISHOME").toString());
					good.put("isRec", varListn.get(i).get("ISREC").toString());
					good.put("status", varListn.get(i).get("STATUS").toString());
					good.put("user", varListn.get(i).get("ADDUSER").toString());
					good.put("time", varListn.get(i).get("ADDTIME")==null?"":varListn.get(i).get("ADDTIME").toString());
					
					varList.add(good);
				}
			}
			
			mv.setViewName("information/good/good_list");
			mv.addObject("varComs", varComs);
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
		logBefore(logger, "去新增Good页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<Com> varComs = this.companyService.listAllCom(pd);
			//生成GOOD_ID
			pd.put("GOOD_ID", this.get32UUID());
			mv.setViewName("information/good/good_add");
			mv.addObject("varComs", varComs);
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
