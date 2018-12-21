package com.fangcang.finance.bill.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryBillCurrencyDTO implements Serializable{

    private Integer billId;

    /**
     * 结算方式：0全部 1单结 2非单结
     */
    private Integer balanceMethodType;
}
