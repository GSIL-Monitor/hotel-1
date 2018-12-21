package com.fangcang.report.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.util.List;

@Data
public class QueryRoomNightReportDTO extends BaseQueryConditionDTO {

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
     * 酒店id列表","隔开
     */
    private List<Integer> hotelIdList;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 供货单状态
     */
    private List<Integer> supplyStatusList;

    /**
     * 商家编码
     */
    private String merchantCode;
}
