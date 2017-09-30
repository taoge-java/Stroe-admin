/**
 * 
 */
package com.stroe.admin.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.stroe.admin.web.session.RequestManger;
import com.stroe.admin.web.session.StroeServletRequestWrapper;

/**
 * 为方便处理分布式session
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月29日下午8:51:41
 */
public class StroeHandler extends Handler {

	private RequestManger requestManger = RequestManger.getRequestManger();
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response,
			boolean[] isHandled) {
		 requestManger.handle(request, response);
		 doHandle(target, new StroeServletRequestWrapper(request), response, isHandled);
		
	}

	/**
	 * @param target
	 * @param stroeServletRequestWrapper
	 * @param response
	 * @param isHandled
	 */
	private void doHandle(String target, StroeServletRequestWrapper request,
			HttpServletResponse response, boolean[] isHandled) {
		next.handle(target, request, response, isHandled);
	}
}
