package com.stroe.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.jfinal.ext.route.ControllerBind;
import com.stroe.admin.dto.OnlineManger;
import com.stroe.admin.dto.UserSession;
import com.stroe.admin.util.ResultCode;
import com.stroe.admin.web.controller.base.BaseController;

/**
 * 访问商城首页
 * @author zengjintao
 *2017年2月24日 下午21:35
 */
@ControllerBind(controllerKey="/")
public class IndexController extends BaseController{

	/**
	 * 访问商城首页
	 */
	@Autowired
	private OnlineManger onlineManger;
	
	public void index(){
		renderView("/index.vm");
	}
		
	public void success(){
		renderView("/index.vm");
	}
	/**
	 * 客户端向服务器发送心跳包
	 */
	public void heart(){
		UserSession session = getCurrentUser();
		UserSession onlineUser = onlineManger.getUserSession(session.getSessionId());
		if(onlineUser == null){
			renderJson(new ResultCode(ResultCode.FAIL, "您的帐号已在"+session.getLast_login_ip()+"上登录,"+"请重新登录"+session.getLast_login_ip()));
			return;
		}
		renderNull();
	}
}
