package com.fangcang.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipleCurrencyAmountDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 币种
     */
    private String currency;

    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;

    /**
     * 实收金额
     */
    private BigDecimal paidinAmount;

    /**
     * 通知金额
     */
    private BigDecimal noticeAmount;
}
