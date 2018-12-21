package com.fangcang.message.email.util;

import com.fangcang.common.util.StringUtil;
import com.fangcang.message.remote.request.email.EmailSendRequestDTO;
import com.itextpdf.text.pdf.BaseFont;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

@Slf4j
public class EmailUtils {

	public static final String HOTMAIL_ADDRESS = "smtp-mail.outlook.com";
	public static final String HOTMAIL = "hotmail";
	public static final String QQ = "@qq.com";

	public static boolean send(EmailSendRequestDTO mail){
		log.info("============ begin EmailUtils.send =================");
	    try{
	    	if(mail==null)throw new RuntimeException("mail is null");
	    	if(!StringUtil.isValidString(mail.getEmailServerHost())
	    		|| !StringUtil.isValidString(mail.getSender())
	    		|| !StringUtil.isValidString(mail.getReceiver()))
	    		throw new RuntimeException("MailServerHost or From or To is undefine");
	    	
	    	// 判断是否需要身份认证
			EmailAuthenticator authenticator = null;
			Properties props = mail.getProperties();

			//加入hotmail的判断
			if((HOTMAIL_ADDRESS.equals(mail.getEmailServerHost()) && mail.getSender().contains(HOTMAIL)) ||  mail.getSender().contains(HOTMAIL)){
				props.put("mail.smtp.starttls.enable","true");
			}else if(mail.getSender().toLowerCase().contains(QQ)){
				log.info("qq邮箱:"+mail.getSender());
				MailSSLSocketFactory sf = new MailSSLSocketFactory();
				sf.setTrustAllHosts(true);
				props.put("mail.smtp.ssl.enable", "true");
				props.put("mail.smtp.ssl.socketFactory", sf);
			}else{
				log.info("邮箱:"+mail.getSender());
				MailSSLSocketFactory sf = new MailSSLSocketFactory();
				sf.setTrustAllHosts(true);
				props.put("mail.smtp.ssl.enable", "true");
				props.put("mail.smtp.ssl.socketFactory", sf);
			}

			// 如果需要身份认证，则创建一个密码验证器
			if(mail.isValidate()){
				authenticator = new EmailAuthenticator(mail.getUserName(), mail.getPassword());
			}
		    // 根据邮件会话属性和密码验证器构造一个发送邮件的session
		    //Session sendMailSession = Session.getDefaultInstance(props,authenticator);
		    Session sendMailSession = Session.getInstance(props, authenticator);
		    
	    	// 根据session创建一个邮件消息
		    Message mailMessage = new MimeMessage(sendMailSession);
		    
		    // 创建邮件发送者地址,设置邮件消息的发送者
		    mailMessage.setFrom(new InternetAddress(mail.getSender()));
		    // 创建邮件的接收者地址，并设置到邮件消息中
		    mailMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(mail.getReceiver()));
		    if(StringUtil.isValidString(mail.getCopyTo())){
		    	mailMessage.setRecipients(Message.RecipientType.CC,(Address[])InternetAddress.parse(mail.getCopyTo()));
		    }
		    
		    // 设置邮件消息的主题
		    mailMessage.setSubject(mail.getSubject());
		    // 设置邮件消息发送的时间
		    mailMessage.setSentDate(new Date());
		    
		    // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		    Multipart mainPart = new MimeMultipart();
		    
		    // 创建一个包含HTML内容的MimeBodyPart
		    BodyPart html = new MimeBodyPart();
		    // 设置HTML内容
		    html.setContent(mail.getContent(), "text/html; charset=utf-8");
		    mainPart.addBodyPart(html);
			//makeStandardtPdfFile(mail, mainPart);
		    
		    //添加附件
		    for(int i=0;i<mail.getAttachFileNameList().size();i++){
		    	MimeBodyPart attachPart = new MimeBodyPart();
			    FileDataSource fileds = new FileDataSource(mail.getAttachFileNameList().get(i));
			    attachPart.setDataHandler(new DataHandler(fileds));
			    attachPart.setFileName(fileds.getName());
			    attachPart.setContentID("LAFN"+i);
			    mainPart.addBodyPart(attachPart);
		    }
		    for(int i=0;i<mail.getAttachFileList().size();i++){
		    	MimeBodyPart attachPart = new MimeBodyPart();
			    FileDataSource fileds = new FileDataSource(mail.getAttachFileList().get(i));
			    attachPart.setDataHandler(new DataHandler(fileds));
			    attachPart.setFileName(fileds.getName());
			    attachPart.setContentID("LAF"+i);
			    mainPart.addBodyPart(attachPart);
		    }
		    
		    for(int i=0;i<mail.getRemoteAttachFileUrlList().size();i++){
		    	log.info("============ remoteFileUrl="+mail.getRemoteAttachFileUrlList().get(i)+"=================");
		    	MimeBodyPart attachPart = new MimeBodyPart();
		    	URLDataSource fileds =new URLDataSource(new URL(mail.getRemoteAttachFileUrlList().get(i)));
			    attachPart.setDataHandler(new DataHandler(fileds));
			    attachPart.setFileName((mail.getRemoteAttachFileNameList()!=null 
			    							&& mail.getRemoteAttachFileNameList().size()>=i
			    							&& mail.getRemoteAttachFileNameList().get(i)!=null)
			    						?mail.getRemoteAttachFileNameList().get(i)
			    						:fileds.getName());
			    attachPart.setContentID("LRAF"+i);
			    mainPart.addBodyPart(attachPart);
		    }
		    
		    // 将MiniMultipart对象设置为邮件内容
		    mailMessage.setContent(mainPart);
		    
		    mailMessage.saveChanges();
		    
		    // 发送邮件
		    Transport.send(mailMessage);
		    
		    log.info("============ end EmailUtils.send =================");
		    return true;
	    }catch(AuthenticationFailedException ex){
	    	String mailMessage="from:"+mail.getSender()+";to:"+mail.getReceiver()
	    	+";server:"+mail.getEmailServerHost()+";port:"+mail.getEmailServerPort()+";userName:"+mail.getUserName()+";password:"+mail.getPassword();
	    	log.error("============ end EmailUtils.send ================用户名或密码不正确"+mailMessage,ex);
	    	throw new RuntimeException("用户名或密码不正确");
	    }catch(Exception ex){
	    	log.error("============ end EmailUtils.send ================发送失败",ex);
	    	throw new RuntimeException("发送失败");
	    }
	}

	private static void makeStandardtPdfFile(EmailSendRequestDTO mail,
											 Multipart mainPart) {
		try {
			OutputStream os = new ByteArrayOutputStream();
			String handleHtml = handleHtml(mail.getContent());

			ITextRenderer renderer = new ITextRenderer();
			String filePath = EmailUtils.class.getResource("/config/font/simsun.ttc").getPath();
//			log.info("handleHtml:" + handleHtml + ",filePath" + filePath);
			renderer.setDocumentFromString(handleHtml);
			//添加宋体
			renderer.getFontResolver().addFont(
					filePath,
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.layout();
			renderer.createPDF(os);
			byte[] byteArray = ((ByteArrayOutputStream) os).toByteArray();
			os.close();
			BodyPart attachPdf = new MimeBodyPart();
			ByteArrayDataSource fds = new ByteArrayDataSource(byteArray,
					"application/pdf");
			attachPdf.setDataHandler(new DataHandler(fds));

			log.info("---------生成的pdf文件名(编码之前):"+mail.getSubject());
			String fileName = MimeUtility.encodeText(mail.getSubject()+ ".pdf");
			attachPdf.setFileName(fileName.replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", ""));
			log.info("---------生成的pdf文件名:"+attachPdf.getFileName());
			mainPart.addBodyPart(attachPdf);
		} catch (Exception e) {
			log.error("生成pdf出错", e);
		}
	}

	/**
	 * 让html成为itext可以的识别的格式
	 * @param content
	 * @return
	 */
	private static String handleHtml(String content) {
		String docType = "<!DOCTYPE html > ";
		String handlContent = new String(content
				.replace("META", "meta")
				.replace("BODY", "body")
				.replace("HTML", "html")
				.replace("&rsquo;", "’")//字符 [’]
				.replace("&ldquo;", "“")//字符[“]
				.replace("&rdquo", "”")//字符[”]
				.replace("&amp;", "&#38;")//字符 [&]
				.replace("&nbsp;", "&#160;")//空格占位符
				.replace("&pound;", "&#163;")//字符[£]
				.replace("&yen;", "&#165;")//字符[¥]
				.replace("&middot;", "&#183;")//字符[·]
				.replace("<td >", "<td>")
				.replace("charset=utf-8\">", "charset=utf-8\"/>")
				.replace("font-family:Tahoma, Geneva, sans-serif;",
						"font-family:SimSun;")
				.replace("&quot;", "\"\"")
				.replace("align=\"right\"", "")
				.replace("gif\">", "gif\"></img>")
				.replace("png\">", "png\"></img>")
				.replace("jpg\">", "jpg\"></img>")
				.replace("<br>", "<br></br>")
				.replace("<col width=\"12%\">",
						"<col width=\"12%\" ></col>")
				.replace("<col width=\"\">",
						"<col width=\"\" ></col>")
				.replace("<col width=\"80\">",
						"<col width=\"80\" ></col>")
				.replace("<col width=\"70\">",
						"<col width=\"70\" ></col>")
				.replace("<col width=\"60\">",
						"<col width=\"60\" ></col>")
				.replace("<col width=\"100\">",
						"<col width=\"100\" ></col>")
				.replace("<col width=\"120\">",
						"<col width=\"120\" ></col>")
				.replace("<col width=\"140\">",
						"<col width=\"140\" ></col>")
				.replace("<col width=\"160\">",
						"<col width=\"160\" ></col>")
				.replace("<col width=\"190\">",
						"<col width=\"190\" ></col>")
				.replace("<col width=\"300\">",
						"<col width=\"300\" ></col>")
				.replace("<col width=\"15%\">",
						"<col width=\"15%\" ></col>")
				.replace("<col width=\"8%\">",
						"<col width=\"8%\" ></col>")
				.replace("<col width=\"6%\">",
						"<col width=\"6%\" ></col>")
				.replace("<col width=\"25%\">",
						"<col width=\"25%\" ></col>")
				.replace("<col width=\"10%\">",
						"<col width=\"10%\" ></col>")
				.replace("<col width=\"7%\">",
						"<col width=\"7%\" ></col>")
				.replace("font-family: \"黑体\";", ""));
		return handlContent;
	}
}
