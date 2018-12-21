package com.fangcang.report.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/7/10
 */
@Data
public class ReceivableReportResponseDTO implements Serializable {
    private static final long serialVersionUID = -8988258151065419760L;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道名称
     */
    private String channelName;


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
     * 分销商币种
     */
    private String saleCurrency;

    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;

    /**
     * 实收金额
     */
    private BigDecimal settlementAmount;

    /**
     * 未收金额
     */
    private BigDecimal unReceivableAmount;

    /**
     * 总利润
     */
    private BigDecimal profit;

}
