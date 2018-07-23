package com.stroe.admin.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProxyBeanManger {

    private static final Map<String,Object> beanMap = new ConcurrentHashMap<String,Object>();
	
    private static final ProxyBeanManger proxyBeanManger = new ProxyBeanManger();
    
    private ProxyBeanManger(){
    	
    }
    public static ProxyBeanManger getProxyBeanManger() {
		return proxyBeanManger;
	}
    
	public  Map<String, Object> getBeanMap() {
		return beanMap;
	}

	public void put(String keyName,Object object){
		beanMap.put(keyName, object);
	}
	
	public Object get(String keyName){
		return beanMap.get(keyName);
	}
}
