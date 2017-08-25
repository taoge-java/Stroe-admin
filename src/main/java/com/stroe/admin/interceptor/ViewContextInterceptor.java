package com.stroe.admin.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.stroe.admin.config.SysConfig;

/**
 * 一些公共数据信息
 * @author zengjinto
 * 2017年2月23号  下午21:06
 */
public class ViewContextInterceptor  implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		HttpServletRequest  request=inv.getController().getRequest();
		request.setAttribute("number", new NumberTool());
		request.setAttribute("dateTool",new DateTool());
		request.setAttribute("resuoucesUpload", SysConfig.uploadPath);
		inv.invoke();
	}
}
