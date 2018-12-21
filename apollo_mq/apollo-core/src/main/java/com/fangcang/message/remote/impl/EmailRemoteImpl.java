package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.email.service.EmailInfoService;
import com.fangcang.message.remote.EmailRemote;
import com.fangcang.message.remote.request.email.AddEmailConfigDTO;
import com.fangcang.message.remote.request.email.EmailQueryRequestDTO;
import com.fangcang.message.remote.request.email.EmailSendRequestDTO;
import com.fangcang.message.remote.request.email.QueryEmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailInfoDTO;
import com.fangcang.message.remote.response.email.EmailQueryResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmailRemoteImpl implements EmailRemote{
	
	@Autowired
	private EmailInfoService emailInfoService;

	@Override
	public ResponseDTO sendEmail(EmailSendRequestDTO request) {
		log.info("..........begin sendemail!............");
		ResponseDTO response = new ResponseDTO();
		try{
			response = emailInfoService.sendEmail(request);
		}catch (ParameterException pe) {
			log.error("sendemail", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("sendemail", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("sendemail", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end sendemail!............");
		return response;
	}

	@Override
	public ResponseDTO<EmailQueryResponseDTO> queryEmailInfo(EmailQueryRequestDTO request) {
		log.info("..........begin queryEmailInfo!............");
		ResponseDTO<EmailQueryResponseDTO> response = new ResponseDTO<EmailQueryResponseDTO>();
		try{
			List<EmailInfoDTO> emailDTOs = emailInfoService.queryEmailInfos(request);
			EmailQueryResponseDTO emailQueryResponseDTO = new EmailQueryResponseDTO();
			emailQueryResponseDTO.setEmailInfoList(emailDTOs);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(emailQueryResponseDTO);
		}catch (ParameterException pe) {
			log.error("queryEmailInfo", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("queryEmailInfo", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("queryEmailInfo", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end queryEmailInfo!............");
		return response;
	}

	@Override
	public ResponseDTO addEmailConfig(AddEmailConfigDTO requestDTO) {
		log.info("..........begin addEmailConfig!............");
		ResponseDTO response = new ResponseDTO();
		try{
			response = emailInfoService.addEmailConfig(requestDTO);
		}catch (ParameterException pe) {
			log.error("addEmailConfig", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("addEmailConfig", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("addEmailConfig", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end queryEmailInfo!............");
		return response;
	}

	@Override
	public ResponseDTO<List<EmailConfigDTO>> queryEmailConfig(QueryEmailConfigDTO requestDTO) {
		log.info("..........begin queryEmailConfig!............");
		ResponseDTO<List<EmailConfigDTO>> response = new ResponseDTO<>();
		try{
			List<EmailConfigDTO> emailConfigDTOList = emailInfoService.queryEmailConfig(requestDTO);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(emailConfigDTOList);
		}catch (ParameterException pe) {
			log.error("queryEmailConfig", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("queryEmailConfig", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("queryEmailConfig", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end queryEmailConfig!............");
		return response;
	}
}
