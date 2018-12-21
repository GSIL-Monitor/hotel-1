package com.fangcang.report.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhanwang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayableSummaryDTO implements Serializable {

    private static final long serialVersionUID = 6660528867585592396L;
    /**
     * 应付金额
     */
    private BigDecimal payableAmountSum;

    /**
     * 实付金额
     */
    private BigDecimal settlementAmountSum;

    /**
     * 未付金额
     */
    private BigDecimal unPayableAmountSum;

    /**
     * 利润
     */
    private BigDecimal profitSum;
}
