package com.fangcang.agent.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 09:25
 * @Description: 分销商用户DTO(B2B)
 */
@Data
public class AgentUserDTO implements Serializable {

    private static final long serialVersionUID = -8685939632362068230L;

    private Long agentUserId;

    /**
     * 登录名(账号)
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 分销商id
     */
    private Long agentId;

    /**--------需要联查-------*/
    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名字
     */
    private String agentName;

    /**
     * 分销商关联的商家
     */
    private Long merchantId;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 结算币种
     */
    private String financeCurrency;

    /**
     * 信用额度(总额度)
     */
    private BigDecimal amount;

    /**
     * 结算方式
     */
    private Integer billingMethod;

    /**
     * 分销商城市名
     */
    private String cityName;

    /**
     * 分销商城市编码
     */
    private String cityCode;

    /**
     * 微信昵称
     */
    private String wxName;

    /**
     * 微信头像地址
     */
    private String wxHead;
}
