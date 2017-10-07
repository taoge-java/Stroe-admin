package com.stroe.admin.web.controller.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.stroe.admin.config.SysConfig;
import com.stroe.admin.constant.CommonConstant;
import com.stroe.admin.dto.UserSession;
import com.stroe.admin.model.system.SystemLog;
import com.stroe.admin.util.DateUtil;
import com.stroe.admin.util.IpUtils;
import com.stroe.admin.util.NumberUtils;

/**
 * 控制器父类
 * @author zengjintao
 * 2017年2月23号  下午21:06
 */
public abstract class BaseController extends Controller{

	public  int pageSize = 30;
	
	public abstract void index();
	
	public void renderView(String path){
		render(SysConfig.BASE_VIEW+path);
	}
	
    /**
     * 获取在线用户信息
     * @return
     */
	public  UserSession getCurrentUser(){
		return  getSessionAttr(CommonConstant.SESSION_ID_KEY);
	}
	
	/**
	 * 获取登录用户名
	 * @return
	 */
	public String getUserName(){
		return getCurrentUser().getLoginName();
	}
	
	/**
	 * 系统日志记录
	 * @param oper_des
	 * @param type
	 */
	@SuppressWarnings("static-access")
	public void systemLog(String oper_des,int type){
		UserSession user=getCurrentUser();
		SystemLog systemLog=new SystemLog();
		systemLog.set("oper_name", user.getLoginName());
		systemLog.set("admin_id", user.getUserId());
		systemLog.set("oper_time", new DateUtil().getDate());
		systemLog.set("oper_ip", IpUtils.getAddressIp(getRequest()));
		systemLog.set("login_type",type);
		systemLog.set("oper_desc",oper_des);
		systemLog.save();
	}
	
	/**
	 * 文件上传重命名
	 * @param upload
	 * @return
	 */
	public String uploadRename(UploadFile upload){
		File file=upload.getFile();
		try {
			FileInputStream in=new FileInputStream(file);
			String fileName=upload.getFileName();
			String style=fileName.substring(fileName.indexOf(","),fileName.length());
			String newName=NumberUtils.getMessageNum(4)+style;
			String imagePath=getImagePath();
			String basePath=SysConfig.resourceUpload+"/"+imagePath;
			File fi=new File(basePath);
			if(!fi.exists()){
				fi.mkdirs();
			}
			File upFile=new File(basePath,newName);
			FileOutputStream out=new FileOutputStream(upFile);
			byte[] bytes=new byte[1024];
			int flag=0;
			while((flag=in.read(bytes, 0, 1024))!=-1){
				out.write(bytes, 0, flag);
			}
			if(out!=null){
				out.close();
			}
			if(in!=null){
				in.close();
			}
			file.delete();
			return basePath+"/"+newName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成新的图片文件
	 * @return
	 */
	public String getImagePath(){
		DateFormat format = new SimpleDateFormat("yyyy-MMdd");
		return format.format(new Date()).replaceAll("-", "/");
	}
	
	/**
	 * 发送站内信
	 * @param userId
	 * @param title
	 * @param content
	 */
	public void sendMessage(int userId,String title,String content){
		
	}
	
}
