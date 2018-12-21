package com.fangcang.finance.invoice.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class AddInvoiceItemDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @NotNull
    private Integer invoiceId;

    /**
     * 开票方式：1按订单开票2按账单开票
     */
    private Integer invoiceMethod;

    @NotEmpty
    private List<String> orderCodeList;

    private String operator;
}
