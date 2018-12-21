package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/2.
 */
@Data
public class DebuctQuotaQueryPricePlanDTO implements Serializable {

    private Long supplyOrderId;

    /**
     * 供货单编码
     */
    private String supplyOrderCode;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 该价格计划扣配额详情
     */
    private List<QuotaStateDTO> quotaStateList;

}
