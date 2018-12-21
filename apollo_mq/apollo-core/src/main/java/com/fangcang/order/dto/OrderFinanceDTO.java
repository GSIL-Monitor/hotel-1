package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class OrderFinanceDTO implements Serializable {
    private static final long serialVersionUID = -7817891414666839737L;
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

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

    /**
     * 财务锁单状态，1：已锁定，2：未锁定
     */
    private Byte financeLockStatus;

    private String creator;

    private Date createTime;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 累计退款金额
     */
    private BigDecimal refundAmount;
}