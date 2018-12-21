package com.fangcang.message.remote.request.weixin;

import java.io.Serializable;

public class WxMediaUploadRequestDTO extends WxBaseRequestDTO implements Serializable{

	private static final long serialVersionUID = 247049065789626671L;
	
	/**
	 * 多媒体类型<br>
	 * 参见ServCommonEnumConstant.MediaTypes<br>
	 * 示例：image或voice
	 */
	private String mediaType;
	/**
	 * 多媒体地址
	 */
	private String mediaUrl;
	
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaUrl() {
		return mediaUrl;
	}
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	
	
}
