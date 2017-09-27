package com.stroe.admin.util;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ClassUtil {

	private static final Injector injector = Guice.createInjector();
	
	private Object object;
	
	public ClassUtil(Class<?> target){
		this.object=target;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(){
		return (T) object;
	}
	
	public static <T>  T newInstance(Class<T> cla){
		return injector.getInstance(cla);
	}
	
	public static Class<?> forName(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ClassUtil getInstance(Class<?> type){
		return new ClassUtil(type);
	}
}
