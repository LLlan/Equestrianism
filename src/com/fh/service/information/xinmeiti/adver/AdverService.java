package com.fh.service.information.xinmeiti.adver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service("adverService")
public class AdverService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/*
	 * 广告主方向的广告招商资源列表
	 */
	public List<PageData> adverListPage(Page page) throws Exception{
		return (List<PageData>) dao.findForList("adverMapper.adverlistPage", page);
	}
	
	/*
	 * 媒体主方向的广告招商资源列表
	 */
	public List<PageData> adverlistPageU(Page page) throws Exception{
		return (List<PageData>) dao.findForList("adverMapper.adverlistPageU", page);
	}
	
	/*
	 * 广告招商模块写的根据ID查询
	 */
	public PageData getDataByAdv(PageData pd) throws Exception{
		return (PageData) dao.findForObject("adverMapper.getDataByAdv", pd);
	}
	
	/*
	 * 停用广告资源
	 */
	public void updateSourcB(PageData pd) throws Exception{
		dao.update("adverMapper.updateSourcB", pd);
	}
	/*
	 * 启用广告资源
	 */
	public void updateSourcA(PageData pd) throws Exception{
		dao.update("adverMapper.updateSourcA", pd);
	}
}
