package com.fh.service.information.xinmeiti.adverOifr;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;

@Service(value = "adverOifrService")
public class AdverOifrService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/*
	 * 保存数据
	 */
	public void saveAO(PageData pd) throws Exception{
		dao.save("adverOifrMapper.save", pd);
	}
	
	/*
	 * 修改数据
	 */
	public void updateAO(PageData pd) throws Exception{
		dao.update("adverOifrMapper.update", pd);
	}
}
