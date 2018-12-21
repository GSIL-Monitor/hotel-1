package com.fangcang.message.weixin.service.impl;

import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.exception.handle.ExceptionHandle;
import com.fangcang.message.remote.enums.WxAuth2ErrorEnum;
import com.fangcang.message.remote.enums.WxTemplateMsgErrorEnum;
import com.fangcang.message.remote.request.weixin.WxAddTemplateIdRequestDTO;
import com.fangcang.message.remote.request.weixin.templatemsg.WxSendTemplateMsgRequestDTO;
import com.fangcang.message.remote.response.weixin.WxAddTemplateIdResponseDTO;
import com.fangcang.message.remote.response.weixin.WxSendTemplateMsgResponseDTO;
import com.fangcang.message.weixin.cache.IndependServiceNoCache;
import com.fangcang.message.weixin.domain.WxConfigDO;
import com.fangcang.message.weixin.domain.WxMsgTemplateDO;
import com.fangcang.message.weixin.dto.WxTemplateMsgInfoDTO;
import com.fangcang.message.weixin.enums.WxErrorConstant;
import com.fangcang.message.weixin.mapper.WxConfigMapper;
import com.fangcang.message.weixin.mapper.WxMsgTemplateMapper;
import com.fangcang.message.weixin.send.WxTemplateMessageCall;
import com.fangcang.message.weixin.send.response.TemplateIdResponse;
import com.fangcang.message.weixin.send.response.TemplateMessageResponse;
import com.fangcang.message.weixin.service.WxTemplateMsgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务号模板消息相关Service实现类
 */
@Slf4j
@Service("wxTemplateMsgService")
public class WxTemplateMsgServiceImpl implements WxTemplateMsgService {

	@Autowired
	private WxConfigMapper wxConfigMapper;
	@Autowired
	private WxMsgTemplateMapper wxMsgTemplateMapper;
	
	@Override
	public WxAddTemplateIdResponseDTO addTemplateId(WxAddTemplateIdRequestDTO request) {
		log.info("************ begin ServTemplateMsgServiceImpl.addTemplateId ************\n" + request);
		WxAddTemplateIdResponseDTO response = new WxAddTemplateIdResponseDTO();

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}
		String appId=configDOList.get(0).getAppid();
		String appSecret=configDOList.get(0).getAppsecret();

		String accessToken = IndependServiceNoCache.getAccessToken(appId, appSecret);
		try {
			TemplateIdResponse templateIdReturn = WxTemplateMessageCall.getTemplateId(accessToken, request.getTemplateCode());
			if(null != templateIdReturn){
				
				log.info("get message template response :" + templateIdReturn.getErrcode() + ",errorMsg:" + templateIdReturn.getErrmsg() + ",templateId:" + templateIdReturn.getTemplate_id());
				
				String templateId = templateIdReturn.getTemplate_id();
				if (StringUtils.isNotBlank(templateId)) {
					response.setTemplateId(templateId);
				} else {
					log.info("************ end ServTemplateMsgServiceImpl.addTemplateId ************\n" + response);
					throw new ServiceException(templateIdReturn.getErrmsg());
				}
			} else {
				log.info("************ end ServTemplateMsgServiceImpl.addTemplateId ************\n" + response);
				throw new ServiceException(templateIdReturn.getErrmsg());
			}

		} catch (Exception e) {
			log.error("************ end ServTemplateMsgServiceImpl.addTemplateId ************\n" + response, e);
			ExceptionHandle.throwServiceException(e,e.getMessage());
		}
		log.info("************ end ServTemplateMsgServiceImpl.addTemplateId ************\n" + response);
		return response;
	}
	
	@Override
	public WxSendTemplateMsgResponseDTO sendTemplateMessage(WxSendTemplateMsgRequestDTO request) {
		log.info("************ begin ServTemplateMsgServiceImpl.sendTemplateMessage ************\n" + request);
		WxSendTemplateMsgResponseDTO response = new WxSendTemplateMsgResponseDTO();

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}
		String appId=configDOList.get(0).getAppid();
		String appSecret=configDOList.get(0).getAppsecret();

		String accessToken = IndependServiceNoCache.getAccessToken(appId, appSecret);

		WxTemplateMsgInfoDTO msgInfoDTO = new WxTemplateMsgInfoDTO();
		WxMsgTemplateDO param=new WxMsgTemplateDO();
		param.setTemplateCode(request.getTemplateCode());
		WxMsgTemplateDO msgTemplateResponseDTO=wxMsgTemplateMapper.selectOne(param);
		if(msgTemplateResponseDTO!=null && StringUtils.isNotBlank(msgTemplateResponseDTO.getWxTemplateId())) {
			msgInfoDTO.setToUser(request.getToUser());
			msgInfoDTO.setTemplateId(msgTemplateResponseDTO.getWxTemplateId());
			msgInfoDTO.setUrl(request.getUrl());
			msgInfoDTO.setData(request.getData());
		}else{
			throw new ServiceException(WxTemplateMsgErrorEnum.TEMPLATEMSG_QUERY_ID_RES_EMPTY.getErrorMsg());
		}

		try {
			
			TemplateMessageResponse servTemplateMessageReturn = WxTemplateMessageCall.sendTemplateMessage(accessToken, msgInfoDTO.getToUser(), msgInfoDTO.getTemplateId(),
					request.getUrl(), null, request.getData());
			if (WxErrorConstant.SUCCESS == servTemplateMessageReturn.getErrcode()) {
				response.setMsgid(servTemplateMessageReturn.getMsgid());
			} else {
				log.info("************ end ServTemplateMsgServiceImpl.sendTemplateMessage ************\n" + response);
				throw new ServiceException(servTemplateMessageReturn.getErrmsg());
			}
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		log.info("************ end ServTemplateMsgServiceImpl.sendTemplateMessage ************\n" + response);
		return response;
	}

}
