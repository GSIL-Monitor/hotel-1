package com.fangcang.message.email.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="t_msg_email_record")
public class EmailRecordDO implements Serializable{
	private static final long serialVersionUID = 7264436401398464187L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="emailserverhost")
    private String emailServerHost;
    
	/**
	 * 发送邮件的服务器的端口
	 */
	@Column(name="emailServerPort")
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
	@Column(name="copyTo")
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
	@Column(name="emailType")
	private Integer emailType;

	/**
	 * 邮件发送结果
	 */
	@Column(name="sendResult")
	private boolean sendResult;
	
	/**
	 * 邮件发送时间
	 */
	@Column(name="sendTime")
	private Date sendTime;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
