package com.fangcang.finance.financeorder.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FinanceOrderItemDTO implements Serializable{

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 金额
     */
    private BigDecimal amount;
}
