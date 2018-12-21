package com.travel.hotel.dlt.request.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhanwang
 */
@Data
public class OrderRequestPriceDTO implements Serializable {

    private static final long serialVersionUID = 8478106339660502151L;

    private Integer id;

    /**
     * 订单申请id
     */
    private Integer orderRequestId;

    /**
     * 日期
     */
    private String saleDate;

    /**
     * 售价
     */
    private BigDecimal salePrice;

}