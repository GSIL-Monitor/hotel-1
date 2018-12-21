package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 供货单对象
 *
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class SupplyOrderDTO implements Serializable {
    private static final long serialVersionUID = 4231945221037238805L;
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
     * 供货单总价
     */
    private BigDecimal supplySum;

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

    /**
     * 产品供应方式， 1商家自签，2系统直连
     */
    private Integer supplyWay;

    /**
     * 应付金额
     */
    private BigDecimal payableAmount;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 供应商确认号码
     */
    private String confirmNo;

    /**
     * 确认人名称
     */
    private String confirmName;

    /**
     * 拒绝原因：1变价，2满房，3，其他自定义
     */
    private String refuseReason;

    /**
     * 总售价
     */
    private BigDecimal salePriceSum;

    private String creator;

    private String createTime;

    private String modifier;

    private String modifyTime;

    /**
     * 产品币种转商家本币汇率
     */
    private BigDecimal rate;

    /**
     * 第一个产品的入住日期
     */
    private String checkinDate;

    /**
     * 第一个产品的离店日期
     */
    private String checkoutDate;

    /**
     * 间数，团房取第一个产品间数
     */
    private Integer roomNum;

    /**
     * 房型名称，团房取第一个房型名称
     */
    private String roomTypeName;

    /**
     * 价格计划名称，团房取第一个产品名称
     */
    private String rateplanName;

    /**
     * 异常金额
     */
    private BigDecimal exceptionAmount;

    /**
     * 业务经理
     */
    private String merchantBm;

    /**
     * 产品经理
     */
    private String merchantPm;

    /**
     * 运营员
     */
    private String merchantOp;
}