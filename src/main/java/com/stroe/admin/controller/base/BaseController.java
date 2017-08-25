package com.stroe.admin.controller.base;

import com.jfinal.core.Controller;
import com.stroe.admin.config.SysConfig;

/**
 * 控制器父类
 * @author zengjintao
 * 2017年2月23号  下午21:06
 */
public class BaseController extends Controller{

	public  int pageSize=20;
	
	public  void renderView(String viewPath){
		render(SysConfig.BASE_VIEW+viewPath);
	}
	
}
