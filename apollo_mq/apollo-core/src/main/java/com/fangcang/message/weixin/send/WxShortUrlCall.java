package com.fangcang.message.weixin.send;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.weixin.enums.WxURLConstant;
import com.fangcang.message.weixin.send.response.ShortUrlResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 长链接转短链接Service
 */
public class WxShortUrlCall extends BaseActiveCall {

	/**
	 * 长链接转短链接
	 * @param accessToken
	 * @param action
	 * @param longUrl
	 * @return
	 * @throws Exception
	 */
	public static ShortUrlResponse toShortUrl(String accessToken, String action, String longUrl) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", accessToken);
		map.put("action", action);
		map.put("long_url", longUrl);
		
		//发送请求
		ShortUrlResponse servShortUrlReturn = postActiveCall(accessToken, WxURLConstant.WXURL_SHORTURL, null, JSONObject.toJSONString(map), ShortUrlResponse.class);
        return servShortUrlReturn;
	}
}
