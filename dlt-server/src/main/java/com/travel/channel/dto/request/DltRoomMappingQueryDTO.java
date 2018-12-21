package com.travel.channel.dto.request;

import lombok.Data;

/**
 * 代理通酒店匹配查询DTO
 *   2018/5/2.
 */
@Data
public class DltRoomMappingQueryDTO {

    private String cityCode;
    private String cityName;
    private String hotelId;
    private String hotelName;
    private Integer mappingStatus;

    /**
     * 每页显示数量
     */
    private Integer pageSize = 5;

    /**
     * 当前页数
     */
    private Integer currentPage = 1;

}
