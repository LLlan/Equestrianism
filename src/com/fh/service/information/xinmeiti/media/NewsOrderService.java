package com.fh.service.information.xinmeiti.media;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service("newsOrderService")
public class NewsOrderService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	 * 显示资news列表
	 */
	public List<PageData> newsOrderListPage(Page page) throws Exception{
		return (List<PageData>) dao.findForList("newsOrderMapper.newsOrderlistPage", page);
	}
	
	/*
	 * 根据ID查询news
	 */
	public PageData getDetailByID(PageData pd) throws Exception{
		return (PageData) dao.findForObject("newsOrderMapper.getDetailByID", pd);
	}
	
	/*
	 *  添加news
	 */
	public void savenewsOrder(PageData pd) throws Exception{
		dao.save("newsOrderMapper.savenewsOrder", pd);
	}
	
	/*
	 * 	修改news
	 */
	public void updatenewsOrder(PageData pd) throws Exception{
		dao.update("newsOrderMapper.updatenewsOrder", pd);
	}
	
	/*
	 * 删除news
	 */
	public void delnewsOrder(PageData pd) throws Exception{
		dao.delete("newsOrderMapper.delnewsOrder", pd);
	}
}
