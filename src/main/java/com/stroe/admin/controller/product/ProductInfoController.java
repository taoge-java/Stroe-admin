package com.stroe.admin.controller.product;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.AopBean;
import com.stroe.admin.controller.base.BaseController;
import com.stroe.admin.service.product.ProductInfoService;
/**
 * 品牌信息
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日下午12:04:00
 */
@ControllerBind(controllerKey="/product/info")
public class ProductInfoController extends BaseController{

	@AopBean
	private ProductInfoService productInfoService;
	
	public void index(){
		renderView("/product/info/index.vm");
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
