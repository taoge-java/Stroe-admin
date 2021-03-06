 package com.stroe.admin.web.interceptor;

import javax.servlet.http.HttpServletRequest;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.stroe.admin.config.SysConfig;
import com.stroe.admin.constant.CommonConstant;
import com.stroe.admin.dto.UserSession;
/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月22日 下午5:29:20
 */
public class ViewContextInterceptor implements 	Interceptor{

	@Override
	public void intercept(Invocation inv) {
        HttpServletRequest request = inv.getController().getRequest();
		UserSession session = (UserSession) request.getSession().getAttribute(CommonConstant.SESSION_ID_KEY);
		if(session != null){
			request.setAttribute("session", session);
		}
		request.setAttribute("resourceDown", SysConfig.resourceDown);
		request.setAttribute("resourceUpload", SysConfig.resourceUpload);
		inv.invoke();
	}
}
