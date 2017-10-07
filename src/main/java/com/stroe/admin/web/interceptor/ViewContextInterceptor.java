 package com.stroe.admin.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
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
		request.setAttribute("dateTool", new DateTool());
		request.setAttribute("number", new NumberTool());
		UserSession session=(UserSession) request.getSession().getAttribute(CommonConstant.SESSION_ID_KEY);
		if(session!=null){
			request.setAttribute("session", session);
		}
		inv.invoke();
	}
}
