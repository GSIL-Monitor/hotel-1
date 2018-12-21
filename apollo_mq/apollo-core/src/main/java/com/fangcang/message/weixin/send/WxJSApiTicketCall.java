package com.fangcang.message.weixin.send;

import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.response.JSApiTicketResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * accessToken服务
 */
@Slf4j
public class WxJSApiTicketCall extends BaseActiveCall {
	
	/**
     * 获取accessToken<br>
     * @param appId appId【必须】
     * @param appSecret appSecret【必须】
     * @return AccessTokenResponse
	 * @throws Exception 
     */
	public static JSApiTicketResponse getJSApiTicket(String accessToken) throws Exception{
		Map<String,String> urlParams = new HashMap<String,String>();
		urlParams.put("type", "jsapi");
		
		//发送请求
		JSApiTicketResponse servJSApiTicketReturn = getActiveCall(accessToken, WxURLConstant.WXURL_JSAPI_TICKET, urlParams, JSApiTicketResponse.class);
        return servJSApiTicketReturn;
	}
}
