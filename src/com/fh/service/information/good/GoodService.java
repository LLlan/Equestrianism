package com.fh.service.information.good;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("goodService")
public class GoodService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("GoodMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("GoodMapper.delete", pd);
	}
	
	/*
	* 删除图片
	*/
	public void delTp(PageData pd)throws Exception{
		dao.update("GoodMapper.delTp", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("GoodMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GoodMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GoodMapper.listAll", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listHomeRec(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GoodMapper.listHomeRec", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listRec(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GoodMapper.listRec", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listByComId(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GoodMapper.listByComId", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listByType(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GoodMapper.listByType", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GoodMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GoodMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

