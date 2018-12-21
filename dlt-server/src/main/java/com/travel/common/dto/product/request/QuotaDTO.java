package com.travel.common.dto.product.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *   2018/1/24.
 */
@Data
public class QuotaDTO implements Serializable {


    private static final long serialVersionUID = -1902888711513773992L;
    private Long pricePlanId;
    private String pricePlanName;
    private String distributeCode;
    private Date saleDate;
    private Integer quotaNum;
    private String orderCode;
}
