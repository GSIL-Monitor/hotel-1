package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ASUS on 2018/7/5.
 */
@Data
public class HotelOrderDerateDTO implements Serializable {
    private static final long serialVersionUID = 2056078723598693648L;

    /**
     * 减免政策名称，例如：全陪免半，8免半16免1
     */
    private String name;

    /**
     * 减免政策价格
     */
    private BigDecimal salePriceSum;
}
