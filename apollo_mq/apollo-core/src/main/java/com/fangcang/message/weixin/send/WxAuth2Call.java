package com.fangcang.message.weixin.send;

import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.response.Auth2AccessTokenResponse;
import com.fangcang.message.weixin.send.response.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * auth2服务
 */
@Slf4j
public class WxAuth2Call extends BaseActiveCall {
	
	/**
     * 获取访问用户auth2 accessToken信息<br>
     * @param appId appId【必须】
     * @param appSecret appSecret【必须】
     * @return AccessTokenResponse
	 * @throws Exception 
     */
	public static Auth2AccessTokenResponse getAuth2AccessToken(String appId, String appSecret, String code) throws Exception{
		Map<String,String> urlParams = new HashMap<String,String>();
		urlParams.put("appid", appId);
		urlParams.put("secret", appSecret);
		urlParams.put("code", code);
		urlParams.put("grant_type", "authorization_code");
		
		//发送请求
		Auth2AccessTokenResponse servAuth2AccessTokenReturn = getActiveCall(null, WxURLConstant.WXURL_AUTH2_ACCESSTOKEN, urlParams, Auth2AccessTokenResponse.class);
		
        return servAuth2AccessTokenReturn;
	}
	
	/**
	 * 获取用户基本信息
	 * @param accessToken
	 * @param openid
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	public static UserInfoResponse getUserInfo(String accessToken, String openid, String lang) throws Exception {
		Map<String,String> urlParams = new HashMap<String,String>();
		urlParams.put("access_token", accessToken);
		urlParams.put("openid", openid);
		urlParams.put("lang", lang);
		
		//发送请求
		UserInfoResponse servUserInfoReturn = getActiveCall(null, WxURLConstant.WXURL_GET_USERINFO, urlParams, UserInfoResponse.class);
		
        return servUserInfoReturn;
	}
}
