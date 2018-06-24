package com.stroe.admin.web.controller.system;


import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.service.system.SystemAdminService;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 管理员管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日下午12:06:28
 */
@ControllerBind(controllerKey="/system/admin")
public class SystemAdminController extends BaseController{

	@Inject
	private SystemAdminService systemAdminService;
	
	
	public void index(){
		renderView("/system/admin/index.vm");
	}
	
    public void list(){
    	String loginName = getPara("login_name");
    	int pageNumber = getParaToInt("pageNumber",1);
    	Result result = systemAdminService.list(loginName, pageNumber);
    	setAttr("page", result.getDefaultModel());
    	renderView("/system/admin/list.vm");
	}
    
	public void add(){
		render("");
	}
	
    public void create(){
		
	}

    public void edit(){
		
	}
    
    public void update(){
		
	}
}
