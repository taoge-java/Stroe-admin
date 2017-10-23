/**
 * 
 */
package com.stroe.admin.web.session;

import java.util.Enumeration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import com.stroe.admin.redis.RedisCacheManger;
import com.stroe.admin.util.StrKit;


/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月29日下午9:51:04
 */
@SuppressWarnings("deprecation")
public class StroeHttpSessionWapper implements HttpSession {

    private static final long SESSION_TIME = TimeUnit.DAYS.toSeconds(1);
    
    private static final RedisCacheManger redisCacheManger = RedisCacheManger.getRedisCacheManger();
    
	@Override
	public Object getAttribute(String key) {
		return redisCacheManger.get(generateKey(key));
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return null;
	}

	@Override
	public long getCreationTime() {
		return 0;
	}

	@Override
	public String getId() {
		return getOrCreateSessionId();
	}

	private String getOrCreateSessionId(){
		String sessionid = getCookie("JSESSIONID");
        if (StrKit.isNotEmpty(sessionid)) {
            return sessionid;
        }

        sessionid = RequestManger.getRequestManger().getRequestAttr("JSESSIONID");
        if (StrKit.isNotEmpty(sessionid)) {
            return sessionid;
        }

        sessionid = UUID.randomUUID().toString().replace("-", "");
        RequestManger.getRequestManger().setRequestAttr("JSESSIONID", sessionid);
        setCookie("JSESSIONID", sessionid, (int) SESSION_TIME);
        return sessionid;
	}
	
	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxInactiveInterval() {
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getValue(String key) {
		return redisCacheManger.get(getOrCreateSessionId());
	}

	@Override
	public String[] getValueNames() {
		return null;
	}

	@Override
	public void invalidate() {
		
	}

	@Override
	public boolean isNew() {
		return false;
	}

	@Override
	public void putValue(String key, Object value) {
		redisCacheManger.set(generateKey(key), value);
	}

	@Override
	public void removeAttribute(String key) {
		redisCacheManger.delete(generateKey(key));
	}

	@Override
	public void removeValue(String key) {
		
	}

	@Override
	public void setAttribute(String key, Object value) {
		redisCacheManger.set(generateKey(key), value);
	}

	@Override
	public void setMaxInactiveInterval(int arg0) {
		
	}
	
	private String generateKey(String name){
		 return String.format("%s:%s", getOrCreateSessionId(), name);
	}

	/**
	 * @param string
	 * @param sessionid
	 * @param sessionTime
	 */
	private void setCookie(String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        RequestManger.getRequestManger().getResponse().addCookie(cookie);
	}

	/**
	 * @param string
	 * @return
	 */
	private String getCookie(String name) {
		  Cookie cookie = getCookieObject(name);
	      return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * @param name
	 * @return
	 */
	private Cookie getCookieObject(String name) {
        Cookie[] cookies = RequestManger.getRequestManger().getRequest().getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
            	if (cookie.getName().equals(name)){
            		return cookie;
            	}
            }
        }
        return null;
    }
}
