package com.fangcang.order.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateSupplyOrderExceptionAmountDTO implements Serializable {

    private Integer supplyOrderId;

    private BigDecimal exceptionAmount;

    private String operator;
}
