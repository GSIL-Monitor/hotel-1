package com.travel.hotel.dlt.request.dto;

import com.travel.hotel.dlt.request.base.BaseRequest;
import com.travel.hotel.dlt.request.base.PagingType;
import com.travel.hotel.dlt.request.base.Requestor;
import lombok.Data;

import java.util.Date;

/**
 *   2018/4/8.
 */
@Data
public class GetDltOrderNotifyRequest extends BaseRequest {

    private Requestor requestor;
    private PagingType pagingType;
    private Integer supplierID;

    private String startTime;
    private String endTime;

}
