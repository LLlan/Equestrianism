package com.fh.service.information.join;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("joinService")
public class JoinService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/*
	*列表(全部)
	*/
	public List<PageData> joinlistPage(Page page)throws Exception{
		return (List<PageData>)dao.findForList("JoinMapper.joinlistPage", page);
	}
	
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("JoinMapper.deleteJoin", pd);
	}
	
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("JoinMapper.save", pd);
	}
	
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("JoinMapper.edit", pd);
	}

	
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("JoinMapper.findById", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findByMobile(PageData pd)throws Exception{
		return (PageData)dao.findForObject("JoinMapper.findByMobile", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("JoinMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

