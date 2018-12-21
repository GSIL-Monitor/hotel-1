package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/28.
 */
@Data
public class ShowChannelPriceDTO implements Serializable {

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 是否开通  1开通 0未开通
     */
    private Integer isOpen;

    /**
     * 渠道币种
     */
    private String channelCurrency;

    /**
     * 英文价格名称
     */
    private String enPriceName;

    /**
     * 中文价格名称
     */
    private String priceName;

    /**
     * 批量改价的操作类型
     */
    private Integer priceOperateType;
}
