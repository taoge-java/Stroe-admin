/**
 * 
 */
package com.stroe.admin.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.stroe.admin.redis.RedisUtil;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月29日下午9:51:04
 */
@SuppressWarnings("deprecation")
public class StroeHttpSessionWapper implements HttpSession {

	
	@Override
	public Object getAttribute(String key) {
		return RedisUtil.get(key);
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAttribute(String key) {
		
	}

	@Override
	public void removeValue(String key) {
		
	}

	@Override
	public void setAttribute(String key, Object value) {
		RedisUtil.set(key, value);
	}

	@Override
	public void setMaxInactiveInterval(int arg0) {
		
	}
}
