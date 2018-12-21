package com.fangcang.message.remote.request.weixin;

import java.io.InputStream;
import java.io.Serializable;

public class WxMsgImageUploadRequestDTO extends WxBaseRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 4575945939311247847L;
	
	/**
	 * 图片地址
	 */
	private String fileNetUrl;
	/**
	 * 图片流
	 */
	private InputStream inputStream;
	/**
	 * 图片名
	 */
	private String fileName;
	
	public String getFileNetUrl() {
		return fileNetUrl;
	}
	public void setFileNetUrl(String fileNetUrl) {
		this.fileNetUrl = fileNetUrl;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
