package com.fangcang.message.remote.enums;

/**
 * 模板消息错误编码枚举类
 */
public enum WxTemplateMsgErrorEnum {

	TEMPLATEMSG_SEND_REQ_EMPTY("TEMPLATEMSG_SEND_REQ_EMPTY", "发送模板消息请求参数为空"),
	TEMPLATEMSG_SEND_REQ_PARAM_ERROR("TEMPLATEMSG_SEND_REQ_PARAM_ERROR", "发送模板消息请求参数不正确"),
	TEMPLATEMSG_QUERY_ID_RES_EMPTY("TEMPLATEMSG_QUERY_ID_RES_EMPTY", "查询模板消息ID结果为空"),
	TEMPLATEMSG_QUERY_ID_EXCEPTION("TEMPLATEMSG_QUERY_ID_EXCEPTION", "查询模板消息ID异常"),
	TEMPLATEMSG_SEND_EXCEPTION("TEMPLATEMSG_SEND_EXCEPTION", "发送模板消息异常");

	private String errorCode;
	private String errorMsg;

	private WxTemplateMsgErrorEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public static String getValue(String errorCode) {
		for (WxTemplateMsgErrorEnum s : WxTemplateMsgErrorEnum.values()) {
			if (s.getErrorCode() == errorCode) {
				return s.errorMsg;
			}
		}
		return null;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}


}
