package com.fh.service.information.xinmeiti.zhibo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
@Service("zhiboService")
public class ZhiBoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public List<PageData> selecttype(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ZhiBoMapper.selectlistPage",page);
	}

	public List<PageData> getById(PageData pd)throws Exception {
		return (List<PageData>)dao.findForList("ZhiBoMapper.getById", pd);
	}

	public void saveZhiBoOrder(PageData pd)throws Exception {
		dao.save("ZhiBoMapper.saveZhiBoOrder", pd);
	}

	public List getByid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("ZhiBoMapper.getByid", pd);
	}

	public void saveresource(PageData pd) throws Exception {
		dao.save("ZhiBoMapper.saveresource", pd);
		
	}


	public List selectById(PageData pd) throws Exception {
		return (List) dao.findForList("ZhiBoMapper.selectById", pd);
	}

	public void savezhiboOrder(PageData pd) throws Exception {
		dao.save("ZhiBoMapper.savezhiboOrder", pd);
	}



}
