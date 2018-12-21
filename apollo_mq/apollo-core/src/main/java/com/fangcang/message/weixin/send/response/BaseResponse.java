package com.fangcang.message.weixin.send.response;

import com.fangcang.message.weixin.util.ErrorUtils;

/**
 * 主动调用微信响应信息
 * 
 */
public class BaseResponse {
	
	//错误码
	private Integer errcode;
	//错误信息
	private String errmsg;
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		if(this.errcode != null){
			String msg = ErrorUtils.getWeixinErrorMessage(this.errcode);
			if(msg != null){
				this.errmsg = msg;
			}
		}
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		if(this.errcode != null){
			String msg = ErrorUtils.getWeixinErrorMessage(this.errcode);
			if(msg != null){
				this.errmsg = msg;
				return;
			}
		}
		this.errmsg = errmsg;
	}
	
	
	
}
