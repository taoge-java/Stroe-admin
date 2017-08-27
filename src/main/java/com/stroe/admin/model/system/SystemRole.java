package com.stroe.admin.model.system;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.admin.model.BaseModel;

/**
 * 角色表
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月27日 下午3:55:02
 */
@TableBind(tableName="system_role")
public class SystemRole extends BaseModel<SystemRole>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821093056423411249L;
	
	public static final SystemRole dao=new SystemRole();


}
