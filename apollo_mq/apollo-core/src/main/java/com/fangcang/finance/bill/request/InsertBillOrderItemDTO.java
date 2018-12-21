package com.fangcang.finance.bill.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InsertBillOrderItemDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 订单号
     */
    private List<Integer> orderIdList;

    /**
     * 操作人
     */
    private String operator;
}
