package com.fangcang.message.weixin.service;

import com.fangcang.message.remote.request.weixin.WxSendGroupMpnewsMessageRequestDTO;
import com.fangcang.message.remote.request.weixin.WxSendGroupTextMessageRequestDTO;
import com.fangcang.message.remote.response.weixin.WxSendGroupMessageResponseDTO;

/**
 * 服务号群发消息
 */
public interface WxSendGroupMessageService {
	
	/**
	 * 发送MPNEWS图文消息
	 * @param request
	 */
	public WxSendGroupMessageResponseDTO sendMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request);
	
	/**
	 * 发送text文本消息
	 * @param request
	 */
	public WxSendGroupMessageResponseDTO sendTextMessage(WxSendGroupTextMessageRequestDTO request);
	
	/**
	 * 预览MPNEWS图文消息
	 * @param request
	 */
	public WxSendGroupMessageResponseDTO previewMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request);
	
	
}
