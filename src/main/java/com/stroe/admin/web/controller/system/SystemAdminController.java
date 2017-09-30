package com.stroe.admin.web.controller.system;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.AopBean;
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

	@AopBean
	private SystemAdminService systemAdminService;
	
	
	public void index(){
		renderView("/image/index.vm");
	}
	
    public void list(){
		
	}
    
	public void add(){
		
	}
	
    public void create(){
		
	}

    public void edit(){
		
	}
    
    public void update(){
		
	}
}
