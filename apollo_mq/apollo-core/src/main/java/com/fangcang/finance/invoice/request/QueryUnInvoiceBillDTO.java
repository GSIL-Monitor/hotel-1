package com.fangcang.finance.invoice.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryUnInvoiceBillDTO extends BaseQueryConditionDTO{

    /**
     * 日期类型：1创建时间2账期
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
     * 账单号
     */
    private String billCode;

    /**
     * 发票状态：0全部，1未开票，2已开票
     */
    private Integer invoiceStatus;

    private String orgCode;

    private String merchantCode;
}
