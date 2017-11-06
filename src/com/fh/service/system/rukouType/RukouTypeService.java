package com.fh.service.system.rukouType;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service("rukouTypeService")
public class RukouTypeService {

	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 查询列表(一页)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistPage(Page page) throws Exception{
		
		return (List<PageData>)dao.findForList("RukouTypeMapper.getlistPage", page);
	}
	/**
	 * 查询列表(全部)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAll(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("RukouTypeMapper.getlistAll", pd);
	}
	/**
	 * 添加
	 * @param pd
	 * @throws Exception
	 */
	public void insert(PageData pd) throws Exception{
		dao.save("RukouTypeMapper.insert", pd);
	}
	/**
	 * 根据id查询一条记录
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getDataById(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("RukouTypeMapper.getById", pd);
	}
	
	
	
	/**
	 * 根据name查询一条记录，判断是否存在
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getDataByName(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("RukouTypeMapper.getByName", pd);
	}
	
	
	
	/**
	 * 更新指定记录
	 * @param pd
	 * @throws Exception
	 */
	public void update(PageData pd) throws Exception{
		dao.update("RukouTypeMapper.update", pd);
	}
	
	/**
	 * 删除指定记录
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd) throws Exception{
		dao.delete("RukouTypeMapper.delete", pd);
	}
	
	/**
	* 批量删除
	*/
	public void deleteAll(String[] USER_IDS)throws Exception{
		dao.delete("RukouTypeMapper.deleteAll", USER_IDS);
	}
}
