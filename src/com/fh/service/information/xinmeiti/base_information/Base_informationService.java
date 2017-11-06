package com.fh.service.information.xinmeiti.base_information;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;

/**
 * 功能：微信公众号、微信朋友圈、微博、网红直播基础信息的Service层
 * @author 张建华
 *
 */
@Service("base_informationService")
public class Base_informationService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 添加
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception{
		dao.save("base_informationMapper.insert", pd);
	}
	/**
	 * 根据外键f_id查询对应的信息(基本信息)
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getDataByfid(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("base_informationMapper.getByfid", pd);
	}
	/**
	 * 更新指定记录
	 * @param pd
	 * @throws Exception
	 */
	public void update(PageData pd) throws Exception{
		dao.update("base_informationMapper.update", pd);
	}
	
	
	/**
	 * 添加
	 * @author ZSQ
	 * @param pd
	 * @throws Exception
	 */
	public void savebase(PageData pd) throws Exception{
		dao.save("base_informationMapper.savebase", pd);
	}
}
