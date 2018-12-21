package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

/**
 * 长链接转短链接返回类
 */
public class ShortUrlResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 6809215138974501241L;

	private String short_url;

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

}
