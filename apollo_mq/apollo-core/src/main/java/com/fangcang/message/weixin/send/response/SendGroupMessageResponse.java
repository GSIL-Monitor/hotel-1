package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

public class SendGroupMessageResponse extends BaseResponse implements Serializable{
	
	private static final long serialVersionUID = 2108016299745620997L;
	
	//发送出去的消息ID
	private String msg_id;

	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	
}
