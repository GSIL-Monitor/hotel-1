package com.fangcang.message.weixin.service;

import com.fangcang.message.remote.request.weixin.WxMediaUploadRequestDTO;
import com.fangcang.message.remote.request.weixin.WxMsgImageUploadRequestDTO;
import com.fangcang.message.remote.response.weixin.WxMediaUploadResponseDTO;
import com.fangcang.message.remote.response.weixin.WxMsgImageUploadResponseDTO;

/**
 * 服务号多媒体素材远程接口
 */
public interface WxMediaService {

	/**
	 * 多媒体素材上传
	 * @param request 多媒体素材上传请求对象
	 * @return MediaUploadResponseDTO
	 */
	public WxMediaUploadResponseDTO uploadMedia(WxMediaUploadRequestDTO request);
	
	/**
	 * 图文消息内图片上传
	 * @param request
	 * @return
	 */
	public WxMsgImageUploadResponseDTO uploadMsgImage(WxMsgImageUploadRequestDTO request);
	
	
}
