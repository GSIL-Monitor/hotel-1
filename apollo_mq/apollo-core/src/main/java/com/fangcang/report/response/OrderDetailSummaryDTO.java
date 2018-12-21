package com.fangcang.report.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailSummaryDTO implements Serializable {

    /**
     * 总应收
     */
    private BigDecimal receivableAmountSum;

    /**
     * 总应付
     */
    private BigDecimal payableAmountSum;

    /**
     * 总间夜数
     */
    private Integer roomNightSum;

    /**
     * 总利润
     */
    private BigDecimal profitSum;

    /**
     * 平均利润
     */
    private BigDecimal avgProfit;

    /**
     * 总间数
     */
    private Integer roomNumSum;
}
