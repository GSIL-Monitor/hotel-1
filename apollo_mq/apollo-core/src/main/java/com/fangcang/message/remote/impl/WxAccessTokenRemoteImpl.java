package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.WxAccessTokenRemote;
import com.fangcang.message.weixin.service.WxAccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxAccessTokenRemoteImpl implements WxAccessTokenRemote{
	
	@Autowired
	private WxAccessTokenService wxAccessTokenService;

	@Override
	public ResponseDTO resetAccessToken() {
		log.info("..........begin resetAccessToken!............");
		ResponseDTO response = new ResponseDTO();
		try{
			wxAccessTokenService.resetAccessToken();
			response.setResult(ResultCodeEnum.SUCCESS.code);
		}catch (ParameterException pe) {
			log.error("resetAccessToken", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("resetAccessToken", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("resetAccessToken", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end resetAccessToken!............");
		return response;
	}

}
