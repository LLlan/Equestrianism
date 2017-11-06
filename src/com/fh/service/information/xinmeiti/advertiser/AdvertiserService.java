package com.fh.service.information.xinmeiti.advertiser;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;

@Service("advertiserService")
public class AdvertiserService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 通过手机号和roleMark获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData selByPhone(PageData pd)throws Exception{
		return (PageData)dao.findForObject("advertiserMapper.selByPhone", pd);
	}
	/**
	 * 通过手机号和密码获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData selByPhoneAndPaw(PageData pd)throws Exception{
		return (PageData)dao.findForObject("advertiserMapper.selByPhoneAndPaw", pd);
	}
	/**
	 * 添加
	 * @param pd
	 * @throws Exception
	 */
	public void addAdvertiser(PageData pd) throws Exception{
		dao.save("advertiserMapper.addAdvertiser", pd);
	}
	/**
	 * 重置密码
	 * @param pd
	 * @throws Exception
	 */
	public void updataPsw(PageData pd) throws Exception{
		dao.update("advertiserMapper.updataPsw", pd);
	}
	
	/**
	 * 资料编辑
	 * @param pd
	 * @throws Exception
	 */
	public void editInformation(PageData pd) throws Exception{
		dao.update("advertiserMapper.editInformation", pd);
	}
	/**
	 * 用户资源数量统计
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData selCountNum(PageData pd) throws Exception{
		return (PageData) dao.findForObject("advertiserMapper.selCountNum", pd);
	}
 	
	
	
	
	
	
}
