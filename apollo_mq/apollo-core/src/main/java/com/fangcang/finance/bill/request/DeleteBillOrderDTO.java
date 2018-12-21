package com.fangcang.finance.bill.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class DeleteBillOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @NotNull
    private Integer billId;

    @NotNull
    private List<Integer> billOrderIdList;

    private String operator;
}
