package com.fangcang.mapping.request;

import lombok.Data;

/**
 * Created by Owen on 2018/6/8.
 */
@Data
public class MappingQueryRequest {

    private String merchantCode;

    private Integer mappingStatus;

    private Integer currentPage=1;

    private Integer pageSize=10;

    private String cityCode;

    private String hotelId;

}
