package com.stroe.admin.controller.product;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.controller.base.BaseController;

/**
 * 品牌管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/product/brand")
public class ProductBrandController extends BaseController{

	public  void index() {
		renderView("/product/brand/index.vm");
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
