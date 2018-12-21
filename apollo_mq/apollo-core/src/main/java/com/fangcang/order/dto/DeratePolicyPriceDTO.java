package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class DeratePolicyPriceDTO implements Serializable {
    private static final long serialVersionUID = 3286929563726214924L;

    private Integer id;

    /**
     * 减免政策id
     */
    private Integer deratePolicyId;

    /**
     * 减免日期
     */
    private String saleDate;

    /**
     * 减免间数
     */
    private BigDecimal roomNum;

    /**
     * 售价
     */
    private BigDecimal salePrice;

    /**
     * 底价
     */
    private BigDecimal basePrice;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

}