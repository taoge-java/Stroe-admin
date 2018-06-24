package com.stroe.admin.service.system;

import java.util.List;


import com.stroe.admin.annotation.Bean;
import com.stroe.admin.model.system.SystemAdminRoleRef;

@Bean
public class SystemService {
	
	/**
	 * 获取用户所拥有的角色
	 * @param adminId
	 * @return
	 */
	public List<SystemAdminRoleRef> findRoles(int adminId){
		return SystemAdminRoleRef.dao.find("select role_id from system_user_role where admin_id=?",adminId);
	}
}
