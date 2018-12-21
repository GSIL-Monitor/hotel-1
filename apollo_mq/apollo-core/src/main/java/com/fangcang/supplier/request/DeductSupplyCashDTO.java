package com.fangcang.supplier.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DeductSupplyCashDTO implements Serializable{

    /**
     * 供应商id
     */
    private Long supplyId;

    /**
     * 操作类型：1扣除，2退还
     */
    private Integer operatorType;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 订单号
     */
    private String orderCode;

    private String note;

    private String operator;
}
