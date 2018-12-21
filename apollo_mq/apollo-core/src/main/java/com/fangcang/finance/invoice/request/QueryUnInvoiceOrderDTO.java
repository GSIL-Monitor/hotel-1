package com.fangcang.finance.invoice.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryUnInvoiceOrderDTO extends BaseQueryConditionDTO{

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

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 发票状态：0全部，1未开票，2已开票
     */
    private Integer invoiceStatus;

    private String merchantCode;

    private String orgCode;
}
