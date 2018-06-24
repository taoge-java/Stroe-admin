package com.stroe.admin.web.controller.goods;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 品牌分类管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/goods/category")
public class GoodsCategoryController extends BaseController{

	public void index(){
		renderView("/goods/category/index.vm");
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
