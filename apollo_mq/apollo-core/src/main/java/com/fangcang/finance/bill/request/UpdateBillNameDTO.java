package com.fangcang.finance.bill.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateBillNameDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 账单名称
     */
    private String billName;

    private String operator;
}
