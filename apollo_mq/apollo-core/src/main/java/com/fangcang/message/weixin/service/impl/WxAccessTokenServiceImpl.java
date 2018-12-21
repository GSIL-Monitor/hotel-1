package com.fangcang.message.weixin.service.impl;

import com.fangcang.common.exception.ServiceException;
import com.fangcang.message.remote.enums.WxAuth2ErrorEnum;
import com.fangcang.message.weixin.cache.IndependServiceNoCache;
import com.fangcang.message.weixin.domain.WxConfigDO;
import com.fangcang.message.weixin.mapper.WxConfigMapper;
import com.fangcang.message.weixin.service.WxAccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("wxAccessTokenService")
public class WxAccessTokenServiceImpl implements WxAccessTokenService {

	@Autowired
	private WxConfigMapper wxConfigMapper;
	
	@Override
	public void resetAccessToken() {
		log.info("begin to WxAccessTokenServiceImpl.resetAccessToken send");

		List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
		if(configDOList==null || configDOList.size()==0
				|| StringUtils.isBlank(configDOList.get(0).getAppid())){
			throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
		}

		IndependServiceNoCache.resetAccessToken(configDOList.get(0).getAppid(), configDOList.get(0).getAppsecret());

		log.info("end to WxAccessTokenServiceImpl.resetAccessToken send");
	}
}
