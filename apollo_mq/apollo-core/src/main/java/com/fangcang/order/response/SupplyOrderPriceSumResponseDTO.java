package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/6/21
 */
@Data
public class SupplyOrderPriceSumResponseDTO implements Serializable {
    private static final long serialVersionUID = 279148996574981793L;

    /**
     * 供货单总底价
     */
    private BigDecimal supplyBasePriceSum;
    /**
     * 供货单总售价
     */
    private BigDecimal supplySalePriceSum;
}
