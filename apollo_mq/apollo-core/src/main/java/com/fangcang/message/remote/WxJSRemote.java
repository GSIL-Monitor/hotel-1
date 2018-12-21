package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.request.weixin.WxGetJSApiTicketRequestDTO;
import com.fangcang.message.remote.response.weixin.WxGetJSApiTicketResponseDTO;

public interface WxJSRemote {
	
	/**
	 * 服务号获取jsapi_ticket接口
	 * @param request
	 * @return BaseResponseDTO
	 */
	public ResponseDTO<WxGetJSApiTicketResponseDTO> getJsApiTicket(WxGetJSApiTicketRequestDTO request);
}
