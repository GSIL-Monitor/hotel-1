package com.fangcang.merchant.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name="t_merchant")
@Data
@EqualsAndHashCode(callSuper = false)
public class MerchantDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long merchantId;

    private String merchantCode;

    /**
     * 公司名称
     */
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
