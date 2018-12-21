package com.fangcang.finance.invoice.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class InvoiceSummaryDTO implements Serializable {

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 未开发票金额
     */
    private BigDecimal unInvoiceAmount;

    /**
     * 已开发票金额
     */
    private BigDecimal invoiceAmount;
}
