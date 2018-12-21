package com.fangcang.message.remote.request.weixin;

import java.io.Serializable;

public class WxSendGroupTextMessageRequestDTO extends WxBaseSendGroupMessageRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 270957954949076741L;
	
	/**
	 * 文本消息内容
	 */
	private String content;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
