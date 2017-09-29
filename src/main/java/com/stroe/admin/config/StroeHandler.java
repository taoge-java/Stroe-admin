/**
 * 
 */
package com.stroe.admin.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.stroe.admin.kit.RequestManger;
import com.stroe.admin.session.StroeServletRequestWrapper;


/**
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
