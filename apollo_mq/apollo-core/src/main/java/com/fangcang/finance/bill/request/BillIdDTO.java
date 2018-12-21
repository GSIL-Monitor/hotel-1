package com.fangcang.finance.bill.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BillIdDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @NotNull
    private Integer billId;

    private String operator;
}
