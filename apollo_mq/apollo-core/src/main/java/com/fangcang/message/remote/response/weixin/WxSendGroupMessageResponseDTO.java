package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxSendGroupMessageResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 5581438186311513755L;
	
	/**
	 * 消息ID
	 */
	private String msg_id;

	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	
	
}
