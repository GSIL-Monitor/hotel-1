package com.fangcang.message.remote.response.email;

import java.io.Serializable;
import java.util.Date;

public class EmailInfoDTO implements Serializable{

	private static final long serialVersionUID = -4101555560289630522L;

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
	 * 邮件消息类型
	 */
	private Integer emailType;

	/**
	 * 邮件发送结果
	 */
	private boolean sendResult;
	
	/**
	 * 邮件发送时间
	 */
	private Date sendTime;

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

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public boolean isSendResult() {
		return sendResult;
	}

	public void setSendResult(boolean sendResult) {
		this.sendResult = sendResult;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
