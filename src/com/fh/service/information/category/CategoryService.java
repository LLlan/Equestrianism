package com.fh.service.information.category;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.information.Cate;
import com.fh.util.PageData;


@Service("categoryService")
public class CategoryService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("CategoryMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CategoryMapper.delete", pd);
	}
	
	/*
	* 删除图片
	*/
	public void delTp(PageData pd)throws Exception{
		dao.update("CategoryMapper.delTp", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CategoryMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.listAll", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<Cate> findAllCate()throws Exception{
		return (List<Cate>)dao.findForList("CategoryMapper.findAllCate", null);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> findAllType()throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.findAllCate", null);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> allCate(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.allCate", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CategoryMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CategoryMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

