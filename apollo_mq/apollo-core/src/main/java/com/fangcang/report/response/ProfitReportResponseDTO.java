package com.fangcang.report.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/7/10
 */
@Data
public class ProfitReportResponseDTO implements Serializable {

    private static final long serialVersionUID = -8239117257831853075L;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 分销商编码
     */
    private String agentCode;
    /**
     * 分销商名称
     */
    private String agentName;
    /**
     * 酒店id
     */
    private Integer hotelId;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 产品经理名称
     */
    private String merchantPm;
    /**
     * 业务经理名称
     */
    private String merchantBm;
    /**
     * 售卖币种
     */
    private String saleCurrency;
    /**
     * 间夜数
     */
    private Integer roomNight;
    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;
    /**
     * 应付金额
     */
    private BigDecimal payableAmount;
    /**
     * 利润
     */
    private BigDecimal profit;

    /**
     * 平均利润
     */
    private BigDecimal avgProfit;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;


}
