package com.stroe.admin.model.system;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.admin.model.BaseModel;

@TableBind(tableName="system_admin_role")
public class SystemAdminRole extends BaseModel<SystemAdminRole>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final SystemAdminRole dao = new SystemAdminRole();

}
