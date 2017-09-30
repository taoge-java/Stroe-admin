/**
 * 
 */
package com.stroe.admin.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月29日下午9:44:23
 */
public class StroeServletRequestWrapper extends HttpServletRequestWrapper {

	HttpServletRequest originHttpServletRequest;
    HttpSession httpSession;
    
	/**
	 * @param request
	 */
	public StroeServletRequestWrapper(HttpServletRequest request) {
		super(request);
        this.originHttpServletRequest = request;
	}
	
    @Override
    public HttpSession getSession() {
    	return getSession(true);
    }
    
    @Override
    public HttpSession getSession(boolean create) {
    	if (httpSession == null) {
            httpSession = new StroeHttpSessionWapper();
        }
        return httpSession;
    }
}
