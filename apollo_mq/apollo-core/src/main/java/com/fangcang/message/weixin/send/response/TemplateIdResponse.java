package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

/**
 * 获取模板ID返回类
 */
public class TemplateIdResponse extends BaseResponse implements
		Serializable {

	private static final long serialVersionUID = 1994859114975904842L;

	/**
	 * 模板ID
	 */
	private String template_id;

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

}
