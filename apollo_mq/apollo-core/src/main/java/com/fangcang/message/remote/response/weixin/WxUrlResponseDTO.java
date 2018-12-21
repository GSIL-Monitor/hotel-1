package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxUrlResponseDTO implements Serializable {

	private static final long serialVersionUID = 6115855804275251456L;

	private String shortUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

}
