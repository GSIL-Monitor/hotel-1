package com.fangcang.finance.invoice.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryInvoiceSummaryDTO extends BaseQueryConditionDTO {

    private String merchantCode;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 日期类型：1下单日期2入住日期3离店日期
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
}
