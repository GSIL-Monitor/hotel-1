package com.fangcang.product.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SharedPoolQueryDTO implements Serializable {

    private static final long serialVersionUID = -8904252977600994743L;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 商家编码
     */
    private String merchantCode;
}
