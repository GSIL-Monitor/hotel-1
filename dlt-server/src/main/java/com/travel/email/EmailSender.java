package com.travel.email;

import com.travel.common.dto.EmailDTO;
import com.travel.common.dto.order.request.OrderConfirmDTO;
import com.travel.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import java.util.Date;
import java.util.Properties;

/**
 * @Description : 邮件发送工具类
 * @author : Zhiping Sun
 * @date : 2018年2月28日上午9:43:02
 */
public class EmailSender {

	private static Logger logger = LoggerFactory.getLogger(EmailSender.class);

	/**
	 * 发送html邮件
	 * @param mail
	 * @return
	 */
	public static boolean sendHtmlEmail(EmailDTO mail, OrderConfirmDTO orderConfirm) {
		try {
			Properties props = mail.getProperties();
			//创建密码验证器
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(mail.getUserName(), mail.getPassword());
				}
			};
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session sendMailSession = Session.getInstance(props, auth);
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址,设置邮件消息的发送者
			mailMessage.setFrom(new InternetAddress(mail.getFrom()));
			// 创建邮件的接收者地址，并设置到邮件消息中
			mailMessage.setRecipients(Message.RecipientType.TO,	(Address[]) InternetAddress.parse(mail.getTo()));
			if (StringUtils.isNoneBlank(mail.getCc())) {
				mailMessage.setRecipients(Message.RecipientType.CC,	(Address[]) InternetAddress.parse(mail.getCc()));
			}
			// 设置邮件消息的主题
			mailMessage.setSubject(mail.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mail.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			
			//添加logo
			if (StringUtils.isNotBlank(orderConfirm.getLogoPath())) {
				BodyPart logo = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(orderConfirm.getLogoPath());
				DataHandler dh = new DataHandler(fds);
				logo.setDataHandler(dh);
				logo.setHeader("Content-ID", "logo");
				logo.setFileName(MimeUtility.encodeText(fds.getName()));
				mainPart.addBodyPart(logo);
			}
			
			//添加电子章
			if (StringUtils.isNotBlank(orderConfirm.getSignetPath())) {
				BodyPart signet = new MimeBodyPart();
				FileDataSource fd = new FileDataSource(orderConfirm.getSignetPath());
				DataHandler d = new DataHandler(fd);
				signet.setDataHandler(d);
				signet.setHeader("Content-ID", "signet");
				signet.setFileName(MimeUtility.encodeText(fd.getName()));
				mainPart.addBodyPart(signet);
			}
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (AuthenticationFailedException ex) {
			logger.error("发件人用户名或密码错误");
			throw new ServiceException("发件人用户名或密码错误", ex);
		} catch (Exception ex) {
			logger.error("邮件发送失败", ex);
			throw new ServiceException("邮件发送失败", ex);
		}
	}

	/**
	 * 发送邮件，无html模板
	 * @param mail
	 * @return
	 */
	public static boolean sendEmail(EmailDTO mail) {
		try {
			//创建密码验证器
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(mail.getUserName(), mail.getPassword());
				}
			};
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session sendMailSession = Session.getInstance(mail.getProperties(), auth);
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址,设置邮件消息的发送者
			mailMessage.setFrom(new InternetAddress(mail.getFrom()));
			// 创建邮件的接收者地址，并设置到邮件消息中
			mailMessage.setRecipients(Message.RecipientType.TO,	(Address[]) InternetAddress.parse(mail.getTo()));
			if (StringUtils.isNoneBlank(mail.getCc())) {
				mailMessage.setRecipients(Message.RecipientType.CC,	(Address[]) InternetAddress.parse(mail.getCc()));
			}
			// 设置邮件消息的主题
			mailMessage.setSubject(mail.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置为邮件内容
			mailMessage.setContent(mail.getContent(), "text/html; charset=utf-8");
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (AuthenticationFailedException ex) {
			logger.error("发件人用户名或密码错误");
			throw new ServiceException("发件人用户名或密码错误", ex);
		} catch (Exception ex) {
			logger.error("邮件发送失败", ex);
			throw new ServiceException("邮件发送失败", ex);
		}
	}

	public static void main(String[] args) {
		EmailDTO dto = new EmailDTO();
		dto.setHost("smtp.qq.com");
		dto.setPort("25");
		dto.setUserName("512191117@qq.com");
		dto.setPassword("hua322729juan168");
		dto.setFrom("512191117@qq.com");
		dto.setTo("hua.xu@fangcang.com");
		dto.setSubject("hello ");
		dto.setContent("你好，这是测试邮件，" + new Date());
		EmailSender.sendEmail(dto);
	}
}
