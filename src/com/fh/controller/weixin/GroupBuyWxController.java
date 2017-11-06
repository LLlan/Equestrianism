package com.fh.controller.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.weixin.Location;
import com.fh.service.information.category.CategoryService;
import com.fh.service.information.collection.CollectionService;
import com.fh.service.information.comimages.ComimagesService;
import com.fh.service.information.company.CompanyService;
import com.fh.service.information.good.GoodService;
import com.fh.service.information.goodimages.GoodimagesService;
import com.fh.service.information.join.JoinService;
import com.fh.service.information.member.MemberService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.MD5;
import com.fh.util.MapDistance;
import com.fh.util.PageData;
import com.fh.util.RandomUtil;
import com.fh.util.Tools;
import com.weixin.model.Member;
/** 
 * 类名称：GroupBuyWxController
 * 创建人：FH 
 * 创建时间：2016-05-05
 */
@Controller
@RequestMapping(value="/groupbyweixin")
public class GroupBuyWxController extends BaseController {
	@Resource(name="companyService")
	private CompanyService companyService;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="joinService")
	private JoinService joinService;
	
	@Resource(name="collectionService")
	private CollectionService collectionService;
	
	@Resource(name="goodService")
	private GoodService goodService;
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	@Resource(name="comimagesService")
	private ComimagesService comimagesService;
	
	@Resource(name="goodimagesService")
	private GoodimagesService goodimagesService;
	
	private static String smsUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
	/**
	 * 去首页
	 */
	@RequestMapping(value="/index_index")
	public ModelAndView index_index(HttpServletRequest request){
		logBefore(logger, "index_index");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/index_index");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 去首页
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request){
		logBefore(logger, "index");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//把定位信息放倒session
			Location loc = new Location();
			loc.setCity(pd.get("city").toString());
			loc.setLongitude(pd.get("lng").toString());
			loc.setLatitude(pd.get("lat").toString());

			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session sess = currentUser.getSession();
			if(sess.getAttribute(Const.SESSION_LOCATION)!=null){
			   sess.removeAttribute(Const.SESSION_LOCATION);
			   sess.setAttribute(Const.SESSION_LOCATION,loc);
			}else{
			   sess.setAttribute(Const.SESSION_LOCATION,loc);
		    }
			
			String longitude = pd.get("lng").toString();
			String latitude = pd.get("lat").toString();
			
            List<PageData> varListType = this.categoryService.findAllType();
			List<PageData> varListn = this.companyService.listHomeRec(pd);
			List<PageData> varListm = this.goodService.listHomeRec(pd);
			
			List<PageData> typeList = new ArrayList<PageData>();
			List<PageData> varListc = new ArrayList<PageData>();
			List<PageData> varListg = new ArrayList<PageData>();
			
			if(varListType.size()>0){
				for(int i=0;i<varListType.size();i++){
					PageData type = new PageData();
					type.put("id", varListType.get(i).get("CATEGORY_ID").toString());
					type.put("name", varListType.get(i).get("NAME").toString());
					type.put("imgurl", varListType.get(i).get("IMGURL").toString());
					
					typeList.add(type);
				}
			}
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData company = new PageData();
					company.put("id", varListn.get(i).get("COMPANY_ID").toString());
					company.put("name", varListn.get(i).get("NAME").toString());
					company.put("imgurl", varListn.get(i).get("LOGOURL").toString());
					company.put("salestime", varListn.get(i).get("SALESTIME").toString());
					company.put("introduce", varListn.get(i).get("INTRODUCE").toString());
					company.put("describe", varListn.get(i).get("DESCRIBES").toString());
					company.put("configure", varListn.get(i).get("CONFIGURE").toString());
					company.put("distance", MapDistance.getDistanceDoubleM(varListn.get(i).get("LONGITUDE").toString(), varListn.get(i).get("LATITUDE").toString(), longitude, latitude)+"米");
					
					varListc.add(company);
				}
			}
			
			if(varListm.size()>0){
				for(int i=0;i<varListm.size();i++){
					PageData good = new PageData();
					good.put("id", varListm.get(i).get("GOOD_ID").toString());
					good.put("name", varListm.get(i).get("NAME").toString());
					good.put("imgurl", varListm.get(i).get("IMGURL").toString());
					good.put("price", varListm.get(i).get("ORIGINALPRICE").toString());
					good.put("newprice", varListm.get(i).get("DISCOUNTPRICE").toString());
					good.put("detail", varListm.get(i).get("DETAIL").toString());
					PageData company = this.getCom(varListm.get(i).get("COMID").toString());
					good.put("distance", MapDistance.getDistanceDoubleM(company.get("LONGITUDE").toString(), company.get("LATITUDE").toString(), longitude, latitude)+"米");

					varListg.add(good);
				}
			}
			
			mv.setViewName("groupbuy/index");
			mv.addObject("typeList", typeList);
			mv.addObject("varListc", varListc);
			mv.addObject("varListg", varListg);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 根据类型获取商品
	 */
	@RequestMapping(value="/goodListByType")
	public ModelAndView goodListByType(){
		logBefore(logger, "goodListByType");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String longitude = "";
	    String latitude = "";
		try {
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session sess = currentUser.getSession();
			Location loc = (Location)sess.getAttribute(Const.SESSION_LOCATION);//获取当前登录者
			
			if(loc==null||loc.getLongitude()==""){
				mv.setViewName("groupbuy/re_index_index");
			}else{
				longitude = loc.getLongitude();
			    latitude = loc.getLatitude();
			}
			List<PageData> varListn = this.goodService.listByType(pd);
			
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData good = new PageData();
					good.put("id", varListn.get(i).get("GOOD_ID").toString());
					good.put("name", varListn.get(i).get("NAME").toString());
					good.put("imgurl", varListn.get(i).get("IMGURL").toString());
					good.put("price", varListn.get(i).get("ORIGINALPRICE").toString());
					good.put("newprice", varListn.get(i).get("DISCOUNTPRICE").toString());
					good.put("detail", varListn.get(i).get("DETAIL").toString());
					PageData company = this.getCom(varListn.get(i).get("COMID").toString());
					good.put("distance", MapDistance.getDistanceDoubleM(company.get("LONGITUDE").toString(), company.get("LATITUDE").toString(), longitude, latitude)+"米");
					
					varList.add(good);
				}
			}
			mv.setViewName("groupbuy/good-list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 商家详情
	 */
	@RequestMapping(value="/comdetails")
	public ModelAndView comdetails(HttpServletRequest request){
		logBefore(logger, "comdetails");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pdc = new PageData();
		pdc = this.getPageData();
		try {
			PageData company = this.companyService.findById(pd);
			PageData comData = new PageData();
			List<PageData> imagesList = this.getImgurl(company.get("COMPANY_ID").toString(), request);
			
			comData.put("id", company.get("COMPANY_ID").toString());
			comData.put("name", company.get("NAME").toString());
			comData.put("type", this.getType(company.get("TYPE").toString()).get("NAME").toString());
			comData.put("phone", company.get("TELEPHONE").toString());
			comData.put("address", company.get("ADDRESS").toString());
			comData.put("lng", company.get("LONGITUDE").toString());
			comData.put("lat", company.get("LATITUDE").toString());
			comData.put("describes", company.get("DESCRIBES").toString());
			
			pdc.put("COMID", pd.get("COMPANY_ID").toString());
            List<PageData> varListn = this.goodService.listByComId(pdc);
			
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData good = new PageData();
					good.put("id", varListn.get(i).get("GOOD_ID").toString());
					good.put("name", varListn.get(i).get("NAME").toString());
					good.put("imgurl", varListn.get(i).get("IMGURL").toString());
					good.put("price", varListn.get(i).get("ORIGINALPRICE").toString());
					good.put("newprice", varListn.get(i).get("DISCOUNTPRICE").toString());
					good.put("detail", varListn.get(i).get("DETAIL").toString());

					varList.add(good);
				}
			}
			
			mv.setViewName("groupbuy/company-details");
			mv.addObject("varList", varList);
			mv.addObject("images", imagesList);
			mv.addObject("pd", comData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 商品详情
	 */
	@RequestMapping(value="/gooddetails")
	public ModelAndView gooddetails(HttpServletRequest request){
		logBefore(logger, "gooddetails");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String memid="";
		try {
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session sess = currentUser.getSession();
			Member mem = (Member)sess.getAttribute(Const.SESSION_MEMBER);//获取当前登录者
			
			PageData good = this.goodService.findById(pd);
			PageData com = this.getCom(good.get("COMID").toString());
			PageData goodData = new PageData();
			List<PageData> imagesList = this.getGoodImgurl(good.get("GOOD_ID").toString(), request);
			
			goodData.put("id", good.get("GOOD_ID").toString());
			goodData.put("name", good.get("NAME").toString());
			goodData.put("price", good.get("ORIGINALPRICE").toString());
			goodData.put("newprice", good.get("DISCOUNTPRICE").toString());
			
			goodData.put("package", good.get("PACKAGE").toString());
			goodData.put("buynotice", good.get("BUYNOTICE").toString());
			goodData.put("describes", good.get("DESCRIBES").toString());
			
			PageData company = new PageData();
			company.put("id", com.get("COMPANY_ID").toString());
			company.put("name", com.get("NAME").toString());
			company.put("type", this.getType(com.get("TYPE").toString()).get("NAME").toString());
			company.put("lat", com.get("LATITUDE").toString());
			company.put("lng", com.get("LONGITUDE").toString());
			company.put("address", com.get("ADDRESS").toString());
			company.put("phone", com.get("TELEPHONE").toString());

			if(mem!=null&&mem.getId()!=""){
				memid=mem.getId();
			}
			
			mv.setViewName("groupbuy/good-details");
			mv.addObject("images", imagesList);
			mv.addObject("pd", goodData);
			mv.addObject("company", company);
			mv.addObject("memid", memid);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 收藏
	 */
	@RequestMapping(value="/collect")
	@ResponseBody
	public Object collect(Page page){
		logBefore(logger, "collect");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();

			//接收参数================================
			String type = pd.getString("type");
			String memid = pd.getString("memid");
			String id = pd.getString("id");

			pd.put("TYPE", type);	//类型
			pd.put("MEMID", memid);	//会员ID
			pd.put("ID", id);	//目标ID
			pd.put("ADDTIME", Tools.date2Str(new Date()));	//注册时间

			collectionService.save(pd);
			result = "01";
			map.put("result", result);

		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 附近商家
	 */
	@RequestMapping(value="/company")
	public ModelAndView company(){
		logBefore(logger, "company");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String longitude = "";
		String latitude = "";
		try {
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session sess = currentUser.getSession();
			Location loc = (Location)sess.getAttribute(Const.SESSION_LOCATION);//获取当前登录者
			
			if(loc==null||loc.getLongitude()==""){
				mv.setViewName("groupbuy/re_index_index");
			}else{
				longitude = loc.getLongitude();
				latitude = loc.getLatitude();
			}
			
			List<PageData> varListType = this.categoryService.findAllType();
			List<PageData> varListn = this.companyService.listDisCom(pd);
			
			List<PageData> typeList = new ArrayList<PageData>();
			List<PageData> varListc = new ArrayList<PageData>();
			
			if(varListType.size()>0){
				for(int i=0;i<varListType.size();i++){
					PageData type = new PageData();
					type.put("id", varListType.get(i).get("CATEGORY_ID").toString());
					type.put("name", varListType.get(i).get("NAME").toString());
					
					typeList.add(type);
				}
			}
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					if(MapDistance.getDistanceDoubleM(varListn.get(i).get("LONGITUDE").toString(), varListn.get(i).get("LATITUDE").toString(), longitude, latitude)-2000<=0){
						PageData company = new PageData();
						company.put("id", varListn.get(i).get("COMPANY_ID").toString());
						company.put("name", varListn.get(i).get("NAME").toString());
						company.put("imgurl", varListn.get(i).get("LOGOURL").toString());
						company.put("salestime", varListn.get(i).get("SALESTIME").toString());
						company.put("introduce", varListn.get(i).get("INTRODUCE").toString());
						company.put("describe", varListn.get(i).get("DESCRIBES").toString());
						company.put("configure", varListn.get(i).get("CONFIGURE").toString());
						company.put("distance", MapDistance.getDistanceDoubleM(varListn.get(i).get("LONGITUDE").toString(), varListn.get(i).get("LATITUDE").toString(), longitude, latitude)+"米");
						
						varListc.add(company);
					}
				}
			}
			
			mv.setViewName("groupbuy/company");
			mv.addObject("typeList", typeList);
			mv.addObject("varListc", varListc);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 发短信
	 */
	@RequestMapping(value="/sms")
	@ResponseBody
	public Object sms(Page page){
		logBefore(logger, "sms");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();

			//接收参数================================
			String mobile = pd.getString("mobile");
			String mobile_code = pd.getString("mobile_code");

			HttpClient client = new HttpClient(); 
			PostMethod method = new PostMethod(smsUrl); 
				
			//client.getParams().setContentCharset("GBK");		
			client.getParams().setContentCharset("UTF-8");
			method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

			//int mobile_code = (int)((Math.random()*9+1)*100000);

			//System.out.println(mobile);
			
		    String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！"); 

			NameValuePair[] data = {//提交短信
				    new NameValuePair("account", "cf_liangsen"), 
				    new NameValuePair("password", "liangsen"), //密码可以使用明文密码或使用32位MD5加密
				    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
				    new NameValuePair("mobile", mobile), 
				    new NameValuePair("content", content),
			};
			
			method.setRequestBody(data);		
			
			
			try {
				client.executeMethod(method);	
				
				String SubmitResult =method.getResponseBodyAsString();
						
				//System.out.println(SubmitResult);

				Document doc = DocumentHelper.parseText(SubmitResult); 
				Element root = doc.getRootElement();


				String code = root.elementText("code");	
				String msg = root.elementText("msg");	
				String smsid = root.elementText("smsid");	
				
				
				System.out.println(code);
				System.out.println(msg);
				System.out.println(smsid);
							
				if("2".equals(code)){
					result = "01";
					map.put("result", result);
				}else{
					result = "02";
					error = msg;
					map.put("error", error);
					map.put("result", result);
				}
				
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//保存参数================================
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 验证手机号码
	 */
	@RequestMapping(value="/checkMobile")
	@ResponseBody
	public Object checkMobile(Page page){
		logBefore(logger, "checkMobile");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		PageData pda = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();

			//接收参数================================
			String mobile = pd.getString("mobile");

			//查询用户名是否重复
			pda.put("PHONE", mobile);
			PageData pdb = this.memberService.findByMobile(pda);
				
			if(pdb!=null){
				result = "01";
				map.put("result", result);
			}else{
				result = "02";
				error = "该手机号码不存在！";
				map.put("error", error);
				map.put("result", result);
			}
			//保存参数================================
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/companyType")
	public ModelAndView companyType(){
		logBefore(logger, "companyType");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
            List<PageData> varListType = this.categoryService.findAllType();
			
			List<PageData> typeList = new ArrayList<PageData>();
			
			List<PageData> varListn = this.companyService.listRec(pd);
			
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListType.size()>0){
				for(int i=0;i<varListType.size();i++){
					PageData type = new PageData();
					type.put("id", varListType.get(i).get("CATEGORY_ID").toString());
					type.put("name", varListType.get(i).get("NAME").toString());
					type.put("imgurl", varListType.get(i).get("IMGURL").toString());
					
					typeList.add(type);
				}
			}
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData company = new PageData();
					company.put("id", varListn.get(i).get("COMPANY_ID").toString());
					company.put("name", varListn.get(i).get("NAME").toString());
					company.put("imgurl", varListn.get(i).get("LOGOURL").toString());
					company.put("salestime", varListn.get(i).get("SALESTIME").toString());
					company.put("introduce", varListn.get(i).get("INTRODUCE").toString());
					company.put("describe", varListn.get(i).get("DESCRIBES").toString());
					company.put("configure", varListn.get(i).get("CONFIGURE").toString());
					company.put("distance", "100米");
					
					varList.add(company);
				}
			}
			
			mv.setViewName("groupbuy/company-type");
			mv.addObject("typeList", typeList);
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/companyListByType")
	public ModelAndView companyListByType(){
		logBefore(logger, "companyListByType");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
            List<PageData> varListn = this.companyService.listByType(pd);
			
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData company = new PageData();
					company.put("id", varListn.get(i).get("COMPANY_ID").toString());
					company.put("name", varListn.get(i).get("NAME").toString());
					company.put("imgurl", varListn.get(i).get("LOGOURL").toString());
					company.put("salestime", varListn.get(i).get("SALESTIME").toString());
					company.put("introduce", varListn.get(i).get("INTRODUCE").toString());
					company.put("describe", varListn.get(i).get("DESCRIBES").toString());
					company.put("configure", varListn.get(i).get("CONFIGURE").toString());
					company.put("distance", "100米");
					
					varList.add(company);
				}
			}
			mv.setViewName("groupbuy/company-list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/buyType")
	public ModelAndView buyType(){
		logBefore(logger, "buyType");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/buy-type");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/aboutdetail")
	public ModelAndView aboutdetail(){
		logBefore(logger, "aboutdetail");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/about-detail");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/about")
	public ModelAndView about(){
		logBefore(logger, "about");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/about");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 
	 */
	@RequestMapping(value="/assess")
	public ModelAndView assess(){
		logBefore(logger, "assess");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/assess");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/carried")
	public ModelAndView carried(){
		logBefore(logger, "carried");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/carried");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 收藏列表
	 */
	@RequestMapping(value="/collection")
	public ModelAndView collection(){
		logBefore(logger, "collection");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pdm = new PageData();
		pdm = this.getPageData();
		try {
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session sess = currentUser.getSession();
			Member mem = (Member)sess.getAttribute(Const.SESSION_MEMBER);//获取当前登录者
			
			if(mem==null||mem.getId()==""){
				mv.setViewName("groupbuy/re_login");
			}else{
				pdm.put("MEMID", mem.getId());
				List<PageData> varListn = this.collectionService.listByMemid(pdm);
					
				List<PageData> varList = new ArrayList<PageData>();
				if(varListn.size()>0){
					for(int i=0;i<varListn.size();i++){
						PageData good = new PageData();
						PageData goodData = this.getGood(varListn.get(i).getString("ID"));

						good.put("id", goodData.get("GOOD_ID").toString());
						good.put("name", goodData.get("NAME").toString());
						good.put("imgurl", goodData.get("IMGURL").toString());
						good.put("price", goodData.get("ORIGINALPRICE").toString());
						good.put("newprice", goodData.get("DISCOUNTPRICE").toString());
						good.put("detail", goodData.get("DETAIL").toString());

						varList.add(good);
					}
				}
				
				mv.setViewName("groupbuy/collection");
				mv.addObject("varList",varList);
				mv.addObject("pd", mem);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/complete")
	public ModelAndView complete(){
		logBefore(logger, "complete");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/complete");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/contract")
	public ModelAndView contract(){
		logBefore(logger, "contract");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/contract");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/coupon")
	public ModelAndView coupon(){
		logBefore(logger, "coupon");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/coupon");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/emptylist")
	public ModelAndView emptylist(){
		logBefore(logger, "emptylist");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/empty-list");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/entire")
	public ModelAndView entire(){
		logBefore(logger, "entire");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/entire");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/entrust")
	public ModelAndView entrust(){
		logBefore(logger, "entrust");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/entrust");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/extension")
	public ModelAndView extension(){
		logBefore(logger, "extension");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/extension");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/housedetails")
	public ModelAndView housedetails(){
		logBefore(logger, "housedetails");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/house-details");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/toJoin")
	public ModelAndView toJoin(){
		logBefore(logger, "toJoin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/join");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 加盟
	 */
	@RequestMapping(value="/join")
	@ResponseBody
	public Object join(Page page){
		logBefore(logger, "join");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		PageData pda = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();

			//接收参数================================
			String name = pd.getString("name");
			String mobile = pd.getString("mobile");
			String shop = pd.getString("shop");
			String address = pd.getString("address");
			String number = pd.getString("number");
			String shopnum = pd.getString("shopnum");

			//查询用户名是否重复
			pda.put("MOBILE", mobile);
			PageData pdb = this.joinService.findByMobile(pda);
				
			if(pdb!=null){
				result = "02";
				error = "您已经申请过了！";
				map.put("error", error);
				map.put("result", result);
			}else{
				pd.put("NAME", name);	//姓名
				pd.put("MOBILE", mobile);	//手机号码
				pd.put("SHOP", shop);	//商家名称
				pd.put("ADDRESS", address);	//地址
				pd.put("NUMBER", number);	//团队数量
				pd.put("SHOPNUM", shopnum);	//店铺数量
				pd.put("APPLYTIME", Tools.date2Str(new Date()));	//申请时间

				joinService.save(pd);
				result = "01";
				map.put("result", result);
			}
			//保存参数================================
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/landlord")
	public ModelAndView landlord(){
		logBefore(logger, "landlord");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/landlord");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/lease")
	public ModelAndView lease(){
		logBefore(logger, "lease");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/lease");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/lifeservice")
	public ModelAndView lifeservice(){
		logBefore(logger, "lifeservice");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/life-service");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/lose")
	public ModelAndView lose(){
		logBefore(logger, "lose");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/lose");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/maphouse")
	public ModelAndView maphouse(){
		logBefore(logger, "maphouse");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/map-house");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/map")
	public ModelAndView map(){
		logBefore(logger, "map");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/map");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/modify")
	public ModelAndView modify(){
		logBefore(logger, "modify");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/modify");
			pd.put("mcode", RandomUtil.getVerificationCode());
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 改变验证码
	 */
	@RequestMapping(value="/changeCode")
	@ResponseBody
	public Object changeCode(Page page){
		logBefore(logger, "changeCode");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();

			result = "01";
			map.put("result", result);
			map.put("code", RandomUtil.getVerificationCode());
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/order")
	public ModelAndView order(){
		logBefore(logger, "order");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/order");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 个人中心
	 */
	@RequestMapping(value="/pcenter")
	public ModelAndView pcenter(){
		logBefore(logger, "pcenter");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session sess = currentUser.getSession();
			Member mem = (Member)sess.getAttribute(Const.SESSION_MEMBER);//获取当前登录者
			
			if(mem==null||mem.getId()==""){
				mv.setViewName("groupbuy/re_login");
			}else{
				mv.setViewName("groupbuy/p-center");
				mv.addObject("pd", mem);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/payrent")
	public ModelAndView payrent(){
		logBefore(logger, "payrent");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//mv.setViewName("groupbuy/pay-rent");
			mv.setViewName("groupbuy/payment");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/payment")
	public ModelAndView payment(){
		logBefore(logger, "payment");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/payment");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/profit")
	public ModelAndView profit(){
		logBefore(logger, "profit");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/profit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/recharge")
	public ModelAndView recharge(){
		logBefore(logger, "recharge");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/recharge");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/toRegister")
	public ModelAndView toRegister(){
		logBefore(logger, "toRegister");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/register");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 注册
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	public Object register(Page page){
		logBefore(logger, "register");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		PageData pda = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();

			//接收参数================================
			String mobile = pd.getString("mobile");
			String pwd = pd.getString("pwd");
            System.out.println(mobile);
			//查询用户名是否重复
			pda.put("PHONE", mobile);
			PageData pdb = this.memberService.findByMobile(pda);
				
			if(pdb!=null){
				result = "02";
				error = "该手机号已经存在！";
				map.put("error", error);
				map.put("result", result);
			}else{
				pd.put("MEMBER_ID", this.get32UUID());	//主键
				pd.put("SOURCE", 1);	//密码
				pd.put("PASSWORD", MD5.md5(pwd));	//密码
				pd.put("PHONE", mobile);	//手机号
				pd.put("INTEGRAL", 0);	//积分
				pd.put("BALANCE", 0);	//余额
				pd.put("SUBSCRIBE_TIME", Tools.date2Str(new Date()));	//注册时间

				memberService.save(pd);
				result = "01";
				map.put("result", result);
			}
			//保存参数================================
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/changepwd")
	@ResponseBody
	public Object changepwd(Page page){
		logBefore(logger, "changepwd");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();

			//接收参数================================
			String mobile = pd.getString("mobile");
			String pwd = pd.getString("pwd");
			
			pd.put("PASSWORD", MD5.md5(pwd));	//密码
			pd.put("PHONE", mobile);	//手机号
			
			memberService.updpwd(pd);
			result = "01";
			map.put("result", result);
			//保存参数================================
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 登录
	 */
	@RequestMapping(value="/login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login(Page page){
		logBefore(logger, "login");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		String result = "00";
		String error = "";
		try{
			pd = this.getPageData();
			
			//接收参数================================
			String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qq313596790fh", "").replaceAll("QQ978336446fh", "").split(",fh,");
			if(null != KEYDATA && KEYDATA.length == 2){
				
			}else{
				result = "02";
				error = "登录失败，用户名或密码不正确！";
				map.put("error", error);
				map.put("result", result);
			}
			String phone = KEYDATA[0];
			String password  = KEYDATA[1];

			String passwd = MD5.md5(password); //密码加密

			pd.put("PHONE", phone);
			pd.put("PASSWORD", passwd);

			PageData pdm = this.memberService.findByLogin(pd);

			if(pdm==null){
				result = "02";
				error = "登录失败，用户名或密码不正确！";
				map.put("error", error);
				map.put("result", result);
			}else{
				//把用户信息放倒session
				Member m = new Member();
				m.setId(pdm.get("MEMBER_ID").toString());
				m.setOpenid(pdm.get("OPENID")==null?"":pdm.get("OPENID").toString());
				m.setSource(pdm.get("SOURCE").toString());
				m.setNickname(pdm.get("NICKNAME")==null?"":pdm.get("NICKNAME").toString());
				m.setName(pdm.get("NAME")==null?"":pdm.get("NAME").toString());
				m.setPhone(pdm.get("PHONE")==null?"":pdm.get("PHONE").toString());
				m.setSex(pdm.get("SEX")==null?"":pdm.get("SEX").toString());
				m.setCity(pdm.get("CITY")==null?"":pdm.get("CITY").toString());
				m.setProvince(pdm.get("PROVINCE")==null?"":pdm.get("PROVINCE").toString());
				m.setCountry(pdm.get("COUNTRY")==null?"":pdm.get("COUNTRY").toString());
				m.setHeadimgurl(pdm.get("HEADIMGURL")==null?"":pdm.get("HEADIMGURL").toString());
				m.setIntegral(pdm.get("INTEGRAL").toString());
				m.setBalance(pdm.get("BALANCE").toString());
				m.setInstime(pdm.get("SUBSCRIBE_TIME").toString());

				//shiro管理的session
				Subject currentUser = SecurityUtils.getSubject();  
				Session sess = currentUser.getSession();
				if(sess.getAttribute(Const.SESSION_MEMBER)!=null){
				   sess.removeAttribute(Const.SESSION_MEMBER);
				   sess.setAttribute(Const.SESSION_MEMBER,m);
				}else{
				   sess.setAttribute(Const.SESSION_MEMBER,m);
			    }

				result = "01";
				map.put("result", result);
			}
			//保存参数================================

		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			error = e.getMessage();
			map.put("result", result);
			map.put("error", error);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 取消登陆
	 */
	@RequestMapping(value="/loginout")
	public ModelAndView loginout(){
		logBefore(logger, "loginout");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session sess = currentUser.getSession();
			if(sess.getAttribute(Const.SESSION_MEMBER)!=null){
			   sess.removeAttribute(Const.SESSION_MEMBER);
			}
			
			mv.setViewName("groupbuy/login");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/rentorderall")
	public ModelAndView rentorderall(){
		logBefore(logger, "rentorderall");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/rent-order-all");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/rentordernopay")
	public ModelAndView rentordernopay(){
		logBefore(logger, "rentordernopay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/rent-order-nopay");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/rentorderpay")
	public ModelAndView rentorderpay(){
		logBefore(logger, "rentorderpay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/rent-order-pay");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/rentordernop")
	public ModelAndView rentordernop(){
		logBefore(logger, "rentordernop");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/rent-order-nop");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/rentorderfinish")
	public ModelAndView rentorderfinish(){
		logBefore(logger, "rentorderfinish");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/rent-order-finish");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/schedule")
	public ModelAndView schedule(){
		logBefore(logger, "schedule");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/empty-schedule");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/servicedetails")
	public ModelAndView servicedetails(){
		logBefore(logger, "servicedetails");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/service-details");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/short")
	public ModelAndView shortt(){
		logBefore(logger, "short");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String str = pd.getString("mobile");
			String mobile = str.substring(0,str.length()-(str.substring(3)).length())+"****"+str.substring(7);
            pd.put("mobiles", mobile);
			mv.setViewName("groupbuy/short");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/toLogin")
	public ModelAndView toLogin(){
		logBefore(logger, "toLogin");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/login");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/success")
	public ModelAndView success(){
		logBefore(logger, "success");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("groupbuy/success");
			mv.addObject("pd", pd);
		} catch (Exception e) {
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
	
	/**
	 * 获取商商品
	 */
	public PageData getGood(String id) throws Exception{
		logBefore(logger, "getGood");
		PageData pd = new PageData();
		pd = this.getPageData();

		if(id==null||id.equals("")){
        	return null;
        }else{
        	pd.put("GOOD_ID", id);
    		PageData com = this.goodService.findById(pd);
    		
    		return com;
        }
	}
	
	/**
	 * 获取类型
	 */
	public PageData getType(String id) throws Exception{
		logBefore(logger, "getType");
		PageData pd = new PageData();
		pd = this.getPageData();

		if(id==null||id.equals("")){
        	return null;
        }else{
        	pd.put("CATEGORY_ID", id);
    		PageData type = this.categoryService.findById(pd);
    		
    		return type;
        }
	}
	
	public List<PageData> getImgurl(String comid,HttpServletRequest request){
		logBefore(logger, "getImgurl");
		PageData pd = new PageData();
		List<PageData> varList = new ArrayList<PageData>();
		try {
			pd = this.getPageData();
			pd.put("COMID", comid);
			List<PageData> varListn = this.comimagesService.findByComId(pd);

			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData images = new PageData();
					images.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("IMGURL").toString());
					
					varList.add(images);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return varList;
	}
	
	public List<PageData> getGoodImgurl(String goodid,HttpServletRequest request){
		logBefore(logger, "getGoodImgurl");
		PageData pd = new PageData();
		List<PageData> varList = new ArrayList<PageData>();
		try {
			pd = this.getPageData();
			pd.put("GOODID", goodid);
			List<PageData> varListn = this.goodimagesService.findByGoodId(pd);

			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData images = new PageData();
					images.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("IMGURL").toString());
					
					varList.add(images);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return varList;
	}
}
