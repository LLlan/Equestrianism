package com.fh.service.information.xinmeiti.media;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

/**
 *  新闻和网站媒体的资源，
 */
@Service("mediaService")
public class MediaService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	 * 新闻发布资源列表
	 */
	public List<PageData> mediaListPage(Page page) throws Exception{
		
		return (List<PageData>) dao.findForList("mediaMapper.medialistPage", page);
	}
	
	
	/*
	 * 广告主方向的资源列表查询
	 */
	public List<PageData> mediaListPage2(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList("mediaMapper.medialistPage2", pd);
	}
	
	/*
	 * 启用资源
	 */
	public void opensourceState(String[] ids) throws Exception{
		dao.update("mediaMapper.OpensourceState", ids);
	}
	
	/*
	 * 下架资源
	 */
	public void endsourceState(String[] ids) throws Exception{
		dao.update("mediaMapper.EndsourceState", ids);
	}
	
	/*
	 * 广告招商资源列表
	 */
/*	public List<PageData> adverListPage(Page page) throws Exception{
		return (List<PageData>) dao.findForList("mediaMapper.adverlistPage", page);
	}*/
	
	/*
	 * 保存新网资源
	 */
	public void saveMedia(PageData pd) throws Exception{
		dao.save("mediaMapper.saveMedia", pd);
	}
	/*
	 * ID查询新网资源
	 */
	public PageData getDetailByID(PageData pd) throws Exception{
		return (PageData) dao.findForObject("mediaMapper.getDetailByID", pd);
	}
	
	/*
	 * 修改新网资源
	 */
	public void updateMedia(PageData pd) throws Exception{
		Object object = dao.update("mediaMapper.updateMedia", pd);
		System.out.println("==========================================================================="+object);
	}
	
	public void updateMe2(PageData pd2){
		try {
			dao.update("mediaMapper.updateMede", pd2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 删除新网资源
	 */
	public void delMedia(PageData pd) throws Exception{
		dao.delete("mediaMapper.delMedia", pd);
	}
	
	/*
	 * 随机取出4条数据
	 */
	public List<PageData> getDataByRandom(PageData pd) throws Exception{
		
		return (List<PageData>) dao.findForList("mediaMapper.getDataByRandom", pd);
	}
	
	/*
	 * 取出不包含目标的集合
	 */
	public List<PageData> getNotID(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList("mediaMapper.getNotID", pd);
	}
	
	
	/*
	 * 根据ID取出广告招商模块的一条数据
	 */
	/*public PageData getDataByAdv(PageData pd) throws Exception{
		return (PageData) dao.findForObject("mediaMapper.getDataByAdv", pd);
	}*/
	/**
	 * 张建华，统计使用
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAllbyfid(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("mediaMapper.getlistAllbyfid", pd);
	}
}
