package com.fangcang.order.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单列表查询对象
 *
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class OrderListRequestDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = 8896379410533277124L;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 日期查询类型，1：下单日期，2：入住日期，3：离店日期
     */
    private Integer dateQueryType;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 创建日期开始
     */
    private String createDateStart;

    /**
     * 创建日期结束
     */
    private String createDateEnd;

    /**
     * 入住日期开始
     */
    private String checkInDateStart;

    /**
     * 入住日期结束
     */
    private String checkInDateEnd;

    /**
     * 离店日期开始
     */
    private String checkOutDateStart;

    /**
     * 离店日期结束
     */
    private String checkOutDateEnd;

    /**
     * 客人名称
     */
    private String guest;

    /**
     * 酒店ID，有ID就不会根据酒店名称模糊查询
     */
    private Integer hotelId;


    /**
     * 酒店名称，支持模糊查询
     */
    private String hotelName;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账
     */
    private Integer payStatus;

    /**
     * 多支付状态查询
     */
    private List<Integer> payStatusList;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    private Integer orderStatus;

    /**
     * 多订单状态查询
     */
    private List<Integer> orderStatusList;

    /**
     * 渠道编码， 支持多个渠道同时查询
     */
    private String channelCode;

    /**
     * 快捷查询类型，1:我的订单，2：今日入住，3：未处理订单，4：取消订单，5：今日新订单，6：手工单，7：待支付，8已支付，9待确认
     */
    private Integer quickQueryType;

    /**
     * 1:团房，0：散房，不传查全部
     */
    private Integer isGroupRoom;
    /**
     * 供货单号
     */
    private String supplyOrderCode;
    /**
     * 确认号
     */
    private String confirmNo;
    /**
     * 客户单号
     */
    private String customerOrderCode;
    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;
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
    /**
     * 供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    private Integer supplyStatus;
    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 操作人帐号
     */
    private String operatorUser;

    /**
     * 订单归属人名称
     */
    private String belongName;

    /**
     * 供应商编码
     */
    private String supplyCode;


}
