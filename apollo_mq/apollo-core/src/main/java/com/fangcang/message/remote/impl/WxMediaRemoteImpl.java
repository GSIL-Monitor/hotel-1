package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.WxMediaRemote;
import com.fangcang.message.remote.request.weixin.WxMediaUploadRequestDTO;
import com.fangcang.message.remote.request.weixin.WxMsgImageUploadRequestDTO;
import com.fangcang.message.remote.response.weixin.WxMediaUploadResponseDTO;
import com.fangcang.message.remote.response.weixin.WxMsgImageUploadResponseDTO;
import com.fangcang.message.weixin.service.WxMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxMediaRemoteImpl implements WxMediaRemote{

	@Autowired
	private WxMediaService wxMediaService;

	@Override
	public ResponseDTO<WxMediaUploadResponseDTO> uploadMedia(WxMediaUploadRequestDTO request) {
		log.info("..........begin uploadMedia!............");
		ResponseDTO<WxMediaUploadResponseDTO> response = new ResponseDTO<WxMediaUploadResponseDTO>();
		try{
			WxMediaUploadResponseDTO responseDTO=wxMediaService.uploadMedia(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("uploadMedia", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("uploadMedia", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("uploadMedia", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end uploadMedia!............");
		return response;
	}

	@Override
	public ResponseDTO<WxMsgImageUploadResponseDTO> uploadMsgImage(WxMsgImageUploadRequestDTO request) {
		log.info("..........begin uploadMedia!............");
		ResponseDTO<WxMsgImageUploadResponseDTO> response = new ResponseDTO<WxMsgImageUploadResponseDTO>();
		try{
			WxMsgImageUploadResponseDTO responseDTO=wxMediaService.uploadMsgImage(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("uploadMedia", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("uploadMedia", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("uploadMedia", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end uploadMedia!............");
		return response;
	}

}
