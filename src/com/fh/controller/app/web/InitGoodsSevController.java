package com.fh.controller.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.service.information.category.CategoryService;
import com.fh.service.information.comimages.ComimagesService;
import com.fh.service.information.company.CompanyService;
import com.fh.service.information.good.GoodService;
import com.fh.service.information.goodimages.GoodimagesService;
import com.fh.service.information.member.MemberService;
import com.fh.service.information.pictures.PicturesService;
import com.fh.util.AppUtil;
import com.fh.util.MD5;
import com.fh.util.MapDistance;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.weixin.model.Member;
/**
 * 终端-接口类 
 * 相关参数协议：
 * 00	请求失败
 * 01	请求成功
 * 02	返回空值
 * 03	请求协议参数不完整    
 * 04  用户名或密码错误
 * 05  FKEY验证失败
*/
@Controller
@RequestMapping(value="/api/v1")
public class InitGoodsSevController extends BaseController{

	@Resource(name="companyService")
	private CompanyService companyService;
	
	@Resource(name="goodService")
	private GoodService goodService;
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	@Resource(name="comimagesService")
	private ComimagesService comimagesService;
	
	@Resource(name="picturesService")
	private PicturesService picturesService;
	
	@Resource(name="goodimagesService")
	private GoodimagesService goodimagesService;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	/**
	 * 获取首页轮播图
	 */
	@RequestMapping(value="/index_banner")
	@ResponseBody
    public Object index_banner(HttpServletRequest request){
		logBefore(logger, "获取首页轮播图：index_banner");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			
			List<PageData> varListn = this.picturesService.listAllForIndex(pd);
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData banner = new PageData();
					banner.put("id", varListn.get(i).get("PICTURES_ID").toString());
					banner.put("title", varListn.get(i).get("TITLE").toString());
					banner.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("PATH").toString());
				
					varList.add(banner);
				}
			}else{
				map.put("datalist", varList);
				String result = "1";
				String msg = "暂无数据";
				map.put("respCode", result);
				map.put("respMsg", msg);
			}
			map.put("datalist", varList);
			String result = "1";
			String msg = "成功";
			map.put("respCode", result);
			map.put("respMsg", msg);
		} catch (Exception e) {
			// TODO: handle exception
			String result = "0";
			String msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取首页推荐商品信息
	 */
	@RequestMapping(value="/index_goods")
	@ResponseBody
    public Object index_goods(HttpServletRequest request){
		logBefore(logger, "获取首页推荐商品信息：index_goods");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			
			List<PageData> varListn = this.goodService.listHomeRec(pd);
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData good = new PageData();
					good.put("id", varListn.get(i).get("GOOD_ID").toString());
					good.put("name", varListn.get(i).get("NAME").toString());
					good.put("oldprice", varListn.get(i).get("ORIGINALPRICE").toString());
					good.put("newprice", varListn.get(i).get("DISCOUNTPRICE").toString());
					good.put("detail", varListn.get(i).get("DETAIL").toString());
					good.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("IMGURL").toString());
				
					varList.add(good);
				}
			}else{
				map.put("datalist", varList);
				String result = "1";
				String msg = "暂无数据";
				map.put("respCode", result);
				map.put("respMsg", msg);
			}
			map.put("datalist", varList);
			String result = "1";
			String msg = "成功";
			map.put("respCode", result);
			map.put("respMsg", msg);
		} catch (Exception e) {
			// TODO: handle exception
			String result = "0";
			String msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取首页推荐商家信息
	 */
	@RequestMapping(value="/index_companys")
	@ResponseBody
    public Object index_companys(HttpServletRequest request){
		logBefore(logger, "获取首页推荐商家信息：index_companys");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String longitude = pd.get("longitude").toString();
			String latitude = pd.get("latitude").toString();

			List<PageData> varListn = this.companyService.listHomeRec(pd);
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData company = new PageData();
					company.put("id", varListn.get(i).get("COMPANY_ID").toString());
					company.put("name", varListn.get(i).get("NAME").toString());
					company.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("LOGOURL").toString());
					company.put("salestime", varListn.get(i).get("SALESTIME").toString());
					//company.put("introduce", varListn.get(i).get("INTRODUCE").toString());
					company.put("describe", varListn.get(i).get("DESCRIBES").toString());
					//company.put("configure", varListn.get(i).get("CONFIGURE").toString());
					company.put("distance", MapDistance.getDistanceDoubleM(varListn.get(i).get("LONGITUDE").toString(), varListn.get(i).get("LATITUDE").toString(), longitude, latitude)+"米");
					company.put("distance", "1000米");
					
					varList.add(company);
				}
			}else{
				map.put("datalist", varList);
				String result = "1";
				String msg = "暂无数据";
				map.put("respCode", result);
				map.put("respMsg", msg);
			}
			map.put("datalist", varList);
			String result = "1";
			String msg = "成功";
			map.put("respCode", result);
			map.put("respMsg", msg);
		} catch (Exception e) {
			// TODO: handle exception
			String result = "0";
			String msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取类别列表
	 */
	@RequestMapping(value="/index_type")
	@ResponseBody
    public Object index_type(HttpServletRequest request){
		logBefore(logger, "获取类别列表：index_type");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			
			List<PageData> varListn = this.categoryService.allCate(pd);
			List<PageData> varList = new ArrayList<PageData>();
			
			if(varListn.size()>0){
				for(int i=0;i<varListn.size();i++){
					PageData cate = new PageData();
					cate.put("id", varListn.get(i).get("CATEGORY_ID").toString());
					cate.put("name", varListn.get(i).get("NAME").toString());
					cate.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("IMGURL").toString());
					
					varList.add(cate);
				}
			}else{
				map.put("datalist", varList);
				String result = "1";
				String msg = "暂无数据";
				map.put("respCode", result);
				map.put("respMsg", msg);
			}
			map.put("datalist", varList);
			String result = "1";
			String msg = "成功";
			map.put("respCode", result);
			map.put("respMsg", msg);
		} catch (Exception e) {
			// TODO: handle exception
			String result = "0";
			String msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 根据类别获取商品列表
	 */
	@RequestMapping(value="/type_goods")
	@ResponseBody
    public Object type_goods(HttpServletRequest request){
		logBefore(logger, "根据类别获取商品列表：type_goods");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		PageData type = new PageData();
		List<PageData> varList = new ArrayList<PageData>();
		try {
			pd = this.getPageData();
			type = this.getPageData();
			
			if(pd.get("id")==null){
				
				String result = "0";
				String msg = "参数id不能为空！";
				map.put("respCode", result);
				map.put("respMsg", msg);
				
			}else{
				type.put("TYPE", pd.get("id").toString());
				List<PageData> varListn = this.goodService.listByType(type);

				if(varListn.size()>0){
					for(int i=0;i<varListn.size();i++){
						PageData good = new PageData();
						good.put("id", varListn.get(i).get("GOOD_ID").toString());
						good.put("name", varListn.get(i).get("NAME").toString());
						good.put("oldprice", varListn.get(i).get("ORIGINALPRICE").toString());
						good.put("newprice", varListn.get(i).get("DISCOUNTPRICE").toString());
						good.put("detail", varListn.get(i).get("DETAIL").toString());
						good.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("IMGURL").toString());
					
						varList.add(good);
					}
					
					map.put("datalist", varList);
					String result = "1";
					String msg = "成功";
					map.put("respCode", result);
					map.put("respMsg", msg);
					
				}else{
					map.put("datalist", varList);
					String result = "1";
					String msg = "暂无数据";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			String result = "0";
			String msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取商品详情
	 */
	@RequestMapping(value="/good_detail")
	@ResponseBody
    public Object good_detail(HttpServletRequest request){
		logBefore(logger, "获取商品详情：good_detail");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			
            if(pd.get("id")==null){
				
				String result = "0";
				String msg = "参数id不能为空！";
				map.put("respCode", result);
				map.put("respMsg", msg);
				
			}else{
				pd.put("GOOD_ID", pd.get("id").toString());
				PageData good = this.goodService.findById(pd);
				if(good==null){
					String result = "0";
					String msg = "暂无数据！";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}else{
					List<PageData> imagesList = this.getGoodImgurl(good.get("GOOD_ID").toString(), request);
					
					Map<String,Object> orderd = new HashMap<String,Object>();
					
					PageData company = this.getCom(good.get("COMID").toString());
					
					PageData data = new PageData();
					PageData comData = new PageData();

					data.put("id", good.get("GOOD_ID").toString());
					data.put("name", good.get("NAME").toString());
					data.put("price", good.get("ORIGINALPRICE").toString());
					data.put("newprice", good.get("DISCOUNTPRICE").toString());
					
					data.put("package", good.get("PACKAGE").toString());
					data.put("buynotice", good.get("BUYNOTICE").toString());
					data.put("describes", good.get("DESCRIBES").toString());
					
					comData.put("id", company.get("COMPANY_ID").toString());
					comData.put("name", company.get("NAME").toString());
					comData.put("address", company.get("ADDRESS").toString());
					comData.put("telephone", company.get("TELEPHONE").toString());
					
					orderd.put("images", imagesList);
					orderd.put("company", comData);
					data.putAll(orderd);
					
					map.put("data", data);
					String result = "1";
					String msg = "成功";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}
			}
            
		} catch (Exception e) {
			// TODO: handle exception
			String result = "0";
			String msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 获取商家详情
	 */
	@RequestMapping(value="/com_detail")
	@ResponseBody
    public Object com_detail(HttpServletRequest request){
		logBefore(logger, "获取商家详情：com_detail");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		PageData com = new PageData();
		try {
			pd = this.getPageData();
			com = this.getPageData();
			
            if(pd.get("id")==null){
				
				String result = "0";
				String msg = "参数id不能为空！";
				map.put("respCode", result);
				map.put("respMsg", msg);
				
			}else{
				pd.put("COMPANY_ID", pd.get("id").toString());
				
				PageData company = this.companyService.findById(pd);
				if(company==null){
					String result = "0";
					String msg = "暂无数据！";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}else{
					com.put("COMID", pd.get("id").toString());
					List<PageData> varListn = this.goodService.listByComId(com);
					List<PageData> varList = new ArrayList<PageData>();
					if(varListn.size()>0){
						for(int i=0;i<varListn.size();i++){
							PageData good = new PageData();
							good.put("id", varListn.get(i).get("GOOD_ID").toString());
							good.put("name", varListn.get(i).get("NAME").toString());
							good.put("oldprice", varListn.get(i).get("ORIGINALPRICE").toString());
							good.put("newprice", varListn.get(i).get("DISCOUNTPRICE").toString());
							good.put("detail", varListn.get(i).get("DETAIL").toString());
							good.put("imgurl", BaseController.getPath(request)+varListn.get(i).get("IMGURL").toString());
						
							varList.add(good);
						}
					}
					List<PageData> imagesList = this.getComImgurl(company.get("COMPANY_ID").toString(), request);
					
					Map<String,Object> orderd = new HashMap<String,Object>();
					
					PageData data = new PageData();

					data.put("id", company.get("COMPANY_ID").toString());
					data.put("name", company.get("NAME").toString());
					data.put("phone", company.get("TELEPHONE").toString());
					data.put("address", company.get("ADDRESS").toString());
					data.put("describes", company.get("DESCRIBES").toString());
					//data.put("introduce", company.get("INTRODUCE").toString());
					
					orderd.put("images", imagesList);
					orderd.put("goods", varList);
					data.putAll(orderd);
					
					map.put("datalist", data);
					String result = "1";
					String msg = "成功";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			String result = "0";
			String msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 注册
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	public Object register(HttpServletRequest request){
		logBefore(logger, "register");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		PageData pda = new PageData();
		String result = "0";
		String msg = "";
		try{
			pd = this.getPageData();

			//接收参数================================
			if(pd.get("mobile")==null||pd.get("pwd")==null){
				result = "0";
				msg = "手机或者密码不能为空！";
				map.put("respCode", result);
				map.put("respMsg", msg);
			}else{
				String mobile = pd.getString("mobile");
				String pwd = pd.getString("pwd");
				//查询用户名是否重复
				pda.put("PHONE", mobile);
				PageData pdb = this.memberService.findByMobile(pda);
					
				if(pdb!=null){
					result = "0";
					msg = "该手机号已经注册！";
					map.put("respCode", result);
					map.put("respMsg", msg);
					
				}else{
					pd.put("MEMBER_ID", this.get32UUID());	//主键
					pd.put("SOURCE", 2);	//来源
					pd.put("PASSWORD", MD5.md5(pwd));	//密码
					pd.put("PHONE", mobile);	//手机号
					pd.put("INTEGRAL", 0);	//积分
					pd.put("BALANCE", 0);	//余额
					pd.put("SUBSCRIBE_TIME", Tools.date2Str(new Date()));	//注册时间

					memberService.save(pd);
					result = "1";
					msg = "注册成功！";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}
			}
			//保存参数================================
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "0";
			msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 登录
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public Object login(HttpServletRequest request){
		logBefore(logger, "login");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		String result = "0";
		String msg = "";
		try{
			pd = this.getPageData();
			//接收参数================================
			if(pd.get("mobile")==null||pd.get("pwd")==null){
				result = "0";
				msg = "手机或者密码不能为空！";
				map.put("respCode", result);
				map.put("respMsg", msg);
			}else{
				String mobile = pd.getString("mobile");
				String password = pd.getString("pwd");
				
				String pwd = MD5.md5(password); //密码加密

				pd.put("PHONE", mobile);
				pd.put("PASSWORD", pwd);

				PageData pdm = this.memberService.findByLogin(pd);

				if(pdm==null){
					result = "0";
					msg = "登录失败，用户名或密码不正确！";
					map.put("respCode", result);
					map.put("respMsg", msg);
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
					PageData data = new PageData();
					Map<String,Object> member = new HashMap<String,Object>();

					member.put("member", m);
					data.putAll(member);
					
					map.put("data", data);
					
					result = "1";
					msg="登陆成功！";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}
			}
			//保存参数================================

		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "02";
			msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/changepwd")
	@ResponseBody
	public Object changepwd(HttpServletRequest request){
		logBefore(logger, "changepwd");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		PageData pda = new PageData();
		String result = "0";
		String msg = "";
		try{
			pd = this.getPageData();
			pda = this.getPageData();

			//接收参数================================
			if(pd.get("mobile")==null||pd.get("pwd")==null){
				result = "0";
				msg = "手机或者密码不能为空！";
				map.put("respCode", result);
				map.put("respMsg", msg);
			}else{
				String mobile = pd.getString("mobile");
				String pwd = pd.getString("pwd");
				//查询用户名是否重复
				pda.put("PHONE", mobile);
				PageData pdb = this.memberService.findByMobile(pda);
					
				if(pdb!=null){
					pd.put("PASSWORD", MD5.md5(pwd));	//密码
					pd.put("PHONE", mobile);	//手机号
					
					memberService.updpwd(pd);
					result = "1";
					msg="修改成功！";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}else{
					result = "0";
					msg = "该手机号暂未注册！";
					map.put("respCode", result);
					map.put("respMsg", msg);
				}
			}
			
			//保存参数================================
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			result = "0";
			msg = e.getMessage();
			map.put("respCode", result);
			map.put("respMsg", msg);
		}
		logBefore(logger, AppUtil.returnObject(new PageData(), map).toString());
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	
	//------------------------------------------------------------------------
	
	public List<PageData> getComImgurl(String comid,HttpServletRequest request){
		logBefore(logger, "getComImgurl");
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
	
	public PageData getCompany(String comid){
		logBefore(logger, "getCompany");
		PageData pd = new PageData();
		PageData company = new PageData();
		try {
			pd = this.getPageData();
			pd.put("COMPANY_ID", comid);
			
			company = this.companyService.findById(pd);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return company;
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
