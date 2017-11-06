package com.fh.service.information.xinmeiti.guanggao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service("guangGaoService")
public class GuangGaoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public List<PageData> selectlistPage(Page page) throws Exception {
		return (List<PageData>)dao.findForList("GuangGaoMapper.selectlistPage", page);
	}

	public List<PageData> getById(PageData pd)throws Exception {
		return (List<PageData>)dao.findForList("GuangGaoMapper.getById", pd);
	}

	public void saveGuangGaoOrder(PageData pd)throws Exception {
		dao.save("GuangGaoMapper.saveGuangGaoOrder", pd);
	}


	public List getByid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("GuangGaoMapper.getByid", pd);
	}

	public void saveresource(PageData pd) throws Exception  {
		dao.save("GuangGaoMapper.saveresource", pd);
	}
}
