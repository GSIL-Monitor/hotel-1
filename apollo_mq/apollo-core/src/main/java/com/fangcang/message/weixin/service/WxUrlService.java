package com.fangcang.message.weixin.service;

import com.fangcang.message.remote.request.weixin.WxUrlRequestDTO;
import com.fangcang.message.remote.response.weixin.WxUrlResponseDTO;

/**
 * 长链接转换成短链接Service
 *
 */
public interface WxUrlService {

	/**
	 * 长链接转短链接
	 * @param request
	 * @return
	 */
	public WxUrlResponseDTO toShortUrl(WxUrlRequestDTO request);
}
