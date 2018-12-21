package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.request.weixin.WxAddTemplateIdRequestDTO;
import com.fangcang.message.remote.request.weixin.templatemsg.WxSendTemplateMsgRequestDTO;
import com.fangcang.message.remote.response.weixin.WxAddTemplateIdResponseDTO;
import com.fangcang.message.remote.response.weixin.WxSendTemplateMsgResponseDTO;

public interface WxTemplateMsgRemote {
	
	/**
	 * 增加模板ID
	 *
	 * @param request	增加模板ID请求类
	 * @return	增加模板ID回复类
	 */
	public ResponseDTO<WxAddTemplateIdResponseDTO> addTemplateId(WxAddTemplateIdRequestDTO request);

	/**
	 * 发送模板消息(给指定微信用户)
	 *
	 * @param request	发送模板消息请求类
	 * @return 发送模板消息回复类
	 */
	public ResponseDTO<WxSendTemplateMsgResponseDTO> sendTemplateMessage(WxSendTemplateMsgRequestDTO request);
	
}
