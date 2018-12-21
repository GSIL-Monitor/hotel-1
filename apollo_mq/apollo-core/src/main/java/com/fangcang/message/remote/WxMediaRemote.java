package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.request.weixin.WxMediaUploadRequestDTO;
import com.fangcang.message.remote.request.weixin.WxMsgImageUploadRequestDTO;
import com.fangcang.message.remote.response.weixin.WxMediaUploadResponseDTO;
import com.fangcang.message.remote.response.weixin.WxMsgImageUploadResponseDTO;

public interface WxMediaRemote {

	/**
	 * 多媒体素材上传
	 * @param request 多媒体素材上传请求对象
	 * @return MediaUploadResponseDTO
	 */
	public ResponseDTO<WxMediaUploadResponseDTO> uploadMedia(WxMediaUploadRequestDTO request);
	
	/**
	 * 图文消息内图片上传
	 * @param request
	 * @return
	 */
	public ResponseDTO<WxMsgImageUploadResponseDTO> uploadMsgImage(WxMsgImageUploadRequestDTO request);
}
