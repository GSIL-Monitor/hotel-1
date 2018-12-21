package com.fangcang.message.remote.request.weixin;

import java.io.Serializable;

public class WxUrlRequestDTO extends WxBaseRequestDTO implements Serializable {

	private static final long serialVersionUID = 4074555245781382373L;

	private String longUrl;

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

}
