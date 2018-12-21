package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxSendTemplateMsgResponseDTO implements Serializable {

	private static final long serialVersionUID = 5594650245204646522L;

	/**
	 * 消息ID
	 */
	private String msgid;

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

}
