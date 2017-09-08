package com.stroe.admin.controller.userInfo;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.AopBean;
import com.stroe.admin.controller.base.BaseController;
import com.stroe.admin.service.userInfo.UserInfoService;

/**
 * 会员管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日下午12:04:14
 */
@ControllerBind(controllerKey="/userInfo")
public class UserInfoController extends BaseController{

	@AopBean
	private UserInfoService userInfoService;
	
	public void index(){
		renderView("/userInfo/index.vm");
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
