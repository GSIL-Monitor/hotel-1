package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxVisitIdResponseDTO implements Serializable {
	
	private static final long serialVersionUID = -8202244022509916665L;
	
	/**
	 * 微信用户openid
	 */
	private String openid;
	/**
	 * 微信用户unionid
	 */
	private String unionid;
	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}
