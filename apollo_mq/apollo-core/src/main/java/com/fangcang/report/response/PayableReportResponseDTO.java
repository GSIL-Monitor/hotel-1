package com.fangcang.report.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/7/10
 */
@Data
public class PayableReportResponseDTO implements Serializable {
    private static final long serialVersionUID = -8988258151065419760L;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 间夜数
     */
    private Integer roomNight;

    /**
     * 底价币种
     */
    private String baseCurrency;

    /**
     * 应付金额
     */
    private BigDecimal payableAmount;

    /**
     * 实付金额
     */
    private BigDecimal settlementAmount;

    /**
     * 未付金额
     */
    private BigDecimal unPayableAmount;

    /**
     * 总利润
     */
    private BigDecimal profit;

}
