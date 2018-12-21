package com.fangcang.message.weixin.service.impl;

import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.exception.handle.ExceptionHandle;
import com.fangcang.message.remote.enums.WxAuth2ErrorEnum;
import com.fangcang.message.remote.request.weixin.WxGetJSApiTicketRequestDTO;
import com.fangcang.message.remote.response.weixin.WxGetJSApiTicketResponseDTO;
import com.fangcang.message.weixin.cache.IndependServiceNoCache;
import com.fangcang.message.weixin.domain.WxConfigDO;
import com.fangcang.message.weixin.mapper.WxConfigMapper;
import com.fangcang.message.weixin.service.WxJSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.List;
import java.util.UUID;

/**
 * 获取jsapi_ticket
 */
@Slf4j
@Service("wxJSService")
public class WxJSServiceImpl implements WxJSService {

    @Autowired
    private WxConfigMapper wxConfigMapper;

    @Override
    public WxGetJSApiTicketResponseDTO getJsApiTicket(WxGetJSApiTicketRequestDTO request) {
        WxGetJSApiTicketResponseDTO response = new WxGetJSApiTicketResponseDTO();

        if(request==null){
            throw new ParameterException("request不能为空");
        }

        List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
        if(configDOList==null || configDOList.size()==0
                || StringUtils.isBlank(configDOList.get(0).getAppid())){
            throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
        }
        response = getJsApiTicket(configDOList.get(0).getAppid(), configDOList.get(0).getAppsecret(), request);
        return response;
    }

	private WxGetJSApiTicketResponseDTO getJsApiTicket(String appId, String appSecret, WxGetJSApiTicketRequestDTO request) {
		log.info("begin to ServJSServiceImpl.getJsApiTicket send");
		WxGetJSApiTicketResponseDTO responseDTO = new WxGetJSApiTicketResponseDTO();
		
        String nonce_str = UUID.randomUUID().toString();
        Long timestamp = System.currentTimeMillis() / 1000;
        String jsapi_ticket = IndependServiceNoCache.getJSApiTicket(appId, appSecret);
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + request.getUrl();
        log.debug("签名string1="+string1);

        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }catch (Exception e){
        	ExceptionHandle.throwServiceException(e, "生成签名signature异常");
        }
        
        log.debug("签名signature="+signature);

        responseDTO.setAppId(appId);
        responseDTO.setNoncestr(nonce_str);
        responseDTO.setTimestamp(timestamp.toString());
        responseDTO.setUrl(request.getUrl());
        responseDTO.setSignature(signature);

        log.info("end to ServJSServiceImpl.getJsApiTicket send");
		return responseDTO;
	}
	
	
	private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
	
}
