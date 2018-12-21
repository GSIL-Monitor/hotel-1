package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vinney on 2018/8/28.
 */
@Data
public class GetDltOrderNotifyOrderListDTO implements Serializable {

    private String dltOrderId;
    private String orderId;
    private String channel;
    private String childChannel;
    private Date updateTime;
    private Date orderDate;
    private String formType;
    private String orderStatus;
    private Date checkinDate;
    private Date checkoutDate;

}
