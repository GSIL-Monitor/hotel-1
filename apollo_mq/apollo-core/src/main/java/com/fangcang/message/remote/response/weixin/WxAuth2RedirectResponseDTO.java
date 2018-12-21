package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxAuth2RedirectResponseDTO implements Serializable {
	private static final long serialVersionUID = 8139988405345415680L;
	
	/**
	 * 微信重定向地址
	 */
	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
