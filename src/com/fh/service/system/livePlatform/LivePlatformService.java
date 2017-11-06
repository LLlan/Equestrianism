package com.fh.service.system.livePlatform;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service("livePlatformService")
public class LivePlatformService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 查询列表(一页)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistPage(Page page) throws Exception{
		
		return (List<PageData>)dao.findForList("LivePlatformMapper.getlistPage", page);
	}
	/**
	 * 查询列表(全部)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAll(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("LivePlatformMapper.getlistAll", pd);
	}
	/**
	 * 添加
	 * @param pd
	 * @throws Exception
	 */
	public void insert(PageData pd) throws Exception{
		dao.save("LivePlatformMapper.insert", pd);
	}
	/**
	 * 根据id查询一条记录
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getDataById(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("LivePlatformMapper.getById", pd);
	}
	/**
	 * 根据name查询一条记录，判断是否存在
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getDataByName(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("LivePlatformMapper.getByName", pd);
	}
	
	/**
	 * 更新指定记录
	 * @param pd
	 * @throws Exception
	 */
	public void update(PageData pd) throws Exception{
		dao.update("LivePlatformMapper.update", pd);
	}
	
	/**
	 * 删除指定记录
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd) throws Exception{
		dao.delete("LivePlatformMapper.delete", pd);
	}
	
	/**
	* 批量删除
	*/
	public void deleteAll(String[] USER_IDS)throws Exception{
		dao.delete("LivePlatformMapper.deleteAll", USER_IDS);
	}
}
