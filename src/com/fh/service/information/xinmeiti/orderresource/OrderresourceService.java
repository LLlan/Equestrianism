package com.fh.service.information.xinmeiti.orderresource;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;

/**
 * 功能：订单资源
 * @author 张建华
 *
 */
@Service("orderresourceService")
public class OrderresourceService {


	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/**
	 * 添加资源信息
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception{
		dao.save("orderresourceMapper.insert", pd);
	}
	/**
	 * 查询订单资源列表根据订单号
	 * @param pd
	 * @return
	 * @throws Exception
	 
	public List<PageData> getSourcelist(PageData pd) throws Exception{
		
		return (List<PageData>) dao.findForList("orderresourceMapper.getSourcelist", pd);
	}
	*/
	/**
	 * 查询订单资源列表根据资源ID
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getSourcelistbyid(PageData pd) throws Exception{
		
		return (List<PageData>) dao.findForList("orderresourceMapper.getSourcelistbyid", pd);
	}
}
