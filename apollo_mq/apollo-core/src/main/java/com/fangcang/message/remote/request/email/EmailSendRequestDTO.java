package com.fangcang.message.remote.request.email;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailSendRequestDTO implements Serializable{

	private static final long serialVersionUID = 443457317342727867L;

	/**
	 * 发送邮件的服务器的IP
	 */
    private String emailServerHost;
    
	/**
	 * 发送邮件的服务器的端口
	 */
    private String emailServerPort;

	/**
	 * 邮件发件者的地址
	 */
	private String sender;

	/**
	 * 邮件接收者的地址
	 */
	private String receiver;
	
	/**
	 * 抄送者的地址,以";"隔开
	 */
	private String copyTo;
	
	/**
	 *  邮件主题
	 */
	private String subject;
	
	/**
	 *  邮件的文本内容
	 */
	private String content;
	
	/**
	 *  邮件本地附件名称列表
	 */
	private List<String> attachFileNameList;
	
	/**
	 * 邮件本地附件列表
	 */
	private List<File> attachFileList;
	
	/**
	 *  邮件远程附件名称列表
	 */
	private List<String> remoteAttachFileNameList;
	
	/**
	 * 邮件本地附件列表
	 */
	private List<String> remoteAttachFileUrlList;
	
	/**
	 * 是否需要身份验证    
	 */
    private boolean validate = true;
    
    /**
     * 登陆邮件发送服务器的用户名
     */
	private String userName;
	
	/**
	 * 登陆邮件发送服务器的密码
	 */
	private String password;
	
	/**
     * 获得邮件会话属性
     */
	public Properties getProperties(){
		Properties p = new Properties();
		p.put("mail.smtp.host", this.emailServerHost);
		p.put("mail.smtp.port", this.emailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
    }

	public String getEmailServerHost() {
		return emailServerHost;
	}

	public void setEmailServerHost(String emailServerHost) {
		this.emailServerHost = emailServerHost;
	}

	public String getEmailServerPort() {
		return emailServerPort;
	}

	public void setEmailServerPort(String emailServerPort) {
		this.emailServerPort = emailServerPort;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getAttachFileNameList() {
		if(attachFileNameList==null){
			attachFileNameList=new ArrayList<String>();
		}
		return attachFileNameList;
	}

	public void setAttachFileNameList(List<String> attachFileNameList) {
		this.attachFileNameList = attachFileNameList;
	}

	public List<File> getAttachFileList() {
		if(attachFileList==null){
			attachFileList=new ArrayList<File>();
		}
		return attachFileList;
	}

	public void setAttachFileList(List<File> attachFileList) {
		this.attachFileList = attachFileList;
	}

	public List<String> getRemoteAttachFileNameList() {
		if(remoteAttachFileNameList==null){
			remoteAttachFileNameList=new ArrayList<String>();
		}
		return remoteAttachFileNameList;
	}

	public void setRemoteAttachFileNameList(List<String> remoteAttachFileNameList) {
		this.remoteAttachFileNameList = remoteAttachFileNameList;
	}

	public List<String> getRemoteAttachFileUrlList() {
		if(remoteAttachFileUrlList==null){
			remoteAttachFileUrlList=new ArrayList<String>();
		}
		return remoteAttachFileUrlList;
	}

	public void setRemoteAttachFileUrlList(List<String> remoteAttachFileUrlList) {
		this.remoteAttachFileUrlList = remoteAttachFileUrlList;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
