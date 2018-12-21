package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ASUS on 2018/7/5.
 */
@Data
public class HotelDeratePolicyDailyDTO implements Serializable {
    private static final long serialVersionUID = -4171673403233875026L;

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
}
