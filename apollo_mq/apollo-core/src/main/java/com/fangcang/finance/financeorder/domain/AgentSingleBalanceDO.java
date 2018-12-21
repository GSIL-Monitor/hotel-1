package com.fangcang.finance.financeorder.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vinney on 2018/7/19.
 */
@Data
public class AgentSingleBalanceDO implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    private Byte orderStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    private Byte payStatus;

    private Byte accountStatus;

    /**
     * 支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     */
    private Byte payMethod;

    /**
     * 分销商币种
     */
    private String saleCurrency;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    private Byte balanceMethod;

    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;

    private BigDecimal hasReceivedAmount;

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
     * 商家名称
     */
    private String merchantName;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 房型名称，多个房型以逗号隔开（冗余字段）
     */
    private String roomTypeNames;

    /**
     * 入住开始日期
     */
    private Date checkinStartDate;

    /**
     * 入住结束日期
     */
    private Date checkinEndDate;

    /**
     * 离店开始日期
     */
    private Date checkoutStartDate;

    /**
     * 离店结束日期
     */
    private Date checkoutEndDate;

    private Date createStartDate;
    private Date createEndDate;

    private Date modifyStartDate;
    private Date modifyEndDate;

    private Date checkinDate;
    private Date checkoutDate;

    /**
     * 间数， 团房取第一个产品的售晚
     */
    private Integer roomNum;


    /**
     * 是否团房订单
     */
    private Byte isGroupRoom;

    /**
     * 所有入住人名单，多个以逗号隔开（冗余字段）
     */
    private String guestNames;

    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    private Byte breakfastType;

    /**
     * 价格计划名称，团房取第一个产品名称
     */
    private String rateplanName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;

}
