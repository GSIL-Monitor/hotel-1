package com.fangcang.message.weixin.send;

import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.response.AccessTokenResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * accessToken服务
 */
@Slf4j
public class WxAccessTokenCall extends BaseActiveCall {
	
	/**
     * 获取accessToken<br>
     * @param appId appId【必须】
     * @param appSecret appSecret【必须】
     * @return AccessTokenResponse
	 * @throws Exception 
     */
	public static AccessTokenResponse getAccessToken(String appId, String appSecret) throws Exception{
		Map<String,String> urlParams = new HashMap<String,String>();
		urlParams.put("grant_type", "client_credential");
		urlParams.put("appid", appId);
		urlParams.put("secret", appSecret);
		
		//发送请求
		AccessTokenResponse servAccessTokenReturn = getActiveCall(null, WxURLConstant.WXURL_ACCESS_TOKEN, urlParams, AccessTokenResponse.class);
        return servAccessTokenReturn;
	}
}
