package com.fangcang.report.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryOrderExceptionDTO extends BaseQueryConditionDTO {

    /**
     * 日期类型（1下单日期，2入住日期，3离店日期）
     */
    private Integer dateType;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 酒店id
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 商家编码
     */
    private String merchantCode;
}
