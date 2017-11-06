package com.fh.service.information.company;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.information.Com;
import com.fh.util.PageData;


@Service("companyService")
public class CompanyService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("CompanyMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CompanyMapper.delete", pd);
	}
	
	/*
	* 删除图片
	*/
	public void delTp(PageData pd)throws Exception{
		dao.update("CompanyMapper.delTp", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CompanyMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.listAll", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<Com> listAllCom(PageData pd)throws Exception{
		return (List<Com>)dao.findForList("CompanyMapper.listAllCom", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listDisCom(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.listAllCom", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listHomeRec(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.listHomeRec", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listRec(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.listRec", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listByType(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CompanyMapper.listByType", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CompanyMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CompanyMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

