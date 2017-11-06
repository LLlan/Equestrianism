package com.fh.service.information.xinmeiti.videopalt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
/**
 * 功能：添加直播推送的资源（主播信息）
 */
@Service("videopaltService")
public class VideopaltService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	 * 显示资Videopalt列表
	 */
	public List<PageData> videopaltListPage(Page page) throws Exception{
		return (List<PageData>) dao.findForList("videopaltMapper.videopaltlistPage", page);
	}
	
	/*
	 * 根据ID查询Videopalt
	 */
	public PageData getDetailByID(PageData pd) throws Exception{
		/*return (PageData) dao.findForObject("videopaltMapper.getDetailByID", pd);*/
		return (PageData) dao.findForObject("videopaltMapper.getDetailByID", pd);
	}
	
	/*
	 *  添加Videopalt
	 */
	public void saveVideopalt(PageData pd) throws Exception{
		 dao.save("videopaltMapper.saveVideopalt", pd);
	}
	
	/*
	 * 	修改Videopalt
	 */
	public void updateVideopalt(PageData pd) throws Exception{
		 dao.update("videopaltMapper.updateVideopalt", pd);
	}
	
	/*
	 * 删除Videopalt
	 */
	public void delVideopalt(PageData pd) throws Exception{
		dao.delete("videopaltMapper.delVideopalt", pd);
	}
	
	/*
	 * 显示直播资源的全部信息
	 */
	public List getDetailByIDAll(PageData pd) throws Exception{
		return (List) dao.findForList("videopaltMapper.getDetailByIDAll",pd);
	}
	/*
	 * 查找不包含目标ID的集合
	 */
	public List<PageData> getNotID(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList("videopaltMapper.getNotID", pd);
	}
	/**
	 * 张建华，统计使用
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAllbyfid(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("videopaltMapper.getlistAllbyfid", pd);
	}
	
	/*
	 * 媒介主更改上传的直播资源的状态
	 */
	public void updateSourceState(PageData pd) throws Exception{
		dao.update("videopaltMapper.updateSource", pd);
	}
	/************************************后台服务号功能star(张建华)************************************************/
	/**
	 * 查询所有的信息(后台系统设置页面展示)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> sysgetlistPage(Page page) throws Exception{
		
		return (List<PageData>)dao.findForList("videopaltMapper.sysgetlistPage", page);
	}
	/**
	 *系统设置之审核不通过
	 */
	public void syscheckedNo(PageData pd) throws Exception{
		dao.update("videopaltMapper.syscheckedNo", pd);
	}
	/**
	 *系统设置之审核通过
	 */
	public void syscheckedYes(PageData pd) throws Exception{
		dao.update("videopaltMapper.syscheckedYes", pd);
	}
	/**
	 *系统设置之批量审核不通过
	 */
	public void syscheckedNoAll(String[] ids) throws Exception{
		dao.update("videopaltMapper.syscheckedNoAll", ids);
	}
	/**
	 *系统设置之审核批量通过
	 */
	public void syscheckedYesAll(String[] ids) throws Exception{
		dao.update("videopaltMapper.syscheckedYesAll", ids);
	}
	/************************************后台服务号功能end
	 * @throws Exception ************************************************/

	public PageData getfid(PageData pd) throws Exception {
		return (PageData) dao.findForObject("videopaltMapper.getfid", pd);
	}
}
