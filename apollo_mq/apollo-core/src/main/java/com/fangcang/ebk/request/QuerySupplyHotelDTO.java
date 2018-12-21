package com.fangcang.ebk.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QuerySupplyHotelDTO extends BaseQueryConditionDTO {
    private static final long serialVersionUID = -5532374694995270217L;

    /**
     * 酒店名称
     * */
    private String hotelName;
    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 供应商编码
     */
    private String supplyCode;
}
