package com.fh.controller.system.dati;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.system.dati.YoujiangdatiService;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/dati")
public class YoujiangdatiController extends BaseController{
	
	@Resource(name="youjiangdatiService")
	private YoujiangdatiService youjiangdatiService;
	
	/*
	 * 题目列表
	 */
	@RequestMapping(value="datiListPage")
	public ModelAndView datiListPage(Page page){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> datilist = youjiangdatiService.getListPage(page);
		mv.addObject("datilist", datilist);
		mv.setViewName("system/dati/dati_list");
		return mv;
		
	}
                 	
	/*
	 * 添加题目
	 */
	@RequestMapping(value="save")
	public ModelAndView save(){
		ModelAndView mv = this.getModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		pd.put("topic_id", this.get32UUID());
		pd.put("topic_title", null);
		pd.put("topic_content", null);
		pd.put("topic_content", null);
		youjiangdatiService.saveDati(pd);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/*
	 * 跳转修改页面
	 */
	@RequestMapping(value="Edit")
	public ModelAndView toEdid(){
		ModelAndView mv = this.getModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		pd.put("topic_id", pd.get("tagID"));
		youjiangdatiService.getDatByID(pd);
		mv.addObject("pd", pd);
		mv.addObject("msg", "update");
		mv.setViewName("system/level/level_edit");
		return mv;
	}
	
	/*
	 * 修改
	 */
	@RequestMapping(value="update")
	public ModelAndView update(){
		ModelAndView mv = this.getModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		youjiangdatiService.updateDati(pd);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		
		return mv;
	}
	
	
	/*
	 * 删除
	 */
	@RequestMapping(value="del")
	public void del(PrintWriter writer){
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("", pd.getString("tagID"));
		
		try {
			youjiangdatiService.delDati(pd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		writer.close();
	}
	
	/*
	 *	根据ID查找题目
	 */
	public ModelAndView getDateByID(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		
		
		return mv;
	}
	
	
}
