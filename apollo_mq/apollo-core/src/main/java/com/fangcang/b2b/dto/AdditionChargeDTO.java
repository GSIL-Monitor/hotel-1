package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class AdditionChargeDTO implements Serializable{
    private static final long serialVersionUID = 3287676696229529987L;

    /**
     * 附加项类型
     */
    private Integer type;
    /**
     * 附加项名称
     */
    private String name;

    /**
     * 附加项数量
     */
    private Integer num;

    /**
     * 售价
     */
    private BigDecimal salePrice;

    /**
     * 天数
     */
    private Integer days;
}
