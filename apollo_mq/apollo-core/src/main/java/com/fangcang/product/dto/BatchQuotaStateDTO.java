package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/29.
 */
@Data
public class BatchQuotaStateDTO implements Serializable {

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 配额账号
     */
    private Long quotaAccountId;

    /**
     * 日期段
     */
    private List<DateSegmentQuotaDTO> dateSegmentQuotaList;
}
