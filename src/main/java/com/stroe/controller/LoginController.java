package com.stroe.controller;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.common.BaseController;
/**
 * 
 * @author zengjintao
 * 2017年2月26日下午13:43
 */
@ControllerBind(controllerKey="/login")
public class LoginController extends BaseController{

	public void index(){
		String name=getPara("username");
		String password=getPara("password");
		
	}
	
	
	private void loginSuccess(String name,String password){
		
	}
}
