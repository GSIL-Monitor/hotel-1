package com.fangcang.finance.bill.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CheckOutCurrencyDO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 金额
     */
    private BigDecimal amount;
}
