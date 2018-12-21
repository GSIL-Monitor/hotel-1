package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

public class MsgImageUploadResponse extends BaseResponse implements Serializable{
	
	private static final long serialVersionUID = -1025102693403026263L;
	
	//媒体文件地址
	private String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}
