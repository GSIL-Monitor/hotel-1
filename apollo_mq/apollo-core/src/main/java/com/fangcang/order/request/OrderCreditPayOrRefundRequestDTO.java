package com.fangcang.order.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/7/26
 */
@Data
public class OrderCreditPayOrRefundRequestDTO implements Serializable {
    private static final long serialVersionUID = -2593419224298718946L;

    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;
    /**
     * 已挂账金额
     */
    private BigDecimal payAmount;

    /**
     * 操作类型：1确认订单，2取消订单，3其他
     */
    private Integer operateType;
    /**
     * 操作人全名
     */
    private String operator;


    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 分销商币种
     */
    private String saleCurrency;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    private Integer orderStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    private Integer payStatus;
}
