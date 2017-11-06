package com.fh.service.information.item;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("itemService")
public class ItemService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	

	/*
	* 重考
	*/
	public void chongkao(PageData pd)throws Exception{
		dao.update("itemMapper.chongkao", pd);
	}
	
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("itemMapper.findById", pd);
	}
	
	/*
	 * 通过随机数id获取数据
	 */
	public PageData findByRandomId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("itemMapper.findByRandomId", pd);
	}
	
	
	/*
	 * 通过随机数id获取数据
	 */
	public PageData getAnswerById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("itemMapper.getAnswerById", pd);
	}
	
	
	/*
	 * 通过ip获取该用户的分数
	 */
	public PageData getFenshuByIp(PageData pd)throws Exception{
		return (PageData)dao.findForObject("itemMapper.getFenshuByIp", pd);
	}
	

	/*
	* 修改考题次数
	*/
	public void updateCount(PageData pd)throws Exception{
		dao.save("itemMapper.updateCount", pd);
	}
	

	/*
	* 修改分数
	*/
	public void updateFenshu(PageData pd)throws Exception{
		dao.save("itemMapper.updateFenshu", pd);
	}
	
	/*
	* 保存分数
	*/
	public void savefenshu(PageData pd)throws Exception{
		dao.save("itemMapper.savefenshu", pd);
	}
	
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("itemMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void del(PageData pd)throws Exception{
		dao.delete("itemMapper.del", pd);
	}
	/*
	 * 删除图片2
	 */
	public void delTp2(PageData pd)throws Exception{
		dao.delete("itemMapper.delTp2", pd);
	}
	
	/*
	* 删除图片
	*/
	public void delTp(PageData pd)throws Exception{
		dao.update("itemMapper.delTp", pd);
	}
	
	/*
	* 修改
	*/
	public void update(PageData pd)throws Exception{
		dao.update("itemMapper.update", pd);
	}
	
	
	/*
	*列表
	*/
	public List<PageData> itemlistPage(Page page)throws Exception{
		return (List<PageData>)dao.findForList("itemMapper.itemlistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("itemMapper.listAll", pd);
	}
	
	
	//******************************加入人员********************************//

	/*
	* 保存分数
	*/
	public void saveJoin(PageData pd)throws Exception{
		dao.save("itemMapper.saveJoin", pd);
	}
	
}

