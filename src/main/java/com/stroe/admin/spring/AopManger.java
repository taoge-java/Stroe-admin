package com.stroe.admin.spring;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AopManger {

    public static  ConcurrentMap<String,Object> beanMap = new ConcurrentHashMap<String,Object>();
	
    private static AopManger aopManger = new AopManger();
    
    private AopManger(){
    	
    }
    public static AopManger getAopManger() {
		return aopManger;
	}
    
	public static ConcurrentMap<String, Object> getBeanMap() {
		return beanMap;
	}

	public static void setBeanMap(ConcurrentMap<String, Object> beanMap) {
		AopManger.beanMap = beanMap;
	}

	public void put(String keyName,Object object){
		beanMap.put(keyName, object);
	}
	
	public Object get(String keyName){
		return beanMap.get(keyName);
	}
}
