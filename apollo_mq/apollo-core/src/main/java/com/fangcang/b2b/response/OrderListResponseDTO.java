package com.fangcang.b2b.response;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class OrderListResponseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 8486246857397705183L;

    /**
     * 主键
     */
    private Long orderId;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    private Integer orderStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    private Integer payStatus;

    /**
     * 支付方式, 1：现付有佣金，2：预付，3：现付无佣金
     */
    private Integer payMethod;

    /**
     * 分销商币种
     */
    private String saleCurrency;

    /**
     * 渠道编码
     */
    private String channelCode;
    private String channelName;

    /**
     * 订单总价
     */
    private BigDecimal orderSum;

    /**
     * 订单确认号（冗余字段）
     */
    private String confirmNo;


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
     * 第一个产品的入住日期
     */
    private String checkinDate;

    /**
     * 第一个产品的离店日期
     */
    private String checkoutDate;

    /**
     * 间数， 团房取第一个产品的售晚
     */
    private Integer roomNum;

    /**
     * 是否团房订单
     */
    private Integer isGroupRoom;

    /**
     * 所有入住人名单，多个以逗号隔开（冗余字段）
     */
    private String guestNames;

    /**
     * 价格计划名称，团房取第一个产品名称
     */
    private String rateplanName;

    /**
     * 晚
     */
    private Long night;

    /**
     * 向导
     */
    private String guide;

    /**
     * 向导电话
     */
    private String guidePhone;
}
