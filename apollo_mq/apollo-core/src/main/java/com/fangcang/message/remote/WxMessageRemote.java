package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.request.weixin.WxSendGroupMpnewsMessageRequestDTO;
import com.fangcang.message.remote.request.weixin.WxSendGroupTextMessageRequestDTO;
import com.fangcang.message.remote.response.weixin.WxSendGroupMessageResponseDTO;

public interface WxMessageRemote {
	
	/**
	 * 服务号发送MPNEWS图文消息
	 * @param request 发送消息请求接口
	 * @return BaseResponseDTO
	 */
	public ResponseDTO<WxSendGroupMessageResponseDTO> sendMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request) ;
	
	/**
	 * 服务号发送TEXT文本消息
	 * @param request 发送消息请求接口
	 * @return BaseResponseDTO
	 */
	public ResponseDTO<WxSendGroupMessageResponseDTO> sendTextMessage(WxSendGroupTextMessageRequestDTO request);
	
	/**
	 * 服务号预览MPNEWS图文消息
	 * @param request 发送消息请求接口
	 * @return BaseResponseDTO
	 */
	public ResponseDTO<WxSendGroupMessageResponseDTO> previewMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request);
	
	
	
}
