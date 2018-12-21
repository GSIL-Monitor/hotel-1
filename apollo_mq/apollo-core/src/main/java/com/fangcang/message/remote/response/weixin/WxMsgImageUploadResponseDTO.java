package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxMsgImageUploadResponseDTO implements Serializable {
	
	private static final long serialVersionUID = -2179383186833340459L;
	
	//图片地址
	private String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
