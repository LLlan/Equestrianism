package com.fh.service.information.xinmeiti.adver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

/**
 * 功能：
 *
 */
@Service("arimOrderService")
public class ArimOrderService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	 * 显示资ArimOrder列表
	 */
	public List<PageData> ArimOrderListPage(Page page) throws Exception{
		return (List<PageData>) dao.findForList("arimOrderMapper.arimOrderlistPage", page);
	}
	
	/*
	 * 根据ID查询ArimOrder
	 */
	public PageData getDetailByID(PageData pd) throws Exception{
		return (PageData) dao.findForObject("arimOrderMapper.getDetailByID", pd);
	}
	
	/*
	 *  添加ArimOrder
	 */
	public void saveArimOrder(PageData pd) throws Exception{
		dao.save("arimOrderMapper.saveArimOrder", pd);
	}
	
	/*
	 * 	修改ArimOrder
	 */
	public void updateArimOrder(PageData pd) throws Exception{
		dao.update("arimOrderMapper.updateArimOrder", pd);
	}
	
	/*
	 * 删除ArimOrder
	 */
	public void delArimOrder(PageData pd) throws Exception{
		dao.delete("arimOrderMapper.delArimOrder", pd);
	}
}
