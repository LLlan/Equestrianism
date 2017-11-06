package com.fh.service.information.xinmeiti.mediaowner;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;

@Service("mediaownerService")
public class MediaownerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 通过手机号获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData selByPhone(PageData pd)throws Exception{
		return (PageData)dao.findForObject("mediaownerMapper.selByPhone", pd);
	}
	/**
	 * 通过手机号和密码获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData selByPhoneAndPaw(PageData pd)throws Exception{
		return (PageData)dao.findForObject("mediaownerMapper.selByPhoneAndPaw", pd);
	}
	/**
	 * 添加
	 * @param pd
	 * @throws Exception
	 */
	public void addMediaowner(PageData pd) throws Exception{
		dao.save("mediaownerMapper.addMediaowner", pd);
	}
	
}
