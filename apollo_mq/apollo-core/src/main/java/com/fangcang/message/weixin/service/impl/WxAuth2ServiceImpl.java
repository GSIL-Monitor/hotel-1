package com.fangcang.message.weixin.service.impl;

import com.fangcang.common.exception.ParameterException;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.exception.handle.ExceptionHandle;
import com.fangcang.message.remote.enums.WxAuth2ErrorEnum;
import com.fangcang.message.remote.request.weixin.WxConfigRequestDTO;
import com.fangcang.message.remote.response.weixin.WxAuth2RedirectResponseDTO;
import com.fangcang.message.remote.response.weixin.WxConfigResponseDTO;
import com.fangcang.message.remote.response.weixin.WxUserInfoResponseDTO;
import com.fangcang.message.remote.response.weixin.WxVisitIdResponseDTO;
import com.fangcang.message.weixin.cache.IndependServiceNoCache;
import com.fangcang.message.weixin.domain.WxConfigDO;
import com.fangcang.message.weixin.mapper.WxConfigMapper;
import com.fangcang.message.weixin.send.WxAuth2Call;
import com.fangcang.message.weixin.send.response.Auth2AccessTokenResponse;
import com.fangcang.message.weixin.send.response.UserInfoResponse;
import com.fangcang.message.weixin.service.WxAuth2Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("wxAuth2Service")
public class WxAuth2ServiceImpl implements WxAuth2Service {

    @Autowired
    private WxConfigMapper wxConfigMapper;

    @Override
    public List<WxConfigResponseDTO> queryWxConfig(WxConfigRequestDTO requestDTO){
        List<WxConfigResponseDTO> responseDTOList=new ArrayList<>();
        List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
        for(WxConfigDO config:configDOList){
            WxConfigResponseDTO responseDTO=new WxConfigResponseDTO();
            responseDTO.setAppid(config.getAppid());
            responseDTO.setAppsecret(config.getAppsecret());
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    @Override
    public WxAuth2RedirectResponseDTO auth2Redirect(String callbackUrl){
        WxAuth2RedirectResponseDTO response = new WxAuth2RedirectResponseDTO();

        if(StringUtils.isBlank(callbackUrl)){
            throw new ParameterException("callbackUrl不能为空");
        }

        List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
        if(configDOList==null || configDOList.size()==0
                || StringUtils.isBlank(configDOList.get(0).getAppid())){
            throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
        }
        String appId=configDOList.get(0).getAppid();
        String appSecret=configDOList.get(0).getAppsecret();

        try{
            String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+configDOList.get(0).getAppid()
                    +"&redirect_uri="+ URLEncoder.encode(callbackUrl,"UTF-8")
                    +"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
            response.setRedirectUrl(redirectUrl);
        }catch(Exception e){
            log.error("转换重定向地址异常", e);
            throw new ServiceException(WxAuth2ErrorEnum.DEAL_REDIRECTURL_ERROR.getErrorMsg());
        }

        return response;
    }

    @Override
    public WxVisitIdResponseDTO getVisitWeixinId(String code) {

        WxVisitIdResponseDTO response = new WxVisitIdResponseDTO();

        if(StringUtils.isBlank(code)){
            throw new ParameterException("code不能为空");
        }

        List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
        if(configDOList==null || configDOList.size()==0
                || StringUtils.isBlank(configDOList.get(0).getAppid())){
            throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
        }

        try{
            Auth2AccessTokenResponse auth2AccessTokenReturn = WxAuth2Call.getAuth2AccessToken(configDOList.get(0).getAppid(), configDOList.get(0).getAppsecret(), code);
            if(auth2AccessTokenReturn.getErrcode() == null || auth2AccessTokenReturn.getErrcode() == 0){
                response.setOpenid(auth2AccessTokenReturn.getOpenid());
                response.setUnionid(auth2AccessTokenReturn.getUnionid());
            }else{
                log.error("获取微信访问者accessToken失败");
                throw new ServiceException("获取微信访问者accessToken失败");
            }
        }catch(Exception e){
            ExceptionHandle.throwServiceException(e,"获取微信访问者accessToken失败");
        }

        return response;
    }

    @Override
    public WxUserInfoResponseDTO getWeixinUserInfo(String openid) {

        WxUserInfoResponseDTO response = new WxUserInfoResponseDTO();

        if(StringUtils.isBlank(openid)){
            throw new ParameterException("openid不能为空");
        }

        List<WxConfigDO> configDOList=wxConfigMapper.selectAll();
        if(configDOList==null || configDOList.size()==0
                || StringUtils.isBlank(configDOList.get(0).getAppid())){
            throw new ServiceException(WxAuth2ErrorEnum.NO_BIND_WEIXIN_SERVICENO.getErrorMsg());
        }

        try {
            String accessToken = IndependServiceNoCache.getAccessToken(configDOList.get(0).getAppid(), configDOList.get(0).getAppsecret());
            if (StringUtils.isNotBlank(accessToken)) {
                UserInfoResponse servUserInfoReturn = WxAuth2Call.getUserInfo(accessToken, openid, "zh_CN");
                if(servUserInfoReturn.getErrcode() == null) {
                    BeanUtils.copyProperties(servUserInfoReturn,response);
                } else {
                    log.info("************ end ServAuth2RemoteImpl.getWeixinUserInfo ************\n" + response);
                    ExceptionHandle.throwServiceException(null,"获取用户信息异常");
                }
            } else {
                log.info("************ end ServAuth2RemoteImpl.getWeixinUserInfo ************\n" + response);
                ExceptionHandle.throwServiceException(null,"获取用户信息异常");
            }
        } catch(Exception e){
            log.error("************ end ServAuth2RemoteImpl.getWeixinUserInfo ************\n" + response, e);
            ExceptionHandle.throwServiceException(e,"获取用户信息异常");
        }

        return response;
    }
}
