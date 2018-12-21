package com.fangcang.message.weixin.send.request;

import com.alibaba.fastjson.JSONObject;

/**
 * 发送模板消息对象
 */
public class SendTemplateMessageRequest {

	/**
	 * 消息接收方的openid
	 */
	private String touser;

	/**
	 * 模板ID
	 */
	private String template_id;

	/**
	 * 点击模板消息跳转到的URL
	 */
	private String url;

	/**
	 * 消息标题颜色
	 */
	private String topcolor;

	/**
	 * 消息内容
	 */
	private JSONObject data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

}
