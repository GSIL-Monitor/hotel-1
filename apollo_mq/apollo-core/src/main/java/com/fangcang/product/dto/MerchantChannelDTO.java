package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class MerchantChannelDTO implements Serializable {

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 是否开通(0未开通  1已开通)
     */
    private Integer isOpen;

    /**
     * 渠道币种
     */
    private String channelCurrency;

}
