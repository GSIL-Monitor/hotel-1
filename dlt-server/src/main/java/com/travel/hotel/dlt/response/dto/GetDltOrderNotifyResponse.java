package com.travel.hotel.dlt.response.dto;

import com.travel.hotel.dlt.request.base.PagingType;
import com.travel.hotel.dlt.response.base.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 *   2018/4/8.
 */
@Data
public class GetDltOrderNotifyResponse extends BaseResponse{

    private List<String> dltOrderIds;
    private PagingType pagingType;


    private List<GetDltOrderNotifyOrderListDTO> dltOrderList;

}
