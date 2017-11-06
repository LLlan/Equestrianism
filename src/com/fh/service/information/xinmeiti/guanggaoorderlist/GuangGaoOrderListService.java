package com.fh.service.information.xinmeiti.guanggaoorderlist;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;

@Service("guangGaoOrderListService")
public class GuangGaoOrderListService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public List getguangGaoOrderlistpage(Page page) throws Exception {
		
		return (List) dao.findForList("GuangGaoOrderListMapper.getguangGaoOrderlistpage", page);
	}
	public List getxinWenOrderlistpage(Page page) throws Exception {
		
		return (List) dao.findForList("GuangGaoOrderListMapper.getxinWenOrderlistpage", page);
	}
	public List zhiboselectlistpage(Page page) throws Exception {
		return (List) dao.findForList("GuangGaoOrderListMapper.zhiboselectlistpage", page);
	}
}
