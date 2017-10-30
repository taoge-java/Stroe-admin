package com.stroe.admin.web.controller.picture;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.service.picture.PictureService;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 图片管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/picture")
public class PictureController extends BaseController{

	@Inject
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
