package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对象
 *
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 5171979680939489860L;
    /**
     * 主键
     */
    private Integer orderId;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    private Integer orderStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款，6：已退账
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
     * 是否手工单
     */
    private Integer isManualOrder;

    /**
     * 是否即时确认单，团房该字段为空
     */
    private Integer isImmediatelyConfirm;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    private Integer balanceMethod;

    /**
     * 订单确认号（冗余字段）
     */
    private String confirmNo;

    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;

    /**
     * 已通知财务收款金额
     */
    private BigDecimal paidInAmount;

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
     * 客人特殊要求
     */
    private String specialRequest;

    /**
     * 联系人名称
     */
    private String contractName;

    /**
     * 联系人电话
     */
    private String contractPhone;

    /**
     * 订单归属人帐号
     */
    private String belongUser;

    /**
     * 订单归属人名称
     */
    private String belongName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

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
     * 锁单人帐号
     */
    private String lockUser;

    /**
     * 锁单人名称
     */
    private String lockName;

    /**
     * 锁单时间
     */
    private Date lockTime;

    /**
     * 退改费
     */
    private BigDecimal changeFee;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 确认订单内容
     */
    private String confirmContent;

    /**
     * 客户订单号
     */
    private String customerOrderCode;

    /**
     * 渠道订单状态
     */
    private String channelOrderStatus;

    /**
     * 是否团房订单
     */
    private Integer isGroupRoom;

    /**
     * 所有入住人名单，多个以逗号隔开（冗余字段）
     */
    private String guestNames;

    /**
     * 供货单号（冗余字段）
     */
    private String supplyOrderCodes;

    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    private Integer breakfastType;

    /**
     * 团号，团房订单才有值
     */
    private String groupNo;

    /**
     * 业务经理
     */
    private String merchantBm;

    /**
     * 产品经理
     */
    private String merchantPm;

    /**
     * 供货单状态(冗余字段，多个供货单状态已逗号隔开)，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    private String supplyStatus;

    /**
     * 向导
     */
    private String guide;

    /**
     * 向导电话
     */
    private String guidePhone;

    /**
     * 价格计划名称，团房取第一个产品名称
     */
    private String rateplanName;

    /**
     * 订单是否锁定
     */
    private Integer isLock;

    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * 供货单确认号
     */
    private String supplyConfirmNo;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 子渠道编码
     */
    private String channelSubCode;

    /**
     * 供应商编码（冗余字段）
     */
    private String supplyCodes;

    /**
     * 分销商币种转商家本币汇率
     */
    private BigDecimal rate;

    /**
     * 总利润
     */
    private BigDecimal profit;
}
