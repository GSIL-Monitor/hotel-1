package com.fangcang.order.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/7/26
 */
@Data
public class SupplyCreditPayOrReceiptRequestDTO implements Serializable {

    private static final long serialVersionUID = -6651139156051613927L;
    /**
     * 应付金额
     */
    private BigDecimal payableAmount;
    /**
     * 已挂账金额
     */
    private BigDecimal payAmount;

    /**
     * 操作类型：1确认供货单，2不确认供货单，3其他
     */
    private Integer operateType;
    /**
     * 操作人全名
     */
    private String operator;
    /**
     * 商家编码
     */
    private String merchantCode;


    /**
     * 供货单信息
     */
    private Integer supplyOrderId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 供货单编码
     */
    private String supplyOrderCode;

    /**
     * 底价币种
     */
    private String baseCurrency;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    private Integer balanceMethod;

    /**
     * 供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    private Integer supplyStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    private Integer payStatus;


}
