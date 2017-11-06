package com.fh.controller.system.level;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.system.level.LevelService;
import com.fh.util.AppUtil;
import com.fh.util.PageData;


@Controller
@RequestMapping(value="/level")
public class LevelController extends BaseController{

	

	@Resource(name="levelService")
	private LevelService levelService;
	
	/**
	 * 获取列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getlistPage")
	public ModelAndView getlistPage(Page page) throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list=levelService.getlistPage(page);
		mv.addObject("pd", pd);
		mv.addObject("list",list);
		mv.setViewName("system/level/level_list");
		return mv;
	}
	/**
	 * 跳转到添加信息页
	 * @return
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(){
		ModelAndView mv=new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.addObject("msg", "insert");
		mv.setViewName("system/level/level_edit");
		return mv;
	}
	/**
	 * 验证是否存在
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectByName")
	@ResponseBody
	public Object selectByName() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		String result="";
		PageData pd=new PageData();
		pd=this.getPageData();
		PageData pd1=new PageData();
		pd1=levelService.getDataByName(pd);
		if(pd1 != null){//说明已经存在，无需添加
			result="已存在";
		}else{
			result="不存在";
		}
		map.put("result", result);
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 添加信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insert")
	public ModelAndView insert() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		pd.put("level_id", this.get32UUID());
		levelService.insert(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 跳转到修改页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		pd.put("level_id", pd.get("tagID"));
		pd=levelService.getDataById(pd);
		
		mv.addObject("pd", pd);
		mv.addObject("msg", "update");
		mv.setViewName("system/level/level_edit");
		return mv;
	}
	/**
	 * 更新指定记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/update")
	public ModelAndView update() throws Exception{
		ModelAndView mv=new ModelAndView();
		PageData pd=new PageData();
		pd=this.getPageData();
		levelService.update(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 删除指定的记录
	 * @param writer
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter writer) throws Exception{
		PageData pd=new PageData();
		pd=this.getPageData();
		pd.put("level_id", pd.get("tagID"));
		levelService.delete(pd);
		writer.close();
	}
	/**
	 * 批量删除记录
	 * @return
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll(){
		PageData pd=new PageData();
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			pd=this.getPageData();
			List<PageData> listpd=new ArrayList<PageData>();
			String USER_ids=pd.getString("USER_ids");
			if(USER_ids!=null && !USER_ids.equals("")){
				String ArrayUSER_ids[]=USER_ids.split(",");//分割成数组
				System.out.println("ArrayUSER_ids[]"+ArrayUSER_ids);
				levelService.deleteAll(ArrayUSER_ids);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}

			listpd.add(pd);
			map.put("list", listpd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
}
