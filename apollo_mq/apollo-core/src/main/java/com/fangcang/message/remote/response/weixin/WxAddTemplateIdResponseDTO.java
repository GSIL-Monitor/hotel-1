package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxAddTemplateIdResponseDTO implements Serializable {
	private static final long serialVersionUID = 5594650245204646522L;

	/**
	 * 模板ID
	 */
	private String templateId;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
