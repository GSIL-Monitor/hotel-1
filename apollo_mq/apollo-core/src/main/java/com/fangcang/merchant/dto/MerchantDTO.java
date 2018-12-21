package com.fangcang.merchant.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MerchantDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long merchantId;

    private String merchantCode;

    private String merchantName;

    private String address;

    private String phone;

    private String fax;

    private String domain;

    private Integer merchantCurrency;

    private Date createTime;

    private String creator;

    private String modifier;

    private Date modifyTime;

    /**
     * ebk域名
     */
    private String ebkDomain;

    /**
     * ebk系统名称
     */
    private String ebkName;

    /**
     * 第二域名
     */
    private String secondDomain;

    /**
     * 内部系统名称
     */
    private String systemName;
}
