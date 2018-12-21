package com.fangcang.order.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateOrderExceptionAmountDTO implements Serializable{

    private Integer orderId;

    private BigDecimal exceptionAmount;

    private String operator;
}
