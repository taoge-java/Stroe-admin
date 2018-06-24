package com.stroe.admin.model.system;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.admin.model.BaseModel;

@TableBind(tableName="system_user_role")
public class SystemAdminRoleRef extends BaseModel<SystemAdminRoleRef>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static  SystemAdminRoleRef dao = new SystemAdminRoleRef();

}
