package com.fangcang.finance.report.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryCheckReportDTO extends BaseQueryConditionDTO{

    private String orderCode;

    /**
     * 日期类型（1下单日期，2入住日期，3离店日期）
     */
    private Integer dateType;

    private String beginDate;

    private String endDate;

    private Integer hotelId;

    private String hotelName;

    private String orgCode;

    private String orgName;

    private String guest;

    /**
     * 结算状态：-1全部，0未结算，1已结算
     */
    private Integer settlementStatus;

    /**
     * 对账状态：-1全部，0未对账，1已对账
     */
    private Integer accountStatus;

    private String merchantCode;
}
