package com.fh.service.information.xinmeiti.videopalt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service("anchorOrderService")
public class AnchorOrderService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	 * 显示资Anchor列表
	 */
	public List<PageData> AnchorListPage(Page page) throws Exception{
		return (List<PageData>) dao.findForList("anchorMapper.anchorlistPage", page);
	}
	
	/*
	 * 根据ID查询Anchor
	 */
	public PageData getDetailByID(PageData pd) throws Exception{
		return (PageData) dao.findForObject("anchorMapper.getDetailByID", pd);
	}
	
	/*
	 *  添加Anchor
	 */
	public void saveAnchor(PageData pd) throws Exception{
		dao.save("anchorMapper.saveAnchor", pd);
	}
	
	/*
	 * 	修改Anchor
	 */
	public void updateAnchor(PageData pd) throws Exception{
		dao.update("anchorMapper.updateAnchor", pd);
	}
	
	/*
	 * 删除Anchor
	 */
	public void delAnchor(PageData pd) throws Exception{
		dao.delete("anchorMapper.delAnchor", pd);
	}
}
