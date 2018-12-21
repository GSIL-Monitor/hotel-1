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
public class ProfitSummaryDTO implements Serializable {

    private static final long serialVersionUID = -3493598730443330379L;
    /**
     * 总间夜数
     */
    private Integer roomNightSum;

    /**
     * 总应收
     */
    private BigDecimal receivableAmountSum;

    /**
     * 总应付
     */
    private BigDecimal payableAmountSum;

    /**
     * 总利润
     */
    private BigDecimal profitSum;

    /**
     * 平均利润
     */
    private BigDecimal avgProfit;
}
