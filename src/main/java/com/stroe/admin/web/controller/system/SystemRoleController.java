package com.stroe.admin.web.controller.system;

import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.Kv;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.service.system.SystemRoleService;
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
	
	@Override
	public void index() {
		renderView("/system/role/index.vm");
	}
	
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
		
	}
	
    public void edit(){
		
	}
    
    public void update(){
		
   	}

}
