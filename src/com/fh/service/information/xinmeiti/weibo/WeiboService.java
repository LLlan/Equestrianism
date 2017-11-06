package com.fh.service.information.xinmeiti.weibo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

/**
 * 功能：微博Service层
 * @author 张建华
 *
 */
@Service("weiboService")
public class WeiboService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/**
	 * 添加
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception{
		dao.save("weiboMapper.insert", pd);
	}
	/**
	 * 查询列表(全部前台页面展示)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAll(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("weiboMapper.getlistAll", pd);
	}
	 /**
	 * 查询列表(根据粉丝量进行排序)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAllorderbyfansNum(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("weiboMapper.getlistAllorderbyfansNum", pd);
	}
	/**
	 * 查询列表(全部)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAllbyfid(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("weiboMapper.getlistAllbyfid", pd);
	}
	/**
	 * 查询列表(全部)根据媒介主的id（资源管理）
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getsourcelistbyfid(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("weiboMapper.getsourcelistbyfid", pd);
	}
	/**
	 * 根据id查询一条记录
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getDataById(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("weiboMapper.getById", pd);
	}
	/**
	 * 根据id和name查询一条记录
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getByIdandname(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("weiboMapper.getByIdandname", pd);
	}
	/**
	 * 更新指定记录
	 * @param pd
	 * @throws Exception
	 */
	public void update(PageData pd) throws Exception{
		dao.update("weiboMapper.update", pd);
	}
	/**
	 * 更新资源的状态（更改为启用）
	 * @param pd
	 * @throws Exception
	 */
	public void changeStatetoYes(String[] ids) throws Exception{
		dao.update("weiboMapper.changeStatetoYes", ids);
	}
	/**
	 * 更新资源的状态（更改为下架）
	 * @param pd
	 * @throws Exception
	 */
	public void changeStatetoNo(String[] ids) throws Exception{
		dao.update("weiboMapper.changeStatetoNo", ids);
	}
	
	/************************************************************************************/
	/************************************订单部分功能************************************************/
	/**
	 * 由详情进入订单页
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData detailgotoorder(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("weiboMapper.detailgotoorder", pd);
	}
	
	/**
	 *创建订单页面中的点击添加资源获取资源列表
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getOrderAddResourceList(String[] ids) throws Exception{
		
		return (List<PageData>) dao.findForList("weiboMapper.getOrderAddResourceList", ids);
	}
	/**
	 * 创建订单时的添加资源中的资源选择
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getresourcelist(String[] ids) throws Exception{
		
		return (List<PageData>) dao.findForList("weiboMapper.getresourcelist", ids);
	}
	/**
	 * 添加订单信息insertOrder
	 * @param pd
	 * @throws Exception
	 */
	public void insertOrder(PageData pd) throws Exception{
		dao.save("weiboMapper.insertOrder", pd);
	}
	
	
	/**
	 * 广告主查询订单信息
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> getOrderInformationList(PageData pd) throws Exception{
		
		return (List<PageData>) dao.findForList("weiboMapper.getOrderInformationList", pd);
	}
	
	/**
	 * 媒介主查询订单信息
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> getMjzOrderInformationList(PageData pd) throws Exception{
		
		return (List<PageData>) dao.findForList("weiboMapper.getMjzOrderInformationList", pd);
	}
	
	
	
	
	/**
	 * 查询订单列表
	 * @param pd
	 * @return
	 * @throws Exception
	 
	public List<PageData> getOrderlist(PageData pd) throws Exception{
		
		return (List<PageData>) dao.findForList("weiboMapper.getOrderlist", pd);
	}
	*/
	/************************************后台服务号功能************************************************/
	/**
	 * 查询所有的信息(后台系统设置页面展示)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> sysgetlistPage(Page page) throws Exception{
		
		return (List<PageData>)dao.findForList("weiboMapper.sysgetlistPage", page);
	}
	/**
	 *系统设置之审核不通过
	 */
	public void syscheckedNo(PageData pd) throws Exception{
		dao.update("weiboMapper.syscheckedNo", pd);
	}
	/**
	 *系统设置之审核通过
	 */
	public void syscheckedYes(PageData pd) throws Exception{
		dao.update("weiboMapper.syscheckedYes", pd);
	}
	/**
	 *系统设置之批量审核不通过
	 */
	public void syscheckedNoAll(String[] ids) throws Exception{
		dao.update("weiboMapper.syscheckedNoAll", ids);
	}
	/**
	 *系统设置之审核批量通过
	 */
	public void syscheckedYesAll(String[] ids) throws Exception{
		dao.update("weiboMapper.syscheckedYesAll", ids);
	}
}
