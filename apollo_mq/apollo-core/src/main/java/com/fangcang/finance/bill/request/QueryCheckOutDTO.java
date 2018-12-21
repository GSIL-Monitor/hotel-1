package com.fangcang.finance.bill.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.util.List;

@Data
public class QueryCheckOutDTO extends BaseQueryConditionDTO {

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
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 客人
     */
    private String guest;

    /**
     * 客户单号
     */
    private String customerOrderCode;

    /**
     * 财务状态
     */
    private List<Integer> accountStatusList;

    /**
     * 结算日期
     */
    private String checkOutDate;

    private String merchantCode;
}
