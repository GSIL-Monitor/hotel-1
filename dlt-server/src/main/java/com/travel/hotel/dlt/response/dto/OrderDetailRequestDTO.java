package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class OrderDetailRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 3349539375134666822L;

    /**
     * 订单ID, 和订单编码必须有一个有值
     */
    private Integer orderId;
    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 商家编码
     */
    private String merchantCode;


}
