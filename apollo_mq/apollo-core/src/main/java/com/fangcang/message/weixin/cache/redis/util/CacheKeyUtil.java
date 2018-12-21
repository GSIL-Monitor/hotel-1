package com.fangcang.message.weixin.cache.redis.util;

public class CacheKeyUtil {

	//key 分隔符
	public final static String KEY_SEPARATOR = ":";
	//redis 前缀
	public final static String PARAM_REDIS_KEY_PREFIX = "weixin-";
	//HttpServletRequest 前缀
	public final static String PARAM_CACHE_KEY_PREFIX = "cache-";
	//店铺代理店主key
	public final static String AGENT = "agent";
	
	/**
	 * 获得店铺的代理店主key(从redis中)
	 * @param agentInfoCode 商家编码
	 * @param fromRedis 是否从redis中(true:从redis中，false,从HttpServletRequest中)
	 * @return
	 */
	public final static String getShopAgentKey(String agentInfoCode, boolean fromRedis){
		if(fromRedis){
			return PARAM_REDIS_KEY_PREFIX + AGENT + KEY_SEPARATOR + agentInfoCode;
		}else{
			return PARAM_CACHE_KEY_PREFIX + AGENT + KEY_SEPARATOR + agentInfoCode;
		}
	}
	
	/**
	 * 获取商家模版消息ID 的 缓存key
	 * @param templateCode
	 * @return
	 */
	public final static String getMsgTemplateKey(String templateCode){
		return "weixin-msgtemplate"+KEY_SEPARATOR+templateCode;
	}
	
	/**
	 * 获取微信公众号accessToken 的 缓存key
	 * @param appId
	 * @return
	 */
	public final static String getWechatAccessTokenKey(String appId){
		return "weixin-accessToken"+KEY_SEPARATOR+appId;
	}
	
	/**
	 * 获取微信公众号accessToken 的 缓存key
	 * @param appId
	 * @return
	 */
	public final static String getWechatJSApiTicketKey(String appId){
		return "weixin-WxJSApiTicketCall"+KEY_SEPARATOR+appId;
	}

	/**
	 * 获取群发消息 的 缓存key
	 * @return
	 */
	public final static String getMassMsgKey() {
		return "weixin-massmsg";
	}
}
