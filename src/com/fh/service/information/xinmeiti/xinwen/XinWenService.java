package com.fh.service.information.xinmeiti.xinwen;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
@Service("xinwenService")
public class XinWenService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public List<PageData> selectlistPage(Page page) throws Exception {
		return (List<PageData>)dao.findForList("XinWenMapper.selectlistPage", page);
	}

	public List<PageData> getById(PageData pd)throws Exception {
		return (List<PageData>)dao.findForList("XinWenMapper.getById", pd);
	}

	public void saveXinWenOrder(PageData pd)throws Exception {
		dao.save("XinWenMapper.saveXinWenOrder", pd);
	}

	public void savemedia2(PageData pd)throws Exception {
		dao.save("XinWenMapper.savemedia2", pd);
	}

	public List getByid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("XinWenMapper.getByid", pd);
	}

	public void saveresource(PageData pd) throws Exception {
		dao.save("XinWenMapper.saveresource", pd);
		
	}

}
