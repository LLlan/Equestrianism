package com.fh.controller.app.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.information.Advertiser;
import com.fh.service.information.xinmeiti.guanggaoorderlist.GuangGaoOrderListService;
import com.fh.util.Const;
import com.fh.util.PageData;

@Controller()
@RequestMapping(value="api/guangGaoOrderList")
public class GuangGaoOrderList extends BaseController {

	@Resource(name="guangGaoOrderListService")
	private GuangGaoOrderListService guangGaoOrderListService;
	
	@RequestMapping(value="/guangGaoList")
	public ModelAndView guangGaoOrderList(){
		
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("information/xinmeiti/guanggaozhu/gg_paidan");
		return mv;
	}
	/**
	 * 新闻订单页面
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/xinWenselectlistpage")
	public ModelAndView xinWenselectlistpage(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		String advertiser_id = advertiser.getAdvertiser_id();
		String s = pd.getString("order_state");
		pd.put("advertiser_id", advertiser_id);
		System.out.println(s);
		page.setPd(pd);
		List list=guangGaoOrderListService.getxinWenOrderlistpage(page);
		mv.addObject("list", list);
		mv.addObject("order_state", s);
		mv.setViewName("information/xinmeiti/xinwen/xinwen_ggzpaidan");
		return mv;
	}
	/**
	 * 黄金广告订单页面
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/guangGaoselectlistpage")
	public ModelAndView guangGaoselectlistpage(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		String advertiser_id = advertiser.getAdvertiser_id();
		pd.put("advertiser_id", advertiser_id);
		page.setPd(pd);
		String s = pd.getString("order_state");
		System.out.println(s);
		List list=guangGaoOrderListService.getguangGaoOrderlistpage(page);
		mv.addObject("list", list);
		mv.addObject("order_state", s);
		mv.setViewName("information/xinmeiti/adv/adv_ggzpaidan");
		return mv;
	}
	
	@RequestMapping(value="/zhiboselectlistpage")
	public ModelAndView zhiboselectlistpage(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		String advertiser_id = advertiser.getAdvertiser_id();
		pd.put("advertiser_id", advertiser_id);
		page.setPd(pd);
		String s = pd.getString("order_state");
		System.out.println(s);
		List list=guangGaoOrderListService.zhiboselectlistpage(page);
		mv.addObject("list", list);
		mv.addObject("order_state", s);
		mv.setViewName("information/xinmeiti/zhibo/zhibo_ggzpaidan");
		return mv;
	}
}
