package com.stroe.admin.model;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * 实体表基类
 * @author zengjintao
 * 2017年2月23号  下午21:06
 */
@SuppressWarnings("rawtypes")
public class BaseModel <M extends Model> extends Model<M>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public void getParamters(Map<String,String[]> map){
		
		String modelName = getClass().getSimpleName();
		/**
		 * 遍历集合
		 */
	    for(Entry<String, String[]> e: map.entrySet()){
	    	//获得map的key值
			String paraKey=e.getKey();
			if(paraKey.toLowerCase().startsWith(modelName.toLowerCase()+".")){
				if(e.getValue().length == 1) {
					set(paraKey.substring(paraKey.indexOf(".")+1), e.getValue()[0]);
				}else if(e.getValue().length > 1) {
					set(paraKey.substring(paraKey.indexOf(".")+1), StringUtils.join(e.getValue(), ","));
				}
			}
		}
	}

}
