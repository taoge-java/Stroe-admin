package com.stroe.admin.mail;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.jfinal.log.Log;
import com.stroe.admin.dto.Mail;
import com.sun.mail.smtp.SMTPTransport;


/**
 * 邮件接口实现类
 * @author zengjintao
 * 2017年3月13号下午17:28
 */
public class MailClientImpl implements MailClient{

	private static final Log LOG = Log.getLog(MailClientImpl.class);
	
	private Mail mail;
	
	public MailClientImpl(Mail mail) {
		this.mail = mail;
	}

	/**
	 * 
	 * @param subject  主题
	 * @param content  内容
	 * @param to  收件人
	 * @return
	 */
	@Override
	public boolean senMsg(String subject, String content, String to) {
		return sendMsg(subject, content, to, null, null, null, null);
	}

	/**
	 * @param subject
	 * @param content
	 * @param to
	 * @param path 文件路径
	 * @param fileName 文件名称
	 * @return
	 */
	@Override
	public boolean senMsg(String subject, String content, String to, String path, String fileName) {
		return sendMsg(subject, content, to, path, fileName, null, null);
	}

	/**
	 * @param subject
	 * @param content
	 * @param to
	 * @param path
	 * @param fileName
	 * @param cc 抄送
	 * @param bcc 密送
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean sendMsg(String subject, String content, String to, String path, String fileName, String cc,
			String bcc) {
		Session session = Session.getInstance(mail.getProperties(), null);
		Message message = new MimeMessage(session);
		try {
			if(mail.getFrom()!= null){
				message.setFrom(new InternetAddress(mail.getFrom()));
			}
			else{
				message.setFrom();
			}//Message.RecipientType.TO邮件接收者
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			if(cc != null){//Message.RecipientType.CC表示抄送人
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
			}
			if(bcc != null){//Message.RecipientType.BCCBCC表示秘密抄送人
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
			}
			message.setSubject(subject);//设置正文
			if(path != null){
			    //html部分
				MimeMultipart mime = new MimeMultipart();
				MimeBodyPart body = new MimeBodyPart();
				body.setContent(content, "text/html;charset=utf-8");
				//附件部分
				BodyPart part = new MimeBodyPart();
				DataSource data = new FileDataSource(path);
				part.setFileName(fileName);
				message.setDataHandler(new DataHandler(data));
				mime.addBodyPart(part);
				message.setContent(mime);
				message.saveChanges();
			}else{
				message.setContent(content, "text/html;charset=utf-8");
			}
			message.setHeader("X-Mailer", Mail.MAILER);
			message.setSentDate(new Date());
			SMTPTransport transport=(SMTPTransport) session.getTransport(mail.PORT);
			if(mail.isAuth()){
				transport.connect(mail.getMailHost(),mail.getMailPort(),mail.getUsername(),mail.getPassword());
			}else{
			    transport.connect();
			}
			transport.sendMessage(message, message.getAllRecipients());
			return true;
		} catch (MessagingException e) {
			LOG.error("邮件发送异常",e);
		}
		return false;
	}

}
