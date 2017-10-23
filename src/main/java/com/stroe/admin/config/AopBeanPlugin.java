package com.stroe.admin.config;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import com.jfinal.aop.Duang;
import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;
import com.stroe.admin.annotation.Aop;
import com.stroe.admin.annotation.Interceptor;
import com.stroe.admin.spring.AopManger;
import com.stroe.admin.util.PackageUtil;
import com.stroe.admin.util.StrKit;

/**
 * aop注入插件
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月25日上午8:14:05
 */
public class AopBeanPlugin<T> implements IPlugin{

	private static final Log LOG=Log.getLog(AopBeanPlugin.class);
	
    private List<String> beanList=new ArrayList<String>();
	
	@SuppressWarnings("rawtypes")
	private List<Class> excludeClasses=new ArrayList<Class>();
	
	private boolean autoScan;
	
	public AopBeanPlugin<T> setPackageName(String... packageName){
		if(packageName.length==0||packageName==null)
			throw new NullPointerException("packageName can not be null");
		for(String pack:packageName){
			beanList.add(pack);
		}
		return this;
	}
	
	public boolean isAutoScan() {
		return autoScan;
	}

	@SuppressWarnings("rawtypes")
	public AopBeanPlugin setAutoScan(boolean autoScan) {
		this.autoScan = autoScan;
		return this;
	}
	
	@Override
	public boolean start() {
		 List<Class<? extends T>> targetClass = PackageUtil.scanPackage(beanList);
		 for(Class<? extends T> target:targetClass){
			 if(excludeClasses.contains(target)){
				 continue;
			 }
			 Annotation[] annotations = target.getAnnotations();
			 if(target.getAnnotations() != null){
				 Object object = null;
				 String value = null;
				 for(Annotation annotation : annotations){
					 if(annotation instanceof Aop){
						 object= Duang.duang(target.getName(),target);
						 value=target.getAnnotation(Aop.class).value();
					 }else if(annotation instanceof Interceptor){
						 Class<? extends com.jfinal.aop.Interceptor>[] interceptorClass = ((Interceptor) annotation).target();
						 value =((Interceptor) annotation).value();
						 object = Duang.duang(target.getName(),target,interceptorClass);
					 }
				 }
				 String simpleName=target.getSimpleName().substring(1, target.getSimpleName().length());
				 String key=StrKit.toLowerCaseFirst(target.getSimpleName())+simpleName;
				 if(object != null){
					 if(StrKit.isNotEmpty(value)){
						 AopManger.beanMap.put(value, object);
					 }else{
						 AopManger.beanMap.put(key, object);
					 }
					 LOG.debug("created singleton aop bean '"+key+"'");
				 }
			 }
			 continue;
		 }
		return true;
	}

	
	@Override
	public boolean stop() {
		return true;
	}
	
	public AopBeanPlugin<T> addExcludeClasses(Class<?>... clazzes) {
        if (clazzes != null) {
            for (Class<?> clazz : clazzes) {
                excludeClasses.add(clazz);
            }
        }
        return this;
    }
}
