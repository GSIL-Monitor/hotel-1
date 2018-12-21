package com.fangcang.message.weixin.send;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.message.weixin.enums.WxErrorConstant;
import com.fangcang.message.weixin.send.response.BaseResponse;
import com.fangcang.message.weixin.util.ErrorUtils;
import com.fangcang.message.weixin.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseActiveCall {
	
	/**
     * get方式主动向微信发送请求
     * @param accessToken accessToken【必须】
     * @param url 请求的URL【必须】
     * @param urlParams 请求URL后带的参数【非必须】
     * @param returnClass 返回对象类型【必须】
     * @return T
	 * @throws Exception 
     */
	protected static <T extends BaseResponse>T getActiveCall(String accessToken, String url, Map<String, String> urlParams, Class<T> returnClass) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		if(accessToken != null){
			urlParams.put("access_token", accessToken);
		}
        
		try{
			String requestUrl = HttpUtils.getUrlWithParams(url,urlParams);
			log.info("请求微信地址[GET]："+requestUrl);
			
			String receiveJsonStr = HttpUtils.get(requestUrl);
	        log.info("微信返回结果[GET]："+receiveJsonStr);
	        
	        T t = JSONObject.parseObject(receiveJsonStr, returnClass);
	        return t;
		}catch(Throwable t){
			log.error(t.getMessage(), t);
			return ErrorUtils.getWeixinErrorReturn(WxErrorConstant.SYSTEM_BUSY, t.getMessage(), returnClass.newInstance());
		}
	}
	
	/**
     * post方式主动向微信发送请求
     * @param String accessToken String accessToken【必须】
     * @param url 请求的URL【必须】
     * @param urlParams 请求URL后带的参数【非必须】
     * @param content post请求内容的字符串【必须】
     * @param returnClass 返回对象类型【必须】
     * @return T
	 * @throws Exception 
     */
	protected static <T extends BaseResponse>T postActiveCall(String accessToken, String url, Map<String, String> urlParams, String content, Class<T> returnClass) throws Exception{
		if(urlParams == null){
			urlParams = new HashMap<String,String>();
		}
		if(accessToken != null){
			urlParams.put("access_token", accessToken);
		}
        
		try{
			String requestUrl = HttpUtils.getUrlWithParams(url,urlParams);
			log.info("请求微信地址[POST]："+requestUrl);
			log.info("请求微信内容[POST]："+content);

	        String receiveJsonStr = HttpUtils.post(requestUrl, content);
	        log.info("微信返回结果[POST]："+receiveJsonStr);
	        
	        T t = JSONObject.parseObject(receiveJsonStr, returnClass);
	        return ErrorUtils.getWeixinErrorReturn(t.getErrcode(), t.getErrmsg(), t);
		}catch(Throwable t){
			log.error(t.getMessage(), t);
			return ErrorUtils.getWeixinErrorReturn(WxErrorConstant.SYSTEM_BUSY, t.getMessage(), returnClass.newInstance());
		}
	}
	
	
	
}
