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
import com.fh.service.information.xinmeiti.zhibo.ZhiBoService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/api/zhiBoOrder")
public class ZhiBoOrder extends BaseController {

	
	@Resource(name="zhiboService")
	private ZhiBoService zhiboService;
	
	@RequestMapping(value="/goZhiBo")
	public ModelAndView goZhiBo(Page page) throws Exception{
		
		PageData pd = this.getPageData();
		
		String tempname=pd.getString("namendp");
		String t="";
		if(tempname.equals("1")){
			t="专场直播";
		}else if(tempname.equals("2")){
			t="直播插入";
		}
		pd.put("name", t);
		ModelAndView mv = this.getModelAndView();
		List list=zhiboService.getByid(pd);
		List list1=zhiboService.getById(pd);
		mv.addObject("type_name",t);
		mv.addObject("list1", list1);
		mv.addObject("list", list);
		mv.setViewName("information/xinmeiti/zhibo/zhibo_order");
		return mv;
	}
	@RequestMapping(value="/selectById")
	@ResponseBody 
	public Map selectById(
			@RequestParam String id,
			@RequestParam String type_name
			)throws Exception{
		Map map = new HashMap();
		PageData pd = new PageData();
		pd.put("type_name", type_name);
		List list=new ArrayList();
		List resultList =new ArrayList();
		String[] sid = id.split(",");
		for(int i=0; i<sid.length;i++){
				pd.put("videoPlatform_id", sid[i]);
				list=zhiboService.selectById(pd);
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
			@RequestParam String anchorOrder_title,
			@RequestParam (required=false) String anchorOrder_content,
			@RequestParam String anchorOrder_location,
			@RequestParam (required=false) String newsOrder_optime,
			@RequestParam String newsOrder_edtime,
			@RequestParam String videoPlatform_id,
			@RequestParam String type_name
			) throws Exception{
		//从session中获取用户id
		
		ModelAndView mv = this.getModelAndView();
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Advertiser advertiser=(Advertiser) session.getAttribute(Const.SESSION_Advertiser);
		String advertiser_id = advertiser.getAdvertiser_id();
		PageData pd = new PageData();
		
		pd.put("anchorOrder_id", this.get32UUID());
		
		pd.put("type_name", type_name);
		
		pd.put("anchorOrder_title", anchorOrder_title);
		
		pd.put("anchorOrder_content", anchorOrder_content);
		
		pd.put("anchorOrder_location", anchorOrder_location);
		
		pd.put("newsOrder_optime", newsOrder_optime);
		
		pd.put("newsOrder_edtime", newsOrder_edtime);
		
		pd.put("order_time", DateUtil.getTime());
		
		pd.put("advertiser_id",advertiser_id);

		pd.put("order_id", pd.getString("anchorOrder_id"));
		zhiboService.savezhiboOrder(pd);
		
		String[] str = videoPlatform_id.split(",");
		
		for (int i = 0; i < str.length; i++){
		
			pd.put("videoPlatform_id", str[i]);
			
			pd.put("resource_id", str[i]);
			
			pd.put("id", this.get32UUID());
			pd.put("order_number",pd.getString("anchorOrder_id"));
			pd.put("order_state",1);
			pd.put("resource_type",4);
			System.out.println(pd.getString("resource_id"));
			zhiboService.saveresource(pd);
		}
		mv.setViewName("redirect:/api/guangGaoOrderList/zhiboselectlistpage.do");
		return mv;
	}
	
}
