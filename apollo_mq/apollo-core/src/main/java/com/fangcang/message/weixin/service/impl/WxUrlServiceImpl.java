package com.fangcang.message.weixin.service.impl;

import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.exception.handle.ExceptionHandle;
import com.fangcang.message.remote.enums.WxAuth2ErrorEnum;
import com.fangcang.message.remote.request.weixin.WxUrlRequestDTO;
import com.fangcang.message.remote.response.weixin.WxUrlResponseDTO;
import com.fangcang.message.weixin.cache.IndependServiceNoCache;
import com.fangcang.message.weixin.domain.WxConfigDO;
import com.fangcang.message.weixin.mapper.WxConfigMapper;
import com.fangcang.message.weixin.send.WxShortUrlCall;
import com.fangcang.message.weixin.send.response.ShortUrlResponse;
import com.fangcang.message.weixin.service.WxUrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 长链接转短链接Service实现类
 */
@Slf4j
@Service("wxUrlService")
public class WxUrlServiceImpl implements WxUrlService {

	@Autowired
	private WxConfigMapper wxConfigMapper;

	@Override
	public WxUrlResponseDTO toShortUrl(WxUrlRequestDTO request) {
		log.info("************ begin ServUrlServiceImpl.toShortUrl ************\n" + request);
		WxUrlResponseDTO responseDTO = new WxUrlResponseDTO();

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}
		String appId=configDOList.get(0).getAppid();
		String appSecret=configDOList.get(0).getAppsecret();
		
		String accessToken = IndependServiceNoCache.getAccessToken(appId, appSecret);
		
		try {
			ShortUrlResponse servShortUrlReturn = WxShortUrlCall.toShortUrl(accessToken, "long2short", request.getLongUrl());
			if (servShortUrlReturn != null) {
				String shortUrl = servShortUrlReturn.getShort_url();
				if (StringUtils.isNotBlank(shortUrl)) {
					responseDTO.setShortUrl(shortUrl);
				} else {
					log.info("************ end ServUrlServiceImpl.toShortUrl ************\n" + responseDTO);
					throw new ServiceException(servShortUrlReturn.getErrmsg());
				}
			}
		} catch (Exception e) {
			ExceptionHandle.throwServiceException(e,e.getMessage());
		}
		log.info("************ end ServUrlServiceImpl.toShortUrl ************\n" + responseDTO);
		return responseDTO;
	}

}
