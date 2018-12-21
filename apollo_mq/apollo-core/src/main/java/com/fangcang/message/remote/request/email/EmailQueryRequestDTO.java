package com.fangcang.message.remote.request.email;

import java.io.Serializable;
import java.util.Date;

public class EmailQueryRequestDTO implements Serializable{

	private static final long serialVersionUID = -1304070333139100766L;	
	
	/**
	 * 邮件发送者的地址
	 */
	private String from;
	
	/**
	 * 邮件接收者的地址
	 */
	private String to;
	
	/**
	 * 邮件接收者的地址数组(由to自动转换)
	 */
	private String[] toArray;
	
	/**
	 * 邮件类型
	 */
	private Integer emailType;
	
	/**
	 * 发送结果
	 */
	private Boolean sendResult;
	
	/**
	 * 查询开始时间
	 */
	private Date beginTime;
	
	/**
	 * 查询结束时间
	 */
	private Date endTime;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
		if(this.to != null){
			this.toArray = this.to.split(";");
		}
	}

	public String[] getToArray() {
		return toArray;
	}

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean isSendResult() {
		return sendResult;
	}

	public void setSendResult(Boolean sendResult) {
		this.sendResult = sendResult;
	}
	
}
