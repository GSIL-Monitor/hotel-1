package com.fangcang.message.remote.request.weixin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class WxBaseRequestDTO implements Serializable {

	private static final long serialVersionUID = 8123111678034060167L;

	/**
	 * 请求时间
	 */
	private Date requestTime;

	public Date getRequestTime() {
		if(null == requestTime){
			return new Date();
		}
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
