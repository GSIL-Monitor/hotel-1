package com.fangcang.finance.financeorder.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryBillRequestDTO extends BaseQueryConditionDTO {

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 页签类型（-1全部，1对账中，2已对账，3待收款，4已收款）
     */
    private Integer tagType=-1;

    /**
     * 账单状态（1对账中，2已对账，3待结算，4已结算，5已取消）
     */
    private Integer billStatus;

    /**
     * 账单编码
     */
    private String billCode;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 账单名/编码
     */
    private String keyWord;

    /**
     * 订单编码
     */
    private String orderCode;

    private String merchantCode;
}
