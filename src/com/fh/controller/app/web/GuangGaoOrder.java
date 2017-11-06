package com.fh.controller.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.information.Advertiser;
import com.fh.service.information.xinmeiti.guanggao.GuangGaoService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="api/guangGao/order")
public class GuangGaoOrder extends BaseController {

	@Resource(name="guangGaoService")
	private GuangGaoService guangGaoService;
	
	@RequestMapping(value="/goGuangGao")
	public ModelAndView goGuangGao(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		System.out.println(pd.getString("media_id"));
		//查询添加资源
		pd.put("media_id",pd.getString("media_id"));
		List<PageData> plist=guangGaoService.selectlistPage(page);
		List list= guangGaoService.getByid(pd);
		mv.addObject("plist", plist);
		mv.addObject("list",list);
		mv.setViewName("information/xinmeiti/adv/adv_order");
		return mv;
	}
	
	/**
	 * 查询添加资源列表
	 * 
	 */
	@RequestMapping(value="/getById")
	@ResponseBody 
	public Map getById(
			@RequestParam String id
			)throws Exception{
		Map map = new HashMap();
		PageData pd = new PageData();
		List list=new ArrayList();
		List resultList =new ArrayList();
		String[] sid = id.split(",");
		for(int i=0; i<sid.length;i++){
			// PageData listpd = new PageData();
			 pd.put("media_id", sid[i]);
			 list=guangGaoService.getById(pd);
			 resultList.add(list);
		}
		map.put("resultList", resultList);
		return map;
	}
	
	/**
	 * 保存订单表
	 * 
	 */
	@RequestMapping(value="/save")
	public ModelAndView save (
			@RequestParam (required=false) String arimOrder_title,
			@RequestParam (required=false) String media_id,
			@RequestParam String newsOrder_optime,
			@RequestParam String newsOrder_edtime
			) throws Exception{
		//从session中获取用户id
		ModelAndView mv = this.getModelAndView();
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		String advertiser_id = advertiser.getAdvertiser_id();
		PageData pd = new PageData();
		
		pd.put("arimOrder_id", this.get32UUID());
		
		pd.put("advertiser_id",advertiser_id);
		
		
		pd.put("arimOrder_title", arimOrder_title);
		
		pd.put("newsOrder_optime", newsOrder_optime);
		
		pd.put("newsOrder_edtime", newsOrder_edtime);
		
		pd.put("order_time", DateUtil.getTime());
		
		guangGaoService.saveGuangGaoOrder(pd);
		
		String[] str = media_id.split(",");
		
		for (int i = 0; i < str.length; i++){
		
			pd.put("media_id", str[i]);
			
			pd.put("resource_id", str[i]);
			pd.put("order_id",pd.getString("arimOrder_id") );
			
			pd.put("id", this.get32UUID());
			pd.put("order_number",pd.getString("arimOrder_id"));
			pd.put("order_state",1);
			pd.put("resource_type",6);
			guangGaoService.saveresource(pd);
		}
		
		
		mv.setViewName("redirect:/api/guangGao/order/goGuangGao");
		
		return mv;
	}
}
