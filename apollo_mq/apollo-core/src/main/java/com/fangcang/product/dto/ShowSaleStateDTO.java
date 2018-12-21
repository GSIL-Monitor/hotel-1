package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/1.
 */
@Data
public class ShowSaleStateDTO implements Serializable {

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 渠道Code
     */
    private String channelCode;

    /**
     * 售卖名称
     */
    private String saleStateName;

    /**
     * 是否开通  1开通 0未开通
     */
    private Integer isOpen;
}
