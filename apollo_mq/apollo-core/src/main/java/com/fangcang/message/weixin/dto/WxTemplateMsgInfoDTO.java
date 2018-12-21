package com.fangcang.message.weixin.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 发送模板消息实际请求类
 */
public class WxTemplateMsgInfoDTO implements Serializable {
	private static final long serialVersionUID = -5229990633374900304L;

	/**
	 * 消息接收方的openid
	 */
	private String toUser;

	/**
	 * 模板ID
	 */
	private String templateId;

	/**
	 * 点击模板消息跳转到的URL
	 */
	private String url;

	/**
	 * 消息内容
	 */
	private JSONObject data;

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

}
