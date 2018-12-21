package com.fangcang.finance.prepay.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class PrepayContractItemDTO implements Serializable {
    private static final long serialVersionUID = -8883292096621734122L;
    private Integer id;

    /**
     * 合约id
     */
    private Integer contractId;

    /**
     * 收付款类型（1收款,2付款）
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 备注
     */
    private String note;

    /**
     * 对象类型，1账单，2订单
     */
    private Integer orderType;

    /**
     * 类型1为账单编码，类型2为订单id
     */
    private String orderCode;

    /**
     * 类型1为账单名称
     */
    private String billName;

    /**
     * 财务工单编码
     */
    private String financeCode;

    private String creator;

    private String createTime;

    private String modifier;

    private Date modifyTime;

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 币种
     */
    private String currency;

}