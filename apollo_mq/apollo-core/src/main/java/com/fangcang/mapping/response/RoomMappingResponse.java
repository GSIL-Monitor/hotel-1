package com.fangcang.mapping.response;

import lombok.Data;

import java.util.List;

/**
 * Created by Owen on 2018/6/8.
 */
@Data
public class RoomMappingResponse {

    private Long roomTypeId;

    private String roomTypeName;

    private List<RatePlanMappingResponse> ratePlanList;
}
