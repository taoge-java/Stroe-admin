package com.stroe.admin.web.controller.goods;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.service.product.ProductInfoService;
import com.stroe.admin.util.ResultCode;
import com.stroe.admin.web.controller.base.BaseController;
/**
 * 品牌信息
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日下午12:04:00
 */
@ControllerBind(controllerKey="/goods/info")
public class GoodsInfoController extends BaseController{

	@Inject
	private ProductInfoService productInfoService;
	
	public void index(){
		renderView("/goods/info/index.vm");
	}
	
    public void list(){
		
	}
    
	public void add(){
		renderView("/goods/info/add.vm");
	}
	
	/**
	 * 图片上传
	 */
	public void uploadImage(){
		String path = uploadRename(getFile("file"));
		renderJson(new ResultCode(ResultCode.SUCCESS, path));
	}
	
    public void create(){
		
	}

    public void edit(){
		
	}
    
    public void update(){
		
	}
}
