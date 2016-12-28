package com.pb.xc.service.impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.util.ObjectUtil;

public class FengYeBasic {
	/**
     * 设置分页初始信息
     * @param pagesize
     * @param currentpage
     */
	public void setPageInfo(Integer pagesize, Integer currentpage) {
		// TODO 设置分页初始信息
		if (pagesize != null || currentpage != null) {// 分页信息设置
			if (pagesize == null) {
				pagesize = 10;
			}
			if (currentpage == null) {
				currentpage = 1;
			}
			PageHelper.startPage(currentpage, pagesize);
		}
		
	}
	
	 /**
     * 设置返回的分页信息
     * @param pagesize
     * @param currentpage
     * @param pos
     * @param resultVo
     */
	public void setReturnPageInfo(Integer pagesize, Integer currentpage,
			List pos, ResultVo resultVo) throws Exception {
		// TODO 设置返回的分页信息
		
		if (pagesize != null || currentpage != null) {
			// 分页信息设置
			Page p = (Page) pos;

			resultVo.setCurrentpage(ObjectUtil.convToString(p.getPageNum()));
			resultVo.setTotal(ObjectUtil.convToString(p.getTotal()));

			resultVo.setPageNum(ObjectUtil.convToString(p.getPageNum()));

			resultVo.setPageSize(ObjectUtil.convToString(p.getPageSize()));

			int pages = p.getPages();
			resultVo.setPages(ObjectUtil.convToString(pages));
		}
		
	}
}
