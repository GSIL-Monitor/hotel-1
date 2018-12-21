package com.fangcang.finance.invoice.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class DeleteInvoiceItemDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @NotNull
    private Integer invoiceId;

    @NotEmpty
    private List<Integer> invoiceItemIdList;

    private String operator;
}
