package com.stroe.admin.web.interceptor;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.spring.ProxyBeanManger;
/**
 * aop对象注入拦截器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月24日下午9:18:12
 */
public class ProxyBeanInterceptor implements Interceptor{
	
    private  ApplicationContext ctx;
	
    private static final ProxyBeanManger proxyBeanManger = ProxyBeanManger.getProxyBeanManger();
    
	public ProxyBeanInterceptor(ApplicationContext ctx){
		this.ctx = ctx;
	}
	
	public ProxyBeanInterceptor(){

	}
	
	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		//controller层aop的自动注入
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(Inject.class)){
				bean = proxyBeanManger.getBeanMap().get(field.getName());
				initServiceBean(bean,field);
			}else if(field.isAnnotationPresent(Autowired.class)){
				bean = ctx.getBean(field.getName());
				initServiceBean(bean,field);
			}else{
				continue ;
		    }
			try {
				if (bean != null) {
					field.setAccessible(true);
					field.set(controller, bean);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		inv.invoke();
	}
	
	private  void initServiceBean(Object bean,Field field){
		Class<?> cla = field.getType();
		//service层aop的自动注入
		for(Field f : cla.getDeclaredFields()){
			Object serviceBean = null;
			if(f.isAnnotationPresent(Inject.class)){
				serviceBean = proxyBeanManger.getBeanMap().get(f.getName());
			}else if(f.isAnnotationPresent(Autowired.class)){
				serviceBean = ctx.getBean(f.getName());
			}
			if(serviceBean != null){
				try {
					f.setAccessible(true);
					f.set(bean, serviceBean);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new NullPointerException("Field can not be null");
				}
			}
		}
	}
}
