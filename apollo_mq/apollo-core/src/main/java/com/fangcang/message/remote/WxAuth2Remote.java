package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.response.weixin.WxAuth2RedirectResponseDTO;
import com.fangcang.message.remote.response.weixin.WxConfigResponseDTO;
import com.fangcang.message.remote.response.weixin.WxUserInfoResponseDTO;
import com.fangcang.message.remote.response.weixin.WxVisitIdResponseDTO;

import java.util.List;

public interface WxAuth2Remote {

	/**
	 * 查询微信配置
	 * @return
     */
	public ResponseDTO<List<WxConfigResponseDTO>> queryWxConfig();
	
	/**
	 * 用户auth2重定向处理
	 * @param callbackUrl	回调地址（业务端处理获取微信openid的回调地址）
	 * @return
	 */
	public ResponseDTO<WxAuth2RedirectResponseDTO> auth2Redirect(String callbackUrl);
	
	/**
	 * 服务号获取访问者微信openID接口
	 * @param code			微信返回的code（一次性使用，获取用户openid使用）
	 * @return
	 */
	public ResponseDTO<WxVisitIdResponseDTO> getVisitWeixinId(String code);
	
	/**
	 * 根据用户openid获取微信端用户相关信息
	 * @param openid		微信用户openid
	 * @return
	 */
	public ResponseDTO<WxUserInfoResponseDTO> getWeixinUserInfo(String openid);
	
}
