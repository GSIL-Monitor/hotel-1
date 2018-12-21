package com.fangcang.message.remote.request.weixin;

import java.io.Serializable;

public class WxAddTemplateIdRequestDTO extends WxBaseRequestDTO implements Serializable {

	private static final long serialVersionUID = -8243178331009895503L;

	/**
	 * 模板编号
	 */
	private String templateCode;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

}
