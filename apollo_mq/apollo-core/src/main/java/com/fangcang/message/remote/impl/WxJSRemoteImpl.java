package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.WxJSRemote;
import com.fangcang.message.remote.request.weixin.WxGetJSApiTicketRequestDTO;
import com.fangcang.message.remote.response.weixin.WxGetJSApiTicketResponseDTO;
import com.fangcang.message.weixin.service.WxJSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxJSRemoteImpl implements WxJSRemote{

	@Autowired
	private WxJSService wxJSService;

	@Override
	public ResponseDTO<WxGetJSApiTicketResponseDTO> getJsApiTicket(WxGetJSApiTicketRequestDTO request) {
		log.info("..........begin getJsApiTicket!............");
		ResponseDTO<WxGetJSApiTicketResponseDTO> response = new ResponseDTO<WxGetJSApiTicketResponseDTO>();
		try{
			WxGetJSApiTicketResponseDTO responseDTO=wxJSService.getJsApiTicket(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("getJsApiTicket", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("getJsApiTicket", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("getJsApiTicket", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end getJsApiTicket!............");
		return response;
	}
}
