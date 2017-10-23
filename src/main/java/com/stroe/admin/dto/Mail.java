package com.stroe.admin.dto;

import java.util.Properties;


public class Mail {
	//邮件用户名
	private String username;
	//密码
	private String password;
	//发送者
	private String from;
	//邮件主机
	private String mailHost;
	//邮件端口号
	private int mailPort;
	
	public Mail() {
		init();
	}
	private Properties properties;
	
	public static final String  PORT="smtp";
	
	public static final String MAILER = "smtpsend";			
	public String getUsername() {
		return username;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Mail(String username, String password, String from, String mailHost, int port, boolean auth) {
		this.username = username;
		this.password = password;
		this.from = from;
		this.mailHost = mailHost;
		this.mailPort = port;
		this.auth = auth;
		this.properties=init();
	}
	private Properties init() {
		Properties p=new Properties();;
		p.put("mail.smtp.host", mailHost);
		if(auth){
			p.put("mail.smtp.auth", "true");
		}
		return p;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMailHost() {
		return mailHost;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public int getMailPort() {
		return mailPort;
	}
	public void setMailPort(int mailPort) {
		this.mailPort = mailPort;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	//是否开启密码验证
	private boolean auth;
}
