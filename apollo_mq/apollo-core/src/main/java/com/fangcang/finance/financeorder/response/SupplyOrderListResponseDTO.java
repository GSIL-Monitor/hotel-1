package com.fangcang.finance.financeorder.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Vinney on 2018/7/8.
 * 分销商单结订单列表
 */
@Data
public class SupplyOrderListResponseDTO implements Serializable {

    private Integer orderId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    private String supplyName;

    private String supplyOrderCode;

    private Long supplyOrderId;

    private String hotelName;

    private String roomTypeNames;

    private String guestNames;

    private Date checkinDate;

    private Date checkoutDate;

    /**
     * 间数
     */
    private Integer roomNum;

    private BigDecimal shouldPay;

    private BigDecimal hasPaid;

    /**
     * 订单结算状态
     * 0-未结算
     * 1-已结算
     */
    private Integer orderFinanceStatus;

    private String currency;


    /**
     * 价格计划名称
     */
    private String rateplanName;

    /**
     * 工单列表
     */
    private List<FinanceOrderResponseDTO> financeOrderList;
}
