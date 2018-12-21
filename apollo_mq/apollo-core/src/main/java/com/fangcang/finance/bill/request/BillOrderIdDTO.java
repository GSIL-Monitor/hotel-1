package com.fangcang.finance.bill.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BillOrderIdDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单明细id
     */
    private Integer billOrderId;

    /**
     * 账单id
     */
    private Integer billId;
}
