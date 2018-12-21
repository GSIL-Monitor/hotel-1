package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供货单产品对象
 */
@Data
public class SupplyProductDTO implements Serializable {

    private static final long serialVersionUID = 7482031154101674652L;
    /**
     * 主键ID
     */
    private Integer supplyProductId;

    /**
     * 供货单ID
     */
    private Integer supplyOrderId;

    /**
     * 房型id
     */
    private Integer roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 价格计划id
     */
    private Integer rateplanId;

    /**
     * 价格计划名称
     */
    private String rateplanName;

    /**
     * 采购类型(1合约房,2配额房,3包房一,4包房二)
     */
    private Integer quotaType;

    /**
     * 床型，0:单床，1：大床，2：双床，3：三床，4：四床，多个床型用逗号隔开
     */
    private String bedtype;

    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    private Integer breakfastType;

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
     * 总售价
     */
    private BigDecimal salePriceSum;

    /**
     * 总底价
     */
    private BigDecimal basePriceSum;

    /**
     * 配额帐号ID
     */
    private Integer quotaAccountId;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;


}