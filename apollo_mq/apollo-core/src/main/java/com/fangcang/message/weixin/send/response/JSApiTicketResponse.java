package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

public class JSApiTicketResponse extends BaseResponse implements Serializable{
	
	private static final long serialVersionUID = -6345841133930139073L;
	
	//获取到的凭证
	private String ticket;
	//失效时间（单位：秒，  在expires_in秒内access_token有效，逾期失效）
	private Integer expires_in;
	
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
}
