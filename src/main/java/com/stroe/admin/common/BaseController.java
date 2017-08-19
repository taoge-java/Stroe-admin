package com.stroe.admin.common;

import com.jfinal.core.Controller;
import com.stroe.admin.config.StroeConfig;

/**
 * 控制器父类
 * @author zengjintao
 * 2017年2月23号  下午21:06
 */
public class BaseController extends Controller{

	public  int pageSize=20;
	
	public  void RenderView(String viewPath){
		render(StroeConfig.BASE_VIEW+viewPath);
	}
	
}
