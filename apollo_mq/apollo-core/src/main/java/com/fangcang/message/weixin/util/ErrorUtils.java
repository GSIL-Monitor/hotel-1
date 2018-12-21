package com.fangcang.message.weixin.util;

import com.fangcang.message.weixin.send.response.BaseResponse;

/**
 * 错误工具类
 */
public class ErrorUtils {
	
	/**
     * 根据自定义错误码获取错误信息
     * @param customErrorCode
	 * @return String
     * @throws IllegalAccessException 
     */
	public static String getCustomErrorMessage(Integer customErrorCode){
		return ConfigUtils.getConfigValue("CUSTOM_ERR_"+customErrorCode);
	}
	
	/**
     * 根据微信错误码获取错误信息
     * @param weixinErrorCode
	 * @return String
     * @throws IllegalAccessException 
     */
	public static String getWeixinErrorMessage(Integer weixinErrorCode){
		return ConfigUtils.getConfigValue("WEIXIN_ERR_"+weixinErrorCode);
	}
	
	/**
	 * 设置微信定义错误信息<br>
	 * 如果配置文件没有配置错误描述，就以传进来的错误信息为准
	 * @param weixinErrcode
	 * @param weixinErrMsg
	 * @return 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T extends BaseResponse> T getWeixinErrorReturn(Integer weixinErrcode, String weixinErrMsg, T t) throws InstantiationException, IllegalAccessException{
		t.setErrcode(weixinErrcode);
		t.setErrmsg(ErrorUtils.getWeixinErrorMessage(t.getErrcode()));
		
		if(t.getErrmsg() == null){
			t.setErrmsg(weixinErrMsg);
		}
		
		return t;
	}
	
	/**
	 * 设置自定义错误信息
	 * @param customErrcode
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T extends BaseResponse> T getCustomErrorReturn(Integer customErrcode, T t) throws InstantiationException, IllegalAccessException{
		t.setErrcode(customErrcode);
		t.setErrmsg(ErrorUtils.getCustomErrorMessage(t.getErrcode()));
		
		return t;
	}
}
