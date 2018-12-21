package com.fangcang.finance.invoice.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UnInvoiceBillDTO implements Serializable{

    /**
     * 账单编码
     */
    private String billCode;

    /**
     * 账单名称
     */
    private String billName;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 账单创建时间
     */
    private String billCreateDate;

    /**
     * 币种
     */
    private String currency;

    /**
     * 应收
     */
    private BigDecimal receivableAmount;

    /**
     * 开票金额
     */
    private BigDecimal invoiceAmount;
}
