package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class PriceInfoOperateDTO implements Serializable {

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    private List<DateSegmentPriceDTO> dateSegmentPriceList;
}
