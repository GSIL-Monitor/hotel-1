package com.fangcang.message.remote.enums;

public enum WxAuth2ErrorEnum {
	NO_BIND_WEIXIN_SERVICENO("NO_BIND_WEIXIN_SERVICENO","未绑定微信公众号"),
	DEAL_REDIRECTURL_ERROR("DEAL_REDIRECTURL_ERROR","转换重定向地址异常");
	
	private String errorCode;
	private String errorMsg;
	
	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	private WxAuth2ErrorEnum(String errorCode, String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
}
