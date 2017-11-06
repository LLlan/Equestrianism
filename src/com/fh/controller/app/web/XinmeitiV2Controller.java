package com.fh.controller.app.web;

import java.io.PrintWriter;
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
import com.fh.service.information.category.CategoryService;
import com.fh.service.information.comimages.ComimagesService;
import com.fh.service.information.company.CompanyService;
import com.fh.service.information.good.GoodService;
import com.fh.service.information.goodimages.GoodimagesService;
import com.fh.service.information.member.MemberService;
import com.fh.service.information.pictures.PicturesService;
import com.fh.service.information.xinmeiti.adver.AdverService;
import com.fh.service.information.xinmeiti.adverOifr.AdverOifrService;
import com.fh.service.information.xinmeiti.base_information.Base_informationService;
import com.fh.service.information.xinmeiti.media.MediaService;
import com.fh.service.information.xinmeiti.type_price.Type_priceService;
import com.fh.service.information.xinmeiti.videopalt.VideopaltService;
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
import com.fh.util.MapDistance;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
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
@RequestMapping(value="/api/xmtv2")
public class XinmeitiV2Controller extends BaseController{
	 @Resource(name = "videopaltService")
	 private VideopaltService videopaltService;
	 @Resource(name = "mediaService")
	 private MediaService mediaService;
	 @Resource(name = "adverService")
	 private AdverService adverService;
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
	 @Resource(name = "livePlatformService")
	 private LivePlatformService livePlatformService;
	 @Resource(name = "adverOifrService")
	 private AdverOifrService adverOifrService;
	 @Resource(name = "base_informationService")
	 private Base_informationService base_informationService;
	 @Resource(name = "type_priceService")
	 private Type_priceService type_priceService;
	 
	 public static final String IMGPATH = "xmtv2/";
//==================================直播资源========================================================	 
	 /*
	  * 跳转Videopalt列表（广告主方向）
	  */
	 @RequestMapping(value = "/videopaltlistPage")
	 public ModelAndView listProprietor(Page page) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 page.setPd(pd);
		 List<PageData> videopallist = videopaltService.videopaltListPage(page);
		 mv.addObject("videopallist", videopallist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/zhibo/zhibo");
		 return mv;
	 }
	 
	 /*
	  * TODO 跳转网红直播资源页面（媒体主方向）
	  */
	  @RequestMapping("/videopaltOwnerlistPage")
	 public ModelAndView videopaltOwnerlistPage(Page page,HttpSession session) throws Exception{
		 ModelAndView mv = new ModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 page.setPd(pd);
		 Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		 pd.put("mediaOwner_id", advertiser.getAdvertiser_id());
		 List<PageData> volist = videopaltService.videopaltListPage(page);
		 for (int i = 0; i < volist.size(); i++) {
			
		}
		 mv.addObject("volist", volist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/media_zhibo/media_zhibo");
		 return mv;
	 }
	 
	/*
	 * 跳转到Videopalt添加页面
	 */
	 @RequestMapping(value = "/goAdd")
	 public ModelAndView goAddVideopalt(Page page) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 List<PageData> livelist = livePlatformService.getlistPage(page);
		 mv.addObject("msg", "save");
		 mv.addObject("livelist", livelist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/media_zhibo/media_zhibo_add");
		 return mv;
	 }
	 
	 /*
	  * 保存Videopalt信息
	  */
	 @RequestMapping(value = "save")
	 public ModelAndView saveVideopalt(
			 HttpServletRequest request,
			 String platformName,//平台
			 String name,//昵称
			 String number,//房间ID
			 @RequestParam(required=false) MultipartFile headfile,//头像
			 String sex,//性别
			 String province,//省
			 String city,//市
			 String fansnumber,//粉丝数
			 @RequestParam(required=false) MultipartFile filefans,//粉丝数截图
			 String[] price,//价格
//			 String price2,//直播植入
			 String videoURL,//往期案例
			 String introduce,//简介
			 HttpSession session
			 ) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 String  ffile = DateUtil.getDays(), fileName = "";
		 /* 存入tb_video_platform表*/
		 pd.put("videoPlatform_id", this.get32UUID());//主键
		 pd.put("platformName", platformName);//直播平台
		 pd.put("number", number);//房间ID
		 pd.put("sex", sex);//性别
		 if(filefans != null){
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			fileName = FileUpload.fileUp(filefans, filePath, this.get32UUID());
			pd.put("fansNumImgURL", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);//粉丝数截图
		 }else{
			 pd.put("fansNumImgURL","");//粉丝数截图
		 }
		 pd.put("sourceState", 0);//资源状态（0-已停用，1-启用）
		 pd.put("checkedState",2 );//资源审核状态(0-审核不通过，1-审核通过，2-待审核)
		 pd.put("videoURL", videoURL);//往期案例
		 Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		 pd.put("mediaOwner_id", advertiser.getAdvertiser_id());//媒介主ID
		 videopaltService.saveVideopalt(pd);
		 
		 /*存入tb_base_information表*/
		 pd.put("base_information_id", this.get32UUID());
		 if(headfile != null){
			 String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			 fileName = FileUpload.fileUp(headfile, filePath, this.get32UUID());
			 pd.put("headImgURL", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);//头像 or logo
		 }else{
			 pd.put("headImgURL","");//头像 or logo
		 }
		 pd.put("name", name);//昵称
		 pd.put("fansNum", fansnumber);//粉丝数
		 pd.put("province", province);//省
		 pd.put("city", city);//市
		 pd.put("introduce", introduce);//资源简介
		 pd.put("time", DateUtil.getTime());
		 base_informationService.savebase(pd);
		 
		 // TODO 存入tb_type_price（有新建数据表，此处可能需要修改）
		 String array[] = {"专场直播","直播插入"};
		 for(int i =0;i < price.length;i++){
			 pd.put("id", this.get32UUID());
			 pd.put("f_id", pd.get("videoPlatform_id"));
			 pd.put("name", array[i]);
			 pd.put("price", price[i]);
			 type_priceService.save(pd);
		 }
		 mv.addObject("msg", "success");
		 mv.setViewName("redirect:/api/xmtv2/videopaltOwnerlistPage.do");
		 return mv;
	 }
	 
	 /*
	  * 跳转到Videopalt修改页面
	  */
	 @RequestMapping(value = "Edit")
	 public ModelAndView toEditVideopalt(Page page) throws Exception{
		 ModelAndView mv = new ModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 pd.put("videoPlatform_id", pd.get("tid"));
//		 pd = videopaltService.getDetailByID(pd);
		 List<PageData> livelist = livePlatformService.getlistPage(page);
//		 pd = videopaltService.getDetailByIDAll(page);
		 List<PageData> vplist = (List<PageData>) videopaltService.getDetailByIDAll(pd);
		 pd = vplist.get(0);
		 mv.addObject("msg", "update");
		 mv.addObject("res", "修改");
		 mv.addObject("livelist", livelist);
		 mv.addObject("vplist", vplist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/media_zhibo/media_zhibo_add");
		 return mv;
	 }
	 
	 /*
	  * 修改Videopalt信息
	  */
	 @RequestMapping(value = "update")
	 public ModelAndView updateVideopalt(
			 String videoPlatform_id,//ID
			 String base_information_id,
			 String platformName,//平台
			 String name,//昵称
			 String number,//房间ID
			 @RequestParam(required=false) MultipartFile file,//头像
			 String sex,//性别
			 String province,//省
			 String city,//市
			 String fansnumber,//粉丝数
			 @RequestParam(required=false) MultipartFile filefans,//粉丝数截图
//			 String price,//专场直播
//			 直播植入
			 String videoURL,//往期案例
			 String introduce,//简介
			 HttpSession session
			 ) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 String  ffile = DateUtil.getDays(), fileName = "";
		 
		 /*修改tb_base_information表*/
		 pd.put("videoPlatform_id", videoPlatform_id);//id
		 pd.put("base_information_id", base_information_id);//从表ID
		 if(file != null){
			 String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			 fileName = FileUpload.fileUp(file, filePath, this.get32UUID());
			 pd.put("headImgURL", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);//头像 or logo
		 }else {
			pd.put("headImgURL", file);
		}
		 pd.put("fansNum", fansnumber);//粉丝数
		 pd.put("province", province);//省
		 pd.put("city", city);
		 pd.put("introduce", introduce);//资源简介
		 pd.put("name", name);//昵称
		 base_informationService.update(pd);
		 
		 /* 修改tb_video_platform表*/
		 pd.put("platformName", platformName);//直播平台
		 pd.put("number", number);//房间ID
		 pd.put("sex", sex);//性别
		 if(fansnumber != null){
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());
		 	pd.put("fansNumImgURL", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);//粉丝数截图
		 }else {
			pd.put("fansNumImgURL", filefans);
		}
		 Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		 pd.put("mediaOwner_id", advertiser.getAdvertiser_id());
		 videopaltService.updateVideopalt(pd);
		 
		 mv.addObject("msg","success");
		 mv.setViewName("redirect:/api/xmtv2/videopaltOwnerlistPage.do");
		 return mv;
	 }
	 
	 /*
	  * 删除Videopalt信息
	  */
	 @RequestMapping(value = "del")
	 public void delVideopalt(PrintWriter writer) throws Exception{
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 pd.put("videoPlatform_id", pd.getString("tid"));
		 
		try {
			videopaltService.delVideopalt(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		writer.close();
		
	 }
	 
	 /*
	  * 跳转详情页面
	  */
	 @RequestMapping(value ="detailsVideopalt")
	 public ModelAndView detailsVideopalt(Page page) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 pd.put("videoPlatform_id",pd.get("tid"));
		 
		 
		 
		 List<PageData> volist = videopaltService.getNotID(pd);
		 pd=videopaltService.getfid(pd);
		 List list=  videopaltService.getDetailByIDAll(pd);
		
		 
		 Collections.shuffle(volist);//打乱顺序
		 mv.addObject("list", list);
		 mv.addObject("pd", pd);
		 mv.addObject("volist", volist);
		 mv.setViewName("information/xinmeiti/zhibo/zhibo_detail");
		 return mv;
		 
	 }
	 
	 
//=====================================media模块===========================================	 
//	 功能：内容为添加新闻发布和广告招商的资源
	 
	 /*
	  * 跳转Media列表(广告主方向)
	  */
	 @RequestMapping(value = "medialistPage")
	 public ModelAndView medialistProprietor(Page page) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 page.setPd(pd);
		 List<PageData> levellist = levelService.getlistPage(page);// 入口等级
		 List<PageData> rukoulist = rukouTypeService.getlistPage(page);// 入口类型
		 List<PageData> medialist = mediaService.mediaListPage(page);
		 mv.addObject("medialist", medialist);
		 mv.addObject("levellist", levellist);
		 mv.addObject("rukoulist", rukoulist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/xinwen/xinwen");
		 return mv;
	 }
	 
	 /*
	  * TODO  跳转到media资源列表(媒体主方向)
	  */
	 @RequestMapping("/mediaOwnerListPage")
	 public ModelAndView mediaOwnerListPage(Page page) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 page.setPd(pd);
		 List<PageData> molist = mediaService.mediaListPage(page);
		 mv.addObject("molist", molist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/media_xinwen/media_xinwen");
		 return mv;
	 }
	 
	 /*
	 * 跳转到Media添加页面
	 */
	 @RequestMapping(value = "/goAddmedia")
	 public ModelAndView goAddMedia(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
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
		return mv;
	 }
	 
	 /*
	  * TODO 保存media信息
	  */
	 @RequestMapping(value = "saveMedia")
	 public ModelAndView saveMedia(
			 HttpServletRequest request,
			 String online,//选择网站
			 String sel_channel,//选择频道
			 String resourceName,//资源名称
			 @RequestParam(required=false) MultipartFile file,//上传LOGO和截图
			 String media_level,//入口等级
			 String linkeHttp,//入口链接
			 String rukouType,//入口形式
			 String textLink_type,//正文带链接类型
			 String baidu_resource,//是否为百度新闻源
			 String price,//价格
			 String media_intro,//对资源的简介
			 @RequestParam(required=false) MultipartFile linkeHttpFile,
			 HttpSession session) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 String  ffile = DateUtil.getDays(), fileName = "";
		 
		 pd.put("media_id", this.get32UUID());//id
		 pd.put("media_name", online);//选网站
		 pd.put("channelName", sel_channel);//选择频道
		 pd.put("resourceName", resourceName);//资源名称
		 pd.put("media_level", media_level);//入口等级
		 if(file != null){
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());
		 	pd.put("media_logo", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);
		 }
		 pd.put("linkeHttp", linkeHttp);//入口链接
		 if(linkeHttpFile != null){
			 String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
				fileName = FileUpload.fileUp(linkeHttpFile, filePath, this.get32UUID());
		 	pd.put("linkeHttp_img", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);
		 }
		 pd.put("rukouType", rukouType);//入口形式
		 pd.put("textLink_type", textLink_type);//正文带链接类型
		 pd.put("baidu_resource", baidu_resource);//是否为百度资源
		 pd.put("sourceState", 0);//资源状态（0-已停用，1-启用）
		 pd.put("checkedState",2 );//资源审核状态(0-审核不通过，1-审核通过，2-待审核)
		 pd.put("resourceType", 0);//资源类型(0-新闻媒体,1-广告招租)
		 
		 pd.put("media_intro", media_intro);//媒体简介
		 Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		 pd.put("mediaOwner_id", advertiser.getAdvertiser_id());
		 pd.put("time", DateUtil.getTime());
		 mediaService.saveMedia(pd);
		 
		 //存入tb_type_price
		 pd.put("id", this.get32UUID());
		 pd.put("f_id", pd.get("media_id"));
		 pd.put("name", "新闻报价");
		 pd.put("price", price);
		 type_priceService.save(pd);
		 
		 mv.addObject("msg", "success");
		 mv.setViewName("redirect:/api/xmtv2/mediaOwnerListPage.do");
		 return mv;
	 }
	 
	 /*
	  * 跳转到media修改页面(媒体主方向)
	  */
	 @RequestMapping(value = "/Editmedia")
	 public ModelAndView toEditMedia(Page page) throws Exception{
		 ModelAndView mv = new ModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 pd.put("media_id", pd.get("tid"));
		 pd = mediaService.getDetailByID(pd);
		 List<PageData> weblist = webResService.getlistAll(pd);// 网站选择
		 List<PageData> channellist = webChannelService.getlistPage(page);// 频道选择
		 List<PageData> levellist = levelService.getlistPage(page);// 入口等级
		 List<PageData> rukoulist = rukouTypeService.getlistPage(page);// 入口类型
		 List<PageData> textlist = textLink_typeService.getlistPage(page);// 正文带链接
		 mv.addObject("res", "修改");
		 mv.addObject("weblist", weblist);
		 mv.addObject("channellist", channellist);
		 mv.addObject("levellist", levellist);
		 mv.addObject("rukoulist", rukoulist);
		 mv.addObject("textlist", textlist);
		 mv.addObject("msg", "updateMedia");
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/media_xinwen/media_xinwen_add");
		 return mv;
	 }
	 
	 /*
	  * 修改media信息
	  */
	 @RequestMapping(value = "updateMedia")
	 public ModelAndView updateMedia(
			 HttpServletRequest request,
			 String media_id,
			 String id,
			 String online,//选择网站
			 String sel_channel,//选择频道
			 String resourceName,//资源名称
//			 @RequestParam(required=false) MultipartFile file,//上传LOGO和截图
			 String media_level,//入口等级
			 String linkeHttp,//入口链接
			 String rukouType,//入口形式
			 String textLink_type,//正文带链接类型
			 String baidu_resource,//是否为百度新闻源
			 String price,//价格
			 String media_intro,//对资源的简介
			 @RequestParam(required=false) MultipartFile linkeHttpFile
			 ){
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 String  ffile = DateUtil.getDays(), fileName = "";
		 pd.put("media_id", media_id);
		 pd.put("media_name", online);
		 pd.put("channelName", sel_channel);
		 pd.put("resourceName", resourceName);
//		 pd.put("media_logo", null);
		 pd.put("media_level", media_level);
		 pd.put("linkeHttp", linkeHttp);
		 pd.put("rukouType", rukouType);
		 pd.put("textLink_type", textLink_type);
		 pd.put("baidu_resource", baidu_resource);
		 pd.put("media_intro", media_intro);
		 
		 if(linkeHttpFile != null){
			 String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			 fileName = FileUpload.fileUp(linkeHttpFile, filePath, this.get32UUID());
			 pd.put("linkeHttp_img", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);
		 }else {
			 pd.put("linkeHttp_img", linkeHttpFile);
		}
		 
		 pd.put("mediaOwner_id", null);
		 try {
			mediaService.updateMedia(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 //存入tb_type_price
		 pd.put("price", price);
		 try {
			type_priceService.update(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		// mv.addObject("msg","success");
		 mv.setViewName("redirect:/api/xmtv2/mediaOwnerListPage.do");
		 return mv;
	 }
	 
	 /*
	  * 删除media信息
	  */
	 @RequestMapping(value = "delMedia")
	 public void delMedia(PrintWriter writer) throws Exception{
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 pd.put("media_id", pd.getString("tid"));
		 
		try {
			mediaService.delMedia(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		writer.close();
	 }
	 
	 /*
	  * 跳转详情页面(广告主方向)
	  */
	 @RequestMapping(value = "/detailsMedia")
	 public ModelAndView detailsMedia() throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
//		 Map map = new HashMap();
		 pd.put("media_id",pd.get("tid") );
		 List<PageData> mslist = mediaService.getNotID(pd);
		 pd =  mediaService.getDetailByID(pd);
		 Collections.shuffle(mslist);
		 mv.addObject("pd",pd);
		 mv.addObject("mslist", mslist);
		 mv.setViewName("information/xinmeiti/xinwen/xinwen_detail");
		 return mv;
	 }
	 
	 /*
	  * TODO 广告主方向的资源列表的筛选
	  */
	 @RequestMapping(value="/mediaShaixuan")
	 @ResponseBody
	 public Object mediaShaixuan() throws Exception{
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
			List<PageData> list=mediaService.mediaListPage2(pd);//获取微信公众号列表
			//Collections.shuffle(list);//混乱排列
			//mv.addObject("list", list);
			//mv.setViewName("information/xinmeiti/gzh/gzh_list");
			map.put("list", list);
			return AppUtil.returnObject(pd, map);
	 }
	 
	 //修改资源状态（上架or下架）
	 @RequestMapping(value="/")
	 @ResponseBody
	 public Object getMediaSourceState() throws Exception{
		 Map<String, Object> map=new HashMap<String, Object>();
			PageData pd=new PageData();
			if(sessionIsOrNo()){
				pd=this.getPageData();
				String num=pd.getString("num");
				String Arrayids[]=pd.getString("ids").split(",");//分割成数组
				if(num.equals("0")){
					mediaService.endsourceState(Arrayids);
				}
				if(num.equals("1")){
					mediaService.opensourceState(Arrayids);
				}
			}else{
				map.put("result", "error");
			}
			return AppUtil.returnObject(pd, map);
	 }
	 
//==================================================广告招商资源==========================================================	 
	 /*
	  * 跳转详情页面
	  */
	 @RequestMapping(value = "detailsAdver")
	 public ModelAndView detailsAdver() throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 pd.put("media_id",pd.get("tid") );
		 
		 List<PageData> mslist = mediaService.getNotID(pd);
		 pd =  mediaService.getDetailByID(pd);
		 mv.addObject("mslist",mslist);
		 mv.addObject("pd",pd );
		 mv.setViewName("information/xinmeiti/adv/adv_detail");
		 return mv;
		 
	 }
	 
	 /*
	  * TODO 跳转广告招商资源列表（广告主方向）待修改
	  */
	 @RequestMapping(value = "adverlistPage")
	 public ModelAndView adverlistProprietor(Page page) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 page.setPd(pd);
		 List<PageData> medialist = adverService.adverListPage(page);
		 mv.addObject("medialist", medialist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/adv/adv");
		 return mv;
	 }
	 
	 /*
	  * 跳转到资源列表（媒体主方向）
	  */
	 @RequestMapping("/adverOwnerlistPage")
	 public ModelAndView adverOwnerlistPage(Page page) throws Exception{
		 ModelAndView mv = new ModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 page.setPd(pd);
		 List<PageData> aolist = adverService.adverlistPageU(page);
		 mv.addObject("aolist", aolist);
		 mv.addObject("pd", pd);
		 mv.setViewName("information/xinmeiti/media_adv/media_adv");
		 return mv;
	 }
	 
	/*
	 * 跳转到广告招商添加页面
	 */
	@RequestMapping("/goAddAdver")
	public ModelAndView goAddAdver(Page page) throws Exception {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> weblist = webResService.getlistAll(pd);//网站选择
		List<PageData> channellist = webChannelService.getlistPage(page);//频道选择
		List<PageData> levellist = levelService.getlistPage(page);//入口等级
		List<PageData> rukoulist = rukouTypeService.getlistPage(page);//入口类型
		List<PageData> textlist = textLink_typeService.getlistPage(page);//正文带链接
		mv.addObject("msg", "saveAdver");
		mv.addObject("pd", pd);
		mv.addObject("weblist", weblist);
		mv.addObject("channellist",channellist);
		mv.addObject("levellist", levellist);
		mv.addObject("rukoulist", rukoulist);
		mv.addObject("textlist", textlist);
		mv.addObject("res", "添加");
		mv.setViewName("information/xinmeiti/media_adv/media_adv_add");
		return mv;
	}
	 
	 /*
	  * TODO 保存广告招商信息
	  */
	 @RequestMapping(value = "saveAdver")
	 public ModelAndView saveAdver(
			 String media_name,//选择网站
			 String channelName,//选择频道
			 String resourceName,//资源名称
			 @RequestParam(required=false) MultipartFile file,//上传LOGO
			 String media_level,//入口等级
			 String linkeHttp,//入口链接
			 String source_type,//资源类型
			 String source_size,//图片尺寸
			 String file_size,//文件大小
			 String word_size,//字数长度
			 String rukouType,//入口形式
			 String textLink_type,//正文带链接类型
			 String baidu_resource,//是否为百度新闻源
			 String price,//价格
			 String media_intro,
			 @RequestParam(required=false) MultipartFile fileadv,
			 HttpSession session
			 ) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 String  ffile = DateUtil.getDays(), fileName = "";
		 
		 //存入tb_tb_advertise_otherinfor表
		 pd.put("media_id", this.get32UUID());//tb_media表的主键，tb_tb_advertise_otherinfor表的外键id
		 pd.put("id",this.get32UUID());
		 pd.put("source_type", source_type);
		 pd.put("source_size", source_size);
		 pd.put("file_size", file_size);
		 pd.put("word_size", word_size);
		 adverOifrService.saveAO(pd);
		 
		 //存入tb_media表
		 pd.put("media_name", media_name);//选网站
		 pd.put("channelName", channelName);//选择频道
		 pd.put("resourceName", resourceName);//资源名称
		 if(file != null){
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());
		 	pd.put("media_logo", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);
		 }
		 pd.put("media_level", media_level);//入口等级
		 pd.put("resourceType", 1);//资源类型(0-新闻媒体,1-广告招租)
		 pd.put("sourceState", 0);//资源状态（0-已停用，1-启用）
		 pd.put("checkedState",2 );//资源审核状态(0-审核不通过，1-审核通过，2-待审核)
		 pd.put("linkeHttp", linkeHttp);//入口链接
		 if(fileadv != null){
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			fileName = FileUpload.fileUp(fileadv, filePath, this.get32UUID());
		 	pd.put("linkeHttp_img",  Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);
		 }
		 pd.put("rukouType", rukouType);//入口形式
		 pd.put("textLink_type", textLink_type);//正文带链接类型
		 pd.put("baidu_resource", baidu_resource);//是否为百度资源
		 pd.put("media_intro", media_intro);//媒体简介
		 Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);//获取session
		 pd.put("mediaOwner_id", advertiser.getAdvertiser_id());//媒介主ID
		 pd.put("time", DateUtil.getTime());//添加时间
		 mediaService.saveMedia(pd);
		
//		存入tb_type_price
		pd.put("id", this.get32UUID());//主键
		pd.put("f_id", pd.get("media_id"));
		pd.put("name", "广告价格");
		pd.put("price", price);//价格
		type_priceService.saveTP(pd);
		
		mv.addObject("msg", "success");
		mv.setViewName("redirect:/api/xmtv2/adverOwnerlistPage.do");
		 return mv;
	 }
	 
	/*
	 * 跳转到广告招商修改页面
	 */
	@RequestMapping("/toEditAdver")
	public ModelAndView EditAdver(Page page) throws Exception {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("media_id", pd.get("tid"));
		List<PageData> weblist = webResService.getlistAll(pd);//网站选择
		List<PageData> channellist = webChannelService.getlistPage(page);//频道选择
		List<PageData> levellist = levelService.getlistPage(page);//入口等级
		List<PageData> rukoulist = rukouTypeService.getlistPage(page);//入口类型
		List<PageData> textlist = textLink_typeService.getlistPage(page);//正文带链接
		pd = adverService.getDataByAdv(pd);
		mv.addObject("weblist", weblist);
		mv.addObject("channellist",channellist);
		mv.addObject("levellist", levellist);
		mv.addObject("rukoulist", rukoulist);
		mv.addObject("textlist", textlist);
		mv.addObject("msg", "updateAdver");
		mv.addObject("pd", pd);
		mv.addObject("res", "修改");
		mv.setViewName("information/xinmeiti/media_adv/media_adv_add");
		return mv;
	}
	
	/*
	 * 修改广告招商信息
	 */
	 @RequestMapping(value = "updateAdver")
	 public ModelAndView updateAdver(
			 String media_id,
			 String id,
			 String media_name,//选择网站
			 String channelName,//选择频道
			 String resourceName,//资源名称
			 @RequestParam(required=false) MultipartFile file,//上传LOGO和截图
			 String media_level,//入口等级
			 String linkeHttp,//入口链接
			 String source_type,
			 String source_size,
			 String file_size,
			 String word_size,
			 String rukouType,//入口形式
			 String textLink_type,//正文带链接类型
			 String baidu_resource,//是否为百度新闻源
			 String price,//价格
			 String media_intro,//对资源的简介
			 @RequestParam(required=false) MultipartFile fileadv
			 ) throws Exception{
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 String  ffile = DateUtil.getDays(), fileName = "";
		 
//		 修改tb_tb_advertise_otherinfor
		 pd.put("id", id);
		 pd.put("source_type", source_type);
		 pd.put("source_size", source_size);
		 pd.put("file_size", file_size);
		 pd.put("word_size", word_size);
		 adverOifrService.updateAO(pd);
//		 修改tb_media
		 pd.put("media_id", media_id);
		 pd.put("media_name", media_name);
		 pd.put("channelName", channelName);
		 pd.put("resourceName", resourceName);
		 if(file.isEmpty()){
			 String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			 fileName = FileUpload.fileUp(file, filePath, this.get32UUID());
			 pd.put("media_logo", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);
		 }else {
			pd.put("media_logo", file);
		}
		 pd.put("media_level", media_level);
		 pd.put("linkeHttp", linkeHttp);
		 pd.put("rukouType", rukouType);
		 pd.put("textLink_type", textLink_type);
		 pd.put("baidu_resource", baidu_resource);
		 pd.put("media_intro", media_intro);
		 if(fileadv != null){
			 String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + IMGPATH + ffile;
			fileName = FileUpload.fileUp(fileadv, filePath, this.get32UUID());
		 	pd.put("linkeHttp_img", Const.FILEPATHIMG + IMGPATH + ffile + "/" + fileName);
		 }else {
			 pd.put("linkeHttp_img", fileadv);
		}
		 mediaService.updateMedia(pd);
		 
//		 修改tb_type_price
		 pd.put("f_id",pd.getString("media_id"));
		 pd.put("price", price);
		 type_priceService.update(pd);
		 
		 mv.addObject("msg","success");
		 mv.setViewName("redirect:/api/xmtv2/mediaOwnerListPage.do");
		 return mv;
	 }

	 /*
	  * TODO 启用广告招商资源
	  */
	 public ModelAndView openSourceState(){
		 ModelAndView mv = new ModelAndView();
		 
		 return mv;
	 }
	 
	 /*
	  * TODO 停用广告招商资源
	  */
	 public ModelAndView closeSourceState(){
		 ModelAndView mv = new ModelAndView();
		 
		 return mv;
		 
	 }
	 
//	 =======================================================================================================//
	 /*
	  * TODO 选择网站的搜索框
	  */
	 public void searchfor(){
		 
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
	 
	 
//===========================================================================================================//	 
	 /*
	  * 跳转到有奖问答页面
	  */
	 @RequestMapping("/goDati")
	 public ModelAndView goDati(){
		 ModelAndView mv = this.getModelAndView();
		 PageData pd = new PageData();
		 pd = this.getPageData();
		 
		 mv.setViewName("information/xinmeiti/youjiangdati/dati");
		 return mv;
	 }
	 
}


