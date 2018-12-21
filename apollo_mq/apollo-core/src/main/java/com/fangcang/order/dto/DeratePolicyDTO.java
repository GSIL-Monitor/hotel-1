package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class DeratePolicyDTO implements Serializable {
    private static final long serialVersionUID = 1631919814237949568L;

    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 供货单id
     */
    private Integer supplyOrderId;

    /**
     * 减免政策名称
     */
    private String name;

    /**
     * 总售价
     */
    private BigDecimal salePriceSum;

    /**
     * 总底价
     */
    private BigDecimal basePriceSum;

    /**
     * 总间夜数
     */
    private BigDecimal roomNumSum;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

}