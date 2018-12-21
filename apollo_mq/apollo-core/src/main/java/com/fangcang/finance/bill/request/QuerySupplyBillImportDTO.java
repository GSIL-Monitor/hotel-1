package com.fangcang.finance.bill.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QuerySupplyBillImportDTO extends BaseQueryConditionDTO {

    /**
     * 导入批次
     */
    private Integer importNo;

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
     * 酒店名称
     */
    private String hotelName;

    /**
     * 入住人
     */
    private String guest;

    /**
     * 1已匹配、2未匹配、-1全部
     */
    private Integer matchStatus;
}
