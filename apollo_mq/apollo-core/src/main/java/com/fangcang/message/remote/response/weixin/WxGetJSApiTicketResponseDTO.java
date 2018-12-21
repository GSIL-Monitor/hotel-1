package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxGetJSApiTicketResponseDTO implements Serializable {
	
	private static final long serialVersionUID = -3798253418810526164L;
	
	/**
	 * 公众号appId
	 */
	private String appId;
	/**
	 * 随机字符串
	 */
	private String noncestr;
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * url
	 */
	private String url;
	/**
	 * 签名
	 */
	private String signature;
	
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

}
