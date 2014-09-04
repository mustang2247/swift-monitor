package com.ganqiang.core.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * 处理增、删、改、查的基类Action <br>
 * 通过url参数key为 <code>method</code>的链接 <br>
 * e.g."http://ip:port?method=query"：调用query方法 <br>
 *
 */
public abstract class CRUDBaseAction extends BaseAction {
  
  private static Logger logger = Logger.getLogger(CRUDBaseAction.class);

	private static final long serialVersionUID = -6746486991488093585L;
	
	private static final String REQUEST_PARAM = "method";

	public abstract void query(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public abstract void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public abstract void modify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public abstract void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public abstract void detail(HttpServletRequest request,
	      HttpServletResponse response) throws ServletException, IOException;

	/**
	 * 通过反射调用相应的query/add/modify/delete方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  Class<?> clazz = this.getClass();   
	    String methodName = request.getParameter(REQUEST_PARAM);   
	    Method[] methods = null;   
	    try {   
	        Object obj = clazz.newInstance();   
	        methods = clazz.getDeclaredMethods();   
	        for (Method method : methods){   
	            if(method.getName().equalsIgnoreCase(methodName)){   
	                method.invoke(obj, new Object[]{request,response}); 
	            }
	        }     
	    }catch(Exception e){
	    	e.printStackTrace();
	    	logger.error("action调用失败:",e.getCause());
	    	RequestDispatcher rd = request.getRequestDispatcher("/jsp/layout/error.jsp");
	      rd.forward(request, response);
	    }
	}
	

}
