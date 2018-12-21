package com.fangcang.supplier.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuerySupplyOrderDeductCreditDTO implements Serializable {
    private static final long serialVersionUID = -3915157452518806984L;

    /**
     * 订单号
     */
    private String orderCode;
}
