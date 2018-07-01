package com.stroe.admin.web.controller.system;

import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.Kv;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.annotation.Permission;
import com.stroe.admin.model.system.SystemRole;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.service.system.SystemRoleService;
import com.stroe.admin.service.system.SystemService;
import com.stroe.admin.util.ResultCode;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 角色管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日下午12:06:54
 */
@ControllerBind(controllerKey="/system/role")
public class SystemRoleController extends BaseController{

	@Inject
	private SystemRoleService systemRoleService;
	
	@Inject
	private SystemService systemService;
	
	@Permission(points = {"1_8_1_1"})
	@Override
	public void index() {
		renderView("/system/role/index.vm");
	}
	
	@Permission(points = {"1_8_1_1"})
    public void list(){
    	int currentPage = getParaToInt("currentPage",1);
    	String roleName = getPara("roleName");
    	Kv condition = Kv.by("currentPage", currentPage).set("pageSize",pageSize)
    			         .set("roleName",roleName);
    	Result result = systemRoleService.paginate(currentPage,pageSize,condition);
    	setAttr("page", result.getDefaultModel());
    	renderView("/system/role/list.vm");
	}

    public void operRole(){
    	int roleId = getParaToInt("id");
		String operList = systemRoleService.getOperByRoId(roleId);
		setAttr("operList", operList);
		renderView("/system/role/oper.vm");
    }
    
	public void add(){
		renderView("/system/role/add.vm");
	}
	
	/**
	 * 创建角色
	 */
	public void create(){
		String roleName = getPara("roleName");
		String remark = getPara("remark");
		Result result = systemRoleService.cretaeRole(roleName, remark);
		renderJson(result.getResultCode());
	}
	
    public void edit(){
    	int roleId = getParaToInt(0);
    	SystemRole systemRole = systemRoleService.findByid(roleId);
    	setAttr("systemRole", systemRole);
    	renderView("/system/role/edit.vm");
	}
    
    public void update(){
    	int roleId = getParaToInt("role_id");
    	String roleName = getPara("roleName");
		String remark = getPara("remark");
		Result result = systemRoleService.updateRole(roleId, roleName, remark);
		renderJson(result.getResultCode());
   	}
    
    /**
	 * 保存用户操作权限集合
	 */
	public void saveOper(){
		int roleId = getParaToInt("roleId");
		String operIds = getPara("operIds");
		Result result = systemService.saveOper(roleId, operIds);
		if(result.isSuccess()){
			renderJson(new ResultCode(ResultCode.SUCCESS, "操作成功"));
		}else{
			renderJson(new ResultCode(ResultCode.FAIL, "操作失败"));
		}
	}
}
