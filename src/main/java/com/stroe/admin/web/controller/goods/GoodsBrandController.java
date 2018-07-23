package com.stroe.admin.web.controller.goods;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.service.goods.GoodsBrandService;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 品牌管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/goods/brand")
public class GoodsBrandController extends BaseController{

	@Inject
	private GoodsBrandService productBrandService;
	
	public  void index() {
		renderView("/goods/brand/index.vm");
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
