package com.fh.service.information.xinmeiti.type_price;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;

/**
 * 功能：微信公众号、微信朋友圈、微博、网红直播推广形式和价格的Service层
 * @author 张建华
 *
 */
@Service("type_priceService")
public class Type_priceService {

	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/**
	 * 添加
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception{
		dao.save("type_priceMapper.insert", pd);
	}
	
	/**
	 * 根据fid和name查询
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getdateByfidandName(PageData pd) throws Exception{
		return (PageData) dao.findForObject("type_priceMapper.getdateByfidandName",pd);
	}
	/**
	 * 查询列表(全部(推广形式和价格))
	 * getlistAllByfid-微信公众号用
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAllByfid(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("type_priceMapper.getlistAllByfid", pd);
	}
	/**
	 * 查询列表(全部(推广形式和价格))
		getlistAllByfid1-微信朋友圈用
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAllByfid1(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("type_priceMapper.getlistAllByfid1", pd);
	}
	/**
	 * 查询列表(全部(推广形式和价格))
		getlistAllByfid2-微博用
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getlistAllByfid2(PageData pd) throws Exception{
		
		return (List<PageData>)dao.findForList("type_priceMapper.getlistAllByfid2", pd);
	}
	/**
	 * 更新指定记录
	 * @param pd
	 * @throws Exception
	 */
	public void update(PageData pd) throws Exception{
		dao.update("type_priceMapper.update", pd);
	}
	/**
	 * 在广告招商模块的添加
	 * @param pd
	 * @throws Exception
	 */
	public void saveTP(PageData pd) throws Exception{
		dao.save("type_priceMapper.save", pd);
	}
}
