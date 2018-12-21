package com.fangcang.message.weixin.service;

import com.fangcang.message.remote.request.weixin.WxAddTemplateIdRequestDTO;
import com.fangcang.message.remote.request.weixin.templatemsg.WxSendTemplateMsgRequestDTO;
import com.fangcang.message.remote.response.weixin.WxAddTemplateIdResponseDTO;
import com.fangcang.message.remote.response.weixin.WxSendTemplateMsgResponseDTO;

/**
 * 服务号模板消息相关Service
 */
public interface WxTemplateMsgService {
	
	/**
	 * 增加模板ID
	 * @param request	增加模板ID请求类
	 * @return	增加模板ID回复类
	 */
	public WxAddTemplateIdResponseDTO addTemplateId(WxAddTemplateIdRequestDTO request);

	/**
	 * 发送模板消息
	 * @param request	发送模板消息请求类
	 * @return 发送模板消息回复类
	 */
	public WxSendTemplateMsgResponseDTO sendTemplateMessage(WxSendTemplateMsgRequestDTO request);
}
