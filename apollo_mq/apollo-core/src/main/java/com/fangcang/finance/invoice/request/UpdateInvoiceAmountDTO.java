package com.fangcang.finance.invoice.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateInvoiceAmountDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer invoiceId;

    private List<String> orderCodeList;

    private List<Integer> invoiceItemIdList;

    /**
     * 类型：1加入发票明细，2移除发票明细
     */
    private Integer type;
}
