package com.fangcang.message.weixin.cache;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.exception.handle.ExceptionHandle;
import com.fangcang.message.weixin.cache.redis.util.CacheKeyUtil;
import com.fangcang.message.weixin.cache.redis.util.RedisUtil;
import com.fangcang.message.weixin.send.WxAccessTokenCall;
import com.fangcang.message.weixin.send.WxJSApiTicketCall;
import com.fangcang.message.weixin.send.response.AccessTokenResponse;
import com.fangcang.message.weixin.send.response.JSApiTicketResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 独立服务号缓存服务
 *
 */
@Component
@Slf4j
public class IndependServiceNoCache {
	
	/**
	 * 获取服务号access_token
	 */
	public static String getAccessToken(String appId, String appSecret){
		return getAccessToken(appId, appSecret, false);
	}
	
	/**
	 * 重置服务号access_token
	 */
	public static String resetAccessToken(String appId, String appSecret){
		return getAccessToken(appId, appSecret, true);
	}

	@Autowired
	private RedisTemplate redisTemplate;

	private static RedisUtil redisUtil;

	@Autowired
	public void setRedisUtil(RedisUtil redisUtil){
		IndependServiceNoCache.redisUtil = redisUtil;
	}
	
	/**
	 * 获取服务号access_token
	 */
	private static String getAccessToken(String appId, String appSecret, boolean isReset){
		String accessToken = null;
		//1.不是重置的话，优先从缓存读取
		String redisCacheKey = CacheKeyUtil.getWechatAccessTokenKey(appId);
		if(!isReset){
			accessToken = redisUtil.queryDataInCache(redisCacheKey);
		}
		
		//2.从微信从新获取，并放到缓存
		if(StringUtils.isBlank(accessToken)){
			try{
				AccessTokenResponse accessTokenReturn = WxAccessTokenCall.getAccessToken(appId, appSecret);
				log.info("getAccessToken result:" + JSON.toJSONString(accessTokenReturn));
				if(accessTokenReturn != null && 
					(accessTokenReturn.getErrcode()==null || accessTokenReturn.getErrcode()==0)){
					
					//将新的设置到缓存中
					accessToken = accessTokenReturn.getAccess_token();
					redisUtil.saveDataInCache(redisCacheKey, accessToken, accessTokenReturn.getExpires_in() - 300);
				}
			}catch(Exception e){
				ExceptionHandle.throwServiceException(e, "获取服务号accessToken失败");
			}	
		}
		
		if(StringUtils.isBlank(accessToken)){
			ExceptionHandle.throwServiceException(null, "获取服务号accessToken失败");
		}
		
		return accessToken;
	}
	
	
	
	/**
	 * 获取服务号JSApiTicket
	 */
	public static String getJSApiTicket(String appId, String appSecret){
		String jsApiTicket = null;
		//1.优先从缓存读取
		String redisCacheKey = CacheKeyUtil.getWechatJSApiTicketKey(appId);
		jsApiTicket = redisUtil.queryDataInCache(redisCacheKey);
		
		//2.从微信从新获取，并放到缓存
		if(StringUtils.isBlank(jsApiTicket)){
			try{
				String accessToken = IndependServiceNoCache.getAccessToken(appId, appSecret);
				
				JSApiTicketResponse servJSApiTicketReturn = WxJSApiTicketCall.getJSApiTicket(accessToken);
				if(servJSApiTicketReturn != null && 
					(servJSApiTicketReturn.getErrcode()==null || servJSApiTicketReturn.getErrcode()==0)){
					
					//将新的设置到缓存中
					jsApiTicket = servJSApiTicketReturn.getTicket();
					redisUtil.saveDataInCache(redisCacheKey, jsApiTicket, servJSApiTicketReturn.getExpires_in() - 300);
				}
			}catch(Exception e){
				ExceptionHandle.throwServiceException(null, "获取服务号accessToken失败");
			}
		}
		
		if(StringUtils.isBlank(jsApiTicket)){
			ExceptionHandle.throwServiceException(null, "获取服务号accessToken失败");
		}
		
		return jsApiTicket;
	}
	
}
