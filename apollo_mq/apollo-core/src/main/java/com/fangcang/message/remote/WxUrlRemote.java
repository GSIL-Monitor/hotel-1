package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.request.weixin.WxUrlRequestDTO;
import com.fangcang.message.remote.response.weixin.WxUrlResponseDTO;

public interface WxUrlRemote {

	/**
	 * 长链接转短链接
	 * @param request
	 * @return
	 */
	public ResponseDTO<WxUrlResponseDTO> toShortUrl(WxUrlRequestDTO request);
}
