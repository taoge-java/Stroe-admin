package com.stroe.admin.web.controller.system;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 系统设置
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日下午12:41:23
 */
@ControllerBind(controllerKey="/system/set")
public class SystemSetController extends BaseController{

	public void index(){
		renderView("/system/set/index.vm");
	}
}
