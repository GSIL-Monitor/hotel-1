package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

public class AccessTokenResponse extends BaseResponse implements Serializable{
	
	private static final long serialVersionUID = -7450041375521898036L;
	
	//获取到的凭证
	private String access_token;
	//失效时间（单位：秒，  在expires_in秒内access_token有效，逾期失效）
	private Integer expires_in;
	
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
}
