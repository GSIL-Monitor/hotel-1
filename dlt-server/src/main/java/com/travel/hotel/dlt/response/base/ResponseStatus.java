package com.travel.hotel.dlt.response.base;

import lombok.Data;

import java.util.List;
import java.util.Date;

/**
 * 代理通接口响应状态
 *   2018/4/8.
 */
@Data
public class ResponseStatus {

    private Date Timestamp;
    private String Ack;
    private List<Error> Errors;
    private String Version;
    private String Build;
    private List<Extension> Extension;

}
