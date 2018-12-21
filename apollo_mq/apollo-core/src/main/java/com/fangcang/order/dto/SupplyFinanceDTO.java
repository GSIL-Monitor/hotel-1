package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class SupplyFinanceDTO implements Serializable {
    private static final long serialVersionUID = -2653133363467267754L;
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 供货单id
     */
    private Integer supplyOrderId;

    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;

    /**
     * 结算状态：0未结算，1已结算
     */
    private Byte settlementStatus;

    /**
     * 对账状态：0新建，1可出账，2已纳入账单，3已对账
     */
    private Byte accountStatus;

    private String creator;

    private Date createTime;

    /**
     * 供货单编码
     */
    private String supplyOrderCode;

    /**
     * 累计已收款金额
     */
    private BigDecimal receiptAmount;

}