package com.stroe.controller;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.common.BaseController;

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
		RenderView("/login.vm");
	}
}
