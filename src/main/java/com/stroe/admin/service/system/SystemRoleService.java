package com.stroe.admin.service.system;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jfinal.kit.Kv;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.stroe.admin.annotation.Bean;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.dto.UserSession;
import com.stroe.admin.model.system.SystemAdminRole;
import com.stroe.admin.model.system.SystemMenu;
import com.stroe.admin.model.system.SystemOper;
import com.stroe.admin.model.system.SystemRole;
import com.stroe.admin.service.base.BaseService;
import com.stroe.admin.service.base.DefaultResult;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.util.ResultCode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 角色管理services
 * @author zjt
 */
@Bean
public class SystemRoleService extends BaseService{
	
	private static final Log LOG = Log.getLog(SystemRoleService.class);
	
	@Inject
	private SystemService systemService;
	
	/**
	 * 角色列表
	 * @param condition
	 * @return
	 */
	public Result paginate(int currentPage,int pageSize,Kv condition){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS);
		try {
			SqlPara sqlPara = SystemRole.dao.getSqlPara("findRoleList",condition);
			Page<SystemRole> page = SystemRole.dao.paginate(currentPage, pageSize, sqlPara);
			result.setDefaultModel(page);
		} catch (Exception e) {
			resultCode = new ResultCode(ResultCode.FAIL);
			LOG.error("查询异常",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	public Result cretaeRole(String roleName,String remark){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS,"操作成功");
		try {
			SystemRole systemRole = SystemRole.dao.findFirst("select * from system_role where role_name=?",roleName);
			if(systemRole != null){
				resultCode = new ResultCode(ResultCode.FAIL,"该角色已经存在,请勿重复创建");
				result.setResultCode(resultCode);
				return result;
			}
			SystemRole role = new SystemRole();
			role.set("role_name", roleName);
			role.set("remark", remark);
			role.set("created_at", new Date());
			role.save();
		} catch (Exception e) {
			LOG.error("操作异常", e);
			resultCode = new ResultCode(ResultCode.FAIL,"操作异常");
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	/**
	 * 根据角色获取操作权限列表
	 * @param roleId 角色id
	 * @return
	 */
	public Set<String> findOperCode(UserSession userSession){
		List<SystemAdminRole> list = systemService.findRoles(userSession.getUserId());
		String roleIds = "";
		//获取用户所有角色
		for (SystemAdminRole systemAdminRoleRef : list) {
			roleIds += systemAdminRoleRef.getInt("role_id") + ",";
		}
		
		List<SystemRole> roles = SystemRole.dao.find("select super_flag,role_name from system_role where id in (?)",roleIds.substring(0,roleIds.length()-1));
		/**
		 * 判断是否拥有超级管理员权限
		 */
		for (SystemRole role : roles) {
			userSession.addRole(role.getStr("role_name"));
			if(role.getBoolean("super_flag") && !userSession.isSuperFlag()){
				userSession.setSuperFlag(true);
			}
		}
		Set<String> operCode = new LinkedHashSet<String>();
		List<SystemOper> oper = null;
		if(userSession.isSuperFlag()){//超级管理员
		    oper = SystemOper.dao.find("select * from system_oper");
		}else{
			//获取角色操作权限
		    oper = SystemOper.dao.find("select * from system_oper where id "
					+ "in(select oper_id from system_role_oper_ref "
					+ "where role_id in("+roleIds.substring(0,roleIds.length()-1)+"))");
		}
		for(SystemOper code : oper){
			operCode.add(code.getStr("oper_code"));
		}
		return operCode;
	}
	
	public SystemRole findByid(int id){
		return SystemRole.dao.findById(id);
	}
	
	public Result updateRole(int id,String roleName,String remark){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS,"操作成功");
		try {
			SystemRole systemRole = findByid(id);
			systemRole.set("role_name", roleName);
			systemRole.set("remark", remark);
			systemRole.set("updated_at", new Date());
			systemRole.update();
		} catch (Exception e) {
			LOG.error("操作异常", e);
			resultCode = new ResultCode(ResultCode.FAIL,"操作异常");
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	/**
	 * 获取权限列表
	 * @param role_id
	 * @return
	 */
	public String getOperByRoId(int role_id){
		HashSet<String> hasOper = new HashSet<String>();
		List<SystemOper> operList = SystemOper.dao.find("select * from system_oper a,system_role_oper_ref b where a.id=b.oper_id and b.role_id=?",role_id);
		if(operList != null){
			for(SystemOper oper: operList){
				hasOper.add(oper.getStr("oper_code"));
			}
		}
		//所有菜单
		List<SystemMenu> menuList = SystemMenu.dao.find("select * from system_menu");
		JSONArray jsonarray = new JSONArray();
		if(menuList.size()>0 && menuList != null){
			JSONObject jsonObject = null;
			for(SystemMenu menu : menuList){
				jsonObject = new JSONObject();
				jsonObject.put("id", "menu_"+ menu.getInt("id"));
				jsonObject.put("pId", menu.get("parent_id") == null? "menu_" + menu.getInt("id"):"menu_" + menu.getInt("parent_id"));
				jsonObject.put("name", menu.getStr("menu_name"));
				jsonObject.put("open", false);
				jsonarray.add(jsonObject);
			}
		}
		//所有操作
		List<SystemOper> allOperList = SystemOper.dao.find("select * from system_oper");
		if(allOperList != null && allOperList.size()>0){
			JSONObject jsonObject = null;
			for(SystemOper oper : allOperList){
				jsonObject = new JSONObject();
				jsonObject.put("id", oper.getInt("id"));
				jsonObject.put("name", oper.getStr("oper_name"));
				jsonObject.put("pId", "menu_" + oper.getInt("menu_id"));
				jsonObject.put("code",oper.getStr("oper_code"));
				if(hasOper.contains(oper.getStr("oper_code"))){
					jsonObject.put("checked", true);
				}
				jsonObject.put("open", false);
				jsonarray.add(jsonObject);
			}
		}
		return jsonarray.toString();
	}
}
