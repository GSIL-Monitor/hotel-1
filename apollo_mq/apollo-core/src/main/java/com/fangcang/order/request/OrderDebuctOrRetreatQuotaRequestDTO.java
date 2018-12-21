package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 扣还配额请求对象
 *
 * @author : zhanwang
 * @date : 2018/6/4
 */
@Data
public class OrderDebuctOrRetreatQuotaRequestDTO implements Serializable {

    private static final long serialVersionUID = -1010328768339635941L;
    /**
     * 配额类型(1 扣配额,2 退配额)
     */
    private Integer debuctQuotaType;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 订单Id
     */
    private Integer orderId;
    /**
     * 供货单id
     */
    private Integer supplyOrderId;
    /**
     * 供货单编码
     */
    private String supplyOrderCode;
    /**
     * 价格计划id
     */
    private Integer ratePlanId;
    /**
     * 配额帐号id
     */
    private Integer quotaAccountId;
    /**
     * 间数
     */
    private Integer roomNum;

    /**
     * 入住日期
     */
    private Date startDate;

    /**
     * 离店日期
     */
    private Date endDate;

    /**
     * 日期明细
     */
    private List<String> dateList;

    /**
     * 商家编码
     */
    private String merchantCode;
}
