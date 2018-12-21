package com.fangcang.finance.invoice.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryInvoiceDTO extends BaseQueryConditionDTO{

    private String merchantCode;

    /**
     * 发票id
     */
    private Integer invoiceId;

    /**
     * 发票名称
     */
    private String invoiceName;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 开始创建时间
     */
    private String startCreateTime;

    /**
     * 结束创建时间
     */
    private String endCreateTime;

    /**
     * 是否有效
     */
    private Integer isInvalid=1;
}
