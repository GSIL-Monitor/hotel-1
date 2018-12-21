package com.fangcang.order.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 供货单附加项对象
 *
 * @author zhanwang
 */
@Data
public class CreateOrderAdditionChargeRequestDTO implements Serializable {


    private static final long serialVersionUID = -7197643273312082751L;

    /**
     * 附加项名称
     */
    private String name;

    /**
     * 附加项总售价
     */
    private BigDecimal salePriceSum;
    /**
     * 附加项总底价
     */
    private BigDecimal basePriceSum;

    /**
     * 购买数量
     */
    private Integer num;
    /**
     * 天数
     */
    private Integer days;

    /**
     * 附加项底价
     */
    private BigDecimal basePrice;

    /**
     * 附加项售价
     */
    private BigDecimal salePrice;

}