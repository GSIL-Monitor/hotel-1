package com.fangcang.finance.bill.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateBillOrderFinanceDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer billId;

    private List<Integer> orderIdList;

    private List<Integer> billOrderIdList;

    private Integer accountStatus;

    private Integer settlementStatus;

    /**
     * 1更新，0不更新
     */
    private Integer updateSettlementAmount;
}
