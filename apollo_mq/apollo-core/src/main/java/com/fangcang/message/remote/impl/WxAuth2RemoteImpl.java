package com.fangcang.message.remote.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.WxAuth2Remote;
import com.fangcang.message.remote.request.weixin.WxConfigRequestDTO;
import com.fangcang.message.remote.response.weixin.WxAuth2RedirectResponseDTO;
import com.fangcang.message.remote.response.weixin.WxConfigResponseDTO;
import com.fangcang.message.remote.response.weixin.WxUserInfoResponseDTO;
import com.fangcang.message.remote.response.weixin.WxVisitIdResponseDTO;
import com.fangcang.message.weixin.service.WxAuth2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WxAuth2RemoteImpl implements WxAuth2Remote{

	@Autowired
	private WxAuth2Service wxAuth2Service;

	@Override
	public ResponseDTO<List<WxConfigResponseDTO>> queryWxConfig(){
		log.info("..........begin queryWxConfig!............");
		ResponseDTO<List<WxConfigResponseDTO>> response = new ResponseDTO<List<WxConfigResponseDTO>>();
		try{
			WxConfigRequestDTO requestDTO=new WxConfigRequestDTO();
			List<WxConfigResponseDTO> responseDTO=wxAuth2Service.queryWxConfig(requestDTO);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("queryWxConfig", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("queryWxConfig", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("queryWxConfig", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end queryWxConfig!............");
		return response;
	}

	@Override
	public ResponseDTO<WxAuth2RedirectResponseDTO> auth2Redirect(String callbackUrl) {
		log.info("..........begin auth2Redirect!............");
		ResponseDTO<WxAuth2RedirectResponseDTO> response = new ResponseDTO<WxAuth2RedirectResponseDTO>();
		try{
			WxAuth2RedirectResponseDTO responseDTO=wxAuth2Service.auth2Redirect(callbackUrl);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("auth2Redirect", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("auth2Redirect", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("auth2Redirect", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end auth2Redirect!............");
		return response;
	}

	@Override
	public ResponseDTO<WxVisitIdResponseDTO> getVisitWeixinId(String code) {
		log.info("..........begin getVisitWeixinId!............");
		ResponseDTO<WxVisitIdResponseDTO> response = new ResponseDTO<WxVisitIdResponseDTO>();
		try{
			WxVisitIdResponseDTO responseDTO=wxAuth2Service.getVisitWeixinId(code);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("getVisitWeixinId", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("getVisitWeixinId", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("getVisitWeixinId", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end getVisitWeixinId!............");
		return response;
	}

	@Override
	public ResponseDTO<WxUserInfoResponseDTO> getWeixinUserInfo(String openid) {
		log.info("..........begin getWeixinUserInfo!............,openid" + openid);
		ResponseDTO<WxUserInfoResponseDTO> response = new ResponseDTO<WxUserInfoResponseDTO>();
		try{
			WxUserInfoResponseDTO responseDTO=wxAuth2Service.getWeixinUserInfo(openid);
			response.setResult(ResultCodeEnum.SUCCESS.code);
			response.setModel(responseDTO);
		}catch (ParameterException pe) {
			log.error("getWeixinUserInfo", pe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorNo);
			response.setFailReason(pe.getMessage());
		} catch (ServiceException fe) {
			log.error("getWeixinUserInfo", fe);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailCode(fe.getErrorCode()!=null ? fe.getErrorCode().errorNo:null);
			response.setFailReason(fe.getErrorCode()!=null ? fe.getErrorCode().errorDesc:fe.getMessage());
		} catch (Exception e) {
			log.error("getWeixinUserInfo", e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		log.info("..........end getWeixinUserInfo!............");
		return response;
	}
}
