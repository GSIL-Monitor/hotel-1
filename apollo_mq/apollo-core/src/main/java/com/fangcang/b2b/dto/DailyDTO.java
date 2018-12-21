package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class DailyDTO implements Serializable{
    private static final long serialVersionUID = -1920719176901921155L;

    /**
     * 日期
     */
    private String saleDate;

    /**
     * 售价
     */
    private BigDecimal salePrice;

    /**
     * 低价
     */
    private BigDecimal basePrice;

    /**
     * 是否可超
     */
    private Integer overDraft;

    /**
     * 配额数
     */
    private Integer quotaNum;
}
