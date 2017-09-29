package com.stroe.admin.controller.information;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.controller.base.BaseController;
import com.stroe.admin.model.system.SystemAdmin;
import com.stroe.admin.util.ResultCode;

/**
 * 资讯管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/information")
public class InformationController extends BaseController{

	public void index(){
		renderView("/information/index.vm");
	}
		
	public void test(){
		String name = getPara("name");
		SystemAdmin systemAdmin = SystemAdmin.dao.findById(3);
		int count = systemAdmin.getInt("login_count");
		systemAdmin.set("login_count", count-Integer.parseInt(name));
		systemAdmin.update();
		renderJson(new ResultCode(ResultCode.SUCCESS, "购买成功"));
	}
	
    public void list(){
		
	}
    
	public void add(){
		renderView("/information/add.vm");
	}
	
    public void create(){
		
	}

    public void edit(){
		
	}
    
    public void update(){
		
	}
}
