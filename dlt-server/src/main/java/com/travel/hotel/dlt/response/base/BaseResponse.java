package com.travel.hotel.dlt.response.base;

import lombok.Data;

/**
 *   2018/4/8.
 */
@Data
public class BaseResponse {

    private com.travel.hotel.dlt.response.base.ResponseStatus ResponseStatus;
    private ResultStatus resultStatus;

}
