package com.fangcang.message.weixin.service;

import com.fangcang.message.remote.request.weixin.WxGetJSApiTicketRequestDTO;
import com.fangcang.message.remote.response.weixin.WxGetJSApiTicketResponseDTO;

/**
 * 服务号JS相关服务接口
 */
public interface WxJSService {

	/**
	 * 服务号获取jsapi_ticket接口
	 * @param request
	 * @return BaseResponseDTO
	 */
	public WxGetJSApiTicketResponseDTO getJsApiTicket(WxGetJSApiTicketRequestDTO request);
}
