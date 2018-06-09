/**
 * 
 */
package com.stroe.admin.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengjintao
 * @version 1.0
 * @createTime 2017年9月29日下午8:52:38
 */
public class RequestManager {

	private static RequestManager requestManger = new RequestManager();
	
	private ThreadLocal<HttpServletRequest> requests = new ThreadLocal<>();
	
	private ThreadLocal<HttpServletResponse> responses = new ThreadLocal<>();
	
	private RequestManager(){
		
	}
	
	public void release() {
        requests.remove();
        responses.remove();
	}
	 
	public void handle(HttpServletRequest req, HttpServletResponse response) {
        requests.set(req);
        responses.set(response);
	}
	 
	public static RequestManager getRequestManger(){
		return requestManger;
	}
	
	public HttpServletRequest getRequest() {
        return requests.get();
    }

    public HttpServletResponse getResponse() {
        return responses.get();
    }
    
    @SuppressWarnings("unchecked")
	public <T> T getRequestAttr(String key) {
        HttpServletRequest request = requests.get();
        if (request == null) {
            return null;
        }
        return (T) request.getAttribute(key);
    }

    public void setRequestAttr(String key, Object value) {
        HttpServletRequest request = requests.get();
        if (request == null) {
            return;
        }
        request.setAttribute(key, value);
    }
}
