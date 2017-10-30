package com.stroe.admin.service.system;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jfinal.kit.Kv;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.stroe.admin.annotation.Bean;
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
	
	/**
	 * 根据角色获取操作权限列表
	 * @param roleId 角色id
	 * @return
	 */
	public Set<String> findRoleById(int roleId){
		SystemRole systemRole = SystemRole.dao.findById(roleId);
		List<SystemOper> oper = null;
		if(systemRole.getBoolean("super_flag")){
			oper = SystemOper.dao.find("select * from system_oper");
		}else{
			//查询角色所有操作权限
			oper = SystemOper.dao.find("select * from system_oper where id in(select oper_id from system_role_oper_ref where role_id="+roleId+")");
		}
		Set<String> operCode = new LinkedHashSet<String>();
		for(SystemOper code : oper){
			operCode.add(code.getStr("oper_code"));
		}
		return operCode;
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
				jsonObject.put("pId", "menu_"+oper.getInt("menu_id"));
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
