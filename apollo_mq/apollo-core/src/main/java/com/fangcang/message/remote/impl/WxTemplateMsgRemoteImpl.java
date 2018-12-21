package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.WxTemplateMsgRemote;
import com.fangcang.message.remote.request.weixin.WxAddTemplateIdRequestDTO;
import com.fangcang.message.remote.request.weixin.templatemsg.WxSendTemplateMsgRequestDTO;
import com.fangcang.message.remote.response.weixin.WxAddTemplateIdResponseDTO;
import com.fangcang.message.remote.response.weixin.WxSendTemplateMsgResponseDTO;
import com.fangcang.message.weixin.service.WxTemplateMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxTemplateMsgRemoteImpl implements WxTemplateMsgRemote{
	
	@Autowired
	private WxTemplateMsgService wxTemplateMsgService;

	@Override
	public ResponseDTO<WxAddTemplateIdResponseDTO> addTemplateId(WxAddTemplateIdRequestDTO request) {
		log.info("..........begin addTemplateId!............");
		ResponseDTO<WxAddTemplateIdResponseDTO> response = new ResponseDTO<WxAddTemplateIdResponseDTO>();
		try{
			WxAddTemplateIdResponseDTO responseDTO=wxTemplateMsgService.addTemplateId(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("addTemplateId", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("addTemplateId", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("addTemplateId", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end addTemplateId!............");
		return response;
	}

	@Override
	public ResponseDTO<WxSendTemplateMsgResponseDTO> sendTemplateMessage(WxSendTemplateMsgRequestDTO request) {
		log.info("..........begin sendTemplateMessage!............");
		ResponseDTO<WxSendTemplateMsgResponseDTO> response = new ResponseDTO<WxSendTemplateMsgResponseDTO>();
		try{
			WxSendTemplateMsgResponseDTO responseDTO=wxTemplateMsgService.sendTemplateMessage(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("sendTemplateMessage", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("sendTemplateMessage", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("sendTemplateMessage", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end sendTemplateMessage!............");
		return response;
	}
	

}
