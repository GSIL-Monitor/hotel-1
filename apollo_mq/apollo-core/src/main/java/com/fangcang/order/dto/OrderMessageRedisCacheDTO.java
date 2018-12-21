package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhanwang
 * @date : 2018/7/26
 */
@Data
public class OrderMessageRedisCacheDTO implements Serializable {

    private static final long serialVersionUID = 4565036324271295735L;
    /**
     * 主键
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
     * 1新单，2.取消申请，3修改申请
     */
    private Integer orderType;

    /**
     * 创建时间
     */
    private Long createTime;
}
