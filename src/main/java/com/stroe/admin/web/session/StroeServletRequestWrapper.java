/**
 * 
 */
package com.stroe.admin.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import com.stroe.admin.redis.RedisCacheManger;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月29日下午9:44:23
 */
public class StroeServletRequestWrapper extends HttpServletRequestWrapper {

	@SuppressWarnings("unused")
	private HttpServletRequest originHttpServletRequest;
	
	private HttpSession httpSession;
	
    private static final RedisCacheManger redisCacheManger = RedisCacheManger.getRedisCacheManger();
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
    		if(redisCacheManger.hasCache()){
    			httpSession = new StroeHttpSessionWapper();
    		}else{
    			httpSession = super.getSession(create);
    		}
        }
        return httpSession;
    }
}
