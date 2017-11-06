package com.fh.controller.app.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.information.Advertiser;
import com.fh.service.information.xinmeiti.adver.AdverService;
import com.fh.service.information.xinmeiti.advertiser.AdvertiserService;
import com.fh.service.information.xinmeiti.base_information.Base_informationService;
import com.fh.service.information.xinmeiti.gzh.GzhService;
import com.fh.service.information.xinmeiti.media.MediaService;
import com.fh.service.information.xinmeiti.orderresource.OrderresourceService;
import com.fh.service.information.xinmeiti.pengyouquan.PengyouquanService;
import com.fh.service.information.xinmeiti.type_price.Type_priceService;
import com.fh.service.information.xinmeiti.videopalt.VideopaltService;
import com.fh.service.information.xinmeiti.weibo.WeiboService;
import com.fh.service.system.level.LevelService;
import com.fh.service.system.livePlatform.LivePlatformService;
import com.fh.service.system.rukouType.RukouTypeService;
import com.fh.service.system.textLink_type.TextLink_typeService;
import com.fh.service.system.webChannel.WebChannelService;
import com.fh.service.system.webRes.WebResService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.MD5;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.SmsUtil;
import com.fh.util.Tools;
/**
 * 
 * @author 张建华
 *
 */
@Controller
@RequestMapping(value="/api/xmtv1")
public class XinmeitiV1Controller extends BaseController{
	public static final String gzhIMG = "gzh/";	//图片上传路径(微信公众号)
	public static final String pyqIMG = "pyq/";	//图片上传路径(微信朋友圈)
	public static final String wbIMG = "wb/";	//图片上传路径(微博)
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
	@Resource(name="advertiserService")
	private AdvertiserService advertiserService;//广告主Service
	@Resource(name="gzhService")
	private GzhService gzhService;//微信公众号Service
	@Resource(name="base_informationService")
	private Base_informationService base_informationService;//基础信息Service
	@Resource(name="type_priceService")
	private Type_priceService type_priceService;//推广形式和价格Service
	@Resource(name="pengyouquanService")
	private PengyouquanService pengyouquanService;//微信朋友圈Service
	@Resource(name="weiboService")
	private WeiboService weiboService;//微博Service
	@Resource(name="orderresourceService")
	private OrderresourceService orderresourceService;//订单资源
	
	
	 @Resource(name="videopaltService")
	 private VideopaltService videopaltService;
	 @Resource(name = "livePlatformService")
	 private LivePlatformService livePlatformService;
	 @Resource(name = "mediaService")
	 private MediaService mediaService;
	 @Resource(name = "webResService")
	 private WebResService webResService;
	 @Resource(name = "webChannelService")
	 private WebChannelService webChannelService;
	 @Resource(name = "levelService")
	 private LevelService levelService;
	 @Resource(name = "rukouTypeService")
	 private RukouTypeService rukouTypeService;
	 @Resource(name = "textLink_typeService")
	 private  TextLink_typeService textLink_typeService;
	 @Resource(name = "adverService")
	 private AdverService adverService;
	/**
	 * 跳转到空白主页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/index")
	public ModelAndView index() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Advertiser advertiser = (Advertiser)session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null){
			mv.addObject("sesseionAdvertiser", advertiser);
		}else{
			mv.addObject("sesseionAdvertiser", "");
		}
		List<PageData> listgzh=gzhService.getlistAll(pd);//微信公众号、动态公告
		List<PageData> listpyq=pengyouquanService.getlistAll(pd);//朋友圈
		List<PageData> listwb=weiboService.getlistAllorderbyfansNum(pd);//微博排行榜
		List<PageData> listwb1=weiboService.getlistAll(pd);//微博
		mv.addObject("listgzh",listgzh);
		mv.addObject("listpyq",listpyq);
		mv.addObject("listwb",listwb);
		mv.addObject("listwb1",listwb1);
		mv.setViewName("information/xinmeiti/index");
		return mv;
	}
/***********************微信公众号star****************************************************/
	//TODO 微信公众号star
	/**
	 * 更改微信公众号的资源状态（启用和下架）
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/changeGzhsourceState")
	@ResponseBody
	public Object changeGzhsourceState() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		if(sessionIsOrNo()){
			pd=this.getPageData();
			String num=pd.getString("num");
			String Arrayids[]=pd.getString("ids").split(",");//分割成数组
			if(num.equals("0")){
				gzhService.changeStatetoNo(Arrayids);
			}
			if(num.equals("1")){
				gzhService.changeStatetoYes(Arrayids);
			}
		}else{
			map.put("result", "error");
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 跳转到微信公众号的修改页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toGzhEdite")
	public ModelAndView toGzhEdite() throws Exception{
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			pd=this.getPageData();
			pd=gzhService.getDataById(pd);//获取主表信息和码表的基本信息
			pd.put("tag_id", pd.getString("WCpublic_id"));
			List<PageData> list=type_priceService.getlistAllByfid(pd);//查找到旗下所有的推广形式和价格
			for (int i = 1; i <= list.size(); i++) {
				pd.put("price"+i, list.get(i-1).getString("price"));
			}
			mv.addObject("pd", pd);
			mv.addObject("caozuoType", "update");
			mv.addObject("msg", "updateGzhSource");
			mv.setViewName("information/xinmeiti/gzh/gzh_addresource");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 微信公众号更新资源
	 * @param request
	 * @param session
	 * @param headImgURLfile
	 * @param fansNumImgURLfile
	 * @param account
	 * @param name
	 * @param fansNum
	 * @param province
	 * @param city
	 * @param prices
	 * @param introduce
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateGzhSource")
	public ModelAndView updateGzhSource(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile headImgURLfile,
			@RequestParam(required = false) MultipartFile fansNumImgURLfile,
			@RequestParam(required = false) String account,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String fansNum,
			@RequestParam(required = false) String province,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String prices,
			@RequestParam(required = false) String introduce,
			@RequestParam(required = false) String WCpublic_id
			) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			PageData pd2=new PageData();
			PageData pd3=new PageData();
			pd3.put("tag_id", WCpublic_id);
			pd3=gzhService.getDataById(pd3);
			//上传图片
			String  ffile = DateUtil.getDays(), headImgURLName = "",fansNumImgURLName = "";
			if (null != headImgURLfile && !headImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + gzhIMG + ffile;//文件上传路径
				headImgURLName = FileUpload.fileUp(headImgURLfile, filePath, this.get32UUID());	//执行上传
				pd2.put("headImgURL", Const.FILEPATHIMG + gzhIMG + ffile + "/" + headImgURLName);
				//删除已存在的图片
				File filed1=new File(PathUtil.getClasspath()+pd3.getString("headImgURL"));
				if(filed1.exists()){
					filed1.delete();
				}
			}else{
				pd2.put("headImgURL", pd3.getString("headImgURL"));
			}
			if (null != fansNumImgURLfile && !fansNumImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + gzhIMG + ffile;//文件上传路径
				fansNumImgURLName = FileUpload.fileUp(fansNumImgURLfile, filePath, this.get32UUID());//执行上传
				pd.put("fansNumImgURL", Const.FILEPATHIMG + gzhIMG + ffile + "/" + fansNumImgURLName);
				//删除已存在的图片
				File filed1=new File(PathUtil.getClasspath()+pd3.getString("fansNumImgURL"));
				if(filed1.exists()){
					filed1.delete();
				}
			}else{
				pd.put("fansNumImgURL", pd3.getString("fansNumImgURL"));
			}
			////主表内容
			pd.put("WCpublic_id", WCpublic_id);
			pd.put("account", account);
			pd.put("checkedState", "2");
			pd.put("sourceState", "0");
			//码表基本信息内容
			pd2.put("f_id", WCpublic_id);
			pd2.put("name", name);
			pd2.put("fansNum", fansNum);
			pd2.put("province", province);
			pd2.put("city", city);
			pd2.put("introduce", introduce);
			pd2.put("time", DateUtil.getTime());
			//码表推广形式和价格
			String pricess[]=prices.split(",");
			pd3.put("tag_id", WCpublic_id);
			List<PageData> listType=type_priceService.getlistAllByfid(pd3);//查找出所有的推广形式和价格
			//执行更新操作
			gzhService.update(pd);
			base_informationService.update(pd2);
			for (int i = 0; i < listType.size(); i++) {
				PageData pd4=new PageData();
				pd4.put("price", pricess[i]);
				pd4.put("id", listType.get(i).getString("id"));
				type_priceService.update(pd4);
			}
			mv.setViewName("redirect:liuSourcejump.do?num=1");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	
	/**
	 *  微信公众号添加资源
	 * @param request
	 * @param session
	 * @param headImgURL 头像
	 * @param fansNumImgURL 粉丝图片截图
	 * @throws Exception 
	 * @param account
	 * @param name
	 * @param fansNum
	 * @param province
	 * @param city
	 * @param names
	 * @param prices
	 * @param introduce
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addGzhSource")
	public ModelAndView addGzhSource(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile headImgURLfile,
			@RequestParam(required = false) MultipartFile fansNumImgURLfile,
			@RequestParam(required = false) String account,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String fansNum,
			@RequestParam(required = false) String province,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String names,
			@RequestParam(required = false) String prices,
			@RequestParam(required = false) String introduce
			) throws Exception {
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			//上传图片
			String  ffile = DateUtil.getDays(), headImgURLName = "",fansNumImgURLName = "";
			if (null != headImgURLfile && !headImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + gzhIMG + ffile;//文件上传路径
				headImgURLName = FileUpload.fileUp(headImgURLfile, filePath, this.get32UUID());	//执行上传
			}
			if (null != fansNumImgURLfile && !fansNumImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + gzhIMG + ffile;//文件上传路径
				fansNumImgURLName = FileUpload.fileUp(fansNumImgURLfile, filePath, this.get32UUID());//执行上传
			}
			
			//执行操作，添加到数据库
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
			//主表内容
			PageData pd=new PageData();
			pd.put("account", account);
			pd.put("fansNumImgURL", Const.FILEPATHIMG + gzhIMG + ffile + "/" + fansNumImgURLName);
			pd.put("checkedState", "2");
			pd.put("sourceState", "0");
			pd.put("WCpublic_id", this.get32UUID());
			pd.put("f_id", advertiser.getAdvertiser_id());
			//码表基本信息内容
			PageData pd2=new PageData();
			pd2.put("headImgURL", Const.FILEPATHIMG + gzhIMG + ffile + "/" + headImgURLName);
			pd2.put("base_information_id", this.get32UUID());
			pd2.put("name", name);
			pd2.put("fansNum", fansNum);
			pd2.put("province", province);
			pd2.put("city", city);
			pd2.put("introduce", introduce);
			pd2.put("f_id", pd.getString("WCpublic_id"));
			pd2.put("time", DateUtil.getTime());
			String namess[]=names.split(",");
			String pricess[]=prices.split(",");
			//执行添加操作
			gzhService.save(pd);//添加资源主表信息
			base_informationService.save(pd2);//添加资源码表基础信息
			
			for (int i = 0; i < namess.length; i++) {//添加形式和价格
				PageData pd1=new PageData();
				pd1.put("id", this.get32UUID());
				pd1.put("price", pricess[i]);
				pd1.put("name", namess[i]);
				pd1.put("f_id", pd.getString("WCpublic_id"));
				type_priceService.save(pd1);
			}
			mv.setViewName("redirect:liuSourcejump.do?num=1");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		
		return mv;
	}
	
	/**
	 * 跳转到微信公众号列表页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/gongzhonghao")
	public ModelAndView gongzhonghao() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		List<PageData> list=gzhService.getlistAll(pd);//获取微信公众号列表
		//Collections.shuffle(list);//混乱排列
		mv.addObject("list", list);
		mv.setViewName("information/xinmeiti/gzh/gzh_list");
		return mv;
	}
	/**
	 * 微信公众号列表页的筛选
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/gzhShaiXuan")
	@ResponseBody
	public Object gzhShaiXuan() throws Exception{
		//ModelAndView mv=new ModelAndView();
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		String selectA=pd.getString("selectA");
		String selectB=pd.getString("selectB");
		String selectC=pd.getString("selectC");
		if(Tools.notEmpty(selectA)){
			if(selectA.indexOf("万以下")!=-1){//等于-1，说明该字符串中不存在"万以下"
				pd.put("minFansNum", 0);
				pd.put("maxFansNum", Integer.parseInt(selectA.replaceAll("万以下", ""))*10000);
			}else{
				selectA=Pattern.compile("[\u4e00-\u9fa5]").matcher(selectA).replaceAll("");//去除中文
				if(selectA.indexOf("-")==-1){//等于-1,说明不存在"-"
					pd.put("maxFansNum", Integer.parseInt(selectA)*10000);
				}else{
					selectA=selectA.replaceAll("-", ",");
					String sa[]=selectA.split(",");
					
					pd.put("minFansNum", Integer.parseInt(sa[0])*10000);
					pd.put("maxFansNum", Integer.parseInt(sa[1])*10000);
				}
			}
		}
		if(Tools.notEmpty(selectB)){
			if(selectB.indexOf("元以下")!=-1){//等于-1，说明该字符串中不存在"元以下"
				pd.put("minPrice", 0);
				pd.put("maxPrice", Integer.parseInt(selectB.replaceAll("元以下", "")));
			}else{
				selectB=Pattern.compile("[\u4e00-\u9fa5]").matcher(selectB).replaceAll("");//去除中文
				if(selectB.indexOf("-")==-1){//等于-1,说明不存在"-"
					pd.put("maxPrice", Integer.parseInt(selectB));
				}else{
					selectB=selectB.replaceAll("-", ",");
					String sb[]=selectB.split(",");
					pd.put("minPrice", Integer.parseInt(sb[0]));
					pd.put("maxPrice", Integer.parseInt(sb[1]));
				}
			}
		}
		if(Tools.notEmpty(selectC)){
			pd.put("area", selectC);
		}
		List<PageData> list=gzhService.getlistAll(pd);//获取微信公众号列表
		//Collections.shuffle(list);//混乱排列
		//mv.addObject("list", list);
		//mv.setViewName("information/xinmeiti/gzh/gzh_list");
		map.put("list", list);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 跳转到微信公众号详情页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/gzhDetail")
	public ModelAndView gzhDetail() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		
		List<PageData> list=type_priceService.getlistAllByfid(pd);//获取所有的推广形式和价格
		pd=base_informationService.getDataByfid(pd);//获取基本信息
		List<PageData> list1=gzhService.getlistAll(pd);//大家都在看
		List<PageData> list2=gzhService.getlistAll(pd);//人气榜
		
		Collections.shuffle(list1);//混乱排列
		Collections.shuffle(list2);//混乱排列
		
		mv.addObject("list", list);
		mv.addObject("list1",list1);
		mv.addObject("list2",list2);
		mv.addObject("pd", pd);
		mv.setViewName("information/xinmeiti/gzh/gzh_detail");
		return mv;
	}
	//TODO 公众号订单部分
	/**
	 * 进入公众号的创建订单页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toGzhCreateOredr")
	public ModelAndView toGzhCreateOredr(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null && advertiser.getRolMark().equals("1")){
			PageData pd=new PageData();
			pd=this.getPageData();
			//获取订单资源的信息
			String tempname=pd.getString("namendp");
			String mark="";
			if(tempname.equals("1")){
				mark="单图文硬广";
			}else if(tempname.equals("2")){
				mark="单图文软广";
			}else if(tempname.equals("3")){
				mark="多图文第一条硬广";
			}else if(tempname.equals("4")){
				mark="多图文第一条软广";
			}else if(tempname.equals("5")){
				mark="多图文第二条硬广";
			}else if(tempname.equals("6")){
				mark="多图文第二条软广";
			}else if(tempname.equals("6")){
				mark="多图文第3~N条硬广";
			}else if(tempname.equals("8")){
				mark="多图文第3~N条软广";
			}
			pd.put("name", mark);
			pd=gzhService.detailgotoorder(pd);
			mv.addObject("pd",pd);
			mv.setViewName("information/xinmeiti/gzh/gzh_createorder");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 创建订单中的资源选择(选择更多资源)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getGzhresourcelist")
	@ResponseBody
	public Object getGzhresourcelist(HttpSession session) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null && advertiser.getRolMark().equals("1")){
			String array[]=pd.getString("ids").split(",");
			List<PageData> list=gzhService.getresourcelist(array);//选中的资源信息
			for (int i = 0; i < list.size(); i++) {
				PageData pdtype=new PageData();
				pdtype.put("tag_id", list.get(i).getString("WCpublic_id"));
				pdtype.put("name", pd.getString("name"));
				pdtype=type_priceService.getdateByfidandName(pdtype);
				list.get(i).put("price", pdtype.getString("price"));
			}
			map.put("msg", "success");
			map.put("list", list);
		}else{
			map.put("msg", "error");
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 创建订单页面中的点击添加资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getGzhOrderAddResourceList")
	@ResponseBody
	public Object getGzhOrderAddResourceList() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		String ids[]=pd.getString("ids").split(",");//获取ID数组
		//获取添加资源中的资源列表信息（不包括价格）
		List<PageData> list=gzhService.getOrderAddResourceList(ids);
		List<PageData> list1=new ArrayList<PageData>();//获取满足条件的资源信息列表（接该类型的任务，price！=-1）
		//循环资源信息列表找到与其对应的价格
		for (int i = 0; i < list.size(); i++) {
			pd.put("tag_id", list.get(i).getString("WCpublic_id"));
			PageData pd1=type_priceService.getdateByfidandName(pd);
			if(pd1!=null){
				if(!pd1.getString("price").equals("-1")){
					list.get(i).put("price", pd1.getString("price"));
					list1.add(list.get(i));
				}
			}
			
		}
		map.put("list", list1);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 创建微信公众号订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/createGzhorder")
	public ModelAndView createGzhorder(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile cover_imgURLfile,
			@RequestParam(required = false) MultipartFile proveURLfile,
			@RequestParam(required = false) String type_name,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String beginTime,
			@RequestParam(required = false) String article_import,
			@RequestParam(required = false) String article_title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String text_content,
			@RequestParam(required = false) String text_http,
			@RequestParam(required = false) String remarks,
			@RequestParam(required = false) String ids
			) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			//上传图片
			String  ffile = DateUtil.getDays(), cover_imgURLName = "",proveURLName = "";
			if (null != cover_imgURLfile && !cover_imgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + gzhIMG + ffile;//文件上传路径
				cover_imgURLName = FileUpload.fileUp(cover_imgURLfile, filePath, this.get32UUID());	//执行上传
				pd.put("cover_imgURL", Const.FILEPATHIMG + gzhIMG + ffile + "/" + cover_imgURLName);
			}
			if (null != proveURLfile && !proveURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + gzhIMG + ffile;//文件上传路径
				proveURLName = FileUpload.fileUp(proveURLfile, filePath, this.get32UUID());//执行上传
				pd.put("proveURL", Const.FILEPATHIMG + gzhIMG + ffile + "/" + proveURLName);
			}else{
				pd.put("proveURL", "");
			}
			String order_number=sdfTime.format(new Date())+(int)((Math.random()*9+1)*100000);//订单号
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
			pd.put("id", this.get32UUID());
			pd.put("type_name", type_name);
			pd.put("name", name);
			pd.put("beginTime", beginTime);
			pd.put("article_import", article_import);
			pd.put("article_title", article_title);
			pd.put("author", author);
			pd.put("text_content", text_content);
			pd.put("text_http", text_http);
			pd.put("remarks", remarks);
			pd.put("order_time", DateUtil.getTime());
			pd.put("order_number", order_number);
			pd.put("f_id", advertiser.getAdvertiser_id());
			gzhService.insertOrder(pd);//添加订单
			
			PageData pd1=new PageData();
			pd1.put("order_number", order_number);
			pd1.put("order_state", "1");
			pd1.put("mjzorder_state", "1");
			pd1.put("resource_type", "1");
			String arrid[]=ids.split(",");
			for (int i = 0; i < arrid.length; i++) {
				pd1.put("id", this.get32UUID());
				pd1.put("resource_id", arrid[i]);
				orderresourceService.save(pd1);//添加订单资源
			}
			mv.setViewName("redirect:/api/xmtv1/toGgzorderGzhlist.do");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 广告主进入公众号订单列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toGgzorderGzhlist")
	public ModelAndView toGgzorderGzhlist(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		//获取session
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null && advertiser.getRolMark().equals("1")){//已登录且身份为广告主
			PageData pd=new PageData();
			pd=this.getPageData();
			pd.put("f_id", advertiser.getAdvertiser_id());
			List<PageData> list=gzhService.getOrderInformationList(pd);
			mv.addObject("pd", pd);
			mv.addObject("list", list);
			mv.setViewName("information/xinmeiti/gzh/gzh_ggzpaidan");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 媒介主进入公众号订单列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toMjzorderGzhlist")
	public ModelAndView toMjzorderGzhlist(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		//去微信公众号订单表中取查找所有指定用户的订单
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
			pd.put("f_id", advertiser.getAdvertiser_id());
			List<PageData> list=gzhService.getMjzOrderInformationList(pd);//查询微信公众号的资源列表信息
			mv.addObject("list", list);
			mv.setViewName("information/xinmeiti/gzh/gzh_paidan");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
/***********************微信公众号end****************************************************/
	
/***********************微信朋友圈star****************************/	
	//TODO 微信朋友圈star
	/**
	 * 更改微信朋友圈的资源状态（启用和下架）
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/changePyqsourceState")
	@ResponseBody
	public Object changePyqsourceState() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		if(sessionIsOrNo()){
			pd=this.getPageData();
			String num=pd.getString("num");
			String Arrayids[]=pd.getString("ids").split(",");//分割成数组
			if(num.equals("0")){
				pengyouquanService.changeStatetoNo(Arrayids);
			}
			if(num.equals("1")){
				pengyouquanService.changeStatetoYes(Arrayids);
			}
		}else{
			map.put("result", "error");
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 跳转到微信公众号的修改页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toPyqEdite")
	public ModelAndView toPyqEdite() throws Exception{
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			pd=this.getPageData();
			pd=pengyouquanService.getDataById(pd);//获取主表信息和码表的基本信息
			mv.addObject("pd", pd);
			mv.addObject("caozuoType", "update");
			mv.addObject("msg", "updataPyqSource");
			mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_addresource");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 *  微信朋友圈修改资源
	 * @param request
	 * @param session
	 * @param headImgURL 头像
	 * @param fansNumImgURL 粉丝图片截图
	 * @throws Exception 
	 * @param account
	 * @param name
	 * @param fansNum
	 * @param province
	 * @param city
	 * @param name1
	 * @param price1
	 * @param introduce
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updataPyqSource")
	public ModelAndView updataPyqSource(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile headImgURLfile,
			@RequestParam(required = false) MultipartFile fansNumImgURLfile,
			@RequestParam(required = false) MultipartFile informationImgURLfile,
			@RequestParam(required = false) String account,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String fansNum,
			@RequestParam(required = false) String province,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String name1,
			@RequestParam(required = false) String price1,
			@RequestParam(required = false) String introduce,
			@RequestParam(required = false) String sex,
			@RequestParam(required = false) String WCfriends_id
			) throws Exception {
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			PageData pd2=new PageData();
			PageData pd3=new PageData();
			pd3.put("tag_id", WCfriends_id);
			pd3=pengyouquanService.getDataById(pd3);
			//上传图片
			String  ffile = DateUtil.getDays(), headImgURLName = "",fansNumImgURLName = "",informationImgURLName="";
			if (null != headImgURLfile && !headImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + pyqIMG + ffile;//文件上传路径
				headImgURLName = FileUpload.fileUp(headImgURLfile, filePath, this.get32UUID());	//执行上传
				pd2.put("headImgURL", Const.FILEPATHIMG + pyqIMG + ffile + "/" + headImgURLName);
				//删除已存在的图片
				File filed1=new File(PathUtil.getClasspath()+pd3.getString("headImgURL"));
				if(filed1.exists()){
					filed1.delete();
				}
			}else{
				pd2.put("headImgURL", pd3.getString("headImgURL"));
			}
			if (null != fansNumImgURLfile && !fansNumImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + pyqIMG + ffile;//文件上传路径
				fansNumImgURLName = FileUpload.fileUp(fansNumImgURLfile, filePath, this.get32UUID());//执行上传
				pd.put("fansNumImgURL", Const.FILEPATHIMG + pyqIMG + ffile + "/" + fansNumImgURLName);
				//删除已存在的图片
				File filed1=new File(PathUtil.getClasspath()+pd3.getString("fansNumImgURL"));
				if(filed1.exists()){
					filed1.delete();
				}
			}else{
				pd.put("fansNumImgURL", pd3.getString("fansNumImgURL"));
			}
			if (null != informationImgURLfile && !informationImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + pyqIMG + ffile;//文件上传路径
				informationImgURLName = FileUpload.fileUp(informationImgURLfile, filePath, this.get32UUID());//执行上传
				pd.put("informationImgURL", Const.FILEPATHIMG + pyqIMG + ffile + "/" + informationImgURLName);
				//删除已存在的图片
				File filed1=new File(PathUtil.getClasspath()+pd3.getString("informationImgURL"));
				if(filed1.exists()){
					filed1.delete();
				}
			}else{
				pd.put("informationImgURL", pd3.getString("informationImgURL"));
			}
			//主表内容
			pd.put("WCfriends_id", WCfriends_id);
			pd.put("account", account);
			pd.put("sex", sex);
			pd.put("checkedState", "2");
			pd.put("sourceState", "0");
			//码表基本信息内容
			pd2.put("f_id", WCfriends_id);
			pd2.put("name", name);
			pd2.put("fansNum", fansNum);
			pd2.put("province", province);
			pd2.put("city", city);
			pd2.put("introduce", introduce);
			pd2.put("time", DateUtil.getTime());
			//添加形式和价格
			pd3.put("name", name1);
			pd3.put("price", price1);
			pd3.put("f_id", WCfriends_id);
			//执行添加操作
			pengyouquanService.update(pd);//添加资源主表信息
			base_informationService.update(pd2);//添加资源码表基础信息
			type_priceService.update(pd3);
			mv.setViewName("redirect:liuSourcejump.do?num=2");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 *  微信朋友圈添加资源
	 * @param request
	 * @param session
	 * @param headImgURL 头像
	 * @param fansNumImgURL 粉丝图片截图
	 * @throws Exception 
	 * @param account
	 * @param name
	 * @param fansNum
	 * @param province
	 * @param city
	 * @param name1
	 * @param price1
	 * @param introduce
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addPyqSource")
	public ModelAndView addPyqSource(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile headImgURLfile,
			@RequestParam(required = false) MultipartFile fansNumImgURLfile,
			@RequestParam(required = false) MultipartFile informationImgURLfile,
			@RequestParam(required = false) String account,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String fansNum,
			@RequestParam(required = false) String province,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String name1,
			@RequestParam(required = false) String price1,
			@RequestParam(required = false) String introduce,
			@RequestParam(required = false) String sex
			) throws Exception {
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			//上传图片
			String  ffile = DateUtil.getDays(), headImgURLName = "",fansNumImgURLName = "",informationImgURLName="";
			if (null != headImgURLfile && !headImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + pyqIMG + ffile;//文件上传路径
				headImgURLName = FileUpload.fileUp(headImgURLfile, filePath, this.get32UUID());	//执行上传
			}
			if (null != fansNumImgURLfile && !fansNumImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + pyqIMG + ffile;//文件上传路径
				fansNumImgURLName = FileUpload.fileUp(fansNumImgURLfile, filePath, this.get32UUID());//执行上传
			}
			if (null != informationImgURLfile && !informationImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + pyqIMG + ffile;//文件上传路径
				informationImgURLName = FileUpload.fileUp(informationImgURLfile, filePath, this.get32UUID());//执行上传
			}
			//执行操作，添加到数据库
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
			//主表内容
			PageData pd=new PageData();
			pd.put("account", account);
			pd.put("fansNumImgURL", Const.FILEPATHIMG + pyqIMG + ffile + "/" + fansNumImgURLName);
			pd.put("informationImgURL", Const.FILEPATHIMG + pyqIMG + ffile + "/" + informationImgURLName);
			pd.put("sex", sex);
			pd.put("checkedState", "2");
			pd.put("sourceState", "0");
			pd.put("WCfriends_id", this.get32UUID());
			pd.put("f_id", advertiser.getAdvertiser_id());
			//码表基本信息内容
			PageData pd2=new PageData();
			pd2.put("headImgURL", Const.FILEPATHIMG + pyqIMG + ffile + "/" + headImgURLName);
			pd2.put("base_information_id", this.get32UUID());
			pd2.put("name", name);
			pd2.put("fansNum", fansNum);
			pd2.put("province", province);
			pd2.put("city", city);
			pd2.put("introduce", introduce);
			pd2.put("f_id", pd.getString("WCfriends_id"));
			pd2.put("time", DateUtil.getTime());
			//添加形式和价格
			PageData pd3=new PageData();
			pd3.put("name", name1);
			pd3.put("price", price1);
			pd3.put("id", this.get32UUID());
			pd3.put("f_id", pd.getString("WCfriends_id"));
			//执行添加操作
			pengyouquanService.save(pd);//添加资源主表信息
			base_informationService.save(pd2);//添加资源码表基础信息
			type_priceService.save(pd3);
			mv.setViewName("redirect:liuSourcejump.do?num=2");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 跳转到微信朋友圈列表页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pengyouquan")
	public ModelAndView pengyouquan() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		List<PageData> list=pengyouquanService.getlistAll(pd);
		//Collections.shuffle(list);//混乱排列
		mv.addObject("list", list);
		mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_list");
		return mv;
	}
	/**
	 * 微信公众号列表页的筛选
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pyqShaiXuan")
	@ResponseBody
	public Object pyqShaiXuan() throws Exception{
		//ModelAndView mv=new ModelAndView();
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		String selectA=pd.getString("selectA");
		String selectB=pd.getString("selectB");
		String selectC=pd.getString("selectC");
		if(Tools.notEmpty(selectA)){
			if(selectA.indexOf("万以下")!=-1){//等于-1，说明该字符串中不存在"万以下"
				pd.put("minFansNum", 0);
				pd.put("maxFansNum", Integer.parseInt(selectA.replaceAll("万以下", ""))*10000);
			}else{
				selectA=Pattern.compile("[\u4e00-\u9fa5]").matcher(selectA).replaceAll("");//去除中文
				if(selectA.indexOf("-")==-1){//等于-1,说明不存在"-"
					pd.put("maxFansNum", Integer.parseInt(selectA)*10000);
				}else{
					selectA=selectA.replaceAll("-", ",");
					String sa[]=selectA.split(",");
					
					pd.put("minFansNum", Integer.parseInt(sa[0])*10000);
					pd.put("maxFansNum", Integer.parseInt(sa[1])*10000);
				}
			}
		}
		if(Tools.notEmpty(selectB)){
			if(selectB.indexOf("元以下")!=-1){//等于-1，说明该字符串中不存在"元以下"
				pd.put("minPrice", 0);
				pd.put("maxPrice", Integer.parseInt(selectB.replaceAll("元以下", "")));
			}else{
				selectB=Pattern.compile("[\u4e00-\u9fa5]").matcher(selectB).replaceAll("");//去除中文
				if(selectB.indexOf("-")==-1){//等于-1,说明不存在"-"
					pd.put("maxPrice", Integer.parseInt(selectB));
				}else{
					selectB=selectB.replaceAll("-", ",");
					String sb[]=selectB.split(",");
					pd.put("minPrice", Integer.parseInt(sb[0]));
					pd.put("maxPrice", Integer.parseInt(sb[1]));
				}
			}
		}
		if(Tools.notEmpty(selectC)){
			pd.put("area", selectC);
		}
		List<PageData> list=pengyouquanService.getlistAll(pd);//获取微信朋友圈列表
		//Collections.shuffle(list);//混乱排列
		//mv.addObject("list", list);
		//mv.setViewName("information/xinmeiti/gzh/gzh_list");
		map.put("list", list);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 跳转到微信朋友圈详情页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pengyouquanDetail")
	public ModelAndView pengyouquanDetail() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();

		List<PageData> list=type_priceService.getlistAllByfid1(pd);//获取所有的推广形式和价格
		pd=base_informationService.getDataByfid(pd);//获取基本信息
		List<PageData> list1=pengyouquanService.getlistAll(pd);//大家都在看
		List<PageData> list2=pengyouquanService.getlistAll(pd);//人气榜
		
		Collections.shuffle(list1);//混乱排列
		Collections.shuffle(list2);//混乱排列
		
		mv.addObject("list", list);
		mv.addObject("list1",list1);
		mv.addObject("list2",list2);
		mv.addObject("pd", pd);
		mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_detail");
		return mv;
	}
	//TODO 朋友圈订单部分
	/**
	 * 进入朋友圈的创建订单页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toPyqCreateOredr")
	public ModelAndView toPyqCreateOredr() throws Exception{
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			pd=this.getPageData();
			pd=pengyouquanService.detailgotoorder(pd);
			mv.addObject("pd",pd);
			mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_createorder");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 创建订单页面中的点击添加资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPyqOrderAddResourceList")
	@ResponseBody
	public Object getPyqOrderAddResourceList() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		String ids[]=pd.getString("ids").split(",");//获取ID数组
		//获取添加资源中的资源列表信息
		List<PageData> list=pengyouquanService.getOrderAddResourceList(ids);
		map.put("list", list);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 创建订单中的资源选择(选择更多资源)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPyqresourcelist")
	@ResponseBody
	public Object getPyqresourcelist() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		String array[]=pd.getString("ids").split(",");
		List<PageData> list=pengyouquanService.getresourcelist(array);//选中的资源信息
		map.put("list", list);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 创建微信朋友圈订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/createPyqorder")
	public ModelAndView createPyqorder(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile proveURLfile,
			@RequestParam(required = false) String type_name,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String beginTime,
			@RequestParam(required = false) String zhuanfa_link,
			@RequestParam(required = false) String steps,
			@RequestParam(required = false) String fenxiangyu,
			@RequestParam(required = false) String remarks,
			@RequestParam(required = false) String ids
			) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			//上传图片
			String  ffile = DateUtil.getDays(), proveURLName = "";
			if (null != proveURLfile && !proveURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + pyqIMG + ffile;//文件上传路径
				proveURLName = FileUpload.fileUp(proveURLfile, filePath, this.get32UUID());//执行上传
				pd.put("proveURL", Const.FILEPATHIMG + pyqIMG + ffile + "/" + proveURLName);
			}else{
				pd.put("proveURL", "");
			}
			String order_number=sdfTime.format(new Date())+(int)((Math.random()*9+1)*100000);//订单号
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
			pd.put("id", this.get32UUID());
			pd.put("type_name", type_name);
			pd.put("name", name);
			pd.put("beginTime", beginTime);
			pd.put("zhuanfa_link", zhuanfa_link);
			pd.put("steps", steps);
			pd.put("fenxiangyu", fenxiangyu);
			pd.put("remarks", remarks);
			pd.put("order_time", DateUtil.getTime());
			pd.put("order_number", order_number);
			pd.put("f_id", advertiser.getAdvertiser_id());
			pengyouquanService.insertOrder(pd);//添加订单
			
			PageData pd1=new PageData();
			pd1.put("order_number", order_number);
			pd1.put("order_state", "1");
			pd1.put("mjzorder_state", "1");
			pd1.put("resource_type", "2");
			String arrid[]=ids.split(",");
			for (int i = 0; i < arrid.length; i++) {
				pd1.put("id", this.get32UUID());
				pd1.put("resource_id", arrid[i]);
				orderresourceService.save(pd1);//添加订单资源
			}
			mv.setViewName("redirect:/api/xmtv1/toGgzorderPyqlist.do");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	
	/**
	 * 广告主进入朋友圈订单列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toGgzorderPyqlist")
	public ModelAndView toGgzorderPyqlist(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		//获取session
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null && advertiser.getRolMark().equals("1")){//已登录且身份为广告主
			PageData pd=new PageData();
			pd=this.getPageData();
			pd.put("f_id", advertiser.getAdvertiser_id());
			List<PageData> list=pengyouquanService.getOrderInformationList(pd);
			mv.addObject("pd", pd);
			mv.addObject("list", list);
			mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_ggzpaidan");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 媒介主进入朋友圈订单列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toMjzorderPyqlist")
	public ModelAndView toMjzorderPyqlist(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		//去微信朋友圈订单表中取查找所有指定用户的订单
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
			pd.put("f_id", advertiser.getAdvertiser_id());
			List<PageData> list=pengyouquanService.getMjzOrderInformationList(pd);//查询微信朋友圈的资源列表信息
			mv.addObject("list", list);
			mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_paidan");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
/***********************微信朋友圈end****************************/	
	
/***********************微博star****************************************************/
	//TODO 微博star
	/**
	 * 更改微博的资源状态（启用和下架）
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/changeWbsourceState")
	@ResponseBody
	public Object changeWbsourceState(HttpSession session) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		if(advertiser(session)!=null && advertiser(session).getRolMark().equals("2")){
			pd=this.getPageData();
			String num=pd.getString("num");
			String Arrayids[]=pd.getString("ids").split(",");//分割成数组
			if(num.equals("0")){
				weiboService.changeStatetoNo(Arrayids);
			}
			if(num.equals("1")){
				weiboService.changeStatetoYes(Arrayids);
			}
			map.put("result", "success");
		}else{
			map.put("result", "error");
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 跳转到微博的修改页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toWbEdite")
	public ModelAndView toWbEdite(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(advertiser(session)!=null && advertiser(session).getRolMark().equals("2")){
			PageData pd=new PageData();
			pd=this.getPageData();
			pd=weiboService.getDataById(pd);//获取主表信息和码表的基本信息
			pd.put("tag_id", pd.getString("microBlog_id"));
			List<PageData> list=type_priceService.getlistAllByfid2(pd);//查找到旗下所有的推广形式和价格
			for (int i = 1; i <= list.size(); i++) {
				pd.put("price"+i, list.get(i-1).getString("price"));
			}
			mv.addObject("pd", pd);
			mv.addObject("caozuoType", "update");
			mv.addObject("msg", "updateWbSource");
			mv.setViewName("information/xinmeiti/weibo/weibo_addresource");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 微博更新资源
	 * @param request
	 * @param session
	 * @param headImgURLfile
	 * @param fansNumImgURLfile
	 * @param account
	 * @param name
	 * @param fansNum
	 * @param province
	 * @param city
	 * @param prices
	 * @param introduce
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateWbSource")
	public ModelAndView updateWbSource(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile headImgURLfile,
			@RequestParam(required = false) String microBlogHttp,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String fansNum,
			@RequestParam(required = false) String province,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String prices,
			@RequestParam(required = false) String introduce,
			@RequestParam(required = false) String microBlog_id
			) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(advertiser(session)!=null && advertiser(session).getRolMark().equals("2")){
			PageData pd=new PageData();
			PageData pd2=new PageData();
			PageData pd3=new PageData();
			pd3.put("tag_id", microBlog_id);
			pd3=weiboService.getDataById(pd3);
			//上传图片
			String  ffile = DateUtil.getDays(), headImgURLName = "";
			if (null != headImgURLfile && !headImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + wbIMG + ffile;//文件上传路径
				headImgURLName = FileUpload.fileUp(headImgURLfile, filePath, this.get32UUID());	//执行上传
				pd2.put("headImgURL", Const.FILEPATHIMG + wbIMG + ffile + "/" + headImgURLName);
				//删除已存在的图片
				File filed1=new File(PathUtil.getClasspath()+pd3.getString("headImgURL"));
				if(filed1.exists()){
					filed1.delete();
				}
			}else{
				pd2.put("headImgURL", pd3.getString("headImgURL"));
			}
			////主表内容
			pd.put("microBlog_id", microBlog_id);
			pd.put("microBlogHttp", microBlogHttp);
			pd.put("checkedState", "2");
			pd.put("sourceState", "0");
			//码表基本信息内容
			pd2.put("f_id", microBlog_id);
			pd2.put("name", name);
			pd2.put("fansNum", fansNum);
			pd2.put("province", province);
			pd2.put("city", city);
			pd2.put("introduce", introduce);
			pd2.put("time", DateUtil.getTime());
			//码表推广形式和价格
			String pricess[]=prices.split(",");
			pd3.put("tag_id", microBlog_id);
			List<PageData> listType=type_priceService.getlistAllByfid2(pd3);//查找出所有的推广形式和价格
			//执行更新操作
			weiboService.update(pd);
			base_informationService.update(pd2);
			for (int i = 0; i < listType.size(); i++) {
				PageData pd4=new PageData();
				pd4.put("price", pricess[i]);
				pd4.put("id", listType.get(i).getString("id"));
				type_priceService.update(pd4);
			}
			mv.setViewName("redirect:liuSourcejump.do?num=3");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 *  微博添加资源
	 * @param request
	 * @param session
	 * @param headImgURL 头像
	 * @throws Exception 
	 * @param microBlogHttp
	 * @param name
	 * @param fansNum
	 * @param province
	 * @param city
	 * @param names
	 * @param prices
	 * @param introduce
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addWbSource")
	public ModelAndView addWbSource(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile headImgURLfile,
			@RequestParam(required = false) String microBlogHttp,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String fansNum,
			@RequestParam(required = false) String province,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String names,
			@RequestParam(required = false) String prices,
			@RequestParam(required = false) String introduce
			) throws Exception {
		ModelAndView mv=new ModelAndView();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		if(advertiser!=null && advertiser.getRolMark().equals("2")){
			//上传图片
			String  ffile = DateUtil.getDays(), headImgURLName = "";
			if (null != headImgURLfile && !headImgURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + wbIMG + ffile;//文件上传路径
				headImgURLName = FileUpload.fileUp(headImgURLfile, filePath, this.get32UUID());	//执行上传
			}
			//执行操作，添加到数据库
			//主表内容
			PageData pd=new PageData();
			pd.put("microBlogHttp", microBlogHttp);
			pd.put("checkedState", "2");
			pd.put("sourceState", "0");
			pd.put("microBlog_id", this.get32UUID());
			pd.put("f_id", advertiser.getAdvertiser_id());
			//码表基本信息内容
			PageData pd2=new PageData();
			pd2.put("headImgURL", Const.FILEPATHIMG + wbIMG + ffile + "/" + headImgURLName);
			pd2.put("base_information_id", this.get32UUID());
			pd2.put("name", name);
			pd2.put("fansNum", fansNum);
			pd2.put("province", province);
			pd2.put("city", city);
			pd2.put("introduce", introduce);
			pd2.put("f_id", pd.getString("microBlog_id"));
			pd2.put("time", DateUtil.getTime());
			String namess[]=names.split(",");
			String pricess[]=prices.split(",");
			//执行添加操作
			weiboService.save(pd);//添加资源主表信息
			base_informationService.save(pd2);//添加资源码表基础信息
			
			for (int i = 0; i < namess.length; i++) {//添加形式和价格
				PageData pd1=new PageData();
				pd1.put("id", this.get32UUID());
				pd1.put("price", pricess[i]);
				pd1.put("name", namess[i]);
				pd1.put("f_id", pd.getString("microBlog_id"));
				type_priceService.save(pd1);
			}
			mv.setViewName("redirect:liuSourcejump.do?num=3");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 跳转到微微博列表页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/weibo")
	public ModelAndView weibo() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		List<PageData> list=weiboService.getlistAll(pd);
		//Collections.shuffle(list);//混乱排列
		mv.addObject("list", list);
		mv.setViewName("information/xinmeiti/weibo/weibo_list");
		return mv;
	}
	/**
	 * 微博列表页的筛选
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/wbShaiXuan")
	@ResponseBody
	public Object wbShaiXuan() throws Exception{
		//ModelAndView mv=new ModelAndView();
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		String selectA=pd.getString("selectA");
		String selectB=pd.getString("selectB");
		String selectC=pd.getString("selectC");
		if(Tools.notEmpty(selectA)){
			if(selectA.indexOf("万以下")!=-1){//等于-1，说明该字符串中不存在"万以下"
				pd.put("minFansNum", 0);
				pd.put("maxFansNum", Integer.parseInt(selectA.replaceAll("万以下", ""))*10000);
			}else{
				selectA=Pattern.compile("[\u4e00-\u9fa5]").matcher(selectA).replaceAll("");//去除中文
				if(selectA.indexOf("-")==-1){//等于-1,说明不存在"-"
					pd.put("maxFansNum", Integer.parseInt(selectA)*10000);
				}else{
					selectA=selectA.replaceAll("-", ",");
					String sa[]=selectA.split(",");
					
					pd.put("minFansNum", Integer.parseInt(sa[0])*10000);
					pd.put("maxFansNum", Integer.parseInt(sa[1])*10000);
				}
			}
		}
		if(Tools.notEmpty(selectB)){
			if(selectB.indexOf("元以下")!=-1){//等于-1，说明该字符串中不存在"元以下"
				pd.put("minPrice", 0);
				pd.put("maxPrice", Integer.parseInt(selectB.replaceAll("元以下", "")));
			}else{
				selectB=Pattern.compile("[\u4e00-\u9fa5]").matcher(selectB).replaceAll("");//去除中文
				if(selectB.indexOf("-")==-1){//等于-1,说明不存在"-"
					pd.put("maxPrice", Integer.parseInt(selectB));
				}else{
					selectB=selectB.replaceAll("-", ",");
					String sb[]=selectB.split(",");
					pd.put("minPrice", Integer.parseInt(sb[0]));
					pd.put("maxPrice", Integer.parseInt(sb[1]));
				}
			}
		}
		if(Tools.notEmpty(selectC)){
			pd.put("area", selectC);
		}
		List<PageData> list=weiboService.getlistAll(pd);//获取微信朋友圈列表
		//Collections.shuffle(list);//混乱排列
		//mv.addObject("list", list);
		//mv.setViewName("information/xinmeiti/gzh/gzh_list");
		map.put("list", list);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 跳转到微信朋友圈详情页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/weiboDetail")
	public ModelAndView weiboDetail() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();

		List<PageData> list=type_priceService.getlistAllByfid2(pd);//获取所有的推广形式和价格
		pd=base_informationService.getDataByfid(pd);//获取基本信息
		List<PageData> list1=weiboService.getlistAll(pd);//大家都在看
		List<PageData> list2=weiboService.getlistAll(pd);//人气榜
		
		Collections.shuffle(list1);//混乱排列
		Collections.shuffle(list2);//混乱排列
		
		mv.addObject("list", list);
		mv.addObject("list1",list1);
		mv.addObject("list2",list2);
		mv.addObject("pd", pd);
		mv.setViewName("information/xinmeiti/weibo/weibo_detail");
		return mv;
	}
	
	//TODO 微博订单部分
	/**
	 * 进入微博的创建订单页面
	 * 页面js已做是否登录以及登录人身份的验证
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toWbCreateOredr")
	public ModelAndView toWbCreateOredr(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(advertiser(session)!=null && advertiser(session).getRolMark().equals("1")){
			PageData pd=new PageData();
			pd=this.getPageData();
			//获取订单资源的信息
			String tempname=pd.getString("namendp");
			String mark="";
			if(tempname.equals("1")){
				mark="硬广直发";
			}else if(tempname.equals("2")){
				mark="硬广转发";
			}else if(tempname.equals("3")){
				mark="软广直发";
			}else if(tempname.equals("4")){
				mark="软广转发";
			}
			pd.put("name", mark);
			pd=weiboService.detailgotoorder(pd);
			mv.addObject("pd",pd);
			mv.setViewName("information/xinmeiti/weibo/weibo_createorder");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 创建订单页面中的点击添加资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWbOrderAddResourceList")
	@ResponseBody
	public Object getWbOrderAddResourceList(HttpSession session) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		if(advertiser(session)!=null && advertiser(session).getRolMark().equals("1")){
			pd=this.getPageData();
			String ids[]=pd.getString("ids").split(",");//获取ID数组
			//获取添加资源中的资源列表信息（不包括价格）
			List<PageData> list=weiboService.getOrderAddResourceList(ids);
			List<PageData> list1=new ArrayList<PageData>();//获取满足条件的资源信息列表（接该类型的任务，price！=-1）
			//循环资源信息列表找到与其对应的价格
			for (int i = 0; i < list.size(); i++) {
				pd.put("tag_id", list.get(i).getString("microBlog_id"));
				PageData pd1=type_priceService.getdateByfidandName(pd);
				if(pd1!=null){
					if(!pd1.getString("price").equals("-1")){
						list.get(i).put("price", pd1.getString("price"));
						list1.add(list.get(i));
					}
				}
			}
			map.put("reslut", "success");
			map.put("list", list1);
		}else{
			map.put("reslut", "error");
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 创建订单中的资源选择(选择更多资源)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWbresourcelist")
	@ResponseBody
	public Object getWbresourcelist(HttpSession session) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		if(advertiser(session)!=null && advertiser(session).getRolMark().equals("1")){
			pd=this.getPageData();
			String array[]=pd.getString("ids").split(",");
			List<PageData> list=weiboService.getresourcelist(array);//选中的资源信息
			for (int i = 0; i < list.size(); i++) {
				PageData pdtype=new PageData();
				pdtype.put("tag_id", list.get(i).getString("microBlog_id"));
				pdtype.put("name", pd.getString("name"));
				pdtype=type_priceService.getdateByfidandName(pdtype);
				list.get(i).put("price", pdtype.getString("price"));
			}
			map.put("reslut", "success");
			map.put("list", list);
		}else{
			map.put("reslut", "error");
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 创建微博订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/createWborder")
	public ModelAndView createWborder(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(required = false) MultipartFile zhifapeitufile,
			@RequestParam(required = false) MultipartFile proveURLfile,
			@RequestParam(required = false) String type_name,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String beginTime,
			@RequestParam(required = false) String content,
			@RequestParam(required = false) String zhuanfa_link,
			@RequestParam(required = false) String zhuanfayu,
			@RequestParam(required = false) String remarks,
			@RequestParam(required = false) String ids
			) throws Exception{
		ModelAndView mv=new ModelAndView();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null && advertiser.getRolMark().equals("1")){
			PageData pd=new PageData();
			//上传图片
			String  ffile = DateUtil.getDays(), zhifapeituName = "",proveURLName = "";
			if (null != zhifapeitufile && !zhifapeitufile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + wbIMG + ffile;//文件上传路径
				zhifapeituName = FileUpload.fileUp(zhifapeitufile, filePath, this.get32UUID());	//执行上传
				pd.put("zhifapeitu", Const.FILEPATHIMG + wbIMG + ffile + "/" + zhifapeituName);
			}else{
				pd.put("zhifapeitu", "");
			}
			if (null != proveURLfile && !proveURLfile.isEmpty()){
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + wbIMG + ffile;//文件上传路径
				proveURLName = FileUpload.fileUp(proveURLfile, filePath, this.get32UUID());//执行上传
				pd.put("proveURL", Const.FILEPATHIMG + wbIMG + ffile + "/" + proveURLName);
			}else{
				pd.put("proveURL", "");
			}
			String order_number=sdfTime.format(new Date())+(int)((Math.random()*9+1)*100000);//订单号
			pd.put("id", this.get32UUID());
			pd.put("type_name", type_name);
			pd.put("name", name);
			pd.put("beginTime", beginTime);
			pd.put("content", content);
			pd.put("zhuanfa_link", zhuanfa_link);
			pd.put("zhuanfayu", zhuanfayu);
			pd.put("remarks", remarks);
			pd.put("order_time", DateUtil.getTime());
			pd.put("order_number", order_number);
			pd.put("f_id", advertiser.getAdvertiser_id());
			weiboService.insertOrder(pd);//添加订单
			
			PageData pd1=new PageData();
			pd1.put("order_number", order_number);
			pd1.put("order_state", "1");
			pd1.put("mjzorder_state", "1");
			pd1.put("resource_type", "3");
			String arrid[]=ids.split(",");
			for (int i = 0; i < arrid.length; i++) {
				pd1.put("id", this.get32UUID());
				pd1.put("resource_id", arrid[i]);
				orderresourceService.save(pd1);//添加订单资源
			}
			mv.setViewName("redirect:/api/xmtv1/toGgzorderWblist.do");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 广告主进入微博订单列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toGgzorderWblist")
	public ModelAndView toGgzorderWblist(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		//获取session
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null && advertiser.getRolMark().equals("1")){//已登录且身份为广告主
			PageData pd=new PageData();
			pd=this.getPageData();
			pd.put("f_id", advertiser.getAdvertiser_id());
			List<PageData> list=weiboService.getOrderInformationList(pd);
			mv.addObject("pd", pd);
			mv.addObject("list", list);
			mv.setViewName("information/xinmeiti/weibo/weibo_ggzpaidan");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 媒介主进入微博订单列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toMjzorderWblist")
	public ModelAndView toMjzorderWblist(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		//去微信朋友圈订单表中取查找所有指定用户的订单
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
			pd.put("f_id", advertiser.getAdvertiser_id());
			List<PageData> list=weiboService.getMjzOrderInformationList(pd);//查询微信朋友圈的资源列表信息
			mv.addObject("list", list);
			mv.setViewName("information/xinmeiti/weibo/weibo_paidan");
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
/***********************微博end****************************************************/
	
	
/*********************************************媒介主个人中心的跳转star****************************************************/
	/**
	 * 
	 * 媒介主个人中心中的跳转
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/personZDjump")
	public ModelAndView personZDjump(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		if(advertiser!=null && advertiser.getRolMark().equals("2")){
			PageData pd=new PageData();
			pd=this.getPageData();
			int num=Integer.parseInt(pd.get("num").toString());
			switch (num) {
			case 1:
				//跳转到个人中心
				//1.查询资源总数
				pd.put("f_id", advertiser.getAdvertiser_id());
				pd=advertiserService.selCountNum(pd);
				//2.查询待审核资源总数
				PageData pd1=new PageData();
				pd1.put("f_id", advertiser.getAdvertiser_id());
				pd1.put("checkedState", "2");
				pd1=advertiserService.selCountNum(pd1);
				//3.查询不通过的资源总数
				PageData pd2=new PageData();
				pd2.put("f_id", advertiser.getAdvertiser_id());
				pd2.put("checkedState", "0");
				pd2=advertiserService.selCountNum(pd2);
				//4.查询下架的资源总数
				PageData pd3=new PageData();
				pd3.put("f_id", advertiser.getAdvertiser_id());
				pd3.put("sourceState", "0");
				pd3=advertiserService.selCountNum(pd3);

				pd.put("numzd", pd1.get("numz").toString());//待审核
				pd.put("numzb", pd2.get("numz").toString());//不通过
				pd.put("numzx", pd3.get("numz").toString());//已下架
				
				mv.addObject("pd", pd);
				mv.setViewName("information/xinmeiti/meijiezhu/personal_center");
				break;
			case 2:
				//跳转到资源管理主页
				mv.setViewName("redirect:liuSourcejump.do?num=1");
				break;
			case 3:
				//拍单列表
				mv.setViewName("redirect:/api/xmtv1/toMjzorderGzhlist");
				break;
			case 4:
				//账单查询
				mv.setViewName("information/xinmeiti/meijiezhu/media_query");
				break;
			case 5:
				//用户中心
				mv.setViewName("information/xinmeiti/meijiezhu/edit_data");
				break;
			default:
				break;
			}
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	//TODO 6大模块资源管理页的跳转
	/**
	 * 
	 * 6大模块资源管理页的跳转
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/liuSourcejump")
	public ModelAndView liuSourcejump(HttpSession session,Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		if(advertiser!=null && advertiser.getRolMark().equals("2")){
			PageData pd=new PageData();
			pd=this.getPageData();
			int num=Integer.parseInt(pd.get("num").toString());
			pd.put("f_id", advertiser.getAdvertiser_id());
			switch (num) {
			case 1:
				//跳转到微信公众号资源管理页
				//获取资源列表信息，包括价格
				List<PageData> list=gzhService.getsourcelistbyfid(pd);
				for (int i = 0; i < list.size(); i++) {
					String prices[]=list.get(i).getString("prices").split(",");
					String names[]=list.get(i).getString("names").split(",");
					for (int j = 0; j < names.length; j++) {
						if(names[j].equals("单图文硬广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price1", "不接");
							}else{
								list.get(i).put("price1", prices[j]);
							}
						}else if(names[j].equals("单图文软广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price2", "不接");
							}else{
								list.get(i).put("price2", prices[j]);
							}
						}else if(names[j].equals("多图文第一条硬广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price3", "不接");
							}else{
								list.get(i).put("price3", prices[j]);
							}
						}else if(names[j].equals("多图文第一条软广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price4", "不接");
							}else{
								list.get(i).put("price4", prices[j]);
							}
						}else if(names[j].equals("多图文第二条硬广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price5", "不接");
							}else{
								list.get(i).put("price5", prices[j]);
							}
						}else if(names[j].equals("多图文第二条软广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price6", "不接");
							}else{
								list.get(i).put("price6", prices[j]);
							}
						}else if(names[j].equals("多图文第3~N条硬广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price7", "不接");
							}else{
								list.get(i).put("price7", prices[j]);
							}
						}else if(names[j].equals("多图文第3~N条软广")){
							if(prices[j].equals("-1")){
								list.get(i).put("price8", "不接");
							}else{
								list.get(i).put("price8", prices[j]);
							}
						}
					}
				}
				mv.addObject("list", list);
				mv.setViewName("information/xinmeiti/gzh/gzh_resource_list");
				break;
			case 2:
				//跳转到微信朋友圈资源管理页
				List<PageData> list1p=pengyouquanService.getlistAllbyfid(pd);//获取指定媒介主下的微信朋友圈资源
				mv.addObject("list1p", list1p);
				mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_resource_list");
				break;
			case 3:
				//跳转到微博资源管理页
				List<PageData> list1w=weiboService.getsourcelistbyfid(pd);//获取指定媒介主下的微信朋友圈资源
				for (int i = 0; i < list1w.size(); i++) {
					String prices[]=list1w.get(i).getString("prices").split(",");
					String names[]=list1w.get(i).getString("names").split(",");
					for (int k = 0; k < names.length; k++) {
						if(names[k].equals("硬广直发")){
							if(prices[k].equals("-1")){
								list1w.get(i).put("price1", "不接");
							}else{
								list1w.get(i).put("price1", prices[k]);
							}
						}else if(names[k].equals("硬广转发")){
							if(prices[k].equals("-1")){
								list1w.get(i).put("price2", "不接");
							}else{
								list1w.get(i).put("price2", prices[k]);
							}
						}else if(names[k].equals("软广直发")){
							if(prices[k].equals("-1")){
								list1w.get(i).put("price3", "不接");
							}else{
								list1w.get(i).put("price3", prices[k]);
							}
						}else if(names[k].equals("软广转发")){
							if(prices[k].equals("-1")){
								list1w.get(i).put("price4", "不接");
							}else{
								list1w.get(i).put("price4", prices[k]);
							}
						}
						
					}
				}
				mv.addObject("list1w", list1w);
				mv.setViewName("information/xinmeiti/weibo/weibo_resource_list");
				break;
			case 4:
				//跳转到网红直播资源管理页
				 List<PageData> volist = videopaltService.videopaltListPage(page);
				 mv.addObject("volist", volist);
				 mv.addObject("pd", pd);
				 mv.setViewName("information/xinmeiti/media_zhibo/media_zhibo");
				break;
			case 5:
				//跳转到新闻媒体发布资源管理页
				 page.setPd(pd);
				 List<PageData> molist = mediaService.mediaListPage(page);
				 mv.addObject("molist", molist);
				 mv.addObject("pd", pd);
				 mv.setViewName("information/xinmeiti/media_xinwen/media_xinwen");
				break;
			case 6:
				//跳转到黄金广告商资源管理页
				page.setPd(pd);
				 List<PageData> aolist = adverService.adverlistPageU(page);
				 mv.addObject("aolist", aolist);
				 mv.addObject("pd", pd);
				 mv.setViewName("information/xinmeiti/media_adv/media_adv");
				break;
			default:
				break;
			}
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	//TODO 6大模块添加资源页面的跳转
	/**
	 * 
	 * 6大模块添加资源页面的跳转
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/liuAddSourcejump")
	public ModelAndView liuAddSourcejump(Page page,HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		if(advertiser!=null && advertiser.getRolMark().equals("2")){
			PageData pd=new PageData();
			pd=this.getPageData();
			int num=Integer.parseInt(pd.get("num").toString());
			switch (num) {
			case 1:
				//跳转到微信公众号添加资源页
				mv.addObject("pd",pd);
				mv.addObject("caozuoType", "insert");
				mv.addObject("msg", "addGzhSource");
				mv.setViewName("information/xinmeiti/gzh/gzh_addresource");
				break;
			case 2:
				//跳转到微信朋友圈添加资源页
				mv.addObject("pd",pd);
				mv.addObject("caozuoType", "insert");
				mv.addObject("msg", "addPyqSource");
				mv.setViewName("information/xinmeiti/pengyouquan/pengyouquan_addresource");
				break;
			case 3:
				//跳转到微博添加资源页
				mv.addObject("pd",pd);
				mv.addObject("caozuoType", "insert");
				mv.addObject("msg", "addWbSource");
				mv.setViewName("information/xinmeiti/weibo/weibo_addresource");
				break;
			case 4:
				//跳转到网红直播添加资源页
				List<PageData> livelist = livePlatformService.getlistPage(page);
				mv.addObject("msg", "save");
				mv.addObject("livelist", livelist);
				mv.addObject("pd", pd);
				mv.addObject("res", "添加");
				mv.setViewName("information/xinmeiti/media_zhibo/media_zhibo_add");
				break;
			case 5:
				//跳转到新闻媒体发布添加资源页
				List<PageData> weblist = webResService.getlistAll(pd);// 网站选择
				List<PageData> channellist = webChannelService.getlistPage(page);// 频道选择
				List<PageData> levellist = levelService.getlistPage(page);// 入口等级
				List<PageData> rukoulist = rukouTypeService.getlistPage(page);// 入口类型
				List<PageData> textlist = textLink_typeService.getlistPage(page);// 正文带链接
				mv.addObject("msg", "saveMedia");
				mv.addObject("weblist", weblist);
				mv.addObject("channellist", channellist);
				mv.addObject("levellist", levellist);
				mv.addObject("rukoulist", rukoulist);
				mv.addObject("textlist", textlist);
				mv.addObject("res", "添加");
				mv.addObject("pd", pd);
				mv.setViewName("information/xinmeiti/media_xinwen/media_xinwen_add");
				break;
			case 6:
				//跳转到黄金广告商添加资源页
				List<PageData> weblist1 = webResService.getlistAll(pd);//网站选择
				List<PageData> channellist1 = webChannelService.getlistPage(page);//频道选择
				List<PageData> levellist1 = levelService.getlistPage(page);//入口等级
				List<PageData> rukoulist1 = rukouTypeService.getlistPage(page);//入口类型
				List<PageData> textlist1 = textLink_typeService.getlistPage(page);//正文带链接
				mv.addObject("msg", "saveAdver");
				mv.addObject("pd", pd);
				mv.addObject("weblist", weblist1);
				mv.addObject("channellist",channellist1);
				mv.addObject("levellist", levellist1);
				mv.addObject("rukoulist", rukoulist1);
				mv.addObject("textlist", textlist1);
				mv.addObject("res", "添加");
				mv.setViewName("information/xinmeiti/media_adv/media_adv_add");
				break;
			default:
				break;
			}
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 媒介主或广告主登录后点击进入我的星推（个人中心）
	 * @return
	 */
	/*@RequestMapping(value="/intoPerson")
	public ModelAndView intoPerson(HttpSession session){
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		String rolMark=pd.getString("rolMark");//1-位广告主，2-位媒介主
		if(rolMark.equals("1")){//说明为广告主
			
		}else if(rolMark.equals("2")){//说明为媒介主
			mv.setViewName("information/xinmeiti/meijiezhu/personal_center");
		}
		return mv;
	}*/
/*********************************************媒介主个人中心的跳转end****************************************************/	
/*********************************************广告主个人中心的跳转star*****************************/
	//TODO 广告主个人中心的跳转star
	/**
	 * 广告主个人中心跳转
	 * @return
	 */
	@RequestMapping(value="/personGgzJump")
	public ModelAndView personGgzJump(HttpSession session){
		ModelAndView mv=new ModelAndView();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		if(advertiser!=null && advertiser.getRolMark().equals("1")){
			PageData pd=new PageData();
			pd=this.getPageData();
			int key=Integer.parseInt(pd.get("num").toString());
			switch (key) {
			case 1:
				mv.setViewName("information/xinmeiti/guanggaozhu/mycount");
				break;
			case 2:
				
				break;
			case 3:
		
				break;
			case 4:
		
				break;
			default:
				break;
			}
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
/*********************************************广告主个人中心的跳转end******************************/
/***********************用户操作star************************/
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping(value="/toLogin")
	public ModelAndView toLogin(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("information/xinmeiti/register/login");
		return mv;
	}
	/**
	 * 跳转到忘记密码页
	 * @return
	 */
	@RequestMapping(value="/toWjmm")
	public ModelAndView toWjmm(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("information/xinmeiti/wjmm/wjmm");
		return mv;
	}
	/**
	 * 重置密码时验证该手机号是否已经注册
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/zhuceYesOrNo")
	@ResponseBody
	public Object zhuceYesOrNo() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		PageData pd1=advertiserService.selByPhone(pd);
		String result="";
		if(pd1!=null){
			result="success";
		}else{
			result="error";
		}
		map.put("result", result);
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 重置密码
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/resetPsw")
	@ResponseBody
	public Object resetPsw() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		PageData pd1=advertiserService.selByPhone(pd);
		String result="";
		if(pd1!=null){
			result="success";
		}else{
			result="error";
		}
		pd1.put("password", MD5.md5(pd.getString("password")));
		advertiserService.updataPsw(pd1);
		map.put("result", result);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 用户资料编辑
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editInformation")
	public ModelAndView editInformation(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(sessionIsOrNo()){
			PageData pd=new PageData();
			pd=this.getPageData();
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
			pd.put("advertiser_id", advertiser.getAdvertiser_id());
			pd.put("rolMark", advertiser.getRolMark());
			advertiserService.editInformation(pd);
			advertiser.setNikeName(pd.getString("nikeName"));
			advertiser.setLinkman(pd.getString("linkman"));
			advertiser.setEmail(pd.getString("email"));
			if(advertiser.getRolMark().equals("1")){
				advertiser.setCompany(pd.getString("company"));
				mv.setViewName("redirect:/api/xmtv1/personGgzJump?num=1");
			}
			if(advertiser.getRolMark().equals("2")){
				advertiser.setWeixinAccount(pd.getString("weixinAccount"));
				mv.setViewName("redirect:/api/xmtv1/personZDjump?num=1");
			}
			session.setAttribute(Const.SESSION_Advertiser, advertiser);//更新session
		}else{
			mv.setViewName("redirect:/api/xmtv1/toLogin");
		}
		return mv;
	}
	/**
	 * 修改密码
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/changePassword")
	@ResponseBody
	public Object changePassword(HttpSession session) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		String result="";
		if(sessionIsOrNo()){
			pd=this.getPageData();
			Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
			if(MD5.md5(pd.getString("oldpassword")).equals(advertiser.getPassword())){
				pd.put("advertiser_id", advertiser.getAdvertiser_id());
				pd.put("password", MD5.md5(pd.getString("newpassword")));
				advertiserService.updataPsw(pd);
				advertiser.setAdvertiser_id(MD5.md5(pd.getString("newpassword")));
				session.setAttribute(Const.SESSION_Advertiser, advertiser);//更新session
				result="success";
			}else{
				result="原始密码输入错误";
			}
		}else{
			result="error";
		}
		map.put("result", result);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 跳转到注册页面(可以注册为广告主或者媒介主)
	 * @return
	 */
	@RequestMapping(value="/toRegister")
	public ModelAndView toRegister(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("information/xinmeiti/register/register");
		return mv;
	}
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(value="/tuichudl")
	public ModelAndView tuichudl(HttpSession session){
		ModelAndView mv = new ModelAndView("redirect:/api/xmtv1/index");
		session.removeAttribute(Const.SESSION_Advertiser);
		return mv;	
	}
	/**
	 * 实现广告主、媒介主的注册
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	public Object register() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String canShu[]=pd.getString("canShu").split(",");//获取参数数组
		//根据手机号去查找，判断该手机号是否已经注册
		pd.put("phone", canShu[1]);
		pd.put("rolMark", canShu[0]);
		pd=advertiserService.selByPhone(pd);
		if(pd!=null){//说明该手机号已经注册
			errInfo="手机号已经注册";
		}else{
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			
			String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);//获取session中的验证码
			if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(canShu[3])){
				String password = MD5.md5(canShu[2]);//密码加密
				PageData pd1 = new PageData();
				
				pd1.put("phone", canShu[1]);
				pd1.put("password", password);
				pd1.put("linkmanQQ", canShu[4]);
				pd1.put("rolMark", canShu[0]);
				pd1.put("advertiser_id", this.get32UUID());
				advertiserService.addAdvertiser(pd1);
				map.put("rolMark", canShu[0]);
				map.put("phone", canShu[1]);
				map.put("password", canShu[2]);
				errInfo = "success";//验证成功
			}else{
				errInfo="验证码不正确";
			}
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 实现广告主、媒介主的登录
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public Object login() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String result="";
		PageData pd=new PageData();
		pd=this.getPageData();
		String rolMark=pd.getString("rolMark");
		String validate=pd.getString("validate");
		String password=MD5.md5(pd.getString("password"));
		pd.put("password", password);
		pd=advertiserService.selByPhoneAndPaw(pd);
		if(pd!=null){
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);//获取session中的验证码
			if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(validate)){
				Advertiser advertiser=new Advertiser();
				advertiser.setPhone(pd.getString("phone"));
				advertiser.setPassword(pd.getString("password"));
				advertiser.setLinkmanQQ(pd.getString("linkmanQQ"));
				advertiser.setAdvertiser_id(pd.getString("advertiser_id"));
				advertiser.setRolMark(rolMark);
				advertiser.setNikeName(pd.getString("nikeName"));
				advertiser.setLinkman(pd.getString("linkman"));
				advertiser.setEmail(pd.getString("email"));
				advertiser.setCompany(pd.getString("company"));
				advertiser.setWeixinAccount(pd.getString("weixinAccount"));
				advertiser.setYiTiXian(Double.parseDouble(pd.get("yiTiXian")+""));
				advertiser.setZhangHYE(Double.parseDouble(pd.get("zhangHYE").toString()));
				advertiser.setZongSY(Double.parseDouble(pd.get("zongSY")+""));
				advertiser.setDongJJE(Double.parseDouble(pd.get("dongJJE")+""));
				
				session.setAttribute(Const.SESSION_Advertiser, advertiser);
				
				session.removeAttribute(Const.SESSION_SECURITY_CODE);//移除验证码session
				result="success";
			}else{
				result="验证码错误";
			}
			
		}else{
			result="用户名或密码错误";
		}
		map.put("result", result);
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取短信验证码
	 * @return
	 */
	@RequestMapping(value="/getSms")
	@ResponseBody
	public Object getSms(){
		PageData pd = new PageData();
		pd= this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String phone = pd.getString("phone");
		map = SmsUtil.sendMsM(phone);
		map.put("phone", phone);
		/*if(map.size()>0){
			map.put("msg", "success");
		}else{
			map.put("msg", "error");
		}*/
		return AppUtil.returnObject(pd, map);
	}
/***********************用户操end************************/
	
	
/***********************系统服务号管理操作**********************************************************************************/
//1.微信公众号
	//TODO 系统服务号管理操作
	/**
	 *跳转到微信公众号列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysGzhlistPage")
	public ModelAndView systoGzhList(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list=gzhService.sysgetlistPage(page);
		mv.addObject("pd", pd);
		mv.addObject("list",list);
		mv.setViewName("system/gzh/gzh_list");
		return mv;
	}
	/**
	 * 微信公众号审核操作
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysGzhChecked")
	@ResponseBody
	public Object sysGzhChecked() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd=new PageData();
		pd=this.getPageData();
		int key=Integer.parseInt(pd.getString("num"));
		switch (key) {
		case 1:
			//单个审核不通过
			pd.put("checkedState", "0");
			gzhService.syscheckedNo(pd);
			break;
		case 2:
			//单个审核通过
			pd.put("checkedState", "1");
			pd.put("sourceState", "1");
			gzhService.syscheckedYes(pd);
			break;
		case 3:
			//批量审核不通过
			String ids=pd.getString("ids");
			String Arrayids[]=ids.split(",");//分割成数组
			gzhService.syscheckedNoAll(Arrayids);
			break;
		case 4:
			//批量审核通过
			String ids1=pd.getString("ids");
			String Arrayids1[]=ids1.split(",");//分割成数组
			gzhService.syscheckedYesAll(Arrayids1);
			break;
		default:
			break;
		}
		map.put("msg", "success");
		return AppUtil.returnObject(pd, map);
	}
//2.微信朋友圈
	/**
	 *跳转到微信朋友圈列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysPyqlistPage")
	public ModelAndView sysPyqlistPage(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list=pengyouquanService.sysgetlistPage(page);
		mv.addObject("pd", pd);
		mv.addObject("list",list);
		mv.setViewName("system/pengyouquan/pengyouquan_list");
		return mv;
	}
	/**
	 * 微信朋友圈审核操作
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysPyqChecked")
	@ResponseBody
	public Object sysPyqChecked() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd=new PageData();
		pd=this.getPageData();
		int key=Integer.parseInt(pd.getString("num"));
		switch (key) {
		case 1:
			//单个审核不通过
			pd.put("checkedState", "0");
			pengyouquanService.syscheckedNo(pd);
			break;
		case 2:
			//单个审核通过
			pd.put("checkedState", "1");
			pd.put("sourceState", "1");
			pengyouquanService.syscheckedYes(pd);
			break;
		case 3:
			//批量审核不通过
			String ids=pd.getString("ids");
			String Arrayids[]=ids.split(",");//分割成数组
			pengyouquanService.syscheckedNoAll(Arrayids);
			break;
		case 4:
			//批量审核通过
			String ids1=pd.getString("ids");
			String Arrayids1[]=ids1.split(",");//分割成数组
			pengyouquanService.syscheckedYesAll(Arrayids1);
			break;
		default:
			break;
		}
		map.put("msg", "success");
		return AppUtil.returnObject(pd, map);
	}
//3.微博
	/**
	 *跳转到微博列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysWblistPage")
	public ModelAndView sysWblistPage(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list=weiboService.sysgetlistPage(page);
		mv.addObject("pd", pd);
		mv.addObject("list",list);
		mv.setViewName("system/weibo/weibo_list");
		return mv;
	}
	/**
	 * 微博审核操作
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysWbChecked")
	@ResponseBody
	public Object sysWbChecked() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd=new PageData();
		pd=this.getPageData();
		int key=Integer.parseInt(pd.getString("num"));
		switch (key) {
		case 1:
			//单个审核不通过
			pd.put("checkedState", "0");
			weiboService.syscheckedNo(pd);
			break;
		case 2:
			//单个审核通过
			pd.put("checkedState", "1");
			pd.put("sourceState", "1");
			weiboService.syscheckedYes(pd);
			break;
		case 3:
			//批量审核不通过
			String ids=pd.getString("ids");
			String Arrayids[]=ids.split(",");//分割成数组
			weiboService.syscheckedNoAll(Arrayids);
			break;
		case 4:
			//批量审核通过
			String ids1=pd.getString("ids");
			String Arrayids1[]=ids1.split(",");//分割成数组
			weiboService.syscheckedYesAll(Arrayids1);
			break;
		default:
			break;
		}
		map.put("msg", "success");
		return AppUtil.returnObject(pd, map);
	}
//3.视频直播
	/**
	 *跳转到微博列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysZblistPage")
	public ModelAndView sysZblistPage(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list=videopaltService.sysgetlistPage(page);
		mv.addObject("pd", pd);
		mv.addObject("list",list);
		mv.setViewName("system/xinWen/xinWen_list");
		return mv;
	}
	/**
	 * 视频直播审核操作
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="/sysZbChecked")
	@ResponseBody
	public Object sysZbChecked() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd=new PageData();
		pd=this.getPageData();
		int key=Integer.parseInt(pd.getString("num"));
		switch (key) {
		case 1:
			//单个审核不通过
			pd.put("checkedState", "0");
			videopaltService.syscheckedNo(pd);
			break;
		case 2:
			//单个审核通过
			pd.put("checkedState", "1");
			pd.put("sourceState", "1");
			videopaltService.syscheckedYes(pd);
			break;
		case 3:
			//批量审核不通过
			String ids=pd.getString("ids");
			String Arrayids[]=ids.split(",");//分割成数组
			videopaltService.syscheckedNoAll(Arrayids);
			break;
		case 4:
			//批量审核通过
			String ids1=pd.getString("ids");
			String Arrayids1[]=ids1.split(",");//分割成数组
			videopaltService.syscheckedYesAll(Arrayids1);
			break;
		default:
			break;
		}
		map.put("msg", "success");
		return AppUtil.returnObject(pd, map);
	}
	
	//判断session是否存在，不存在进登录页面
	public boolean sessionIsOrNo(){
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		if(advertiser!=null){
			return true;
		}
		return false;
	}
	//获取session对象
	public Advertiser advertiser(HttpSession session){
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		return advertiser;
	}
	//判断是否登录以及登录人的身份
	@RequestMapping(value="/panduanShenfen")
	@ResponseBody
	public Object panduanShenfen(HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd=new PageData();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		String result="";
		if(advertiser!=null){
			if(advertiser.getRolMark().equals("1")){
				result="1";
			}else{
				result="2";
			}
		}else{
			result="0";
		}
		map.put("result", result);
		return AppUtil.returnObject(pd, map);
	}
}
