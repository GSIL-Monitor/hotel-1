package com.fangcang.message.weixin.service.impl;

import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.exception.handle.ExceptionHandle;
import com.fangcang.message.remote.enums.WxAuth2ErrorEnum;
import com.fangcang.message.remote.request.weixin.WxMediaUploadRequestDTO;
import com.fangcang.message.remote.request.weixin.WxMsgImageUploadRequestDTO;
import com.fangcang.message.remote.response.weixin.WxMediaUploadResponseDTO;
import com.fangcang.message.remote.response.weixin.WxMsgImageUploadResponseDTO;
import com.fangcang.message.weixin.cache.IndependServiceNoCache;
import com.fangcang.message.weixin.domain.WxConfigDO;
import com.fangcang.message.weixin.mapper.WxConfigMapper;
import com.fangcang.message.weixin.send.WxMediaCall;
import com.fangcang.message.weixin.send.response.MediaUploadResponse;
import com.fangcang.message.weixin.send.response.MsgImageUploadResponse;
import com.fangcang.message.weixin.service.WxMediaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取jsapi_ticket
 */
@Slf4j
@Service("wxMediaService")
public class WxMediaServiceImpl implements WxMediaService {

	@Autowired
	private WxConfigMapper wxConfigMapper;

	@Override
	public WxMediaUploadResponseDTO uploadMedia(WxMediaUploadRequestDTO request) {
		log.info("begin to ServMediaServiceImpl.uploadMedia send");

		WxMediaUploadResponseDTO responseDTO = new WxMediaUploadResponseDTO();

		if(request==null){
			throw new ParameterException("request不能为空");
		}

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}
		
		try{
			String accessToken = IndependServiceNoCache.getAccessToken(configDOList.get(0).getAppid(), configDOList.get(0).getAppsecret());
			MediaUploadResponse servMediaUploadReturn = WxMediaCall.uploadMedia(accessToken, request.getMediaType(), null, request.getMediaUrl());
			if(servMediaUploadReturn.getErrcode() == null || servMediaUploadReturn.getErrcode()==0){
				responseDTO.setType(servMediaUploadReturn.getType());
				responseDTO.setMedia_id(servMediaUploadReturn.getMedia_id());
				responseDTO.setCreated_at(servMediaUploadReturn.getCreated_at());
			}else{
				throw new ServiceException("多媒体素材上传");
			}
		}catch(Exception e){
			ExceptionHandle.throwServiceException(e,"多媒体素材上传");
		}
		
		log.info("end to ServMediaServiceImpl.uploadMedia send");
		return responseDTO;
	}


	@Override
	public WxMsgImageUploadResponseDTO uploadMsgImage(WxMsgImageUploadRequestDTO request) {
		log.info("begin to ServMediaServiceImpl.uploadMsgImage send");
		
		WxMsgImageUploadResponseDTO responseDTO = new WxMsgImageUploadResponseDTO();

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}

		try{
			String accessToken = IndependServiceNoCache.getAccessToken(configDOList.get(0).getAppid(), configDOList.get(0).getAppsecret());
			
			MsgImageUploadResponse servMsgImageUploadReturn = null;
			if(request.getInputStream() != null){
				servMsgImageUploadReturn = WxMediaCall.uploadMsgImage(accessToken, request.getInputStream(), request.getFileName());
			}else{
				servMsgImageUploadReturn = WxMediaCall.uploadMsgImage(accessToken, request.getFileNetUrl());
			}
			
			if(servMsgImageUploadReturn.getErrcode() == null || servMsgImageUploadReturn.getErrcode()==0){
				responseDTO.setUrl(servMsgImageUploadReturn.getUrl());
			}else{
				throw new ServiceException("图文消息内图片上传");
			}
		}catch(Exception e){
			ExceptionHandle.throwServiceException(e,"图文消息内图片上传");
		}
		
		log.info("end to ServMediaServiceImpl.uploadMsgImage send");
		return responseDTO;
	}
	
}
