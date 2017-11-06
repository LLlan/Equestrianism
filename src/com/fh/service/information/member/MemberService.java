package com.fh.service.information.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("memberService")
public class MemberService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("MemberMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("MemberMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("MemberMapper.edit", pd);
	}
	
	/*
	* 更新手机号
	*/
	public void updatePhone(PageData pd)throws Exception{
		dao.update("MemberMapper.updatePhone", pd);
	}
	
	/*
	* 更新手机号
	*/
	public void updatemoney(PageData pd)throws Exception{
		dao.update("MemberMapper.updatemoney", pd);
	}
	
	/*
	* 更新密码
	*/
	public void updpwd(PageData pd)throws Exception{
		dao.update("MemberMapper.updpwd", pd);
	}
	
	/*
	* 更新信息
	*/
	public void editInfo(PageData pd)throws Exception{
		dao.update("MemberMapper.editInfo", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.listAll", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAllMem(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.listAllMem", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findById", pd);
	}
	
	/*
	* 通过openid获取数据
	*/
	public PageData findByOpenId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findByOpenId", pd);
	}
	
	/*
	* 通过openid获取数据
	*/
	public PageData findByLogin(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findByLogin", pd);
	}
	
	/*
	* 通过openid获取数据
	*/
	public PageData findByMobile(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findByMobile", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MemberMapper.deleteAll", ArrayDATA_IDS);
	}
}

