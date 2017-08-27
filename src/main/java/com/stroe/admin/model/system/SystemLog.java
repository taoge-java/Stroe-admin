package com.stroe.admin.model.system;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.admin.model.BaseModel;
/**
 * 系统日志表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:55:23
 */
@TableBind(tableName="system_log")
public class SystemLog extends BaseModel<SystemLog>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemLog dao=new SystemLog();

}
