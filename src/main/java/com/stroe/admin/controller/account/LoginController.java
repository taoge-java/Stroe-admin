package com.stroe.admin.controller.account;


import org.springframework.beans.factory.annotation.Autowired;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Log;
import com.stroe.admin.common.CommonConstant;
import com.stroe.admin.common.CommonEnum.LogType;
import com.stroe.admin.controller.base.BaseController;
import com.stroe.admin.dto.OnlineManger;
import com.stroe.admin.dto.OnlineUser;
import com.stroe.admin.dto.UserSession;
import com.stroe.admin.model.system.SystemAdmin;
import com.stroe.admin.util.DateUtil;
import com.stroe.admin.util.EncryptUtil;
import com.stroe.admin.util.ImageUtil;
import com.stroe.admin.util.IpUtils;
import com.stroe.admin.util.Md5Utils;
import com.stroe.admin.util.ResultCode;
import com.stroe.admin.util.StrKit;
/**
 * 用户登录
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/account")
public class LoginController extends BaseController{

	private static final Log LOG = Log.getLog(LoginController.class);
	
	@Autowired
	private OnlineManger onlineManger;
    
	/**
	 * 用户登录页面
	 */
	public void index(){
		renderView("/account/login.vm");
	}

	/**
	 * 验证码
	 */
	public void image(){
		ImageUtil image = new ImageUtil();
		render(image);
	}

	public void login(){
		String userName = getPara("username");
		String password = getPara("password");
		String code = getPara("code");
		String number = (String) this.getSession().getAttribute(CommonConstant.IMAGE_CODE);//获取session中得验证码
		String remberPassword[]= getParaValues("checkbox");//判断用户是否记住密码
		
		SystemAdmin admin = SystemAdmin.dao.findFirst("select * from system_admin where login_name=?",userName);
		if(admin == null){
			renderJson(new ResultCode(ResultCode.FAIL,"用户不存在"));
			return;
		}
		//判断用户是否选择自动登陆
		if(remberPassword != null && remberPassword.length>0){//将用户名密码保存在cookie中
			String encryptUserInfo = EncryptUtil.encryptUserInfo(admin.getInt("id").toString(), password);
			setCookie(CommonConstant.COOKIE_USER_ID,encryptUserInfo,60*60*24*30,"/");
		}else{//清除cookie
			removeCookie(CommonConstant.COOKIE_USER_ID, "/");
		}
		if(StrKit.isEmpoty(code)){//如果用户没有输入验证码
			   loginSrvice(admin,password);
		}else{//出现验证码
			if(code.equalsIgnoreCase(number)){
		     	loginSrvice(admin,password);//验证码不区分大小写
			}else{
				renderJson(new ResultCode(ResultCode.FAIL,"用户不存在"));
				return;
			}
		}
	}
	

	/**
	 * @param admin
	 * @param password
	 * @param code
	 * @param number
	 */
	private void loginSrvice(SystemAdmin admin, String password) {
		UserSession session = onlineManger.getUserSessionById(admin.getInt("id"));
		if(session != null){//如果用户已经登录
		    onlineManger.remove(session);
		}
		if(admin.getStr("password").equals(Md5Utils.getMd5(password, admin.getStr("encrypt")))){
			if(admin.getBoolean("disabled_flag")){
				renderJson(new ResultCode(ResultCode.FAIL, "用户已被禁用,请联系管理员"));
				return;
			}
			loginSuccess(admin);//登录成功
			renderJson(new ResultCode(ResultCode.SUCCESS, "登录成功"));
		}else{
			LOG.error("用户名或密码错误");
			renderJson(new ResultCode(ResultCode.FAIL, "用户名或密码错误"));
			return;
		}
	}

	/**
	 * 登录成功
	 */
	private void loginSuccess(SystemAdmin admin) {
		OnlineUser online = new OnlineUser();
		UserSession session = new UserSession();
		session.setSessionId(online.getSessionId());
		session.setUserId(admin.getInt("id"));
		session.setHeartTime(System.currentTimeMillis());
		session.setLast_login_time(DateUtil.getSecondDate(admin.getDate("last_login_time")));
		session.setLoginName(admin.getStr("login_name"));
		session.setSuperFlag(admin.getBoolean("super_flag") ? true : false);
		session.setNickName(admin.getStr("nickname"));
		session.setMobile(admin.getStr("mobile"));
		admin.set("last_login_time",DateUtil.getDate());
		admin.set("last_login_ip",IpUtils.getAddressIp(getRequest()));
		admin.set("login_count",admin.getInt("login_count"+1));
		admin.update();
		session.setLast_login_ip(admin.getStr("last_login_ip"));
		setSessionAttr(CommonConstant.SESSION_ID_KEY, session);
		onlineManger.add(session);
		systemLog("登录系统",LogType.LOGIN.getValue());
	}
	/**
	 * 用户注销
	 */
	public void exit(){		
		if(getCurrentUser() != null){
			if(onlineManger.getUserSession(getCurrentUser().getSessionId()) != null){//移除sessionid
				onlineManger.remove(getCurrentUser());
        	}
			systemLog("登出系统",LogType.LOGIN.getValue());
			getRequest().getSession().removeAttribute(CommonConstant.SESSION_ID_KEY);
			getRequest().getSession().invalidate();//用户注销
			removeCookie(CommonConstant.COOKIE_USER_ID, "/");//清除cookie
			redirect("/",false);
		}
	}
}
