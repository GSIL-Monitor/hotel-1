package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供货单产品价格对象
 */
@Data
public class SupplyProductPriceDTO implements Serializable {
    private static final long serialVersionUID = -3690155608491575992L;
    private Integer id;

    /**
     * 供货单id
     */
    private Integer supplyProductId;

    /**
     * 日期
     */
    private String saleDate;

    /**
     * 售价
     */
    private BigDecimal salePrice;

    /**
     * 底价
     */
    private BigDecimal basePrice;


}