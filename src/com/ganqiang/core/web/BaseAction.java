package com.ganqiang.core.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ganqiang.core.dao.support.Page;
import com.ganqiang.core.util.common.StringUtil;

/**
 * 提供简单的基类Action <br>
 * 一般用于处理单独请求 <br>
 *
 */
public abstract class BaseAction extends HttpServlet
{

	private static final long serialVersionUID = 2873293070152371543L;

	public void init() throws ServletException {
		super.init();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	public abstract void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public int getPageNo(HttpServletRequest request){
		String pageNoStr = request.getParameter(Page.PAGE_NO);
		int pageNo = StringUtil.isNullOrBlank(pageNoStr)?Page.DEFAULT_PAGE_NO:Integer.valueOf(pageNoStr).intValue();
		if(pageNo < Page.DEFAULT_PAGE_NO){
			pageNo = Page.DEFAULT_PAGE_NO;
		}
		return pageNo;
	}
	
	public int getPageSize(HttpServletRequest request){
		String pageSizeStr = request.getParameter(Page.PAGE_SIZE);
		int pageSize = StringUtil.isNullOrBlank(pageSizeStr)?Page.DEFAULT_PAGE_SIZE:Integer.valueOf(pageSizeStr).intValue();
		if (pageSize < Page.DEFAULT_PAGE_SIZE) {
			pageSize = Page.DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}
	
}
