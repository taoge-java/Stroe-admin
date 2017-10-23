package com.stroe.admin.mail;

/**
 * 邮件客户端接口
 * @author zenfjintao
 * 2017年2月24日 下午21:33
 */
public interface MailClient {

	/**
	 * 
	 * @param subject  主题
	 * @param content  内容
	 * @param to  收件人
	 * @return
	 */
	public boolean senMsg(String subject,String content,String to);
	
	/**
	 * 
	 * @param subject
	 * @param content
	 * @param to
	 * @param path 文件路径
	 * @param fileName 文件名称
	 * @return
	 */
	public boolean senMsg(String subject,String content,String to,String path,String fileName);
	
	/**
	 * 
	 * @param subject
	 * @param content
	 * @param to
	 * @param path
	 * @param fileName
	 * @param cc 抄送
	 * @param bcc 密送
	 * @return
	 */
	public boolean sendMsg(String subject,String content,String to,String path,String fileName,String cc,String bcc);
}
