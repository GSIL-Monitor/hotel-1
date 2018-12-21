package com.fangcang.finance.financelock.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/7/4
 */
@Data
public class UnlockOrderListResponseDTO implements Serializable {
    private static final long serialVersionUID = 8286626408094071248L;

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 分销商名称
     */
    private String agentName;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 入住日期
     */
    private String checkinDate;
    /**
     * 离店日期
     */
    private String checkoutDate;
    /**
     * 间数
     */
    private Integer roomNum;
    /**
     * 入住人名称
     */
    private String guestNames;

    /**
     * 财务锁单状态，1：已锁定，2：未锁定
     */
    private Integer financeLockStatus;
    /**
     * 对账状态（已对账不支持解锁）：1：未对账，2：已对账，3：已结算
     */
    private Integer accountStatus;
}
