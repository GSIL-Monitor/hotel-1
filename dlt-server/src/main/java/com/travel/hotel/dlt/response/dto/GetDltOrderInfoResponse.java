package com.travel.hotel.dlt.response.dto;

import com.travel.hotel.dlt.response.base.BaseResponse;
import lombok.Data;

/**
 *   2018/4/8.
 */
@Data
public class GetDltOrderInfoResponse extends BaseResponse{

    private DltOrderInfo dltOrderInfo;
}
