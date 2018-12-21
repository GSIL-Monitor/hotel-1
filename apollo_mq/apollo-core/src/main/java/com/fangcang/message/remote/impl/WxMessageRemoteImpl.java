package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.WxMessageRemote;
import com.fangcang.message.remote.request.weixin.WxSendGroupMpnewsMessageRequestDTO;
import com.fangcang.message.remote.request.weixin.WxSendGroupTextMessageRequestDTO;
import com.fangcang.message.remote.response.weixin.WxSendGroupMessageResponseDTO;
import com.fangcang.message.weixin.service.WxSendGroupMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WxMessageRemoteImpl implements WxMessageRemote{

	@Autowired
	private WxSendGroupMessageService wxSendGroupMessageService;

	@Override
	public ResponseDTO<WxSendGroupMessageResponseDTO> sendMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request) {
		log.info("..........begin sendMpnewsMessage!............");
		ResponseDTO<WxSendGroupMessageResponseDTO> response = new ResponseDTO<WxSendGroupMessageResponseDTO>();
		try{
			WxSendGroupMessageResponseDTO responseDTO=wxSendGroupMessageService.sendMpnewsMessage(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("sendMpnewsMessage", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("sendMpnewsMessage", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("sendMpnewsMessage", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end sendMpnewsMessage!............");
		return response;
	}

	@Override
	public ResponseDTO<WxSendGroupMessageResponseDTO> sendTextMessage(WxSendGroupTextMessageRequestDTO request) {
		log.info("..........begin sendTextMessage!............");
		ResponseDTO<WxSendGroupMessageResponseDTO> response = new ResponseDTO<WxSendGroupMessageResponseDTO>();
		try{
			WxSendGroupMessageResponseDTO responseDTO=wxSendGroupMessageService.sendTextMessage(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("sendTextMessage", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("sendTextMessage", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("sendTextMessage", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end sendTextMessage!............");
		return response;
	}

	@Override
	public ResponseDTO<WxSendGroupMessageResponseDTO> previewMpnewsMessage(WxSendGroupMpnewsMessageRequestDTO request) {
		log.info("..........begin previewMpnewsMessage!............");
		ResponseDTO<WxSendGroupMessageResponseDTO> response = new ResponseDTO<WxSendGroupMessageResponseDTO>();
		try{
			WxSendGroupMessageResponseDTO responseDTO=wxSendGroupMessageService.previewMpnewsMessage(request);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("previewMpnewsMessage", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("previewMpnewsMessage", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("previewMpnewsMessage", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end previewMpnewsMessage!............");
		return response;
	}

}
