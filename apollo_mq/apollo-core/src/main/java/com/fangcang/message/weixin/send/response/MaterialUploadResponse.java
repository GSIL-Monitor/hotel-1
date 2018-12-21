package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

public class MaterialUploadResponse extends BaseResponse implements Serializable{
	
	private static final long serialVersionUID = 7730278983212906724L;
	
	//媒体文件类型，分别有图片（image）、语音（voice）、视频（video）,普通文件(thumb)
	//private String type;
	//媒体文件上传后获取的唯一标识
	private String media_id;
	//媒体文件上传时间戳
	//private Integer created_at;

//	public Integer getCreated_at() {
//		return created_at;
//	}
//
//	public void setCreated_at(Integer created_at) {
//		this.created_at = created_at;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	
	
}
