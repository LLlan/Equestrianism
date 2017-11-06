package com.fh.service.system.dati;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service
public class YoujiangdatiService {
	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	/*
	 * 列表
	 */
	public List<PageData> getListPage(Page page){
		return null;
		
	}
	
	/*
	 * 添加
	 */
	public void saveDati(PageData pd){
		
	}
	
	
	/*
	 * 修改
	 */
	public void updateDati(PageData pd){
		
	}
	
	/*
	 * 删除
	 */
	public void delDati(PageData pd){
		
	}
	
	
	/*
	 * 根据ID查询
	 */
	public PageData getDatByID(PageData pd){
		return null;
	}
}
