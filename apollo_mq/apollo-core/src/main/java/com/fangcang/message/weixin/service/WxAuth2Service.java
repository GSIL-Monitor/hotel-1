package com.fangcang.message.weixin.service;

import com.fangcang.message.remote.request.weixin.WxConfigRequestDTO;
import com.fangcang.message.remote.response.weixin.WxAuth2RedirectResponseDTO;
import com.fangcang.message.remote.response.weixin.WxConfigResponseDTO;
import com.fangcang.message.remote.response.weixin.WxUserInfoResponseDTO;
import com.fangcang.message.remote.response.weixin.WxVisitIdResponseDTO;

import java.util.List;

public interface WxAuth2Service {

    /**
     * 查询微信配置
     * @param requestDTO
     * @return
     */
    public List<WxConfigResponseDTO> queryWxConfig(WxConfigRequestDTO requestDTO);

    /**
     * 用户auth2重定向处理
     * @param callbackUrl	回调地址（业务端处理获取微信openid的回调地址）
     * @return
     */
    public WxAuth2RedirectResponseDTO auth2Redirect(String callbackUrl);

    /**
     * 服务号获取访问者微信openID接口
     * @param code			微信返回的code（一次性使用，获取用户openid使用）
     * @return
     */
    public WxVisitIdResponseDTO getVisitWeixinId(String code);

    /**
     * 根据用户openid获取微信端用户相关信息
     * @param openid		微信用户openid
     * @param lang			返回国家地区语言版本(非必填)，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     */
    public WxUserInfoResponseDTO getWeixinUserInfo(String openid);
}
