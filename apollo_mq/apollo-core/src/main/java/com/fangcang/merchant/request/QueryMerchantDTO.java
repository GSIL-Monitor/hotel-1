package com.fangcang.merchant.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryMerchantDTO implements Serializable {
    private static final long serialVersionUID = -9179160103816200551L;

    private Long merchantId;

    private String merchantCode;

    private String domain;

    private String ebkDomain;
}
