package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供货单附加项对象
 */
@Data
public class SupplyAdditionChargeDTO implements Serializable {

    private static final long serialVersionUID = 8190911269846340441L;

    private Integer supplyAdditionChargeId;

    /**
     * 供货单id
     */
    private Integer supplyOrderId;

    /**
     * 附加项名称
     */
    private String name;

    /**
     * 附加项总底价
     */
    private BigDecimal basePriceSum;

    /**
     * 附加项总售价
     */
    private BigDecimal salePriceSum;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 附加费类型：1附加项（ 加房/ 加早/其他）、2退改费、3减免政策、4核销单
     */
    private Integer additionType;

    /**
     * 减免类型，1全陪免半，2：8免半16免1
     */
    private Integer derateType;

    /**
     * 附加项底价
     */
    private BigDecimal basePrice;

    /**
     * 附加项售价
     */
    private BigDecimal salePrice;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

}