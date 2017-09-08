package com.stroe.admin.controller.picture;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.AopBean;
import com.stroe.admin.controller.base.BaseController;
import com.stroe.admin.service.picture.PictureService;

/**
 * 图片管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/picture")
public class PictureController extends BaseController{

	@AopBean
	private PictureService pictureService;
	
	public void index(){
		renderView("/picture/index.vm");
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
