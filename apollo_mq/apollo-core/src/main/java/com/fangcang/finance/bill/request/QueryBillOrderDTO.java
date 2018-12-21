package com.fangcang.finance.bill.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryBillOrderDTO extends BaseQueryConditionDTO {

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 结算方式：0全部 1单结 2非单结
     */
    private Integer balanceMethodType;
}
