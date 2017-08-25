package com.stroe.admin.controller;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.controller.base.BaseController;

/**
 * 访问商城首页
 * @author zengjintao
 *2017年2月24日 下午21:35
 */
@ControllerBind(controllerKey="/")
public class IndexController extends BaseController{

	/**
	 * 访问商城首页
	 */
	public void index(){
		renderView("/login.vm");
	}
}
