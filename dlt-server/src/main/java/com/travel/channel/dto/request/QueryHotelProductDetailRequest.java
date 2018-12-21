package com.travel.channel.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *   2018/2/28.
 */
@Data
public class QueryHotelProductDetailRequest implements Serializable {

    private static final long serialVersionUID = 588214080851408901L;
    private Long pricePlanId;

    private Date checkInDate;

    private Date checkOutDate;

    private String channelCode;

}
