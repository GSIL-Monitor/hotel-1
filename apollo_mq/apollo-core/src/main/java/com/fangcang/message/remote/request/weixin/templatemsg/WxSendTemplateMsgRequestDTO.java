package com.fangcang.message.remote.request.weixin.templatemsg;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.remote.enums.WxTemplateMsgConstants;
import com.fangcang.message.remote.request.weixin.WxBaseRequestDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WxSendTemplateMsgRequestDTO extends WxBaseRequestDTO implements
		Serializable {

	private static final long serialVersionUID = -7377778241273492209L;
	
	/**
	 * 调用方应用名
	 */
	@NotNull
	private String clientAppName;

	/**
	 * 商家编码
	 */
	private String merchantCode;

	/**
	 * 消息接收方的openid
	 */
	@NotNull
	private String toUser;

	/**
	 * 点击模板消息跳转到的URL
	 */
	private String url;
	
	/**
	 * 消息标题
	 */
	@NotNull
	private String title;
	
	/**
	 * 消息结尾备注 <br>
	 * \n 代表换行
	 */
	private String remark;
	
	/**
	 * 消息标题 字体颜色
	 */
	private String titleColor = WxTemplateMsgConstants.DEFAULT_COLOR;
	/**
	 * 消息结尾备注 字体颜色
	 */
	private String remarkColor = WxTemplateMsgConstants.DEFAULT_COLOR;
	
	/**
	 * 消息模版编码
	 */
	private String templateCode;
	

	public String getClientAppName() {
		return clientAppName;
	}

	public void setClientAppName(String clientAppName) {
		this.clientAppName = clientAppName;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getRemarkColor() {
		return remarkColor;
	}

	public void setRemarkColor(String remarkColor) {
		this.remarkColor = remarkColor;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	protected void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public JSONObject getData() {
		return null;
	}

}
