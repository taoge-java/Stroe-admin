package com.stroe.admin.spring;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProxyBeanManger {

    public static  ConcurrentMap<String,Object> beanMap = new ConcurrentHashMap<String,Object>();
	
    private static ProxyBeanManger aopManger = new ProxyBeanManger();
    
    private ProxyBeanManger(){
    	
    }
    public static ProxyBeanManger getAopManger() {
		return aopManger;
	}
    
	public static ConcurrentMap<String, Object> getBeanMap() {
		return beanMap;
	}

	
	public static void setBeanMap(ConcurrentMap<String, Object> beanMap) {
		ProxyBeanManger.beanMap = beanMap;
	}

	public void put(String keyName,Object object){
		beanMap.put(keyName, object);
	}
	
	public Object get(String keyName){
		return beanMap.get(keyName);
	}
}
