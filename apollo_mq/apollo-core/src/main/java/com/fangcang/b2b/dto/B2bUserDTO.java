package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/5 17:22
 * @Description: B2B登录账号信息
 */
@Data
public class B2bUserDTO implements Serializable {

    private static final long serialVersionUID = -2200072413975421110L;


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
     * 信用额度(总额度)
     */
    private BigDecimal amount;

    /**
     * 结算方式
     */
    private Integer billingMethod;

    /*    *//**
     * 产品经理账号ID
     *//*
    private Long merchantPM;

    *//**
     * 产品经理姓名
     *//*
    private String merchantPMName;

    *//**
     * 产品经理电话
     *//*
    private String merchantPMPhone;

    *//**
     * 业务经理账号ID
     *//*
    private Long merchantBM;

    *//**
     * 业务经理姓名
     *//*
    private String merchantBMName;

    *//**
     * 业务经理电话
     *//*
    private String merchantBMPhone;

    *//**
     * 跟单员账号ID
     *//*
    private Long merchantOP;

    *//**
     * 跟单员姓名
     *//*
    private String merchantOPName;

    *//**
     * 跟单员电话
     *//*
    private String merchantOPPhone;

    *//**
     * 财务员账号ID
     *//*
    private Long merchantFinancer;

    *//**
     * 财务员姓名
     *//*
    private String merchantFinancerName;

    *//**
     * 财务员电话
     *//*
    private String merchantFinancerPhone;*/

}
