package com.stroe.admin.web.controller.product;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 品牌分类管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/product/category")
public class ProductCategoryController extends BaseController{

	public void index(){
		renderView("/product/category/index.vm");
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
