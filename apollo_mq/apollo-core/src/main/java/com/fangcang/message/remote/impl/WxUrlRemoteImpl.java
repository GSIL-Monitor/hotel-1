package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.WxUrlRemote;
import com.fangcang.message.remote.request.weixin.WxUrlRequestDTO;
import com.fangcang.message.remote.response.weixin.WxUrlResponseDTO;
import com.fangcang.message.weixin.service.WxUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxUrlRemoteImpl implements WxUrlRemote{
	
	@Autowired
	private WxUrlService wxUrlService;

	@Override
	public ResponseDTO<WxUrlResponseDTO> toShortUrl(WxUrlRequestDTO request) {
		log.info("..........begin toShortUrl!............");
		ResponseDTO<WxUrlResponseDTO> response = new ResponseDTO<WxUrlResponseDTO>();
		try{
			WxUrlResponseDTO responseDTO=wxUrlService.toShortUrl(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("toShortUrl", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("toShortUrl", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("toShortUrl", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end toShortUrl!............");
		return response;
	}

}
