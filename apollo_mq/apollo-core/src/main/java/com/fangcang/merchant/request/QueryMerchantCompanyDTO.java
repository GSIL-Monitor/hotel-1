package com.fangcang.merchant.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryMerchantCompanyDTO implements Serializable {
    private static final long serialVersionUID = -3915157452518806984L;

    private Integer merchantCompanyId;

    private Integer supplyId;

    private String merchantCode;
}
