package com.fangcang.message.remote.request.weixin;

import java.io.Serializable;

public class WxGetJSApiTicketRequestDTO extends WxBaseRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 775604606913678288L;
	
	/**
	 * url
	 */
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
