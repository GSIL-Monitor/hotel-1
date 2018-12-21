package com.fangcang.finance.bill.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class AddBillOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @NotNull
    private Integer billId;

    @NotEmpty
    private List<Integer> orderIdList;

    private String merchantCode;

    private String operator;
}
