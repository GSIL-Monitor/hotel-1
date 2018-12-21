package com.travel.hotel.dlt.request.dto;

import com.travel.hotel.dlt.request.base.BaseRequest;
import com.travel.hotel.dlt.request.base.Requestor;
import lombok.Data;

/**
 *   2018/4/8.
 */
@Data
public class GetDltOrderInfoRequest extends BaseRequest {

    private Requestor requestor;
    private Integer supplierID;
    private String dltOrderId;
}
