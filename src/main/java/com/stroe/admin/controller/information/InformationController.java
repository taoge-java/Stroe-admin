package com.stroe.admin.controller.information;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.controller.base.BaseController;

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
