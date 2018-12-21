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
public class ReceivableSummaryDTO implements Serializable {

    private static final long serialVersionUID = 1445569467788091214L;
    /**
     * 应收金额
     */
    private BigDecimal receivableAmountSum;

    /**
     * 实收金额
     */
    private BigDecimal settlementAmountSum;

    /**
     * 未收金额
     */
    private BigDecimal unReceivableAmountSum;

    /**
     * 利润
     */
    private BigDecimal profitSum;
}
