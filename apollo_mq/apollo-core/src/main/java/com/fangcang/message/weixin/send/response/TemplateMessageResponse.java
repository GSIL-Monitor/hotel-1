package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

/**
 * 发送模板消息返回类
 */
public class TemplateMessageResponse extends BaseResponse implements
		Serializable {

	private static final long serialVersionUID = -7766695765678003753L;

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
