package com.stroe.admin.service.system;

import java.util.List;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.stroe.admin.annotation.Bean;
import com.stroe.admin.model.system.SystemAdminRole;
import com.stroe.admin.model.system.SystemRoleOperRef;
import com.stroe.admin.service.base.BaseService;
import com.stroe.admin.service.base.DefaultResult;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.util.ResultCode;
import com.stroe.admin.util.StrKit;

@Bean
public class SystemService extends BaseService{
	
	private static final Log logger = Log.getLog(SystemService.class);
	
	/**
	 * 获取用户所拥有的角色
	 * @param adminId
	 * @return
	 */
	public List<SystemAdminRole> findRoles(int adminId){
		return SystemAdminRole.dao.find("select role_id from system_admin_role where admin_id=?",adminId);
	}
	
	/**
	 * 保存权限
	 * @param roleId
	 * @param session
	 * @param operIds
	 */
	public Result saveOper(int roleId,String operIds){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS,"操作成功");
		try{
			Db.update("delete from system_role_oper_ref where role_id=?", roleId);
			if (StrKit.isEmpty(operIds)) {
				result.setResultCode(resultCode);
				return result;
			}
			String[] opers = operIds.split(",");
			SystemRoleOperRef roleOperRef = null;
			for (String oper_id: opers) {
				roleOperRef =  new SystemRoleOperRef();
				roleOperRef.set("role_id", roleId);
				roleOperRef.set("oper_id", Integer.parseInt(oper_id) );
				roleOperRef.save();
			}
			result.setResultCode(resultCode);
		}catch(Exception e){
			resultCode = new ResultCode(ResultCode.FAIL,"保存异常");
			logger.error("保存权限异常", e);
		}
		return result;
	}
}
