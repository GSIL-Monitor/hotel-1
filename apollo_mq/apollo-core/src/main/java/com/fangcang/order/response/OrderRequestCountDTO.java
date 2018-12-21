package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRequestCountDTO implements Serializable{

    private Integer orderId;

    private Integer modifyCount;

    private Integer cancelCount;

    private Integer delayCount;
}
