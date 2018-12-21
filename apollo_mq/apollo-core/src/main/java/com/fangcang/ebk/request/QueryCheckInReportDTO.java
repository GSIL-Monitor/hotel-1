package com.fangcang.ebk.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

@Data
public class QueryCheckInReportDTO extends BaseQueryConditionDTO {

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 酒店id
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 供应商编码
     */
    private String supplyCode;
}
