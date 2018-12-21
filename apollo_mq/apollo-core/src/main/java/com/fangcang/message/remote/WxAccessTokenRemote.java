package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;

public interface WxAccessTokenRemote {
	
	/**
	 * 重置AccessToken缓存
	 */
	public ResponseDTO resetAccessToken();
}
