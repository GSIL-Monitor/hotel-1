package com.fangcang.order.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AddAbatementOrderDTO implements Serializable{

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 核销单名称
     */
    private String name;

    /**
     * 分销商金额
     */
    private BigDecimal agentAmount;

    /**
     * 供应商名称
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商金额
     */
    private BigDecimal supplyAmount;

    private String operator;
}
