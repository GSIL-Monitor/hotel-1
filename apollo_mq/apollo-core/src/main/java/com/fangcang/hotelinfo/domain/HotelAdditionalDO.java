package com.fangcang.hotelinfo.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HotelAdditionalDO extends BaseDO{
    /**
     * 附加项ID
     */
    private Long additionalId;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 附加费类型
     */
    private Integer additionalType;

    /**
     * 附加项名称
     */
    private String additionalName;

    /**
     * 附加项价格
     */
    private BigDecimal additionalPrice;

    /**
     * 商家编码
     */
    private String merchantCode;


}